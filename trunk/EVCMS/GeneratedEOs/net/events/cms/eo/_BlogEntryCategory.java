// _BlogEntryCategory.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to BlogEntryCategory.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _BlogEntryCategory extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _BlogEntryCategory.class );
	
	// KeyValueCoding support
	
    public static final String ENTRIES = "entries";
    public static final String CLIENT = "client";
    public static final String CATEGORYNAME = "categoryName";
    public static final String BLOG = "blog";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "BlogEntryCategory";

	/**
	 * Standard constructor
	 */	
    public _BlogEntryCategory() {
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
	 * Create a "BlogEntryCategory" object with all required values
	 */
	public static BlogEntryCategory createBlogEntryCategory(EOEditingContext editingContext, String categoryName, net.events.cms.eo.Blog blog, net.events.cms.eo.Client client) {
		if (log.isDebugEnabled()) log.debug ("Creating object: BlogEntryCategory");
		BlogEntryCategory eoObject = (BlogEntryCategory)EOUtilities.createAndInsertInstance(editingContext, _BlogEntryCategory.ENTITY_NAME);
		eoObject.setCategoryName(categoryName);
		eoObject.setBlog(blog);
		eoObject.setClient(client);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "BlogEntryCategory" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogEntryCategory'");
		return _BlogEntryCategory.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "BlogEntryCategory" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogEntryCategory' with sortOrderings " + _sortOrderings);
		return _BlogEntryCategory.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "BlogEntryCategory" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogEntryCategory.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "BlogEntryCategory" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogEntryCategory' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_BlogEntryCategory.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static BlogEntryCategory fetchBlogEntryCategoryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogEntryCategory.fetchBlogEntryCategoryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static BlogEntryCategory fetchBlogEntryCategoryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'BlogEntryCategory' with qualifier: " + _qualifier);
		NSArray eoObjects = _BlogEntryCategory.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		BlogEntryCategory eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (BlogEntryCategory)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one BlogEntryCategory that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static BlogEntryCategory fetchRequiredBlogEntryCategoryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogEntryCategory.fetchRequiredBlogEntryCategoryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static BlogEntryCategory fetchRequiredBlogEntryCategoryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'BlogEntryCategory' with qualifier: " + _qualifier);
		BlogEntryCategory eoObject = _BlogEntryCategory.fetchBlogEntryCategoryWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no BlogEntryCategory that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public BlogEntryCategory localInstanceOfBlogEntryCategory(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'BlogEntryCategory': " + this.toString());
		return (BlogEntryCategory)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static BlogEntryCategory localInstanceOfBlogEntryCategory(EOEditingContext _editingContext, BlogEntryCategory _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'BlogEntryCategory': " + _eo.toString());
		}
		return (_eo == null) ? null : (BlogEntryCategory)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "categoryName"
	 */
    public String categoryName() {
        return (String) storedValueForKey("categoryName");
    }

	/**
	 * Set the value for "categoryName"
	 */
    public void setCategoryName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating categoryName from "+categoryName()+" to "+aValue );
        takeStoredValueForKey(aValue, "categoryName");
    }

    public net.events.cms.eo.Blog blog() {
        return (net.events.cms.eo.Blog)storedValueForKey("blog");
    }

    public void setBlog(net.events.cms.eo.Blog aValue) {
        takeStoredValueForKey(aValue, "blog");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
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