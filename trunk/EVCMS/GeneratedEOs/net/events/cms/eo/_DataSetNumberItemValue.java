// _DataSetNumberItemValue.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetNumberItemValue.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetNumberItemValue extends net.events.cms.eo.DataSetItemValue {
	private static Logger log = Logger.getLogger( _DataSetNumberItemValue.class );
	
	// KeyValueCoding support
	
    public static final String NUMBERVALUE = "numberValue";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DATASETITEM = "dataSetItem";
    public static final String DATASETENTRY = "dataSetEntry";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetNumberItemValue";

	/**
	 * Standard constructor
	 */	
    public _DataSetNumberItemValue() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("DataSetNumberItemValue", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "DataSetNumberItemValue" object with all required values
	 */
	public static DataSetNumberItemValue createDataSetNumberItemValue(EOEditingContext editingContext, net.events.cms.eo.Client client, net.events.cms.eo.DataSetEntry dataSetEntry, net.events.cms.eo.DataSetItem dataSetItem) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetNumberItemValue");
		DataSetNumberItemValue eoObject = (DataSetNumberItemValue)EOUtilities.createAndInsertInstance(editingContext, _DataSetNumberItemValue.ENTITY_NAME);
		eoObject.setClient(client);
		eoObject.setDataSetEntry(dataSetEntry);
		eoObject.setDataSetItem(dataSetItem);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetNumberItemValue" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetNumberItemValue'");
		return _DataSetNumberItemValue.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetNumberItemValue" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetNumberItemValue' with sortOrderings " + _sortOrderings);
		return _DataSetNumberItemValue.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetNumberItemValue" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetNumberItemValue.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetNumberItemValue" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetNumberItemValue' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetNumberItemValue.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetNumberItemValue fetchDataSetNumberItemValueWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetNumberItemValue.fetchDataSetNumberItemValueWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetNumberItemValue fetchDataSetNumberItemValueWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetNumberItemValue' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetNumberItemValue.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetNumberItemValue eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetNumberItemValue)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetNumberItemValue that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetNumberItemValue fetchRequiredDataSetNumberItemValueWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetNumberItemValue.fetchRequiredDataSetNumberItemValueWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetNumberItemValue fetchRequiredDataSetNumberItemValueWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetNumberItemValue' with qualifier: " + _qualifier);
		DataSetNumberItemValue eoObject = _DataSetNumberItemValue.fetchDataSetNumberItemValueWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetNumberItemValue that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetNumberItemValue localInstanceOfDataSetNumberItemValue(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetNumberItemValue': " + this.toString());
		return (DataSetNumberItemValue)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetNumberItemValue localInstanceOfDataSetNumberItemValue(EOEditingContext _editingContext, DataSetNumberItemValue _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetNumberItemValue': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetNumberItemValue)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "numberValue"
	 */
    public Number numberValue() {
        return (Number) storedValueForKey("numberValue");
    }

	/**
	 * Set the value for "numberValue"
	 */
    public void setNumberValue(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating numberValue from "+numberValue()+" to "+aValue );
        takeStoredValueForKey(aValue, "numberValue");
    }
}