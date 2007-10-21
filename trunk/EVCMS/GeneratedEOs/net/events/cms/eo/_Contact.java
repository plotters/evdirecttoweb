// _Contact.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Contact.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Contact extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Contact.class );
	
	// KeyValueCoding support
	
    public static final String NOTES = "notes";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DELETED = "deleted";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    public static final String ADDRESSES = "addresses";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Contact";

	/**
	 * Standard constructor
	 */	
    public _Contact() {
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
	 * Create a "Contact" object with all required values
	 */
	public static Contact createContact(EOEditingContext editingContext, NSTimestamp creationTime, Boolean deleted, String inheritanceType) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Contact");
		Contact eoObject = (Contact)EOUtilities.createAndInsertInstance(editingContext, _Contact.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setDeleted(deleted);
		eoObject.setInheritanceType(inheritanceType);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Contact" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Contact'");
		return _Contact.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Contact" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Contact' with sortOrderings " + _sortOrderings);
		return _Contact.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Contact" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Contact.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Contact" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Contact' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Contact.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Contact fetchContactWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Contact.fetchContactWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Contact fetchContactWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Contact' with qualifier: " + _qualifier);
		NSArray eoObjects = _Contact.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Contact eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Contact)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Contact that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Contact fetchRequiredContactWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Contact.fetchRequiredContactWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Contact fetchRequiredContactWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Contact' with qualifier: " + _qualifier);
		Contact eoObject = _Contact.fetchContactWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Contact that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Contact localInstanceOfContact(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Contact': " + this.toString());
		return (Contact)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Contact localInstanceOfContact(EOEditingContext _editingContext, Contact _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Contact': " + _eo.toString());
		}
		return (_eo == null) ? null : (Contact)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "deleted"
	 */
    public Boolean deleted() {
        return (Boolean) storedValueForKey("deleted");
    }

	/**
	 * Set the value for "deleted"
	 */
    public void setDeleted(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating deleted from "+deleted()+" to "+aValue );
        takeStoredValueForKey(aValue, "deleted");
    }

	/**
	 * The value for "inheritanceType"
	 */
    public String inheritanceType() {
        return (String) storedValueForKey("inheritanceType");
    }

	/**
	 * Set the value for "inheritanceType"
	 */
    public void setInheritanceType(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating inheritanceType from "+inheritanceType()+" to "+aValue );
        takeStoredValueForKey(aValue, "inheritanceType");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.Person createdBy() {
        return (net.events.cms.eo.Person)storedValueForKey("createdBy");
    }

    public void setCreatedBy(net.events.cms.eo.Person aValue) {
        takeStoredValueForKey(aValue, "createdBy");
    }

	/**
	 * Returns the objects for the relationship "addresses"
	 */
    public NSArray addresses() {
        return (NSArray)storedValueForKey("addresses");
    }

    public void setAddresses(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating addresses from "+addresses()+" to "+aValue );
        takeStoredValueForKey(aValue, "addresses");
    }

    public void addToAddresses(net.events.cms.eo.Address object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to addresses" );
	    includeObjectIntoPropertyWithKey(object, "addresses");
    }
    

    public void removeFromAddresses(net.events.cms.eo.Address object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from addresses" );
	    excludeObjectFromPropertyWithKey(object, "addresses");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Address" and add it
     * to the relationship "addresses"
     */
    public net.events.cms.eo.Address createObjectAndAddToAddresses() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: addresses");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Address");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "addresses");
	    return (net.events.cms.eo.Address) eoObject;
    }
    
    /**
     * Removes object from the relationship "addresses" and delete object
     */
    public void removeFromAddressesAndDelete(net.events.cms.eo.Address object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: addresses");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "addresses");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "addresses", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllAddresses() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: addresses");
	    Enumeration objects = addresses().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromAddressesAndDelete((net.events.cms.eo.Address)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "notes"
	 */
    public NSArray notes() {
        return (NSArray)storedValueForKey("notes");
    }

    public void setNotes(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating notes from "+notes()+" to "+aValue );
        takeStoredValueForKey(aValue, "notes");
    }

    public void addToNotes(net.events.cms.eo.ContactNote object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to notes" );
	    includeObjectIntoPropertyWithKey(object, "notes");
    }
    

    public void removeFromNotes(net.events.cms.eo.ContactNote object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from notes" );
	    excludeObjectFromPropertyWithKey(object, "notes");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.ContactNote" and add it
     * to the relationship "notes"
     */
    public net.events.cms.eo.ContactNote createObjectAndAddToNotes() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: notes");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ContactNote");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "notes");
	    return (net.events.cms.eo.ContactNote) eoObject;
    }
    
    /**
     * Removes object from the relationship "notes" and delete object
     */
    public void removeFromNotesAndDelete(net.events.cms.eo.ContactNote object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: notes");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "notes");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "notes", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllNotes() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: notes");
	    Enumeration objects = notes().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromNotesAndDelete((net.events.cms.eo.ContactNote)objects.nextElement());
    }
}