// DataSetSelectionItem.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class DataSetSelectionItem extends _DataSetSelectionItem {
	
	private static Logger log = Logger.getLogger( DataSetSelectionItem.class );

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
    public DataSetSelectionItem() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetSelectionItem");
    }
    
    /**
     * Creates a new instance of "DataSetSelectionItem" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetSelectionItem( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetSelectionItem and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
}