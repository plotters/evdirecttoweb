// DataSetNumberItemActionTrigger.java

package net.events.cms.eo;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import org.apache.log4j.Logger;

public class DataSetNumberItemActionTrigger extends _DataSetNumberItemActionTrigger {
	
	private static Logger log = Logger.getLogger( DataSetNumberItemActionTrigger.class );

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
    public DataSetNumberItemActionTrigger() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetNumberItemActionTrigger");
    }
    
    /**
     * Creates a new instance of "DataSetNumberItemActionTrigger" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetNumberItemActionTrigger( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetNumberItemActionTrigger and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}