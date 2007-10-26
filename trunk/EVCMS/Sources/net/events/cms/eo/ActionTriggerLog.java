// ActionTriggerLog.java

package net.events.cms.eo;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import org.apache.log4j.Logger;

public class ActionTriggerLog extends _ActionTriggerLog {
	
	private static Logger log = Logger.getLogger( ActionTriggerLog.class );

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
    public ActionTriggerLog() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class ActionTriggerLog");
    }
    
    /**
     * Creates a new instance of "ActionTriggerLog" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public ActionTriggerLog( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class ActionTriggerLog and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}