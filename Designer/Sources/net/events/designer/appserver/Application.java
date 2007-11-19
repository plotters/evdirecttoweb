package net.events.designer.appserver;
// Generated by the WOLips Templateengine Plug-in at 11.02.2007 19:25:05

import net.events.cms.extensions.*;
import net.events.d2w.extensions.*;
import net.events.designer.components.*;

import org.apache.log4j.*;

import com.webobjects.appserver.*;
import com.webobjects.directtoweb.*;
import com.webobjects.eoaccess.*;

import er.extensions.*;

public class Application extends EVD2WApplication {
	
	/**
	 * Logging support
	 */
	private Logger log = Logger.getLogger(Application.class);
    
	/**
	 * Standard constructor
	 * @param argv
	 */
    public static void main(String argv[]) {
        ERXApplication.main(argv, Application.class);
    }

    public Application() {
        super();
        log.info("Welcome to " + this.name() + "!");
        
        // one second refresh
        ERXEC.setDefaultFetchTimestampLag(5000);
        
        // Ajax requires that
        // setAllowsConcurrentRequestHandling(true);
        
        // we start with the direct action request handler, so that we can control what happens without starting a session
        setDefaultRequestHandler(requestHandlerForKey(directActionRequestHandlerKey()));

        // set our own factory class to start with our desired startpage
		D2W.setFactory(new EVDesignerD2WFactory());
    }
    
    /**
     * Set up navigation and clean the database from session tracking from previous runs.
     * 
     * @see er.extensions.ERXApplication#didFinishLaunching()
     */
    public void didFinishLaunching() {
		super.didFinishLaunching();
		ERXNavigationManager.manager().configureNavigation();
		
		ERXEC editingContext = (ERXEC) ERXEC.newEditingContext();
		try {
			editingContext.lock();
			String sql = EVCMSQueryManager.cleanupLoggedInUsersForApplicationInstance(this.name() + this.number());
			EOUtilities.rawRowsForSQL(editingContext, "EVCMS", sql, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			editingContext.unlock();
			editingContext.dispose();
			editingContext = null;
		}
	}
    
    /**
     * Returns a useful session timeout page
     */
    public WOResponse handleSessionRestorationErrorInContext(WOContext aContext) {
        SessionTimeoutPage stp = (SessionTimeoutPage) pageWithName(SessionTimeoutPage.class.getName(),aContext);
        return stp.generateResponse();
    }
    
	/**
	 * Make URLs nicer
	 */
	public String _rewriteURL(String url) {
		if (ERXProperties.booleanForKeyWithDefault("net.events.Designer.shouldRewriteUrls", false)) {
			url = url.replaceAll("/cgi-bin/WebObjects/Designer.woa/", "/Designer");
		}
		return url;
	}   
}