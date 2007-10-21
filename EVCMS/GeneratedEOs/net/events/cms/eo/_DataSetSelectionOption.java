// _DataSetSelectionOption.java
// 
// Created by eogenerator/JavaSourceEOF52.eotemplate
// DO NOT EDIT.  Make changes to DataSetSelectionOption.java instead.
package net.events.cms.eo;


import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import er.extensions.*;
import org.apache.log4j.Logger;
import net.events.eof.*;

public abstract class _DataSetSelectionOption extends net.events.cms.eo.EVCMSGenericRecord {
	private static Logger log = Logger.getLogger( _DataSetSelectionOption.class );
	
	// KeyValueCoding support
	
    public static final String SCORE = "score";
    public static final String ORDERNUMBER = "orderNumber";
    public static final String OPTIONNAME = "optionName";
    public static final String DATASETITEM = "dataSetItem";
    public static final String CREATIONTIME = "creationTime";
    public static final String CREATEDBY = "createdBy";
    public static final String CLIENT = "client";
    public static final String ACTIVE = "active";
    
    /**
     * For KeyValueCoding support
     */
    public static final String ENTITY_NAME = "DataSetSelectionOption";

	/**
	 * Standard constructor
	 */	
    public _DataSetSelectionOption() {
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
	 * Create a "DataSetSelectionOption" object with all required values
	 */
	public static DataSetSelectionOption createDataSetSelectionOption(EOEditingContext editingContext, Boolean active, NSTimestamp creationTime, String optionName, Number orderNumber, net.events.cms.eo.Client client, net.events.cms.eo.EventsUser createdBy, net.events.cms.eo.DataSetSelectionItem dataSetItem) {
		if (log.isDebugEnabled()) log.debug ("Creating object: DataSetSelectionOption");
		DataSetSelectionOption eoObject = (DataSetSelectionOption)EOUtilities.createAndInsertInstance(editingContext, _DataSetSelectionOption.ENTITY_NAME);
		eoObject.setActive(active);
		eoObject.setCreationTime(creationTime);
		eoObject.setOptionName(optionName);
		eoObject.setOrderNumber(orderNumber);
		eoObject.setClient(client);
		eoObject.setCreatedBy(createdBy);
		eoObject.setDataSetItem(dataSetItem);
		return eoObject;
	}
 
 	/**
 	 * Fetch all "DataSetSelectionOption" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetSelectionOption'");
		return _DataSetSelectionOption.fetchAllObjects(_editingContext, null);
	}
 
 	/**
 	 * Fetch all "DataSetSelectionOption" objects from the database. Be careful with that function
 	 * as it might return a lot of objects
 	 */
	public static NSArray fetchAllObjects(EOEditingContext _editingContext, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetSelectionOption' with sortOrderings " + _sortOrderings);
		return _DataSetSelectionOption.fetchObjectsWithQualifier(_editingContext, null, _sortOrderings);
	}

	/**
	 * Fetches all "DataSetSelectionOption" objects matching value for Key
	 */
	public static NSArray fetchObjectsWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetSelectionOption.fetchObjectsWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value), null);
	}

	/**
	 * Fetches all "DataSetSelectionOption" objects matching the given qualifier
	 */
	public static NSArray fetchObjectsWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier, NSArray _sortOrderings) {
		if (log.isDebugEnabled()) log.debug ("Fetching all objects for entity 'DataSetSelectionOption' with qualifier " + _qualifier + "and sortOrderings " + _sortOrderings);
		EOFetchSpecification fetchSpec = new EOFetchSpecification(_DataSetSelectionOption.ENTITY_NAME, _qualifier, _sortOrderings);
		fetchSpec.setIsDeep(true);
		NSArray eoObjects = _editingContext.objectsWithFetchSpecification(fetchSpec);
		return eoObjects;
 	}
 
 	/**
 	 * Fetch exactly one object for value for key. Uses fetchWithQualifier for that. Might throw an Exception 
 	 * if more than one object was found! Returns null, if no object was found.
 	 */
	public static DataSetSelectionOption fetchDataSetSelectionOptionWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetSelectionOption.fetchDataSetSelectionOptionWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if more than one object was found!
 	 * Returns null, if no object was found.
 	 */
 	public static DataSetSelectionOption fetchDataSetSelectionOptionWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching 'DataSetSelectionOption' with qualifier: " + _qualifier);
		NSArray eoObjects = _DataSetSelectionOption.fetchObjectsWithQualifier(_editingContext, _qualifier, null);
		DataSetSelectionOption eoObject;
		int count = eoObjects.count();
		if (count == 0) {
			eoObject = null;
  		}
		else if (count == 1) {
			eoObject = (DataSetSelectionOption)eoObjects.objectAtIndex(0);
		}
		else {
			throw new IllegalStateException("There was more than one DataSetSelectionOption that matched the qualifier '" + _qualifier + "'.");
		}
		return eoObject;
	}
    
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetSelectionOption fetchRequiredDataSetSelectionOptionWithKeyAndValue(EOEditingContext _editingContext, String _keyName, Object _value) {
		return _DataSetSelectionOption.fetchRequiredDataSetSelectionOptionWithQualifier(_editingContext, new EOKeyValueQualifier(_keyName, EOQualifier.QualifierOperatorEqual, _value));
	}
 
 	/**
 	 * Fetch exactly one object for value for key. Might throw an Exception if none or more than one object was found!
 	 */
	public static DataSetSelectionOption fetchRequiredDataSetSelectionOptionWithQualifier(EOEditingContext _editingContext, EOQualifier _qualifier) {
		if (log.isDebugEnabled()) log.debug ("Fetching required 'DataSetSelectionOption' with qualifier: " + _qualifier);
		DataSetSelectionOption eoObject = _DataSetSelectionOption.fetchDataSetSelectionOptionWithQualifier(_editingContext, _qualifier);
		if (eoObject == null) {
			throw new NoSuchElementException("There was no DataSetSelectionOption that matched the qualifier '" + _qualifier + "'.");
   		}
		return eoObject;
	}
 
 	/**
 	 * Returns a local instance of this object in the given editing context
 	 */
	public DataSetSelectionOption localInstanceOfDataSetSelectionOption(EOEditingContext _editingContext) {
		if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetSelectionOption': " + this.toString());
		return (DataSetSelectionOption)EOUtilities.localInstanceOfObject(_editingContext, this);
	}
 
 	/** 
 	 * Returns a local object or null; this works for null objects as wel
 	 */
	public static DataSetSelectionOption localInstanceOfDataSetSelectionOption(EOEditingContext _editingContext, DataSetSelectionOption _eo) {
		if (_eo != null) {
			if (log.isDebugEnabled()) log.debug ("Returning local instance of 'DataSetSelectionOption': " + _eo.toString());
		}
		return (_eo == null) ? null : (DataSetSelectionOption)EOUtilities.localInstanceOfObject(_editingContext, _eo);
	}
  

	/**
	 * The value for "active"
	 */
    public Boolean active() {
        return (Boolean) storedValueForKey("active");
    }

	/**
	 * Set the value for "active"
	 */
    public void setActive(Boolean aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating active from "+active()+" to "+aValue );
        takeStoredValueForKey(aValue, "active");
    }

	/**
	 * The value for "creationTime"
	 */
    public NSTimestamp creationTime() {
        return (NSTimestamp) storedValueForKey("creationTime");
    }

	/**
	 * Set the value for "creationTime"
	 */
    public void setCreationTime(NSTimestamp aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating creationTime from "+creationTime()+" to "+aValue );
        takeStoredValueForKey(aValue, "creationTime");
    }

	/**
	 * The value for "optionName"
	 */
    public String optionName() {
        return (String) storedValueForKey("optionName");
    }

	/**
	 * Set the value for "optionName"
	 */
    public void setOptionName(String aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating optionName from "+optionName()+" to "+aValue );
        takeStoredValueForKey(aValue, "optionName");
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
	 * The value for "score"
	 */
    public Number score() {
        return (Number) storedValueForKey("score");
    }

	/**
	 * Set the value for "score"
	 */
    public void setScore(Number aValue) {
        if( log.isDebugEnabled() ) log.debug( "updating score from "+score()+" to "+aValue );
        takeStoredValueForKey(aValue, "score");
    }

    public net.events.cms.eo.Client client() {
        return (net.events.cms.eo.Client)storedValueForKey("client");
    }

    public void setClient(net.events.cms.eo.Client aValue) {
        takeStoredValueForKey(aValue, "client");
    }

    public net.events.cms.eo.EventsUser createdBy() {
        return (net.events.cms.eo.EventsUser)storedValueForKey("createdBy");
    }

    public void setCreatedBy(net.events.cms.eo.EventsUser aValue) {
        takeStoredValueForKey(aValue, "createdBy");
    }

    public net.events.cms.eo.DataSetSelectionItem dataSetItem() {
        return (net.events.cms.eo.DataSetSelectionItem)storedValueForKey("dataSetItem");
    }

    public void setDataSetItem(net.events.cms.eo.DataSetSelectionItem aValue) {
        takeStoredValueForKey(aValue, "dataSetItem");
    }
}