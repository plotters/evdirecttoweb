// _FilesystemObject.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to FilesystemObject.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _FilesystemObject extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _FilesystemObject.class );
	
	// KeyValueCoding support
	
    public static final String PATH = "path";
    public static final String NAME = "name";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "FilesystemObject";

	/**
	 * Standard constructor
	 */	
    public _FilesystemObject() {
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
	 * Create a "FilesystemObject" object with all required values
	 */
	public static FilesystemObject createFilesystemObject(EOEditingContext editingContext, String inheritanceType, String name, String path, net.events.cms.eo.Client client) {
		if (log.isDebugEnabled()) log.debug ("Creating object: FilesystemObject");
		FilesystemObject eoObject = (FilesystemObject)EOUtilities.createAndInsertInstance(editingContext, _FilesystemObject.ENTITY_NAME);
		eoObject.setInheritanceType(inheritanceType);
		eoObject.setName(name);
		eoObject.setPath(path);
		eoObject.setClient(client);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "FilesystemObject" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'FilesystemObject'");
		return _FilesystemObject.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "FilesystemObject" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'FilesystemObject' with sortOrderings " + _sortOrderings);
		return _FilesystemObject.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "FilesystemObject" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _FilesystemObject.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "FilesystemObject" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'FilesystemObject' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_FilesystemObject.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static FilesystemObject fetchFilesystemObjectWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _FilesystemObject.fetchFilesystemObjectWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static FilesystemObject fetchFilesystemObjectWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'FilesystemObject' with qualifier: " + _qualifier);
		NSArray eoObjects = _FilesystemObject.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		FilesystemObject eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (FilesystemObject)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one FilesystemObject that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static FilesystemObject fetchRequiredFilesystemObjectWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _FilesystemObject.fetchRequiredFilesystemObjectWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static FilesystemObject fetchRequiredFilesystemObjectWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'FilesystemObject' with qualifier: " + _qualifier);
		FilesystemObject eoObject = _FilesystemObject.fetchFilesystemObjectWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no FilesystemObject that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public FilesystemObject localInstanceOfFilesystemObject(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'FilesystemObject': " + this.toString());
		return (FilesystemObject)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static FilesystemObject localInstanceOfFilesystemObject(EOEditingContext _editingContext, FilesystemObject _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'FilesystemObject': " + _eo.toString());
		}
		return (_eo == null) ? null : (FilesystemObject)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "path"
	 */
    public String path() {
        return (String) storedValueForKey("path");
    }

	/**
	 * Set the value for "path"
	 */
    public void setPath(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating path from "+path()+" to "+aValue );
        takeStoredValueForKey(aValue, "path");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }
}