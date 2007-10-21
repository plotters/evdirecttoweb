// _StudyParticipant.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to StudyParticipant.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _StudyParticipant extends net.events.cms.eo.Person {
	private static Logger log = Logger.getLogger( _StudyParticipant.class );
	
	// KeyValueCoding support
	
    public static final String WEIGHT = "weight";
    public static final String USERGROUP = "usergroup";
    public static final String TITLE = "title";
    public static final String STATUS = "status";
    public static final String PASSWORDATTEMPTS = "passwordAttempts";
    public static final String PASSWORD = "password";
    public static final String PARTICIPANTID = "participantId";
    public static final String OTHERETHNICGROUP = "otherEthnicGroup";
    public static final String NOTES = "notes";
    public static final String MIDDLENAME = "middlename";
    public static final String MEDICALDATASETENTRIES = "medicalDataSetEntries";
    public static final String LOGIN = "login";
    public static final String LASTNAME = "lastname";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String HEIGHT = "height";
    public static final String GENDER = "gender";
    public static final String FIRSTNAME = "firstname";
    public static final String ETHNICGROUP = "ethnicGroup";
    public static final String EMAIL = "email";
    public static final String DELETED = "deleted";
    public static final String DATEOFBIRTH = "dateOfBirth";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String COMPANY = "company";
    public static final String CLINICALTRIAL = "clinicalTrial";
    public static final String CLIENT = "client";
    public static final String ADVERSEEVENTS = "adverseEvents";
    public static final String ADDRESSES = "addresses";
    public static final String ACADEMICTITLE = "academicTitle";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "StudyParticipant";

	/**
	 * Standard constructor
	 */	
    public _StudyParticipant() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("StudyParticipant", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "StudyParticipant" object with all required values
	 */
	public static StudyParticipant createStudyParticipant(EOEditingContext editingContext, String participantId, String firstname, String lastname, String login, String password, Number passwordAttempts, net.events.cms.eo.ClinicalTrial clinicalTrial, net.events.cms.eo.Gender gender) {
		if (log.isDebugEnabled()) log.debug ("Creating object: StudyParticipant");
		StudyParticipant eoObject = (StudyParticipant)EOUtilities.createAndInsertInstance(editingContext, _StudyParticipant.ENTITY_NAME);
		eoObject.setParticipantId(participantId);
		eoObject.setClinicalTrial(clinicalTrial);
		eoObject.setFirstname(firstname);
		eoObject.setLastname(lastname);
		eoObject.setLogin(login);
		eoObject.setPassword(password);
		eoObject.setPasswordAttempts(passwordAttempts);
		eoObject.setGender(gender);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "StudyParticipant" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'StudyParticipant'");
		return _StudyParticipant.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "StudyParticipant" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'StudyParticipant' with sortOrderings " + _sortOrderings);
		return _StudyParticipant.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "StudyParticipant" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _StudyParticipant.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "StudyParticipant" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'StudyParticipant' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_StudyParticipant.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static StudyParticipant fetchStudyParticipantWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _StudyParticipant.fetchStudyParticipantWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static StudyParticipant fetchStudyParticipantWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'StudyParticipant' with qualifier: " + _qualifier);
		NSArray eoObjects = _StudyParticipant.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		StudyParticipant eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (StudyParticipant)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one StudyParticipant that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static StudyParticipant fetchRequiredStudyParticipantWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _StudyParticipant.fetchRequiredStudyParticipantWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static StudyParticipant fetchRequiredStudyParticipantWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'StudyParticipant' with qualifier: " + _qualifier);
		StudyParticipant eoObject = _StudyParticipant.fetchStudyParticipantWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no StudyParticipant that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public StudyParticipant localInstanceOfStudyParticipant(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'StudyParticipant': " + this.toString());
		return (StudyParticipant)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static StudyParticipant localInstanceOfStudyParticipant(EOEditingContext _editingContext, StudyParticipant _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'StudyParticipant': " + _eo.toString());
		}
		return (_eo == null) ? null : (StudyParticipant)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "ethnicGroup"
	 */
    public String ethnicGroup() {
        return (String) storedValueForKey("ethnicGroup");
    }

	/**
	 * Set the value for "ethnicGroup"
	 */
    public void setEthnicGroup(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating ethnicGroup from "+ethnicGroup()+" to "+aValue );
        takeStoredValueForKey(aValue, "ethnicGroup");
    }

	/**
	 * The value for "height"
	 */
    public Number height() {
        return (Number) storedValueForKey("height");
    }

	/**
	 * Set the value for "height"
	 */
    public void setHeight(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating height from "+height()+" to "+aValue );
        takeStoredValueForKey(aValue, "height");
    }

	/**
	 * The value for "otherEthnicGroup"
	 */
    public String otherEthnicGroup() {
        return (String) storedValueForKey("otherEthnicGroup");
    }

	/**
	 * Set the value for "otherEthnicGroup"
	 */
    public void setOtherEthnicGroup(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating otherEthnicGroup from "+otherEthnicGroup()+" to "+aValue );
        takeStoredValueForKey(aValue, "otherEthnicGroup");
    }

	/**
	 * The value for "participantId"
	 */
    public String participantId() {
        return (String) storedValueForKey("participantId");
    }

	/**
	 * Set the value for "participantId"
	 */
    public void setParticipantId(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating participantId from "+participantId()+" to "+aValue );
        takeStoredValueForKey(aValue, "participantId");
    }

	/**
	 * The value for "status"
	 */
    public String status() {
        return (String) storedValueForKey("status");
    }

	/**
	 * Set the value for "status"
	 */
    public void setStatus(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating status from "+status()+" to "+aValue );
        takeStoredValueForKey(aValue, "status");
    }

	/**
	 * The value for "weight"
	 */
    public BigDecimal weight() {
        return (BigDecimal) storedValueForKey("weight");
    }

	/**
	 * Set the value for "weight"
	 */
    public void setWeight(BigDecimal aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating weight from "+weight()+" to "+aValue );
        takeStoredValueForKey(aValue, "weight");
    }

    public net.events.cms.eo.ClinicalTrial clinicalTrial() {
        return (net.events.cms.eo.ClinicalTrial)storedValueForKey("clinicalTrial");
    }

    public void setClinicalTrial(net.events.cms.eo.ClinicalTrial aValue) {
        takeStoredValueForKey(aValue, "clinicalTrial");
    }

	/**
	 * Returns the objects for the relationship "adverseEvents"
	 */
    public NSArray adverseEvents() {
        return (NSArray)storedValueForKey("adverseEvents");
    }

    public void setAdverseEvents(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating adverseEvents from "+adverseEvents()+" to "+aValue );
        takeStoredValueForKey(aValue, "adverseEvents");
    }

    public void addToAdverseEvents(net.events.cms.eo.AdverseEvent object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to adverseEvents" );
	    includeObjectIntoPropertyWithKey(object, "adverseEvents");
    }
    

    public void removeFromAdverseEvents(net.events.cms.eo.AdverseEvent object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from adverseEvents" );
	    excludeObjectFromPropertyWithKey(object, "adverseEvents");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.AdverseEvent" and add it
     * to the relationship "adverseEvents"
     */
    public net.events.cms.eo.AdverseEvent createObjectAndAddToAdverseEvents() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: adverseEvents");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("AdverseEvent");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "adverseEvents");
	    return (net.events.cms.eo.AdverseEvent) eoObject;
    }
    
    /**
     * Removes object from the relationship "adverseEvents" and delete object
     */
    public void removeFromAdverseEventsAndDelete(net.events.cms.eo.AdverseEvent object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: adverseEvents");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "adverseEvents");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "adverseEvents", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllAdverseEvents() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: adverseEvents");
	    Enumeration objects = adverseEvents().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromAdverseEventsAndDelete((net.events.cms.eo.AdverseEvent)objects.nextElement());
    }
}