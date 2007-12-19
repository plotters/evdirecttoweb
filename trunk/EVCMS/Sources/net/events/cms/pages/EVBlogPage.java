package net.events.cms.pages;
// Generated by the WOLips Templateengine Plug-in at Jul 28, 2007 10:11:55 PM

import org.apache.log4j.*;

import net.events.cms.eo.*;
import net.events.cms.extensions.*;

import com.webobjects.appserver.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

/**
 * Display page for a list of blog entries. Controlled and set up by its page
 * objects which must be a BlogPage
 * 
 * @author cug
 * 
 */
public class EVBlogPage extends EVCMSPage {
	
	// for the repetition
	private BlogEntry blogEntry;
	
	private NSArray<BlogEntry> blogEntries;
	
	/**
	 * Current batch number
	 */
	private Number batch;
	
	/**
	 * Current categoryId
	 */
	private Number categoryId;
	
	/**
	 * number of objects
	 */
	private Number numberOfObjects;

    /**
     * Standard constructor
     * @param context
     */
    public EVBlogPage(WOContext context) {
        super(context);
    }
    
    /**
     * convenience method, casts page to blog page
     * 
     * @return
     */
    public BlogPage blogPage () {
    	return (BlogPage) this.page();
    }
    
    /**
     * Gets the blogEntries to display for the current batch
     * 
     * @return array of blog entries
     */
    public NSArray<BlogEntry> blogEntries () {
    	if (this.blogEntries == null) {
    		// get the current entries batch from the URL 
    		this.batch = this.context().request().numericFormValueForKey("batch", EVCMSConstants.SIMPLE_NUMBER_FORMATTER);
    		if (this.batch == null) {
    			// set default to 1, of not found
    			this.batch = new Integer(1);
    		}
    		
    		this.categoryId = this.context().request().numericFormValueForKey("catId", EVCMSConstants.SIMPLE_NUMBER_FORMATTER);
    		EOQualifier q;
    		if (this.categoryId != null) {
    			Category selectedCategory = (Category) ERXEOControlUtilities.objectWithPrimaryKeyValue(this.editingContext, BlogEntryCategory.ENTITY_NAME, categoryId, null);
    			q = EOQualifier.qualifierWithQualifierFormat("active = 'true' and blog = %@ and category = %@", new NSMutableArray<Object> (this.blogPage().blog(), selectedCategory));
    		}
    		else {
    			q = EOQualifier.qualifierWithQualifierFormat("active = 'true' and blog = %@", new NSArray<Object> (this.blogPage().blog()));
    		}
    		EOSortOrdering sortOrdering = EOSortOrdering.sortOrderingWithKey(BlogEntry.CREATIONTIME, EOSortOrdering.CompareDescending);
    		EOFetchSpecification fs = new EOFetchSpecification(BlogEntry.ENTITY_NAME, q, new NSArray<EOSortOrdering> (sortOrdering));

    		int batchSize = this.blogPage().entriesPerPage().intValue();
    		int start = (this.batch.intValue() - 1) * batchSize;
    		int end = start + batchSize;

    		blogEntries = ERXEOControlUtilities.objectsInRange(this.editingContext, fs, start, end);
    		
    		this.numberOfObjects = ERXEOControlUtilities.objectCountWithQualifier(this.editingContext, BlogEntry.ENTITY_NAME, q);
    	}
    	return blogEntries;
    }
    
    /**
     * Additional parameters to attach to the generated URL in the batch navigation
     * 
     * @return
     */
    public NSDictionary<String, Object> additionalUrlParameters () {
    	NSMutableDictionary<String, Object> dict = new NSMutableDictionary<String, Object> ();
    	dict.setObjectForKey(this.page().primaryKey(), "pageId");
    	return dict;
    }
    

	/**
	 * @return the blogEntry
	 */
	public BlogEntry blogEntry() {
		return blogEntry;
	}

	/**
	 * @param blogEntry the blogEntry to set
	 */
	public void setBlogEntry(BlogEntry blogEntry) {
		this.blogEntry = blogEntry;
	}
	
	/**
	 * Returns just the name of the page
	 * 
	 * @return
	 */
	public String pageTitle () {
		return this.page().pageName();
	}

	/**
	 * @return the batch
	 */
	public Number batch() {
		return batch;
	}

	/**
	 * @param batch the batch to set
	 */
	public void setBatch(Number batch) {
		this.batch = batch;
	}

	/**
	 * @return the numberOfObjects
	 */
	public Number numberOfObjects() {
		return numberOfObjects;
	}

}