// InterfaceElementType.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class InterfaceElementType extends _InterfaceElementType {
	
	private static Logger log = Logger.getLogger( InterfaceElementType.class );

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
    public InterfaceElementType() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class InterfaceElementType");
    }
    
    /**
     * Creates a new instance of "InterfaceElementType" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public InterfaceElementType( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class InterfaceElementType and inserted it into an editingContext");
        ec.insertObject( this );
    }
    
    

}