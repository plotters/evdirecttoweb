package net.events.appserver;
import org.apache.log4j.Logger;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOMessage;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOSharedEditingContext;
import com.webobjects.foundation.*;

import er.extensions.ERXApplication;
import er.extensions.ERXMessageEncoding;
import er.extensions.ERXProperties;

/**
 * Application superclass for better handling of exception thrown in the request-response loop.
 * Works in addidtion to EVDirectAction.
 * <br><br>
 * 
 * This class also installs comparison support with Java locales for EOSortOrdering and sets
 * the default message encoding.
 * <br><br>
 * 
 * Used properties (only the ones used in our own methods are listed here, look at ERExtensions - Properties for more):
 * <ul>
 * 	<li>net.events.Application.installComparisonSupport - boolean value, default is false</li>
 * 	<li>net.events.Application.setDefaultMessageEncoding - boolean value, default is true</li>
 * 	<li>net.events.Application.useSharedEditingContext - boolean value, default is true</li>
 * </ul>
 * 
 * Properties used from ERExtensions:
 * <ul>
 * 	<li>er.extensions.ERXApplication.DefaultMessageEncoding - string value, used by ERXApplication</li>
 * 	<li>er.extensions.ERXMessageEncoding.Enabled - boolean value, used by ERXApplication</li>
 * 	<li>er.extensions.ERXRequest.BrowserFormValueEncodingOverrideEnabled - boolean value, used by ERXApplication</li>
 * </ul>
 *  
 * @author cug
 */
public class EVApplication extends ERXApplication {
	
	private static Logger log = Logger.getLogger(EVApplication.class);
	private static Logger requestLogger = Logger.getLogger("RequestHandling");
	
	public EVApplication () {
		super();
		
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

}
