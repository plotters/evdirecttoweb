package net.events.cms.extensions;

import net.events.cms.eo.*;
import net.events.extensions.*;

import com.webobjects.appserver.*;
import com.webobjects.eocontrol.*;

import er.extensions.*;

/**
 * Base class for all Portal pages. Holds a page object from the database to get
 * the necessary values and settings.
 * 
 * @author cug
 */
public class EVCMSPage extends EVPage {
	
	/**
	 * Holds the page object from the database
	 */
	private AbstractPage page;
	
    // holds the last page
	private WOComponent lastPage;
	
	/**
	 * The editingContext that can be used on that page
	 */
	protected EOEditingContext editingContext;

	/**
	 * Standard constructor 
	 * 
	 * @param c
	 */
	public EVCMSPage(WOContext c) {
		super(c);
		
		this.editingContext = (EOEditingContext) ERXThreadStorage.valueForKey("EDITING_CONTEXT_FOR_RRLOOP");
	}
	
	/**
	 * Gets the page from the ThreadStorage
	 */
	public void awake () {
		super.awake();
		if (this.page == null) {
			// try to get a page
			this.setPage((AbstractPage) ERXThreadStorage.valueForKey(EVCMSConstants.PAGE_OBJECT_KEY));
		}
	}

	/**
	 * @return the page
	 */
	public AbstractPage page() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(AbstractPage page) {
		this.page = page;
	}
	
	/**
	 * @return the lastPage
	 */
	public WOComponent lastPage() {
		return this.lastPage;
	}

	/**
	 * @param lastPage the lastPage to set
	 */
	public void setLastPage(WOComponent lastPage) {
		this.lastPage = lastPage;
	}

}
