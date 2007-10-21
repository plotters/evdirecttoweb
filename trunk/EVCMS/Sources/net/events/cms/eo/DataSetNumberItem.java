// DataSetNumberItem.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;

public class DataSetNumberItem extends _DataSetNumberItem {
	
	private static Logger log = Logger.getLogger( DataSetNumberItem.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		this.setInterfaceElementType((InterfaceElementType) EOUtilities.objectMatchingKeyAndValue(this.editingContext(), InterfaceElementType.ENTITY_NAME, InterfaceElementType.ELEMENTNAME, "Numberfield"));
	}

	/**
	 * Standard constructor
	 */
    public DataSetNumberItem() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetNumberItem");
    }
    
    /**
     * Creates a new instance of "DataSetNumberItem" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetNumberItem( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetNumberItem and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}