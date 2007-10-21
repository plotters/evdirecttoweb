// PageWrapper.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class PageWrapper extends _PageWrapper {
	
	private static Logger log = Logger.getLogger( PageWrapper.class );

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
    public PageWrapper() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class PageWrapper");
    }
    
    /**
     * Creates a new instance of "PageWrapper" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public PageWrapper( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class PageWrapper and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}