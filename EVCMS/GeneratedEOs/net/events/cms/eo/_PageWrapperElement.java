// _PageWrapperElement.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to PageWrapperElement.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _PageWrapperElement extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _PageWrapperElement.class );
	
	// KeyValueCoding support
	
    public static final String PAGEWRAPPER = "pageWrapper";
    public static final String PAGEELEMENT = "pageElement";
    public static final String PAGEAREA = "pageArea";
    public static final String ORDERNUMBER = "orderNumber";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "PageWrapperElement";

	/**
	 * Standard constructor
	 */	
    public _PageWrapperElement() {
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
	 * Create a "PageWrapperElement" object with all required values
	 */
	public static PageWrapperElement createPageWrapperElement(EOEditingContext editingContext, Number orderNumber, String pageArea, net.events.cms.eo.PageElement pageElement, net.events.cms.eo.PageWrapper pageWrapper) {
		if (log.isDebugEnabled()) log.debug ("Creating object: PageWrapperElement");
		PageWrapperElement eoObject = (PageWrapperElement)EOUtilities.createAndInsertInstance(editingContext, _PageWrapperElement.ENTITY_NAME);
		eoObject.setOrderNumber(orderNumber);
		eoObject.setPageArea(pageArea);
		eoObject.setPageElement(pageElement);
		eoObject.setPageWrapper(pageWrapper);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "PageWrapperElement" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'PageWrapperElement'");
		return _PageWrapperElement.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "PageWrapperElement" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'PageWrapperElement' with sortOrderings " + _sortOrderings);
		return _PageWrapperElement.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "PageWrapperElement" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _PageWrapperElement.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "PageWrapperElement" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'PageWrapperElement' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_PageWrapperElement.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static PageWrapperElement fetchPageWrapperElementWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _PageWrapperElement.fetchPageWrapperElementWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static PageWrapperElement fetchPageWrapperElementWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'PageWrapperElement' with qualifier: " + _qualifier);
		NSArray eoObjects = _PageWrapperElement.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		PageWrapperElement eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (PageWrapperElement)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one PageWrapperElement that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static PageWrapperElement fetchRequiredPageWrapperElementWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _PageWrapperElement.fetchRequiredPageWrapperElementWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static PageWrapperElement fetchRequiredPageWrapperElementWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'PageWrapperElement' with qualifier: " + _qualifier);
		PageWrapperElement eoObject = _PageWrapperElement.fetchPageWrapperElementWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no PageWrapperElement that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public PageWrapperElement localInstanceOfPageWrapperElement(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'PageWrapperElement': " + this.toString());
		return (PageWrapperElement)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static PageWrapperElement localInstanceOfPageWrapperElement(EOEditingContext _editingContext, PageWrapperElement _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'PageWrapperElement': " + _eo.toString());
		}
		return (_eo == null) ? null : (PageWrapperElement)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "orderNumber"
	 */
    public Number orderNumber() {
        return (Number) storedValueForKey("orderNumber");
    }

	/**
	 * Set the value for "orderNumber"
	 */
    public void setOrderNumber(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating orderNumber from "+orderNumber()+" to "+aValue );
        takeStoredValueForKey(aValue, "orderNumber");
    }

	/**
	 * The value for "pageArea"
	 */
    public String pageArea() {
        return (String) storedValueForKey("pageArea");
    }

	/**
	 * Set the value for "pageArea"
	 */
    public void setPageArea(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating pageArea from "+pageArea()+" to "+aValue );
        takeStoredValueForKey(aValue, "pageArea");
    }

    public net.events.cms.eo.PageElement pageElement() {
        return (net.events.cms.eo.PageElement)storedValueForKey("pageElement");
    }

    public void setPageElement(net.events.cms.eo.PageElement aValue) {
        takeStoredValueForKey(aValue, "pageElement");
    }

    public net.events.cms.eo.PageWrapper pageWrapper() {
        return (net.events.cms.eo.PageWrapper)storedValueForKey("pageWrapper");
    }

    public void setPageWrapper(net.events.cms.eo.PageWrapper aValue) {
        takeStoredValueForKey(aValue, "pageWrapper");
    }
}