// _DataSetItem.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetItem.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetItem extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _DataSetItem.class );
	
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
    public static final String ANSWERS = "answers";
    public static final String ACTIVE = "active";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetItem";

	/**
	 * Standard constructor
	 */	
    public _DataSetItem() {
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
	 * Create a "DataSetItem" object with all required values
	 */
	public static DataSetItem createDataSetItem(EOEditingContext editingContext, NSTimestamp creationTime, String inheritanceType, Boolean isRequired, String name, Number orderNumber, net.events.cms.eo.Client client, net.events.cms.eo.DataSetTemplate dataSetTemplate, net.events.cms.eo.InterfaceElementType interfaceElementType) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetItem");
		DataSetItem eoObject = (DataSetItem)EOUtilities.createAndInsertInstance(editingContext, _DataSetItem.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setInheritanceType(inheritanceType);
		eoObject.setIsRequired(isRequired);
		eoObject.setName(name);
		eoObject.setOrderNumber(orderNumber);
		eoObject.setClient(client);
		eoObject.setDataSetTemplate(dataSetTemplate);
		eoObject.setInterfaceElementType(interfaceElementType);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetItem" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetItem'");
		return _DataSetItem.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetItem" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetItem' with sortOrderings " + _sortOrderings);
		return _DataSetItem.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetItem" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetItem.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetItem" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetItem' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetItem.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetItem fetchDataSetItemWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetItem.fetchDataSetItemWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetItem fetchDataSetItemWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetItem' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetItem.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetItem eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetItem)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetItem that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetItem fetchRequiredDataSetItemWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetItem.fetchRequiredDataSetItemWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetItem fetchRequiredDataSetItemWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetItem' with qualifier: " + _qualifier);
		DataSetItem eoObject = _DataSetItem.fetchDataSetItemWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetItem that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetItem localInstanceOfDataSetItem(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetItem': " + this.toString());
		return (DataSetItem)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetItem localInstanceOfDataSetItem(EOEditingContext _editingContext, DataSetItem _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetItem': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetItem)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "active"
	 */
    public Boolean active() {
        return (Boolean) storedValueForKey("active");
    }

	/**
	 * Set the value for "active"
	 */
    public void setActive(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating active from "+active()+" to "+aValue );
        takeStoredValueForKey(aValue, "active");
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

	/**
	 * The value for "isRequired"
	 */
    public Boolean isRequired() {
        return (Boolean) storedValueForKey("isRequired");
    }

	/**
	 * Set the value for "isRequired"
	 */
    public void setIsRequired(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating isRequired from "+isRequired()+" to "+aValue );
        takeStoredValueForKey(aValue, "isRequired");
    }

	/**
	 * The value for "name"
	 */
    public String name() {
        return (String) storedValueForKey("name");
    }

	/**
	 * Set the value for "name"
	 */
    public void setName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating name from "+name()+" to "+aValue );
        takeStoredValueForKey(aValue, "name");
    }

	/**
	 * The value for "orderNumber"
	 */
    public Number orderNumber() {
        return (Number) storedValueForKey("orderNumber");
    }

	/**
	 * Set the value for "orderNumber"
	 */
    public void setOrderNumber(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating orderNumber from "+orderNumber()+" to "+aValue );
        takeStoredValueForKey(aValue, "orderNumber");
    }

	/**
	 * The value for "textDescription"
	 */
    public String textDescription() {
        return (String) storedValueForKey("textDescription");
    }

	/**
	 * Set the value for "textDescription"
	 */
    public void setTextDescription(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating textDescription from "+textDescription()+" to "+aValue );
        takeStoredValueForKey(aValue, "textDescription");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.DataSetTemplate dataSetTemplate() {
        return (net.events.cms.eo.DataSetTemplate)storedValueForKey("dataSetTemplate");
    }

    public void setDataSetTemplate(net.events.cms.eo.DataSetTemplate aValue) {
        takeStoredValueForKey(aValue, "dataSetTemplate");
    }

    public net.events.cms.eo.InterfaceElementType interfaceElementType() {
        return (net.events.cms.eo.InterfaceElementType)storedValueForKey("interfaceElementType");
    }

    public void setInterfaceElementType(net.events.cms.eo.InterfaceElementType aValue) {
        takeStoredValueForKey(aValue, "interfaceElementType");
    }

    public net.events.cms.eo.DataSetSection section() {
        return (net.events.cms.eo.DataSetSection)storedValueForKey("section");
    }

    public void setSection(net.events.cms.eo.DataSetSection aValue) {
        takeStoredValueForKey(aValue, "section");
    }

	/**
	 * Returns the objects for the relationship "answers"
	 */
    public NSArray answers() {
        return (NSArray)storedValueForKey("answers");
    }

    public void setAnswers(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating answers from "+answers()+" to "+aValue );
        takeStoredValueForKey(aValue, "answers");
    }

    public void addToAnswers(net.events.cms.eo.DataSetItemValue object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to answers" );
	    includeObjectIntoPropertyWithKey(object, "answers");
    }
    

    public void removeFromAnswers(net.events.cms.eo.DataSetItemValue object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from answers" );
	    excludeObjectFromPropertyWithKey(object, "answers");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.DataSetItemValue" and add it
     * to the relationship "answers"
     */
    public net.events.cms.eo.DataSetItemValue createObjectAndAddToAnswers() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: answers");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("DataSetItemValue");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "answers");
	    return (net.events.cms.eo.DataSetItemValue) eoObject;
    }
    
    /**
     * Removes object from the relationship "answers" and delete object
     */
    public void removeFromAnswersAndDelete(net.events.cms.eo.DataSetItemValue object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: answers");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "answers");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "answers", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllAnswers() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: answers");
	    Enumeration objects = answers().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromAnswersAndDelete((net.events.cms.eo.DataSetItemValue)objects.nextElement());
    }
}