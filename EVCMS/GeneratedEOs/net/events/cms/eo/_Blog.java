// _Blog.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Blog.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Blog extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Blog.class );
	
	// KeyValueCoding support
	
    public static final String SITE = "site";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String ENTRIES = "entries";
    public static final String EDITORS = "editors";
    public static final String DEFAULTLANGUAGE = "defaultLanguage";
    public static final String CLIENT = "client";
    public static final String CATEGORIES = "categories";
    public static final String BLOGPAGES = "blogPages";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Blog";

	/**
	 * Standard constructor
	 */	
    public _Blog() {
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
	 * Create a "Blog" object with all required values
	 */
	public static Blog createBlog(EOEditingContext editingContext, String name, net.events.cms.eo.Client client, net.events.cms.eo.Site site) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Blog");
		Blog eoObject = (Blog)EOUtilities.createAndInsertInstance(editingContext, _Blog.ENTITY_NAME);
		eoObject.setName(name);
		eoObject.setClient(client);
		eoObject.setSite(site);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Blog" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Blog'");
		return _Blog.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Blog" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Blog' with sortOrderings " + _sortOrderings);
		return _Blog.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Blog" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Blog.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Blog" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Blog' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Blog.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Blog fetchBlogWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Blog.fetchBlogWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Blog fetchBlogWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Blog' with qualifier: " + _qualifier);
		NSArray eoObjects = _Blog.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Blog eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Blog)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Blog that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Blog fetchRequiredBlogWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Blog.fetchRequiredBlogWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Blog fetchRequiredBlogWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Blog' with qualifier: " + _qualifier);
		Blog eoObject = _Blog.fetchBlogWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Blog that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Blog localInstanceOfBlog(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Blog': " + this.toString());
		return (Blog)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Blog localInstanceOfBlog(EOEditingContext _editingContext, Blog _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Blog': " + _eo.toString());
		}
		return (_eo == null) ? null : (Blog)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "password"
	 */
    public String password() {
        return (String) storedValueForKey("password");
    }

	/**
	 * Set the value for "password"
	 */
    public void setPassword(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating password from "+password()+" to "+aValue );
        takeStoredValueForKey(aValue, "password");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.Language defaultLanguage() {
        return (net.events.cms.eo.Language)storedValueForKey("defaultLanguage");
    }

    public void setDefaultLanguage(net.events.cms.eo.Language aValue) {
        takeStoredValueForKey(aValue, "defaultLanguage");
    }

    public net.events.cms.eo.Site site() {
        return (net.events.cms.eo.Site)storedValueForKey("site");
    }

    public void setSite(net.events.cms.eo.Site aValue) {
        takeStoredValueForKey(aValue, "site");
    }

	/**
	 * Returns the objects for the relationship "blogPages"
	 */
    public NSArray blogPages() {
        return (NSArray)storedValueForKey("blogPages");
    }

    public void setBlogPages(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating blogPages from "+blogPages()+" to "+aValue );
        takeStoredValueForKey(aValue, "blogPages");
    }

    public void addToBlogPages(net.events.cms.eo.BlogPage object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to blogPages" );
	    includeObjectIntoPropertyWithKey(object, "blogPages");
    }
    

    public void removeFromBlogPages(net.events.cms.eo.BlogPage object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from blogPages" );
	    excludeObjectFromPropertyWithKey(object, "blogPages");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.BlogPage" and add it
     * to the relationship "blogPages"
     */
    public net.events.cms.eo.BlogPage createObjectAndAddToBlogPages() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: blogPages");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("BlogPage");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "blogPages");
	    return (net.events.cms.eo.BlogPage) eoObject;
    }
    
    /**
     * Removes object from the relationship "blogPages" and delete object
     */
    public void removeFromBlogPagesAndDelete(net.events.cms.eo.BlogPage object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: blogPages");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "blogPages");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "blogPages", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllBlogPages() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: blogPages");
	    Enumeration objects = blogPages().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromBlogPagesAndDelete((net.events.cms.eo.BlogPage)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "categories"
	 */
    public NSArray categories() {
        return (NSArray)storedValueForKey("categories");
    }

    public void setCategories(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating categories from "+categories()+" to "+aValue );
        takeStoredValueForKey(aValue, "categories");
    }

    public void addToCategories(net.events.cms.eo.BlogEntryCategory object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to categories" );
	    includeObjectIntoPropertyWithKey(object, "categories");
    }
    

    public void removeFromCategories(net.events.cms.eo.BlogEntryCategory object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from categories" );
	    excludeObjectFromPropertyWithKey(object, "categories");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.BlogEntryCategory" and add it
     * to the relationship "categories"
     */
    public net.events.cms.eo.BlogEntryCategory createObjectAndAddToCategories() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: categories");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("BlogEntryCategory");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "categories");
	    return (net.events.cms.eo.BlogEntryCategory) eoObject;
    }
    
    /**
     * Removes object from the relationship "categories" and delete object
     */
    public void removeFromCategoriesAndDelete(net.events.cms.eo.BlogEntryCategory object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: categories");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "categories");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "categories", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllCategories() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: categories");
	    Enumeration objects = categories().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromCategoriesAndDelete((net.events.cms.eo.BlogEntryCategory)objects.nextElement());
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

    public void addToEditors(net.events.cms.eo.EventsUser object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to editors" );
	    includeObjectIntoPropertyWithKey(object, "editors");
    }
    

    public void removeFromEditors(net.events.cms.eo.EventsUser object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from editors" );
	    excludeObjectFromPropertyWithKey(object, "editors");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.EventsUser" and add it
     * to the relationship "editors"
     */
    public net.events.cms.eo.EventsUser createObjectAndAddToEditors() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: editors");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("EventsUser");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "editors");
	    return (net.events.cms.eo.EventsUser) eoObject;
    }
    
    /**
     * Removes object from the relationship "editors" and delete object
     */
    public void removeFromEditorsAndDelete(net.events.cms.eo.EventsUser object) {
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
	        removeFromEditorsAndDelete((net.events.cms.eo.EventsUser)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "entries"
	 */
    public NSArray entries() {
        return (NSArray)storedValueForKey("entries");
    }

    public void setEntries(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating entries from "+entries()+" to "+aValue );
        takeStoredValueForKey(aValue, "entries");
    }

    public void addToEntries(net.events.cms.eo.BlogEntry object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to entries" );
	    includeObjectIntoPropertyWithKey(object, "entries");
    }
    

    public void removeFromEntries(net.events.cms.eo.BlogEntry object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from entries" );
	    excludeObjectFromPropertyWithKey(object, "entries");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.BlogEntry" and add it
     * to the relationship "entries"
     */
    public net.events.cms.eo.BlogEntry createObjectAndAddToEntries() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: entries");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("BlogEntry");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "entries");
	    return (net.events.cms.eo.BlogEntry) eoObject;
    }
    
    /**
     * Removes object from the relationship "entries" and delete object
     */
    public void removeFromEntriesAndDelete(net.events.cms.eo.BlogEntry object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: entries");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "entries");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "entries", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllEntries() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: entries");
	    Enumeration objects = entries().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromEntriesAndDelete((net.events.cms.eo.BlogEntry)objects.nextElement());
    }
}