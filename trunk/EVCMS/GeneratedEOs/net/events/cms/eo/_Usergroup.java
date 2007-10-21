// _Usergroup.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Usergroup.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Usergroup extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Usergroup.class );
	
	// KeyValueCoding support
	
    public static final String USERS = "users";
    public static final String PRIVILEGE = "privilege";
    public static final String NAME = "name";
    public static final String AREA = "area";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Usergroup";

	/**
	 * Standard constructor
	 */	
    public _Usergroup() {
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
	 * Create a "Usergroup" object with all required values
	 */
	public static Usergroup createUsergroup(EOEditingContext editingContext, String area, String name, Number privilege) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Usergroup");
		Usergroup eoObject = (Usergroup)EOUtilities.createAndInsertInstance(editingContext, _Usergroup.ENTITY_NAME);
		eoObject.setArea(area);
		eoObject.setName(name);
		eoObject.setPrivilege(privilege);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Usergroup" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Usergroup'");
		return _Usergroup.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Usergroup" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Usergroup' with sortOrderings " + _sortOrderings);
		return _Usergroup.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Usergroup" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Usergroup.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Usergroup" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Usergroup' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Usergroup.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Usergroup fetchUsergroupWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Usergroup.fetchUsergroupWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Usergroup fetchUsergroupWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Usergroup' with qualifier: " + _qualifier);
		NSArray eoObjects = _Usergroup.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Usergroup eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Usergroup)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Usergroup that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Usergroup fetchRequiredUsergroupWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Usergroup.fetchRequiredUsergroupWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Usergroup fetchRequiredUsergroupWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Usergroup' with qualifier: " + _qualifier);
		Usergroup eoObject = _Usergroup.fetchUsergroupWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Usergroup that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Usergroup localInstanceOfUsergroup(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Usergroup': " + this.toString());
		return (Usergroup)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Usergroup localInstanceOfUsergroup(EOEditingContext _editingContext, Usergroup _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Usergroup': " + _eo.toString());
		}
		return (_eo == null) ? null : (Usergroup)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "area"
	 */
    public String area() {
        return (String) storedValueForKey("area");
    }

	/**
	 * Set the value for "area"
	 */
    public void setArea(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating area from "+area()+" to "+aValue );
        takeStoredValueForKey(aValue, "area");
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
	 * The value for "privilege"
	 */
    public Number privilege() {
        return (Number) storedValueForKey("privilege");
    }

	/**
	 * Set the value for "privilege"
	 */
    public void setPrivilege(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating privilege from "+privilege()+" to "+aValue );
        takeStoredValueForKey(aValue, "privilege");
    }

	/**
	 * Returns the objects for the relationship "users"
	 */
    public NSArray users() {
        return (NSArray)storedValueForKey("users");
    }

    public void setUsers(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating users from "+users()+" to "+aValue );
        takeStoredValueForKey(aValue, "users");
    }

    public void addToUsers(net.events.cms.eo.Person object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to users" );
	    includeObjectIntoPropertyWithKey(object, "users");
    }
    

    public void removeFromUsers(net.events.cms.eo.Person object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from users" );
	    excludeObjectFromPropertyWithKey(object, "users");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Person" and add it
     * to the relationship "users"
     */
    public net.events.cms.eo.Person createObjectAndAddToUsers() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: users");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Person");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "users");
	    return (net.events.cms.eo.Person) eoObject;
    }
    
    /**
     * Removes object from the relationship "users" and delete object
     */
    public void removeFromUsersAndDelete(net.events.cms.eo.Person object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: users");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "users");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "users", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllUsers() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: users");
	    Enumeration objects = users().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromUsersAndDelete((net.events.cms.eo.Person)objects.nextElement());
    }
}