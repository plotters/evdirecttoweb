// _Event.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Event.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Event extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Event.class );
	
	// KeyValueCoding support
	
    public static final String PRICES = "prices";
    public static final String PARENTEVENT = "parentEvent";
    public static final String NAME = "name";
    public static final String LOCATION = "location";
    public static final String ISWHOLEDAYEVENT = "isWholeDayEvent";
    public static final String EVENTS = "events";
    public static final String EVENTSTART = "eventStart";
    public static final String EVENTEND = "eventEnd";
    public static final String EVENTDESCRIPTION = "eventDescription";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    public static final String CITY = "city";
    public static final String CATEGORY = "category";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Event";

	/**
	 * Standard constructor
	 */	
    public _Event() {
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
	 * Create a "Event" object with all required values
	 */
	public static Event createEvent(EOEditingContext editingContext, NSTimestamp eventStart, String location, String name, net.events.cms.eo.EventCategory category, net.events.cms.eo.City city, net.events.cms.eo.Client client, net.events.cms.eo.EventsUser createdBy) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Event");
		Event eoObject = (Event)EOUtilities.createAndInsertInstance(editingContext, _Event.ENTITY_NAME);
		eoObject.setEventStart(eventStart);
		eoObject.setLocation(location);
		eoObject.setName(name);
		eoObject.setCategory(category);
		eoObject.setCity(city);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Event" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Event'");
		return _Event.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Event" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Event' with sortOrderings " + _sortOrderings);
		return _Event.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Event" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Event.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Event" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Event' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Event.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Event fetchEventWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Event.fetchEventWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Event fetchEventWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Event' with qualifier: " + _qualifier);
		NSArray eoObjects = _Event.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Event eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Event)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Event that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Event fetchRequiredEventWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Event.fetchRequiredEventWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Event fetchRequiredEventWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Event' with qualifier: " + _qualifier);
		Event eoObject = _Event.fetchEventWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Event that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Event localInstanceOfEvent(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Event': " + this.toString());
		return (Event)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Event localInstanceOfEvent(EOEditingContext _editingContext, Event _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Event': " + _eo.toString());
		}
		return (_eo == null) ? null : (Event)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "eventDescription"
	 */
    public String eventDescription() {
        return (String) storedValueForKey("eventDescription");
    }

	/**
	 * Set the value for "eventDescription"
	 */
    public void setEventDescription(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating eventDescription from "+eventDescription()+" to "+aValue );
        takeStoredValueForKey(aValue, "eventDescription");
    }

	/**
	 * The value for "eventEnd"
	 */
    public NSTimestamp eventEnd() {
        return (NSTimestamp) storedValueForKey("eventEnd");
    }

	/**
	 * Set the value for "eventEnd"
	 */
    public void setEventEnd(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating eventEnd from "+eventEnd()+" to "+aValue );
        takeStoredValueForKey(aValue, "eventEnd");
    }

	/**
	 * The value for "eventStart"
	 */
    public NSTimestamp eventStart() {
        return (NSTimestamp) storedValueForKey("eventStart");
    }

	/**
	 * Set the value for "eventStart"
	 */
    public void setEventStart(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating eventStart from "+eventStart()+" to "+aValue );
        takeStoredValueForKey(aValue, "eventStart");
    }

	/**
	 * The value for "isWholeDayEvent"
	 */
    public Boolean isWholeDayEvent() {
        return (Boolean) storedValueForKey("isWholeDayEvent");
    }

	/**
	 * Set the value for "isWholeDayEvent"
	 */
    public void setIsWholeDayEvent(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating isWholeDayEvent from "+isWholeDayEvent()+" to "+aValue );
        takeStoredValueForKey(aValue, "isWholeDayEvent");
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

    public net.events.cms.eo.EventCategory category() {
        return (net.events.cms.eo.EventCategory)storedValueForKey("category");
    }

    public void setCategory(net.events.cms.eo.EventCategory aValue) {
        takeStoredValueForKey(aValue, "category");
    }

    public net.events.cms.eo.City city() {
        return (net.events.cms.eo.City)storedValueForKey("city");
    }

    public void setCity(net.events.cms.eo.City aValue) {
        takeStoredValueForKey(aValue, "city");
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

    public net.events.cms.eo.Event parentEvent() {
        return (net.events.cms.eo.Event)storedValueForKey("parentEvent");
    }

    public void setParentEvent(net.events.cms.eo.Event aValue) {
        takeStoredValueForKey(aValue, "parentEvent");
    }

	/**
	 * Returns the objects for the relationship "events"
	 */
    public NSArray events() {
        return (NSArray)storedValueForKey("events");
    }

    public void setEvents(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating events from "+events()+" to "+aValue );
        takeStoredValueForKey(aValue, "events");
    }

    public void addToEvents(net.events.cms.eo.Event object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to events" );
	    includeObjectIntoPropertyWithKey(object, "events");
    }
    

    public void removeFromEvents(net.events.cms.eo.Event object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from events" );
	    excludeObjectFromPropertyWithKey(object, "events");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Event" and add it
     * to the relationship "events"
     */
    public net.events.cms.eo.Event createObjectAndAddToEvents() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: events");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Event");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "events");
	    return (net.events.cms.eo.Event) eoObject;
    }
    
    /**
     * Removes object from the relationship "events" and delete object
     */
    public void removeFromEventsAndDelete(net.events.cms.eo.Event object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: events");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "events");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "events", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllEvents() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: events");
	    Enumeration objects = events().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromEventsAndDelete((net.events.cms.eo.Event)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "prices"
	 */
    public NSArray prices() {
        return (NSArray)storedValueForKey("prices");
    }

    public void setPrices(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating prices from "+prices()+" to "+aValue );
        takeStoredValueForKey(aValue, "prices");
    }

    public void addToPrices(net.events.cms.eo.EventPrice object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to prices" );
	    includeObjectIntoPropertyWithKey(object, "prices");
    }
    

    public void removeFromPrices(net.events.cms.eo.EventPrice object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from prices" );
	    excludeObjectFromPropertyWithKey(object, "prices");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.EventPrice" and add it
     * to the relationship "prices"
     */
    public net.events.cms.eo.EventPrice createObjectAndAddToPrices() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: prices");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("EventPrice");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "prices");
	    return (net.events.cms.eo.EventPrice) eoObject;
    }
    
    /**
     * Removes object from the relationship "prices" and delete object
     */
    public void removeFromPricesAndDelete(net.events.cms.eo.EventPrice object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: prices");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "prices");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "prices", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllPrices() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: prices");
	    Enumeration objects = prices().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromPricesAndDelete((net.events.cms.eo.EventPrice)objects.nextElement());
    }
}