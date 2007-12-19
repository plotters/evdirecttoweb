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
 

    /**
     * Validates the e-mail with regular expression
     * 
     * FIXME cug: move this to a "ValidationUtilities" class or so
     * 
     * @param value
     * @return
     */
    public Object validateEmail (Object value) {
    	if (value != null && value instanceof String) {
    		String v = (String) value;
    		if (v.matches("^[A-Za-z0-9_\\-]+([.][A-Za-z0-9_\\-]+)*[@][A-Za-z0-9_\\-]+([.][A-Za-z0-9_\\-]+)+$")) {
    			return v;
    		}
    	}
    	throw new NSValidation.ValidationException ("BlogComment.INVALID_EMAIL");
    }

	/**
	 * Checks whether this comment is a duplicate for the given entry. Right now, this is done with simple
	 * string comparison, which is pretty inefficient but should be good enough for now as I'm not expecting 
	 * hundreds of comments. If this gets too slow, the idea is to create checksum for each comment on save
	 * and do a database query to find entries with the same checksum. But right now this is not necessary.
	 * 
	 * @param email the email address
	 * @param entry the 
	 * @param comment
	 * @return
	 */
	public static boolean isDuplicate(String email, BlogEntry entry, String comment) {
		
		for (BlogComment existingComment : (NSArray<BlogComment>)entry.comments()) {
			if (existingComment.comment().equals(comment) && existingComment.email().equals(email)) {
				return true;
			}
		}
		
		return false;
	}
}