package net.events.d2w.pages;

import com.webobjects.appserver.WOContext;

import er.directtoweb.pages.templates.ERD2WCompactInspectPageTemplate;
import er.extensions.foundation.ERXValueUtilities;

public class EVD2WCompactInspectPage extends ERD2WCompactInspectPageTemplate {
	public EVD2WCompactInspectPage(WOContext wocontext) {
		super(wocontext);
	}

	public boolean showSaveButton () {
		return this.isEditing() && !ERXValueUtilities.booleanValue(this.d2wContext().valueForKey("hideSaveButton"));
	}
}
