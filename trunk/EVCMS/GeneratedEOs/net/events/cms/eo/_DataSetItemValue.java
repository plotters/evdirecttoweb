// _DataSetItemValue.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetItemValue.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetItemValue extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _DataSetItemValue.class );
	
	// KeyValueCoding support
	
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String HASTRIGGERED = "hasTriggered";
    public static final String DATASETITEM = "dataSetItem";
    public static final String DATASETENTRY = "dataSetEntry";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetItemValue";

	/**
	 * Standard constructor
	 */	
    public _DataSetItemValue() {
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
	 * Create a "DataSetItemValue" object with all required values
	 */
	public static DataSetItemValue createDataSetItemValue(EOEditingContext editingContext, String inheritanceType, net.events.cms.eo.Client client, net.events.cms.eo.DataSetEntry dataSetEntry, net.events.cms.eo.DataSetItem dataSetItem) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetItemValue");
		DataSetItemValue eoObject = (DataSetItemValue)EOUtilities.createAndInsertInstance(editingContext, _DataSetItemValue.ENTITY_NAME);
		eoObject.setInheritanceType(inheritanceType);
		eoObject.setClient(client);
		eoObject.setDataSetEntry(dataSetEntry);
		eoObject.setDataSetItem(dataSetItem);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetItemValue" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetItemValue'");
		return _DataSetItemValue.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetItemValue" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetItemValue' with sortOrderings " + _sortOrderings);
		return _DataSetItemValue.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetItemValue" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetItemValue.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetItemValue" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetItemValue' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetItemValue.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetItemValue fetchDataSetItemValueWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetItemValue.fetchDataSetItemValueWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetItemValue fetchDataSetItemValueWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetItemValue' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetItemValue.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetItemValue eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetItemValue)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetItemValue that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetItemValue fetchRequiredDataSetItemValueWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetItemValue.fetchRequiredDataSetItemValueWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetItemValue fetchRequiredDataSetItemValueWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetItemValue' with qualifier: " + _qualifier);
		DataSetItemValue eoObject = _DataSetItemValue.fetchDataSetItemValueWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetItemValue that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetItemValue localInstanceOfDataSetItemValue(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetItemValue': " + this.toString());
		return (DataSetItemValue)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetItemValue localInstanceOfDataSetItemValue(EOEditingContext _editingContext, DataSetItemValue _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetItemValue': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetItemValue)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "hasTriggered"
	 */
    public Boolean hasTriggered() {
        return (Boolean) storedValueForKey("hasTriggered");
    }

	/**
	 * Set the value for "hasTriggered"
	 */
    public void setHasTriggered(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating hasTriggered from "+hasTriggered()+" to "+aValue );
        takeStoredValueForKey(aValue, "hasTriggered");
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

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.DataSetEntry dataSetEntry() {
        return (net.events.cms.eo.DataSetEntry)storedValueForKey("dataSetEntry");
    }

    public void setDataSetEntry(net.events.cms.eo.DataSetEntry aValue) {
        takeStoredValueForKey(aValue, "dataSetEntry");
    }

    public net.events.cms.eo.DataSetItem dataSetItem() {
        return (net.events.cms.eo.DataSetItem)storedValueForKey("dataSetItem");
    }

    public void setDataSetItem(net.events.cms.eo.DataSetItem aValue) {
        takeStoredValueForKey(aValue, "dataSetItem");
    }
}