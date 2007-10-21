// _PageWrapper.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to PageWrapper.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _PageWrapper extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _PageWrapper.class );
	
	// KeyValueCoding support
	
    public static final String STYLESHEETS = "stylesheets";
    public static final String SITE = "site";
    public static final String PAGES = "pages";
    public static final String PAGEELEMENTS = "pageElements";
    public static final String NAME = "name";
    public static final String DEFAULTTITLE = "defaultTitle";
    public static final String COMPONENTTEMPLATE = "componentTemplate";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "PageWrapper";

	/**
	 * Standard constructor
	 */	
    public _PageWrapper() {
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
	 * Create a "PageWrapper" object with all required values
	 */
	public static PageWrapper createPageWrapper(EOEditingContext editingContext, String componentTemplate, String name, net.events.cms.eo.Client client, net.events.cms.eo.Site site) {
		if (log.isDebugEnabled()) log.debug ("Creating object: PageWrapper");
		PageWrapper eoObject = (PageWrapper)EOUtilities.createAndInsertInstance(editingContext, _PageWrapper.ENTITY_NAME);
		eoObject.setComponentTemplate(componentTemplate);
		eoObject.setName(name);
		eoObject.setClient(client);
		eoObject.setSite(site);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "PageWrapper" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'PageWrapper'");
		return _PageWrapper.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "PageWrapper" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'PageWrapper' with sortOrderings " + _sortOrderings);
		return _PageWrapper.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "PageWrapper" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _PageWrapper.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "PageWrapper" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'PageWrapper' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_PageWrapper.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static PageWrapper fetchPageWrapperWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _PageWrapper.fetchPageWrapperWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static PageWrapper fetchPageWrapperWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'PageWrapper' with qualifier: " + _qualifier);
		NSArray eoObjects = _PageWrapper.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		PageWrapper eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (PageWrapper)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one PageWrapper that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static PageWrapper fetchRequiredPageWrapperWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _PageWrapper.fetchRequiredPageWrapperWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static PageWrapper fetchRequiredPageWrapperWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'PageWrapper' with qualifier: " + _qualifier);
		PageWrapper eoObject = _PageWrapper.fetchPageWrapperWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no PageWrapper that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public PageWrapper localInstanceOfPageWrapper(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'PageWrapper': " + this.toString());
		return (PageWrapper)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static PageWrapper localInstanceOfPageWrapper(EOEditingContext _editingContext, PageWrapper _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'PageWrapper': " + _eo.toString());
		}
		return (_eo == null) ? null : (PageWrapper)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "componentTemplate"
	 */
    public String componentTemplate() {
        return (String) storedValueForKey("componentTemplate");
    }

	/**
	 * Set the value for "componentTemplate"
	 */
    public void setComponentTemplate(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating componentTemplate from "+componentTemplate()+" to "+aValue );
        takeStoredValueForKey(aValue, "componentTemplate");
    }

	/**
	 * The value for "defaultTitle"
	 */
    public String defaultTitle() {
        return (String) storedValueForKey("defaultTitle");
    }

	/**
	 * Set the value for "defaultTitle"
	 */
    public void setDefaultTitle(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating defaultTitle from "+defaultTitle()+" to "+aValue );
        takeStoredValueForKey(aValue, "defaultTitle");
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

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.Site site() {
        return (net.events.cms.eo.Site)storedValueForKey("site");
    }

    public void setSite(net.events.cms.eo.Site aValue) {
        takeStoredValueForKey(aValue, "site");
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

    public void addToPageElements(net.events.cms.eo.PageWrapperElement object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to pageElements" );
	    includeObjectIntoPropertyWithKey(object, "pageElements");
    }
    

    public void removeFromPageElements(net.events.cms.eo.PageWrapperElement object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from pageElements" );
	    excludeObjectFromPropertyWithKey(object, "pageElements");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.PageWrapperElement" and add it
     * to the relationship "pageElements"
     */
    public net.events.cms.eo.PageWrapperElement createObjectAndAddToPageElements() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: pageElements");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("PageWrapperElement");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "pageElements");
	    return (net.events.cms.eo.PageWrapperElement) eoObject;
    }
    
    /**
     * Removes object from the relationship "pageElements" and delete object
     */
    public void removeFromPageElementsAndDelete(net.events.cms.eo.PageWrapperElement object) {
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
	        removeFromPageElementsAndDelete((net.events.cms.eo.PageWrapperElement)objects.nextElement());
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

    public void addToPages(net.events.cms.eo.AbstractPage object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to pages" );
	    includeObjectIntoPropertyWithKey(object, "pages");
    }
    

    public void removeFromPages(net.events.cms.eo.AbstractPage object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from pages" );
	    excludeObjectFromPropertyWithKey(object, "pages");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.AbstractPage" and add it
     * to the relationship "pages"
     */
    public net.events.cms.eo.AbstractPage createObjectAndAddToPages() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: pages");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("AbstractPage");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "pages");
	    return (net.events.cms.eo.AbstractPage) eoObject;
    }
    
    /**
     * Removes object from the relationship "pages" and delete object
     */
    public void removeFromPagesAndDelete(net.events.cms.eo.AbstractPage object) {
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
	        removeFromPagesAndDelete((net.events.cms.eo.AbstractPage)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "stylesheets"
	 */
    public NSArray stylesheets() {
        return (NSArray)storedValueForKey("stylesheets");
    }

    public void setStylesheets(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating stylesheets from "+stylesheets()+" to "+aValue );
        takeStoredValueForKey(aValue, "stylesheets");
    }

    public void addToStylesheets(net.events.cms.eo.Stylesheet object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to stylesheets" );
	    includeObjectIntoPropertyWithKey(object, "stylesheets");
    }
    

    public void removeFromStylesheets(net.events.cms.eo.Stylesheet object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from stylesheets" );
	    excludeObjectFromPropertyWithKey(object, "stylesheets");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Stylesheet" and add it
     * to the relationship "stylesheets"
     */
    public net.events.cms.eo.Stylesheet createObjectAndAddToStylesheets() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: stylesheets");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Stylesheet");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "stylesheets");
	    return (net.events.cms.eo.Stylesheet) eoObject;
    }
    
    /**
     * Removes object from the relationship "stylesheets" and delete object
     */
    public void removeFromStylesheetsAndDelete(net.events.cms.eo.Stylesheet object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: stylesheets");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "stylesheets");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "stylesheets", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllStylesheets() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: stylesheets");
	    Enumeration objects = stylesheets().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromStylesheetsAndDelete((net.events.cms.eo.Stylesheet)objects.nextElement());
    }
}