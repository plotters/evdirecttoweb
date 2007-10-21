// _BlogComment.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to BlogComment.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _BlogComment extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _BlogComment.class );
	
	// KeyValueCoding support
	
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String CREATIONTIME = "creationTime";
    public static final String COMMENT = "comment";
    public static final String CLIENT = "client";
    public static final String BLOGENTRY = "blogEntry";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "BlogComment";

	/**
	 * Standard constructor
	 */	
    public _BlogComment() {
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
	 * Create a "BlogComment" object with all required values
	 */
	public static BlogComment createBlogComment(EOEditingContext editingContext, String comment, NSTimestamp creationTime, String email, String name, net.events.cms.eo.BlogEntry blogEntry, net.events.cms.eo.Client client) {
		if (log.isDebugEnabled()) log.debug ("Creating object: BlogComment");
		BlogComment eoObject = (BlogComment)EOUtilities.createAndInsertInstance(editingContext, _BlogComment.ENTITY_NAME);
		eoObject.setComment(comment);
		eoObject.setCreationTime(creationTime);
		eoObject.setEmail(email);
		eoObject.setName(name);
		eoObject.setBlogEntry(blogEntry);
		eoObject.setClient(client);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "BlogComment" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogComment'");
		return _BlogComment.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "BlogComment" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogComment' with sortOrderings " + _sortOrderings);
		return _BlogComment.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "BlogComment" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogComment.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "BlogComment" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogComment' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_BlogComment.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static BlogComment fetchBlogCommentWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogComment.fetchBlogCommentWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static BlogComment fetchBlogCommentWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'BlogComment' with qualifier: " + _qualifier);
		NSArray eoObjects = _BlogComment.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		BlogComment eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (BlogComment)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one BlogComment that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static BlogComment fetchRequiredBlogCommentWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogComment.fetchRequiredBlogCommentWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static BlogComment fetchRequiredBlogCommentWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'BlogComment' with qualifier: " + _qualifier);
		BlogComment eoObject = _BlogComment.fetchBlogCommentWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no BlogComment that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public BlogComment localInstanceOfBlogComment(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'BlogComment': " + this.toString());
		return (BlogComment)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static BlogComment localInstanceOfBlogComment(EOEditingContext _editingContext, BlogComment _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'BlogComment': " + _eo.toString());
		}
		return (_eo == null) ? null : (BlogComment)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "comment"
	 */
    public String comment() {
        return (String) storedValueForKey("comment");
    }

	/**
	 * Set the value for "comment"
	 */
    public void setComment(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating comment from "+comment()+" to "+aValue );
        takeStoredValueForKey(aValue, "comment");
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
	 * The value for "email"
	 */
    public String email() {
        return (String) storedValueForKey("email");
    }

	/**
	 * Set the value for "email"
	 */
    public void setEmail(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating email from "+email()+" to "+aValue );
        takeStoredValueForKey(aValue, "email");
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

    public net.events.cms.eo.BlogEntry blogEntry() {
        return (net.events.cms.eo.BlogEntry)storedValueForKey("blogEntry");
    }

    public void setBlogEntry(net.events.cms.eo.BlogEntry aValue) {
        takeStoredValueForKey(aValue, "blogEntry");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }
}