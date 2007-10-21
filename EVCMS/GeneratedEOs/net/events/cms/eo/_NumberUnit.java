// _NumberUnit.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to NumberUnit.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _NumberUnit extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _NumberUnit.class );
	
	// KeyValueCoding support
	
    public static final String UNIT = "unit";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "NumberUnit";

	/**
	 * Standard constructor
	 */	
    public _NumberUnit() {
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
	 * Create a "NumberUnit" object with all required values
	 */
	public static NumberUnit createNumberUnit(EOEditingContext editingContext, String unit) {
		if (log.isDebugEnabled()) log.debug ("Creating object: NumberUnit");
		NumberUnit eoObject = (NumberUnit)EOUtilities.createAndInsertInstance(editingContext, _NumberUnit.ENTITY_NAME);
		eoObject.setUnit(unit);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "NumberUnit" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'NumberUnit'");
		return _NumberUnit.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "NumberUnit" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'NumberUnit' with sortOrderings " + _sortOrderings);
		return _NumberUnit.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "NumberUnit" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _NumberUnit.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "NumberUnit" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'NumberUnit' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_NumberUnit.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static NumberUnit fetchNumberUnitWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _NumberUnit.fetchNumberUnitWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static NumberUnit fetchNumberUnitWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'NumberUnit' with qualifier: " + _qualifier);
		NSArray eoObjects = _NumberUnit.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		NumberUnit eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (NumberUnit)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one NumberUnit that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static NumberUnit fetchRequiredNumberUnitWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _NumberUnit.fetchRequiredNumberUnitWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static NumberUnit fetchRequiredNumberUnitWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'NumberUnit' with qualifier: " + _qualifier);
		NumberUnit eoObject = _NumberUnit.fetchNumberUnitWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no NumberUnit that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public NumberUnit localInstanceOfNumberUnit(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'NumberUnit': " + this.toString());
		return (NumberUnit)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static NumberUnit localInstanceOfNumberUnit(EOEditingContext _editingContext, NumberUnit _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'NumberUnit': " + _eo.toString());
		}
		return (_eo == null) ? null : (NumberUnit)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "unit"
	 */
    public String unit() {
        return (String) storedValueForKey("unit");
    }

	/**
	 * Set the value for "unit"
	 */
    public void setUnit(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating unit from "+unit()+" to "+aValue );
        takeStoredValueForKey(aValue, "unit");
    }
}