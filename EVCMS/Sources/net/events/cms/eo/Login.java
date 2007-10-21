// Login.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class Login extends _Login {
	
	private static Logger log = Logger.getLogger( Login.class );

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
    public Login() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Login");
    }
    
    /**
     * Creates a new instance of "Login" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Login( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Login and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}