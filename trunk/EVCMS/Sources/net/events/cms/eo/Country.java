// Country.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class Country extends _Country {
	
	private static Logger log = Logger.getLogger( Country.class );

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
    public Country() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Country");
    }
    
    /**
     * Creates a new instance of "Country" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Country( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Country and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}