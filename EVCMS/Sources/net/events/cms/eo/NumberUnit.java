// NumberUnit.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class NumberUnit extends _NumberUnit {
	
	private static Logger log = Logger.getLogger( NumberUnit.class );

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
    public NumberUnit() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class NumberUnit");
    }
    
    /**
     * Creates a new instance of "NumberUnit" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public NumberUnit( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class NumberUnit and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}