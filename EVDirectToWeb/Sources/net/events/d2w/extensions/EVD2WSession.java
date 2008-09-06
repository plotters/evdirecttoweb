package net.events.d2w.extensions;

import net.events.appserver.EVSession;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WORedirect;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.ListPageInterface;
import com.webobjects.directtoweb.QueryPageInterface;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.foundation.NSKeyValueCoding;

import er.extensions.appserver.ERXApplication;
import er.extensions.foundation.ERXProperties;

public class EVD2WSession extends EVSession {

	/**
	 * Generic page for a keypath
	 * @return
	 */
    public Object genericPage() {
    	return new NSKeyValueCoding() {
			public void takeValueForKey(Object value, String key) {
				// nothing
			}

			public Object valueForKey(String key) {
				return d2wPageWithName(key);
			}
    		
    	};
    }
    
    /**
     * Returns a list page for an entity name provided by key value coding key path
     * @return
     */
    public Object listPage () {
    	return new NSKeyValueCoding() {
			public void takeValueForKey(Object value, String key) {
				// nothing
			}

			public Object valueForKey(String key) {
				return d2wListPageForEntity(key);
			}
    	};
    }
    
    /**
     * Returns a query page for an entity name provided by key value coding key path
     * @return
     */
    public Object queryPage () {
    	return new NSKeyValueCoding() {
			public void takeValueForKey(Object value, String key) {
				// nothing
			}

			public Object valueForKey(String key) {
				return d2wQueryPageForEntity(key);
			}
    	};
    }
    
    /**
     * Returns a query page for an entity name provided by key value coding key path
     * @return
     */
    public Object createPage () {
    	return new NSKeyValueCoding() {
			public void takeValueForKey(Object value, String key) {
				// nothing
			}

			public Object valueForKey(String key) {
				return d2wCreateObjectPageForEntity(key);
			}
    	};
    }
    
    public Object pageForPageConfiguration () {
    	return new NSKeyValueCoding() {
			public void takeValueForKey(Object value, String key) {
				// nothing
			}

			public Object valueForKey(String key) {
				return d2wPageForConfiguration(key);
			}
    	};
    }

    
    /**
     * Returns a WOComponent by calling the applications pageWithName method
     * 
     * @param pagename
     * @return page for the given name
     */
    public WOComponent pageWithName(String pagename) {
		return ERXApplication.application().pageWithName(pagename, context());
	}

    /**
     * Create a D2W page for a given pageConfiguration
     * @param pageConfigurationName
     * @return
     */
    public WOComponent d2wPageWithName(String pageConfigurationName) {
		return D2W.factory().pageForConfigurationNamed(pageConfigurationName, this);
	}
    
    /**
     * Creates and returns a list page for a given entity name
     * @param entityName
     * @return
     */
    public WOComponent d2wListPageForEntity (String entityName) {
		if (log.isDebugEnabled()) log.debug ("Creating list page for entity named: " + entityName);
		
		try {
			ListPageInterface nextpage = D2W.factory().listPageForEntityNamed(entityName, this);
			nextpage.setNextPage(this.context().page());
			EODatabaseDataSource ds = new EODatabaseDataSource (this.defaultEditingContext(), entityName);
			nextpage.setDataSource(ds);
			
			return (WOComponent) nextpage;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    /**
     * Creates and returns a query page for a given entity name
     * @param entityName
     * @return
     */
    public WOComponent d2wQueryPageForEntity (String entityName) {
		if (log.isDebugEnabled()) log.debug ("Creating list page for entity named: " + entityName);
		
		try {
			QueryPageInterface nextpage = D2W.factory().queryPageForEntityNamed(entityName, this);
			return (WOComponent) nextpage;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * Creates and returns an edit page for a given entity name and a new object
     * @param entityName
     * @return
     */
    public WOComponent d2wCreateObjectPageForEntity (String entityName) {
    	if (log.isDebugEnabled()) log.debug ("Creating create page for entity named: " + entityName);
    	
    	try {
    		EditPageInterface nextpage = D2W.factory().editPageForNewObjectWithEntityNamed(entityName, this);
    		ListPageInterface listpage = (ListPageInterface) d2wListPageForEntity(entityName);
    		nextpage.setNextPage((WOComponent) listpage);
    		return (WOComponent) nextpage;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /**
     * Returns a page for a given page configuration
     * 
     * @param pageConfigurationName
     * @return
     */
    public WOComponent d2wPageForConfiguration (String pageConfigurationName) {
    	if (log.isDebugEnabled()) log.debug ("Creating page for page configuration named: " + pageConfigurationName);
    	return D2W.factory().pageForConfigurationNamed(pageConfigurationName, this);
    }
    
    /**
     * Returns the a boolean value for the given key, false if the key does not exist
     * 
     * @param key
     * @return
     */
    public Boolean booleanPropertyForKeyPath (String keyPath) {
    	return new Boolean(ERXProperties.booleanForKeyWithDefault(keyPath, false));
    }
    
    /**
     * Returns valueForKeypath, overridden to return values set in the properties
     */
    public Object valueForKeyPath (String keyPath) {
    	if (keyPath != null && keyPath.startsWith("booleanPropertyForKey")) {
    		return this.booleanPropertyForKeyPath(keyPath.replaceFirst("booleanPropertyForKey.", ""));
    	}
    	else {
    		return super.valueForKeyPath(keyPath);
    	}
	}
    
    /**
     * Logs the user out and terminates the session
     * 
     * @return
     */
    public WOComponent logout() {
        WOComponent redirectPage = pageWithName(WORedirect.class.getName());
        ((WORedirect) redirectPage).setUrl(D2W.factory().homeHrefInContext(context()));
        session().terminate();
        return redirectPage;
    }
}
