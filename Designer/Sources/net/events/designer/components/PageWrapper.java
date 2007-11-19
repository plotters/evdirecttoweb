package net.events.designer.components;

import net.events.cms.eo.*;

import com.webobjects.appserver.*;
import com.webobjects.directtoweb.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

/**
 * PageWrapper for the admin part of the application
 * 
 * @author cug
 *
 */
public class PageWrapper extends WOComponent {
	
	/**
	 * client for iteration in popup 
	 */
	public Client client;
	
	/**
	 * Cached array of clients 
	 */
	private NSArray<EOGenericRecord> clients;
	
	/**
	 * show link? 
	 */
	public boolean _showLink = true;

    /**
     * Standard constructor
     * 
     * @param aContext
     */
    public PageWrapper(WOContext aContext) {
        super(aContext);
    }
    
    /**
     * Returns the current navigation context
     * @return
     */
    public NSKeyValueCoding navigationContext() {
        NSKeyValueCoding context = (NSKeyValueCoding)session().objectForKey("navigationContext");
        
        if (context().page() instanceof D2WPage) {
            context = ((D2WPage)context().page()).d2wContext();
        }
		return context;
	}
    
    /**
     * Returns the current d2wContext if there is one
     * 
     * @return
     */
    public D2WContext d2wContext() {
    	if (context().page() instanceof D2WPage) {
			D2WPage d2wPage = (D2WPage) context().page();
			return d2wPage.d2wContext();
		}
    	return null;
    }
    
    /**
     * Current page name
     * @return
     */
    public String pageName() {
    	D2WContext context = d2wContext();
    	String result = null;
    	if(context != null) {
    		result = context.dynamicPage();
    		if(result == null) {
    			result = context.task();
    		}
    	}
    	if(result == null) {
    		result = "DontKnow";
    	}
    	return result;
    }
    
    
    /**
     * Cached array of clients
     * 
     * @return
     */
    public NSArray<EOGenericRecord> clients () {
    	if (clients == null) {
    		clients = ERXArrayUtilities.sortedArraySortedWithKey(Client.fetchAllObjects(session().defaultEditingContext()), Client.NAME);
    	}
    	return clients;
    }
    
    /**
     * Should show clients link
     * 
     * @return
     */
    public boolean showLink () {
    	return _showLink;
    }
    
    /**
     * Show clients popup
     * 
     * @return
     */
    public WOComponent showPopup () {
    	_showLink = false;
    	return null;
    }
    
    /**
     * Select the client, set it in the session
     * 
     * @return
     */
    public WOComponent selectClient () {
    	_showLink = true;
    	if (this.d2wContext() != null && this.d2wContext().entity() != null) {
    		if (this.context().page() instanceof QueryPageInterface) {
    			return (WOComponent) this.valueForKeyPath("session.queryPage." + this.d2wContext().entity().name());
    		}
    		return (WOComponent) this.valueForKeyPath("session.listPage." + this.d2wContext().entity().name());
    	}
    	else return D2W.factory().defaultPage(this.session());
    }
    
    /**
     * User canceled the selection of a client 
     * 
     * @return
     */
    public WOComponent cancel () {
    	_showLink = true;
    	return null;
    }
    
}
