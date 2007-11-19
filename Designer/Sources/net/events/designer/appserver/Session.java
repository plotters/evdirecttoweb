package net.events.designer.appserver;

// Generated by the WOLips Templateengine Plug-in at 11.02.2007 19:25:05

import net.events.cms.extensions.*;

import com.webobjects.appserver.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public class Session extends EVCMSSession {

	public Session () {
		super();
		log.info("Created session with id: "+ this.sessionID());
	}


	/**
	 * Returns an upload page for the ABDA Update files; sets the navigation
	 * state to ABDA.UploadABDAFile
	 * 
	 * @return
	 */
	public WOComponent uploadABDAFiles() {
		ERXNavigationManager.manager().navigationStateForSession(this).setState(new NSArray<Object>(new Object[] { "ABDA", "UploadABDAFile" }));
		return this.pageWithName("ABDAFileUploadPage");
	}

}