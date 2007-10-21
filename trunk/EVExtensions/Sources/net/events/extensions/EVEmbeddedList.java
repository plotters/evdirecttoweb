package net.events.extensions;


import java.util.Enumeration;

import net.events.page.EVPageLevelComponent;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.eoaccess.EOGeneralAdaptorException;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOArrayDataSource;
import com.webobjects.eocontrol.EOClassDescription;
import com.webobjects.eocontrol.EOCustomObject;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOObjectStore;
import com.webobjects.eocontrol.EOOrQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSNotification;
import com.webobjects.foundation.NSNotificationCenter;
import com.webobjects.foundation.NSSelector;

import er.extensions.ERXArrayUtilities;
import er.extensions.ERXBatchingDisplayGroup;
import er.extensions.ERXConstant;
import er.extensions.ERXEC;
import er.extensions.ERXEOControlUtilities;
import er.extensions.ERXGenericRecord;
import er.extensions.ERXProperties;
import er.extensions.ERXThreadStorage;

/**
 *
 * This component is intended to be used as a subcomponent on a wrapper page like the 
 * base pages "PageLevelListComponent" or similar.
 * <br><br>
 * It provides everything you need to display a list of objects in a subcomponent like 
 * the ObjectTableComponent or directly. You get:
 * <ul>
 *  <li>a displayGroup (ERXBatchingDisplayGroup)</li>
 *  <li>an editing context to fetch in</li>
 *  <li>a mutable array of sort orderings (you can add objects and/or reset it)</li>
 * </ul>
 *  
 *  This class is abstract, so you have to subclass it and provide some methods of your own
 *  to handle the list content. You do this with setting up qualifiers and adding them
 *  to the qualifier in this component.<br><br> 
 *  
 *  <b>Short tutorial:</b>
 *  
 *  <ul>
 *  	<li>You MUST have user logged in your app, the user MUST have a "client" attribute</li>
 *  	<li>Push the user into the ERXThreadStorage with the key "CURRENT_USER" during session's awake</li>
 *  	<li>If you don't have a user with a client, you MUST override "useStandardQualifier" to return false</li>
 *  	<li>Create a wo component and make it a subclass of this component</li>
 *  	<li>Implement all abstract methods from this class</li>
 *  	<li>Add a subclass of ObjectTableComponent to your html and bind all necessary values</li>
 *  	<li>done ...</li>
 *  </ul>
 *  
 *  Required bindings:<br><br>
 *  
 *  @binding contentSelector - provide a String you can use to decide, what qualifiers you have to build
 *  @binding refreshContent - a boolean whether this component shall refresh its content
 *  @binding batchSize - the batch size for the integrated displayGroup
 *  
 *  <br><br>
 * 
 * @author cug
 */
public abstract class EVEmbeddedList extends WOComponent {
	
	// private editing context
	private ERXEC editingContext;

	// standard sort orderings
	private NSMutableArray sortOrderings;
	
	// we use this in the displaygroup
	private EOFetchSpecification fetchSpecification;

	// display group used for fetching the projects in batches from the db for
	// performance reasons (we may have hundreds or even thousands of projects for a large
	// company)
	private WODisplayGroup _displayGroup;
	
	// holds the qualifier
	private EOQualifier qualifier;
	
	// logging support
	protected Logger log = Logger.getLogger(EVEmbeddedList.class);
	
	// Set with code. 
	private boolean refreshContentCode = false;

	// the batch size for the displaygroup, set by bindings
	private Integer batchSize;
	
	// the object to iterate
	private ERXGenericRecord object;

	// we use this to switch even and odd table rows css class
	protected boolean odd = true;
	
	// should we show a title over the list?
	private Boolean showTitle;
	
	// if used for displaying an object array
	private NSArray objectList;
	
	//
	private EOQualifier externalQualifier = null;

	private boolean initialized;

	private String entityName;
	
	// Should we try to show a list when the qualifier is empty?
	private Boolean qualifyOnEmptyQualifier = Boolean.FALSE;
	
	/**
	 * Creates an EmbeddedList object and sets:
	 * <ul>
	 * 	<li>editingContext to a new ERXEC
	 * 	<li>qualifier to the clientQualifier
	 * </ul>
	 * 
	 * Also the initial fetch specification method is called, so be sure, to do something useful
	 * in there.
	 * 
	 * @param context
	 */
	public EVEmbeddedList (WOContext context) {
        super(context);
        
//		// initialize our editing context
//        // editingContext = (ERXEC) ERXEC.newEditingContext();
//        
//        // we go with the default editing context for now, perhaps it saves some memory/time
//        editingContext = (ERXEC) ERXEC.newEditingContext();
//
//		// initialize qualifier with the client
//		this.qualifier = this.clientQualifier();
//		
//		this.prepareInitialFetchSpecification();

		NSNotificationCenter.defaultCenter().addObserver(this, 
						new NSSelector("editingContextDidSaveChanges", ERXConstant.NotificationClassArray), 
						EOEditingContext.EditingContextDidSaveChangesNotification, null);
	}
	
	public void appendToResponse (WOResponse r, WOContext c) {
		if (!this.initialized) {
			if (log.isDebugEnabled()) log.debug("First initialization of EmbeddedList for " + this.entityName());
			// initialize our editing context
	        // editingContext = (ERXEC) ERXEC.newEditingContext();
	        
	        editingContext = (ERXEC) ERXEC.newEditingContext();

			// initialize qualifier with the client
			this.qualifier = this.clientQualifier();
			
			this.prepareInitialFetchSpecification();
			this.initialized = true;
		}
		super.appendToResponse(r, c);
	}
	
	public void editingContextDidSaveChanges (NSNotification notif) {
		
		NSDictionary userinfo = notif.userInfo();
		
		NSMutableArray changedObjects = new NSMutableArray();
		
		changedObjects.addObjectsFromArray((NSArray) userinfo.objectForKey(EOObjectStore.UpdatedKey));
		changedObjects.addObjectsFromArray((NSArray) userinfo.objectForKey(EOObjectStore.InsertedKey));
		changedObjects.addObjectsFromArray((NSArray) userinfo.objectForKey(EOObjectStore.DeletedKey));
		
		NSArray onlyOneEntityPerName = ERXArrayUtilities.arrayWithoutDuplicateKeyValue(changedObjects, "entityName");
		
		Enumeration enumeration = onlyOneEntityPerName.objectEnumerator();

		NSMutableDictionary entities = new NSMutableDictionary(); 
		while (enumeration.hasMoreElements()){
			EOCustomObject item = (EOCustomObject) enumeration.nextElement();
			entities.setObjectForKey(Boolean.TRUE, item.entityName());
		}
		
		if (this.entityNames() == null) {
			if (entities.objectForKey(this.entityName()) != null) {
				this.refreshContentCode = true;
			}
		}
		else {
			Enumeration enumeration2 = this.entityNames().objectEnumerator();
			while (enumeration2.hasMoreElements()) {
				if (entities.objectForKey(enumeration2.nextElement()) != null) {
					this.refreshContentCode = true;
					break;
				}
			}
		}
	}
	
	/**
	 * Empty, does nothing except calling super at the moment.
	 */
	public void awake () {
		super.awake();
		
		this.odd = true;
	}


	/**
	 * Implement this method to build an initial fetchspecification. This may be quite easy:<br><br>
	 * 
	 * <code>this.setFetchSpecification(new EOFetchSpecification("Activity", this.qualifier(), null));</code>
	 * <br><br>
	 * You can use the initial qualifier which qualifies on "client" and "not deleted" 
	 * and retrieve all "Activities" from the database. You may use "entityname" here, 
	 * to have only one point where you set the entity to work on.
	 * <br><br>
	 * In order to have your own base qualifier, just override "resetQualifier" and provide whatever you 
	 * like in there.
	 * <br><br>
	 * You definitely have to set an entityname in the fetchspec, the rest is not as important.
	 */
	public abstract void prepareInitialFetchSpecification();
	
    /**
     * Returns the entity name for the list - set it via "entityName" binding
     */
	public String entityName() {
		return this.entityName;
	}
	
	/**
	 * Set the entity name for the list - set it via "entityName" binding
	 * @param s
	 */
	public void setEntityName (String s) {
		this.entityName = s;
	}
	
	
	/**
	 * Override this method to return an NSArray of strings for entity names you are responsible for.
	 * Then the list will be refreshed when an editing context saveChanges message comes in via 
	 * notification.
	 *  
	 * @return array of entity names e.g. <code>new NSArray (new String[] {"Call", "Task"});</code>
	 */
	public NSArray entityNames () {
		return null;
	}
	
	/**
	 * Override this method to set a different number of objects before switching to batching displaygroup.
	 * Return 0 to always use batching displaygroup if <code>shouldUseBatchingDisplayGroup()</code> returns 
	 * true.<br><br>
	 * This method does NOT override <code>shouldUseBatchingDisplayGroup()</code>, it is instead an addition 
	 * to it. With using this, you an additional call to the database, each time the displayGroup is refreshed,
	 * to get the number of objects for the current qualifier. This may slow things down, if select count(*) ... is
	 * slow for the qualifier.
	 * 
	 * @return standard is 200 
	 */
	public int maxObjectCountBeforeUsingBatchingDisplayGroup() {
		return 200;
	}

	/**
	 * Returns the display group for the currently available objects (depends
	 * on the fetch specification) - you can also bind an object array to this component
	 * at the objectList binding. 
	 * 
	 * This methods needs an entityName provided by the subclass as string to build the datasource!
	 * So implement the entityName() method by returning the name of the entity you want to fetch from
	 * the database.
	 * 
	 * @return displayGroup
	 */
	public WODisplayGroup displayGroup() {
		if (_displayGroup == null || this.refreshContentCode) {
			
			// if (this.objectList() == null && this.qualifier() != null) {
			if (this.objectList() == null && (this.qualifier() != null || this.qualifyOnEmptyQualifier.booleanValue())) {
				if (log.isDebugEnabled()) log.debug ("Re-creating displayGroup");
				
				if (this.shouldUseBatchingDisplayGroup()) {
					if (this.maxObjectCountBeforeUsingBatchingDisplayGroup() == 0 ||
							ERXEOControlUtilities.objectCountWithQualifier(editingContext, this.entityName(), this.fetchSpecification().qualifier()).intValue() 
							> this.maxObjectCountBeforeUsingBatchingDisplayGroup()) {
						
						_displayGroup = new ERXBatchingDisplayGroup();
					}
					else _displayGroup = new WODisplayGroup();
	
				}
				else {
					_displayGroup = new WODisplayGroup();
				}
				
				if (log.isDebugEnabled()) log.debug ("DisplayGroup: " + _displayGroup);
				
				_displayGroup.setNumberOfObjectsPerBatch(this.batchSize().intValue());
				
				EODatabaseDataSource ds = new EODatabaseDataSource(editingContext, this.entityName());
				ds.setFetchSpecification(this.fetchSpecification());
					
				_displayGroup.setDataSource(ds);
				_displayGroup.setSortOrderings(this.sortOrderings());
				_displayGroup.fetch();
			}
			else {
				if (log.isDebugEnabled()) log.debug ("Re-creating displayGroup for NSArray display");
				
				this._displayGroup = new WODisplayGroup();
				EOArrayDataSource ds = new EOArrayDataSource (EOClassDescription.classDescriptionForEntityName(this.entityName()), editingContext);
				ds.setArray(this.objectList());
				
				_displayGroup.setDataSource(ds);
				_displayGroup.setSortOrderings(this.sortOrderings());
				_displayGroup.setNumberOfObjectsPerBatch(this.batchSize().intValue());
				_displayGroup.fetch();
			}
			this.refreshContentCode = false;
		}
		
		return _displayGroup;
	}
	
	/**
	 * Implements sorting by keypaths. To use this, add a WOHyperlink to your list header for the column
	 * you want to order on. Set the action binding to "orderBy" and provide a url parameter with the order
	 * key like this:<br><br>
	 * ?key = "name";<br><br>
	 * 
	 * or for a relationship attribute:<br><br>
	 * ?key = "relationship.attribute";<br><br>
	 * This methods maintains two order keys in an array, so, if you order on a new attribute, 
	 * it pushes the old attribute to the secondary order attribute and sets the new one as the 
	 * primary. If you click twice on the same link, the primary sort order is reversed.<br><br>
	 * 
	 * Make sure, to only provide keys available to the objects in the list.
	 * 
	 * @return same page, new sort ordering applied
	 */
	public WOComponent orderBy () {
		String key = this.context().request().stringFormValueForKey("key");
		
		// we keep two attributes for the ordering
		
		if (this.sortOrderings() == null || this.sortOrderings().count() == 0) {
			// we have no sort orderings, so set a new one with key, ascending
			this.setSortOrderings(new NSMutableArray(EOSortOrdering.sortOrderingWithKey(key, EOSortOrdering.CompareCaseInsensitiveAscending)));
		}
		
		if (this.sortOrderings().count() > 0) {
			// first case: we have sortOrderings in place right now, so we check whether
			// the first one sorts on the same key, as the new one
			
			if (((EOSortOrdering) this.sortOrderings().objectAtIndex(0)).key().equals(key)) {
				// YES, we go on the same key, so we only switch the sort order from
				// ascending to descending or vice versa
				
				if (log.isDebugEnabled()) log.debug ("Primary Selector: " + this.sortOrderings().objectAtIndex(0));
				if (log.isDebugEnabled()) log.debug ("Switching selectors only");

				// first get the sort ordering
				EOSortOrdering item = (EOSortOrdering) this.sortOrderings().objectAtIndex(0);
				NSSelector sel;
				
				if (item.selector().equals(EOSortOrdering.CompareAscending) || 
								item.selector().equals(EOSortOrdering.CompareCaseInsensitiveAscending)) {
					// it was ascending, set to descending
					if (log.isDebugEnabled()) log.debug ("Switch to sort descending");
					sel = EOSortOrdering.CompareCaseInsensitiveDescending;
				}
				else {
					// it was descending, set to ascending
					if (log.isDebugEnabled()) log.debug ("Switch to sort ascending");
					sel = EOSortOrdering.CompareCaseInsensitiveAscending;
				}
				// set item to a new sort ordering on the same key, switched selector
				item = EOSortOrdering.sortOrderingWithKey(key, sel);
				this.sortOrderings().replaceObjectAtIndex(item, 0);
				
			}
			else if (this.sortOrderings().count() > 1 && ((EOSortOrdering) this.sortOrderings().objectAtIndex(1)).key().equals(key)) {
				// we should use our currently secondary sort key as the primary, so we just switch
				EOSortOrdering newOrdering = (EOSortOrdering) this.sortOrderings().objectAtIndex(1);
				NSMutableArray tmp = new NSMutableArray(newOrdering);
				tmp.addObject(this.sortOrderings().objectAtIndex(0));
				
				this.setSortOrderings(tmp);
			}
			else {
				// our first sort ordering goes to a different attribute, so we shift it to the next slot
				// and insert our own object here
				
				if (log.isDebugEnabled()) log.debug ("Primary Selector: " + this.sortOrderings().objectAtIndex(0));
				if (log.isDebugEnabled()) log.debug ("Replacing last primary sort ordering with new one, shifting the to secondary");
				
				EOSortOrdering newOrdering = EOSortOrdering.sortOrderingWithKey(key, EOSortOrdering.CompareCaseInsensitiveAscending);
				NSMutableArray tmp = new NSMutableArray(newOrdering);
				tmp.addObject(this.sortOrderings().objectAtIndex(0));
				
				this.setSortOrderings(tmp);
			}
		}
		
		if (log.isDebugEnabled()) log.debug ("New orderings are: " + this.sortOrderings());
		
		this.setRefreshContentCode(true);
		
		return null;
	}
	

	/**
	 * Deletes the selected object from the database
	 * 
	 * @return same page
	 */
	public WOComponent deleteObject () {
		log.info ("Deleting object: " + this.object());
		
		ERXEC editingContext = (ERXEC) this.object().editingContext();
		editingContext.deleteObject(this.object());
		
		try {
			editingContext.saveChanges();
			
			// wait ... wait ... wait ... no crash - we successfully deleted our object
			// if someone now wants to use the back button she should not see our object again,
			// therefor we insert a new blank object
		}
		catch (EOGeneralAdaptorException ae) {
			// FIXME place a more correct message here
			throw new EVDeletedObjectException (EVDeletedObjectException.DELETED_OBJECT_MESSAGE);
		}
		catch (Exception e) {
			// FIXME do some more handling here 
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Shall we use our standard qualifier?
	 * 
	 * @return true or false
	 */
	public boolean useStandardQualifier () {
		return true;
	}
	
	/**
	 * Shall we use the batching display group
	 * @return
	 */
	public boolean shouldUseBatchingDisplayGroup() {
		return true;
	}
	
	/**
	 * Convenience method to have the client qualifier available whereever we need it.
	 * This method relies on the ERXThreadStorage to get a user object with the key "CURRENT_USER"
	 * which must return a client object with the key "client" to be used in a "client = %@" comparison.
	 * 
	 * @return EOQualifier with "client = %@ and deleted = 0" that gets the value from the current user
	 */
	protected EOQualifier clientQualifier () {
		if (this.useStandardQualifier()) {
			EOEnterpriseObject eo = (EOEnterpriseObject) ERXThreadStorage.valueForKey("CURRENT_USER");
			return EOQualifier.qualifierWithQualifierFormat("client = %@ and deleted = 0", new NSArray(eo.valueForKey("client")));
		}
		else return null;
	}
	
	/**
	 * Add q to the current qualifier, combine them with "and"
	 * @param q
	 */
	public void addQualifierToQualifierWithAnd (EOQualifier q) {
		if (log.isDebugEnabled()) log.debug ("Add qualifier (and): " + q);
		
		if (this.qualifier() != null) {
			EOAndQualifier tmp = new EOAndQualifier(new NSArray (new EOQualifier[] {this.qualifier(), q}));
			this.qualifier = tmp;
		}
		else {
			this.qualifier = q;
		}
		this.setRefreshContentCode(true);
	}
	
	/**
	 * Add q to the current qualifier, combine them with "or"
	 * 
	 * @param q
	 */
	public void addQualifierToQualifierWithOr (EOQualifier q) {
		if (log.isDebugEnabled()) log.debug ("Add qualifier (or): " + q);

		if (this.qualifier != null) {
			EOOrQualifier tmp = new EOOrQualifier (new NSArray (new EOQualifier[] {this.qualifier(), q}));
			this.qualifier = tmp;
		}
		else {
			this.qualifier = q;
		}
		this.setRefreshContentCode(true);
	}
	
	/**
	 * Reset the qualifier to its standard: only select on "client and not deleted"
	 */
	public void resetQualifier () {
		if (useStandardQualifier()) {
			if (log.isDebugEnabled()) log.debug ("Resetting qualifier to client qualifier");
			this.qualifier = this.clientQualifier();
		}
		else {
			this.qualifier = null;
		}
		this.fetchSpecification().setQualifier(qualifier);
		this.setRefreshContentCode(true);
	}
	
	/**
	 * The current qualifier
	 * 
	 * @return the qualifier
	 */
	protected EOQualifier qualifier() {
		if (this.externalQualifier() == null) {
			return this.qualifier;
		}
		else {
			return this.externalQualifier();
		}
	}
	
	/**
	 * Set the qualifier to a specified one
	 * 
	 * @param q
	 */
	protected void setQualifier (EOQualifier q) {
		this.qualifier = q;
	}

	/**
	 * @return the editingContext
	 */
	protected ERXEC editingContext() {
		return this.editingContext;
	}

	/**
	 * @param editingContext the editingContext to set
	 */
	protected void setEditingContext(ERXEC editingContext) {
		this.editingContext = editingContext;
	}

	/**
	 * @return the sortOrderings
	 */
	public NSMutableArray sortOrderings() {
		return this.sortOrderings;
	}

	/**
	 * @param sortOrderings the sortOrderings to set
	 */
	public void setSortOrderings(NSMutableArray sortOrderings) {
		this.sortOrderings = sortOrderings;
	}

	/**
	 * @return the fetchSpecification
	 */
	protected EOFetchSpecification fetchSpecification() {
		// reset the qualifiers, they may have changed
		if (this.qualifier() != null && this.fetchSpecification != null) {
			this.fetchSpecification.setQualifier(this.qualifier());
		}
		
		// and return the fs
		return this.fetchSpecification;
	}

	/**
	 * @param fetchSpecification the fetchSpecification to set
	 */
	protected void setFetchSpecification(EOFetchSpecification fetchSpecification) {
		if (log.isDebugEnabled()) log.debug ("Setting fetch specification: " + fetchSpecification);

		this.fetchSpecification = fetchSpecification;
	}

	/**
	 * The batch size for the integrated display group
	 * @return - integer
	 */
	public Integer batchSize() {
		return batchSize;
	}
	
	/**
	 * The batch size for the integrated display group
	 * @param i
	 */
	public void setBatchSize (Integer i) {
		if (log.isDebugEnabled()) log.debug ("Setting the batchSize to: " + i);
		this.batchSize = i;	
	}

	/**
	 * @return the refreshContentCode
	 */
	protected boolean refreshContentCode() {
		return this.refreshContentCode;
	}

	/**
	 * @param refreshContentCode the refreshContentCode to set
	 */
	protected void setRefreshContentCode(boolean refreshContentCode) {
		this.refreshContentCode = refreshContentCode;
	}
	
	public WOComponent inspectObjectWithPageFromProperties () {
		return this.inspectObject(object, ERXProperties.stringForKeyWithDefault("net.events.pageForObjectDetail." + this.entityName(), null));
	}

	/**
	 * Returns a page to inspect an object, current page MUST inherit from PageLevelComponent
	 * 
	 * @param object - the object to inspect
	 * @param pageName - the component's name to use
	 * @return inspect component 
	 */
	public WOComponent inspectObject (ERXGenericRecord object, String pageName) { 
		EVPageLevelComponent page = (EVPageLevelComponent) this.context().page();
		return page.inspectObject (object, pageName);
	}

	/**
	 * @return the object
	 */
	public ERXGenericRecord object() {
		return this.object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(ERXGenericRecord object) {
		this.object = object;
	}

	/**
	 * CSS class for table rows - eg. for alternating background color
	 * @return the class (tl_even or tl_odd)
	 */
    public String rowClass () {
    	odd = !odd;
    	if (odd) {
    		return "tl_odd";
    	}
    	else return "tl_even"; 
    }

	/**
	 * @return the showTitle
	 */
	public Boolean showTitle() {
		if (this.showTitle == null) {
			return Boolean.TRUE;
		}
		return this.showTitle;
	}

	/**
	 * @param showTitle the showTitle to set
	 */
	public void setShowTitle(Boolean showTitle) {
		this.showTitle = showTitle;
	}

	/**
	 * @return the objectList
	 */
	public NSArray objectList() {
		return this.objectList;
	}

	/**
	 * @param objectList the objectList to set
	 */
	public void setObjectList(NSArray objectList) {
		this.objectList = objectList;
	}

	/**
	 * This completely overrides the usage of the qualifier build by this component, so
	 * you are responsible for maintaining this qualifier.
	 * 
	 * @return the externalQualifier
	 */
	public EOQualifier externalQualifier() {
		return externalQualifier;
	}

	/**
	 * Set an external qualifier with this binding to a qualifier, you want to use.
	 * This completely overrides the usage of the qualifier build by this component, so
	 * you are responsible for maintaining this qualifier.
	 * 
	 * @param externalQualifier the externalQualifier to set
	 */
	public void setExternalQualifier(EOQualifier externalQualifier) {
		
		// we refresh only, if we get a new qualifier, this is absolutely necessary for paging!
		if (this.externalQualifier != externalQualifier) {
			this.setRefreshContentCode(true);
		}
		this.externalQualifier = externalQualifier;
	}

	/**
	 * If you set this to true, the list fetches on an empty qualifier, if set to false, it does not
	 * fetch in this situation.
	 * 
	 * @return the qualifyOnEmptyQualifier
	 */
	public Boolean qualifyOnEmptyQualifier() {
		return qualifyOnEmptyQualifier;
	}

	/**
	 * @param qualifyOnEmptyQualifier the qualifyOnEmptyQualifier to set
	 */
	public void setQualifyOnEmptyQualifier(Boolean qualifyOnEmptyQualifier) {
		this.qualifyOnEmptyQualifier = qualifyOnEmptyQualifier;
	}
 
	
}