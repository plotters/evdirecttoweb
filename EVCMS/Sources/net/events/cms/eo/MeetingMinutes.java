// MeetingMinutes.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class MeetingMinutes extends _MeetingMinutes {
	
	private static Logger log = Logger.getLogger( MeetingMinutes.class );

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
    public MeetingMinutes() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class MeetingMinutes");
    }
    
    /**
     * Creates a new instance of "MeetingMinutes" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public MeetingMinutes( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class MeetingMinutes and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}