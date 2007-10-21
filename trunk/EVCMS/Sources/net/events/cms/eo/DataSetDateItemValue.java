// DataSetDateItemValue.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

public class DataSetDateItemValue extends _DataSetDateItemValue {
	
	private static Logger log = Logger.getLogger( DataSetDateItemValue.class );

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
    public DataSetDateItemValue() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetDateItemValue");
    }
    
    /**
     * Creates a new instance of "DataSetDateItemValue" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetDateItemValue( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetDateItemValue and inserted it into an editingContext");
        ec.insertObject( this );
    }

	@Override
	public String valueAsString() {
		if (this.dateValue() == null) {
			return "";
		}
		// TODO cug: use formatter here
		else return this.dateValue().toString();
	}
 
	public void setDataSetItem (DataSetItem item) {
		super.setDataSetItem(item);
		if (((DataSetDateItem)this.dataSetItem()).autosetToToday() != null && ((DataSetDateItem)this.dataSetItem()).autosetToToday().booleanValue()) {
			this.setDateValue(new NSTimestamp());
		}
	}
	
	public NSTimestamp validateDateValue (Object value) {
		if (value != null && !(value instanceof NSTimestamp)) {
			throw new NSValidation.ValidationException("Wrong value class, expecting NSTimestamp, but received a " + value.getClass().getName());
		}
		if (value != null) {
			NSTimestamp given = (NSTimestamp) value;
			if (given != null && given.after(new NSTimestamp())) {
				throw new NSValidation.ValidationException("Wrong date!");
			}
		}
		return (NSTimestamp) value;
	}


}