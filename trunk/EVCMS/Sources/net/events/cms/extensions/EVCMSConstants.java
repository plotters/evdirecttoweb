package net.events.cms.extensions;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

public class EVCMSConstants {
	
	/**
	 * The current user, as it should be set in into the ERXThreadStorage
	 */
	public static final String CURRENT_USER = "CURRENT_USER";
	
	/**
	 * The name of the Administrator Usergroup
	 */
	public static final String SUEPR_ADMIN_USERGROUP_NAME = "Super Admin";

	/**
	 * The name of the customer admin usergroup
	 */
	public static final String CUSTOMER_ADMIN_USERGROUP_NAME = "Customer Admin";
	
	public static final String STUDY_PARTICIPANT_USERGROUP_NAME = "Study Participant";

	/**
	 * The key for the current client in the ERXThreadStorage
	 */
	public static String CURRENT_CLIENT = "CURRENT_CLIENT";
	
	/**
	 * key of the page id parameter in URL requests
	 */
	public static String PAGE_ID_KEY = "pageId";  

	/**
	 * key of the blog entry id parameter in URL requests 
	 */
	public static final String BLOG_ENTRY_ID_KEY = "entryId";

	/**
	 * key of cat id in URL requests
	 */
	public static final String BLOG_CAT_ID_KEY = "catId";

	/**
	 * Key for a "message" ivar, used in key-value-coding 
	 */
	public static final String MESSAGE_KEY = "message";

	/**
	 * Key for a Boolean to terminate the session after an action 
	 */
	public static final String SHALL_TERMINATE_SESSION_KEY = "shallTerminateSession";

	/**
	 * Key for a "cssClassForMessage" ivar, used in key-value-coding
	 */
	public static final String CSS_CLASS_FOR_MESSAGE_KEY = "cssClassForMessage";

	/**
	 * Key for URL parameter "dataSetId"
	 */
	public static final String DATA_SET_ID_KEY = "dataSetId";

	/**
	 * Key for the current site
	 */
	public static final String CURRENT_SITE = "currentSite";

	/**
	 * Usergroup area "study"
	 */
	public static final String STUDY_AREA = "study";

	/**
	 * Used to compare while saving
	 */
	public static final String OTHER_ETHNIC_GROUP = "Other - please specify";

	/**
	 * Used in the threadstorage
	 */
	public static String PAGE_OBJECT_KEY = "PAGE_OBJECT_KEY";
	
	/**
	 * used to get a simple numeric value
	 */
	public static NSNumberFormatter SIMPLE_NUMBER_FORMATTER = new NSNumberFormatter("#");
	
	/**
	 * Sorts with the key "orderNumber" ascending
	 */
	public static EOSortOrdering orderNumberAscendingOrdering = EOSortOrdering.sortOrderingWithKey("orderNumber", EOSortOrdering.CompareAscending);

	/**
	 * Sorts with the key "orderNumber" ascending
	 */
	public static EOSortOrdering orderNumberDescendingOrdering = EOSortOrdering.sortOrderingWithKey("orderNumber", EOSortOrdering.CompareDescending);
}
