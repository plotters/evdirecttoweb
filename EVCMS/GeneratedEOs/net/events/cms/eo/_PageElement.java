// _PageElement.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to PageElement.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _PageElement extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _PageElement.class );
	
	// KeyValueCoding support
	
    public static final String SITE = "site";
    public static final String PAGES = "pages";
    public static final String PAGEWRAPPERS = "pageWrappers";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String ELEMENTNAME = "elementName";
    public static final String ELEMENTDESCRIPTION = "elementDescription";
    public static final String CSSCONTAINERTYPE = "cssContainerType";
    public static final String CSSCONTAINERNAME = "cssContainerName";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String COMPONENTNAME = "componentName";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "PageElement";

	/**
	 * Standard constructor
	 */	
    public _PageElement() {
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
	 * Create a "PageElement" object with all required values
	 */
	public static PageElement createPageElement(EOEditingContext editingContext, String componentName, NSTimestamp creationTime, String elementName, String inheritanceType, net.events.cms.eo.Client client, net.events.cms.eo.Person createdBy, net.events.cms.eo.Site site) {
		if (log.isDebugEnabled()) log.debug ("Creating object: PageElement");
		PageElement eoObject = (PageElement)EOUtilities.createAndInsertInstance(editingContext, _PageElement.ENTITY_NAME);
		eoObject.setComponentName(componentName);
		eoObject.setCreationTime(creationTime);
		eoObject.setElementName(elementName);
		eoObject.setInheritanceType(inheritanceType);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		eoObject.setSite(site);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "PageElement" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'PageElement'");
		return _PageElement.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "PageElement" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'PageElement' with sortOrderings " + _sortOrderings);
		return _PageElement.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "PageElement" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _PageElement.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "PageElement" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'PageElement' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_PageElement.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static PageElement fetchPageElementWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _PageElement.fetchPageElementWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static PageElement fetchPageElementWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'PageElement' with qualifier: " + _qualifier);
		NSArray eoObjects = _PageElement.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		PageElement eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (PageElement)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one PageElement that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static PageElement fetchRequiredPageElementWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _PageElement.fetchRequiredPageElementWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static PageElement fetchRequiredPageElementWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'PageElement' with qualifier: " + _qualifier);
		PageElement eoObject = _PageElement.fetchPageElementWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no PageElement that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public PageElement localInstanceOfPageElement(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'PageElement': " + this.toString());
		return (PageElement)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static PageElement localInstanceOfPageElement(EOEditingContext _editingContext, PageElement _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'PageElement': " + _eo.toString());
		}
		return (_eo == null) ? null : (PageElement)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "elementDescription"
	 */
    public String elementDescription() {
        return (String) storedValueForKey("elementDescription");
    }

	/**
	 * Set the value for "elementDescription"
	 */
    public void setElementDescription(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating elementDescription from "+elementDescription()+" to "+aValue );
        takeStoredValueForKey(aValue, "elementDescription");
    }

	/**
	 * The value for "elementName"
	 */
    public String elementName() {
        return (String) storedValueForKey("elementName");
    }

	/**
	 * Set the value for "elementName"
	 */
    public void setElementName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating elementName from "+elementName()+" to "+aValue );
        takeStoredValueForKey(aValue, "elementName");
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

    public net.events.cms.eo.Site site() {
        return (net.events.cms.eo.Site)storedValueForKey("site");
    }

    public void setSite(net.events.cms.eo.Site aValue) {
        takeStoredValueForKey(aValue, "site");
    }

	/**
	 * Returns the objects for the relationship "pageWrappers"
	 */
    public NSArray pageWrappers() {
        return (NSArray)storedValueForKey("pageWrappers");
    }

    public void setPageWrappers(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating pageWrappers from "+pageWrappers()+" to "+aValue );
        takeStoredValueForKey(aValue, "pageWrappers");
    }

    public void addToPageWrappers(net.events.cms.eo.PageWrapperElement object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to pageWrappers" );
	    includeObjectIntoPropertyWithKey(object, "pageWrappers");
    }
    

    public void removeFromPageWrappers(net.events.cms.eo.PageWrapperElement object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from pageWrappers" );
	    excludeObjectFromPropertyWithKey(object, "pageWrappers");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.PageWrapperElement" and add it
     * to the relationship "pageWrappers"
     */
    public net.events.cms.eo.PageWrapperElement createObjectAndAddToPageWrappers() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: pageWrappers");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("PageWrapperElement");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "pageWrappers");
	    return (net.events.cms.eo.PageWrapperElement) eoObject;
    }
    
    /**
     * Removes object from the relationship "pageWrappers" and delete object
     */
    public void removeFromPageWrappersAndDelete(net.events.cms.eo.PageWrapperElement object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: pageWrappers");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "pageWrappers");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "pageWrappers", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllPageWrappers() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: pageWrappers");
	    Enumeration objects = pageWrappers().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromPageWrappersAndDelete((net.events.cms.eo.PageWrapperElement)objects.nextElement());
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

    public void addToPages(net.events.cms.eo.PageElementOnPage object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to pages" );
	    includeObjectIntoPropertyWithKey(object, "pages");
    }
    

    public void removeFromPages(net.events.cms.eo.PageElementOnPage object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from pages" );
	    excludeObjectFromPropertyWithKey(object, "pages");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.PageElementOnPage" and add it
     * to the relationship "pages"
     */
    public net.events.cms.eo.PageElementOnPage createObjectAndAddToPages() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: pages");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("PageElementOnPage");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "pages");
	    return (net.events.cms.eo.PageElementOnPage) eoObject;
    }
    
    /**
     * Removes object from the relationship "pages" and delete object
     */
    public void removeFromPagesAndDelete(net.events.cms.eo.PageElementOnPage object) {
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
	        removeFromPagesAndDelete((net.events.cms.eo.PageElementOnPage)objects.nextElement());
    }
}