// DataSetDecimalItem.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;

public class DataSetDecimalItem extends _DataSetDecimalItem {
	
	private static Logger log = Logger.getLogger( DataSetDecimalItem.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		this.setInterfaceElementType((InterfaceElementType) EOUtilities.objectMatchingKeyAndValue(this.editingContext(), InterfaceElementType.ENTITY_NAME, InterfaceElementType.ELEMENTNAME, "Decimalfield"));
}

	/**
	 * Standard constructor
	 */
    public DataSetDecimalItem() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetDecimalItem");
    }
    
    /**
     * Creates a new instance of "DataSetDecimalItem" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetDecimalItem( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetDecimalItem and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
}