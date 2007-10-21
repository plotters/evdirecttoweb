// EditedSite.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class EditedSite extends _EditedSite {
	
	private static Logger log = Logger.getLogger( EditedSite.class );

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
    public EditedSite() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class EditedSite");
    }
    
    /**
     * Creates a new instance of "EditedSite" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public EditedSite( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class EditedSite and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}