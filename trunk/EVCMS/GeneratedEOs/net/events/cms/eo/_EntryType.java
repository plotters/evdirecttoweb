// _EntryType.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to EntryType.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _EntryType extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _EntryType.class );
	
	// KeyValueCoding support
	
    public static final String TYPEDESCRIPTION = "typeDescription";
    public static final String NAME = "name";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "EntryType";

	/**
	 * Standard constructor
	 */	
    public _EntryType() {
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
	 * Create a "EntryType" object with all required values
	 */
	public static EntryType createEntryType(EOEditingContext editingContext, String name) {
		if (log.isDebugEnabled()) log.debug ("Creating object: EntryType");
		EntryType eoObject = (EntryType)EOUtilities.createAndInsertInstance(editingContext, _EntryType.ENTITY_NAME);
		eoObject.setName(name);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "EntryType" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EntryType'");
		return _EntryType.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "EntryType" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EntryType' with sortOrderings " + _sortOrderings);
		return _EntryType.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "EntryType" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EntryType.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "EntryType" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EntryType' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_EntryType.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static EntryType fetchEntryTypeWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EntryType.fetchEntryTypeWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static EntryType fetchEntryTypeWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'EntryType' with qualifier: " + _qualifier);
		NSArray eoObjects = _EntryType.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		EntryType eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (EntryType)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one EntryType that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EntryType fetchRequiredEntryTypeWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EntryType.fetchRequiredEntryTypeWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EntryType fetchRequiredEntryTypeWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'EntryType' with qualifier: " + _qualifier);
		EntryType eoObject = _EntryType.fetchEntryTypeWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no EntryType that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public EntryType localInstanceOfEntryType(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EntryType': " + this.toString());
		return (EntryType)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static EntryType localInstanceOfEntryType(EOEditingContext _editingContext, EntryType _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EntryType': " + _eo.toString());
		}
		return (_eo == null) ? null : (EntryType)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "typeDescription"
	 */
    public String typeDescription() {
        return (String) storedValueForKey("typeDescription");
    }

	/**
	 * Set the value for "typeDescription"
	 */
    public void setTypeDescription(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating typeDescription from "+typeDescription()+" to "+aValue );
        takeStoredValueForKey(aValue, "typeDescription");
    }
}