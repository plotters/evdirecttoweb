// _Action.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Action.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Action extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Action.class );
	
	// KeyValueCoding support
	
    public static final String NAME = "name";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String ACTIONTRIGGERS = "actionTriggers";
    public static final String ACTIONDESCRIPTION = "actionDescription";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Action";

	/**
	 * Standard constructor
	 */	
    public _Action() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	
    	if (this.attributeKeys().containsObject("creationTime")) {
    		this.takeValueForKey (new NSTimestamp(), "creationTime");
    	}
    	if (this.toOneRelationshipKeys().containsObject("createdBy")) {
	   		EOEnterpriseObject currentUser = ERXEOControlUtilities.localInstanceOfObject(this.editingContext(), (EOEnterpriseObject)ERXThreadStorage.valueForKey("CURRENT_USER"));
			if (currentUser != null) {
	    		this.addObjectToBothSidesOfRelationshipWithKey(currentUser, "createdBy");
	    	}
    	}
    	if (this.toOneRelationshipKeys().containsObject("client")) {
    		EOEnterpriseObject currentClient = ERXEOControlUtilities.localInstanceOfObject(this.editingContext(), (EOEnterpriseObject)ERXThreadStorage.valueForKey("CURRENT_CLIENT"));
			if (currentClient != null) {
	    		this.addObjectToBothSidesOfRelationshipWithKey(currentClient, "client");
	    	}
    	}
    }

	/**
	 * Create a "Action" object with all required values
	 */
	public static Action createAction(EOEditingContext editingContext, String inheritanceType, String name) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Action");
		Action eoObject = (Action)EOUtilities.createAndInsertInstance(editingContext, _Action.ENTITY_NAME);
		eoObject.setInheritanceType(inheritanceType);
		eoObject.setName(name);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Action" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Action'");
		return _Action.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Action" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Action' with sortOrderings " + _sortOrderings);
		return _Action.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Action" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Action.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Action" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Action' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Action.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Action fetchActionWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Action.fetchActionWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Action fetchActionWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Action' with qualifier: " + _qualifier);
		NSArray eoObjects = _Action.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Action eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Action)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Action that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Action fetchRequiredActionWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Action.fetchRequiredActionWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Action fetchRequiredActionWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Action' with qualifier: " + _qualifier);
		Action eoObject = _Action.fetchActionWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Action that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Action localInstanceOfAction(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Action': " + this.toString());
		return (Action)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Action localInstanceOfAction(EOEditingContext _editingContext, Action _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Action': " + _eo.toString());
		}
		return (_eo == null) ? null : (Action)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "actionDescription"
	 */
    public String actionDescription() {
        return (String) storedValueForKey("actionDescription");
    }

	/**
	 * Set the value for "actionDescription"
	 */
    public void setActionDescription(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating actionDescription from "+actionDescription()+" to "+aValue );
        takeStoredValueForKey(aValue, "actionDescription");
    }

	/**
	 * The value for "inheritanceType"
	 */
    public String inheritanceType() {
        return (String) storedValueForKey("inheritanceType");
    }

	/**
	 * Set the value for "inheritanceType"
	 */
    public void setInheritanceType(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating inheritanceType from "+inheritanceType()+" to "+aValue );
        takeStoredValueForKey(aValue, "inheritanceType");
    }

	/**
	 * The value for "name"
	 */
    public String name() {
        return (String) storedValueForKey("name");
    }

	/**
	 * Set the value for "name"
	 */
    public void setName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating name from "+name()+" to "+aValue );
        takeStoredValueForKey(aValue, "name");
    }

	/**
	 * Returns the objects for the relationship "actionTriggers"
	 */
    public NSArray actionTriggers() {
        return (NSArray)storedValueForKey("actionTriggers");
    }

    public void setActionTriggers(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating actionTriggers from "+actionTriggers()+" to "+aValue );
        takeStoredValueForKey(aValue, "actionTriggers");
    }

    public void addToActionTriggers(net.events.cms.eo.ActionTrigger object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to actionTriggers" );
	    includeObjectIntoPropertyWithKey(object, "actionTriggers");
    }
    

    public void removeFromActionTriggers(net.events.cms.eo.ActionTrigger object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from actionTriggers" );
	    excludeObjectFromPropertyWithKey(object, "actionTriggers");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.ActionTrigger" and add it
     * to the relationship "actionTriggers"
     */
    public net.events.cms.eo.ActionTrigger createObjectAndAddToActionTriggers() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: actionTriggers");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ActionTrigger");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "actionTriggers");
	    return (net.events.cms.eo.ActionTrigger) eoObject;
    }
    
    /**
     * Removes object from the relationship "actionTriggers" and delete object
     */
    public void removeFromActionTriggersAndDelete(net.events.cms.eo.ActionTrigger object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: actionTriggers");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "actionTriggers");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "actionTriggers", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllActionTriggers() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: actionTriggers");
	    Enumeration objects = actionTriggers().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromActionTriggersAndDelete((net.events.cms.eo.ActionTrigger)objects.nextElement());
    }
}