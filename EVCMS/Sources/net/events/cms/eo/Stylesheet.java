// Stylesheet.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class Stylesheet extends _Stylesheet {
	
	private static Logger log = Logger.getLogger( Stylesheet.class );

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
    public Stylesheet() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Stylesheet");
    }
    
    /**
     * Creates a new instance of "Stylesheet" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Stylesheet( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Stylesheet and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}