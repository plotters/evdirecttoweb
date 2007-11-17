// EventPrice.java

package net.events.cms.eo;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import org.apache.log4j.Logger;

public class EventPrice extends _EventPrice {
	
	private static Logger log = Logger.getLogger( EventPrice.class );

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
    public EventPrice() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class EventPrice");
    }
    
    /**
     * Creates a new instance of "EventPrice" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public EventPrice( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class EventPrice and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}