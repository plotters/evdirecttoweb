// Meeting.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public class Meeting extends _Meeting {
	
	private static Logger log = Logger.getLogger( Meeting.class );

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
    public Meeting() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Meeting");
    }
    
    /**
     * Creates a new instance of "Meeting" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Meeting( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Meeting and inserted it into an editingContext");
        ec.insertObject( this );
    }

	/**
	 * Overridden to show more than just the related objects from the too many relationship.
	 */
	@Override
	public NSArray<MeetingMinutesEntry> entries() {
		return ERXArrayUtilities.arrayByAddingObjectsFromArrayWithoutDuplicates(super.entries(), this.olderNonClosedEntries());
	}

	/**
	 * Returns non closed entries from other other meetings
	 * 
	 * @return
	 */
	private NSArray<MeetingMinutesEntry> olderNonClosedEntries () {
		// FIXME cug: get only older entries
		EOQualifier q = EOQualifier.qualifierWithQualifierFormat("(meeting != %@) and (status != %@) and (meeting.dateAndTime < %@)", 
				new NSMutableArray<Object>(this, EntryStatus.statusForName(EntryStatus.CLOSED, this.editingContext()), this.dateAndTime()));
		return ERXEOControlUtilities.objectsWithQualifier(this.editingContext(), MeetingMinutesEntry.ENTITY_NAME, q, null, false);
	}
}