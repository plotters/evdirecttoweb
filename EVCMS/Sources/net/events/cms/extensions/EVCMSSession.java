package net.events.cms.extensions;

import net.events.cms.eo.*;
import net.events.d2w.extensions.*;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

/**
 * @author cug
 *
 */
public class EVCMSSession extends EVD2WSession {

	/**
	 * The current User
	 */
	private Person currentUser; 
	
	/**
	 * The current Client
	 */
	private Client currentClient;
	
	/**
	 * An object requested by a browser request; stored here for convenience
	 */
	private EOGenericRecord requestedObject;

	private Site currentSite;
	
	public EVCMSSession() {
	}

	public void awake() {
		super.awake();

		// make sure, we have client
		if (this.currentClient() == null && this.currentUser() != null)
			this.setCurrentClient(this.currentUser().client());

		if (this.currentUser() != null) {
			// put the user into the thread storage
			ERXThreadStorage.takeValueForKey(currentUser(), EVCMSConstants.CURRENT_USER);
		}
		
		if (this.currentClient() != null) {
			ERXThreadStorage.takeValueForKey(currentClient(), EVCMSConstants.CURRENT_CLIENT);
		}

	}
	
	public void sleep () {
		if (this.currentUser() != null && this.currentUser() instanceof EventsUser) {
			// store the current user into an accessible place
			 this.updateSessionTrack();
		}

		super.sleep();
	}
	
	/**
	 * Creates an entry in the database to track the actions of the currently
	 * logged in users. Also used to get the count of the currently logged
	 * in users.
	 */
	private void updateSessionTrack () {
		ERXEC editingContext = (ERXEC) ERXEC.newEditingContext();
		try {
			editingContext.lock();
			SessionTrack track;
			try {
				track = (SessionTrack) EOUtilities.objectMatchingKeyAndValue(editingContext, SessionTrack.ENTITY_NAME, SessionTrack.SESSIONID, this.sessionID());
			}
			catch (EOObjectNotAvailableException e) {
				track = SessionTrack.createSessionTrack(editingContext, this.application().name() + this.application().number(), null, false, this.sessionID(), ERXEOControlUtilities.localInstanceOfObject(editingContext, currentUser()));
			}
			
			track.setLastActionTime(new NSTimestamp());

			String navigationState = ERXNavigationManager.manager().navigationStateForSession(this).stateAsString();
			if (navigationState != null && navigationState.length() > 0) {
				track.setNavigationState(navigationState);
			} else {
				track.setNavigationState("unknown");
			}
			
			track.editingContext().saveChanges();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			editingContext.unlock();
			editingContext.dispose();
			editingContext = null;
		}

	}

	/**
	 * When the session terminates, we set all the entries for the current user
	 * to "logged out" because the user is forcefully logged out when the
	 * session terminates
	 * 
	 * @see er.extensions.ERXSession#terminate()
	 */
	public void terminate() {
		String sql = EVCMSQueryManager.logOutUserFromSession(this.sessionID());
		ERXEC editingContext = (ERXEC) ERXEC.newEditingContext();
		try {
			editingContext.lock();
			EOUtilities.rawRowsForSQL(editingContext, "EVCMS", sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			editingContext.unlock();
			editingContext.dispose();
			editingContext = null;
		}
		log.info("Session terminated: " + this.sessionID());
		super.terminate();
	}

	/**
	 * Returns the current user
	 * 
	 * @return
	 */
	public Person currentUser() {
		return currentUser;
	}

	/**
	 * Set the current user
	 * 
	 * @param currentUser
	 */
	public void setCurrentUser(Person currentUser) {
		this.currentUser = currentUser;
		if (this.currentUser() != null) {
			ERXThreadStorage.takeValueForKey(this.currentUser(), EVCMSConstants.CURRENT_USER);
			if (this.currentUser() instanceof EventsUser) { 
					this.updateSessionTrack();
			}
		}
	}

	/**
	 * @return the currentClient
	 */
	public Client currentClient() {
		return currentClient;
	}

	/**
	 * @param currentClient
	 *            the currentClient to set
	 */
	public void setCurrentClient(Client currentClient) {
		this.currentClient = currentClient;
	}
	
    public int userCount () {
    	int intCount = 0;
    	String sql = EVCMSQueryManager.uniqueSessionsWithLoggedInUsers();
    	NSArray result = EOUtilities.rawRowsForSQL(this.defaultEditingContext(), "EVCMS", sql, null);
    	
    	if (result != null && result.count() > 0) {
    		Number count = (Number) (((NSDictionary) result.lastObject()).valueForKey("USER_COUNT"));
    		if (count != null) {
    			intCount = count.intValue();
    		}
    	}
    	return intCount;
    }

	/**
	 * @return the requestedObject
	 */
	public EOGenericRecord requestedObject() {
		return requestedObject;
	}

	/**
	 * @param requestedObject the requestedObject to set
	 */
	public void setRequestedObject(EOGenericRecord requestedObject) {
		this.requestedObject = requestedObject;
	}

	/**
	 * Set the current site
	 * 
	 * @param site
	 */
	public void setCurrentSite(Site site) {
		this.currentSite = site;
		
	}

	/**
	 * @return the currentSite
	 */
	public Site currentSite() {
		return currentSite;
	}

}
