package net.events.extensions;


import net.events.page.EVPageLevelComponent;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOCustomObject;
import com.webobjects.foundation.NSArray;

import er.extensions.ERXArrayUtilities;
import er.extensions.ERXGenericRecord;
import er.extensions.ERXNonSynchronizingComponent;
import er.extensions.ERXProperties;

/**
 * @author cug
 *
 */

public class EVDisplayToManyUL extends ERXNonSynchronizingComponent {
	
	// for iterating over the list
	private EOCustomObject item;
	
	private Logger log = Logger.getLogger(EVDisplayToManyUL.class);
	

    public EVDisplayToManyUL(WOContext context) {
        super(context);
    }
    
    /**
     * The list that should be displayed
     * 
     * @return array of enterprise objects
     */
    public NSArray list () {
    	return (NSArray) this.valueForBinding("list");
    }
    
    /**
     * Set the list to be displayed
     * 
     * @param a 
     */
    public void setList (NSArray a) {
    	this.setValueForBinding(a, "list");
    }
    
    /**
     * The bound list, ordered by "orderKey"
     * 
     * @return ordered array of enterprise objects
     */
    public NSArray orderedList () {
    	return ERXArrayUtilities.sortedArraySortedWithKey(this.list(), this.orderKey());
    }
    
    /**
     * The key used for KVC on the relationships target object
     * @return
     */
    public String displayKey () {
    	return this.stringValueForBinding("displayKey");
    }
    
    /**
     * The actual display string
     */
    public String displayString () {
    	if (this.item().valueForKey(this.displayKey()) != null) {
    		return this.item().valueForKey(this.displayKey()).toString();
    	}
    	else return "-- no value --";
    }
    
    /**
     * set the key
     * @param key
     */
    public void setDisplayKey (String key) {
    	this.setValueForBinding(key, "displayKey");
    }

    /** 
     * This key is used to order the list of enterprise objects
     * @return
     */
    public String orderKey () {
    	return this.stringValueForBinding("orderKey");
    }
    
    /**
     * Set the key
     * @param key
     */
    public void setOrderKey (String key) {
    	this.setValueForBinding(key, "orderKey");
    }
    
    /**
     * used for iterating over the list
     */
    public EOCustomObject item () {
    	return this.item;
    }
    
    /**
     * used for iterating over the list
     */
    public void setItem (EOCustomObject eo) {
    	this.item = eo;
    }

    /**
     * Returns a component for inspecting the the object at the destination. Uses the PageLevelComponent method for inspecting objects
     * so lastPage, nextPage and object are set automatically.
     * 
     * @return inspect component for the related object
     */
    public WOComponent inspectObject () {
    	if (this.inspectComponentName() != null && this.inspectable()) {
    		WOComponent page = this.context().page();
    		if (log.isDebugEnabled()) log.debug ("Page: " + page);
    		if (page instanceof EVPageLevelComponent) {
    			EVPageLevelComponent pl = (EVPageLevelComponent) page;
    			return pl.inspectObject((ERXGenericRecord) item, this.inspectComponentName());
    		}
    	}
    	
    	return null;
    }
  
    /** 
     * Binding getter. This binding determines whether the list entry should have a hyperlink
     * to the related object. 
     * 
     * @return true, when the binding "inspectable" is set to true, false otherwise.
     */
    public boolean inspectable () {
    	return this.booleanValueForBinding("inspectable", false);
    }
    
    /** 
     * Inverts inspectable to have a binding for the hyperlinks "disabled" binding.
     * 
     * @return true, if no hyperlink should be used
     */
    public boolean disabled () {
    	return !inspectable();
    }
    
    /**
     * Binding setter. This binding determines whether the list entry should have a hyperlink
     * to the related object.
     *  
     * @param true for showing a hyperlink
     */
    public void setInspectable (Boolean b) {
    	this.setValueForBinding(b, "inspectable");
    }
    
    /*
     * What component should be used to insect the current list item? Uses
     * "net.events.pageForObjectDetail.ClassName" property to get the page name.
     *  
     * @return page name as String
     */
    private String inspectComponentName() {
    	return ERXProperties.stringForKeyWithDefault("net.events.pageForObjectDetail." + this.item.entityName(), null);
    }

}