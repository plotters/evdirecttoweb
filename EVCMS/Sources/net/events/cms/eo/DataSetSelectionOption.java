// DataSetSelectionOption.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class DataSetSelectionOption extends _DataSetSelectionOption {
	
	private static Logger log = Logger.getLogger( DataSetSelectionOption.class );

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
    public DataSetSelectionOption() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetSelectionOption");
    }
    
    /**
     * Creates a new instance of "DataSetSelectionOption" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetSelectionOption( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetSelectionOption and inserted it into an editingContext");
        ec.insertObject( this );
    }
    
    public String userPresentableDescription () {
    	return this.optionName();
    }
 

}