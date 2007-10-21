// _DataSetTemplate.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetTemplate.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetTemplate extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _DataSetTemplate.class );
	
	// KeyValueCoding support
	
    public static final String TOPTEXT = "topText";
    public static final String TEXTFORTHANKYOUPAGE = "textForThankYouPage";
    public static final String TEXTDESCRIPTION = "textDescription";
    public static final String SECTIONS = "sections";
    public static final String PAGES = "pages";
    public static final String NAME = "name";
    public static final String LOGINREQUIRED = "loginRequired";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DATASETPAGETEMPLATE = "dataSetPageTemplate";
    public static final String DATASETITEMS = "dataSetItems";
    public static final String DATASETENTRIES = "dataSetEntries";
    public static final String CSSCLASSFORTHANKYOUMESSAGE = "cssClassForThankYouMessage";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    public static final String BOTTOMTEXT = "bottomText";
    public static final String ACTIVE = "active";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetTemplate";

	/**
	 * Standard constructor
	 */	
    public _DataSetTemplate() {
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
	 * Create a "DataSetTemplate" object with all required values
	 */
	public static DataSetTemplate createDataSetTemplate(EOEditingContext editingContext, Boolean active, NSTimestamp creationTime, String name, net.events.cms.eo.Client client, net.events.cms.eo.Person createdBy) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetTemplate");
		DataSetTemplate eoObject = (DataSetTemplate)EOUtilities.createAndInsertInstance(editingContext, _DataSetTemplate.ENTITY_NAME);
		eoObject.setActive(active);
		eoObject.setCreationTime(creationTime);
		eoObject.setName(name);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetTemplate" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetTemplate'");
		return _DataSetTemplate.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetTemplate" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetTemplate' with sortOrderings " + _sortOrderings);
		return _DataSetTemplate.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetTemplate" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetTemplate.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetTemplate" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetTemplate' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetTemplate.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetTemplate fetchDataSetTemplateWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetTemplate.fetchDataSetTemplateWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetTemplate fetchDataSetTemplateWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetTemplate' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetTemplate.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetTemplate eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetTemplate)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetTemplate that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetTemplate fetchRequiredDataSetTemplateWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetTemplate.fetchRequiredDataSetTemplateWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetTemplate fetchRequiredDataSetTemplateWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetTemplate' with qualifier: " + _qualifier);
		DataSetTemplate eoObject = _DataSetTemplate.fetchDataSetTemplateWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetTemplate that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetTemplate localInstanceOfDataSetTemplate(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetTemplate': " + this.toString());
		return (DataSetTemplate)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetTemplate localInstanceOfDataSetTemplate(EOEditingContext _editingContext, DataSetTemplate _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetTemplate': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetTemplate)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "bottomText"
	 */
    public String bottomText() {
        return (String) storedValueForKey("bottomText");
    }

	/**
	 * Set the value for "bottomText"
	 */
    public void setBottomText(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating bottomText from "+bottomText()+" to "+aValue );
        takeStoredValueForKey(aValue, "bottomText");
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
	 * The value for "cssClassForThankYouMessage"
	 */
    public String cssClassForThankYouMessage() {
        return (String) storedValueForKey("cssClassForThankYouMessage");
    }

	/**
	 * Set the value for "cssClassForThankYouMessage"
	 */
    public void setCssClassForThankYouMessage(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating cssClassForThankYouMessage from "+cssClassForThankYouMessage()+" to "+aValue );
        takeStoredValueForKey(aValue, "cssClassForThankYouMessage");
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
	 * The value for "loginRequired"
	 */
    public Boolean loginRequired() {
        return (Boolean) storedValueForKey("loginRequired");
    }

	/**
	 * Set the value for "loginRequired"
	 */
    public void setLoginRequired(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating loginRequired from "+loginRequired()+" to "+aValue );
        takeStoredValueForKey(aValue, "loginRequired");
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

	/**
	 * The value for "textForThankYouPage"
	 */
    public String textForThankYouPage() {
        return (String) storedValueForKey("textForThankYouPage");
    }

	/**
	 * Set the value for "textForThankYouPage"
	 */
    public void setTextForThankYouPage(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating textForThankYouPage from "+textForThankYouPage()+" to "+aValue );
        takeStoredValueForKey(aValue, "textForThankYouPage");
    }

	/**
	 * The value for "topText"
	 */
    public String topText() {
        return (String) storedValueForKey("topText");
    }

	/**
	 * Set the value for "topText"
	 */
    public void setTopText(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating topText from "+topText()+" to "+aValue );
        takeStoredValueForKey(aValue, "topText");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.Person createdBy() {
        return (net.events.cms.eo.Person)storedValueForKey("createdBy");
    }

    public void setCreatedBy(net.events.cms.eo.Person aValue) {
        takeStoredValueForKey(aValue, "createdBy");
    }

    public net.events.cms.eo.DataSetPageTemplate dataSetPageTemplate() {
        return (net.events.cms.eo.DataSetPageTemplate)storedValueForKey("dataSetPageTemplate");
    }

    public void setDataSetPageTemplate(net.events.cms.eo.DataSetPageTemplate aValue) {
        takeStoredValueForKey(aValue, "dataSetPageTemplate");
    }

	/**
	 * Returns the objects for the relationship "dataSetEntries"
	 */
    public NSArray dataSetEntries() {
        return (NSArray)storedValueForKey("dataSetEntries");
    }

    public void setDataSetEntries(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating dataSetEntries from "+dataSetEntries()+" to "+aValue );
        takeStoredValueForKey(aValue, "dataSetEntries");
    }

    public void addToDataSetEntries(net.events.cms.eo.DataSetEntry object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to dataSetEntries" );
	    includeObjectIntoPropertyWithKey(object, "dataSetEntries");
    }
    

    public void removeFromDataSetEntries(net.events.cms.eo.DataSetEntry object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from dataSetEntries" );
	    excludeObjectFromPropertyWithKey(object, "dataSetEntries");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.DataSetEntry" and add it
     * to the relationship "dataSetEntries"
     */
    public net.events.cms.eo.DataSetEntry createObjectAndAddToDataSetEntries() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: dataSetEntries");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("DataSetEntry");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "dataSetEntries");
	    return (net.events.cms.eo.DataSetEntry) eoObject;
    }
    
    /**
     * Removes object from the relationship "dataSetEntries" and delete object
     */
    public void removeFromDataSetEntriesAndDelete(net.events.cms.eo.DataSetEntry object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: dataSetEntries");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "dataSetEntries");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "dataSetEntries", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllDataSetEntries() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: dataSetEntries");
	    Enumeration objects = dataSetEntries().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromDataSetEntriesAndDelete((net.events.cms.eo.DataSetEntry)objects.nextElement());
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

	/**
	 * Returns the objects for the relationship "pages"
	 */
    public NSArray pages() {
        return (NSArray)storedValueForKey("pages");
    }

    public void setPages(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating pages from "+pages()+" to "+aValue );
        takeStoredValueForKey(aValue, "pages");
    }

    public void addToPages(net.events.cms.eo.DataSetPage object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to pages" );
	    includeObjectIntoPropertyWithKey(object, "pages");
    }
    

    public void removeFromPages(net.events.cms.eo.DataSetPage object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from pages" );
	    excludeObjectFromPropertyWithKey(object, "pages");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.DataSetPage" and add it
     * to the relationship "pages"
     */
    public net.events.cms.eo.DataSetPage createObjectAndAddToPages() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: pages");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("DataSetPage");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "pages");
	    return (net.events.cms.eo.DataSetPage) eoObject;
    }
    
    /**
     * Removes object from the relationship "pages" and delete object
     */
    public void removeFromPagesAndDelete(net.events.cms.eo.DataSetPage object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: pages");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "pages");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "pages", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllPages() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: pages");
	    Enumeration objects = pages().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromPagesAndDelete((net.events.cms.eo.DataSetPage)objects.nextElement());
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