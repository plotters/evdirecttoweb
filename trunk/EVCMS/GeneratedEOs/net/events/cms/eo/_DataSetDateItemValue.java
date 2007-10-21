// _DataSetDateItemValue.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetDateItemValue.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetDateItemValue extends net.events.cms.eo.DataSetItemValue {
	private static Logger log = Logger.getLogger( _DataSetDateItemValue.class );
	
	// KeyValueCoding support
	
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DATEVALUE = "dateValue";
    public static final String DATASETITEM = "dataSetItem";
    public static final String DATASETENTRY = "dataSetEntry";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetDateItemValue";

	/**
	 * Standard constructor
	 */	
    public _DataSetDateItemValue() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("DataSetDateItemValue", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "DataSetDateItemValue" object with all required values
	 */
	public static DataSetDateItemValue createDataSetDateItemValue(EOEditingContext editingContext, net.events.cms.eo.Client client, net.events.cms.eo.DataSetEntry dataSetEntry, net.events.cms.eo.DataSetItem dataSetItem) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetDateItemValue");
		DataSetDateItemValue eoObject = (DataSetDateItemValue)EOUtilities.createAndInsertInstance(editingContext, _DataSetDateItemValue.ENTITY_NAME);
		eoObject.setClient(client);
		eoObject.setDataSetEntry(dataSetEntry);
		eoObject.setDataSetItem(dataSetItem);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetDateItemValue" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetDateItemValue'");
		return _DataSetDateItemValue.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetDateItemValue" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetDateItemValue' with sortOrderings " + _sortOrderings);
		return _DataSetDateItemValue.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetDateItemValue" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetDateItemValue.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetDateItemValue" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetDateItemValue' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetDateItemValue.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetDateItemValue fetchDataSetDateItemValueWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetDateItemValue.fetchDataSetDateItemValueWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetDateItemValue fetchDataSetDateItemValueWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetDateItemValue' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetDateItemValue.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetDateItemValue eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetDateItemValue)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetDateItemValue that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetDateItemValue fetchRequiredDataSetDateItemValueWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetDateItemValue.fetchRequiredDataSetDateItemValueWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetDateItemValue fetchRequiredDataSetDateItemValueWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetDateItemValue' with qualifier: " + _qualifier);
		DataSetDateItemValue eoObject = _DataSetDateItemValue.fetchDataSetDateItemValueWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetDateItemValue that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetDateItemValue localInstanceOfDataSetDateItemValue(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetDateItemValue': " + this.toString());
		return (DataSetDateItemValue)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetDateItemValue localInstanceOfDataSetDateItemValue(EOEditingContext _editingContext, DataSetDateItemValue _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetDateItemValue': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetDateItemValue)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "dateValue"
	 */
    public NSTimestamp dateValue() {
        return (NSTimestamp) storedValueForKey("dateValue");
    }

	/**
	 * Set the value for "dateValue"
	 */
    public void setDateValue(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating dateValue from "+dateValue()+" to "+aValue );
        takeStoredValueForKey(aValue, "dateValue");
    }
}