// _SessionTrack.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to SessionTrack.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _SessionTrack extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _SessionTrack.class );
	
	// KeyValueCoding support
	
    public static final String USER = "user";
    public static final String SESSIONID = "sessionId";
    public static final String NAVIGATIONSTATE = "navigationState";
    public static final String LOGGED_OUT = "logged_out";
    public static final String LASTACTIONTIME = "lastActionTime";
    public static final String INSTANCENAME = "instanceName";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "SessionTrack";

	/**
	 * Standard constructor
	 */	
    public _SessionTrack() {
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
	 * Create a "SessionTrack" object with all required values
	 */
	public static SessionTrack createSessionTrack(EOEditingContext editingContext, String instanceName, NSTimestamp lastActionTime, Boolean logged_out, String sessionId, net.events.cms.eo.Person user) {
		if (log.isDebugEnabled()) log.debug ("Creating object: SessionTrack");
		SessionTrack eoObject = (SessionTrack)EOUtilities.createAndInsertInstance(editingContext, _SessionTrack.ENTITY_NAME);
		eoObject.setInstanceName(instanceName);
		eoObject.setLastActionTime(lastActionTime);
		eoObject.setLogged_out(logged_out);
		eoObject.setSessionId(sessionId);
		eoObject.setUser(user);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "SessionTrack" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'SessionTrack'");
		return _SessionTrack.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "SessionTrack" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'SessionTrack' with sortOrderings " + _sortOrderings);
		return _SessionTrack.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "SessionTrack" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _SessionTrack.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "SessionTrack" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'SessionTrack' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_SessionTrack.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static SessionTrack fetchSessionTrackWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _SessionTrack.fetchSessionTrackWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static SessionTrack fetchSessionTrackWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'SessionTrack' with qualifier: " + _qualifier);
		NSArray eoObjects = _SessionTrack.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		SessionTrack eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (SessionTrack)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one SessionTrack that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static SessionTrack fetchRequiredSessionTrackWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _SessionTrack.fetchRequiredSessionTrackWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static SessionTrack fetchRequiredSessionTrackWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'SessionTrack' with qualifier: " + _qualifier);
		SessionTrack eoObject = _SessionTrack.fetchSessionTrackWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no SessionTrack that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public SessionTrack localInstanceOfSessionTrack(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'SessionTrack': " + this.toString());
		return (SessionTrack)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static SessionTrack localInstanceOfSessionTrack(EOEditingContext _editingContext, SessionTrack _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'SessionTrack': " + _eo.toString());
		}
		return (_eo == null) ? null : (SessionTrack)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "instanceName"
	 */
    public String instanceName() {
        return (String) storedValueForKey("instanceName");
    }

	/**
	 * Set the value for "instanceName"
	 */
    public void setInstanceName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating instanceName from "+instanceName()+" to "+aValue );
        takeStoredValueForKey(aValue, "instanceName");
    }

	/**
	 * The value for "lastActionTime"
	 */
    public NSTimestamp lastActionTime() {
        return (NSTimestamp) storedValueForKey("lastActionTime");
    }

	/**
	 * Set the value for "lastActionTime"
	 */
    public void setLastActionTime(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating lastActionTime from "+lastActionTime()+" to "+aValue );
        takeStoredValueForKey(aValue, "lastActionTime");
    }

	/**
	 * The value for "logged_out"
	 */
    public Boolean logged_out() {
        return (Boolean) storedValueForKey("logged_out");
    }

	/**
	 * Set the value for "logged_out"
	 */
    public void setLogged_out(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating logged_out from "+logged_out()+" to "+aValue );
        takeStoredValueForKey(aValue, "logged_out");
    }

	/**
	 * The value for "navigationState"
	 */
    public String navigationState() {
        return (String) storedValueForKey("navigationState");
    }

	/**
	 * Set the value for "navigationState"
	 */
    public void setNavigationState(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating navigationState from "+navigationState()+" to "+aValue );
        takeStoredValueForKey(aValue, "navigationState");
    }

	/**
	 * The value for "sessionId"
	 */
    public String sessionId() {
        return (String) storedValueForKey("sessionId");
    }

	/**
	 * Set the value for "sessionId"
	 */
    public void setSessionId(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating sessionId from "+sessionId()+" to "+aValue );
        takeStoredValueForKey(aValue, "sessionId");
    }

    public net.events.cms.eo.Person user() {
        return (net.events.cms.eo.Person)storedValueForKey("user");
    }

    public void setUser(net.events.cms.eo.Person aValue) {
        takeStoredValueForKey(aValue, "user");
    }
}