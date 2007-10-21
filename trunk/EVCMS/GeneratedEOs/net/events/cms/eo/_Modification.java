// _Modification.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Modification.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Modification extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Modification.class );
	
	// KeyValueCoding support
	
    public static final String TYPE = "type";
    public static final String PRIMARYKEYVALUE = "primaryKeyValue";
    public static final String PRIMARYKEYNAME = "primaryKeyName";
    public static final String MODIFICATIONSARRAY = "modificationsArray";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Modification";

	/**
	 * Standard constructor
	 */	
    public _Modification() {
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
	 * Create a "Modification" object with all required values
	 */
	public static Modification createModification(EOEditingContext editingContext, NSTimestamp creationTime, String primaryKeyName, String primaryKeyValue, String type, net.events.cms.eo.Person createdBy) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Modification");
		Modification eoObject = (Modification)EOUtilities.createAndInsertInstance(editingContext, _Modification.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setPrimaryKeyName(primaryKeyName);
		eoObject.setPrimaryKeyValue(primaryKeyValue);
		eoObject.setType(type);
		eoObject.setCreatedBy(createdBy);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Modification" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Modification'");
		return _Modification.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Modification" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Modification' with sortOrderings " + _sortOrderings);
		return _Modification.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Modification" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Modification.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Modification" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Modification' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Modification.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Modification fetchModificationWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Modification.fetchModificationWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Modification fetchModificationWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Modification' with qualifier: " + _qualifier);
		NSArray eoObjects = _Modification.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Modification eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Modification)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Modification that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Modification fetchRequiredModificationWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Modification.fetchRequiredModificationWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Modification fetchRequiredModificationWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Modification' with qualifier: " + _qualifier);
		Modification eoObject = _Modification.fetchModificationWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Modification that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Modification localInstanceOfModification(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Modification': " + this.toString());
		return (Modification)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Modification localInstanceOfModification(EOEditingContext _editingContext, Modification _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Modification': " + _eo.toString());
		}
		return (_eo == null) ? null : (Modification)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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

	/**
	 * The value for "modificationsArray"
	 */
    public er.extensions.ERXMutableArray modificationsArray() {
        return (er.extensions.ERXMutableArray) storedValueForKey("modificationsArray");
    }

	/**
	 * Set the value for "modificationsArray"
	 */
    public void setModificationsArray(er.extensions.ERXMutableArray aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating modificationsArray from "+modificationsArray()+" to "+aValue );
        takeStoredValueForKey(aValue, "modificationsArray");
    }

	/**
	 * The value for "primaryKeyName"
	 */
    public String primaryKeyName() {
        return (String) storedValueForKey("primaryKeyName");
    }

	/**
	 * Set the value for "primaryKeyName"
	 */
    public void setPrimaryKeyName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating primaryKeyName from "+primaryKeyName()+" to "+aValue );
        takeStoredValueForKey(aValue, "primaryKeyName");
    }

	/**
	 * The value for "primaryKeyValue"
	 */
    public String primaryKeyValue() {
        return (String) storedValueForKey("primaryKeyValue");
    }

	/**
	 * Set the value for "primaryKeyValue"
	 */
    public void setPrimaryKeyValue(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating primaryKeyValue from "+primaryKeyValue()+" to "+aValue );
        takeStoredValueForKey(aValue, "primaryKeyValue");
    }

	/**
	 * The value for "type"
	 */
    public String type() {
        return (String) storedValueForKey("type");
    }

	/**
	 * Set the value for "type"
	 */
    public void setType(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating type from "+type()+" to "+aValue );
        takeStoredValueForKey(aValue, "type");
    }

    public net.events.cms.eo.Person createdBy() {
        return (net.events.cms.eo.Person)storedValueForKey("createdBy");
    }

    public void setCreatedBy(net.events.cms.eo.Person aValue) {
        takeStoredValueForKey(aValue, "createdBy");
    }
}