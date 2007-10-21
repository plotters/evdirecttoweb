// Seriousness.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class Seriousness extends _Seriousness {
	
	private static Logger log = Logger.getLogger( Seriousness.class );

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
    public Seriousness() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Seriousness");
    }
    
    /**
     * Creates a new instance of "Seriousness" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Seriousness( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Seriousness and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
    public String relationshipRepresentation () {
    	return "[" + this.orderNumber() + "] " + this.name();
    }

}