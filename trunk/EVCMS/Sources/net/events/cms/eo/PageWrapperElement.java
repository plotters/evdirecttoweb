// PageWrapperElement.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class PageWrapperElement extends _PageWrapperElement {
	
	private static Logger log = Logger.getLogger( PageWrapperElement.class );

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
    public PageWrapperElement() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class PageWrapperElement");
    }
    
    /**
     * Creates a new instance of "PageWrapperElement" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public PageWrapperElement( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class PageWrapperElement and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}