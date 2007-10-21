package net.events.extensions;


import net.events.eof.EVGenericRecord;

import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOAttribute;
import com.webobjects.eoaccess.EOEntity;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSMutableArray;

import er.extensions.ERXEOAccessUtilities;
import er.extensions.ERXStringUtilities;

public class EVGenericEmbeddedList extends EVEmbeddedList {

	/**
	 * The current attributeName used to build the table
	 */
	private String attributeName;
	
	/**
	 * The list of attributes to use to build the table, determined from the EO by calling
	 * the static variable <code>propertyKeysForList</code> which should return an NSArray of strings<br><br>
	 * This array is only used for caching, so not every row will ask for the list
	 */
	private NSArray attributes;
	
	/**
	 * The attributes which should not be sortable<br><br>
	 * This array is only used for caching, so not every row will ask for the list
	 */
	private NSArray notSortableAttributes;
	
	/**
	 * For caching whether we have checked with the class for notSortableAttributes
	 */
	private boolean checkedNotSortableAttributes;

	/**
	 * Provide a string like "80%" or similar, this is pushed to the table width attribute
	 */
	private String tableWidth;
	
	/**
	 * Provide an int for the cellpadding attribute of the list table
	 */
	private int cellpadding;
	
	/**
	 * provide an in for the cellspacing attribute of the list table
	 */
	private int cellspacing;
	
	/**
	 * provide an int for the table border attribute
	 */
	private int border;

	/**
	 * provide a string which should match one of the attributes 
	 */
	private String defaultSortOrderKey;
	
	/**
	 * Set this binding to true, if you want to see a quicksearch field at the top of the list
	 * table
	 */
	private Boolean quicksearch = Boolean.FALSE;
	
	/**
	 * Set, whether to show a delete icon
	 */
	private Boolean isEntityDeletable = Boolean.FALSE;
	
	/**
	 * Set, whether to show an edit icon
	 */
	private Boolean isEntityEditable = Boolean.FALSE;
	
    public EVGenericEmbeddedList(WOContext context) {
        super(context);
        
        this.setQualifyOnEmptyQualifier(Boolean.TRUE);
    }

	public void prepareInitialFetchSpecification() {
		if (this.defaultSortOrderKey != null) {
			EOSortOrdering sortOrdering = EOSortOrdering.sortOrderingWithKey(this.defaultSortOrderKey(), EOSortOrdering.CompareCaseInsensitiveAscending);
			this.setSortOrderings(new NSMutableArray(sortOrdering));
		}
		
		this.setFetchSpecification(new EOFetchSpecification ((String) this.valueForBinding("entityName"), null, null));
	}
	
	/**
	 * We don't want the ProjectCenter functionality to qualifiy on "client"
	 */
	public boolean useStandardQualifier () {
		return false;
	}

	/**
	 * @return the columnEntry
	 */
	public Object columnValue() {
		return object().valueForKey(this.attributeName);
	}
	
	public String componentName () {
		if (object().valueForKey(attributeName) instanceof String || object().valueForKey(attributeName) instanceof NSKeyValueCoding.Null) {
			// easy case first
			return "EVDisplayStringOrNull";
		}
		else {
			// we have to decide what component we shall use for the display of this object, we do this
			// based on the EOEntity information for our current object (eo) and our prototypes (there should
			// be a better way, this is a hack that relies on the prototypes, but okay, for now, it should work
			
			// FIXME gn - make this more flexible
			// FIXME gn - create static string in the prototypes framework to get rid of the strings here
			
			EOEntity entity = ERXEOAccessUtilities.entityForEo(object());
			EOAttribute attr = entity.attributeNamed(attributeName);
			
			if (attr != null) {
				// we have a real attributeName, not a relationship
				// for refactoring safety we use the last property of the keypath of the fully qualified class name
				
				if (attr.prototypeName().equals("datetime")) {
					return ERXStringUtilities.lastPropertyKeyInKeyPath(EVDisplayDateTimeOrNull.class.getName());
				}
				else if (attr.prototypeName().equals("time")) {
					return ERXStringUtilities.lastPropertyKeyInKeyPath(EVDisplayTimeOrNull.class.getName());
				}
				else if (attr.prototypeName().equals("date")) {
					return ERXStringUtilities.lastPropertyKeyInKeyPath(EVDisplayDateOrNull.class.getName());
				}
				else if (attr.prototypeName().equals("boolean")) {
					return ERXStringUtilities.lastPropertyKeyInKeyPath(EVDisplayBoolean.class.getName());
				}
			}
		}
		return "WOString";
	}
	
	/**
	 * @return the column header
	 */
	public String columnHeader () {
		return this.attributeName;
	}
	
	/**
	 * The attributeName keys are cached in a local array - so this shouldn't be an issue
	 * 
	 * @return the attributeName keys
	 */
	public NSArray attributeList () {
		if (this.attributes != null) {
			return this.attributes;
		}
		else {
			this.attributes = EVEOUtilities.attributesForEntityNameAndKey(this.entityName(), EVGenericRecord.kATTRIBUTE_KEYS_FOR_LIST);
			return this.attributes;
		}
	}
	
	/**
	 * Returns the attributes which deliver values in form of a String object
	 * 
	 * @return an array of keys which can be used to access string values
	 */
	public NSArray stringAttributes () {
		return EVEOUtilities.stringAttributesForEntityNameAndKey(this.entityName(), EVGenericRecord.kATTRIBUTE_KEYS_FOR_LIST);
	}
	
	/**
	 * Checks whether an attribute is sortable; this method only checks with a method in the 
	 * currently used EO
	 * 
	 * @return true if attribute is sortable
	 */
	public boolean isAttributeSortable () {
		// get the attributes an cache them
		if (!this.checkedNotSortableAttributes) {
			this.notSortableAttributes = EVEOUtilities.attributesForEntityNameAndKey(this.entityName(), EVGenericRecord.kNOT_SORTABLE_ATTRIBUTES);
			this.checkedNotSortableAttributes = true;
		}
		
		// if there are attributes that are not sortable, check whether the current attributeName is sortable
		if (this.notSortableAttributes != null && this.notSortableAttributes.count() > 0) {
			return !this.notSortableAttributes.containsObject(attributeName);
		}
		
		// okay, nothing found, default is true ...
		return true;
	}

	/**
	 * @return the attributeName
	 */
	public String attributeName() {
		return attributeName;
	}

	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attribute) {
		this.attributeName = attribute;
	}

	/**
	 * Returns the number of columns for the table
	 * 
	 * @return int # of columns
	 */
	public int numColumns () {
		int colspan = attributeList().count();
		
		// we need another column for the trashcan image
		if (this.isEntityDeletable().booleanValue()) colspan++;
		
		// and another one, if the entity is editable
		if (this.isEntityEditable().booleanValue()) colspan++; 
		
		return colspan;
	}
	
	/**
	 * Generated name for the use in a CSS stylesheet - the entity name and the displayed attributeName
	 * name are combined to provide a more or less unique class name for a style. All styles start
	 * with "EVGL" for "EVGenericList"  
	 */
	public String cssClassName () {
		return "EVGL" + this.entityName() + ERXStringUtilities.capitalize(this.attributeName);
	}
	
	/**
	 * Returns the same construct as <code>cssClassName</code> but appends a "TH" at the end. 
	 */
	public String cssClassNameForHeader() {
		return this.cssClassName() + "TH";
	}

	/**
	 * @return the tableWidth
	 */
	public String tableWidth() {
		return tableWidth;
	}

	/**
	 * @param tableWidth the tableWidth to set
	 */
	public void setTableWidth(String tableWidth) {
		this.tableWidth = tableWidth;
	}

	/**
	 * @return the border
	 */
	public int border() {
		return border;
	}

	/**
	 * @param border the border to set
	 */
	public void setBorder(int border) {
		this.border = border;
	}

	/**
	 * @return the defaultSortOrderKey
	 */
	public String defaultSortOrderKey() {
		return defaultSortOrderKey;
	}

	/**
	 * @param defaultSortOrderKey the defaultSortOrderKey to set
	 */
	public void setDefaultSortOrderKey(String defaultSortOrderKey) {
		this.defaultSortOrderKey = defaultSortOrderKey;
	}

	/**
	 * @return the cellpadding
	 */
	public int cellpadding() {
		return cellpadding;
	}

	/**
	 * @param cellpadding the cellpadding to set
	 */
	public void setCellpadding(int cellpadding) {
		this.cellpadding = cellpadding;
	}

	/**
	 * @return the cellspacing
	 */
	public int cellspacing() {
		return cellspacing;
	}

	/**
	 * @param cellspacing the cellspacing to set
	 */
	public void setCellspacing(int cellspacing) {
		this.cellspacing = cellspacing;
	}

	/**
	 * @return the quicksearch
	 */
	public Boolean quicksearch() {
		return quicksearch;
	}

	/**
	 * @param quicksearch the quicksearch to set
	 */
	public void setQuicksearch(Boolean quicksearch) {
		this.quicksearch = quicksearch;
	}

	/**
	 * @return the isEntityDeletable
	 */
	public Boolean isEntityDeletable() {
		return isEntityDeletable;
	}

	/**
	 * @param isEntityDeletable the isEntityDeletable to set
	 */
	public void setIsEntityDeletable(Boolean isEntityDeletable) {
		this.isEntityDeletable = isEntityDeletable;
	}

	/**
	 * @return the isEntityEditable
	 */
	public Boolean isEntityEditable() {
		return isEntityEditable;
	}

	/**
	 * @param isEntityEditable the isEntityEditable to set
	 */
	public void setIsEntityEditable(Boolean isEntityEditable) {
		this.isEntityEditable = isEntityEditable;
	}
	
}