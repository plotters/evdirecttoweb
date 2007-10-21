// _Login.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Login.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Login extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Login.class );
	
	// KeyValueCoding support
	
    public static final String LOGINTIME = "loginTime";
    public static final String LOGINFROMIP = "loginFromIp";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Login";

	/**
	 * Standard constructor
	 */	
    public _Login() {
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
	 * Create a "Login" object with all required values
	 */
	public static Login createLogin(EOEditingContext editingContext, NSTimestamp loginTime) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Login");
		Login eoObject = (Login)EOUtilities.createAndInsertInstance(editingContext, _Login.ENTITY_NAME);
		eoObject.setLoginTime(loginTime);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Login" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Login'");
		return _Login.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Login" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Login' with sortOrderings " + _sortOrderings);
		return _Login.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Login" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Login.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Login" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Login' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Login.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Login fetchLoginWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Login.fetchLoginWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Login fetchLoginWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Login' with qualifier: " + _qualifier);
		NSArray eoObjects = _Login.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Login eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Login)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Login that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Login fetchRequiredLoginWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Login.fetchRequiredLoginWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Login fetchRequiredLoginWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Login' with qualifier: " + _qualifier);
		Login eoObject = _Login.fetchLoginWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Login that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Login localInstanceOfLogin(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Login': " + this.toString());
		return (Login)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Login localInstanceOfLogin(EOEditingContext _editingContext, Login _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Login': " + _eo.toString());
		}
		return (_eo == null) ? null : (Login)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "loginFromIp"
	 */
    public String loginFromIp() {
        return (String) storedValueForKey("loginFromIp");
    }

	/**
	 * Set the value for "loginFromIp"
	 */
    public void setLoginFromIp(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating loginFromIp from "+loginFromIp()+" to "+aValue );
        takeStoredValueForKey(aValue, "loginFromIp");
    }

	/**
	 * The value for "loginTime"
	 */
    public NSTimestamp loginTime() {
        return (NSTimestamp) storedValueForKey("loginTime");
    }

	/**
	 * Set the value for "loginTime"
	 */
    public void setLoginTime(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating loginTime from "+loginTime()+" to "+aValue );
        takeStoredValueForKey(aValue, "loginTime");
    }
}