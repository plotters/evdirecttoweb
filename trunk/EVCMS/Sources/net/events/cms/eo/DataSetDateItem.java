// DataSetDateItem.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;

public class DataSetDateItem extends _DataSetDateItem {
	
	private static Logger log = Logger.getLogger( DataSetDateItem.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		this.setInterfaceElementType((InterfaceElementType) EOUtilities.objectMatchingKeyAndValue(this.editingContext(), InterfaceElementType.ENTITY_NAME, InterfaceElementType.ELEMENTNAME, "Datefield"));
	}

	/**
	 * Standard constructor
	 */
    public DataSetDateItem() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetDateItem");
    }
    
    /**
     * Creates a new instance of "DataSetDateItem" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetDateItem( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetDateItem and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}