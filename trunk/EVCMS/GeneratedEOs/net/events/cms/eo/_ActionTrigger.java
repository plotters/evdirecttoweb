// _ActionTrigger.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to ActionTrigger.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _ActionTrigger extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _ActionTrigger.class );
	
	// KeyValueCoding support
	
    public static final String TRIGGERDESCRIPTION = "triggerDescription";
    public static final String NAME = "name";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    public static final String ACTIVE = "active";
    public static final String ACTIONTRIGGERLOGS = "actionTriggerLogs";
    public static final String ACTION = "action";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "ActionTrigger";

	/**
	 * Standard constructor
	 */	
    public _ActionTrigger() {
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
	 * Create a "ActionTrigger" object with all required values
	 */
	public static ActionTrigger createActionTrigger(EOEditingContext editingContext, Boolean active, String inheritanceType, String name, net.events.cms.eo.Action action, net.events.cms.eo.Client client, net.events.cms.eo.EventsUser createdBy) {
		if (log.isDebugEnabled()) log.debug ("Creating object: ActionTrigger");
		ActionTrigger eoObject = (ActionTrigger)EOUtilities.createAndInsertInstance(editingContext, _ActionTrigger.ENTITY_NAME);
		eoObject.setActive(active);
		eoObject.setInheritanceType(inheritanceType);
		eoObject.setName(name);
		eoObject.setAction(action);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "ActionTrigger" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'ActionTrigger'");
		return _ActionTrigger.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "ActionTrigger" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'ActionTrigger' with sortOrderings " + _sortOrderings);
		return _ActionTrigger.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "ActionTrigger" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _ActionTrigger.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "ActionTrigger" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'ActionTrigger' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_ActionTrigger.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static ActionTrigger fetchActionTriggerWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _ActionTrigger.fetchActionTriggerWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static ActionTrigger fetchActionTriggerWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'ActionTrigger' with qualifier: " + _qualifier);
		NSArray eoObjects = _ActionTrigger.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		ActionTrigger eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (ActionTrigger)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one ActionTrigger that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static ActionTrigger fetchRequiredActionTriggerWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _ActionTrigger.fetchRequiredActionTriggerWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static ActionTrigger fetchRequiredActionTriggerWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'ActionTrigger' with qualifier: " + _qualifier);
		ActionTrigger eoObject = _ActionTrigger.fetchActionTriggerWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no ActionTrigger that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public ActionTrigger localInstanceOfActionTrigger(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'ActionTrigger': " + this.toString());
		return (ActionTrigger)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static ActionTrigger localInstanceOfActionTrigger(EOEditingContext _editingContext, ActionTrigger _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'ActionTrigger': " + _eo.toString());
		}
		return (_eo == null) ? null : (ActionTrigger)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "active"
	 */
    public Boolean active() {
        return (Boolean) storedValueForKey("active");
    }

	/**
	 * Set the value for "active"
	 */
    public void setActive(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating active from "+active()+" to "+aValue );
        takeStoredValueForKey(aValue, "active");
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
	 * The value for "triggerDescription"
	 */
    public String triggerDescription() {
        return (String) storedValueForKey("triggerDescription");
    }

	/**
	 * Set the value for "triggerDescription"
	 */
    public void setTriggerDescription(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating triggerDescription from "+triggerDescription()+" to "+aValue );
        takeStoredValueForKey(aValue, "triggerDescription");
    }

    public net.events.cms.eo.Action action() {
        return (net.events.cms.eo.Action)storedValueForKey("action");
    }

    public void setAction(net.events.cms.eo.Action aValue) {
        takeStoredValueForKey(aValue, "action");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.EventsUser createdBy() {
        return (net.events.cms.eo.EventsUser)storedValueForKey("createdBy");
    }

    public void setCreatedBy(net.events.cms.eo.EventsUser aValue) {
        takeStoredValueForKey(aValue, "createdBy");
    }

	/**
	 * Returns the objects for the relationship "actionTriggerLogs"
	 */
    public NSArray actionTriggerLogs() {
        return (NSArray)storedValueForKey("actionTriggerLogs");
    }

    public void setActionTriggerLogs(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating actionTriggerLogs from "+actionTriggerLogs()+" to "+aValue );
        takeStoredValueForKey(aValue, "actionTriggerLogs");
    }

    public void addToActionTriggerLogs(net.events.cms.eo.ActionTriggerLog object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to actionTriggerLogs" );
	    includeObjectIntoPropertyWithKey(object, "actionTriggerLogs");
    }
    

    public void removeFromActionTriggerLogs(net.events.cms.eo.ActionTriggerLog object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from actionTriggerLogs" );
	    excludeObjectFromPropertyWithKey(object, "actionTriggerLogs");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.ActionTriggerLog" and add it
     * to the relationship "actionTriggerLogs"
     */
    public net.events.cms.eo.ActionTriggerLog createObjectAndAddToActionTriggerLogs() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: actionTriggerLogs");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ActionTriggerLog");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "actionTriggerLogs");
	    return (net.events.cms.eo.ActionTriggerLog) eoObject;
    }
    
    /**
     * Removes object from the relationship "actionTriggerLogs" and delete object
     */
    public void removeFromActionTriggerLogsAndDelete(net.events.cms.eo.ActionTriggerLog object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: actionTriggerLogs");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "actionTriggerLogs");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "actionTriggerLogs", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllActionTriggerLogs() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: actionTriggerLogs");
	    Enumeration objects = actionTriggerLogs().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromActionTriggerLogsAndDelete((net.events.cms.eo.ActionTriggerLog)objects.nextElement());
    }
}