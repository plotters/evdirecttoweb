package net.events.d2w.extensions;

import java.util.Random;

import net.events.d2w.pages.EVGenericErrorPage;
import ognl.helperfunction.WOHelperFunctionHTMLTemplateParser;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOAction;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOMessage;
import com.webobjects.appserver.WORedirect;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WORequestHandler;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOSharedEditingContext;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.ERXApplication;
import er.extensions.ERXMessageEncoding;
import er.extensions.ERXProperties;

public class EVD2WApplication extends ERXApplication {
	
	private static Logger log = Logger.getLogger(EVD2WApplication.class);
	private static Logger requestLogger = Logger.getLogger("RequestHandling");
	private Random random;

	public static final NSDictionary noWOSID = new NSDictionary(Boolean.FALSE, "wosid");

	public EVD2WApplication () {
		super();
		
		WOHelperFunctionHTMLTemplateParser.registerTagShortcut("WOSwitchComponent", "switch");
		
		// set sharedEditingContext to null, to avoid use of it
		if (!ERXProperties.booleanForKeyWithDefault("net.events.Application.useSharedEditingContext", true)) {
			log.info ("Setting shared editing context to null.");
			EOSharedEditingContext.setDefaultSharedEditingContext(null);
		}

		// FIXME cug: Do we need to add comparison support?
//        // install international ordering support
//		if (ERXProperties.booleanForKeyWithDefault("net.events.Application.installComparisonSupport", false)) {
//			log.info ("Installing java comparison support for correct ordering with locales.");
//			EOSortOrdering.ComparisonSupport.setSupportForClass(new ERXComparisonSupport(), String.class);
//		}

		// Set the default encoding for everything (we normally use UTF8):
		if (ERXProperties.booleanForKeyWithDefault("net.events.Application.setDefaultMessageEncoding", true)) {
			log.info ("Setting default message encoding to: " + WOMessage.defaultEncoding());
			ERXMessageEncoding.setDefaultEncodingForAllLanguages(WOMessage.defaultEncoding());
			ERXMessageEncoding.setDefaultEncoding(WOMessage.defaultEncoding());
		}

	}

	/**
	 * Overriden to make sure not to get any deadlocks by making sure, the session is checked in into the sessionStore 
	 */
	public WOResponse dispatchRequest(WORequest request) {
		requestLogger.debug("\n====================== Start of Request - Response Loop ======================");
		requestLogger.debug("Starting to dispatch URL: " + request.uri());
		requestLogger.debug("request headers = " + request.headers());
		NSTimestamp startTime = new NSTimestamp();

		WOResponse response = null;
		try {
			// execute super's implementation
			response = super.dispatchRequest(request);
		}
		finally {
			
			NSDictionary userInfo = request.userInfo();
			if (userInfo != null) {
				NSDictionary mutableDict = (NSDictionary) userInfo.valueForKey("mutableDict");
				
				WOContext context = (WOContext) mutableDict.valueForKey("context");
				
				if (context.hasSession()) {
					sessionStore().checkInSessionForContext(context);
				}
			}
		}
		NSTimestamp endTime = new NSTimestamp();
		requestLogger.debug ("response headers = " + response.headers());
		requestLogger.debug ("Finished dispatching URL: " + request.uri());
		
		// Note odd output formatting.  This makes it easy to grep out these lines
		// and load them into Excel as CSV for analysis
		requestLogger.debug (",Elapsed Time," + new Double((endTime.getTime() - startTime.getTime())/1000.0).toString());
		
		return response;
	}

	/**
	 * Overriden to make sure not to get any deadlocks (see dispatchRequest) 
	 */
	public WOContext createContextForRequest(WORequest request) {
		// setup user mutable dictionary to be available for the
		// rest of this request. request.userInfo() always
		// will return a immutable dictionary.
		
		NSMutableDictionary mutableDict = new NSMutableDictionary();
		NSDictionary userInfo = request.userInfo();
		if (userInfo == null) {
			/* from scratch */
			request.setUserInfo(new NSDictionary(mutableDict, "mutableDict"));
		}
		else {
			/* incorperate existing userInfo */
			
			NSMutableDictionary appended = new NSMutableDictionary();
			appended.addEntriesFromDictionary(userInfo);
			appended.takeValueForKey(mutableDict, "mutableDict");
			request.setUserInfo(appended);
		}
		
		// execute super's implementation
		WOContext context = super.createContextForRequest(request);
		
		mutableDict.takeValueForKey(context, "context");
		
		return context;
	}
	
    /**
     * Returns a new random integer, initializes random lazily. This can be used for creating 
     * random numbers to append to id's of update containers to make them not too ugly.
     * 
     * @return a random integer
     */
    public int randomInteger () {
    	if (this.random == null) {
    		this.random = new Random(new NSTimestamp().getTime());
    	}
    	return this.random.nextInt();
    }
    
	/** (non-Javadoc)
	 * @see com.webobjects.appserver.WOApplication#handleSessionRestorationErrorInContext(com.webobjects.appserver.WOContext)
	 * fängt Session-Timeout-Fehlerseite ab
	 */
	public WOResponse handleSessionRestorationErrorInContext(WOContext aContext) {
		WORedirect mainPage = (WORedirect) pageWithName ("WORedirect", aContext);
		mainPage.setUrl(aContext.directActionURLForActionNamed("default",	noWOSID));

		return mainPage.generateResponse();
	}
	
	
	/**
	 * We handle all exceptions - nothing should go through the app to the user
	 */

	public WOResponse handleException(Exception anException, WOContext aContext) {
		super.handleException(anException, aContext);
		
		anException.printStackTrace();
		
		if (aContext.hasSession()) {
			aContext.session().terminate();
		}

		WOComponent errorPage = this.pageWithName(EVGenericErrorPage.class.getName(), aContext);
		return errorPage.generateResponse();
	}

	/**
	 * Handle action request errors, returns generic error page for now
	 */
	public WOResponse handleActionRequestError(WORequest aRequest, Exception exception, String reason, WORequestHandler aHandler, String actionClassName, String actionName, Class actionClass, WOAction actionInstance) {

		log.fatal("Reason: " + reason);
		log.fatal("Classname: " + actionClassName);
		log.fatal("Action: " + actionName);
		log.fatal("Class: " + actionClass);

		exception.printStackTrace();

		WOContext aContext = new WOContext(aRequest);
		WOComponent errorPage = this.pageWithName(EVGenericErrorPage.class.getName(), aContext);
		return errorPage.generateResponse();
	}
}
