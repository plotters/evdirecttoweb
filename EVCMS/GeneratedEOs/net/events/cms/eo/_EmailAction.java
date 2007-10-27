// _EmailAction.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to EmailAction.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _EmailAction extends net.events.cms.eo.Action {
	private static Logger log = Logger.getLogger( _EmailAction.class );
	
	// KeyValueCoding support
	
    public static final String TOADDRESS = "toAddress";
    public static final String TEMPLATE = "template";
    public static final String SUBJECT = "subject";
    public static final String NAME = "name";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String FROMADDRESS = "fromAddress";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    public static final String ACTIONTRIGGERS = "actionTriggers";
    public static final String ACTIONDESCRIPTION = "actionDescription";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "EmailAction";

	/**
	 * Standard constructor
	 */	
    public _EmailAction() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("EmailAction", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "EmailAction" object with all required values
	 */
	public static EmailAction createEmailAction(EOEditingContext editingContext, String fromAddress, String subject, String template, String toAddress, NSTimestamp creationTime, String name, net.events.cms.eo.Client client, net.events.cms.eo.EventsUser createdBy) {
		if (log.isDebugEnabled()) log.debug ("Creating object: EmailAction");
		EmailAction eoObject = (EmailAction)EOUtilities.createAndInsertInstance(editingContext, _EmailAction.ENTITY_NAME);
		eoObject.setFromAddress(fromAddress);
		eoObject.setSubject(subject);
		eoObject.setTemplate(template);
		eoObject.setToAddress(toAddress);
		eoObject.setCreationTime(creationTime);
		eoObject.setName(name);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "EmailAction" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EmailAction'");
		return _EmailAction.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "EmailAction" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EmailAction' with sortOrderings " + _sortOrderings);
		return _EmailAction.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "EmailAction" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EmailAction.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "EmailAction" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'EmailAction' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_EmailAction.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static EmailAction fetchEmailActionWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EmailAction.fetchEmailActionWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static EmailAction fetchEmailActionWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'EmailAction' with qualifier: " + _qualifier);
		NSArray eoObjects = _EmailAction.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		EmailAction eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (EmailAction)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one EmailAction that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EmailAction fetchRequiredEmailActionWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _EmailAction.fetchRequiredEmailActionWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static EmailAction fetchRequiredEmailActionWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'EmailAction' with qualifier: " + _qualifier);
		EmailAction eoObject = _EmailAction.fetchEmailActionWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no EmailAction that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public EmailAction localInstanceOfEmailAction(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EmailAction': " + this.toString());
		return (EmailAction)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static EmailAction localInstanceOfEmailAction(EOEditingContext _editingContext, EmailAction _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'EmailAction': " + _eo.toString());
		}
		return (_eo == null) ? null : (EmailAction)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "fromAddress"
	 */
    public String fromAddress() {
        return (String) storedValueForKey("fromAddress");
    }

	/**
	 * Set the value for "fromAddress"
	 */
    public void setFromAddress(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating fromAddress from "+fromAddress()+" to "+aValue );
        takeStoredValueForKey(aValue, "fromAddress");
    }

	/**
	 * The value for "subject"
	 */
    public String subject() {
        return (String) storedValueForKey("subject");
    }

	/**
	 * Set the value for "subject"
	 */
    public void setSubject(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating subject from "+subject()+" to "+aValue );
        takeStoredValueForKey(aValue, "subject");
    }

	/**
	 * The value for "template"
	 */
    public String template() {
        return (String) storedValueForKey("template");
    }

	/**
	 * Set the value for "template"
	 */
    public void setTemplate(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating template from "+template()+" to "+aValue );
        takeStoredValueForKey(aValue, "template");
    }

	/**
	 * The value for "toAddress"
	 */
    public String toAddress() {
        return (String) storedValueForKey("toAddress");
    }

	/**
	 * Set the value for "toAddress"
	 */
    public void setToAddress(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating toAddress from "+toAddress()+" to "+aValue );
        takeStoredValueForKey(aValue, "toAddress");
    }
}