// _Company.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Company.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Company extends net.events.cms.eo.Contact {
	private static Logger log = Logger.getLogger( _Company.class );
	
	// KeyValueCoding support
	
    public static final String NOTES = "notes";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String DEPARTMENTS = "departments";
    public static final String DELETED = "deleted";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    public static final String ADDRESSES = "addresses";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Company";

	/**
	 * Standard constructor
	 */	
    public _Company() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("Company", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "Company" object with all required values
	 */
	public static Company createCompany(EOEditingContext editingContext, NSTimestamp creationTime, Boolean deleted) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Company");
		Company eoObject = (Company)EOUtilities.createAndInsertInstance(editingContext, _Company.ENTITY_NAME);
		eoObject.setCreationTime(creationTime);
		eoObject.setDeleted(deleted);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Company" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Company'");
		return _Company.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Company" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Company' with sortOrderings " + _sortOrderings);
		return _Company.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Company" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Company.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Company" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Company' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Company.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Company fetchCompanyWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Company.fetchCompanyWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Company fetchCompanyWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Company' with qualifier: " + _qualifier);
		NSArray eoObjects = _Company.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Company eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Company)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Company that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Company fetchRequiredCompanyWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Company.fetchRequiredCompanyWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Company fetchRequiredCompanyWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Company' with qualifier: " + _qualifier);
		Company eoObject = _Company.fetchCompanyWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Company that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Company localInstanceOfCompany(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Company': " + this.toString());
		return (Company)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Company localInstanceOfCompany(EOEditingContext _editingContext, Company _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Company': " + _eo.toString());
		}
		return (_eo == null) ? null : (Company)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * Returns the objects for the relationship "departments"
	 */
    public NSArray departments() {
        return (NSArray)storedValueForKey("departments");
    }

    public void setDepartments(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating departments from "+departments()+" to "+aValue );
        takeStoredValueForKey(aValue, "departments");
    }

    public void addToDepartments(net.events.cms.eo.Department object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to departments" );
	    includeObjectIntoPropertyWithKey(object, "departments");
    }
    

    public void removeFromDepartments(net.events.cms.eo.Department object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from departments" );
	    excludeObjectFromPropertyWithKey(object, "departments");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.Department" and add it
     * to the relationship "departments"
     */
    public net.events.cms.eo.Department createObjectAndAddToDepartments() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: departments");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Department");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "departments");
	    return (net.events.cms.eo.Department) eoObject;
    }
    
    /**
     * Removes object from the relationship "departments" and delete object
     */
    public void removeFromDepartmentsAndDelete(net.events.cms.eo.Department object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: departments");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "departments");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "departments", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllDepartments() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: departments");
	    Enumeration objects = departments().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromDepartmentsAndDelete((net.events.cms.eo.Department)objects.nextElement());
    }
}