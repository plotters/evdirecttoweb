// _DataSetItemActionTrigger.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetItemActionTrigger.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetItemActionTrigger extends net.events.cms.eo.ActionTrigger {
	private static Logger log = Logger.getLogger( _DataSetItemActionTrigger.class );
	
	// KeyValueCoding support
	
    public static final String TRIGGERDESCRIPTION = "triggerDescription";
    public static final String PERSON = "person";
    public static final String NAME = "name";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String ACTIVE = "active";
    public static final String ACTION = "action";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetItemActionTrigger";

	/**
	 * Standard constructor
	 */	
    public _DataSetItemActionTrigger() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("DataSetItemActionTrigger", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "DataSetItemActionTrigger" object with all required values
	 */
	public static DataSetItemActionTrigger createDataSetItemActionTrigger(EOEditingContext editingContext, Boolean active, String name, net.events.cms.eo.Person person, net.events.cms.eo.Action action) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetItemActionTrigger");
		DataSetItemActionTrigger eoObject = (DataSetItemActionTrigger)EOUtilities.createAndInsertInstance(editingContext, _DataSetItemActionTrigger.ENTITY_NAME);
		eoObject.setPerson(person);
		eoObject.setActive(active);
		eoObject.setName(name);
		eoObject.setAction(action);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetItemActionTrigger" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetItemActionTrigger'");
		return _DataSetItemActionTrigger.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetItemActionTrigger" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetItemActionTrigger' with sortOrderings " + _sortOrderings);
		return _DataSetItemActionTrigger.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetItemActionTrigger" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetItemActionTrigger.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetItemActionTrigger" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetItemActionTrigger' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetItemActionTrigger.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetItemActionTrigger fetchDataSetItemActionTriggerWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetItemActionTrigger.fetchDataSetItemActionTriggerWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetItemActionTrigger fetchDataSetItemActionTriggerWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetItemActionTrigger' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetItemActionTrigger.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetItemActionTrigger eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetItemActionTrigger)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetItemActionTrigger that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetItemActionTrigger fetchRequiredDataSetItemActionTriggerWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetItemActionTrigger.fetchRequiredDataSetItemActionTriggerWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetItemActionTrigger fetchRequiredDataSetItemActionTriggerWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetItemActionTrigger' with qualifier: " + _qualifier);
		DataSetItemActionTrigger eoObject = _DataSetItemActionTrigger.fetchDataSetItemActionTriggerWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetItemActionTrigger that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetItemActionTrigger localInstanceOfDataSetItemActionTrigger(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetItemActionTrigger': " + this.toString());
		return (DataSetItemActionTrigger)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetItemActionTrigger localInstanceOfDataSetItemActionTrigger(EOEditingContext _editingContext, DataSetItemActionTrigger _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetItemActionTrigger': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetItemActionTrigger)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

    public net.events.cms.eo.Person person() {
        return (net.events.cms.eo.Person)storedValueForKey("person");
    }

    public void setPerson(net.events.cms.eo.Person aValue) {
        takeStoredValueForKey(aValue, "person");
    }
}