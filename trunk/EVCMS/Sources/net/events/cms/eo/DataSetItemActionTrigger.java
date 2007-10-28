// DataSetItemActionTrigger.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public abstract class DataSetItemActionTrigger extends _DataSetItemActionTrigger {
	
	private static Logger log = Logger.getLogger( DataSetItemActionTrigger.class );

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
    public DataSetItemActionTrigger() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetItemActionTrigger");
    }
    
    /**
     * Creates a new instance of "DataSetItemActionTrigger" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetItemActionTrigger( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetItemActionTrigger and inserted it into an editingContext");
        ec.insertObject( this );
    }
    
    abstract public DataSetItem dataSetItem();
 
}