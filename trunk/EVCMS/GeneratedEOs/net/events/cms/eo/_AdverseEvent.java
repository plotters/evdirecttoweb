// _AdverseEvent.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to AdverseEvent.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _AdverseEvent extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _AdverseEvent.class );
	
	// KeyValueCoding support
	
    public static final String TEXTDESCRIPTION = "textDescription";
    public static final String STUDYPARTICIPANT = "studyParticipant";
    public static final String SEVERITYGRADE = "severityGrade";
    public static final String SERIOUSNESS = "seriousness";
    public static final String OUTCOME = "outcome";
    public static final String EVENTDATE = "eventDate";
    public static final String DATEOFRESOLUTION = "dateOfResolution";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLINICALTRIAL = "clinicalTrial";
    public static final String CLIENT = "client";
    public static final String CAUSALITYASSESSMENT = "causalityAssessment";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "AdverseEvent";

	/**
	 * Standard constructor
	 */	
    public _AdverseEvent() {
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
	 * Create a "AdverseEvent" object with all required values
	 */
	public static AdverseEvent createAdverseEvent(EOEditingContext editingContext, NSTimestamp creationTime, NSTimestamp eventDate, String textDescription, net.events.cms.eo.CausalityAssessment causalityAssessment, net.events.cms.eo.Client client, net.events.cms.eo.ClinicalTrial clinicalTrial, net.events.cms.eo.Person createdBy, net.events.cms.eo.AdverseEventOutcome outcome, net.events.cms.eo.Seriousness seriousness, net.events.cms.eo.SeverityGrade severityGrade, net.events.cms.eo.StudyParticipant studyParticipant) {
		if (log.isDebugEnabled()) log.debug ("Creating object: AdverseEvent");
		AdverseEvent eoObject = (AdverseEvent)EOUtilities.createAndInsertInstance(editingContext, _AdverseEvent.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setEventDate(eventDate);
		eoObject.setTextDescription(textDescription);
		eoObject.setCausalityAssessment(causalityAssessment);
		eoObject.setClient(client);
		eoObject.setClinicalTrial(clinicalTrial);
		eoObject.setCreatedBy(createdBy);
		eoObject.setOutcome(outcome);
		eoObject.setSeriousness(seriousness);
		eoObject.setSeverityGrade(severityGrade);
		eoObject.setStudyParticipant(studyParticipant);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "AdverseEvent" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'AdverseEvent'");
		return _AdverseEvent.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "AdverseEvent" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'AdverseEvent' with sortOrderings " + _sortOrderings);
		return _AdverseEvent.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "AdverseEvent" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _AdverseEvent.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "AdverseEvent" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'AdverseEvent' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_AdverseEvent.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static AdverseEvent fetchAdverseEventWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _AdverseEvent.fetchAdverseEventWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static AdverseEvent fetchAdverseEventWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'AdverseEvent' with qualifier: " + _qualifier);
		NSArray eoObjects = _AdverseEvent.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		AdverseEvent eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (AdverseEvent)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one AdverseEvent that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static AdverseEvent fetchRequiredAdverseEventWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _AdverseEvent.fetchRequiredAdverseEventWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static AdverseEvent fetchRequiredAdverseEventWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'AdverseEvent' with qualifier: " + _qualifier);
		AdverseEvent eoObject = _AdverseEvent.fetchAdverseEventWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no AdverseEvent that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public AdverseEvent localInstanceOfAdverseEvent(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'AdverseEvent': " + this.toString());
		return (AdverseEvent)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static AdverseEvent localInstanceOfAdverseEvent(EOEditingContext _editingContext, AdverseEvent _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'AdverseEvent': " + _eo.toString());
		}
		return (_eo == null) ? null : (AdverseEvent)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "dateOfResolution"
	 */
    public NSTimestamp dateOfResolution() {
        return (NSTimestamp) storedValueForKey("dateOfResolution");
    }

	/**
	 * Set the value for "dateOfResolution"
	 */
    public void setDateOfResolution(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating dateOfResolution from "+dateOfResolution()+" to "+aValue );
        takeStoredValueForKey(aValue, "dateOfResolution");
    }

	/**
	 * The value for "eventDate"
	 */
    public NSTimestamp eventDate() {
        return (NSTimestamp) storedValueForKey("eventDate");
    }

	/**
	 * Set the value for "eventDate"
	 */
    public void setEventDate(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating eventDate from "+eventDate()+" to "+aValue );
        takeStoredValueForKey(aValue, "eventDate");
    }

	/**
	 * The value for "textDescription"
	 */
    public String textDescription() {
        return (String) storedValueForKey("textDescription");
    }

	/**
	 * Set the value for "textDescription"
	 */
    public void setTextDescription(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating textDescription from "+textDescription()+" to "+aValue );
        takeStoredValueForKey(aValue, "textDescription");
    }

    public net.events.cms.eo.CausalityAssessment causalityAssessment() {
        return (net.events.cms.eo.CausalityAssessment)storedValueForKey("causalityAssessment");
    }

    public void setCausalityAssessment(net.events.cms.eo.CausalityAssessment aValue) {
        takeStoredValueForKey(aValue, "causalityAssessment");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.ClinicalTrial clinicalTrial() {
        return (net.events.cms.eo.ClinicalTrial)storedValueForKey("clinicalTrial");
    }

    public void setClinicalTrial(net.events.cms.eo.ClinicalTrial aValue) {
        takeStoredValueForKey(aValue, "clinicalTrial");
    }

    public net.events.cms.eo.Person createdBy() {
        return (net.events.cms.eo.Person)storedValueForKey("createdBy");
    }

    public void setCreatedBy(net.events.cms.eo.Person aValue) {
        takeStoredValueForKey(aValue, "createdBy");
    }

    public net.events.cms.eo.AdverseEventOutcome outcome() {
        return (net.events.cms.eo.AdverseEventOutcome)storedValueForKey("outcome");
    }

    public void setOutcome(net.events.cms.eo.AdverseEventOutcome aValue) {
        takeStoredValueForKey(aValue, "outcome");
    }

    public net.events.cms.eo.Seriousness seriousness() {
        return (net.events.cms.eo.Seriousness)storedValueForKey("seriousness");
    }

    public void setSeriousness(net.events.cms.eo.Seriousness aValue) {
        takeStoredValueForKey(aValue, "seriousness");
    }

    public net.events.cms.eo.SeverityGrade severityGrade() {
        return (net.events.cms.eo.SeverityGrade)storedValueForKey("severityGrade");
    }

    public void setSeverityGrade(net.events.cms.eo.SeverityGrade aValue) {
        takeStoredValueForKey(aValue, "severityGrade");
    }

    public net.events.cms.eo.StudyParticipant studyParticipant() {
        return (net.events.cms.eo.StudyParticipant)storedValueForKey("studyParticipant");
    }

    public void setStudyParticipant(net.events.cms.eo.StudyParticipant aValue) {
        takeStoredValueForKey(aValue, "studyParticipant");
    }
}