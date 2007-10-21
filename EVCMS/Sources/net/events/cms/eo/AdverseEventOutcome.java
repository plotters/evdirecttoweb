// AdverseEventOutcome.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class AdverseEventOutcome extends _AdverseEventOutcome {
	
	private static Logger log = Logger.getLogger( AdverseEventOutcome.class );

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
    public AdverseEventOutcome() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class AdverseEventOutcome");
    }
    
    /**
     * Creates a new instance of "AdverseEventOutcome" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public AdverseEventOutcome( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class AdverseEventOutcome and inserted it into an editingContext");
        ec.insertObject( this );
    }

    public String relationshipRepresentation () {
    	return "[" + this.orderNumber() + "] " + this.outcome();
    }
}