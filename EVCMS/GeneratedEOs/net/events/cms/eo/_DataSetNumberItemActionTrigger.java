// _DataSetNumberItemActionTrigger.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetNumberItemActionTrigger.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetNumberItemActionTrigger extends net.events.cms.eo.DataSetItemActionTrigger {
	private static Logger log = Logger.getLogger( _DataSetNumberItemActionTrigger.class );
	
	// KeyValueCoding support
	
    public static final String TRIGGERVALUE = "triggerValue";
    public static final String TRIGGERDESCRIPTION = "triggerDescription";
    public static final String PERSON = "person";
    public static final String NAME = "name";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DATASETNUMBERITEM = "dataSetNumberItem";
    public static final String CREATEDBY = "createdBy";
    public static final String COMPARATOR = "comparator";
    public static final String CLIENT = "client";
    public static final String ACTIVE = "active";
    public static final String ACTIONTRIGGERLOGS = "actionTriggerLogs";
    public static final String ACTION = "action";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetNumberItemActionTrigger";

	/**
	 * Standard constructor
	 */	
    public _DataSetNumberItemActionTrigger() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("DataSetNumberItemActionTrigger", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "DataSetNumberItemActionTrigger" object with all required values
	 */
	public static DataSetNumberItemActionTrigger createDataSetNumberItemActionTrigger(EOEditingContext editingContext, String comparator, Number triggerValue, net.events.cms.eo.DataSetNumberItem dataSetNumberItem, net.events.cms.eo.Person person) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetNumberItemActionTrigger");
		DataSetNumberItemActionTrigger eoObject = (DataSetNumberItemActionTrigger)EOUtilities.createAndInsertInstance(editingContext, _DataSetNumberItemActionTrigger.ENTITY_NAME);
		eoObject.setComparator(comparator);
		eoObject.setTriggerValue(triggerValue);
		eoObject.setDataSetNumberItem(dataSetNumberItem);
		eoObject.setPerson(person);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetNumberItemActionTrigger" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetNumberItemActionTrigger'");
		return _DataSetNumberItemActionTrigger.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetNumberItemActionTrigger" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetNumberItemActionTrigger' with sortOrderings " + _sortOrderings);
		return _DataSetNumberItemActionTrigger.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetNumberItemActionTrigger" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetNumberItemActionTrigger.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetNumberItemActionTrigger" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetNumberItemActionTrigger' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetNumberItemActionTrigger.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetNumberItemActionTrigger fetchDataSetNumberItemActionTriggerWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetNumberItemActionTrigger.fetchDataSetNumberItemActionTriggerWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetNumberItemActionTrigger fetchDataSetNumberItemActionTriggerWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetNumberItemActionTrigger' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetNumberItemActionTrigger.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetNumberItemActionTrigger eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetNumberItemActionTrigger)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetNumberItemActionTrigger that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetNumberItemActionTrigger fetchRequiredDataSetNumberItemActionTriggerWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetNumberItemActionTrigger.fetchRequiredDataSetNumberItemActionTriggerWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetNumberItemActionTrigger fetchRequiredDataSetNumberItemActionTriggerWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetNumberItemActionTrigger' with qualifier: " + _qualifier);
		DataSetNumberItemActionTrigger eoObject = _DataSetNumberItemActionTrigger.fetchDataSetNumberItemActionTriggerWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetNumberItemActionTrigger that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetNumberItemActionTrigger localInstanceOfDataSetNumberItemActionTrigger(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetNumberItemActionTrigger': " + this.toString());
		return (DataSetNumberItemActionTrigger)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetNumberItemActionTrigger localInstanceOfDataSetNumberItemActionTrigger(EOEditingContext _editingContext, DataSetNumberItemActionTrigger _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetNumberItemActionTrigger': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetNumberItemActionTrigger)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "comparator"
	 */
    public String comparator() {
        return (String) storedValueForKey("comparator");
    }

	/**
	 * Set the value for "comparator"
	 */
    public void setComparator(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating comparator from "+comparator()+" to "+aValue );
        takeStoredValueForKey(aValue, "comparator");
    }

	/**
	 * The value for "triggerValue"
	 */
    public Number triggerValue() {
        return (Number) storedValueForKey("triggerValue");
    }

	/**
	 * Set the value for "triggerValue"
	 */
    public void setTriggerValue(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating triggerValue from "+triggerValue()+" to "+aValue );
        takeStoredValueForKey(aValue, "triggerValue");
    }

    public net.events.cms.eo.DataSetNumberItem dataSetNumberItem() {
        return (net.events.cms.eo.DataSetNumberItem)storedValueForKey("dataSetNumberItem");
    }

    public void setDataSetNumberItem(net.events.cms.eo.DataSetNumberItem aValue) {
        takeStoredValueForKey(aValue, "dataSetNumberItem");
    }
}