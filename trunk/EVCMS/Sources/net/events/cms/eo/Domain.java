// Domain.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class Domain extends _Domain {
	
	private static Logger log = Logger.getLogger( Domain.class );

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
    public Domain() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Domain");
    }
    
    /**
     * Creates a new instance of "Domain" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Domain( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Domain and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}