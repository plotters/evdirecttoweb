// _InterfaceElementType.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to InterfaceElementType.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _InterfaceElementType extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _InterfaceElementType.class );
	
	// KeyValueCoding support
	
    public static final String ELEMENTNAME = "elementName";
    public static final String COMPONENTNAME = "componentName";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "InterfaceElementType";

	/**
	 * Standard constructor
	 */	
    public _InterfaceElementType() {
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
	 * Create a "InterfaceElementType" object with all required values
	 */
	public static InterfaceElementType createInterfaceElementType(EOEditingContext editingContext, String componentName, String elementName) {
		if (log.isDebugEnabled()) log.debug ("Creating object: InterfaceElementType");
		InterfaceElementType eoObject = (InterfaceElementType)EOUtilities.createAndInsertInstance(editingContext, _InterfaceElementType.ENTITY_NAME);
		eoObject.setComponentName(componentName);
		eoObject.setElementName(elementName);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "InterfaceElementType" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'InterfaceElementType'");
		return _InterfaceElementType.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "InterfaceElementType" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'InterfaceElementType' with sortOrderings " + _sortOrderings);
		return _InterfaceElementType.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "InterfaceElementType" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _InterfaceElementType.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "InterfaceElementType" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'InterfaceElementType' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_InterfaceElementType.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static InterfaceElementType fetchInterfaceElementTypeWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _InterfaceElementType.fetchInterfaceElementTypeWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static InterfaceElementType fetchInterfaceElementTypeWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'InterfaceElementType' with qualifier: " + _qualifier);
		NSArray eoObjects = _InterfaceElementType.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		InterfaceElementType eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (InterfaceElementType)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one InterfaceElementType that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static InterfaceElementType fetchRequiredInterfaceElementTypeWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _InterfaceElementType.fetchRequiredInterfaceElementTypeWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static InterfaceElementType fetchRequiredInterfaceElementTypeWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'InterfaceElementType' with qualifier: " + _qualifier);
		InterfaceElementType eoObject = _InterfaceElementType.fetchInterfaceElementTypeWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no InterfaceElementType that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public InterfaceElementType localInstanceOfInterfaceElementType(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'InterfaceElementType': " + this.toString());
		return (InterfaceElementType)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static InterfaceElementType localInstanceOfInterfaceElementType(EOEditingContext _editingContext, InterfaceElementType _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'InterfaceElementType': " + _eo.toString());
		}
		return (_eo == null) ? null : (InterfaceElementType)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "componentName"
	 */
    public String componentName() {
        return (String) storedValueForKey("componentName");
    }

	/**
	 * Set the value for "componentName"
	 */
    public void setComponentName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating componentName from "+componentName()+" to "+aValue );
        takeStoredValueForKey(aValue, "componentName");
    }

	/**
	 * The value for "elementName"
	 */
    public String elementName() {
        return (String) storedValueForKey("elementName");
    }

	/**
	 * Set the value for "elementName"
	 */
    public void setElementName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating elementName from "+elementName()+" to "+aValue );
        takeStoredValueForKey(aValue, "elementName");
    }

	/**
	 * Gets the objects for the fetchspec "ToOneUserInterfaceElementsFS" and bindings
	 */
	public static NSArray fetchWithToOneUserInterfaceElementsFS( EOEditingContext ec ) {
		NSMutableDictionary	bindings = new NSMutableDictionary();
			
		if (bindings.allKeys() == null || bindings.allKeys().count() == 0) {
			bindings = null;
		}
		
		return EOUtilities.objectsWithFetchSpecificationAndBindings(
			ec,
			"InterfaceElementType",
			"ToOneUserInterfaceElementsFS",
			bindings );
	}
	
	/**
	 * fetches ONE object for the fetchspec ToOneUserInterfaceElementsFS
	 */
	public static InterfaceElementType fetchOneWithToOneUserInterfaceElementsFS( EOEditingContext ec ) {
		InterfaceElementType	result = null;
		NSMutableDictionary	bindings = new NSMutableDictionary();
			
		if (bindings.allKeys() == null || bindings.allKeys().count() == 0) {
			bindings = null;
		}
		try {
			result = (InterfaceElementType) EOUtilities.objectWithFetchSpecificationAndBindings(
				ec,
				"InterfaceElementType",
				"ToOneUserInterfaceElementsFS",
				bindings );
		} catch( EOObjectNotAvailableException e ) {}
		
		return result;
	}
}