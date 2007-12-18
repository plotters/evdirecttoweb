// _EntryStatus.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to EntryStatus.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _EntryStatus extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _EntryStatus.class );
	
	// KeyValueCoding support
	
    public static final String STATUSDESCRIPTION = "statusDescription";
    public static final String ORDERNUMBER = "orderNumber";
    public static final String NAME = "name";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "EntryStatus";

	/**
	 * Standard constructor
	 */	
    public _EntryStatus() {
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
	 * Create a "EntryStatus" object with all required values
	 */
	public static EntryStatus createEntryStatus(EOEditingContext editingContext, String name) {
		if (log.isDebugEnabled()) log.debug ("Creating object: EntryStatus");
		EntryStatus eoObject = (EntryStatus)EOUtilities.createAndInsertInstance(editingContext, _EntryStatus.ENTITY_NAME);
		eoObject.setName(name);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "EntryStatus" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EntryStatus'");
		return _EntryStatus.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "EntryStatus" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EntryStatus' with sortOrderings " + _sortOrderings);
		return _EntryStatus.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "EntryStatus" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EntryStatus.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "EntryStatus" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EntryStatus' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_EntryStatus.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static EntryStatus fetchEntryStatusWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EntryStatus.fetchEntryStatusWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static EntryStatus fetchEntryStatusWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'EntryStatus' with qualifier: " + _qualifier);
		NSArray eoObjects = _EntryStatus.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		EntryStatus eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (EntryStatus)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one EntryStatus that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EntryStatus fetchRequiredEntryStatusWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EntryStatus.fetchRequiredEntryStatusWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EntryStatus fetchRequiredEntryStatusWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'EntryStatus' with qualifier: " + _qualifier);
		EntryStatus eoObject = _EntryStatus.fetchEntryStatusWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no EntryStatus that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public EntryStatus localInstanceOfEntryStatus(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EntryStatus': " + this.toString());
		return (EntryStatus)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static EntryStatus localInstanceOfEntryStatus(EOEditingContext _editingContext, EntryStatus _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EntryStatus': " + _eo.toString());
		}
		return (_eo == null) ? null : (EntryStatus)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "orderNumber"
	 */
    public Number orderNumber() {
        return (Number) storedValueForKey("orderNumber");
    }

	/**
	 * Set the value for "orderNumber"
	 */
    public void setOrderNumber(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating orderNumber from "+orderNumber()+" to "+aValue );
        takeStoredValueForKey(aValue, "orderNumber");
    }

	/**
	 * The value for "statusDescription"
	 */
    public String statusDescription() {
        return (String) storedValueForKey("statusDescription");
    }

	/**
	 * Set the value for "statusDescription"
	 */
    public void setStatusDescription(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating statusDescription from "+statusDescription()+" to "+aValue );
        takeStoredValueForKey(aValue, "statusDescription");
    }
}