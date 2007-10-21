// AdverseEvent.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

import er.extensions.*;

public class AdverseEvent extends _AdverseEvent {
	
	private static Logger log = Logger.getLogger( AdverseEvent.class );

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
    public AdverseEvent() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class AdverseEvent");
    }
    
    /**
     * Creates a new instance of "AdverseEvent" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public AdverseEvent( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class AdverseEvent and inserted it into an editingContext");
        ec.insertObject( this );
    }
    
    public void setStudyParticipant (StudyParticipant sp) {
    	if (sp != null) {
    		this.setClinicalTrial(ERXEOControlUtilities.localInstanceOfObject(this.editingContext(), sp.clinicalTrial()));
    	}
    	super.setStudyParticipant(sp);
    }

}