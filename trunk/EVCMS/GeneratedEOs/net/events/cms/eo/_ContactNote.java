// _ContactNote.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to ContactNote.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _ContactNote extends net.events.cms.eo.Note {
	private static Logger log = Logger.getLogger( _ContactNote.class );
	
	// KeyValueCoding support
	
    public static final String NOTE = "note";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String CONTACT = "contact";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "ContactNote";

	/**
	 * Standard constructor
	 */	
    public _ContactNote() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("ContactNote", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "ContactNote" object with all required values
	 */
	public static ContactNote createContactNote(EOEditingContext editingContext, String note, net.events.cms.eo.Contact contact, net.events.cms.eo.Client client) {
		if (log.isDebugEnabled()) log.debug ("Creating object: ContactNote");
		ContactNote eoObject = (ContactNote)EOUtilities.createAndInsertInstance(editingContext, _ContactNote.ENTITY_NAME);
		eoObject.setContact(contact);
		eoObject.setNote(note);
		eoObject.setClient(client);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "ContactNote" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'ContactNote'");
		return _ContactNote.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "ContactNote" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'ContactNote' with sortOrderings " + _sortOrderings);
		return _ContactNote.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "ContactNote" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _ContactNote.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "ContactNote" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'ContactNote' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_ContactNote.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static ContactNote fetchContactNoteWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _ContactNote.fetchContactNoteWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static ContactNote fetchContactNoteWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'ContactNote' with qualifier: " + _qualifier);
		NSArray eoObjects = _ContactNote.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		ContactNote eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (ContactNote)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one ContactNote that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static ContactNote fetchRequiredContactNoteWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _ContactNote.fetchRequiredContactNoteWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static ContactNote fetchRequiredContactNoteWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'ContactNote' with qualifier: " + _qualifier);
		ContactNote eoObject = _ContactNote.fetchContactNoteWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no ContactNote that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public ContactNote localInstanceOfContactNote(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'ContactNote': " + this.toString());
		return (ContactNote)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static ContactNote localInstanceOfContactNote(EOEditingContext _editingContext, ContactNote _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'ContactNote': " + _eo.toString());
		}
		return (_eo == null) ? null : (ContactNote)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

    public net.events.cms.eo.Contact contact() {
        return (net.events.cms.eo.Contact)storedValueForKey("contact");
    }

    public void setContact(net.events.cms.eo.Contact aValue) {
        takeStoredValueForKey(aValue, "contact");
    }
}