// _Person.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Person.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Person extends net.events.cms.eo.Contact {
	private static Logger log = Logger.getLogger( _Person.class );
	
	// KeyValueCoding support
	
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
    public static final String DELETED = "deleted";
    public static final String DATEOFBIRTH = "dateOfBirth";
    public static final String DATASETITEMACTIONTRIGGERS = "dataSetItemActionTriggers";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String COMPANYNAME2 = "companyName2";
    public static final String COMPANYNAME = "companyName";
    public static final String COMPANY = "company";
    public static final String CLIENT = "client";
    public static final String ADDRESSES = "addresses";
    public static final String ACADEMICTITLE = "academicTitle";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Person";

	/**
	 * Standard constructor
	 */	
    public _Person() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("Person", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "Person" object with all required values
	 */
	public static Person createPerson(EOEditingContext editingContext, Number passwordAttempts, NSTimestamp creationTime, Boolean deleted, net.events.cms.eo.Gender gender) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Person");
		Person eoObject = (Person)EOUtilities.createAndInsertInstance(editingContext, _Person.ENTITY_NAME);
		eoObject.setPasswordAttempts(passwordAttempts);
		eoObject.setGender(gender);
		eoObject.setCreationTime(creationTime);
		eoObject.setDeleted(deleted);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Person" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Person'");
		return _Person.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Person" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Person' with sortOrderings " + _sortOrderings);
		return _Person.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Person" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Person.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Person" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Person' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Person.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Person fetchPersonWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Person.fetchPersonWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Person fetchPersonWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Person' with qualifier: " + _qualifier);
		NSArray eoObjects = _Person.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Person eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Person)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Person that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Person fetchRequiredPersonWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Person.fetchRequiredPersonWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Person fetchRequiredPersonWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Person' with qualifier: " + _qualifier);
		Person eoObject = _Person.fetchPersonWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Person that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Person localInstanceOfPerson(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Person': " + this.toString());
		return (Person)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Person localInstanceOfPerson(EOEditingContext _editingContext, Person _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Person': " + _eo.toString());
		}
		return (_eo == null) ? null : (Person)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "academicTitle"
	 */
    public String academicTitle() {
        return (String) storedValueForKey("academicTitle");
    }

	/**
	 * Set the value for "academicTitle"
	 */
    public void setAcademicTitle(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating academicTitle from "+academicTitle()+" to "+aValue );
        takeStoredValueForKey(aValue, "academicTitle");
    }

	/**
	 * The value for "dateOfBirth"
	 */
    public NSTimestamp dateOfBirth() {
        return (NSTimestamp) storedValueForKey("dateOfBirth");
    }

	/**
	 * Set the value for "dateOfBirth"
	 */
    public void setDateOfBirth(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating dateOfBirth from "+dateOfBirth()+" to "+aValue );
        takeStoredValueForKey(aValue, "dateOfBirth");
    }

	/**
	 * The value for "login"
	 */
    public String login() {
        return (String) storedValueForKey("login");
    }

	/**
	 * Set the value for "login"
	 */
    public void setLogin(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating login from "+login()+" to "+aValue );
        takeStoredValueForKey(aValue, "login");
    }

	/**
	 * The value for "password"
	 */
    public String password() {
        return (String) storedValueForKey("password");
    }

	/**
	 * Set the value for "password"
	 */
    public void setPassword(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating password from "+password()+" to "+aValue );
        takeStoredValueForKey(aValue, "password");
    }

	/**
	 * The value for "passwordAttempts"
	 */
    public Number passwordAttempts() {
        return (Number) storedValueForKey("passwordAttempts");
    }

	/**
	 * Set the value for "passwordAttempts"
	 */
    public void setPasswordAttempts(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating passwordAttempts from "+passwordAttempts()+" to "+aValue );
        takeStoredValueForKey(aValue, "passwordAttempts");
    }

	/**
	 * The value for "title"
	 */
    public String title() {
        return (String) storedValueForKey("title");
    }

	/**
	 * Set the value for "title"
	 */
    public void setTitle(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating title from "+title()+" to "+aValue );
        takeStoredValueForKey(aValue, "title");
    }

    public net.events.cms.eo.Company company() {
        return (net.events.cms.eo.Company)storedValueForKey("company");
    }

    public void setCompany(net.events.cms.eo.Company aValue) {
        takeStoredValueForKey(aValue, "company");
    }

    public net.events.cms.eo.Gender gender() {
        return (net.events.cms.eo.Gender)storedValueForKey("gender");
    }

    public void setGender(net.events.cms.eo.Gender aValue) {
        takeStoredValueForKey(aValue, "gender");
    }

    public net.events.cms.eo.Usergroup usergroup() {
        return (net.events.cms.eo.Usergroup)storedValueForKey("usergroup");
    }

    public void setUsergroup(net.events.cms.eo.Usergroup aValue) {
        takeStoredValueForKey(aValue, "usergroup");
    }

	/**
	 * Returns the objects for the relationship "dataSetItemActionTriggers"
	 */
    public NSArray dataSetItemActionTriggers() {
        return (NSArray)storedValueForKey("dataSetItemActionTriggers");
    }

    public void setDataSetItemActionTriggers(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating dataSetItemActionTriggers from "+dataSetItemActionTriggers()+" to "+aValue );
        takeStoredValueForKey(aValue, "dataSetItemActionTriggers");
    }

    public void addToDataSetItemActionTriggers(net.events.cms.eo.DataSetItemActionTrigger object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to dataSetItemActionTriggers" );
	    includeObjectIntoPropertyWithKey(object, "dataSetItemActionTriggers");
    }
    

    public void removeFromDataSetItemActionTriggers(net.events.cms.eo.DataSetItemActionTrigger object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from dataSetItemActionTriggers" );
	    excludeObjectFromPropertyWithKey(object, "dataSetItemActionTriggers");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.DataSetItemActionTrigger" and add it
     * to the relationship "dataSetItemActionTriggers"
     */
    public net.events.cms.eo.DataSetItemActionTrigger createObjectAndAddToDataSetItemActionTriggers() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: dataSetItemActionTriggers");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("DataSetItemActionTrigger");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "dataSetItemActionTriggers");
	    return (net.events.cms.eo.DataSetItemActionTrigger) eoObject;
    }
    
    /**
     * Removes object from the relationship "dataSetItemActionTriggers" and delete object
     */
    public void removeFromDataSetItemActionTriggersAndDelete(net.events.cms.eo.DataSetItemActionTrigger object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: dataSetItemActionTriggers");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "dataSetItemActionTriggers");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "dataSetItemActionTriggers", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllDataSetItemActionTriggers() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: dataSetItemActionTriggers");
	    Enumeration objects = dataSetItemActionTriggers().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromDataSetItemActionTriggersAndDelete((net.events.cms.eo.DataSetItemActionTrigger)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "medicalDataSetEntries"
	 */
    public NSArray medicalDataSetEntries() {
        return (NSArray)storedValueForKey("medicalDataSetEntries");
    }

    public void setMedicalDataSetEntries(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating medicalDataSetEntries from "+medicalDataSetEntries()+" to "+aValue );
        takeStoredValueForKey(aValue, "medicalDataSetEntries");
    }

    public void addToMedicalDataSetEntries(net.events.cms.eo.DataSetEntry object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to medicalDataSetEntries" );
	    includeObjectIntoPropertyWithKey(object, "medicalDataSetEntries");
    }
    

    public void removeFromMedicalDataSetEntries(net.events.cms.eo.DataSetEntry object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from medicalDataSetEntries" );
	    excludeObjectFromPropertyWithKey(object, "medicalDataSetEntries");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.DataSetEntry" and add it
     * to the relationship "medicalDataSetEntries"
     */
    public net.events.cms.eo.DataSetEntry createObjectAndAddToMedicalDataSetEntries() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: medicalDataSetEntries");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("DataSetEntry");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "medicalDataSetEntries");
	    return (net.events.cms.eo.DataSetEntry) eoObject;
    }
    
    /**
     * Removes object from the relationship "medicalDataSetEntries" and delete object
     */
    public void removeFromMedicalDataSetEntriesAndDelete(net.events.cms.eo.DataSetEntry object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: medicalDataSetEntries");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "medicalDataSetEntries");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "medicalDataSetEntries", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllMedicalDataSetEntries() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: medicalDataSetEntries");
	    Enumeration objects = medicalDataSetEntries().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromMedicalDataSetEntriesAndDelete((net.events.cms.eo.DataSetEntry)objects.nextElement());
    }
}