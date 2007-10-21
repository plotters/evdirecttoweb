// SeverityGrade.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class SeverityGrade extends _SeverityGrade {
	
	private static Logger log = Logger.getLogger( SeverityGrade.class );

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
    public SeverityGrade() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class SeverityGrade");
    }
    
    /**
     * Creates a new instance of "SeverityGrade" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public SeverityGrade( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class SeverityGrade and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
    public String relationshipRepresentation () {
    	return "[" + this.orderNumber() + "] " + this.name();
    }

}