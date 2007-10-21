// _ClinicalTrial.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to ClinicalTrial.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _ClinicalTrial extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _ClinicalTrial.class );
	
	// KeyValueCoding support
	
    public static final String SEVERITYGRADES = "severityGrades";
    public static final String SERIOUSNESSES = "seriousnesses";
    public static final String PROTOCOLNUMBER = "protocolNumber";
    public static final String PARTICIPANTS = "participants";
    public static final String NAME = "name";
    public static final String MEDICALDATASETTEMPLATES = "medicalDataSetTemplates";
    public static final String CLIENT = "client";
    public static final String CAUSALITYASSESSMENTS = "causalityAssessments";
    public static final String ADVERSEEVENTS = "adverseEvents";
    public static final String ADVERSEEVENTOUTCOMES = "adverseEventOutcomes";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "ClinicalTrial";

	/**
	 * Standard constructor
	 */	
    public _ClinicalTrial() {
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
	 * Create a "ClinicalTrial" object with all required values
	 */
	public static ClinicalTrial createClinicalTrial(EOEditingContext editingContext, String name, String protocolNumber, net.events.cms.eo.Client client) {
		if (log.isDebugEnabled()) log.debug ("Creating object: ClinicalTrial");
		ClinicalTrial eoObject = (ClinicalTrial)EOUtilities.createAndInsertInstance(editingContext, _ClinicalTrial.ENTITY_NAME);
		eoObject.setName(name);
		eoObject.setProtocolNumber(protocolNumber);
		eoObject.setClient(client);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "ClinicalTrial" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'ClinicalTrial'");
		return _ClinicalTrial.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "ClinicalTrial" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'ClinicalTrial' with sortOrderings " + _sortOrderings);
		return _ClinicalTrial.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "ClinicalTrial" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _ClinicalTrial.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "ClinicalTrial" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'ClinicalTrial' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_ClinicalTrial.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static ClinicalTrial fetchClinicalTrialWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _ClinicalTrial.fetchClinicalTrialWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static ClinicalTrial fetchClinicalTrialWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'ClinicalTrial' with qualifier: " + _qualifier);
		NSArray eoObjects = _ClinicalTrial.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		ClinicalTrial eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (ClinicalTrial)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one ClinicalTrial that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static ClinicalTrial fetchRequiredClinicalTrialWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _ClinicalTrial.fetchRequiredClinicalTrialWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static ClinicalTrial fetchRequiredClinicalTrialWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'ClinicalTrial' with qualifier: " + _qualifier);
		ClinicalTrial eoObject = _ClinicalTrial.fetchClinicalTrialWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no ClinicalTrial that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public ClinicalTrial localInstanceOfClinicalTrial(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'ClinicalTrial': " + this.toString());
		return (ClinicalTrial)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static ClinicalTrial localInstanceOfClinicalTrial(EOEditingContext _editingContext, ClinicalTrial _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'ClinicalTrial': " + _eo.toString());
		}
		return (_eo == null) ? null : (ClinicalTrial)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "protocolNumber"
	 */
    public String protocolNumber() {
        return (String) storedValueForKey("protocolNumber");
    }

	/**
	 * Set the value for "protocolNumber"
	 */
    public void setProtocolNumber(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating protocolNumber from "+protocolNumber()+" to "+aValue );
        takeStoredValueForKey(aValue, "protocolNumber");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

	/**
	 * Returns the objects for the relationship "adverseEventOutcomes"
	 */
    public NSArray adverseEventOutcomes() {
        return (NSArray)storedValueForKey("adverseEventOutcomes");
    }

    public void setAdverseEventOutcomes(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating adverseEventOutcomes from "+adverseEventOutcomes()+" to "+aValue );
        takeStoredValueForKey(aValue, "adverseEventOutcomes");
    }

    public void addToAdverseEventOutcomes(net.events.cms.eo.AdverseEventOutcome object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to adverseEventOutcomes" );
	    includeObjectIntoPropertyWithKey(object, "adverseEventOutcomes");
    }
    

    public void removeFromAdverseEventOutcomes(net.events.cms.eo.AdverseEventOutcome object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from adverseEventOutcomes" );
	    excludeObjectFromPropertyWithKey(object, "adverseEventOutcomes");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.AdverseEventOutcome" and add it
     * to the relationship "adverseEventOutcomes"
     */
    public net.events.cms.eo.AdverseEventOutcome createObjectAndAddToAdverseEventOutcomes() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: adverseEventOutcomes");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("AdverseEventOutcome");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "adverseEventOutcomes");
	    return (net.events.cms.eo.AdverseEventOutcome) eoObject;
    }
    
    /**
     * Removes object from the relationship "adverseEventOutcomes" and delete object
     */
    public void removeFromAdverseEventOutcomesAndDelete(net.events.cms.eo.AdverseEventOutcome object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: adverseEventOutcomes");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "adverseEventOutcomes");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "adverseEventOutcomes", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllAdverseEventOutcomes() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: adverseEventOutcomes");
	    Enumeration objects = adverseEventOutcomes().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromAdverseEventOutcomesAndDelete((net.events.cms.eo.AdverseEventOutcome)objects.nextElement());
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

	/**
	 * Returns the objects for the relationship "causalityAssessments"
	 */
    public NSArray causalityAssessments() {
        return (NSArray)storedValueForKey("causalityAssessments");
    }

    public void setCausalityAssessments(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating causalityAssessments from "+causalityAssessments()+" to "+aValue );
        takeStoredValueForKey(aValue, "causalityAssessments");
    }

    public void addToCausalityAssessments(net.events.cms.eo.CausalityAssessment object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to causalityAssessments" );
	    includeObjectIntoPropertyWithKey(object, "causalityAssessments");
    }
    

    public void removeFromCausalityAssessments(net.events.cms.eo.CausalityAssessment object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from causalityAssessments" );
	    excludeObjectFromPropertyWithKey(object, "causalityAssessments");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.CausalityAssessment" and add it
     * to the relationship "causalityAssessments"
     */
    public net.events.cms.eo.CausalityAssessment createObjectAndAddToCausalityAssessments() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: causalityAssessments");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("CausalityAssessment");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "causalityAssessments");
	    return (net.events.cms.eo.CausalityAssessment) eoObject;
    }
    
    /**
     * Removes object from the relationship "causalityAssessments" and delete object
     */
    public void removeFromCausalityAssessmentsAndDelete(net.events.cms.eo.CausalityAssessment object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: causalityAssessments");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "causalityAssessments");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "causalityAssessments", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllCausalityAssessments() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: causalityAssessments");
	    Enumeration objects = causalityAssessments().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromCausalityAssessmentsAndDelete((net.events.cms.eo.CausalityAssessment)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "medicalDataSetTemplates"
	 */
    public NSArray medicalDataSetTemplates() {
        return (NSArray)storedValueForKey("medicalDataSetTemplates");
    }

    public void setMedicalDataSetTemplates(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating medicalDataSetTemplates from "+medicalDataSetTemplates()+" to "+aValue );
        takeStoredValueForKey(aValue, "medicalDataSetTemplates");
    }

    public void addToMedicalDataSetTemplates(net.events.cms.eo.MedicalDataSetTemplate object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to medicalDataSetTemplates" );
	    includeObjectIntoPropertyWithKey(object, "medicalDataSetTemplates");
    }
    

    public void removeFromMedicalDataSetTemplates(net.events.cms.eo.MedicalDataSetTemplate object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from medicalDataSetTemplates" );
	    excludeObjectFromPropertyWithKey(object, "medicalDataSetTemplates");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.MedicalDataSetTemplate" and add it
     * to the relationship "medicalDataSetTemplates"
     */
    public net.events.cms.eo.MedicalDataSetTemplate createObjectAndAddToMedicalDataSetTemplates() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: medicalDataSetTemplates");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("MedicalDataSetTemplate");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "medicalDataSetTemplates");
	    return (net.events.cms.eo.MedicalDataSetTemplate) eoObject;
    }
    
    /**
     * Removes object from the relationship "medicalDataSetTemplates" and delete object
     */
    public void removeFromMedicalDataSetTemplatesAndDelete(net.events.cms.eo.MedicalDataSetTemplate object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: medicalDataSetTemplates");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "medicalDataSetTemplates");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "medicalDataSetTemplates", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllMedicalDataSetTemplates() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: medicalDataSetTemplates");
	    Enumeration objects = medicalDataSetTemplates().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromMedicalDataSetTemplatesAndDelete((net.events.cms.eo.MedicalDataSetTemplate)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "participants"
	 */
    public NSArray participants() {
        return (NSArray)storedValueForKey("participants");
    }

    public void setParticipants(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating participants from "+participants()+" to "+aValue );
        takeStoredValueForKey(aValue, "participants");
    }

    public void addToParticipants(net.events.cms.eo.StudyParticipant object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to participants" );
	    includeObjectIntoPropertyWithKey(object, "participants");
    }
    

    public void removeFromParticipants(net.events.cms.eo.StudyParticipant object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from participants" );
	    excludeObjectFromPropertyWithKey(object, "participants");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.StudyParticipant" and add it
     * to the relationship "participants"
     */
    public net.events.cms.eo.StudyParticipant createObjectAndAddToParticipants() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: participants");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("StudyParticipant");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "participants");
	    return (net.events.cms.eo.StudyParticipant) eoObject;
    }
    
    /**
     * Removes object from the relationship "participants" and delete object
     */
    public void removeFromParticipantsAndDelete(net.events.cms.eo.StudyParticipant object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: participants");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "participants");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "participants", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllParticipants() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: participants");
	    Enumeration objects = participants().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromParticipantsAndDelete((net.events.cms.eo.StudyParticipant)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "seriousnesses"
	 */
    public NSArray seriousnesses() {
        return (NSArray)storedValueForKey("seriousnesses");
    }

    public void setSeriousnesses(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating seriousnesses from "+seriousnesses()+" to "+aValue );
        takeStoredValueForKey(aValue, "seriousnesses");
    }

    public void addToSeriousnesses(net.events.cms.eo.Seriousness object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to seriousnesses" );
	    includeObjectIntoPropertyWithKey(object, "seriousnesses");
    }
    

    public void removeFromSeriousnesses(net.events.cms.eo.Seriousness object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from seriousnesses" );
	    excludeObjectFromPropertyWithKey(object, "seriousnesses");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Seriousness" and add it
     * to the relationship "seriousnesses"
     */
    public net.events.cms.eo.Seriousness createObjectAndAddToSeriousnesses() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: seriousnesses");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Seriousness");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "seriousnesses");
	    return (net.events.cms.eo.Seriousness) eoObject;
    }
    
    /**
     * Removes object from the relationship "seriousnesses" and delete object
     */
    public void removeFromSeriousnessesAndDelete(net.events.cms.eo.Seriousness object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: seriousnesses");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "seriousnesses");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "seriousnesses", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllSeriousnesses() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: seriousnesses");
	    Enumeration objects = seriousnesses().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromSeriousnessesAndDelete((net.events.cms.eo.Seriousness)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "severityGrades"
	 */
    public NSArray severityGrades() {
        return (NSArray)storedValueForKey("severityGrades");
    }

    public void setSeverityGrades(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating severityGrades from "+severityGrades()+" to "+aValue );
        takeStoredValueForKey(aValue, "severityGrades");
    }

    public void addToSeverityGrades(net.events.cms.eo.SeverityGrade object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to severityGrades" );
	    includeObjectIntoPropertyWithKey(object, "severityGrades");
    }
    

    public void removeFromSeverityGrades(net.events.cms.eo.SeverityGrade object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from severityGrades" );
	    excludeObjectFromPropertyWithKey(object, "severityGrades");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.SeverityGrade" and add it
     * to the relationship "severityGrades"
     */
    public net.events.cms.eo.SeverityGrade createObjectAndAddToSeverityGrades() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: severityGrades");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("SeverityGrade");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "severityGrades");
	    return (net.events.cms.eo.SeverityGrade) eoObject;
    }
    
    /**
     * Removes object from the relationship "severityGrades" and delete object
     */
    public void removeFromSeverityGradesAndDelete(net.events.cms.eo.SeverityGrade object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: severityGrades");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "severityGrades");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "severityGrades", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllSeverityGrades() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: severityGrades");
	    Enumeration objects = severityGrades().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromSeverityGradesAndDelete((net.events.cms.eo.SeverityGrade)objects.nextElement());
    }
}