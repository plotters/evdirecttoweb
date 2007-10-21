// _PageElementOnPage.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to PageElementOnPage.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _PageElementOnPage extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _PageElementOnPage.class );
	
	// KeyValueCoding support
	
    public static final String PAGEELEMENT = "pageElement";
    public static final String ORDERNUMBER = "orderNumber";
    public static final String CLIENT = "client";
    public static final String ABSTRACTPAGE = "abstractPage";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "PageElementOnPage";

	/**
	 * Standard constructor
	 */	
    public _PageElementOnPage() {
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
	 * Create a "PageElementOnPage" object with all required values
	 */
	public static PageElementOnPage createPageElementOnPage(EOEditingContext editingContext, Number orderNumber, net.events.cms.eo.AbstractPage abstractPage, net.events.cms.eo.Client client, net.events.cms.eo.PageElement pageElement) {
		if (log.isDebugEnabled()) log.debug ("Creating object: PageElementOnPage");
		PageElementOnPage eoObject = (PageElementOnPage)EOUtilities.createAndInsertInstance(editingContext, _PageElementOnPage.ENTITY_NAME);
		eoObject.setOrderNumber(orderNumber);
		eoObject.setAbstractPage(abstractPage);
		eoObject.setClient(client);
		eoObject.setPageElement(pageElement);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "PageElementOnPage" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'PageElementOnPage'");
		return _PageElementOnPage.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "PageElementOnPage" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'PageElementOnPage' with sortOrderings " + _sortOrderings);
		return _PageElementOnPage.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "PageElementOnPage" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _PageElementOnPage.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "PageElementOnPage" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'PageElementOnPage' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_PageElementOnPage.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static PageElementOnPage fetchPageElementOnPageWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _PageElementOnPage.fetchPageElementOnPageWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static PageElementOnPage fetchPageElementOnPageWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'PageElementOnPage' with qualifier: " + _qualifier);
		NSArray eoObjects = _PageElementOnPage.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		PageElementOnPage eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (PageElementOnPage)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one PageElementOnPage that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static PageElementOnPage fetchRequiredPageElementOnPageWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _PageElementOnPage.fetchRequiredPageElementOnPageWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static PageElementOnPage fetchRequiredPageElementOnPageWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'PageElementOnPage' with qualifier: " + _qualifier);
		PageElementOnPage eoObject = _PageElementOnPage.fetchPageElementOnPageWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no PageElementOnPage that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public PageElementOnPage localInstanceOfPageElementOnPage(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'PageElementOnPage': " + this.toString());
		return (PageElementOnPage)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static PageElementOnPage localInstanceOfPageElementOnPage(EOEditingContext _editingContext, PageElementOnPage _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'PageElementOnPage': " + _eo.toString());
		}
		return (_eo == null) ? null : (PageElementOnPage)EOUtilities.localInstanceOfObject(_editingContext, _eo);
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

    public net.events.cms.eo.AbstractPage abstractPage() {
        return (net.events.cms.eo.AbstractPage)storedValueForKey("abstractPage");
    }

    public void setAbstractPage(net.events.cms.eo.AbstractPage aValue) {
        takeStoredValueForKey(aValue, "abstractPage");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.PageElement pageElement() {
        return (net.events.cms.eo.PageElement)storedValueForKey("pageElement");
    }

    public void setPageElement(net.events.cms.eo.PageElement aValue) {
        takeStoredValueForKey(aValue, "pageElement");
    }
}