package net.events.extensions;



import net.events.page.EVPageLevelEditComponent;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.foundation.NSDictionary;

import er.extensions.ERXNonSynchronizingComponent;

/**
 * This component should be used for "inline" editing of EOs. It does not collect validation errors.
 * 
 * @binding object EOEnterpriseObject - the object to edit
 * @binding editing Boolean - whether the form should be in edit or inspect mode, true for editing
 * @binding validationDictionary NSDictionary - a dictionary from the validation errors @see {@link EVPageLevelEditComponent}  
 * 
 * @author cug
 *
 */
public class EVEditObjectFormComponent extends ERXNonSynchronizingComponent {

	// logging support
	private Logger log = Logger.getLogger(EVEditObjectFormComponent.class);

	/**
	 * Generated constructor
	 * 
	 * @param context
	 */
    public EVEditObjectFormComponent(WOContext context) {
        super(context);
    }
    
    /**
     * the object to edit/inspect
     * 
     * @return the object
     */
    public EOGenericRecord object () {
    	return (EOGenericRecord) this.valueForBinding("object");
    }
    /**
     * set the object to edit/inspect
     * 
     * @param the object
     */
    public void setObject (EOGenericRecord eo) {
    	this.setValueForBinding(eo, "object");
    }
    
    /**
     * is the form editing?
     * 
     * @return true if yes, no if inspecting
     */
    public Boolean editing () {
    	return (Boolean) this.valueForBinding("editing");
    }
    
    /**
     * set the editing/inspecting mode
     * 
     * @param Boolean - true if editing, false if inspecting
     */
    public void setEditing (Boolean e) {
    	this.setValueForBinding(e, "editing");
    }
    
    public boolean inspecting () {
    	return !((Boolean) this.valueForBinding("editing")).booleanValue();
    }
    
    /**
     * the dictionary with validation information
     * 
     * @return the validation dictionary
     */
    public NSDictionary validationDictionary () {
    	return (NSDictionary) this.valueForBinding("validationDictionary");
    }
    
    /**
     * set the validation dict
     * 
     * @param dict
     */
    public void setValidationDictionary (NSDictionary dict) {
    	this.setValueForBinding(dict, "validationDictionary");
    }
    
    /**
     * cancel method - calls the parents cancel method
     * @see {@link EVPageLevelEditComponent}
     *
     * @return next page
     */
    public WOComponent cancel () {
    	return (WOComponent) this.performParentAction("cancel");
    }
    
    /**
     * save method - calls the parents saveChanges method
     * @see {@link EVPageLevelEditComponent}
     * 
     * @return next page
     */
    public WOComponent saveChanges () {
    	return (WOComponent) this.performParentAction("saveChanges");
    }
    
	/**
	 * We only force the values submitted in this form into the object values, so that the user
	 * sees what he has provided to the app and what has caused the error
	 */
	public void validationFailedWithException (
			java.lang.Throwable exception, 
			java.lang.Object value, 
			java.lang.String keyPath) {

		if (log.isDebugEnabled()) {
			log.debug ("Validation failed with Exception: " + exception.getMessage());
			log.debug ("with value: " + value);
			log.debug ("for keyPath: " + keyPath);
			log.debug ("Forcing the value into our object.");
		}
		
		// try to force the value into the field:
		try {
			takeValueForKeyPath(value, keyPath);
		} catch (IllegalArgumentException e) {
			// just ignore
		}
		
	}
	
	public WOComponent deleteObject () {
		return (WOComponent) this.performParentAction("verifyDelete");
	}

}