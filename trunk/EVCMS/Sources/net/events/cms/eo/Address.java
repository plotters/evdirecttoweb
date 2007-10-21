// Address.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class Address extends _Address {
	
	private static Logger log = Logger.getLogger( Address.class );

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
    public Address() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Address");
    }
    
    /**
     * Creates a new instance of "Address" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Address( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Address and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}