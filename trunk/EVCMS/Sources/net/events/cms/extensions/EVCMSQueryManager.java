package net.events.cms.extensions;

import net.events.cms.eo.*;

/**
 * All collected SQL queries for EVCMS
 * 
 * @author cug
 */
public class EVCMSQueryManager {
	
	/**
	 * SQL for logging setting logged_out = true for a particular session
	 * 
	 * @param sessionId
	 * @return sql string
	 */
	public static String logOutUserFromSession (String sessionId) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("update session_track set logged_out = true where session_id = '" + sessionId + "';");
		
		return sql.toString();
	}
	
	/**
	 * All unique sessions for currently logged in users 
	 * 
	 * @return sql string
	 */
	public static String uniqueSessionsWithLoggedInUsers () {
		StringBuffer sql = new StringBuffer();
		sql.append("select count (distinct session_id) as user_count from session_track where logged_out = false;");
		return sql.toString();
	}
	
	/**
	 * SQL for cleaning up the logged in users table after an application instance restart
	 * 
	 * @param applicationInstance - the application instance e.g. "Designer-1"
	 * @return sql string
	 */
	public static String cleanupLoggedInUsersForApplicationInstance (String applicationInstance) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from session_track where instance_name = '" + applicationInstance + "';");
		return sql.toString();
	}
	
	/**
	 * SQL for getting the currently logged in users with their navigation state
	 * to display on the admin page
	 * 
	 * @return sql string
	 */
	public static String loggedInUsersWithNavigationState () {
		return "select firstname, lastname, s.last_action_time, s.navigation_state, s.session_id, s.instance_name" +
				" from contacts c join session_track s on s.user_id = c.contact_id" +
				" where s.logged_out = false order by last_action_time desc;";
	}
	
	/**
	 * SQL for getting the article count for a specific category
	 * 
	 * @param category
	 * @return sql string
	 */
	public static String articleCountForCategory (BlogEntryCategory category) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select count(*) as article_count from blog_entries where category_id = ");
		sql.append(category.primaryKey());
		sql.append(";");
		
		return sql.toString();
	}

}
