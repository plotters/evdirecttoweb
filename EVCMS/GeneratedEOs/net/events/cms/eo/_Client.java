// _Client.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Client.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Client extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Client.class );
	
	// KeyValueCoding support
	
    public static final String STREET2 = "street2";
    public static final String STREET1 = "street1";
    public static final String SITES = "sites";
    public static final String PROVINCE = "province";
    public static final String POSTALCODE = "postalCode";
    public static final String PAGEWRAPPERS = "pageWrappers";
    public static final String NAME = "name";
    public static final String MEETINGPROTOCOLS = "meetingProtocols";
    public static final String DELETED = "deleted";
    public static final String CUSTOMERNUMBER = "customerNumber";
    public static final String COUNTRY = "country";
    public static final String CONTACTS = "contacts";
    public static final String CONTACTPHONE = "contactPhone";
    public static final String CONTACTLASTNAME = "contactLastname";
    public static final String CONTACTFIRSTNAME = "contactFirstname";
    public static final String CONTACTEMAIL = "contactEmail";
    public static final String CLINICALTRIALS = "clinicalTrials";
    public static final String CITY = "city";
    public static final String BLOGS = "blogs";
    public static final String ADMINSTYLESHEET = "adminStylesheet";
    public static final String ACTIVE = "active";
    public static final String ACTIONS = "actions";
    public static final String ACTIONTRIGGERS = "actionTriggers";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Client";

	/**
	 * Standard constructor
	 */	
    public _Client() {
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
	 * Create a "Client" object with all required values
	 */
	public static Client createClient(EOEditingContext editingContext, Boolean active, String city, String contactEmail, String contactFirstname, String contactLastname, String contactPhone, String name, String postalCode, String street1, net.events.cms.eo.Country country) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Client");
		Client eoObject = (Client)EOUtilities.createAndInsertInstance(editingContext, _Client.ENTITY_NAME);
		eoObject.setActive(active);
		eoObject.setCity(city);
		eoObject.setContactEmail(contactEmail);
		eoObject.setContactFirstname(contactFirstname);
		eoObject.setContactLastname(contactLastname);
		eoObject.setContactPhone(contactPhone);
		eoObject.setName(name);
		eoObject.setPostalCode(postalCode);
		eoObject.setStreet1(street1);
		eoObject.setCountry(country);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Client" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Client'");
		return _Client.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Client" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Client' with sortOrderings " + _sortOrderings);
		return _Client.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Client" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Client.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Client" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Client' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Client.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Client fetchClientWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Client.fetchClientWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Client fetchClientWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Client' with qualifier: " + _qualifier);
		NSArray eoObjects = _Client.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Client eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Client)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Client that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Client fetchRequiredClientWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Client.fetchRequiredClientWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Client fetchRequiredClientWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Client' with qualifier: " + _qualifier);
		Client eoObject = _Client.fetchClientWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Client that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Client localInstanceOfClient(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Client': " + this.toString());
		return (Client)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Client localInstanceOfClient(EOEditingContext _editingContext, Client _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Client': " + _eo.toString());
		}
		return (_eo == null) ? null : (Client)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "adminStylesheet"
	 */
    public String adminStylesheet() {
        return (String) storedValueForKey("adminStylesheet");
    }

	/**
	 * Set the value for "adminStylesheet"
	 */
    public void setAdminStylesheet(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating adminStylesheet from "+adminStylesheet()+" to "+aValue );
        takeStoredValueForKey(aValue, "adminStylesheet");
    }

	/**
	 * The value for "city"
	 */
    public String city() {
        return (String) storedValueForKey("city");
    }

	/**
	 * Set the value for "city"
	 */
    public void setCity(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating city from "+city()+" to "+aValue );
        takeStoredValueForKey(aValue, "city");
    }

	/**
	 * The value for "contactEmail"
	 */
    public String contactEmail() {
        return (String) storedValueForKey("contactEmail");
    }

	/**
	 * Set the value for "contactEmail"
	 */
    public void setContactEmail(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating contactEmail from "+contactEmail()+" to "+aValue );
        takeStoredValueForKey(aValue, "contactEmail");
    }

	/**
	 * The value for "contactFirstname"
	 */
    public String contactFirstname() {
        return (String) storedValueForKey("contactFirstname");
    }

	/**
	 * Set the value for "contactFirstname"
	 */
    public void setContactFirstname(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating contactFirstname from "+contactFirstname()+" to "+aValue );
        takeStoredValueForKey(aValue, "contactFirstname");
    }

	/**
	 * The value for "contactLastname"
	 */
    public String contactLastname() {
        return (String) storedValueForKey("contactLastname");
    }

	/**
	 * Set the value for "contactLastname"
	 */
    public void setContactLastname(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating contactLastname from "+contactLastname()+" to "+aValue );
        takeStoredValueForKey(aValue, "contactLastname");
    }

	/**
	 * The value for "contactPhone"
	 */
    public String contactPhone() {
        return (String) storedValueForKey("contactPhone");
    }

	/**
	 * Set the value for "contactPhone"
	 */
    public void setContactPhone(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating contactPhone from "+contactPhone()+" to "+aValue );
        takeStoredValueForKey(aValue, "contactPhone");
    }

	/**
	 * The value for "customerNumber"
	 */
    public String customerNumber() {
        return (String) storedValueForKey("customerNumber");
    }

	/**
	 * Set the value for "customerNumber"
	 */
    public void setCustomerNumber(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating customerNumber from "+customerNumber()+" to "+aValue );
        takeStoredValueForKey(aValue, "customerNumber");
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
	 * The value for "postalCode"
	 */
    public String postalCode() {
        return (String) storedValueForKey("postalCode");
    }

	/**
	 * Set the value for "postalCode"
	 */
    public void setPostalCode(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating postalCode from "+postalCode()+" to "+aValue );
        takeStoredValueForKey(aValue, "postalCode");
    }

	/**
	 * The value for "province"
	 */
    public String province() {
        return (String) storedValueForKey("province");
    }

	/**
	 * Set the value for "province"
	 */
    public void setProvince(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating province from "+province()+" to "+aValue );
        takeStoredValueForKey(aValue, "province");
    }

	/**
	 * The value for "street1"
	 */
    public String street1() {
        return (String) storedValueForKey("street1");
    }

	/**
	 * Set the value for "street1"
	 */
    public void setStreet1(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating street1 from "+street1()+" to "+aValue );
        takeStoredValueForKey(aValue, "street1");
    }

	/**
	 * The value for "street2"
	 */
    public String street2() {
        return (String) storedValueForKey("street2");
    }

	/**
	 * Set the value for "street2"
	 */
    public void setStreet2(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating street2 from "+street2()+" to "+aValue );
        takeStoredValueForKey(aValue, "street2");
    }

    public net.events.cms.eo.Country country() {
        return (net.events.cms.eo.Country)storedValueForKey("country");
    }

    public void setCountry(net.events.cms.eo.Country aValue) {
        takeStoredValueForKey(aValue, "country");
    }

	/**
	 * Returns the objects for the relationship "actionTriggers"
	 */
    public NSArray actionTriggers() {
        return (NSArray)storedValueForKey("actionTriggers");
    }

    public void setActionTriggers(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating actionTriggers from "+actionTriggers()+" to "+aValue );
        takeStoredValueForKey(aValue, "actionTriggers");
    }

    public void addToActionTriggers(net.events.cms.eo.ActionTrigger object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to actionTriggers" );
	    includeObjectIntoPropertyWithKey(object, "actionTriggers");
    }
    

    public void removeFromActionTriggers(net.events.cms.eo.ActionTrigger object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from actionTriggers" );
	    excludeObjectFromPropertyWithKey(object, "actionTriggers");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.ActionTrigger" and add it
     * to the relationship "actionTriggers"
     */
    public net.events.cms.eo.ActionTrigger createObjectAndAddToActionTriggers() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: actionTriggers");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ActionTrigger");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "actionTriggers");
	    return (net.events.cms.eo.ActionTrigger) eoObject;
    }
    
    /**
     * Removes object from the relationship "actionTriggers" and delete object
     */
    public void removeFromActionTriggersAndDelete(net.events.cms.eo.ActionTrigger object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: actionTriggers");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "actionTriggers");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "actionTriggers", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllActionTriggers() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: actionTriggers");
	    Enumeration objects = actionTriggers().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromActionTriggersAndDelete((net.events.cms.eo.ActionTrigger)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "actions"
	 */
    public NSArray actions() {
        return (NSArray)storedValueForKey("actions");
    }

    public void setActions(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating actions from "+actions()+" to "+aValue );
        takeStoredValueForKey(aValue, "actions");
    }

    public void addToActions(net.events.cms.eo.Action object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to actions" );
	    includeObjectIntoPropertyWithKey(object, "actions");
    }
    

    public void removeFromActions(net.events.cms.eo.Action object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from actions" );
	    excludeObjectFromPropertyWithKey(object, "actions");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Action" and add it
     * to the relationship "actions"
     */
    public net.events.cms.eo.Action createObjectAndAddToActions() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: actions");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Action");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "actions");
	    return (net.events.cms.eo.Action) eoObject;
    }
    
    /**
     * Removes object from the relationship "actions" and delete object
     */
    public void removeFromActionsAndDelete(net.events.cms.eo.Action object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: actions");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "actions");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "actions", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllActions() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: actions");
	    Enumeration objects = actions().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromActionsAndDelete((net.events.cms.eo.Action)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "blogs"
	 */
    public NSArray blogs() {
        return (NSArray)storedValueForKey("blogs");
    }

    public void setBlogs(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating blogs from "+blogs()+" to "+aValue );
        takeStoredValueForKey(aValue, "blogs");
    }

    public void addToBlogs(net.events.cms.eo.Blog object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to blogs" );
	    includeObjectIntoPropertyWithKey(object, "blogs");
    }
    

    public void removeFromBlogs(net.events.cms.eo.Blog object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from blogs" );
	    excludeObjectFromPropertyWithKey(object, "blogs");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Blog" and add it
     * to the relationship "blogs"
     */
    public net.events.cms.eo.Blog createObjectAndAddToBlogs() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: blogs");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Blog");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "blogs");
	    return (net.events.cms.eo.Blog) eoObject;
    }
    
    /**
     * Removes object from the relationship "blogs" and delete object
     */
    public void removeFromBlogsAndDelete(net.events.cms.eo.Blog object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: blogs");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "blogs");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "blogs", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllBlogs() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: blogs");
	    Enumeration objects = blogs().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromBlogsAndDelete((net.events.cms.eo.Blog)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "clinicalTrials"
	 */
    public NSArray clinicalTrials() {
        return (NSArray)storedValueForKey("clinicalTrials");
    }

    public void setClinicalTrials(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating clinicalTrials from "+clinicalTrials()+" to "+aValue );
        takeStoredValueForKey(aValue, "clinicalTrials");
    }

    public void addToClinicalTrials(net.events.cms.eo.ClinicalTrial object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to clinicalTrials" );
	    includeObjectIntoPropertyWithKey(object, "clinicalTrials");
    }
    

    public void removeFromClinicalTrials(net.events.cms.eo.ClinicalTrial object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from clinicalTrials" );
	    excludeObjectFromPropertyWithKey(object, "clinicalTrials");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.ClinicalTrial" and add it
     * to the relationship "clinicalTrials"
     */
    public net.events.cms.eo.ClinicalTrial createObjectAndAddToClinicalTrials() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: clinicalTrials");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ClinicalTrial");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "clinicalTrials");
	    return (net.events.cms.eo.ClinicalTrial) eoObject;
    }
    
    /**
     * Removes object from the relationship "clinicalTrials" and delete object
     */
    public void removeFromClinicalTrialsAndDelete(net.events.cms.eo.ClinicalTrial object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: clinicalTrials");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "clinicalTrials");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "clinicalTrials", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllClinicalTrials() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: clinicalTrials");
	    Enumeration objects = clinicalTrials().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromClinicalTrialsAndDelete((net.events.cms.eo.ClinicalTrial)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "contacts"
	 */
    public NSArray contacts() {
        return (NSArray)storedValueForKey("contacts");
    }

    public void setContacts(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating contacts from "+contacts()+" to "+aValue );
        takeStoredValueForKey(aValue, "contacts");
    }

    public void addToContacts(net.events.cms.eo.Contact object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to contacts" );
	    includeObjectIntoPropertyWithKey(object, "contacts");
    }
    

    public void removeFromContacts(net.events.cms.eo.Contact object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from contacts" );
	    excludeObjectFromPropertyWithKey(object, "contacts");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Contact" and add it
     * to the relationship "contacts"
     */
    public net.events.cms.eo.Contact createObjectAndAddToContacts() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: contacts");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Contact");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "contacts");
	    return (net.events.cms.eo.Contact) eoObject;
    }
    
    /**
     * Removes object from the relationship "contacts" and delete object
     */
    public void removeFromContactsAndDelete(net.events.cms.eo.Contact object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: contacts");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "contacts");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "contacts", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllContacts() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: contacts");
	    Enumeration objects = contacts().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromContactsAndDelete((net.events.cms.eo.Contact)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "meetingProtocols"
	 */
    public NSArray meetingProtocols() {
        return (NSArray)storedValueForKey("meetingProtocols");
    }

    public void setMeetingProtocols(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating meetingProtocols from "+meetingProtocols()+" to "+aValue );
        takeStoredValueForKey(aValue, "meetingProtocols");
    }

    public void addToMeetingProtocols(net.events.cms.eo.MeetingMinutes object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to meetingProtocols" );
	    includeObjectIntoPropertyWithKey(object, "meetingProtocols");
    }
    

    public void removeFromMeetingProtocols(net.events.cms.eo.MeetingMinutes object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from meetingProtocols" );
	    excludeObjectFromPropertyWithKey(object, "meetingProtocols");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.MeetingMinutes" and add it
     * to the relationship "meetingProtocols"
     */
    public net.events.cms.eo.MeetingMinutes createObjectAndAddToMeetingProtocols() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: meetingProtocols");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("MeetingMinutes");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "meetingProtocols");
	    return (net.events.cms.eo.MeetingMinutes) eoObject;
    }
    
    /**
     * Removes object from the relationship "meetingProtocols" and delete object
     */
    public void removeFromMeetingProtocolsAndDelete(net.events.cms.eo.MeetingMinutes object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: meetingProtocols");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "meetingProtocols");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "meetingProtocols", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllMeetingProtocols() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: meetingProtocols");
	    Enumeration objects = meetingProtocols().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromMeetingProtocolsAndDelete((net.events.cms.eo.MeetingMinutes)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "pageWrappers"
	 */
    public NSArray pageWrappers() {
        return (NSArray)storedValueForKey("pageWrappers");
    }

    public void setPageWrappers(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating pageWrappers from "+pageWrappers()+" to "+aValue );
        takeStoredValueForKey(aValue, "pageWrappers");
    }

    public void addToPageWrappers(net.events.cms.eo.PageWrapperEO object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to pageWrappers" );
	    includeObjectIntoPropertyWithKey(object, "pageWrappers");
    }
    

    public void removeFromPageWrappers(net.events.cms.eo.PageWrapperEO object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from pageWrappers" );
	    excludeObjectFromPropertyWithKey(object, "pageWrappers");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.PageWrapperEO" and add it
     * to the relationship "pageWrappers"
     */
    public net.events.cms.eo.PageWrapperEO createObjectAndAddToPageWrappers() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: pageWrappers");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("PageWrapperEO");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "pageWrappers");
	    return (net.events.cms.eo.PageWrapperEO) eoObject;
    }
    
    /**
     * Removes object from the relationship "pageWrappers" and delete object
     */
    public void removeFromPageWrappersAndDelete(net.events.cms.eo.PageWrapperEO object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: pageWrappers");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "pageWrappers");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "pageWrappers", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllPageWrappers() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: pageWrappers");
	    Enumeration objects = pageWrappers().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromPageWrappersAndDelete((net.events.cms.eo.PageWrapperEO)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "sites"
	 */
    public NSArray sites() {
        return (NSArray)storedValueForKey("sites");
    }

    public void setSites(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating sites from "+sites()+" to "+aValue );
        takeStoredValueForKey(aValue, "sites");
    }

    public void addToSites(net.events.cms.eo.Site object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to sites" );
	    includeObjectIntoPropertyWithKey(object, "sites");
    }
    

    public void removeFromSites(net.events.cms.eo.Site object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from sites" );
	    excludeObjectFromPropertyWithKey(object, "sites");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Site" and add it
     * to the relationship "sites"
     */
    public net.events.cms.eo.Site createObjectAndAddToSites() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: sites");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Site");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "sites");
	    return (net.events.cms.eo.Site) eoObject;
    }
    
    /**
     * Removes object from the relationship "sites" and delete object
     */
    public void removeFromSitesAndDelete(net.events.cms.eo.Site object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: sites");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "sites");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "sites", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllSites() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: sites");
	    Enumeration objects = sites().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromSitesAndDelete((net.events.cms.eo.Site)objects.nextElement());
    }
}