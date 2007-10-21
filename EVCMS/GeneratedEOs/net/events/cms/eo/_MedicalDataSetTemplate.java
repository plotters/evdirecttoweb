// _MedicalDataSetTemplate.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to MedicalDataSetTemplate.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _MedicalDataSetTemplate extends net.events.cms.eo.DataSetTemplate {
	private static Logger log = Logger.getLogger( _MedicalDataSetTemplate.class );
	
	// KeyValueCoding support
	
    public static final String TOPTEXT = "topText";
    public static final String TEXTFORTHANKYOUPAGE = "textForThankYouPage";
    public static final String TEXTDESCRIPTION = "textDescription";
    public static final String SECTIONS = "sections";
    public static final String PAGES = "pages";
    public static final String NAME = "name";
    public static final String LOGINREQUIRED = "loginRequired";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DATASETPAGETEMPLATE = "dataSetPageTemplate";
    public static final String DATASETITEMS = "dataSetItems";
    public static final String DATASETENTRIES = "dataSetEntries";
    public static final String CSSCLASSFORTHANKYOUMESSAGE = "cssClassForThankYouMessage";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLINICALTRIAL = "clinicalTrial";
    public static final String CLIENT = "client";
    public static final String BOTTOMTEXT = "bottomText";
    public static final String ACTIVE = "active";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "MedicalDataSetTemplate";

	/**
	 * Standard constructor
	 */	
    public _MedicalDataSetTemplate() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("MedicalDataSetTemplate", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "MedicalDataSetTemplate" object with all required values
	 */
	public static MedicalDataSetTemplate createMedicalDataSetTemplate(EOEditingContext editingContext, Boolean active, NSTimestamp creationTime, String name, net.events.cms.eo.ClinicalTrial clinicalTrial, net.events.cms.eo.Client client, net.events.cms.eo.Person createdBy) {
		if (log.isDebugEnabled()) log.debug ("Creating object: MedicalDataSetTemplate");
		MedicalDataSetTemplate eoObject = (MedicalDataSetTemplate)EOUtilities.createAndInsertInstance(editingContext, _MedicalDataSetTemplate.ENTITY_NAME);
		eoObject.setClinicalTrial(clinicalTrial);
		eoObject.setActive(active);
		eoObject.setCreationTime(creationTime);
		eoObject.setName(name);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "MedicalDataSetTemplate" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'MedicalDataSetTemplate'");
		return _MedicalDataSetTemplate.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "MedicalDataSetTemplate" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'MedicalDataSetTemplate' with sortOrderings " + _sortOrderings);
		return _MedicalDataSetTemplate.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "MedicalDataSetTemplate" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _MedicalDataSetTemplate.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "MedicalDataSetTemplate" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'MedicalDataSetTemplate' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_MedicalDataSetTemplate.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static MedicalDataSetTemplate fetchMedicalDataSetTemplateWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _MedicalDataSetTemplate.fetchMedicalDataSetTemplateWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static MedicalDataSetTemplate fetchMedicalDataSetTemplateWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'MedicalDataSetTemplate' with qualifier: " + _qualifier);
		NSArray eoObjects = _MedicalDataSetTemplate.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		MedicalDataSetTemplate eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (MedicalDataSetTemplate)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one MedicalDataSetTemplate that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static MedicalDataSetTemplate fetchRequiredMedicalDataSetTemplateWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _MedicalDataSetTemplate.fetchRequiredMedicalDataSetTemplateWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static MedicalDataSetTemplate fetchRequiredMedicalDataSetTemplateWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'MedicalDataSetTemplate' with qualifier: " + _qualifier);
		MedicalDataSetTemplate eoObject = _MedicalDataSetTemplate.fetchMedicalDataSetTemplateWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no MedicalDataSetTemplate that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public MedicalDataSetTemplate localInstanceOfMedicalDataSetTemplate(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'MedicalDataSetTemplate': " + this.toString());
		return (MedicalDataSetTemplate)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static MedicalDataSetTemplate localInstanceOfMedicalDataSetTemplate(EOEditingContext _editingContext, MedicalDataSetTemplate _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'MedicalDataSetTemplate': " + _eo.toString());
		}
		return (_eo == null) ? null : (MedicalDataSetTemplate)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

    public net.events.cms.eo.ClinicalTrial clinicalTrial() {
        return (net.events.cms.eo.ClinicalTrial)storedValueForKey("clinicalTrial");
    }

    public void setClinicalTrial(net.events.cms.eo.ClinicalTrial aValue) {
        takeStoredValueForKey(aValue, "clinicalTrial");
    }
}