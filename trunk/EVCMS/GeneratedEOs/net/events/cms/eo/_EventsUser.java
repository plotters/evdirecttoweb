// _EventsUser.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to EventsUser.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _EventsUser extends net.events.cms.eo.Person {
	private static Logger log = Logger.getLogger( _EventsUser.class );
	
	// KeyValueCoding support
	
    public static final String VISITEDBLOGS = "visitedBlogs";
    public static final String USERGROUP = "usergroup";
    public static final String TITLE = "title";
    public static final String PASSWORDATTEMPTS = "passwordAttempts";
    public static final String PASSWORD = "password";
    public static final String NOTES = "notes";
    public static final String MIDDLENAME = "middlename";
    public static final String MEDICALDATASETENTRIES = "medicalDataSetEntries";
    public static final String LOGIN = "login";
    public static final String LASTNAME = "lastname";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String GENDER = "gender";
    public static final String FIRSTNAME = "firstname";
    public static final String EMAIL = "email";
    public static final String EDITEDSITES = "editedSites";
    public static final String EDITEDBLOGS = "editedBlogs";
    public static final String DELETED = "deleted";
    public static final String DATEOFBIRTH = "dateOfBirth";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String COMPANY = "company";
    public static final String CLIENT = "client";
    public static final String ADDRESSES = "addresses";
    public static final String ACTIVE = "active";
    public static final String ACADEMICTITLE = "academicTitle";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "EventsUser";

	/**
	 * Standard constructor
	 */	
    public _EventsUser() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("EventsUser", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "EventsUser" object with all required values
	 */
	public static EventsUser createEventsUser(EOEditingContext editingContext, Boolean active, String firstname, String lastname, String login, String password, Number passwordAttempts, net.events.cms.eo.Gender gender) {
		if (log.isDebugEnabled()) log.debug ("Creating object: EventsUser");
		EventsUser eoObject = (EventsUser)EOUtilities.createAndInsertInstance(editingContext, _EventsUser.ENTITY_NAME);
		eoObject.setActive(active);
		eoObject.setFirstname(firstname);
		eoObject.setLastname(lastname);
		eoObject.setLogin(login);
		eoObject.setPassword(password);
		eoObject.setPasswordAttempts(passwordAttempts);
		eoObject.setGender(gender);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "EventsUser" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EventsUser'");
		return _EventsUser.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "EventsUser" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EventsUser' with sortOrderings " + _sortOrderings);
		return _EventsUser.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "EventsUser" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EventsUser.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "EventsUser" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EventsUser' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_EventsUser.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static EventsUser fetchEventsUserWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EventsUser.fetchEventsUserWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static EventsUser fetchEventsUserWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'EventsUser' with qualifier: " + _qualifier);
		NSArray eoObjects = _EventsUser.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		EventsUser eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (EventsUser)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one EventsUser that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EventsUser fetchRequiredEventsUserWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EventsUser.fetchRequiredEventsUserWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EventsUser fetchRequiredEventsUserWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'EventsUser' with qualifier: " + _qualifier);
		EventsUser eoObject = _EventsUser.fetchEventsUserWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no EventsUser that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public EventsUser localInstanceOfEventsUser(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EventsUser': " + this.toString());
		return (EventsUser)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static EventsUser localInstanceOfEventsUser(EOEditingContext _editingContext, EventsUser _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EventsUser': " + _eo.toString());
		}
		return (_eo == null) ? null : (EventsUser)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "active"
	 */
    public Boolean active() {
        return (Boolean) storedValueForKey("active");
    }

	/**
	 * Set the value for "active"
	 */
    public void setActive(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating active from "+active()+" to "+aValue );
        takeStoredValueForKey(aValue, "active");
    }

	/**
	 * Returns the objects for the relationship "editedBlogs"
	 */
    public NSArray editedBlogs() {
        return (NSArray)storedValueForKey("editedBlogs");
    }

    public void setEditedBlogs(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating editedBlogs from "+editedBlogs()+" to "+aValue );
        takeStoredValueForKey(aValue, "editedBlogs");
    }

    public void addToEditedBlogs(net.events.cms.eo.Blog object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to editedBlogs" );
	    includeObjectIntoPropertyWithKey(object, "editedBlogs");
    }
    

    public void removeFromEditedBlogs(net.events.cms.eo.Blog object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from editedBlogs" );
	    excludeObjectFromPropertyWithKey(object, "editedBlogs");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Blog" and add it
     * to the relationship "editedBlogs"
     */
    public net.events.cms.eo.Blog createObjectAndAddToEditedBlogs() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: editedBlogs");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Blog");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "editedBlogs");
	    return (net.events.cms.eo.Blog) eoObject;
    }
    
    /**
     * Removes object from the relationship "editedBlogs" and delete object
     */
    public void removeFromEditedBlogsAndDelete(net.events.cms.eo.Blog object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: editedBlogs");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "editedBlogs");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "editedBlogs", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllEditedBlogs() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: editedBlogs");
	    Enumeration objects = editedBlogs().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromEditedBlogsAndDelete((net.events.cms.eo.Blog)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "editedSites"
	 */
    public NSArray editedSites() {
        return (NSArray)storedValueForKey("editedSites");
    }

    public void setEditedSites(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating editedSites from "+editedSites()+" to "+aValue );
        takeStoredValueForKey(aValue, "editedSites");
    }

    public void addToEditedSites(net.events.cms.eo.EditedSite object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to editedSites" );
	    includeObjectIntoPropertyWithKey(object, "editedSites");
    }
    

    public void removeFromEditedSites(net.events.cms.eo.EditedSite object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from editedSites" );
	    excludeObjectFromPropertyWithKey(object, "editedSites");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.EditedSite" and add it
     * to the relationship "editedSites"
     */
    public net.events.cms.eo.EditedSite createObjectAndAddToEditedSites() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: editedSites");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("EditedSite");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "editedSites");
	    return (net.events.cms.eo.EditedSite) eoObject;
    }
    
    /**
     * Removes object from the relationship "editedSites" and delete object
     */
    public void removeFromEditedSitesAndDelete(net.events.cms.eo.EditedSite object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: editedSites");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "editedSites");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "editedSites", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllEditedSites() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: editedSites");
	    Enumeration objects = editedSites().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromEditedSitesAndDelete((net.events.cms.eo.EditedSite)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "visitedBlogs"
	 */
    public NSArray visitedBlogs() {
        return (NSArray)storedValueForKey("visitedBlogs");
    }

    public void setVisitedBlogs(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating visitedBlogs from "+visitedBlogs()+" to "+aValue );
        takeStoredValueForKey(aValue, "visitedBlogs");
    }

    public void addToVisitedBlogs(EOEnterpriseObject object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to visitedBlogs" );
	    includeObjectIntoPropertyWithKey(object, "visitedBlogs");
    }
    

    public void removeFromVisitedBlogs(EOEnterpriseObject object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from visitedBlogs" );
	    excludeObjectFromPropertyWithKey(object, "visitedBlogs");
    }
	
    
    /** 
     * creates a new object "EOEnterpriseObject" and add it
     * to the relationship "visitedBlogs"
     */
    public EOEnterpriseObject createObjectAndAddToVisitedBlogs() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: visitedBlogs");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("XVisitorBlog");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "visitedBlogs");
	    return (EOEnterpriseObject) eoObject;
    }
    
    /**
     * Removes object from the relationship "visitedBlogs" and delete object
     */
    public void removeFromVisitedBlogsAndDelete(EOEnterpriseObject object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: visitedBlogs");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "visitedBlogs");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "visitedBlogs", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllVisitedBlogs() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: visitedBlogs");
	    Enumeration objects = visitedBlogs().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromVisitedBlogsAndDelete((EOEnterpriseObject)objects.nextElement());
    }
}