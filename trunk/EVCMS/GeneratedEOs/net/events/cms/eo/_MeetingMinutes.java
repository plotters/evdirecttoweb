// _MeetingMinutes.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to MeetingMinutes.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _MeetingMinutes extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _MeetingMinutes.class );
	
	// KeyValueCoding support
	
    public static final String SHORTDESCRIPTION = "shortDescription";
    public static final String NAME = "name";
    public static final String MEETINGS = "meetings";
    public static final String ENTRIES = "entries";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "MeetingMinutes";

	/**
	 * Standard constructor
	 */	
    public _MeetingMinutes() {
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
	 * Create a "MeetingMinutes" object with all required values
	 */
	public static MeetingMinutes createMeetingMinutes(EOEditingContext editingContext, NSTimestamp creationTime, String name, String shortDescription, net.events.cms.eo.Client client, net.events.cms.eo.EventsUser createdBy) {
		if (log.isDebugEnabled()) log.debug ("Creating object: MeetingMinutes");
		MeetingMinutes eoObject = (MeetingMinutes)EOUtilities.createAndInsertInstance(editingContext, _MeetingMinutes.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setName(name);
		eoObject.setShortDescription(shortDescription);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "MeetingMinutes" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'MeetingMinutes'");
		return _MeetingMinutes.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "MeetingMinutes" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'MeetingMinutes' with sortOrderings " + _sortOrderings);
		return _MeetingMinutes.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "MeetingMinutes" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _MeetingMinutes.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "MeetingMinutes" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'MeetingMinutes' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_MeetingMinutes.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static MeetingMinutes fetchMeetingMinutesWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _MeetingMinutes.fetchMeetingMinutesWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static MeetingMinutes fetchMeetingMinutesWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'MeetingMinutes' with qualifier: " + _qualifier);
		NSArray eoObjects = _MeetingMinutes.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		MeetingMinutes eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (MeetingMinutes)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one MeetingMinutes that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static MeetingMinutes fetchRequiredMeetingMinutesWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _MeetingMinutes.fetchRequiredMeetingMinutesWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static MeetingMinutes fetchRequiredMeetingMinutesWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'MeetingMinutes' with qualifier: " + _qualifier);
		MeetingMinutes eoObject = _MeetingMinutes.fetchMeetingMinutesWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no MeetingMinutes that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public MeetingMinutes localInstanceOfMeetingMinutes(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'MeetingMinutes': " + this.toString());
		return (MeetingMinutes)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static MeetingMinutes localInstanceOfMeetingMinutes(EOEditingContext _editingContext, MeetingMinutes _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'MeetingMinutes': " + _eo.toString());
		}
		return (_eo == null) ? null : (MeetingMinutes)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "shortDescription"
	 */
    public String shortDescription() {
        return (String) storedValueForKey("shortDescription");
    }

	/**
	 * Set the value for "shortDescription"
	 */
    public void setShortDescription(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating shortDescription from "+shortDescription()+" to "+aValue );
        takeStoredValueForKey(aValue, "shortDescription");
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

	/**
	 * Returns the objects for the relationship "meetings"
	 */
    public NSArray meetings() {
        return (NSArray)storedValueForKey("meetings");
    }

    public void setMeetings(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating meetings from "+meetings()+" to "+aValue );
        takeStoredValueForKey(aValue, "meetings");
    }

    public void addToMeetings(net.events.cms.eo.Meeting object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to meetings" );
	    includeObjectIntoPropertyWithKey(object, "meetings");
    }
    

    public void removeFromMeetings(net.events.cms.eo.Meeting object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from meetings" );
	    excludeObjectFromPropertyWithKey(object, "meetings");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Meeting" and add it
     * to the relationship "meetings"
     */
    public net.events.cms.eo.Meeting createObjectAndAddToMeetings() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: meetings");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Meeting");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "meetings");
	    return (net.events.cms.eo.Meeting) eoObject;
    }
    
    /**
     * Removes object from the relationship "meetings" and delete object
     */
    public void removeFromMeetingsAndDelete(net.events.cms.eo.Meeting object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: meetings");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "meetings");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "meetings", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllMeetings() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: meetings");
	    Enumeration objects = meetings().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromMeetingsAndDelete((net.events.cms.eo.Meeting)objects.nextElement());
    }
}