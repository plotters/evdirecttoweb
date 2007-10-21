// _DataSetDecimalItem.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetDecimalItem.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetDecimalItem extends net.events.cms.eo.DataSetItem {
	private static Logger log = Logger.getLogger( _DataSetDecimalItem.class );
	
	// KeyValueCoding support
	
    public static final String UNIT = "unit";
    public static final String TEXTDESCRIPTION = "textDescription";
    public static final String SECTION = "section";
    public static final String ORDERNUMBER = "orderNumber";
    public static final String NAME = "name";
    public static final String ISREQUIRED = "isRequired";
    public static final String INTERFACEELEMENTTYPE = "interfaceElementType";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String FORMAT = "format";
    public static final String DATASETTEMPLATE = "dataSetTemplate";
    public static final String CREATIONTIME = "creationTime";
    public static final String CLIENT = "client";
    public static final String ANSWERS = "answers";
    public static final String ACTIVE = "active";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetDecimalItem";

	/**
	 * Standard constructor
	 */	
    public _DataSetDecimalItem() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("DataSetDecimalItem", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "DataSetDecimalItem" object with all required values
	 */
	public static DataSetDecimalItem createDataSetDecimalItem(EOEditingContext editingContext, String format, NSTimestamp creationTime, Boolean isRequired, String name, Number orderNumber, net.events.cms.eo.Client client, net.events.cms.eo.DataSetTemplate dataSetTemplate, net.events.cms.eo.InterfaceElementType interfaceElementType) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetDecimalItem");
		DataSetDecimalItem eoObject = (DataSetDecimalItem)EOUtilities.createAndInsertInstance(editingContext, _DataSetDecimalItem.ENTITY_NAME);
		eoObject.setFormat(format);
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
 	 * Fetch all "DataSetDecimalItem" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetDecimalItem'");
		return _DataSetDecimalItem.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetDecimalItem" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetDecimalItem' with sortOrderings " + _sortOrderings);
		return _DataSetDecimalItem.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetDecimalItem" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetDecimalItem.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetDecimalItem" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetDecimalItem' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetDecimalItem.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetDecimalItem fetchDataSetDecimalItemWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetDecimalItem.fetchDataSetDecimalItemWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetDecimalItem fetchDataSetDecimalItemWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetDecimalItem' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetDecimalItem.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetDecimalItem eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetDecimalItem)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetDecimalItem that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetDecimalItem fetchRequiredDataSetDecimalItemWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetDecimalItem.fetchRequiredDataSetDecimalItemWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetDecimalItem fetchRequiredDataSetDecimalItemWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetDecimalItem' with qualifier: " + _qualifier);
		DataSetDecimalItem eoObject = _DataSetDecimalItem.fetchDataSetDecimalItemWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetDecimalItem that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetDecimalItem localInstanceOfDataSetDecimalItem(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetDecimalItem': " + this.toString());
		return (DataSetDecimalItem)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetDecimalItem localInstanceOfDataSetDecimalItem(EOEditingContext _editingContext, DataSetDecimalItem _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetDecimalItem': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetDecimalItem)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "format"
	 */
    public String format() {
        return (String) storedValueForKey("format");
    }

	/**
	 * Set the value for "format"
	 */
    public void setFormat(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating format from "+format()+" to "+aValue );
        takeStoredValueForKey(aValue, "format");
    }

    public net.events.cms.eo.NumberUnit unit() {
        return (net.events.cms.eo.NumberUnit)storedValueForKey("unit");
    }

    public void setUnit(net.events.cms.eo.NumberUnit aValue) {
        takeStoredValueForKey(aValue, "unit");
    }
}