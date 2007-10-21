// BlogEntryCategory.java

package net.events.cms.eo;

import net.events.cms.extensions.*;

import org.apache.log4j.*;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

/**
 * Category for blog entries
 * 
 * @author cug
 *
 */
public class BlogEntryCategory extends _BlogEntryCategory {
	
	/**
	 * Logging support
	 */
	private static Logger log = Logger.getLogger( BlogEntryCategory.class );
	private Integer _cachedArticleCount;

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
    public BlogEntryCategory() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class BlogEntryCategory");
    }
    
    /**
     * Creates a new instance of "BlogEntryCategory" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public BlogEntryCategory( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class BlogEntryCategory and inserted it into an editingContext");
        ec.insertObject( this );
    }
    
    /**
     * Gets the article count with a raw fetch
     * 
     * @return number of articles in this category
     */
    public Integer rawArticleCount () {
    	if (!this.isNewObject() && this._cachedArticleCount == null) {
    		String sql = EVCMSQueryManager.articleCountForCategory(this);
    		
    		NSArray result = EOUtilities.rawRowsForSQL(this.editingContext(), "EVCMS", sql, null);
    		if (result != null && result.count() > 0) {
    			NSDictionary item = (NSDictionary) result.objectAtIndex(0);
    			_cachedArticleCount = new Integer(((Number) item.valueForKey("ARTICLE_COUNT")).intValue());
    		}
    		log.fatal("********* Article count for: " + this.categoryName());
    	}
    	return _cachedArticleCount;
    }
 
    public void addToEntries (BlogEntry entry) {
    	super.addToEntries(entry);
    	this._cachedArticleCount = null;
    }
    
    public void removeFromEntries (BlogEntry entry) {
    	super.removeFromEntries(entry);
    	this._cachedArticleCount = null;
    }

}