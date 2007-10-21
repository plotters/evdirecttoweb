// _DataSetEntry.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetEntry.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetEntry extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _DataSetEntry.class );
	
	// KeyValueCoding support
	
    public static final String VALUES = "values";
    public static final String PERSON = "person";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DATASETTEMPLATE = "dataSetTemplate";
    public static final String CREATIONTIME = "creationTime";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetEntry";

	/**
	 * Standard constructor
	 */	
    public _DataSetEntry() {
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
	 * Create a "DataSetEntry" object with all required values
	 */
	public static DataSetEntry createDataSetEntry(EOEditingContext editingContext, NSTimestamp creationTime, String inheritanceType, net.events.cms.eo.Client client, net.events.cms.eo.DataSetTemplate dataSetTemplate, net.events.cms.eo.Person person) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetEntry");
		DataSetEntry eoObject = (DataSetEntry)EOUtilities.createAndInsertInstance(editingContext, _DataSetEntry.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setInheritanceType(inheritanceType);
		eoObject.setClient(client);
		eoObject.setDataSetTemplate(dataSetTemplate);
		eoObject.setPerson(person);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetEntry" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetEntry'");
		return _DataSetEntry.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetEntry" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetEntry' with sortOrderings " + _sortOrderings);
		return _DataSetEntry.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetEntry" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetEntry.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetEntry" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetEntry' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetEntry.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetEntry fetchDataSetEntryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetEntry.fetchDataSetEntryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetEntry fetchDataSetEntryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetEntry' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetEntry.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetEntry eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetEntry)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetEntry that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetEntry fetchRequiredDataSetEntryWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetEntry.fetchRequiredDataSetEntryWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetEntry fetchRequiredDataSetEntryWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetEntry' with qualifier: " + _qualifier);
		DataSetEntry eoObject = _DataSetEntry.fetchDataSetEntryWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetEntry that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetEntry localInstanceOfDataSetEntry(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetEntry': " + this.toString());
		return (DataSetEntry)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetEntry localInstanceOfDataSetEntry(EOEditingContext _editingContext, DataSetEntry _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetEntry': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetEntry)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "inheritanceType"
	 */
    public String inheritanceType() {
        return (String) storedValueForKey("inheritanceType");
    }

	/**
	 * Set the value for "inheritanceType"
	 */
    public void setInheritanceType(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating inheritanceType from "+inheritanceType()+" to "+aValue );
        takeStoredValueForKey(aValue, "inheritanceType");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.DataSetTemplate dataSetTemplate() {
        return (net.events.cms.eo.DataSetTemplate)storedValueForKey("dataSetTemplate");
    }

    public void setDataSetTemplate(net.events.cms.eo.DataSetTemplate aValue) {
        takeStoredValueForKey(aValue, "dataSetTemplate");
    }

    public net.events.cms.eo.Person person() {
        return (net.events.cms.eo.Person)storedValueForKey("person");
    }

    public void setPerson(net.events.cms.eo.Person aValue) {
        takeStoredValueForKey(aValue, "person");
    }

	/**
	 * Returns the objects for the relationship "values"
	 */
    public NSArray values() {
        return (NSArray)storedValueForKey("values");
    }

    public void setValues(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating values from "+values()+" to "+aValue );
        takeStoredValueForKey(aValue, "values");
    }

    public void addToValues(net.events.cms.eo.DataSetItemValue object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to values" );
	    includeObjectIntoPropertyWithKey(object, "values");
    }
    

    public void removeFromValues(net.events.cms.eo.DataSetItemValue object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from values" );
	    excludeObjectFromPropertyWithKey(object, "values");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.DataSetItemValue" and add it
     * to the relationship "values"
     */
    public net.events.cms.eo.DataSetItemValue createObjectAndAddToValues() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: values");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("DataSetItemValue");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "values");
	    return (net.events.cms.eo.DataSetItemValue) eoObject;
    }
    
    /**
     * Removes object from the relationship "values" and delete object
     */
    public void removeFromValuesAndDelete(net.events.cms.eo.DataSetItemValue object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: values");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "values");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "values", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllValues() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: values");
	    Enumeration objects = values().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromValuesAndDelete((net.events.cms.eo.DataSetItemValue)objects.nextElement());
    }
}