// _ActionTriggerLog.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to ActionTriggerLog.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _ActionTriggerLog extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _ActionTriggerLog.class );
	
	// KeyValueCoding support
	
    public static final String CREATIONTIME = "creationTime";
    public static final String CLIENT = "client";
    public static final String ACTIONTRIGGER = "actionTrigger";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "ActionTriggerLog";

	/**
	 * Standard constructor
	 */	
    public _ActionTriggerLog() {
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
	 * Create a "ActionTriggerLog" object with all required values
	 */
	public static ActionTriggerLog createActionTriggerLog(EOEditingContext editingContext, NSTimestamp creationTime, net.events.cms.eo.ActionTrigger actionTrigger, net.events.cms.eo.Client client) {
		if (log.isDebugEnabled()) log.debug ("Creating object: ActionTriggerLog");
		ActionTriggerLog eoObject = (ActionTriggerLog)EOUtilities.createAndInsertInstance(editingContext, _ActionTriggerLog.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setActionTrigger(actionTrigger);
		eoObject.setClient(client);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "ActionTriggerLog" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'ActionTriggerLog'");
		return _ActionTriggerLog.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "ActionTriggerLog" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'ActionTriggerLog' with sortOrderings " + _sortOrderings);
		return _ActionTriggerLog.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "ActionTriggerLog" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _ActionTriggerLog.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "ActionTriggerLog" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'ActionTriggerLog' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_ActionTriggerLog.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static ActionTriggerLog fetchActionTriggerLogWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _ActionTriggerLog.fetchActionTriggerLogWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static ActionTriggerLog fetchActionTriggerLogWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'ActionTriggerLog' with qualifier: " + _qualifier);
		NSArray eoObjects = _ActionTriggerLog.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		ActionTriggerLog eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (ActionTriggerLog)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one ActionTriggerLog that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static ActionTriggerLog fetchRequiredActionTriggerLogWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _ActionTriggerLog.fetchRequiredActionTriggerLogWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static ActionTriggerLog fetchRequiredActionTriggerLogWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'ActionTriggerLog' with qualifier: " + _qualifier);
		ActionTriggerLog eoObject = _ActionTriggerLog.fetchActionTriggerLogWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no ActionTriggerLog that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public ActionTriggerLog localInstanceOfActionTriggerLog(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'ActionTriggerLog': " + this.toString());
		return (ActionTriggerLog)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static ActionTriggerLog localInstanceOfActionTriggerLog(EOEditingContext _editingContext, ActionTriggerLog _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'ActionTriggerLog': " + _eo.toString());
		}
		return (_eo == null) ? null : (ActionTriggerLog)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "creationTime"
	 */
    public NSTimestamp creationTime() {
        return (NSTimestamp) storedValueForKey("creationTime");
    }

	/**
	 * Set the value for "creationTime"
	 */
    public void setCreationTime(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating creationTime from "+creationTime()+" to "+aValue );
        takeStoredValueForKey(aValue, "creationTime");
    }

    public net.events.cms.eo.ActionTrigger actionTrigger() {
        return (net.events.cms.eo.ActionTrigger)storedValueForKey("actionTrigger");
    }

    public void setActionTrigger(net.events.cms.eo.ActionTrigger aValue) {
        takeStoredValueForKey(aValue, "actionTrigger");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }
}