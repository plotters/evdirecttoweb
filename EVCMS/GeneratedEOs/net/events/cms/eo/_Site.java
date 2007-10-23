// _Site.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Site.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Site extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Site.class );
	
	// KeyValueCoding support
	
    public static final String STYLESHEETS = "stylesheets";
    public static final String STARTPAGE = "startpage";
    public static final String RESOURCEPATH = "resourcePath";
    public static final String PAGES = "pages";
    public static final String PAGEWRAPPERS = "pageWrappers";
    public static final String NAME = "name";
    public static final String EDITORS = "editors";
    public static final String DOMAINS = "domains";
    public static final String DEFAULTTITLE = "defaultTitle";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Site";

	/**
	 * Standard constructor
	 */	
    public _Site() {
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
	 * Create a "Site" object with all required values
	 */
	public static Site createSite(EOEditingContext editingContext, String name, String resourcePath, net.events.cms.eo.Client client) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Site");
		Site eoObject = (Site)EOUtilities.createAndInsertInstance(editingContext, _Site.ENTITY_NAME);
		eoObject.setName(name);
		eoObject.setResourcePath(resourcePath);
		eoObject.setClient(client);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Site" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Site'");
		return _Site.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Site" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Site' with sortOrderings " + _sortOrderings);
		return _Site.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Site" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Site.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Site" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Site' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Site.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Site fetchSiteWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Site.fetchSiteWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Site fetchSiteWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Site' with qualifier: " + _qualifier);
		NSArray eoObjects = _Site.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Site eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Site)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Site that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Site fetchRequiredSiteWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Site.fetchRequiredSiteWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Site fetchRequiredSiteWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Site' with qualifier: " + _qualifier);
		Site eoObject = _Site.fetchSiteWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Site that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Site localInstanceOfSite(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Site': " + this.toString());
		return (Site)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Site localInstanceOfSite(EOEditingContext _editingContext, Site _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Site': " + _eo.toString());
		}
		return (_eo == null) ? null : (Site)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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

	/**
	 * The value for "resourcePath"
	 */
    public String resourcePath() {
        return (String) storedValueForKey("resourcePath");
    }

	/**
	 * Set the value for "resourcePath"
	 */
    public void setResourcePath(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating resourcePath from "+resourcePath()+" to "+aValue );
        takeStoredValueForKey(aValue, "resourcePath");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.AbstractPage startpage() {
        return (net.events.cms.eo.AbstractPage)storedValueForKey("startpage");
    }

    public void setStartpage(net.events.cms.eo.AbstractPage aValue) {
        takeStoredValueForKey(aValue, "startpage");
    }

	/**
	 * Returns the objects for the relationship "domains"
	 */
    public NSArray domains() {
        return (NSArray)storedValueForKey("domains");
    }

    public void setDomains(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating domains from "+domains()+" to "+aValue );
        takeStoredValueForKey(aValue, "domains");
    }

    public void addToDomains(net.events.cms.eo.Domain object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to domains" );
	    includeObjectIntoPropertyWithKey(object, "domains");
    }
    

    public void removeFromDomains(net.events.cms.eo.Domain object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from domains" );
	    excludeObjectFromPropertyWithKey(object, "domains");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Domain" and add it
     * to the relationship "domains"
     */
    public net.events.cms.eo.Domain createObjectAndAddToDomains() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: domains");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Domain");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "domains");
	    return (net.events.cms.eo.Domain) eoObject;
    }
    
    /**
     * Removes object from the relationship "domains" and delete object
     */
    public void removeFromDomainsAndDelete(net.events.cms.eo.Domain object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: domains");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "domains");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "domains", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllDomains() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: domains");
	    Enumeration objects = domains().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromDomainsAndDelete((net.events.cms.eo.Domain)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "editors"
	 */
    public NSArray editors() {
        return (NSArray)storedValueForKey("editors");
    }

    public void setEditors(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating editors from "+editors()+" to "+aValue );
        takeStoredValueForKey(aValue, "editors");
    }

    public void addToEditors(net.events.cms.eo.EditedSite object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to editors" );
	    includeObjectIntoPropertyWithKey(object, "editors");
    }
    

    public void removeFromEditors(net.events.cms.eo.EditedSite object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from editors" );
	    excludeObjectFromPropertyWithKey(object, "editors");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.EditedSite" and add it
     * to the relationship "editors"
     */
    public net.events.cms.eo.EditedSite createObjectAndAddToEditors() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: editors");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("EditedSite");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "editors");
	    return (net.events.cms.eo.EditedSite) eoObject;
    }
    
    /**
     * Removes object from the relationship "editors" and delete object
     */
    public void removeFromEditorsAndDelete(net.events.cms.eo.EditedSite object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: editors");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "editors");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "editors", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllEditors() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: editors");
	    Enumeration objects = editors().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromEditorsAndDelete((net.events.cms.eo.EditedSite)objects.nextElement());
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

    public void addToPageWrappers(net.events.cms.eo.PageWrapperEO object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to pageWrappers" );
	    includeObjectIntoPropertyWithKey(object, "pageWrappers");
    }
    

    public void removeFromPageWrappers(net.events.cms.eo.PageWrapperEO object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from pageWrappers" );
	    excludeObjectFromPropertyWithKey(object, "pageWrappers");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.PageWrapperEO" and add it
     * to the relationship "pageWrappers"
     */
    public net.events.cms.eo.PageWrapperEO createObjectAndAddToPageWrappers() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: pageWrappers");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("PageWrapperEO");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "pageWrappers");
	    return (net.events.cms.eo.PageWrapperEO) eoObject;
    }
    
    /**
     * Removes object from the relationship "pageWrappers" and delete object
     */
    public void removeFromPageWrappersAndDelete(net.events.cms.eo.PageWrapperEO object) {
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
	        removeFromPageWrappersAndDelete((net.events.cms.eo.PageWrapperEO)objects.nextElement());
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

	/**
	 * Gets the objects for the fetchspec "NameAndTitleFetchSpec" and bindings
	 */
	public static NSArray fetchWithNameAndTitleFetchSpec( EOEditingContext ec, String nameBinding, String titleBinding ) {
		NSMutableDictionary	bindings = new NSMutableDictionary();
		if( nameBinding != null )
			bindings.setObjectForKey(nameBinding, "name");
		if( titleBinding != null )
			bindings.setObjectForKey(titleBinding, "title");
			
		if (bindings.allKeys() == null || bindings.allKeys().count() == 0) {
			bindings = null;
		}
		
		return EOUtilities.objectsWithFetchSpecificationAndBindings(
			ec,
			"Site",
			"NameAndTitleFetchSpec",
			bindings );
	}
	
	/**
	 * fetches ONE object for the fetchspec NameAndTitleFetchSpec
	 */
	public static Site fetchOneWithNameAndTitleFetchSpec( EOEditingContext ec, String nameBinding, String titleBinding ) {
		Site	result = null;
		NSMutableDictionary	bindings = new NSMutableDictionary();
		if( nameBinding != null )
			bindings.setObjectForKey(nameBinding, "name");
		if( titleBinding != null )
			bindings.setObjectForKey(titleBinding, "title");
			
		if (bindings.allKeys() == null || bindings.allKeys().count() == 0) {
			bindings = null;
		}
		try {
			result = (Site) EOUtilities.objectWithFetchSpecificationAndBindings(
				ec,
				"Site",
				"NameAndTitleFetchSpec",
				bindings );
		} catch( EOObjectNotAvailableException e ) {}
		
		return result;
	}
}