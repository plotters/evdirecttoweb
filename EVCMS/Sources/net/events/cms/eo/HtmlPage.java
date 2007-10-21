// HtmlPage.java

package net.events.cms.eo;

import net.events.cms.pages.*;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class HtmlPage extends _HtmlPage {
	
	private static Logger log = Logger.getLogger( HtmlPage.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		this.setComponentName(EVHtmlPage.class.getCanonicalName());
	}

	/**
	 * Standard constructor
	 */
    public HtmlPage() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class HtmlPage");
    }
    
    /**
     * Creates a new instance of "HtmlPage" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public HtmlPage( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class HtmlPage and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}