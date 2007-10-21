package net.events.cms.extensions;

import net.events.extensions.*;
import net.events.page.*;

import org.apache.log4j.*;

import com.webobjects.appserver.*;
import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public class EVCMSEditPage extends EVCMSPage {
	
	private NSMutableDictionary validationDictionary;
	private NSMutableArray validationErrors;
	private ERXGenericRecord object;
	private WOComponent nextPage;
	private static Logger log = Logger.getLogger(EVPageLevelEditComponent.class);
	private String validationError;
	
	private boolean returnToLastpageAfterSave = false;

	// are we in editing or inspecting mode?
    private boolean editing;
    
    protected ERXEC editingContext;
    
    // holds whether we should do a refresh on awake
	private boolean refresh;

	public EVCMSEditPage(WOContext c) {
		super(c);
		editingContext = (ERXEC) ERXEC.newEditingContext();

		NSNotificationCenter.defaultCenter().addObserver(this, 
						new NSSelector("editingContextDidSaveChanges", ERXConstant.NotificationClassArray), 
						EOEditingContext.EditingContextDidSaveChangesNotification, null);

	}

	
	/**
	 * Selector for catching the notification that somewhere around us, an editing
	 * context has saved its changes. This forces us to reload our own object, because
	 * we don't want to handle problems with not displaying the correct content
	 * 
	 * @param notif
	 */
	public void editingContextDidSaveChanges(NSNotification notif) {
		// when we load our page again, we will refresh our object by invalidating it,
		// it may be that something we want to display has changed
		
		this.refresh = true;
	}
	
	/**
	 * Checks whether we still have our object
	 * 
	 * @see com.webobjects.appserver.WOComponent#awake()
	 */
	public void awake () {
		super.awake();
		
		if (this.object() != null && this.object().editingContext() == null) {
			// our object was deleted in another thread
			throw new EVDeletedObjectException (EVDeletedObjectException.DELETED_OBJECT_MESSAGE);
		}
		
		
		// TODO Check whether this really works out!
		if (this.refresh && this.object() != null && this.object().editingContext() != null && !this.object().isNewObject()) {
			// we should refresh our object - something has changed in the object graph 
			EOGlobalID gid = this.object().editingContext().globalIDForObject(this.object());
			this.object().editingContext().invalidateObjectsWithGlobalIDs(new NSArray (gid));
			this.object = (ERXGenericRecord) this.editingContext.objectForGlobalID(gid);
			
			this.refresh = false;
		}
		
	}
	
	/**
	 * Returns the entity name for "object" eo on this page 
	 * @return
	 */
	protected String entityName () { 
		if (this.object() != null) {
			return EOUtilities.entityForObject(this.object().editingContext(), this.object()).name();
		}
		return "";
	}

	/**
	 * save changes
	 */
	public WOComponent saveChanges() {
		
		// we start with empty validation error containers
		validationDictionary = new NSMutableDictionary();
		validationErrors = new NSMutableArray();

		try {
			this.object().editingContext().saveChanges();
			this.setEditing(false);
		}
		catch (ValidationException e) {
			if (log.isDebugEnabled()) e.printStackTrace();
			this.setEditing(true);
			
			this.validationFailedWithException(e, e.object(), e.key());
			
			if (e.additionalExceptions() != null) {
				
				this.validationFailedWithException(e, e.object(), e.key());
				
				for (int i = 0; i < e.additionalExceptions().count(); i++) {
					ValidationException item = (ValidationException) e.additionalExceptions().objectAtIndex(i);
					this.validationFailedWithException(item, item.object(), item.key());
				}
			}
			
			return null;
		}
		catch (EOGeneralAdaptorException ae) {
			ae.printStackTrace();
			throw new EVDeletedObjectException (EVDeletedObjectException.DELETED_OBJECT_MESSAGE);
		}
		catch (Exception e) {
			this.setEditing(true);
			e.printStackTrace();
			return null;
		}
		
		if (this.returnToLastpageAfterSave && this.lastPage() != null) {
			return lastPage();
		}
		else {
			return null;	
		}
	}
	
	public WOComponent cancel () {
		this.validationDictionary = null;
		this.validationErrors = null;
		
		this.editing = false;
		
		this.object.editingContext().revert();
		
		if (this.object().editingContext() == null || this.object().isNewObject()) {
			// means, we were editing a new object, when someone hit cancel
			// with setting the same object, we don't loose anything and it gets inserted
			// in the editing context again - so, if someone comes back to this
			// page with a back button, it shouldn't be a problem
			ERXGenericRecord eo = this.object();
			this.setObject(eo);
			
			this.setEditing(true);
		}
		
		if (nextPage() != null && !this.object().isNewObject()) {
			// Last case might happen when a user comes back with the back button. 
			return nextPage();
		}
		else if (lastPage() != null) {
			return this.lastPage();
		}
		else {
			// FIXME - generate some better error messages
			// shit happens
			throw new EVDeletedObjectException(EVDeletedObjectException.DELETED_OBJECT_MESSAGE);
		}
	}
	
	/**
	 * Deletes the displayed object from the database
	 * 
	 * @return lastpage or nextpage or, if nothing is set, "Home"
	 */
	public WOComponent deleteObject () {
		log.info ("Deleting object: " + this.object());
		
		ERXEC editingContext = (ERXEC) this.object().editingContext();
		editingContext.deleteObject(this.object());
		
		try {
			// we need this for later re-use
			String entityNameForDeletedObject = this.object().entityName();
			
			editingContext.saveChanges();
			
			// wait ... wait ... wait ... no crash - we successfully deleted our object
			// if someone now wants to use the back button she should not see our object again,
			// therefor we insert a new blank object
			this.setObject((ERXGenericRecord) ERXEOControlUtilities.createAndInsertObject(editingContext, entityNameForDeletedObject));
		}
		catch (EOGeneralAdaptorException ae) {
			// FIXME place a more correct message here
			throw new EVDeletedObjectException (EVDeletedObjectException.DELETED_OBJECT_MESSAGE);
		}
		catch (Exception e) {
			// FIXME do some more handling here 
			e.printStackTrace();
			return null;
		}
		if (this.nextPage() != null && this.nextPage() != this) {
			return this.nextPage();
		}
		else if (this.lastPage() != null && this.lastPage() != this) {
			return this.lastPage();
		}
		else return pageWithName ("Home");
	}
	
	
	/**
	 * Returns true, if the validation array contains at least one error
	 * @return true or false
	 */
	public boolean hasValidationErrors () {
		return this.validationErrors.count() > 0;
	}

	/**
	 * Collects the validation errors when "object" is saved
	 */
	public void validationFailedWithException (
			java.lang.Throwable exception, 
			java.lang.Object value, 
			java.lang.String keyPath) {
		
		if (validationDictionary == null) validationDictionary = new NSMutableDictionary();
		if (validationErrors == null) validationErrors = new NSMutableArray();
		
		this.validationErrors.addObject(exception.getMessage());

		String prefix = "object.";
		String field;
		if (keyPath != null) {
			if (keyPath.startsWith(prefix)) {
				field = keyPath.substring(prefix.length());
			}
			else {
				field = keyPath;
			}

			this.validationDictionary.setObjectForKey("err_label", field + "Class");
			
			if (keyPath.startsWith(prefix)) {
				// we got the callback - not a subcomponent
				// try to force the value into the field:
				try {
					takeValueForKeyPath(value, keyPath);
				} catch (IllegalArgumentException e) {
					// just ignore
				}
			}
		}
	}
	
	/**
	 * @return editing mode
	 */
	public boolean editing() {
		return this.editing;
	}
	
	/**
	 * @return not (editing mode), means true when we are not editing 
	 */
	public boolean inspecting () {
		return !this.editing;
	}

	/**
	 * @param set editing mode
	 */
	public void setEditing(boolean editing) {
		this.editing = editing;
	}


	/**
	 * Holds validation errors after a "saveChanges" was called on an editing context. Exceptions are
	 * not catched, only collected by "validationFailedWithException"
	 * 
	 * @return dictionary with validation messages.
	 */
	public NSMutableDictionary validationDictionary() {
		return validationDictionary;
	}

	/**
	 * @param validationDictionary the validationDictionary to set
	 */
	public void setValidationDictionary(NSMutableDictionary validationDictionary) {
		this.validationDictionary = validationDictionary;
	}

	/**
	 * @return the object
	 */
	public ERXGenericRecord object() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(ERXGenericRecord object) {
		if (object != null) {
			if (object.editingContext() != null) {
				// editingContext = (ERXEC) ERXEC.newEditingContext(object.editingContext());
				this.object = (ERXGenericRecord) ERXEOControlUtilities.localInstanceOfObject(editingContext, object);
			}
			else {
				this.object = object;
				editingContext.insertObject(this.object);
			}
		}
		else this.object = null;
	}

	public void createNewObjectForEntityName (String entityName) {
		if (log.isDebugEnabled()) log.debug ("Should create and insert object in editing context: " + editingContext);
		this.object = (ERXGenericRecord) ERXEOControlUtilities.createAndInsertObject(editingContext, entityName);
	}

	/**
	 * @return the validationErrors
	 */
	public NSArray validationErrors() {
		if (validationErrors != null && validationErrors.count() > 1) {
			return ERXArrayUtilities.arrayWithoutDuplicates(this.validationErrors);
		}
		else return validationErrors;
	}


	/**
	 * @param validationErrors the validationErrors to set
	 */
	public void setValidationErrors(NSMutableArray validationErrors) {
		this.validationErrors = validationErrors;
	}


	/**
	 * @return the nextpage
	 */
	public WOComponent nextPage() {
		return nextPage;
	}


	/**
	 * @param nextpage the nextpage to set
	 */
	public void setNextPage(WOComponent nextpage) {
		this.nextPage = nextpage;
	}

	/**
	 * @return the validationError
	 */
	public String validationError() {
		return validationError;
	}

	/**
	 * @param validationError the validationError to set
	 */
	public void setValidationError(String validationError) {
		this.validationError = validationError;
	}
	
	public String errorHeaderMessage () {
		String message = "There ";
		if (this.validationErrors != null && this.validationErrors.count() > 1) {
			message = ((ERXSession) session()).localizer().localizedStringForKey("There were errors while saving your input.");
		}
		else if (this.validationErrors != null && this.validationErrors.count() == 1) {
			message = ((ERXSession) session()).localizer().localizedStringForKey("There was an error while saving your input.");
		}
		return message;
	}
	
}
