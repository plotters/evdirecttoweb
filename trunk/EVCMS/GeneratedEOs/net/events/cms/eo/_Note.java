// _Note.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Note.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Note extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _Note.class );
	
	// KeyValueCoding support
	
    public static final String NOTE = "note";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Note";

	/**
	 * Standard constructor
	 */	
    public _Note() {
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
	 * Create a "Note" object with all required values
	 */
	public static Note createNote(EOEditingContext editingContext, String inheritanceType, String note, net.events.cms.eo.Client client) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Note");
		Note eoObject = (Note)EOUtilities.createAndInsertInstance(editingContext, _Note.ENTITY_NAME);
		eoObject.setInheritanceType(inheritanceType);
		eoObject.setNote(note);
		eoObject.setClient(client);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Note" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Note'");
		return _Note.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Note" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Note' with sortOrderings " + _sortOrderings);
		return _Note.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Note" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Note.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Note" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Note' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Note.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Note fetchNoteWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Note.fetchNoteWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Note fetchNoteWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Note' with qualifier: " + _qualifier);
		NSArray eoObjects = _Note.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Note eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Note)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Note that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Note fetchRequiredNoteWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Note.fetchRequiredNoteWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Note fetchRequiredNoteWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Note' with qualifier: " + _qualifier);
		Note eoObject = _Note.fetchNoteWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Note that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Note localInstanceOfNote(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Note': " + this.toString());
		return (Note)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Note localInstanceOfNote(EOEditingContext _editingContext, Note _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Note': " + _eo.toString());
		}
		return (_eo == null) ? null : (Note)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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
	 * The value for "note"
	 */
    public String note() {
        return (String) storedValueForKey("note");
    }

	/**
	 * Set the value for "note"
	 */
    public void setNote(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating note from "+note()+" to "+aValue );
        takeStoredValueForKey(aValue, "note");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }
}