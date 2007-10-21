// _BlogPage.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to BlogPage.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _BlogPage extends net.events.cms.eo.AbstractPage {
	private static Logger log = Logger.getLogger( _BlogPage.class );
	
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
    public static final String ENTRIESPERPAGE = "entriesPerPage";
    public static final String CSSCONTAINERTYPE = "cssContainerType";
    public static final String CSSCONTAINERNAME = "cssContainerName";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String COMPONENTNAME = "componentName";
    public static final String CLIENT = "client";
    public static final String CHILDPAGES = "childPages";
    public static final String BLOG = "blog";
    public static final String ACTIVE = "active";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "BlogPage";

	/**
	 * Standard constructor
	 */	
    public _BlogPage() {
        super();
    }
    
    /**
     * This sets the property "inheritanceType" if exists and if the class has a parent entity.
     * This is just a convention for my database and inheritance design style. 
     */
    public void awakeFromInsertion (EOEditingContext editingContext) {
    	super.awakeFromInsertion(editingContext);
    	

    	if (this.attributeKeys().containsObject("inheritanceType")) {
    		this.takeValueForKey ("BlogPage", "inheritanceType");
    	}
    	
    }

	/**
	 * Create a "BlogPage" object with all required values
	 */
	public static BlogPage createBlogPage(EOEditingContext editingContext, Number entriesPerPage, String componentName, NSTimestamp creationTime, Number orderNumber, String pageName, net.events.cms.eo.Blog blog, net.events.cms.eo.Client client, net.events.cms.eo.Person createdBy, net.events.cms.eo.PageWrapper pageWrapper, net.events.cms.eo.Site site) {
		if (log.isDebugEnabled()) log.debug ("Creating object: BlogPage");
		BlogPage eoObject = (BlogPage)EOUtilities.createAndInsertInstance(editingContext, _BlogPage.ENTITY_NAME);
		eoObject.setEntriesPerPage(entriesPerPage);
		eoObject.setBlog(blog);
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
 	 * Fetch all "BlogPage" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogPage'");
		return _BlogPage.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "BlogPage" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogPage' with sortOrderings " + _sortOrderings);
		return _BlogPage.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "BlogPage" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogPage.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "BlogPage" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'BlogPage' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_BlogPage.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static BlogPage fetchBlogPageWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogPage.fetchBlogPageWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static BlogPage fetchBlogPageWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'BlogPage' with qualifier: " + _qualifier);
		NSArray eoObjects = _BlogPage.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		BlogPage eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (BlogPage)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one BlogPage that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static BlogPage fetchRequiredBlogPageWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _BlogPage.fetchRequiredBlogPageWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static BlogPage fetchRequiredBlogPageWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'BlogPage' with qualifier: " + _qualifier);
		BlogPage eoObject = _BlogPage.fetchBlogPageWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no BlogPage that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public BlogPage localInstanceOfBlogPage(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'BlogPage': " + this.toString());
		return (BlogPage)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static BlogPage localInstanceOfBlogPage(EOEditingContext _editingContext, BlogPage _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'BlogPage': " + _eo.toString());
		}
		return (_eo == null) ? null : (BlogPage)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "entriesPerPage"
	 */
    public Number entriesPerPage() {
        return (Number) storedValueForKey("entriesPerPage");
    }

	/**
	 * Set the value for "entriesPerPage"
	 */
    public void setEntriesPerPage(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating entriesPerPage from "+entriesPerPage()+" to "+aValue );
        takeStoredValueForKey(aValue, "entriesPerPage");
    }

    public net.events.cms.eo.Blog blog() {
        return (net.events.cms.eo.Blog)storedValueForKey("blog");
    }

    public void setBlog(net.events.cms.eo.Blog aValue) {
        takeStoredValueForKey(aValue, "blog");
    }
}