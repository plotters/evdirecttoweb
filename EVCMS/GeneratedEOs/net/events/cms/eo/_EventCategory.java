// _EventCategory.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to EventCategory.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _EventCategory extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _EventCategory.class );
	
	// KeyValueCoding support
	
    public static final String SUBCATEGORIES = "subCategories";
    public static final String PARENTCATEGORY = "parentCategory";
    public static final String NAME = "name";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    public static final String CATEGORYDESCRIPTION = "categoryDescription";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "EventCategory";

	/**
	 * Standard constructor
	 */	
    public _EventCategory() {
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
	 * Create a "EventCategory" object with all required values
	 */
	public static EventCategory createEventCategory(EOEditingContext editingContext, NSTimestamp creationTime, String name, net.events.cms.eo.Client client, net.events.cms.eo.EventsUser createdBy) {
		if (log.isDebugEnabled()) log.debug ("Creating object: EventCategory");
		EventCategory eoObject = (EventCategory)EOUtilities.createAndInsertInstance(editingContext, _EventCategory.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setName(name);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "EventCategory" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EventCategory'");
		return _EventCategory.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "EventCategory" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EventCategory' with sortOrderings " + _sortOrderings);
		return _EventCategory.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "EventCategory" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EventCategory.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "EventCategory" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EventCategory' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_EventCategory.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static EventCategory fetchEventCategoryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EventCategory.fetchEventCategoryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static EventCategory fetchEventCategoryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'EventCategory' with qualifier: " + _qualifier);
		NSArray eoObjects = _EventCategory.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		EventCategory eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (EventCategory)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one EventCategory that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EventCategory fetchRequiredEventCategoryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EventCategory.fetchRequiredEventCategoryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EventCategory fetchRequiredEventCategoryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'EventCategory' with qualifier: " + _qualifier);
		EventCategory eoObject = _EventCategory.fetchEventCategoryWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no EventCategory that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public EventCategory localInstanceOfEventCategory(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EventCategory': " + this.toString());
		return (EventCategory)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static EventCategory localInstanceOfEventCategory(EOEditingContext _editingContext, EventCategory _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EventCategory': " + _eo.toString());
		}
		return (_eo == null) ? null : (EventCategory)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "categoryDescription"
	 */
    public String categoryDescription() {
        return (String) storedValueForKey("categoryDescription");
    }

	/**
	 * Set the value for "categoryDescription"
	 */
    public void setCategoryDescription(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating categoryDescription from "+categoryDescription()+" to "+aValue );
        takeStoredValueForKey(aValue, "categoryDescription");
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

    public net.events.cms.eo.EventCategory parentCategory() {
        return (net.events.cms.eo.EventCategory)storedValueForKey("parentCategory");
    }

    public void setParentCategory(net.events.cms.eo.EventCategory aValue) {
        takeStoredValueForKey(aValue, "parentCategory");
    }

	/**
	 * Returns the objects for the relationship "subCategories"
	 */
    public NSArray subCategories() {
        return (NSArray)storedValueForKey("subCategories");
    }

    public void setSubCategories(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating subCategories from "+subCategories()+" to "+aValue );
        takeStoredValueForKey(aValue, "subCategories");
    }

    public void addToSubCategories(net.events.cms.eo.EventCategory object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to subCategories" );
	    includeObjectIntoPropertyWithKey(object, "subCategories");
    }
    

    public void removeFromSubCategories(net.events.cms.eo.EventCategory object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from subCategories" );
	    excludeObjectFromPropertyWithKey(object, "subCategories");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.EventCategory" and add it
     * to the relationship "subCategories"
     */
    public net.events.cms.eo.EventCategory createObjectAndAddToSubCategories() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: subCategories");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("EventCategory");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "subCategories");
	    return (net.events.cms.eo.EventCategory) eoObject;
    }
    
    /**
     * Removes object from the relationship "subCategories" and delete object
     */
    public void removeFromSubCategoriesAndDelete(net.events.cms.eo.EventCategory object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: subCategories");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "subCategories");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "subCategories", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllSubCategories() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: subCategories");
	    Enumeration objects = subCategories().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromSubCategoriesAndDelete((net.events.cms.eo.EventCategory)objects.nextElement());
    }
}