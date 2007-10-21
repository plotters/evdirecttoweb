// CausalityAssessment.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class CausalityAssessment extends _CausalityAssessment {
	
	private static Logger log = Logger.getLogger( CausalityAssessment.class );

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
    public CausalityAssessment() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class CausalityAssessment");
    }
    
    /**
     * Creates a new instance of "CausalityAssessment" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public CausalityAssessment( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class CausalityAssessment and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
    public String relationshipRepresentation () {
    	return "[" + this.orderNumber() + "] " + this.causality();
    }

}