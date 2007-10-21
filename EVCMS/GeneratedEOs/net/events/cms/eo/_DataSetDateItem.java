// _DataSetDateItem.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetDateItem.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetDateItem extends net.events.cms.eo.DataSetItem {
	private static Logger log = Logger.getLogger( _DataSetDateItem.class );
	
	// KeyValueCoding support
	
    public static final String TEXTDESCRIPTION = "textDescription";
    public static final String SECTION = "section";
    public static final String ORDERNUMBER = "orderNumber";
    public static final String NAME = "name";
    public static final String ISREQUIRED = "isRequired";
    public static final String INTERFACEELEMENTTYPE = "interfaceElementType";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DATASETTEMPLATE = "dataSetTemplate";
    public static final String CREATIONTIME = "creationTime";
    public static final String CLIENT = "client";
    public static final String AUTOSETTOTODAY = "autosetToToday";
    public static final String ANSWERS = "answers";
    public static final String ACTIVE = "active";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetDateItem";

	/**
	 * Standard constructor
	 */	
    public _DataSetDateItem() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("DataSetDateItem", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "DataSetDateItem" object with all required values
	 */
	public static DataSetDateItem createDataSetDateItem(EOEditingContext editingContext, NSTimestamp creationTime, Boolean isRequired, String name, Number orderNumber, net.events.cms.eo.Client client, net.events.cms.eo.DataSetTemplate dataSetTemplate, net.events.cms.eo.InterfaceElementType interfaceElementType) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetDateItem");
		DataSetDateItem eoObject = (DataSetDateItem)EOUtilities.createAndInsertInstance(editingContext, _DataSetDateItem.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setIsRequired(isRequired);
		eoObject.setName(name);
		eoObject.setOrderNumber(orderNumber);
		eoObject.setClient(client);
		eoObject.setDataSetTemplate(dataSetTemplate);
		eoObject.setInterfaceElementType(interfaceElementType);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetDateItem" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetDateItem'");
		return _DataSetDateItem.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetDateItem" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetDateItem' with sortOrderings " + _sortOrderings);
		return _DataSetDateItem.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetDateItem" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetDateItem.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetDateItem" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetDateItem' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetDateItem.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetDateItem fetchDataSetDateItemWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetDateItem.fetchDataSetDateItemWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetDateItem fetchDataSetDateItemWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetDateItem' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetDateItem.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetDateItem eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetDateItem)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetDateItem that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetDateItem fetchRequiredDataSetDateItemWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetDateItem.fetchRequiredDataSetDateItemWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetDateItem fetchRequiredDataSetDateItemWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetDateItem' with qualifier: " + _qualifier);
		DataSetDateItem eoObject = _DataSetDateItem.fetchDataSetDateItemWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetDateItem that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetDateItem localInstanceOfDataSetDateItem(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetDateItem': " + this.toString());
		return (DataSetDateItem)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetDateItem localInstanceOfDataSetDateItem(EOEditingContext _editingContext, DataSetDateItem _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetDateItem': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetDateItem)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "autosetToToday"
	 */
    public Boolean autosetToToday() {
        return (Boolean) storedValueForKey("autosetToToday");
    }

	/**
	 * Set the value for "autosetToToday"
	 */
    public void setAutosetToToday(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating autosetToToday from "+autosetToToday()+" to "+aValue );
        takeStoredValueForKey(aValue, "autosetToToday");
    }
}