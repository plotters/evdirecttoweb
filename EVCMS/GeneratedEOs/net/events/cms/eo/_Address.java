// _Address.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Address.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Address extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Address.class );
	
	// KeyValueCoding support
	
    public static final String STREET2 = "street2";
    public static final String STREET1 = "street1";
    public static final String PROVINCE = "province";
    public static final String POSTALCODE = "postalCode";
    public static final String COUNTRY = "country";
    public static final String CONTACT = "contact";
    public static final String CLIENT = "client";
    public static final String CITY = "city";
    public static final String ADDRESSTYPECODE = "addressTypeCode";
    public static final String ADDRESSNAME = "addressName";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Address";

	/**
	 * Standard constructor
	 */	
    public _Address() {
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
	 * Create a "Address" object with all required values
	 */
	public static Address createAddress(EOEditingContext editingContext, String addressName, String city, String postalCode, String street1, net.events.cms.eo.Client client, net.events.cms.eo.Contact contact) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Address");
		Address eoObject = (Address)EOUtilities.createAndInsertInstance(editingContext, _Address.ENTITY_NAME);
		eoObject.setAddressName(addressName);
		eoObject.setCity(city);
		eoObject.setPostalCode(postalCode);
		eoObject.setStreet1(street1);
		eoObject.setClient(client);
		eoObject.setContact(contact);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Address" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Address'");
		return _Address.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Address" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Address' with sortOrderings " + _sortOrderings);
		return _Address.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Address" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Address.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Address" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Address' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Address.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Address fetchAddressWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Address.fetchAddressWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Address fetchAddressWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Address' with qualifier: " + _qualifier);
		NSArray eoObjects = _Address.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Address eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Address)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Address that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Address fetchRequiredAddressWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Address.fetchRequiredAddressWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Address fetchRequiredAddressWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Address' with qualifier: " + _qualifier);
		Address eoObject = _Address.fetchAddressWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Address that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Address localInstanceOfAddress(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Address': " + this.toString());
		return (Address)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Address localInstanceOfAddress(EOEditingContext _editingContext, Address _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Address': " + _eo.toString());
		}
		return (_eo == null) ? null : (Address)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "addressName"
	 */
    public String addressName() {
        return (String) storedValueForKey("addressName");
    }

	/**
	 * Set the value for "addressName"
	 */
    public void setAddressName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating addressName from "+addressName()+" to "+aValue );
        takeStoredValueForKey(aValue, "addressName");
    }

	/**
	 * The value for "addressTypeCode"
	 */
    public String addressTypeCode() {
        return (String) storedValueForKey("addressTypeCode");
    }

	/**
	 * Set the value for "addressTypeCode"
	 */
    public void setAddressTypeCode(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating addressTypeCode from "+addressTypeCode()+" to "+aValue );
        takeStoredValueForKey(aValue, "addressTypeCode");
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

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.Contact contact() {
        return (net.events.cms.eo.Contact)storedValueForKey("contact");
    }

    public void setContact(net.events.cms.eo.Contact aValue) {
        takeStoredValueForKey(aValue, "contact");
    }

    public net.events.cms.eo.Country country() {
        return (net.events.cms.eo.Country)storedValueForKey("country");
    }

    public void setCountry(net.events.cms.eo.Country aValue) {
        takeStoredValueForKey(aValue, "country");
    }

    public net.events.cms.eo.Province province() {
        return (net.events.cms.eo.Province)storedValueForKey("province");
    }

    public void setProvince(net.events.cms.eo.Province aValue) {
        takeStoredValueForKey(aValue, "province");
    }
}