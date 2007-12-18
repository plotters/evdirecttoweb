// _MeetingMinutesEntry.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to MeetingMinutesEntry.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _MeetingMinutesEntry extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _MeetingMinutesEntry.class );
	
	// KeyValueCoding support
	
    public static final String TYPE = "type";
    public static final String STATUS = "status";
    public static final String ORIGINALDUEDATE = "originalDueDate";
    public static final String MEETINGMINUTES = "meetingMinutes";
    public static final String MEETING = "meeting";
    public static final String ENTRYID = "entryId";
    public static final String ENTRY = "entry";
    public static final String DUEDATE = "dueDate";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    public static final String ASSIGNEDTO = "assignedTo";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "MeetingMinutesEntry";

	/**
	 * Standard constructor
	 */	
    public _MeetingMinutesEntry() {
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
	 * Create a "MeetingMinutesEntry" object with all required values
	 */
	public static MeetingMinutesEntry createMeetingMinutesEntry(EOEditingContext editingContext, NSTimestamp creationTime, String entry, Number entryId, net.events.cms.eo.Client client, net.events.cms.eo.EventsUser createdBy, net.events.cms.eo.Meeting meeting, net.events.cms.eo.MeetingMinutes meetingMinutes, net.events.cms.eo.EntryStatus status, net.events.cms.eo.EntryType type) {
		if (log.isDebugEnabled()) log.debug ("Creating object: MeetingMinutesEntry");
		MeetingMinutesEntry eoObject = (MeetingMinutesEntry)EOUtilities.createAndInsertInstance(editingContext, _MeetingMinutesEntry.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setEntry(entry);
		eoObject.setEntryId(entryId);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		eoObject.setMeeting(meeting);
		eoObject.setMeetingMinutes(meetingMinutes);
		eoObject.setStatus(status);
		eoObject.setType(type);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "MeetingMinutesEntry" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'MeetingMinutesEntry'");
		return _MeetingMinutesEntry.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "MeetingMinutesEntry" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'MeetingMinutesEntry' with sortOrderings " + _sortOrderings);
		return _MeetingMinutesEntry.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "MeetingMinutesEntry" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _MeetingMinutesEntry.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "MeetingMinutesEntry" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'MeetingMinutesEntry' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_MeetingMinutesEntry.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static MeetingMinutesEntry fetchMeetingMinutesEntryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _MeetingMinutesEntry.fetchMeetingMinutesEntryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static MeetingMinutesEntry fetchMeetingMinutesEntryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'MeetingMinutesEntry' with qualifier: " + _qualifier);
		NSArray eoObjects = _MeetingMinutesEntry.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		MeetingMinutesEntry eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (MeetingMinutesEntry)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one MeetingMinutesEntry that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static MeetingMinutesEntry fetchRequiredMeetingMinutesEntryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _MeetingMinutesEntry.fetchRequiredMeetingMinutesEntryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static MeetingMinutesEntry fetchRequiredMeetingMinutesEntryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'MeetingMinutesEntry' with qualifier: " + _qualifier);
		MeetingMinutesEntry eoObject = _MeetingMinutesEntry.fetchMeetingMinutesEntryWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no MeetingMinutesEntry that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public MeetingMinutesEntry localInstanceOfMeetingMinutesEntry(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'MeetingMinutesEntry': " + this.toString());
		return (MeetingMinutesEntry)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static MeetingMinutesEntry localInstanceOfMeetingMinutesEntry(EOEditingContext _editingContext, MeetingMinutesEntry _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'MeetingMinutesEntry': " + _eo.toString());
		}
		return (_eo == null) ? null : (MeetingMinutesEntry)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "assignedTo"
	 */
    public String assignedTo() {
        return (String) storedValueForKey("assignedTo");
    }

	/**
	 * Set the value for "assignedTo"
	 */
    public void setAssignedTo(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating assignedTo from "+assignedTo()+" to "+aValue );
        takeStoredValueForKey(aValue, "assignedTo");
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
	 * The value for "dueDate"
	 */
    public NSTimestamp dueDate() {
        return (NSTimestamp) storedValueForKey("dueDate");
    }

	/**
	 * Set the value for "dueDate"
	 */
    public void setDueDate(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating dueDate from "+dueDate()+" to "+aValue );
        takeStoredValueForKey(aValue, "dueDate");
    }

	/**
	 * The value for "entry"
	 */
    public String entry() {
        return (String) storedValueForKey("entry");
    }

	/**
	 * Set the value for "entry"
	 */
    public void setEntry(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating entry from "+entry()+" to "+aValue );
        takeStoredValueForKey(aValue, "entry");
    }

	/**
	 * The value for "entryId"
	 */
    public Number entryId() {
        return (Number) storedValueForKey("entryId");
    }

	/**
	 * Set the value for "entryId"
	 */
    public void setEntryId(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating entryId from "+entryId()+" to "+aValue );
        takeStoredValueForKey(aValue, "entryId");
    }

	/**
	 * The value for "originalDueDate"
	 */
    public NSTimestamp originalDueDate() {
        return (NSTimestamp) storedValueForKey("originalDueDate");
    }

	/**
	 * Set the value for "originalDueDate"
	 */
    public void setOriginalDueDate(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating originalDueDate from "+originalDueDate()+" to "+aValue );
        takeStoredValueForKey(aValue, "originalDueDate");
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

    public net.events.cms.eo.Meeting meeting() {
        return (net.events.cms.eo.Meeting)storedValueForKey("meeting");
    }

    public void setMeeting(net.events.cms.eo.Meeting aValue) {
        takeStoredValueForKey(aValue, "meeting");
    }

    public net.events.cms.eo.MeetingMinutes meetingMinutes() {
        return (net.events.cms.eo.MeetingMinutes)storedValueForKey("meetingMinutes");
    }

    public void setMeetingMinutes(net.events.cms.eo.MeetingMinutes aValue) {
        takeStoredValueForKey(aValue, "meetingMinutes");
    }

    public net.events.cms.eo.EntryStatus status() {
        return (net.events.cms.eo.EntryStatus)storedValueForKey("status");
    }

    public void setStatus(net.events.cms.eo.EntryStatus aValue) {
        takeStoredValueForKey(aValue, "status");
    }

    public net.events.cms.eo.EntryType type() {
        return (net.events.cms.eo.EntryType)storedValueForKey("type");
    }

    public void setType(net.events.cms.eo.EntryType aValue) {
        takeStoredValueForKey(aValue, "type");
    }
}