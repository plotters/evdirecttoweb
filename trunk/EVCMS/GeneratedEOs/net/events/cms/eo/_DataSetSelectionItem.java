// _DataSetSelectionItem.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetSelectionItem.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetSelectionItem extends net.events.cms.eo.DataSetItem {
	private static Logger log = Logger.getLogger( _DataSetSelectionItem.class );
	
	// KeyValueCoding support
	
    public static final String TEXTDESCRIPTION = "textDescription";
    public static final String SECTION = "section";
    public static final String ORDERNUMBER = "orderNumber";
    public static final String OPTIONS = "options";
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
    public static final String ENTITY_NAME = "DataSetSelectionItem";

	/**
	 * Standard constructor
	 */	
    public _DataSetSelectionItem() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("DataSetSelectionItem", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "DataSetSelectionItem" object with all required values
	 */
	public static DataSetSelectionItem createDataSetSelectionItem(EOEditingContext editingContext, NSTimestamp creationTime, Boolean isRequired, String name, Number orderNumber, net.events.cms.eo.Client client, net.events.cms.eo.DataSetTemplate dataSetTemplate, net.events.cms.eo.InterfaceElementType interfaceElementType) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetSelectionItem");
		DataSetSelectionItem eoObject = (DataSetSelectionItem)EOUtilities.createAndInsertInstance(editingContext, _DataSetSelectionItem.ENTITY_NAME);
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
 	 * Fetch all "DataSetSelectionItem" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetSelectionItem'");
		return _DataSetSelectionItem.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetSelectionItem" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetSelectionItem' with sortOrderings " + _sortOrderings);
		return _DataSetSelectionItem.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetSelectionItem" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetSelectionItem.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetSelectionItem" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetSelectionItem' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetSelectionItem.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetSelectionItem fetchDataSetSelectionItemWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetSelectionItem.fetchDataSetSelectionItemWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetSelectionItem fetchDataSetSelectionItemWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetSelectionItem' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetSelectionItem.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetSelectionItem eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetSelectionItem)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetSelectionItem that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetSelectionItem fetchRequiredDataSetSelectionItemWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetSelectionItem.fetchRequiredDataSetSelectionItemWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetSelectionItem fetchRequiredDataSetSelectionItemWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetSelectionItem' with qualifier: " + _qualifier);
		DataSetSelectionItem eoObject = _DataSetSelectionItem.fetchDataSetSelectionItemWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetSelectionItem that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetSelectionItem localInstanceOfDataSetSelectionItem(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetSelectionItem': " + this.toString());
		return (DataSetSelectionItem)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetSelectionItem localInstanceOfDataSetSelectionItem(EOEditingContext _editingContext, DataSetSelectionItem _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetSelectionItem': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetSelectionItem)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * Returns the objects for the relationship "options"
	 */
    public NSArray options() {
        return (NSArray)storedValueForKey("options");
    }

    public void setOptions(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating options from "+options()+" to "+aValue );
        takeStoredValueForKey(aValue, "options");
    }

    public void addToOptions(net.events.cms.eo.DataSetSelectionOption object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to options" );
	    includeObjectIntoPropertyWithKey(object, "options");
    }
    

    public void removeFromOptions(net.events.cms.eo.DataSetSelectionOption object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from options" );
	    excludeObjectFromPropertyWithKey(object, "options");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.DataSetSelectionOption" and add it
     * to the relationship "options"
     */
    public net.events.cms.eo.DataSetSelectionOption createObjectAndAddToOptions() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: options");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("DataSetSelectionOption");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "options");
	    return (net.events.cms.eo.DataSetSelectionOption) eoObject;
    }
    
    /**
     * Removes object from the relationship "options" and delete object
     */
    public void removeFromOptionsAndDelete(net.events.cms.eo.DataSetSelectionOption object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: options");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "options");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "options", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllOptions() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: options");
	    Enumeration objects = options().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromOptionsAndDelete((net.events.cms.eo.DataSetSelectionOption)objects.nextElement());
    }
}