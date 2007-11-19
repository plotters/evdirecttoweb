// Image.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class Image extends _Image {
	
	private static Logger log = Logger.getLogger( Image.class );

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
    public Image() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Image");
    }
    
    /**
     * Creates a new instance of "Image" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Image( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Image and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}