// DataSetItemValue.java

package net.events.cms.eo;

import net.events.cms.extensions.*;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public abstract class DataSetItemValue extends _DataSetItemValue {
	
	private static Logger log = Logger.getLogger( DataSetItemValue.class );

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
    public DataSetItemValue() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetItemValue");
    }
    
    /**
     * Creates a new instance of "DataSetItemValue" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetItemValue( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetItemValue and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
    public abstract String valueAsString();
    
    public void validateForSave () {
    	super.validateForSave();
    }
    
    public void willInsert () {
    	super.willInsert();
    	this.checkTriggers();
    }
    
    public void willUpdate () {
    	super.willUpdate();
    	this.checkTriggers();
    }
    
    abstract protected void checkTriggers ();
    
    /**
     * @param a
     */
    public void executeTrigger (Action a) {
    	this.setHasTriggered(Boolean.TRUE);
    	if (a instanceof EmailAction) {
    		EVMailManager.sendActionTriggerMailWithObject((EmailAction) a, this);
    	}
    }
    
}