package net.events.page;

import org.apache.log4j.*;

import com.webobjects.appserver.*;

/**
 * Base component for all pages; can be used with component actions, then at least last page should be
 * set or direct actions. This class does not create a session or rely on one. Only common methods to all pages
 * should go here
 * 
 * @author cug
 *
 */
public class EVPage extends WOComponent {
	
	// ===============================================================================================
	// instance variables
	
	/**
	 * The last page
	 */
	private WOComponent lastpage;
	
	/**
	 * The nextpage
	 */
	private WOComponent nextpage;
	
	/**
	 * Logging support
	 */
	protected Logger log = Logger.getLogger(EVPage.class);

	// ===============================================================================================
	// functional methods

    public EVPage(WOContext context) {
        super(context);
        
        log.info("--- " + this.getClass().getName() + " created ---");
    }

	// ===============================================================================================
    // get / set methods

	/**
	 * @return the lastpage
	 */
	public WOComponent lastpage() {
		return lastpage;
	}

	/**
	 * @param lastpage the lastpage to set
	 */
	public void setLastpage(WOComponent lastpage) {
		this.lastpage = lastpage;
	}

	/**
	 * @return the nextpage
	 */
	public WOComponent nextpage() {
		return nextpage;
	}

	/**
	 * @param nextpage the nextpage to set
	 */
	public void setNextpage(WOComponent nextpage) {
		this.nextpage = nextpage;
	} 

	
}
