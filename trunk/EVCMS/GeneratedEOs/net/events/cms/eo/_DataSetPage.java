// _DataSetPage.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetPage.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetPage extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _DataSetPage.class );
	
	// KeyValueCoding support
	
    public static final String SECTIONS = "sections";
    public static final String PAGETOPTEXT = "pageTopText";
    public static final String PAGENUMBER = "pageNumber";
    public static final String PAGEHEADER = "pageHeader";
    public static final String PAGEBOTTOMTEXT = "pageBottomText";
    public static final String DATASETTEMPLATE = "dataSetTemplate";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetPage";

	/**
	 * Standard constructor
	 */	
    public _DataSetPage() {
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
	 * Create a "DataSetPage" object with all required values
	 */
	public static DataSetPage createDataSetPage(EOEditingContext editingContext, NSTimestamp creationTime, Number pageNumber, net.events.cms.eo.Client client, net.events.cms.eo.EventsUser createdBy, net.events.cms.eo.DataSetTemplate dataSetTemplate) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetPage");
		DataSetPage eoObject = (DataSetPage)EOUtilities.createAndInsertInstance(editingContext, _DataSetPage.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setPageNumber(pageNumber);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		eoObject.setDataSetTemplate(dataSetTemplate);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetPage" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetPage'");
		return _DataSetPage.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetPage" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetPage' with sortOrderings " + _sortOrderings);
		return _DataSetPage.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetPage" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetPage.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetPage" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetPage' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetPage.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetPage fetchDataSetPageWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetPage.fetchDataSetPageWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetPage fetchDataSetPageWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetPage' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetPage.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetPage eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetPage)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetPage that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetPage fetchRequiredDataSetPageWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetPage.fetchRequiredDataSetPageWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetPage fetchRequiredDataSetPageWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetPage' with qualifier: " + _qualifier);
		DataSetPage eoObject = _DataSetPage.fetchDataSetPageWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetPage that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetPage localInstanceOfDataSetPage(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetPage': " + this.toString());
		return (DataSetPage)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetPage localInstanceOfDataSetPage(EOEditingContext _editingContext, DataSetPage _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetPage': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetPage)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "pageBottomText"
	 */
    public String pageBottomText() {
        return (String) storedValueForKey("pageBottomText");
    }

	/**
	 * Set the value for "pageBottomText"
	 */
    public void setPageBottomText(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating pageBottomText from "+pageBottomText()+" to "+aValue );
        takeStoredValueForKey(aValue, "pageBottomText");
    }

	/**
	 * The value for "pageHeader"
	 */
    public String pageHeader() {
        return (String) storedValueForKey("pageHeader");
    }

	/**
	 * Set the value for "pageHeader"
	 */
    public void setPageHeader(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating pageHeader from "+pageHeader()+" to "+aValue );
        takeStoredValueForKey(aValue, "pageHeader");
    }

	/**
	 * The value for "pageNumber"
	 */
    public Number pageNumber() {
        return (Number) storedValueForKey("pageNumber");
    }

	/**
	 * Set the value for "pageNumber"
	 */
    public void setPageNumber(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating pageNumber from "+pageNumber()+" to "+aValue );
        takeStoredValueForKey(aValue, "pageNumber");
    }

	/**
	 * The value for "pageTopText"
	 */
    public String pageTopText() {
        return (String) storedValueForKey("pageTopText");
    }

	/**
	 * Set the value for "pageTopText"
	 */
    public void setPageTopText(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating pageTopText from "+pageTopText()+" to "+aValue );
        takeStoredValueForKey(aValue, "pageTopText");
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

	/**
	 * Returns the objects for the relationship "sections"
	 */
    public NSArray sections() {
        return (NSArray)storedValueForKey("sections");
    }

    public void setSections(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating sections from "+sections()+" to "+aValue );
        takeStoredValueForKey(aValue, "sections");
    }

    public void addToSections(net.events.cms.eo.DataSetSection object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to sections" );
	    includeObjectIntoPropertyWithKey(object, "sections");
    }
    

    public void removeFromSections(net.events.cms.eo.DataSetSection object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from sections" );
	    excludeObjectFromPropertyWithKey(object, "sections");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.DataSetSection" and add it
     * to the relationship "sections"
     */
    public net.events.cms.eo.DataSetSection createObjectAndAddToSections() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: sections");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("DataSetSection");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "sections");
	    return (net.events.cms.eo.DataSetSection) eoObject;
    }
    
    /**
     * Removes object from the relationship "sections" and delete object
     */
    public void removeFromSectionsAndDelete(net.events.cms.eo.DataSetSection object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: sections");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "sections");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "sections", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllSections() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: sections");
	    Enumeration objects = sections().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromSectionsAndDelete((net.events.cms.eo.DataSetSection)objects.nextElement());
    }
}