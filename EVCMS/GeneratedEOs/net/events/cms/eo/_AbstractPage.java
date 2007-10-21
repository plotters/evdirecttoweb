// _AbstractPage.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to AbstractPage.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _AbstractPage extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _AbstractPage.class );
	
	// KeyValueCoding support
	
    public static final String STYLESHEET = "stylesheet";
    public static final String SITE = "site";
    public static final String PARENTPAGE = "parentPage";
    public static final String PAGEWRAPPER = "pageWrapper";
    public static final String PAGENAME = "pageName";
    public static final String PAGEELEMENTS = "pageElements";
    public static final String PAGEDESCRIPTION = "pageDescription";
    public static final String ORDERNUMBER = "orderNumber";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String CSSCONTAINERTYPE = "cssContainerType";
    public static final String CSSCONTAINERNAME = "cssContainerName";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String COMPONENTNAME = "componentName";
    public static final String CLIENT = "client";
    public static final String CHILDPAGES = "childPages";
    public static final String ACTIVE = "active";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "AbstractPage";

	/**
	 * Standard constructor
	 */	
    public _AbstractPage() {
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
	 * Create a "AbstractPage" object with all required values
	 */
	public static AbstractPage createAbstractPage(EOEditingContext editingContext, String componentName, NSTimestamp creationTime, String inheritanceType, Number orderNumber, String pageName, net.events.cms.eo.Client client, net.events.cms.eo.Person createdBy, net.events.cms.eo.PageWrapper pageWrapper, net.events.cms.eo.Site site) {
		if (log.isDebugEnabled()) log.debug ("Creating object: AbstractPage");
		AbstractPage eoObject = (AbstractPage)EOUtilities.createAndInsertInstance(editingContext, _AbstractPage.ENTITY_NAME);
		eoObject.setComponentName(componentName);
		eoObject.setCreationTime(creationTime);
		eoObject.setInheritanceType(inheritanceType);
		eoObject.setOrderNumber(orderNumber);
		eoObject.setPageName(pageName);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		eoObject.setPageWrapper(pageWrapper);
		eoObject.setSite(site);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "AbstractPage" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'AbstractPage'");
		return _AbstractPage.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "AbstractPage" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'AbstractPage' with sortOrderings " + _sortOrderings);
		return _AbstractPage.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "AbstractPage" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _AbstractPage.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "AbstractPage" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'AbstractPage' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_AbstractPage.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static AbstractPage fetchAbstractPageWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _AbstractPage.fetchAbstractPageWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static AbstractPage fetchAbstractPageWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'AbstractPage' with qualifier: " + _qualifier);
		NSArray eoObjects = _AbstractPage.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		AbstractPage eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (AbstractPage)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one AbstractPage that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static AbstractPage fetchRequiredAbstractPageWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _AbstractPage.fetchRequiredAbstractPageWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static AbstractPage fetchRequiredAbstractPageWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'AbstractPage' with qualifier: " + _qualifier);
		AbstractPage eoObject = _AbstractPage.fetchAbstractPageWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no AbstractPage that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public AbstractPage localInstanceOfAbstractPage(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'AbstractPage': " + this.toString());
		return (AbstractPage)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static AbstractPage localInstanceOfAbstractPage(EOEditingContext _editingContext, AbstractPage _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'AbstractPage': " + _eo.toString());
		}
		return (_eo == null) ? null : (AbstractPage)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "componentName"
	 */
    public String componentName() {
        return (String) storedValueForKey("componentName");
    }

	/**
	 * Set the value for "componentName"
	 */
    public void setComponentName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating componentName from "+componentName()+" to "+aValue );
        takeStoredValueForKey(aValue, "componentName");
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
	 * The value for "cssContainerName"
	 */
    public String cssContainerName() {
        return (String) storedValueForKey("cssContainerName");
    }

	/**
	 * Set the value for "cssContainerName"
	 */
    public void setCssContainerName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating cssContainerName from "+cssContainerName()+" to "+aValue );
        takeStoredValueForKey(aValue, "cssContainerName");
    }

	/**
	 * The value for "cssContainerType"
	 */
    public String cssContainerType() {
        return (String) storedValueForKey("cssContainerType");
    }

	/**
	 * Set the value for "cssContainerType"
	 */
    public void setCssContainerType(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating cssContainerType from "+cssContainerType()+" to "+aValue );
        takeStoredValueForKey(aValue, "cssContainerType");
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
	 * The value for "pageDescription"
	 */
    public String pageDescription() {
        return (String) storedValueForKey("pageDescription");
    }

	/**
	 * Set the value for "pageDescription"
	 */
    public void setPageDescription(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating pageDescription from "+pageDescription()+" to "+aValue );
        takeStoredValueForKey(aValue, "pageDescription");
    }

	/**
	 * The value for "pageName"
	 */
    public String pageName() {
        return (String) storedValueForKey("pageName");
    }

	/**
	 * Set the value for "pageName"
	 */
    public void setPageName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating pageName from "+pageName()+" to "+aValue );
        takeStoredValueForKey(aValue, "pageName");
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

    public net.events.cms.eo.PageWrapper pageWrapper() {
        return (net.events.cms.eo.PageWrapper)storedValueForKey("pageWrapper");
    }

    public void setPageWrapper(net.events.cms.eo.PageWrapper aValue) {
        takeStoredValueForKey(aValue, "pageWrapper");
    }

    public net.events.cms.eo.AbstractPage parentPage() {
        return (net.events.cms.eo.AbstractPage)storedValueForKey("parentPage");
    }

    public void setParentPage(net.events.cms.eo.AbstractPage aValue) {
        takeStoredValueForKey(aValue, "parentPage");
    }

    public net.events.cms.eo.Site site() {
        return (net.events.cms.eo.Site)storedValueForKey("site");
    }

    public void setSite(net.events.cms.eo.Site aValue) {
        takeStoredValueForKey(aValue, "site");
    }

    public net.events.cms.eo.Stylesheet stylesheet() {
        return (net.events.cms.eo.Stylesheet)storedValueForKey("stylesheet");
    }

    public void setStylesheet(net.events.cms.eo.Stylesheet aValue) {
        takeStoredValueForKey(aValue, "stylesheet");
    }

	/**
	 * Returns the objects for the relationship "childPages"
	 */
    public NSArray childPages() {
        return (NSArray)storedValueForKey("childPages");
    }

    public void setChildPages(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating childPages from "+childPages()+" to "+aValue );
        takeStoredValueForKey(aValue, "childPages");
    }

    public void addToChildPages(net.events.cms.eo.AbstractPage object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to childPages" );
	    includeObjectIntoPropertyWithKey(object, "childPages");
    }
    

    public void removeFromChildPages(net.events.cms.eo.AbstractPage object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from childPages" );
	    excludeObjectFromPropertyWithKey(object, "childPages");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.AbstractPage" and add it
     * to the relationship "childPages"
     */
    public net.events.cms.eo.AbstractPage createObjectAndAddToChildPages() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: childPages");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("AbstractPage");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "childPages");
	    return (net.events.cms.eo.AbstractPage) eoObject;
    }
    
    /**
     * Removes object from the relationship "childPages" and delete object
     */
    public void removeFromChildPagesAndDelete(net.events.cms.eo.AbstractPage object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: childPages");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "childPages");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "childPages", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllChildPages() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: childPages");
	    Enumeration objects = childPages().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromChildPagesAndDelete((net.events.cms.eo.AbstractPage)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "pageElements"
	 */
    public NSArray pageElements() {
        return (NSArray)storedValueForKey("pageElements");
    }

    public void setPageElements(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating pageElements from "+pageElements()+" to "+aValue );
        takeStoredValueForKey(aValue, "pageElements");
    }

    public void addToPageElements(net.events.cms.eo.PageElementOnPage object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to pageElements" );
	    includeObjectIntoPropertyWithKey(object, "pageElements");
    }
    

    public void removeFromPageElements(net.events.cms.eo.PageElementOnPage object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from pageElements" );
	    excludeObjectFromPropertyWithKey(object, "pageElements");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.PageElementOnPage" and add it
     * to the relationship "pageElements"
     */
    public net.events.cms.eo.PageElementOnPage createObjectAndAddToPageElements() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: pageElements");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("PageElementOnPage");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "pageElements");
	    return (net.events.cms.eo.PageElementOnPage) eoObject;
    }
    
    /**
     * Removes object from the relationship "pageElements" and delete object
     */
    public void removeFromPageElementsAndDelete(net.events.cms.eo.PageElementOnPage object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: pageElements");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "pageElements");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "pageElements", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllPageElements() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: pageElements");
	    Enumeration objects = pageElements().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromPageElementsAndDelete((net.events.cms.eo.PageElementOnPage)objects.nextElement());
    }
}