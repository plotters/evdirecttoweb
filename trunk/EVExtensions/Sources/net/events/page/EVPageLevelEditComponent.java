package net.events.page;

import net.events.extensions.EVDeletedObjectException;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOGeneralAdaptorException;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOGlobalID;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSNotification;
import com.webobjects.foundation.NSNotificationCenter;
import com.webobjects.foundation.NSSelector;

import er.extensions.ERXArrayUtilities;
import er.extensions.ERXConstant;
import er.extensions.ERXEC;
import er.extensions.ERXEOControlUtilities;
import er.extensions.ERXGenericRecord;
import er.extensions.ERXSession;

/**
 * @author cug
 *
 */
public abstract class EVPageLevelEditComponent extends EVPageLevelComponent {
	
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

	public EVPageLevelEditComponent(WOContext context) {
		super(context);
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
	
	// empty implementations - just for convenience not to have errors in the subclasses
	// edit components generally don't need this stuff right now
	protected void prepareInitialFetchSpecification() {}
	
	
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
	 * Creates a "VerifyDeletePage" to verify, that the user is user to delete this object
	 * 
	 * @return VerifyDeletePage
	 */
	public WOComponent verifyDelete () {
		EVVerifyDeletePage nextpage = (EVVerifyDeletePage)this.pageWithName(this.verifyDeletePageName());
		
		nextpage.setMessage("AreYouSureToDelete");
		nextpage.setObject(this.object());
		nextpage.setCaller(this);
		
		return nextpage;
	}
	
	/**
	 * Override this method, if your VerifyDeletePage has not the name "VerifyDelete"
	 * 
	 * @return "VerifyDelete"
	 */
	public String verifyDeletePageName() {
		return "VerifyDelete";
	}
	
	/**
	 * Callback method from VerifyDelete page, returns this
	 */
	public WOComponent cancelDeletion () {
		this.setEditing(false);
		
		return this;
	}
	
	/**
	 * Switches the boolean "editing", this is for the use with SSSwitchableString.framework 
	 * 
	 * @return same page
	 */
	public WOComponent switchEditingMode () {
		if (!editing) {
			this.setNextPage(this);
		}
		else {
			if (!this.object().isNewObject()) { 
				if (this.object.editingContext().parentObjectStore() != null && this.object.editingContext().parentObjectStore() instanceof ERXEC) {
					((ERXEC) this.object.editingContext().parentObjectStore()).revert();
				}
				this.object().editingContext().revert();
			}
			else {
				// The object is a newly created instance and the user cancels the creation
				// while the object has not beed saved - so we go back to the last page, 
				// and if this is not set, to the home page
				if (this.lastPage() != null && this.lastPage() != this) {
					return this.lastPage();
				}
				// FIXME This should not be set by string, rather determined at runtime
				else return pageWithName ("Home");
			}
		}
		this.editing = !this.editing;
		
		return this;
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
	
	/**
	 * Returns whether our object has been saved to the db before (indicating whether we are
	 * creating a new object or changing an old one)
	 * 
	 * @return true, if object has never been saved to the db
	 */
	public boolean objectHasNotBeenSaved () {
		return ((ERXGenericRecord) object).isNewObject();
	}
	

	/**
	 * Convenience method - returns a string that can be used as a page header. If we are in editing mode
	 * it returns "Edit Entity", in inspect mode it returns "Inspect Entity", where "Entity" is fetched
	 * from the current <code>object.entityName()</code>
	 * 
	 * @return string for header
	 */
	public String headerString () {
		if (this.editing()) {
			return "Edit " + this.object().entityName();
		}
		else {
			return "Inspect "+ this.object().entityName();
		}
	}

	/**
	 * Convenience method that can be used to display header for the editing form. It
	 * takes the objects entity name from this page, and returns strings like "New Entityname",
	 * "Edit Entityname" and "Inspect Entityname".
	 * 
	 * So, if your entities are named properly, you can use this directly, or correct the 
	 * whole string in Localizable.strings
	 * 
	 * Perhaps this with change in the future, so you get a directly translated entry for less
	 * entries in Localizable.strings.
	 *   
	 * @return string
	 */
	public String genericHeadLineString () {
		if (this.editing) {
			if (this.objectHasNotBeenSaved()) {
				return "New " + this.entityName();
			}
			return "Edit " + this.entityName();
		}
		else {
			return "Inspect " + this.entityName();
		}
	}

	/**
	 * @return the returnToLastpageAfterSave
	 */
	public boolean returnToLastpageAfterSave() {
		return returnToLastpageAfterSave;
	}

	/**
	 * @param returnToLastpageAfterSave the returnToLastpageAfterSave to set
	 */
	public void setReturnToLastpageAfterSave(boolean returnToLastpageAfterSave) {
		this.returnToLastpageAfterSave = returnToLastpageAfterSave;
	}

}
