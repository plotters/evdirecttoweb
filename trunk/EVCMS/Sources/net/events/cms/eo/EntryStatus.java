// EntryStatus.java

package net.events.cms.eo;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.BigDecimal;
import java.util.*;
import org.apache.log4j.Logger;

public class EntryStatus extends _EntryStatus {
	
	private static Logger log = Logger.getLogger( EntryStatus.class );
	
	public static String CLOSED = "closed";

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
    public EntryStatus() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class EntryStatus");
    }
    
    /**
     * Creates a new instance of "EntryStatus" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public EntryStatus( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class EntryStatus and inserted it into an editingContext");
        ec.insertObject( this );
    }
    
    public static EntryStatus statusForName (String name, EOEditingContext editingContext) {
    	return (EntryStatus) EOUtilities.objectMatchingKeyAndValue(editingContext, EntryStatus.ENTITY_NAME, EntryStatus.NAME, name);
    }
 

}