// _BlogEntryViewHistory.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to BlogEntryViewHistory.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _BlogEntryViewHistory extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _BlogEntryViewHistory.class );
	
	// KeyValueCoding support
	
    public static final String USERAGENT = "userAgent";
    public static final String TYPE = "type";
    public static final String REQUESTURI = "requestUri";
    public static final String REMOTEADDRESS = "remoteAddress";
    public static final String CREATIONTIME = "creationTime";
    public static final String BLOGENTRY = "blogEntry";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "BlogEntryViewHistory";

	/**
	 * Standard constructor
	 */	
    public _BlogEntryViewHistory() {
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
	 * Create a "BlogEntryViewHistory" object with all required values
	 */
	public static BlogEntryViewHistory createBlogEntryViewHistory(EOEditingContext editingContext, NSTimestamp creationTime, net.events.cms.eo.BlogEntry blogEntry) {
		if (log.isDebugEnabled()) log.debug ("Creating object: BlogEntryViewHistory");
		BlogEntryViewHistory eoObject = (BlogEntryViewHistory)EOUtilities.createAndInsertInstance(editingContext, _BlogEntryViewHistory.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setBlogEntry(blogEntry);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "BlogEntryViewHistory" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogEntryViewHistory'");
		return _BlogEntryViewHistory.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "BlogEntryViewHistory" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogEntryViewHistory' with sortOrderings " + _sortOrderings);
		return _BlogEntryViewHistory.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "BlogEntryViewHistory" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogEntryViewHistory.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "BlogEntryViewHistory" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogEntryViewHistory' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_BlogEntryViewHistory.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static BlogEntryViewHistory fetchBlogEntryViewHistoryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogEntryViewHistory.fetchBlogEntryViewHistoryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static BlogEntryViewHistory fetchBlogEntryViewHistoryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'BlogEntryViewHistory' with qualifier: " + _qualifier);
		NSArray eoObjects = _BlogEntryViewHistory.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		BlogEntryViewHistory eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (BlogEntryViewHistory)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one BlogEntryViewHistory that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static BlogEntryViewHistory fetchRequiredBlogEntryViewHistoryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogEntryViewHistory.fetchRequiredBlogEntryViewHistoryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static BlogEntryViewHistory fetchRequiredBlogEntryViewHistoryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'BlogEntryViewHistory' with qualifier: " + _qualifier);
		BlogEntryViewHistory eoObject = _BlogEntryViewHistory.fetchBlogEntryViewHistoryWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no BlogEntryViewHistory that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public BlogEntryViewHistory localInstanceOfBlogEntryViewHistory(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'BlogEntryViewHistory': " + this.toString());
		return (BlogEntryViewHistory)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static BlogEntryViewHistory localInstanceOfBlogEntryViewHistory(EOEditingContext _editingContext, BlogEntryViewHistory _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'BlogEntryViewHistory': " + _eo.toString());
		}
		return (_eo == null) ? null : (BlogEntryViewHistory)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "remoteAddress"
	 */
    public String remoteAddress() {
        return (String) storedValueForKey("remoteAddress");
    }

	/**
	 * Set the value for "remoteAddress"
	 */
    public void setRemoteAddress(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating remoteAddress from "+remoteAddress()+" to "+aValue );
        takeStoredValueForKey(aValue, "remoteAddress");
    }

	/**
	 * The value for "requestUri"
	 */
    public String requestUri() {
        return (String) storedValueForKey("requestUri");
    }

	/**
	 * Set the value for "requestUri"
	 */
    public void setRequestUri(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating requestUri from "+requestUri()+" to "+aValue );
        takeStoredValueForKey(aValue, "requestUri");
    }

	/**
	 * The value for "type"
	 */
    public String type() {
        return (String) storedValueForKey("type");
    }

	/**
	 * Set the value for "type"
	 */
    public void setType(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating type from "+type()+" to "+aValue );
        takeStoredValueForKey(aValue, "type");
    }

	/**
	 * The value for "userAgent"
	 */
    public String userAgent() {
        return (String) storedValueForKey("userAgent");
    }

	/**
	 * Set the value for "userAgent"
	 */
    public void setUserAgent(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating userAgent from "+userAgent()+" to "+aValue );
        takeStoredValueForKey(aValue, "userAgent");
    }

    public net.events.cms.eo.BlogEntry blogEntry() {
        return (net.events.cms.eo.BlogEntry)storedValueForKey("blogEntry");
    }

    public void setBlogEntry(net.events.cms.eo.BlogEntry aValue) {
        takeStoredValueForKey(aValue, "blogEntry");
    }
}