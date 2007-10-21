// Company.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class Company extends _Company {
	
	private static Logger log = Logger.getLogger( Company.class );

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
    public Company() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Company");
    }
    
    /**
     * Creates a new instance of "Company" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Company( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Company and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}