package net.events.d2w.components;

import net.events.d2w.extensions.*;
import net.events.util.*;

import com.webobjects.appserver.*;

import er.directtoweb.*;

/**
 * Shows a truncated string instead of the whole thing. 
 * 
 * This implementation relies on an updateContainerId given in a rule, to update this container. This is done
 * because the nested containers didn't update in my testing.
 * 
 * TODO cug: add more javadoc
 */
public class EVD2WDisplayLargeString extends ERDCustomEditComponent {
	
	private boolean collapsed = true;
	private String updateContainerId;
	
	/**
	 * Standard constructor
	 * 
	 * @param context
	 */
	public EVD2WDisplayLargeString(WOContext context) {
		super(context);
	}
	
	/**
	 * We have to keep track whether we are collapsed or not
	 */
	public boolean isStateless () {
		return false;
	}
	
	/**
	 * The value to show
	 * 
	 * @return
	 */
	public String stringValue () {
		if (collapsed) {
			return EVStringUtilities.shortenStringWithAppendingEllipse((String) this.objectPropertyValue(), 50);
		}
		return (String) this.objectPropertyValue();
	}
	
	/**
	 * The link value
	 * 
	 * @return
	 */
	public String linkStringValue () {
		if (this.collapsed) {
			return "[read more]";
		}
		else {
			return "[hide details]";
		}
	}
	
	/**
	 * Toggle the collapse display
	 * 
	 * @return
	 */
	public WOComponent toggleDisplay () {
		collapsed = !collapsed;
		return null;
	}
	
	/**
	 * The updateContainerId from the DirectToWeb context
	 *  
	 * @return
	 */
	public String updateContainerId () {
		// return (String) this.d2wContext().valueForKey("updateContainerId");
		if (this.updateContainerId == null) {
			this.updateContainerId = this.d2wContext().entity().name() + "_" + Math.abs(((EVD2WApplication) this.application()).randomInteger());
		}
		return this.updateContainerId;
	}
	
}