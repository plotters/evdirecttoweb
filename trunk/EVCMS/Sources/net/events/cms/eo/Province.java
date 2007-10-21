// Province.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class Province extends _Province {
	
	private static Logger log = Logger.getLogger( Province.class );

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
    public Province() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Province");
    }
    
    /**
     * Creates a new instance of "Province" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Province( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Province and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}