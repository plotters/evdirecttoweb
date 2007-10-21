package net.events.extensions;



import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;

import er.extensions.ERXStatelessComponent;

/**
 * Component for displaying column headers in sortable lists (like EmbeddedList). This component
 * takes a "value" to display in the header, an NSArray of sortOrderings (current sortOrderings for
 * the list) and a key that defines which object key (or keypath) is used to order the list 
 * when clicking on this column. 
 * <br><br>
 * The components maintains the order state by displaying one of four arrows to indicate whether the 
 * current column is used to order the list 
 * <ol>
 *  <li> as the primary order criteria in ascending order</li>
 *  <li> as the primary order criteria in descending order</li>
 *  <li> as the secondary order criteria in ascending order</li>
 *  <li> as the secondary order criteria in descending order</li>
 * </ol>
 * The parent component MUST implement an "orderBy" method like EmbeddedList does, as the included
 * hyperlink in this component uses a binding "action = parent.orderBy" and appends the order key
 * to the URL.
 * <br><br>
 * Bindings (all required):
 * <ul>
 * 	<li>value - the value to display in the header string (this gets localized)</li>
 * 	<li>key - the key for sorting with this attribute</li>
 * 	<li>sortOrderings - NSArray of sortOrderings for the current list</li>
 * </ul>
 * 
 * @author cug
 *
 */
public class EVSortColumnHeader extends ERXStatelessComponent {
	
	private EOSortOrdering matchingSortOrdering;
//	private ERXLogger log = ERXLogger.getERXLogger(SortColumnHeader.class);
	private int secondary;

	public EVSortColumnHeader(WOContext context) {
		super(context);
	}

	public void awake() {
		super.awake();
		this.matchingSortOrdering = null;
	}
	
	public void reset () {
		super.reset();
		this.matchingSortOrdering = null;
		this.secondary = 0;
	}

	/**
	 * Set the value for the displayed string
	 * @param value
	 */
	public void setValue (String value) {
		this.setValueForBinding(value, "value");
	}
	
	/**
	 * The value for the displayed string
	 * @return a string
	 */
	public String value () {
		return (String) this.valueForBinding("value");
	}
	
	/**
	 * Set the sortOrderings 
	 * @param sortOrderings
	 */
	public void setSortOrderings (NSArray sortOrderings) {
		this.setValueForBinding(sortOrderings, "sortOrderings");
	}
	
	/**
	 * @return the sortOrderings
	 */
	public NSArray sortOrderings () {
		return (NSArray) this.valueForBinding("sortOrderings");
	}
	
	/**
	 * The key to sort with
	 * @return key as string
	 */
	public String key () {
		return (String) this.valueForBinding("key");
	}
	
	/**
	 * Set the key to sort with
	 * @param key
	 */
	public void setKey (String key) {
		this.setValueForBinding(key, "key");
	}
	
	public boolean sortable () {
		if (this.valueForBinding("sortable") != null) { 
			return this.booleanValueForBinding("sortable");
		}
		else return true;
	}
	
	public void setSortable (Boolean flag) {
		this.setValueForBinding(flag, "sortable");
	}
	
	public boolean disableSortLink (){
		return !this.sortable();
	}
		
	/**
	 * The filename for the arrow image
	 * 
	 * @return a filename
	 */
	public String filename () {
		// check for null, just to be sure, if this is called we must have a matching sort ordering
		// which is set by the code, that evaluates whether we have to show an image
		if (this.matchingSortOrdering != null) {
			if (this.matchingSortOrdering.selector().equals(EOSortOrdering.CompareAscending) || 
							this.matchingSortOrdering.selector().equals(EOSortOrdering.CompareCaseInsensitiveAscending)) {
				if (secondary == 1) {
					return "ArrowUpSec.gif";
				}
				else {
					return "ArrowUp.gif";
				}
			}
			else {
				if (secondary == 1) {
					return "ArrowDownSec.gif";
				}
				else {
					return  "ArrowDown.gif";
				}
			}
		}
		return null;
	}
	
	/**
	 * Should we show the image, means, is the list ordererd with this column?
	 * @return true if the list has a sortOrdering for the key of this component
	 */
	public boolean showImage () {
		if (this.sortOrderings() != null && this.sortOrderings().count() > 0) {
			for (int i=0; i<this.sortOrderings().count(); i++) {
				if (this.key().equals(((EOSortOrdering) this.sortOrderings().objectAtIndex(i)).key())) {
					this.matchingSortOrdering = (EOSortOrdering) this.sortOrderings().objectAtIndex(i);
					secondary = i;
					return true;
				}
			}
		}
		return false;
	}
	
}