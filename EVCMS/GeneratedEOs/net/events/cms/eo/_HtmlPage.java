// _HtmlPage.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to HtmlPage.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _HtmlPage extends net.events.cms.eo.AbstractPage {
	private static Logger log = Logger.getLogger( _HtmlPage.class );
	
	// KeyValueCoding support
	
    public static final String STYLESHEET = "stylesheet";
    public static final String SITE = "site";
    public static final String PARENTPAGE = "parentPage";
    public static final String PAGEWRAPPER = "pageWrapper";
    public static final String PAGENAME = "pageName";
    public static final String PAGEELEMENTS = "pageElements";
    public static final String PAGEDESCRIPTION = "pageDescription";
    public static final String ORDERNUMBER = "orderNumber";
    public static final String INHERITANCETYPE = "inheritanceType";
    public static final String HTMLCONTENT = "htmlContent";
    public static final String CSSCONTAINERTYPE = "cssContainerType";
    public static final String CSSCONTAINERNAME = "cssContainerName";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String COMPONENTNAME = "componentName";
    public static final String CLIENT = "client";
    public static final String CHILDPAGES = "childPages";
    public static final String ACTIVE = "active";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "HtmlPage";

	/**
	 * Standard constructor
	 */	
    public _HtmlPage() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("HtmlPage", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "HtmlPage" object with all required values
	 */
	public static HtmlPage createHtmlPage(EOEditingContext editingContext, String htmlContent, String componentName, NSTimestamp creationTime, Number orderNumber, String pageName, net.events.cms.eo.Client client, net.events.cms.eo.Person createdBy, net.events.cms.eo.PageWrapperEO pageWrapper, net.events.cms.eo.Site site) {
		if (log.isDebugEnabled()) log.debug ("Creating object: HtmlPage");
		HtmlPage eoObject = (HtmlPage)EOUtilities.createAndInsertInstance(editingContext, _HtmlPage.ENTITY_NAME);
		eoObject.setHtmlContent(htmlContent);
		eoObject.setComponentName(componentName);
		eoObject.setCreationTime(creationTime);
		eoObject.setOrderNumber(orderNumber);
		eoObject.setPageName(pageName);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		eoObject.setPageWrapper(pageWrapper);
		eoObject.setSite(site);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "HtmlPage" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'HtmlPage'");
		return _HtmlPage.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "HtmlPage" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'HtmlPage' with sortOrderings " + _sortOrderings);
		return _HtmlPage.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "HtmlPage" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _HtmlPage.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "HtmlPage" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'HtmlPage' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_HtmlPage.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static HtmlPage fetchHtmlPageWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _HtmlPage.fetchHtmlPageWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static HtmlPage fetchHtmlPageWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'HtmlPage' with qualifier: " + _qualifier);
		NSArray eoObjects = _HtmlPage.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		HtmlPage eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (HtmlPage)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one HtmlPage that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static HtmlPage fetchRequiredHtmlPageWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _HtmlPage.fetchRequiredHtmlPageWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static HtmlPage fetchRequiredHtmlPageWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'HtmlPage' with qualifier: " + _qualifier);
		HtmlPage eoObject = _HtmlPage.fetchHtmlPageWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no HtmlPage that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public HtmlPage localInstanceOfHtmlPage(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'HtmlPage': " + this.toString());
		return (HtmlPage)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static HtmlPage localInstanceOfHtmlPage(EOEditingContext _editingContext, HtmlPage _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'HtmlPage': " + _eo.toString());
		}
		return (_eo == null) ? null : (HtmlPage)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "htmlContent"
	 */
    public String htmlContent() {
        return (String) storedValueForKey("htmlContent");
    }

	/**
	 * Set the value for "htmlContent"
	 */
    public void setHtmlContent(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating htmlContent from "+htmlContent()+" to "+aValue );
        takeStoredValueForKey(aValue, "htmlContent");
    }
}