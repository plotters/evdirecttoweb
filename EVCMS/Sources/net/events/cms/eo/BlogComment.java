// BlogComment.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

public class BlogComment extends _BlogComment {
	
	private static Logger log = Logger.getLogger( BlogComment.class );

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
    public BlogComment() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class BlogComment");
    }
    
    /**
     * Creates a new instance of "BlogComment" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public BlogComment( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class BlogComment and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

    public Object validateEmail (Object value) {
    	if (value != null && value instanceof String) {
    		String v = (String) value;
    		if (v.matches("^[A-Za-z0-9_\\-]+([.][A-Za-z0-9_\\-]+)*[@][A-Za-z0-9_\\-]+([.][A-Za-z0-9_\\-]+)+$")) {
    			return v;
    		}
    	}
    	throw new NSValidation.ValidationException ("BlogComment.INVALID_EMAIL");
    }
}