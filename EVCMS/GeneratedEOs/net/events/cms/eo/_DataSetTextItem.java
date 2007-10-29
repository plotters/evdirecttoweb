// _DataSetTextItem.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetTextItem.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetTextItem extends net.events.cms.eo.DataSetItem {
	private static Logger log = Logger.getLogger( _DataSetTextItem.class );
	
	// KeyValueCoding support
	
    public static final String USETEXTAREA = "useTextArea";
    public static final String TEXTDESCRIPTION = "textDescription";
    public static final String SORTPREDEFINEDVALUES = "sortPredefinedValues";
    public static final String SECTION = "section";
    public static final String PREDEFINEDVALUES = "predefinedValues";
    public static final String ORDERNUMBER = "orderNumber";
    public static final String NAME = "name";
    public static final String ISREQUIRED = "isRequired";
    public static final String INTERFACEELEMENTTYPE = "interfaceElementType";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DATASETTEMPLATE = "dataSetTemplate";
    public static final String CREATIONTIME = "creationTime";
    public static final String CLIENT = "client";
    public static final String ANSWERS = "answers";
    public static final String ACTIVE = "active";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetTextItem";

	/**
	 * Standard constructor
	 */	
    public _DataSetTextItem() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("DataSetTextItem", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "DataSetTextItem" object with all required values
	 */
	public static DataSetTextItem createDataSetTextItem(EOEditingContext editingContext, NSTimestamp creationTime, Boolean isRequired, String name, Number orderNumber, net.events.cms.eo.Client client, net.events.cms.eo.DataSetTemplate dataSetTemplate, net.events.cms.eo.InterfaceElementType interfaceElementType) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetTextItem");
		DataSetTextItem eoObject = (DataSetTextItem)EOUtilities.createAndInsertInstance(editingContext, _DataSetTextItem.ENTITY_NAME);
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
 	 * Fetch all "DataSetTextItem" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetTextItem'");
		return _DataSetTextItem.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetTextItem" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetTextItem' with sortOrderings " + _sortOrderings);
		return _DataSetTextItem.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetTextItem" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetTextItem.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetTextItem" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetTextItem' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetTextItem.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetTextItem fetchDataSetTextItemWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetTextItem.fetchDataSetTextItemWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetTextItem fetchDataSetTextItemWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetTextItem' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetTextItem.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetTextItem eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetTextItem)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetTextItem that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetTextItem fetchRequiredDataSetTextItemWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetTextItem.fetchRequiredDataSetTextItemWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetTextItem fetchRequiredDataSetTextItemWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetTextItem' with qualifier: " + _qualifier);
		DataSetTextItem eoObject = _DataSetTextItem.fetchDataSetTextItemWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetTextItem that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetTextItem localInstanceOfDataSetTextItem(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetTextItem': " + this.toString());
		return (DataSetTextItem)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetTextItem localInstanceOfDataSetTextItem(EOEditingContext _editingContext, DataSetTextItem _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetTextItem': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetTextItem)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "predefinedValues"
	 */
    public String predefinedValues() {
        return (String) storedValueForKey("predefinedValues");
    }

	/**
	 * Set the value for "predefinedValues"
	 */
    public void setPredefinedValues(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating predefinedValues from "+predefinedValues()+" to "+aValue );
        takeStoredValueForKey(aValue, "predefinedValues");
    }

	/**
	 * The value for "sortPredefinedValues"
	 */
    public Boolean sortPredefinedValues() {
        return (Boolean) storedValueForKey("sortPredefinedValues");
    }

	/**
	 * Set the value for "sortPredefinedValues"
	 */
    public void setSortPredefinedValues(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating sortPredefinedValues from "+sortPredefinedValues()+" to "+aValue );
        takeStoredValueForKey(aValue, "sortPredefinedValues");
    }

	/**
	 * The value for "useTextArea"
	 */
    public Boolean useTextArea() {
        return (Boolean) storedValueForKey("useTextArea");
    }

	/**
	 * Set the value for "useTextArea"
	 */
    public void setUseTextArea(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating useTextArea from "+useTextArea()+" to "+aValue );
        takeStoredValueForKey(aValue, "useTextArea");
    }
}