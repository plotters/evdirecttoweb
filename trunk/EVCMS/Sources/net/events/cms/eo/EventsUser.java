// EventsUser.java

package net.events.cms.eo;

import net.events.cms.extensions.*;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public class EventsUser extends _EventsUser {
	
	private static Logger log = Logger.getLogger( EventsUser.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		
		this.setPasswordAttempts(new Integer(0));
		this.setActive(Boolean.TRUE);
	}

	/**
	 * Standard constructor
	 */
    public EventsUser() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class EventsUser");
    }
    
    /**
     * Creates a new instance of "EventsUser" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public EventsUser( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class EventsUser and inserted it into an editingContext");
        ec.insertObject( this );
    }

        
    public boolean canDelete () {
    	EventsUser currentUser = (EventsUser) ERXThreadStorage.valueForKey(EVCMSConstants.CURRENT_USER);
    	if (currentUser != null && this.primaryKey().equals(currentUser.primaryKey())) {
    		return false;
    	}
    	return super.canDelete();
    }
    
    /**
     * Show only the usergroups that are available for the area of this user
     * @return
     */
    public NSArray availableUsergroups () {
    	EOQualifier q = EOQualifier.qualifierWithQualifierFormat("area = %@", new NSArray (this.usergroup().area()));
    	return ERXArrayUtilities.filteredArrayWithQualifierEvaluation(Usergroup.fetchAllObjects(this.editingContext()), q);
    }
}