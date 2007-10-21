// Gender.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;

public class Gender extends _Gender {
	
	private static Logger log = Logger.getLogger( Gender.class );

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
    public Gender() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Gender");
    }
    
    /**
     * Creates a new instance of "Gender" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Gender( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Gender and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
    /**
     * Returns the EO for unknown gender in the given editing context
     * @param ec
     * @return
     */
    public static Gender unknownGender (EOEditingContext ec) {
    	Gender result = null;
    	try {
    		result = (Gender) EOUtilities.objectMatchingKeyAndValue(ec, Gender.ENTITY_NAME, Gender.NAME, "unknown");
    	}
    	catch (Exception e) {
    		// do nothing
    	}
    	return result;
    }
    
}