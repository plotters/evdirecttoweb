// _DataSetSection.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetSection.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetSection extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _DataSetSection.class );
	
	// KeyValueCoding support
	
    public static final String SECTIONTOPTEXT = "sectionTopText";
    public static final String SECTIONNAME = "sectionName";
    public static final String SECTIONBOTTOMTEXT = "sectionBottomText";
    public static final String PAGE = "page";
    public static final String ORDERNUMBER = "orderNumber";
    public static final String ENUMERATEQUESTIONS = "enumerateQuestions";
    public static final String DATASETTEMPLATE = "dataSetTemplate";
    public static final String DATASETITEMS = "dataSetItems";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetSection";

	/**
	 * Standard constructor
	 */	
    public _DataSetSection() {
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
	 * Create a "DataSetSection" object with all required values
	 */
	public static DataSetSection createDataSetSection(EOEditingContext editingContext, NSTimestamp creationTime, String sectionName, net.events.cms.eo.Client client, net.events.cms.eo.EventsUser createdBy, net.events.cms.eo.DataSetTemplate dataSetTemplate) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetSection");
		DataSetSection eoObject = (DataSetSection)EOUtilities.createAndInsertInstance(editingContext, _DataSetSection.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setSectionName(sectionName);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		eoObject.setDataSetTemplate(dataSetTemplate);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetSection" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetSection'");
		return _DataSetSection.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetSection" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetSection' with sortOrderings " + _sortOrderings);
		return _DataSetSection.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetSection" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetSection.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetSection" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetSection' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetSection.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetSection fetchDataSetSectionWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetSection.fetchDataSetSectionWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetSection fetchDataSetSectionWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetSection' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetSection.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetSection eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetSection)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetSection that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetSection fetchRequiredDataSetSectionWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetSection.fetchRequiredDataSetSectionWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetSection fetchRequiredDataSetSectionWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetSection' with qualifier: " + _qualifier);
		DataSetSection eoObject = _DataSetSection.fetchDataSetSectionWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetSection that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetSection localInstanceOfDataSetSection(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetSection': " + this.toString());
		return (DataSetSection)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetSection localInstanceOfDataSetSection(EOEditingContext _editingContext, DataSetSection _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetSection': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetSection)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "enumerateQuestions"
	 */
    public Boolean enumerateQuestions() {
        return (Boolean) storedValueForKey("enumerateQuestions");
    }

	/**
	 * Set the value for "enumerateQuestions"
	 */
    public void setEnumerateQuestions(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating enumerateQuestions from "+enumerateQuestions()+" to "+aValue );
        takeStoredValueForKey(aValue, "enumerateQuestions");
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
	 * The value for "sectionBottomText"
	 */
    public String sectionBottomText() {
        return (String) storedValueForKey("sectionBottomText");
    }

	/**
	 * Set the value for "sectionBottomText"
	 */
    public void setSectionBottomText(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating sectionBottomText from "+sectionBottomText()+" to "+aValue );
        takeStoredValueForKey(aValue, "sectionBottomText");
    }

	/**
	 * The value for "sectionName"
	 */
    public String sectionName() {
        return (String) storedValueForKey("sectionName");
    }

	/**
	 * Set the value for "sectionName"
	 */
    public void setSectionName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating sectionName from "+sectionName()+" to "+aValue );
        takeStoredValueForKey(aValue, "sectionName");
    }

	/**
	 * The value for "sectionTopText"
	 */
    public String sectionTopText() {
        return (String) storedValueForKey("sectionTopText");
    }

	/**
	 * Set the value for "sectionTopText"
	 */
    public void setSectionTopText(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating sectionTopText from "+sectionTopText()+" to "+aValue );
        takeStoredValueForKey(aValue, "sectionTopText");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.EventsUser createdBy() {
        return (net.events.cms.eo.EventsUser)storedValueForKey("createdBy");
    }

    public void setCreatedBy(net.events.cms.eo.EventsUser aValue) {
        takeStoredValueForKey(aValue, "createdBy");
    }

    public net.events.cms.eo.DataSetTemplate dataSetTemplate() {
        return (net.events.cms.eo.DataSetTemplate)storedValueForKey("dataSetTemplate");
    }

    public void setDataSetTemplate(net.events.cms.eo.DataSetTemplate aValue) {
        takeStoredValueForKey(aValue, "dataSetTemplate");
    }

    public net.events.cms.eo.DataSetPage page() {
        return (net.events.cms.eo.DataSetPage)storedValueForKey("page");
    }

    public void setPage(net.events.cms.eo.DataSetPage aValue) {
        takeStoredValueForKey(aValue, "page");
    }

	/**
	 * Returns the objects for the relationship "dataSetItems"
	 */
    public NSArray dataSetItems() {
        return (NSArray)storedValueForKey("dataSetItems");
    }

    public void setDataSetItems(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating dataSetItems from "+dataSetItems()+" to "+aValue );
        takeStoredValueForKey(aValue, "dataSetItems");
    }

    public void addToDataSetItems(net.events.cms.eo.DataSetItem object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to dataSetItems" );
	    includeObjectIntoPropertyWithKey(object, "dataSetItems");
    }
    

    public void removeFromDataSetItems(net.events.cms.eo.DataSetItem object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from dataSetItems" );
	    excludeObjectFromPropertyWithKey(object, "dataSetItems");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.DataSetItem" and add it
     * to the relationship "dataSetItems"
     */
    public net.events.cms.eo.DataSetItem createObjectAndAddToDataSetItems() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: dataSetItems");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("DataSetItem");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "dataSetItems");
	    return (net.events.cms.eo.DataSetItem) eoObject;
    }
    
    /**
     * Removes object from the relationship "dataSetItems" and delete object
     */
    public void removeFromDataSetItemsAndDelete(net.events.cms.eo.DataSetItem object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: dataSetItems");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "dataSetItems");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "dataSetItems", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllDataSetItems() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: dataSetItems");
	    Enumeration objects = dataSetItems().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromDataSetItemsAndDelete((net.events.cms.eo.DataSetItem)objects.nextElement());
    }
}