// JavaScript.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class JavaScript extends _JavaScript {
	
	private static Logger log = Logger.getLogger( JavaScript.class );

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
    public JavaScript() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class JavaScript");
    }
    
    /**
     * Creates a new instance of "JavaScript" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public JavaScript( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class JavaScript and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}