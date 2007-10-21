// DataSetTextItem.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

/**
 * @author cug
 *
 */
public class DataSetTextItem extends _DataSetTextItem {
	
	private static Logger log = Logger.getLogger( DataSetTextItem.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		this.setInterfaceElementType((InterfaceElementType) EOUtilities.objectMatchingKeyAndValue(this.editingContext(), InterfaceElementType.ENTITY_NAME, InterfaceElementType.ELEMENTNAME, "Textfield"));
	}

	/**
	 * Standard constructor
	 */
    public DataSetTextItem() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetTextItem");
    }
    
    /**
     * Creates a new instance of "DataSetTextItem" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetTextItem( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetTextItem and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
    /**
     * Returns the predefined values as an array
     * 
     * @return array of strings
     */
    public NSArray predefinedValuesArray () {
    	if (this.predefinedValues() != null) {
    		String[] lines = this.predefinedValues().split("\n");
    		return new NSArray<String>(lines);
    	}
    	else {
    		return null;
    	}
    }
    
    /**
     * Sorts the entries in predefinedValues if sortPredefinedValues is set and is true
     */
    public void validateForSave () {
    	if (this.predefinedValues() != null && this.sortPredefinedValues() != null && this.sortPredefinedValues().booleanValue()) {
    		NSMutableArray<String> values = new NSMutableArray<String>(this.predefinedValues().split("\n"));
    		ERXArrayUtilities.sortArrayWithKey(values, "toString");
    		this.setPredefinedValues(values.componentsJoinedByString("\n"));
    	}
    }

}