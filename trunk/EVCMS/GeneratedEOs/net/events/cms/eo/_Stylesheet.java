// _Stylesheet.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to Stylesheet.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _Stylesheet extends net.events.cms.eo.FilesystemObject {
	private static Logger log = Logger.getLogger( _Stylesheet.class );
	
	// KeyValueCoding support
	
    public static final String SITE = "site";
    public static final String PATH = "path";
    public static final String PAGES = "pages";
    public static final String PAGEWRAPPERS = "pageWrappers";
    public static final String NAME = "name";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String CLIENT = "client";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "Stylesheet";

	/**
	 * Standard constructor
	 */	
    public _Stylesheet() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("Stylesheet", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "Stylesheet" object with all required values
	 */
	public static Stylesheet createStylesheet(EOEditingContext editingContext, String name, String path, net.events.cms.eo.Site site, net.events.cms.eo.Client client) {
		if (log.isDebugEnabled()) log.debug ("Creating object: Stylesheet");
		Stylesheet eoObject = (Stylesheet)EOUtilities.createAndInsertInstance(editingContext, _Stylesheet.ENTITY_NAME);
		eoObject.setSite(site);
		eoObject.setName(name);
		eoObject.setPath(path);
		eoObject.setClient(client);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "Stylesheet" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Stylesheet'");
		return _Stylesheet.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "Stylesheet" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Stylesheet' with sortOrderings " + _sortOrderings);
		return _Stylesheet.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "Stylesheet" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Stylesheet.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "Stylesheet" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'Stylesheet' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_Stylesheet.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static Stylesheet fetchStylesheetWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Stylesheet.fetchStylesheetWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static Stylesheet fetchStylesheetWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'Stylesheet' with qualifier: " + _qualifier);
		NSArray eoObjects = _Stylesheet.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		Stylesheet eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (Stylesheet)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one Stylesheet that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Stylesheet fetchRequiredStylesheetWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _Stylesheet.fetchRequiredStylesheetWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static Stylesheet fetchRequiredStylesheetWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'Stylesheet' with qualifier: " + _qualifier);
		Stylesheet eoObject = _Stylesheet.fetchStylesheetWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no Stylesheet that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public Stylesheet localInstanceOfStylesheet(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Stylesheet': " + this.toString());
		return (Stylesheet)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static Stylesheet localInstanceOfStylesheet(EOEditingContext _editingContext, Stylesheet _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'Stylesheet': " + _eo.toString());
		}
		return (_eo == null) ? null : (Stylesheet)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

    public net.events.cms.eo.Site site() {
        return (net.events.cms.eo.Site)storedValueForKey("site");
    }

    public void setSite(net.events.cms.eo.Site aValue) {
        takeStoredValueForKey(aValue, "site");
    }

	/**
	 * Returns the objects for the relationship "pageWrappers"
	 */
    public NSArray pageWrappers() {
        return (NSArray)storedValueForKey("pageWrappers");
    }

    public void setPageWrappers(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating pageWrappers from "+pageWrappers()+" to "+aValue );
        takeStoredValueForKey(aValue, "pageWrappers");
    }

    public void addToPageWrappers(net.events.cms.eo.PageWrapperEO object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to pageWrappers" );
	    includeObjectIntoPropertyWithKey(object, "pageWrappers");
    }
    

    public void removeFromPageWrappers(net.events.cms.eo.PageWrapperEO object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from pageWrappers" );
	    excludeObjectFromPropertyWithKey(object, "pageWrappers");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.PageWrapperEO" and add it
     * to the relationship "pageWrappers"
     */
    public net.events.cms.eo.PageWrapperEO createObjectAndAddToPageWrappers() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: pageWrappers");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("PageWrapperEO");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "pageWrappers");
	    return (net.events.cms.eo.PageWrapperEO) eoObject;
    }
    
    /**
     * Removes object from the relationship "pageWrappers" and delete object
     */
    public void removeFromPageWrappersAndDelete(net.events.cms.eo.PageWrapperEO object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: pageWrappers");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "pageWrappers");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "pageWrappers", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllPageWrappers() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: pageWrappers");
	    Enumeration objects = pageWrappers().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromPageWrappersAndDelete((net.events.cms.eo.PageWrapperEO)objects.nextElement());
    }

	/**
	 * Returns the objects for the relationship "pages"
	 */
    public NSArray pages() {
        return (NSArray)storedValueForKey("pages");
    }

    public void setPages(NSArray aValue) {
    	if( log.isDebugEnabled() ) log.debug( "updating pages from "+pages()+" to "+aValue );
        takeStoredValueForKey(aValue, "pages");
    }

    public void addToPages(net.events.cms.eo.AbstractPage object) {
        if( log.isDebugEnabled() ) log.debug( "adding "+object+" to pages" );
	    includeObjectIntoPropertyWithKey(object, "pages");
    }
    

    public void removeFromPages(net.events.cms.eo.AbstractPage object) {
        if( log.isDebugEnabled() ) log.debug( "removing "+object+" from pages" );
	    excludeObjectFromPropertyWithKey(object, "pages");
    }
	
    
    /** 
     * creates a new object "net.events.cms.eo.AbstractPage" and add it
     * to the relationship "pages"
     */
    public net.events.cms.eo.AbstractPage createObjectAndAddToPages() {
    	if (log.isDebugEnabled()) log.debug ("Creating object and adding to relationship: pages");
	    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("AbstractPage");
	    EOEnterpriseObject eoObject = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
	    editingContext().insertObject(eoObject);
	    addObjectToBothSidesOfRelationshipWithKey(eoObject, "pages");
	    return (net.events.cms.eo.AbstractPage) eoObject;
    }
    
    /**
     * Removes object from the relationship "pages" and delete object
     */
    public void removeFromPagesAndDelete(net.events.cms.eo.AbstractPage object) {
    	if (log.isDebugEnabled()) log.debug ("Deleting object " + object + "from relationship: pages");
        removeObjectFromBothSidesOfRelationshipWithKey(object, "pages");
        editingContext().deleteObject(object);
    }
    
    /**
     * Delete all objects found in the relationship "pages", be careful, this method
     * DELETES it does not only a remove!
     */
    public void deleteAllPages() {
    	if (log.isDebugEnabled()) log.debug ("Deleting all objects from relationship: pages");
	    Enumeration objects = pages().objectEnumerator();
	    while ( objects.hasMoreElements() )
	        removeFromPagesAndDelete((net.events.cms.eo.AbstractPage)objects.nextElement());
    }
}