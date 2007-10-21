package net.events.extensions;


import org.apache.log4j.Logger;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;

import er.extensions.ERXArrayUtilities;
import er.extensions.ERXNonSynchronizingComponent;

/**
 * This component can be used to display a nested ul/ol list with minimal input.<br>
 * The only thing you have to do is to provide some bindings:
 * 
 * <ul>
 * 	<li><b>dictionary (required)</b> - an NSDictionary object, like one you get from ERXArrayUtilities.arrayGroupedByKeyPath</li>
 * 	<li><b>innerDisplayKey (required)</b> - the key to be used to display a value in the inner list</li>
 * 	<li><b>innerSortKey (optional)</b> - the key to be used for sorting the inner list elements, if null, innerDisplayKey is used</li>
 * 	<li><b>outerListIsOrderedList (optional)</b> - set this to true, if you want an ol list</li>
 * 	<li><b>innerListIsOrderedList (optional)</b> - set this to true, if you want an ol list</li>
 * 	<li><b>outerClass (optional)</b> - css class used for the outer list</li>
 * 	<li><b>innerClass (optional)</b> - css class used for the inner list</li>
 * </ul>
 * 
 * If you want other html for the list, you may subclass this component and change the HTML.
 * 
 * @author cug
 */
public class EVNestedList extends ERXNonSynchronizingComponent {

    // static strings for key-value-coding
	private static final String kDICTIONARY = "dictionary";
	private static final String kOUTER_CLASS = "outerClass";
	private static final String kINNER_CLASS = "innerClass";
	private static final String kOUTER_LIST_IS_ORDERED_LIST = "outerListIsOrderedList";
	private static final String kINNER_LIST_IS_ORDERED_LIST = "innerListIsOrderedList";
	private static final String kINNER_DISPLAY_KEY = "innerDisplayKey";
	private static final String kINNER_SORT_KEY = "innerSortKey";

	// values for the repetitions
	private String currentKey;
	private EOEnterpriseObject currentObject;
	
	// Logging
	private static Logger log = Logger.getLogger(EVNestedList.class);

    public EVNestedList(WOContext context) {
        super(context);
        
        if (log.isDebugEnabled()) log.debug ("Created nested list object");
    }
    
    /**
     * Set the dictionary that is used to build the nested lists
     * 
     * @param d
     */
    public void setDictionary (NSDictionary d) {
    	this.setValueForBinding(d, kDICTIONARY);
    }
    
    /**
     * The dictionary that is used to build the nested lists
     * 
     * @return
     */
    public NSDictionary dictionary () {
    	return (NSDictionary) this.valueForBinding(kDICTIONARY);
    }

    /**
     * Set the css class for the outer list (for the ul, ol elements)
     */
    public void setOuterClass (String s) {
    	this.setValueForBinding(s, kOUTER_CLASS);
    }
    
    /**
     * The css class for the outer list (for the ul, ol elements)
     */
    public String outerClass () {
    	return this.stringValueForBinding(kOUTER_CLASS);
    }
    
    /**
     * Set the css class for the inner list (for the ul, ol elements)
     */
    public void setInnerClass (String s) {
    	this.setValueForBinding(s, kINNER_CLASS);
    }
    
    /**
     * The css class for the inner list (for the ul, ol elements)
     */
    public String innerClass () {
    	return this.stringValueForBinding(kINNER_CLASS);
    }
    
    /**
     * The key to be used with KVC to determine the attribute to display in the inner list
     * 
     * @return
     */
    public String innerDisplayKey () {
    	return this.stringValueForBinding(kINNER_DISPLAY_KEY);
    }
    
    /**
     * Set the key to be used with KVC to determine the attribute to display in the inner list
     */
    public void setInnerDisplayKey (String s) {
    	this.setValueForBinding(s, kINNER_DISPLAY_KEY);
    }
    
    /**
     * Determines whether the inner list should be an ordered (numbered) list
     * 
     * @return true, if ordered
     */
    public boolean innerListIsOrderedList () {
    	return this.booleanValueForBinding(kINNER_LIST_IS_ORDERED_LIST, false);
    }
    
    /**
     * Set whether the inner list should be an ordered (numbered) list
     * 
     * @param true for an ordered, false for an unordered list
     */
    public void setInnerListIsOrderedList (boolean flag) {
    	this.setValueForBinding(new Boolean (flag), kINNER_LIST_IS_ORDERED_LIST);
    }

    /**
     * Determines whether the outer list should be an ordered (numbered) list
     * 
     * @return true, if ordered
     */
    public boolean outerListIsOrderedList () {
    	return this.booleanValueForBinding(kOUTER_LIST_IS_ORDERED_LIST, false);
    }
    
    /**
     * Set whether the outer list should be an ordered (numbered) list
     * 
     * @param true for an ordered, false for an unordered list
     */
    public void setOuterListIsOrderedList (boolean flag) {
    	this.setValueForBinding(new Boolean (flag), kOUTER_LIST_IS_ORDERED_LIST);
    }

    /**
     * Set the key for sorting the inner list
     * 
     * @param s
     */
    public void setInnerSortKey (String s) {
    	this.setValueForBinding(s, kINNER_SORT_KEY);
    }
    
    /**
     * The key for sorting the inner list
     */
    public String innerSortKey () {
    	if (this.stringValueForBinding(kINNER_SORT_KEY) != null) {
    		return this.stringValueForBinding(kINNER_SORT_KEY);
    	}
    	else {
    		return this.innerDisplayKey();
    	}
    }
    
    /**
     * The array of keys to display in the outer list as strings and for getting the
     * inner list arrays.
     * <br><br>
     * This is always sorted alphabetically.
     * 
     * @return array of strings
     */
    public NSArray keys () {
    	return ERXArrayUtilities.sortedArraySortedWithKey(this.dictionary().allKeys(), "toString");
    }
    
    /**
     * Returns the object array for the currently used inner list
     * 
     * @return array of enterprise objects
     */
    public NSArray objectsForCurrentKey () {
    	return ERXArrayUtilities.sortedArraySortedWithKey((NSArray) this.dictionary().valueForKey(currentKey), this.innerSortKey());
    }
    
    /**
     * The element name used in the outer list
     * 
     * @return ul or ol
     */
    public String elementNameForOuterList () {
    	return this.outerListIsOrderedList() ? "ol" :"ul";
    }
    
    /**
     * The element name used in the inner list
     * 
     * @return ul or ol
     */
    public String elementNameForInnerList () {
    	return this.innerListIsOrderedList() ? "ol" :"ul";
    }

    /**
     * Returns the string value for the inner list item. Uses innerDisplayKey() for 
     * key value coding to get the value and performs toString() on the value
     * 
     * @return value string
     */
    public String valueForInnerItem () {
    	return this.currentObject.valueForKey(innerDisplayKey()).toString();
    }

	/**
	 * @return the currentKey
	 */
	public String currentKey() {
		return currentKey;
	}

	/**
	 * @param currentKey the currentKey to set
	 */
	public void setCurrentKey(String currentKey) {
		this.currentKey = currentKey;
	}

	/**
	 * @return the currentObject
	 */
	public EOEnterpriseObject currentObject() {
		return currentObject;
	}

	/**
	 * @param currentObject the currentObject to set
	 */
	public void setCurrentObject(EOEnterpriseObject currentObject) {
		this.currentObject = currentObject;
	} 
    
}