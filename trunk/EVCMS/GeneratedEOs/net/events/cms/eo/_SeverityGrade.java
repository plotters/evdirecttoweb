// _SeverityGrade.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to SeverityGrade.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _SeverityGrade extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _SeverityGrade.class );
	
	// KeyValueCoding support
	
    public static final String ORDERNUMBER = "orderNumber";
    public static final String NAME = "name";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLINICALTRIAL = "clinicalTrial";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "SeverityGrade";

	/**
	 * Standard constructor
	 */	
    public _SeverityGrade() {
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
	 * Create a "SeverityGrade" object with all required values
	 */
	public static SeverityGrade createSeverityGrade(EOEditingContext editingContext, NSTimestamp creationTime, String name, Number orderNumber, net.events.cms.eo.Client client, net.events.cms.eo.ClinicalTrial clinicalTrial, net.events.cms.eo.Person createdBy) {
		if (log.isDebugEnabled()) log.debug ("Creating object: SeverityGrade");
		SeverityGrade eoObject = (SeverityGrade)EOUtilities.createAndInsertInstance(editingContext, _SeverityGrade.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setName(name);
		eoObject.setOrderNumber(orderNumber);
		eoObject.setClient(client);
		eoObject.setClinicalTrial(clinicalTrial);
		eoObject.setCreatedBy(createdBy);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "SeverityGrade" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'SeverityGrade'");
		return _SeverityGrade.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "SeverityGrade" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'SeverityGrade' with sortOrderings " + _sortOrderings);
		return _SeverityGrade.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "SeverityGrade" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _SeverityGrade.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "SeverityGrade" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'SeverityGrade' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_SeverityGrade.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static SeverityGrade fetchSeverityGradeWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _SeverityGrade.fetchSeverityGradeWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static SeverityGrade fetchSeverityGradeWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'SeverityGrade' with qualifier: " + _qualifier);
		NSArray eoObjects = _SeverityGrade.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		SeverityGrade eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (SeverityGrade)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one SeverityGrade that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static SeverityGrade fetchRequiredSeverityGradeWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _SeverityGrade.fetchRequiredSeverityGradeWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static SeverityGrade fetchRequiredSeverityGradeWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'SeverityGrade' with qualifier: " + _qualifier);
		SeverityGrade eoObject = _SeverityGrade.fetchSeverityGradeWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no SeverityGrade that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public SeverityGrade localInstanceOfSeverityGrade(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'SeverityGrade': " + this.toString());
		return (SeverityGrade)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static SeverityGrade localInstanceOfSeverityGrade(EOEditingContext _editingContext, SeverityGrade _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'SeverityGrade': " + _eo.toString());
		}
		return (_eo == null) ? null : (SeverityGrade)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "orderNumber"
	 */
    public Number orderNumber() {
        return (Number) storedValueForKey("orderNumber");
    }

	/**
	 * Set the value for "orderNumber"
	 */
    public void setOrderNumber(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating orderNumber from "+orderNumber()+" to "+aValue );
        takeStoredValueForKey(aValue, "orderNumber");
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
}