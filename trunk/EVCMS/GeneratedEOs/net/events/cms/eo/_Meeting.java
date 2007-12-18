// _Meeting.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Meeting.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Meeting extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Meeting.class );
	
	// KeyValueCoding support
	
    public static final String MEETINGMINUTES = "meetingMinutes";
    public static final String LOCATION = "location";
    public static final String ENTRIES = "entries";
    public static final String DATEANDTIME = "dateAndTime";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Meeting";

	/**
	 * Standard constructor
	 */	
    public _Meeting() {
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
	 * Create a "Meeting" object with all required values
	 */
	public static Meeting createMeeting(EOEditingContext editingContext, NSTimestamp creationTime, NSTimestamp dateAndTime, String location, net.events.cms.eo.Client client, net.events.cms.eo.EventsUser createdBy, net.events.cms.eo.MeetingMinutes meetingMinutes) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Meeting");
		Meeting eoObject = (Meeting)EOUtilities.createAndInsertInstance(editingContext, _Meeting.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setDateAndTime(dateAndTime);
		eoObject.setLocation(location);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		eoObject.setMeetingMinutes(meetingMinutes);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Meeting" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Meeting'");
		return _Meeting.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Meeting" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Meeting' with sortOrderings " + _sortOrderings);
		return _Meeting.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Meeting" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Meeting.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Meeting" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Meeting' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Meeting.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Meeting fetchMeetingWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Meeting.fetchMeetingWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Meeting fetchMeetingWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Meeting' with qualifier: " + _qualifier);
		NSArray eoObjects = _Meeting.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Meeting eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Meeting)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Meeting that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Meeting fetchRequiredMeetingWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Meeting.fetchRequiredMeetingWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Meeting fetchRequiredMeetingWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Meeting' with qualifier: " + _qualifier);
		Meeting eoObject = _Meeting.fetchMeetingWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Meeting that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Meeting localInstanceOfMeeting(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Meeting': " + this.toString());
		return (Meeting)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Meeting localInstanceOfMeeting(EOEditingContext _editingContext, Meeting _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Meeting': " + _eo.toString());
		}
		return (_eo == null) ? null : (Meeting)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "dateAndTime"
	 */
    public NSTimestamp dateAndTime() {
        return (NSTimestamp) storedValueForKey("dateAndTime");
    }

	/**
	 * Set the value for "dateAndTime"
	 */
    public void setDateAndTime(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating dateAndTime from "+dateAndTime()+" to "+aValue );
        takeStoredValueForKey(aValue, "dateAndTime");
    }

	/**
	 * The value for "location"
	 */
    public String location() {
        return (String) storedValueForKey("location");
    }

	/**
	 * Set the value for "location"
	 */
    public void setLocation(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating location from "+location()+" to "+aValue );
        takeStoredValueForKey(aValue, "location");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.EventsUser createdBy() {
        return (net.events.cms.eo.EventsUser)storedValueForKey("createdBy");
    }

    public void setCreatedBy(net.events.cms.eo.EventsUser aValue) {
        takeStoredValueForKey(aValue, "createdBy");
    }

    public net.events.cms.eo.MeetingMinutes meetingMinutes() {
        return (net.events.cms.eo.MeetingMinutes)storedValueForKey("meetingMinutes");
    }

    public void setMeetingMinutes(net.events.cms.eo.MeetingMinutes aValue) {
        takeStoredValueForKey(aValue, "meetingMinutes");
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

    public void addToEntries(net.events.cms.eo.MeetingMinutesEntry object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to entries" );
	    includeObjectIntoPropertyWithKey(object, "entries");
    }
    

    public void removeFromEntries(net.events.cms.eo.MeetingMinutesEntry object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from entries" );
	    excludeObjectFromPropertyWithKey(object, "entries");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.MeetingMinutesEntry" and add it
     * to the relationship "entries"
     */
    public net.events.cms.eo.MeetingMinutesEntry createObjectAndAddToEntries() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: entries");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("MeetingMinutesEntry");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "entries");
	    return (net.events.cms.eo.MeetingMinutesEntry) eoObject;
    }
    
    /**
     * Removes object from the relationship "entries" and delete object
     */
    public void removeFromEntriesAndDelete(net.events.cms.eo.MeetingMinutesEntry object) {
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
	        removeFromEntriesAndDelete((net.events.cms.eo.MeetingMinutesEntry)objects.nextElement());
    }
}