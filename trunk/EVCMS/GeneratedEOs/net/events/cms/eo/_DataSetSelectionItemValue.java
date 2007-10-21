// _DataSetSelectionItemValue.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetSelectionItemValue.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetSelectionItemValue extends net.events.cms.eo.DataSetItemValue {
	private static Logger log = Logger.getLogger( _DataSetSelectionItemValue.class );
	
	// KeyValueCoding support
	
    public static final String SELECTEDOPTION = "selectedOption";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DATASETITEM = "dataSetItem";
    public static final String DATASETENTRY = "dataSetEntry";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetSelectionItemValue";

	/**
	 * Standard constructor
	 */	
    public _DataSetSelectionItemValue() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("DataSetSelectionItemValue", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "DataSetSelectionItemValue" object with all required values
	 */
	public static DataSetSelectionItemValue createDataSetSelectionItemValue(EOEditingContext editingContext, net.events.cms.eo.Client client, net.events.cms.eo.DataSetEntry dataSetEntry, net.events.cms.eo.DataSetItem dataSetItem) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetSelectionItemValue");
		DataSetSelectionItemValue eoObject = (DataSetSelectionItemValue)EOUtilities.createAndInsertInstance(editingContext, _DataSetSelectionItemValue.ENTITY_NAME);
		eoObject.setClient(client);
		eoObject.setDataSetEntry(dataSetEntry);
		eoObject.setDataSetItem(dataSetItem);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetSelectionItemValue" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetSelectionItemValue'");
		return _DataSetSelectionItemValue.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetSelectionItemValue" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetSelectionItemValue' with sortOrderings " + _sortOrderings);
		return _DataSetSelectionItemValue.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetSelectionItemValue" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetSelectionItemValue.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetSelectionItemValue" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetSelectionItemValue' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetSelectionItemValue.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetSelectionItemValue fetchDataSetSelectionItemValueWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetSelectionItemValue.fetchDataSetSelectionItemValueWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetSelectionItemValue fetchDataSetSelectionItemValueWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetSelectionItemValue' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetSelectionItemValue.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetSelectionItemValue eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetSelectionItemValue)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetSelectionItemValue that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetSelectionItemValue fetchRequiredDataSetSelectionItemValueWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetSelectionItemValue.fetchRequiredDataSetSelectionItemValueWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetSelectionItemValue fetchRequiredDataSetSelectionItemValueWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetSelectionItemValue' with qualifier: " + _qualifier);
		DataSetSelectionItemValue eoObject = _DataSetSelectionItemValue.fetchDataSetSelectionItemValueWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetSelectionItemValue that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetSelectionItemValue localInstanceOfDataSetSelectionItemValue(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetSelectionItemValue': " + this.toString());
		return (DataSetSelectionItemValue)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetSelectionItemValue localInstanceOfDataSetSelectionItemValue(EOEditingContext _editingContext, DataSetSelectionItemValue _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetSelectionItemValue': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetSelectionItemValue)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

    public net.events.cms.eo.DataSetSelectionOption selectedOption() {
        return (net.events.cms.eo.DataSetSelectionOption)storedValueForKey("selectedOption");
    }

    public void setSelectedOption(net.events.cms.eo.DataSetSelectionOption aValue) {
        takeStoredValueForKey(aValue, "selectedOption");
    }
}