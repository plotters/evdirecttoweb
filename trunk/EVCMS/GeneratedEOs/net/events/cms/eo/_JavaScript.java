// _JavaScript.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to JavaScript.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _JavaScript extends net.events.cms.eo.FilesystemObject {
	private static Logger log = Logger.getLogger( _JavaScript.class );
	
	// KeyValueCoding support
	
    public static final String PATH = "path";
    public static final String NAME = "name";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "JavaScript";

	/**
	 * Standard constructor
	 */	
    public _JavaScript() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("JavaScript", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "JavaScript" object with all required values
	 */
	public static JavaScript createJavaScript(EOEditingContext editingContext, String name, String path, net.events.cms.eo.Client client) {
		if (log.isDebugEnabled()) log.debug ("Creating object: JavaScript");
		JavaScript eoObject = (JavaScript)EOUtilities.createAndInsertInstance(editingContext, _JavaScript.ENTITY_NAME);
		eoObject.setName(name);
		eoObject.setPath(path);
		eoObject.setClient(client);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "JavaScript" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'JavaScript'");
		return _JavaScript.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "JavaScript" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'JavaScript' with sortOrderings " + _sortOrderings);
		return _JavaScript.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "JavaScript" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _JavaScript.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "JavaScript" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'JavaScript' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_JavaScript.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static JavaScript fetchJavaScriptWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _JavaScript.fetchJavaScriptWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static JavaScript fetchJavaScriptWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'JavaScript' with qualifier: " + _qualifier);
		NSArray eoObjects = _JavaScript.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		JavaScript eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (JavaScript)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one JavaScript that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static JavaScript fetchRequiredJavaScriptWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _JavaScript.fetchRequiredJavaScriptWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static JavaScript fetchRequiredJavaScriptWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'JavaScript' with qualifier: " + _qualifier);
		JavaScript eoObject = _JavaScript.fetchJavaScriptWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no JavaScript that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public JavaScript localInstanceOfJavaScript(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'JavaScript': " + this.toString());
		return (JavaScript)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static JavaScript localInstanceOfJavaScript(EOEditingContext _editingContext, JavaScript _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'JavaScript': " + _eo.toString());
		}
		return (_eo == null) ? null : (JavaScript)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  
}