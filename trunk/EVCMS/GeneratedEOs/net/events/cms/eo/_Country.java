// _Country.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Country.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Country extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Country.class );
	
	// KeyValueCoding support
	
    public static final String PROVINCES = "provinces";
    public static final String NAME = "name";
    public static final String CODE = "code";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Country";

	/**
	 * Standard constructor
	 */	
    public _Country() {
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
	 * Create a "Country" object with all required values
	 */
	public static Country createCountry(EOEditingContext editingContext, String code, String name) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Country");
		Country eoObject = (Country)EOUtilities.createAndInsertInstance(editingContext, _Country.ENTITY_NAME);
		eoObject.setCode(code);
		eoObject.setName(name);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Country" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Country'");
		return _Country.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Country" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Country' with sortOrderings " + _sortOrderings);
		return _Country.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Country" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Country.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Country" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Country' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Country.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Country fetchCountryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Country.fetchCountryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Country fetchCountryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Country' with qualifier: " + _qualifier);
		NSArray eoObjects = _Country.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Country eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Country)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Country that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Country fetchRequiredCountryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Country.fetchRequiredCountryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Country fetchRequiredCountryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Country' with qualifier: " + _qualifier);
		Country eoObject = _Country.fetchCountryWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Country that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Country localInstanceOfCountry(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Country': " + this.toString());
		return (Country)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Country localInstanceOfCountry(EOEditingContext _editingContext, Country _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Country': " + _eo.toString());
		}
		return (_eo == null) ? null : (Country)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "code"
	 */
    public String code() {
        return (String) storedValueForKey("code");
    }

	/**
	 * Set the value for "code"
	 */
    public void setCode(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating code from "+code()+" to "+aValue );
        takeStoredValueForKey(aValue, "code");
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
	 * Returns the objects for the relationship "provinces"
	 */
    public NSArray provinces() {
        return (NSArray)storedValueForKey("provinces");
    }

    public void setProvinces(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating provinces from "+provinces()+" to "+aValue );
        takeStoredValueForKey(aValue, "provinces");
    }

    public void addToProvinces(net.events.cms.eo.Province object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to provinces" );
	    includeObjectIntoPropertyWithKey(object, "provinces");
    }
    

    public void removeFromProvinces(net.events.cms.eo.Province object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from provinces" );
	    excludeObjectFromPropertyWithKey(object, "provinces");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Province" and add it
     * to the relationship "provinces"
     */
    public net.events.cms.eo.Province createObjectAndAddToProvinces() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: provinces");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Province");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "provinces");
	    return (net.events.cms.eo.Province) eoObject;
    }
    
    /**
     * Removes object from the relationship "provinces" and delete object
     */
    public void removeFromProvincesAndDelete(net.events.cms.eo.Province object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: provinces");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "provinces");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "provinces", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllProvinces() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: provinces");
	    Enumeration objects = provinces().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromProvincesAndDelete((net.events.cms.eo.Province)objects.nextElement());
    }
}