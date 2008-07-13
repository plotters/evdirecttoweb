package net.events.d2w.extensions;

import java.util.Random;

import net.events.d2w.pages.EVGenericErrorPage;

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

import er.extensions.appserver.ERXApplication;
import er.extensions.appserver.ERXMessageEncoding;
import er.extensions.foundation.ERXProperties;

public abstract class EVD2WApplication extends ERXApplication {
	
	private static Logger log = Logger.getLogger(EVD2WApplication.class);
	private static Logger requestLogger = Logger.getLogger("RequestHandling");
	private Random random;

	public static final NSDictionary<String, Object> noWOSID = new NSDictionary<String, Object>(Boolean.FALSE, "wosid");

	public EVD2WApplication () {
		super();
		
		// WOHelperFunctionHTMLTemplateParser.registerTagShortcut("WOSwitchComponent", "switch");
		
		// set sharedEditingContext to null, to avoid use of it
		if (!ERXProperties.booleanForKeyWithDefault("net.events.Application.useSharedEditingContext", true)) {
			log.info ("Setting shared editing context to null.");
			EOSharedEditingContext.setDefaultSharedEditingContext(null);
		}

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
	@Override
	public WOResponse dispatchRequest(WORequest request) {
		
		requestLogger.info("\n====================== Start of Request - Response Loop ======================");
		requestLogger.info("Starting to dispatch URL: " + request.uri());
		requestLogger.info("request headers = " + request.headers());
		NSTimestamp startTime = new NSTimestamp();

		WOResponse response = null;
		try {
			// execute super's implementation
			response = super.dispatchRequest(request);
		}
		finally {
			
//			NSDictionary userInfo = request.userInfo();
//			if (userInfo != null) {
//				NSDictionary mutableDict = (NSDictionary) userInfo.valueForKey("mutableDict");
//				
//				WOContext context = (WOContext) mutableDict.valueForKey("context");
//				
//				if (context.hasSession()) {
//					sessionStore().checkInSessionForContext(context);
//				}
//			}
		}
		NSTimestamp endTime = new NSTimestamp();
		requestLogger.info ("response headers = " + response.headers());
		requestLogger.info ("Finished dispatching URL: " + request.uri());
		
		// Note odd output formatting.  This makes it easy to grep out these lines
		// and load them into Excel as CSV for analysis
		requestLogger.info (",Elapsed Time," + new Double((endTime.getTime() - startTime.getTime())/1000.0).toString());
		
		return response;
	}

	/**
	 * Overriden to make sure not to get any deadlocks (see dispatchRequest) 
	 */
	@Override
	public WOContext createContextForRequest(WORequest request) {
		// setup user mutable dictionary to be available for the
		// rest of this request. request.userInfo() always
		// will return a immutable dictionary.
		
		log.info("Request: " + request);
		
		NSMutableDictionary<String, Object> mutableDict = new NSMutableDictionary<String, Object>();
		NSDictionary<String, Object> userInfo = request.userInfo();
		if (userInfo == null) {
			/* from scratch */
			request.setUserInfo(new NSDictionary<String, Object>(mutableDict, "mutableDict"));
		}
		else {
			/* incorperate existing userInfo */
			
			NSMutableDictionary<String, Object> appended = new NSMutableDictionary<String, Object>();
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
	 * catches Session-Timeout
	 */
    @Override
	public WOResponse handleSessionRestorationErrorInContext(WOContext aContext) {
		WORedirect mainPage = (WORedirect) pageWithName ("WORedirect", aContext);
		mainPage.setUrl(aContext.directActionURLForActionNamed("default",	noWOSID));

		return mainPage.generateResponse();
	}
	
	
	/**
	 * We handle all exceptions - nothing should go through the app to the user
	 */
	@Override
	public WOResponse handleException(Exception anException, WOContext aContext) {
		super.handleException(anException, aContext);
		
		sendExceptionEmail(anException, aContext, null);
		
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
	@Override
	public WOResponse handleActionRequestError(WORequest aRequest, Exception exception, String reason, WORequestHandler aHandler, String actionClassName, String actionName, Class actionClass, WOAction actionInstance) {

		log.fatal("Reason: " + reason);
		log.fatal("Classname: " + actionClassName);
		log.fatal("Action: " + actionName);
		log.fatal("Class: " + actionClass);

		exception.printStackTrace();

		WOContext aContext = new WOContext(aRequest);
		WOComponent errorPage = this.pageWithName(EVGenericErrorPage.class.getName(), aContext);
		
		sendExceptionEmail(exception, null, null);
		
		return errorPage.generateResponse();
	}
	
	public void sendExceptionEmail (Exception exception, WOContext context, NSDictionary<String, ?> userInfo) {
		// send only in production
		if (!isDevelopmentMode()) {
			try {
				generateAndSendExceptionEmail(exception, null, null);
			}
			catch (Exception e) {
				// just dump it
				e.printStackTrace();
			}
		}
	}
	
	protected abstract void generateAndSendExceptionEmail (Exception exception, WOContext context, NSDictionary<String, ?> userInfo);

}


