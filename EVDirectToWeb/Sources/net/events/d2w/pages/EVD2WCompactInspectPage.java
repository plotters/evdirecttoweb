package net.events.d2w.pages;

import com.webobjects.appserver.WOContext;

import er.directtoweb.ERD2WCompactInspectPageTemplate;
import er.extensions.ERXValueUtilities;

public class EVD2WCompactInspectPage extends ERD2WCompactInspectPageTemplate {
	public EVD2WCompactInspectPage(WOContext wocontext) {
		super(wocontext);
	}

	public boolean showSaveButton () {
		return this.isEditing() && !ERXValueUtilities.booleanValue(this.d2wContext().valueForKey("hideSaveButton"));
	}
}
