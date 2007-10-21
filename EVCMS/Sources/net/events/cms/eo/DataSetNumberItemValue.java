// DataSetNumberItemValue.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class DataSetNumberItemValue extends _DataSetNumberItemValue {
	
	private static Logger log = Logger.getLogger( DataSetNumberItemValue.class );

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
    public DataSetNumberItemValue() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetNumberItemValue");
    }
    
    /**
     * Creates a new instance of "DataSetNumberItemValue" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetNumberItemValue( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetNumberItemValue and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
    public String valueAsString () {
    	if (this.numberValue() != null) {
    		return this.numberValue().toString();
    	}
    	else return "";
    }
    
}