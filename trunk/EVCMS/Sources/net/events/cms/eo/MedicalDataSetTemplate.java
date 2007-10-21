// MedicalDataSetTemplate.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class MedicalDataSetTemplate extends _MedicalDataSetTemplate {
	
	private static Logger log = Logger.getLogger( MedicalDataSetTemplate.class );

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
    public MedicalDataSetTemplate() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class MedicalDataSetTemplate");
    }
    
    /**
     * Creates a new instance of "MedicalDataSetTemplate" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public MedicalDataSetTemplate( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class MedicalDataSetTemplate and inserted it into an editingContext");
        ec.insertObject( this );
    }

	public MedicalDataSetEntry createNewInstanceForTemplateInEditingContext(EOEditingContext editingContext) {
		return MedicalDataSetEntry.createNewInstanceForTemplateInEditingContext(this, editingContext);
	}
 

}