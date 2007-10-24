// _MedicalDataSetEntry.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to MedicalDataSetEntry.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _MedicalDataSetEntry extends net.events.cms.eo.DataSetEntry {
	private static Logger log = Logger.getLogger( _MedicalDataSetEntry.class );
	
	// KeyValueCoding support
	
    public static final String VALUES = "values";
    public static final String PERSON = "person";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DATE = "date";
    public static final String DATASETTEMPLATE = "dataSetTemplate";
    public static final String CREATIONTIME = "creationTime";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "MedicalDataSetEntry";

	/**
	 * Standard constructor
	 */	
    public _MedicalDataSetEntry() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("MedicalDataSetEntry", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "MedicalDataSetEntry" object with all required values
	 */
	public static MedicalDataSetEntry createMedicalDataSetEntry(EOEditingContext editingContext, NSTimestamp creationTime, net.events.cms.eo.Client client, net.events.cms.eo.DataSetTemplate dataSetTemplate, net.events.cms.eo.Person person) {
		if (log.isDebugEnabled()) log.debug ("Creating object: MedicalDataSetEntry");
		MedicalDataSetEntry eoObject = (MedicalDataSetEntry)EOUtilities.createAndInsertInstance(editingContext, _MedicalDataSetEntry.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setClient(client);
		eoObject.setDataSetTemplate(dataSetTemplate);
		eoObject.setPerson(person);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "MedicalDataSetEntry" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'MedicalDataSetEntry'");
		return _MedicalDataSetEntry.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "MedicalDataSetEntry" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'MedicalDataSetEntry' with sortOrderings " + _sortOrderings);
		return _MedicalDataSetEntry.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "MedicalDataSetEntry" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _MedicalDataSetEntry.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "MedicalDataSetEntry" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'MedicalDataSetEntry' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_MedicalDataSetEntry.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static MedicalDataSetEntry fetchMedicalDataSetEntryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _MedicalDataSetEntry.fetchMedicalDataSetEntryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static MedicalDataSetEntry fetchMedicalDataSetEntryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'MedicalDataSetEntry' with qualifier: " + _qualifier);
		NSArray eoObjects = _MedicalDataSetEntry.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		MedicalDataSetEntry eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (MedicalDataSetEntry)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one MedicalDataSetEntry that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static MedicalDataSetEntry fetchRequiredMedicalDataSetEntryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _MedicalDataSetEntry.fetchRequiredMedicalDataSetEntryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static MedicalDataSetEntry fetchRequiredMedicalDataSetEntryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'MedicalDataSetEntry' with qualifier: " + _qualifier);
		MedicalDataSetEntry eoObject = _MedicalDataSetEntry.fetchMedicalDataSetEntryWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no MedicalDataSetEntry that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public MedicalDataSetEntry localInstanceOfMedicalDataSetEntry(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'MedicalDataSetEntry': " + this.toString());
		return (MedicalDataSetEntry)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static MedicalDataSetEntry localInstanceOfMedicalDataSetEntry(EOEditingContext _editingContext, MedicalDataSetEntry _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'MedicalDataSetEntry': " + _eo.toString());
		}
		return (_eo == null) ? null : (MedicalDataSetEntry)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  
}