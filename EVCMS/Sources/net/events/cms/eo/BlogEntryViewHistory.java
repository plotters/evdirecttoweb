// BlogEntryViewHistory.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class BlogEntryViewHistory extends _BlogEntryViewHistory {
	
	private static Logger log = Logger.getLogger( BlogEntryViewHistory.class );
	
	public static String DETAIL_TYPE = "detail";
	public static String LIST_TYPE = "list";

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
    public BlogEntryViewHistory() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class BlogEntryViewHistory");
    }
    
    /**
     * Creates a new instance of "BlogEntryViewHistory" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public BlogEntryViewHistory( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class BlogEntryViewHistory and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}