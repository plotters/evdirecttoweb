// DataSetTextItemValue.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public class DataSetTextItemValue extends _DataSetTextItemValue {
	
	private static Logger log = Logger.getLogger( DataSetTextItemValue.class );

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
    public DataSetTextItemValue() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetTextItemValue");
    }
    
    /**
     * Creates a new instance of "DataSetTextItemValue" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetTextItemValue( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetTextItemValue and inserted it into an editingContext");
        ec.insertObject( this );
    }

    public void validateForSave () {
    	super.validateForSave();
    	if (this.dataSetItem().isRequired() != null && this.dataSetItem().isRequired().booleanValue()) {
    		if (this.textValue() == null || this.textValue().equals("")) {
    			throw new NSValidation.ValidationException(ERXLocalizer.currentLocalizer().localizedTemplateStringForKeyWithObject("Survey.MissingAnswer", this.dataSetItem()));
    		}
    	}
    }
    
    /* (non-Javadoc)
     * @see net.events.cms.eo.DataSetItemValue#valueAsString()
     */
    public String valueAsString () {
    	return this.textValue();
    }
}