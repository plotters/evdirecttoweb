// BlogEntry.java

package net.events.cms.eo;

import net.events.cms.extensions.*;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

import er.extensions.*;

public class BlogEntry extends _BlogEntry {
	
	private static Logger log = Logger.getLogger( BlogEntry.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		this.setAllowDigging(Boolean.TRUE);
		this.setActive(Boolean.TRUE);
	}

	/**
	 * Standard constructor
	 */
    public BlogEntry() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class BlogEntry");
    }
    
    /**
     * Creates a new instance of "BlogEntry" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public BlogEntry( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class BlogEntry and inserted it into an editingContext");
        ec.insertObject( this );
    }
    
    /**
     * The URL for this article, based on some assumptions which page and with domain to use.
     * 
     * FIXME cug: remove the assumptions - perhaps add some more relationships
     * 
     * @return
     */
    public String entryUrl () {
    	if (this.blog() != null && this.blog().blogPages() != null && this.blog().blogPages().count() > 0 && 
    			this.blog().site().domains() != null && this.blog().site().domains().count() > 0 && !this.isNewObject()) {
    		return "http://" + ((Domain) this.blog().site().domains().objectAtIndex(0)).url() + "/cgi-bin/WebObjects/Designer.woa/wa/Web/page?pageId=" + 
    			((ERXGenericRecord) this.blog().blogPages().objectAtIndex(0)).primaryKey() + "&" + EVCMSConstants.BLOG_ENTRY_ID_KEY + "=" +
    			this.primaryKey();
    	}
    	else return null;
    }
 
    public boolean canDelete () {
    	return this.canUpdate();
    }
    
    public boolean canUpdate () {
    	// FIXME cug: can't be - there must be a way of checking that shit!
   		return true;
    }

}