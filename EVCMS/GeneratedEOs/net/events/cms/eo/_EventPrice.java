// _EventPrice.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to EventPrice.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _EventPrice extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _EventPrice.class );
	
	// KeyValueCoding support
	
    public static final String PRICE = "price";
    public static final String LABEL = "label";
    public static final String EVENT = "event";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "EventPrice";

	/**
	 * Standard constructor
	 */	
    public _EventPrice() {
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
	 * Create a "EventPrice" object with all required values
	 */
	public static EventPrice createEventPrice(EOEditingContext editingContext, NSTimestamp creationTime, String label, BigDecimal price, net.events.cms.eo.Client client, net.events.cms.eo.EventsUser createdBy, net.events.cms.eo.Event event) {
		if (log.isDebugEnabled()) log.debug ("Creating object: EventPrice");
		EventPrice eoObject = (EventPrice)EOUtilities.createAndInsertInstance(editingContext, _EventPrice.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setLabel(label);
		eoObject.setPrice(price);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		eoObject.setEvent(event);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "EventPrice" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EventPrice'");
		return _EventPrice.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "EventPrice" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EventPrice' with sortOrderings " + _sortOrderings);
		return _EventPrice.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "EventPrice" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EventPrice.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "EventPrice" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EventPrice' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_EventPrice.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static EventPrice fetchEventPriceWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EventPrice.fetchEventPriceWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static EventPrice fetchEventPriceWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'EventPrice' with qualifier: " + _qualifier);
		NSArray eoObjects = _EventPrice.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		EventPrice eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (EventPrice)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one EventPrice that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EventPrice fetchRequiredEventPriceWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EventPrice.fetchRequiredEventPriceWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EventPrice fetchRequiredEventPriceWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'EventPrice' with qualifier: " + _qualifier);
		EventPrice eoObject = _EventPrice.fetchEventPriceWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no EventPrice that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public EventPrice localInstanceOfEventPrice(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EventPrice': " + this.toString());
		return (EventPrice)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static EventPrice localInstanceOfEventPrice(EOEditingContext _editingContext, EventPrice _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EventPrice': " + _eo.toString());
		}
		return (_eo == null) ? null : (EventPrice)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "label"
	 */
    public String label() {
        return (String) storedValueForKey("label");
    }

	/**
	 * Set the value for "label"
	 */
    public void setLabel(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating label from "+label()+" to "+aValue );
        takeStoredValueForKey(aValue, "label");
    }

	/**
	 * The value for "price"
	 */
    public BigDecimal price() {
        return (BigDecimal) storedValueForKey("price");
    }

	/**
	 * Set the value for "price"
	 */
    public void setPrice(BigDecimal aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating price from "+price()+" to "+aValue );
        takeStoredValueForKey(aValue, "price");
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

    public net.events.cms.eo.Event event() {
        return (net.events.cms.eo.Event)storedValueForKey("event");
    }

    public void setEvent(net.events.cms.eo.Event aValue) {
        takeStoredValueForKey(aValue, "event");
    }
}