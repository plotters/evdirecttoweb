package net.events.page;



import net.events.extensions.*;

import org.apache.log4j.*;

import com.webobjects.appserver.*;
import com.webobjects.foundation.*;

import er.extensions.*;

/**
 * This component should be the base for all page level components. It provides handling for the 
 * navigation and some basic things like a convenience methog to get an EOQualifier for the client
 * the current user belongs to.
 * 
 * <br><br>
 * @author cug
 */
public abstract class EVPageLevelComponent extends WOComponent implements EVPageLevelInterface {

	// used for iterating over the subnavigation
	private EVNavigationEntry subNaviItem;
	
	// holds the subnavigation
	private NSArray subNaviList;
	
	// Logging
	// private ERXLogger log = ERXLogger.getERXLogger(PageLevelComponent.class);

    // holds the last page
	private WOComponent lastPage;

	// holds the navigation area
	private String mainNavigationArea = null; 


	private Logger log = Logger.getLogger(EVPageLevelComponent.class);

	/**
	 * Generated constructor 
	 * 
	 * @param aContext
	 */
	public EVPageLevelComponent(WOContext aContext) {
		super(aContext);
		
        // initialize the subnavigation with what we get from the subclass
		this.setSubNaviList(subNavigation());
	}
		
	/**
	 * We only push the current components navigation name to the ThreadStorage, so it can be highlighted.
	 * 
	 * <br><br>
	 * @see com.webobjects.appserver.WOComponent#awake()
	 */
	public void awake () {
		super.awake();
		
		// ((Session) this.session()).setCurrentNavigationitemName(this.mainNavigationArea());
		ERXThreadStorage.takeValueForKey(this.mainNavigationArea(), "CURRENT_NAVIGATION_ITEM_NAME");
		
	}
	
//	/**
//	 * Overridden to add logging support
//	 */
//	public void appendToResponse (WOResponse r, WOContext c) {
//		super.appendToResponse(r, c);
//
//		if (ERXProperties.booleanForKeyWithDefault("net.events.logging.LogToDB", false)) {
//			this.writeLogEntryToDB();
//		}
//	}
	
    
	/**
	 * Creates a new object for "entityName" on the page "pageName". pageName must be a 
	 * PageLevelEditComponent, because it must implement "createNewObjectForEntityName(entityName);"
	 * <br> 
	 * @param entityName - the name of the entity to be created
	 * @param pageName - the edit page to go to
	 *  
	 * @return the edit page for the entity, with a newly created object
	 */
	public WOComponent createNewEnterpriseObjectAndPage (String entityName, String pageName) {
    	EVPageLevelEditComponent nextpage = (EVPageLevelEditComponent) this.pageWithName(pageName);
    	
    	nextpage.createNewObjectForEntityName(entityName);
    	nextpage.setNextPage(this);
    	nextpage.setLastPage(this);
    	
    	// tell the nextpage that we want to have editing mode right now
    	nextpage.setEditing(true);
    	
    	return nextpage;
    }
    
	/**
	 * Returns an inspect page for an object
	 * 
	 * @param object - the object to be inspected
	 * @param pageName - the page name to use for inspecting the object
	 * 
	 * @return inspect page
	 */
    public WOComponent inspectObject(ERXGenericRecord object, String pageName) {
    	EVPageLevelEditComponent nextPage = (EVPageLevelEditComponent) this.pageWithName (pageName);
    	
    	// what to inspect
    	nextPage.setObject(object);
    	
    	// where to ga after
    	nextPage.setNextPage(this);
    	nextPage.setLastPage(this);
    	
    	// we want to inspect, not edit
    	nextPage.setEditing(false);
    	
    	return nextPage;
    }

	
    /**
     * Simple navigation method - just uses page with name to create and serve a page for the clicked navi entry
     * @return next page
     */
	public WOComponent navigate () {
		if (subNaviItem.componentName() != null) {
			return this.pageWithName(this.subNaviItem.componentName());
		}
		else return null;
	}
	/**
	 * @return the subNaviItem
	 */
	public EVNavigationEntry subNaviItem() {
		return this.subNaviItem;
	}

	/**
	 * @param subNaviItem the subNaviItem to set
	 */
	public void setSubNaviItem(EVNavigationEntry subNaviItem) {
		this.subNaviItem = subNaviItem;
	}

	/**
	 * @return the subNaviList
	 */
	public NSArray subNaviList() {
		return this.subNaviList;
	}

	/**
	 * @param subNaviList the subNaviList to set
	 */
	public void setSubNaviList(NSArray subNaviList) {
		this.subNaviList = subNaviList;
	}
	
	public NSArray subNavigation() {
		return null;
	}
	
	public String mainNavigationArea() {
		return this.mainNavigationArea;
	}
	
	public void setMainNavigationArea(String s) {
		this.mainNavigationArea = s;
	}

	/**
	 * @return the lastPage
	 */
	public WOComponent lastPage() {
		return this.lastPage;
	}

	/**
	 * @param lastPage the lastPage to set
	 */
	public void setLastPage(WOComponent lastPage) {
		this.lastPage = lastPage;
	}
	
//	/**
//	 * This method uses the logging model from this WebObjectExtensions to write page level logging entries in
//	 * the database if the property "net.events.logging.LogToDB" is set to true. 
//	 * <br><br>
//	 * Default is, not to write anything.
//	 */
//	protected void writeLogEntryToDB () {
//		ERXEC editingContext = (ERXEC) ERXEC.newEditingContext();
//		PageLogEntry entry = (PageLogEntry) ERXEOControlUtilities.createAndInsertObject(editingContext, "PageLogEntry");
//
//		entry.setSessionId(this.session().sessionID());
//		entry.setClientIp(((ERXRequest) this.context().request()).remoteHost());
//		
//		entry.setPagename(ERXStringUtilities.lastPropertyKeyInKeyPath(this.getClass().getName()));
//		entry.setRequestUrl(this.context().request().uri());
//		entry.setReferer(this.context().request().headerForKey("referer"));
//		
//		entry.setBrowser(((ERXRequest) this.context().request()).browser().browserName() + " Version: " + ((ERXRequest) this.context().request()).browser().version());
//		entry.setHttpHeader(this.context().request().headers().toString());
//		entry.setPlattform(((ERXRequest) this.context().request()).browser().platform());
//		
//		// Session (or anybody else early in RR-Loop) has to push this to the ERXThreadStorage
//		entry.setLogin((String) ERXThreadStorage.valueForKey("USER_LOGIN"));
//		
//		try {
//			editingContext.saveChanges();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		editingContext = null;
//	}
	
}
