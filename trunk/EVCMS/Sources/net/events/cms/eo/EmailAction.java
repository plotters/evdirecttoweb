// EmailAction.java

package net.events.cms.eo;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import org.apache.log4j.Logger;

public class EmailAction extends _EmailAction {
	
	private static Logger log = Logger.getLogger( EmailAction.class );

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
    public EmailAction() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class EmailAction");
    }
    
    /**
     * Creates a new instance of "EmailAction" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public EmailAction( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class EmailAction and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}