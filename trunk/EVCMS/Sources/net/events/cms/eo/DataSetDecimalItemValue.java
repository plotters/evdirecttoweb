// DataSetDecimalItemValue.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class DataSetDecimalItemValue extends _DataSetDecimalItemValue {
	
	private static Logger log = Logger.getLogger( DataSetDecimalItemValue.class );

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
    public DataSetDecimalItemValue() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetDecimalItemValue");
    }
    
    /**
     * Creates a new instance of "DataSetDecimalItemValue" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetDecimalItemValue( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetDecimalItemValue and inserted it into an editingContext");
        ec.insertObject( this );
    }

	public String valueAsString() {
		if (this.decimalValue() != null) {
			return this.decimalValue().toString();
		}
		else {
			return "";
		}
	}
 
	public void validateForSave () {
		if (this.dataSetItem().isRequired() != null && this.dataSetItem().isRequired().booleanValue()) {
			if (this.decimalValue() == null) {
				throw new ValidationException ("Please type a decimal value in this field!");
			}
		}
	}

	@Override
	protected void checkTriggers()  {
    	DataSetItemActionTrigger trigger = this.dataSetEntry().person().triggerForDataSetItem(this.dataSetItem()); 
    	if (trigger != null) { 
    	}
    }
}