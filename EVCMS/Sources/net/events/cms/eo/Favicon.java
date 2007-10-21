// Favicon.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class Favicon extends _Favicon {
	
	private static Logger log = Logger.getLogger( Favicon.class );

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
    public Favicon() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Favicon");
    }
    
    /**
     * Creates a new instance of "Favicon" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Favicon( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Favicon and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}