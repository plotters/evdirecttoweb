// DataSetSelectionItemValue.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public class DataSetSelectionItemValue extends _DataSetSelectionItemValue {
	
	private static Logger log = Logger.getLogger( DataSetSelectionItemValue.class );

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
    public DataSetSelectionItemValue() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetSelectionItemValue");
    }
    
    /**
     * Creates a new instance of "DataSetSelectionItemValue" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetSelectionItemValue( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetSelectionItemValue and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
    /** 
     * the selected options name
     */
    public String valueAsString () {
    	if (this.selectedOption() != null) {
    		return this.selectedOption().optionName();
    	}
    	else return "";
    }
    
    public void validateForSave () {
    	super.validateForSave();
    	if (this.dataSetItem().isRequired() != null && this.dataSetItem().isRequired().booleanValue()) {
    		if (this.selectedOption() == null) {
    			throw new NSValidation.ValidationException(ERXLocalizer.currentLocalizer().localizedTemplateStringForKeyWithObject("Survey.MissingAnswer", this.dataSetItem()));
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