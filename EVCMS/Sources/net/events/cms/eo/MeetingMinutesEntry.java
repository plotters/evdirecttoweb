// MeetingMinutesEntry.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class MeetingMinutesEntry extends _MeetingMinutesEntry {
	
	private static Logger log = Logger.getLogger( MeetingMinutesEntry.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
	}

	/**
	 * Standard constructor
	 */
    public MeetingMinutesEntry() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class MeetingMinutesEntry");
    }
    
    /**
     * Creates a new instance of "MeetingMinutesEntry" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public MeetingMinutesEntry( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class MeetingMinutesEntry and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
    /**
     * Overridden to set the meetingMinutes from the meeting in this object
     */
    public void setMeeting (Meeting meeting) {
    	super.setMeeting(meeting);
    	if (meeting != null) {
    		this.addObjectToBothSidesOfRelationshipWithKey(meeting.meetingMinutes(), MeetingMinutesEntry.MEETINGMINUTES);
    	}
    }
    
    /**
     * Returns the first character of the type name
     * 
     * @return D for Decision, T for Task, 	I for Information
     */
    public String shortType () {
    	return this.type().name().substring(0, 1);
    }
    
}