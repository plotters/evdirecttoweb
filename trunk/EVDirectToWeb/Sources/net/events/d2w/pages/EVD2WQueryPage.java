package net.events.d2w.pages;

import com.webobjects.appserver.WOContext;

import er.directtoweb.pages.templates.ERD2WQueryPageTemplate;

/**
 * Re-formatted query page with more css, cleaner structure
 * 
 * @author cug
 *
 */
public class EVD2WQueryPage extends ERD2WQueryPageTemplate {
	public EVD2WQueryPage(WOContext wocontext) {
		super(wocontext);
	}

	public boolean showFetchLimitString () {
		if (this.fetchLimit() > 0) {
			return true;
		}
		else return false;
	}
}
