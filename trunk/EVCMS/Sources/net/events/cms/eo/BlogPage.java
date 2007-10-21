// BlogPage.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class BlogPage extends _BlogPage {
	
	private static Logger log = Logger.getLogger( BlogPage.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		this.setComponentName("EVBlogPage");
		this.setActive(Boolean.FALSE);
		this.setEntriesPerPage(new Integer(10));
		this.setPageName("My New Blog");
	}

	/**
	 * Standard constructor
	 */
    public BlogPage() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class BlogPage");
    }
    
    /**
     * Creates a new instance of "BlogPage" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public BlogPage( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class BlogPage and inserted it into an editingContext");
        ec.insertObject( this );
    }
    

}