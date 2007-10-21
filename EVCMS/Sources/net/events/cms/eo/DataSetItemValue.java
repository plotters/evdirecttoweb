// DataSetItemValue.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public abstract class DataSetItemValue extends _DataSetItemValue {
	
	private static Logger log = Logger.getLogger( DataSetItemValue.class );

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
    public DataSetItemValue() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetItemValue");
    }
    
    /**
     * Creates a new instance of "DataSetItemValue" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetItemValue( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetItemValue and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
    public abstract String valueAsString();
    
}