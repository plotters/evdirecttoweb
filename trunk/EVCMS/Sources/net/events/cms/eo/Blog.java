// Blog.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public class Blog extends _Blog {
	
	private static Logger log = Logger.getLogger( Blog.class );

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
    public Blog() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Blog");
    }
    
    /**
     * Creates a new instance of "Blog" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Blog( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Blog and inserted it into an editingContext");
        ec.insertObject( this );
    }
    
    /**
     * Returns the first object from the blog pages array, this is basically a to one 
     * relationship which is just not modeled nicely (1:1 are ugly in WebObjects)
     * 
     * @return the page to display this blog
     */
    public BlogPage blogPage () {
    	if (this.blogPages() != null && this.blogPages().count() > 0) {
    		return (BlogPage) this.blogPages().objectAtIndex(0);
    	}
    	else return null;
    }
 
    /**
     * Makes sure, only one blog page can be set.
     * 
     * @param page
     */
    public void setBlogPage (BlogPage page) {
    	if (this.blogPages() != null && this.blogPages().count() > 0) {
    		this.deleteAllBlogPages();
    	}
    	this.addObjectToBothSidesOfRelationshipWithKey(page, Blog.BLOGPAGES);
    }
    
    /**
     * Returns the active articles
     * 
     * @return array of blog entries
     */
    public NSArray activeEntries () {
    	EOQualifier q = EOQualifier.qualifierWithQualifierFormat("active = 'true'", null);
    	return ERXArrayUtilities.filteredArrayWithQualifierEvaluation(this.entries(), q);
    }
    
    public NSArray recentEntries () {
    	log.fatal("***************");
    	EOQualifier q = EOQualifier.qualifierWithQualifierFormat("active = 'true' and blog = %@" , new NSArray (this));
    	EOSortOrdering sortOrdering = EOSortOrdering.sortOrderingWithKey(BlogEntry.CREATIONTIME, EOSortOrdering.CompareDescending);
    	EOFetchSpecification fs = new EOFetchSpecification(BlogEntry.ENTITY_NAME, q, new NSArray<EOSortOrdering> (sortOrdering));
    	
    	// TODO cug: make this dynamic
    	fs.setFetchLimit(20);
    	return this.editingContext().objectsWithFetchSpecification(fs);
    }
}