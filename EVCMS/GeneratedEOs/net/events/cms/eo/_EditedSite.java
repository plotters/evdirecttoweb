// _EditedSite.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to EditedSite.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _EditedSite extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _EditedSite.class );
	
	// KeyValueCoding support
	
    public static final String SITE = "site";
    public static final String EVENTSUSER = "eventsUser";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "EditedSite";

	/**
	 * Standard constructor
	 */	
    public _EditedSite() {
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
	 * Create a "EditedSite" object with all required values
	 */
	public static EditedSite createEditedSite(EOEditingContext editingContext, net.events.cms.eo.Client client, net.events.cms.eo.EventsUser eventsUser, net.events.cms.eo.Site site) {
		if (log.isDebugEnabled()) log.debug ("Creating object: EditedSite");
		EditedSite eoObject = (EditedSite)EOUtilities.createAndInsertInstance(editingContext, _EditedSite.ENTITY_NAME);
		eoObject.setClient(client);
		eoObject.setEventsUser(eventsUser);
		eoObject.setSite(site);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "EditedSite" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EditedSite'");
		return _EditedSite.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "EditedSite" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EditedSite' with sortOrderings " + _sortOrderings);
		return _EditedSite.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "EditedSite" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EditedSite.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "EditedSite" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EditedSite' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_EditedSite.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static EditedSite fetchEditedSiteWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EditedSite.fetchEditedSiteWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static EditedSite fetchEditedSiteWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'EditedSite' with qualifier: " + _qualifier);
		NSArray eoObjects = _EditedSite.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		EditedSite eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (EditedSite)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one EditedSite that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EditedSite fetchRequiredEditedSiteWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EditedSite.fetchRequiredEditedSiteWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EditedSite fetchRequiredEditedSiteWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'EditedSite' with qualifier: " + _qualifier);
		EditedSite eoObject = _EditedSite.fetchEditedSiteWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no EditedSite that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public EditedSite localInstanceOfEditedSite(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EditedSite': " + this.toString());
		return (EditedSite)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static EditedSite localInstanceOfEditedSite(EOEditingContext _editingContext, EditedSite _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EditedSite': " + _eo.toString());
		}
		return (_eo == null) ? null : (EditedSite)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.EventsUser eventsUser() {
        return (net.events.cms.eo.EventsUser)storedValueForKey("eventsUser");
    }

    public void setEventsUser(net.events.cms.eo.EventsUser aValue) {
        takeStoredValueForKey(aValue, "eventsUser");
    }

    public net.events.cms.eo.Site site() {
        return (net.events.cms.eo.Site)storedValueForKey("site");
    }

    public void setSite(net.events.cms.eo.Site aValue) {
        takeStoredValueForKey(aValue, "site");
    }
}