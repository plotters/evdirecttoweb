// Usergroup.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class Usergroup extends _Usergroup {
	
	private static Logger log = Logger.getLogger( Usergroup.class );

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
    public Usergroup() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Usergroup");
    }
    
    /**
     * Creates a new instance of "Usergroup" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Usergroup( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Usergroup and inserted it into an editingContext");
        ec.insertObject( this );
    }
    
    /**
     * Checks whether this object can be deleted
     *  
     * @see er.extensions.ERXGenericRecord#canDelete()
     */
    public boolean canDelete () {
    	return this.users() == null || this.users().count() ==  0;
    }
 

}