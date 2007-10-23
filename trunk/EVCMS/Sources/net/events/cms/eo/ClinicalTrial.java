// ClinicalTrial.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class ClinicalTrial extends _ClinicalTrial {
	
	private static Logger log = Logger.getLogger( ClinicalTrial.class );

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
    public ClinicalTrial() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class ClinicalTrial");
    }
    
    /**
     * Creates a new instance of "ClinicalTrial" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public ClinicalTrial( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class ClinicalTrial and inserted it into an editingContext");
        ec.insertObject( this );
    }
     

}