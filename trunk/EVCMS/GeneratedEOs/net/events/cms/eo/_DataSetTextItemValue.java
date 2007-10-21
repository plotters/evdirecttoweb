// _DataSetTextItemValue.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetTextItemValue.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetTextItemValue extends net.events.cms.eo.DataSetItemValue {
	private static Logger log = Logger.getLogger( _DataSetTextItemValue.class );
	
	// KeyValueCoding support
	
    public static final String TEXTVALUE = "textValue";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DATASETITEM = "dataSetItem";
    public static final String DATASETENTRY = "dataSetEntry";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetTextItemValue";

	/**
	 * Standard constructor
	 */	
    public _DataSetTextItemValue() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("DataSetTextItemValue", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "DataSetTextItemValue" object with all required values
	 */
	public static DataSetTextItemValue createDataSetTextItemValue(EOEditingContext editingContext, net.events.cms.eo.Client client, net.events.cms.eo.DataSetEntry dataSetEntry, net.events.cms.eo.DataSetItem dataSetItem) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetTextItemValue");
		DataSetTextItemValue eoObject = (DataSetTextItemValue)EOUtilities.createAndInsertInstance(editingContext, _DataSetTextItemValue.ENTITY_NAME);
		eoObject.setClient(client);
		eoObject.setDataSetEntry(dataSetEntry);
		eoObject.setDataSetItem(dataSetItem);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetTextItemValue" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetTextItemValue'");
		return _DataSetTextItemValue.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetTextItemValue" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetTextItemValue' with sortOrderings " + _sortOrderings);
		return _DataSetTextItemValue.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetTextItemValue" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetTextItemValue.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetTextItemValue" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetTextItemValue' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetTextItemValue.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetTextItemValue fetchDataSetTextItemValueWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetTextItemValue.fetchDataSetTextItemValueWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetTextItemValue fetchDataSetTextItemValueWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetTextItemValue' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetTextItemValue.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetTextItemValue eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetTextItemValue)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetTextItemValue that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetTextItemValue fetchRequiredDataSetTextItemValueWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetTextItemValue.fetchRequiredDataSetTextItemValueWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetTextItemValue fetchRequiredDataSetTextItemValueWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetTextItemValue' with qualifier: " + _qualifier);
		DataSetTextItemValue eoObject = _DataSetTextItemValue.fetchDataSetTextItemValueWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetTextItemValue that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetTextItemValue localInstanceOfDataSetTextItemValue(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetTextItemValue': " + this.toString());
		return (DataSetTextItemValue)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetTextItemValue localInstanceOfDataSetTextItemValue(EOEditingContext _editingContext, DataSetTextItemValue _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetTextItemValue': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetTextItemValue)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "textValue"
	 */
    public String textValue() {
        return (String) storedValueForKey("textValue");
    }

	/**
	 * Set the value for "textValue"
	 */
    public void setTextValue(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating textValue from "+textValue()+" to "+aValue );
        takeStoredValueForKey(aValue, "textValue");
    }
}