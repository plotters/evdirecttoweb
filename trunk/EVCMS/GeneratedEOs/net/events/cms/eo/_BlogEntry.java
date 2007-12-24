// _BlogEntry.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to BlogEntry.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _BlogEntry extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _BlogEntry.class );
	
	// KeyValueCoding support
	
    public static final String VIEWHISTORYENTRIES = "viewHistoryEntries";
    public static final String TITLE = "title";
    public static final String TEASER = "teaser";
    public static final String SUBTITLE = "subtitle";
    public static final String LANGUAGE = "language";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CONTENT = "content";
    public static final String COMMENTS = "comments";
    public static final String CLIENT = "client";
    public static final String CATEGORY = "category";
    public static final String BLOG = "blog";
    public static final String ALLOWDIGGING = "allowDigging";
    public static final String ACTIVE = "active";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "BlogEntry";

	/**
	 * Standard constructor
	 */	
    public _BlogEntry() {
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
	 * Create a "BlogEntry" object with all required values
	 */
	public static BlogEntry createBlogEntry(EOEditingContext editingContext, Boolean active, NSTimestamp creationTime, String teaser, String title, net.events.cms.eo.Blog blog, net.events.cms.eo.Client client, net.events.cms.eo.Person createdBy) {
		if (log.isDebugEnabled()) log.debug ("Creating object: BlogEntry");
		BlogEntry eoObject = (BlogEntry)EOUtilities.createAndInsertInstance(editingContext, _BlogEntry.ENTITY_NAME);
		eoObject.setActive(active);
		eoObject.setCreationTime(creationTime);
		eoObject.setTeaser(teaser);
		eoObject.setTitle(title);
		eoObject.setBlog(blog);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "BlogEntry" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogEntry'");
		return _BlogEntry.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "BlogEntry" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogEntry' with sortOrderings " + _sortOrderings);
		return _BlogEntry.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "BlogEntry" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogEntry.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "BlogEntry" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogEntry' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_BlogEntry.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static BlogEntry fetchBlogEntryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogEntry.fetchBlogEntryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static BlogEntry fetchBlogEntryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'BlogEntry' with qualifier: " + _qualifier);
		NSArray eoObjects = _BlogEntry.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		BlogEntry eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (BlogEntry)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one BlogEntry that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static BlogEntry fetchRequiredBlogEntryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogEntry.fetchRequiredBlogEntryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static BlogEntry fetchRequiredBlogEntryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'BlogEntry' with qualifier: " + _qualifier);
		BlogEntry eoObject = _BlogEntry.fetchBlogEntryWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no BlogEntry that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public BlogEntry localInstanceOfBlogEntry(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'BlogEntry': " + this.toString());
		return (BlogEntry)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static BlogEntry localInstanceOfBlogEntry(EOEditingContext _editingContext, BlogEntry _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'BlogEntry': " + _eo.toString());
		}
		return (_eo == null) ? null : (BlogEntry)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "allowDigging"
	 */
    public Boolean allowDigging() {
        return (Boolean) storedValueForKey("allowDigging");
    }

	/**
	 * Set the value for "allowDigging"
	 */
    public void setAllowDigging(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating allowDigging from "+allowDigging()+" to "+aValue );
        takeStoredValueForKey(aValue, "allowDigging");
    }

	/**
	 * The value for "content"
	 */
    public String content() {
        return (String) storedValueForKey("content");
    }

	/**
	 * Set the value for "content"
	 */
    public void setContent(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating content from "+content()+" to "+aValue );
        takeStoredValueForKey(aValue, "content");
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
	 * The value for "subtitle"
	 */
    public String subtitle() {
        return (String) storedValueForKey("subtitle");
    }

	/**
	 * Set the value for "subtitle"
	 */
    public void setSubtitle(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating subtitle from "+subtitle()+" to "+aValue );
        takeStoredValueForKey(aValue, "subtitle");
    }

	/**
	 * The value for "teaser"
	 */
    public String teaser() {
        return (String) storedValueForKey("teaser");
    }

	/**
	 * Set the value for "teaser"
	 */
    public void setTeaser(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating teaser from "+teaser()+" to "+aValue );
        takeStoredValueForKey(aValue, "teaser");
    }

	/**
	 * The value for "title"
	 */
    public String title() {
        return (String) storedValueForKey("title");
    }

	/**
	 * Set the value for "title"
	 */
    public void setTitle(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating title from "+title()+" to "+aValue );
        takeStoredValueForKey(aValue, "title");
    }

    public net.events.cms.eo.Blog blog() {
        return (net.events.cms.eo.Blog)storedValueForKey("blog");
    }

    public void setBlog(net.events.cms.eo.Blog aValue) {
        takeStoredValueForKey(aValue, "blog");
    }

    public net.events.cms.eo.BlogEntryCategory category() {
        return (net.events.cms.eo.BlogEntryCategory)storedValueForKey("category");
    }

    public void setCategory(net.events.cms.eo.BlogEntryCategory aValue) {
        takeStoredValueForKey(aValue, "category");
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

    public net.events.cms.eo.Language language() {
        return (net.events.cms.eo.Language)storedValueForKey("language");
    }

    public void setLanguage(net.events.cms.eo.Language aValue) {
        takeStoredValueForKey(aValue, "language");
    }

	/**
	 * Returns the objects for the relationship "comments"
	 */
    public NSArray comments() {
        return (NSArray)storedValueForKey("comments");
    }

    public void setComments(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating comments from "+comments()+" to "+aValue );
        takeStoredValueForKey(aValue, "comments");
    }

    public void addToComments(net.events.cms.eo.BlogComment object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to comments" );
	    includeObjectIntoPropertyWithKey(object, "comments");
    }
    

    public void removeFromComments(net.events.cms.eo.BlogComment object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from comments" );
	    excludeObjectFromPropertyWithKey(object, "comments");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.BlogComment" and add it
     * to the relationship "comments"
     */
    public net.events.cms.eo.BlogComment createObjectAndAddToComments() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: comments");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("BlogComment");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "comments");
	    return (net.events.cms.eo.BlogComment) eoObject;
    }
    
    /**
     * Removes object from the relationship "comments" and delete object
     */
    public void removeFromCommentsAndDelete(net.events.cms.eo.BlogComment object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: comments");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "comments");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "comments", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllComments() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: comments");
	    Enumeration objects = comments().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromCommentsAndDelete((net.events.cms.eo.BlogComment)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "viewHistoryEntries"
	 */
    public NSArray viewHistoryEntries() {
        return (NSArray)storedValueForKey("viewHistoryEntries");
    }

    public void setViewHistoryEntries(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating viewHistoryEntries from "+viewHistoryEntries()+" to "+aValue );
        takeStoredValueForKey(aValue, "viewHistoryEntries");
    }

    public void addToViewHistoryEntries(net.events.cms.eo.BlogEntryViewHistory object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to viewHistoryEntries" );
	    includeObjectIntoPropertyWithKey(object, "viewHistoryEntries");
    }
    

    public void removeFromViewHistoryEntries(net.events.cms.eo.BlogEntryViewHistory object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from viewHistoryEntries" );
	    excludeObjectFromPropertyWithKey(object, "viewHistoryEntries");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.BlogEntryViewHistory" and add it
     * to the relationship "viewHistoryEntries"
     */
    public net.events.cms.eo.BlogEntryViewHistory createObjectAndAddToViewHistoryEntries() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: viewHistoryEntries");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("BlogEntryViewHistory");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "viewHistoryEntries");
	    return (net.events.cms.eo.BlogEntryViewHistory) eoObject;
    }
    
    /**
     * Removes object from the relationship "viewHistoryEntries" and delete object
     */
    public void removeFromViewHistoryEntriesAndDelete(net.events.cms.eo.BlogEntryViewHistory object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: viewHistoryEntries");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "viewHistoryEntries");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "viewHistoryEntries", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllViewHistoryEntries() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: viewHistoryEntries");
	    Enumeration objects = viewHistoryEntries().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromViewHistoryEntriesAndDelete((net.events.cms.eo.BlogEntryViewHistory)objects.nextElement());
    }
}