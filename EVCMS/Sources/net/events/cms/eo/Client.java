// Client.java

package net.events.cms.eo;

import net.events.cms.extensions.*;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

public class Client extends _Client {
	
	private static Logger log = Logger.getLogger( Client.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		this.setActive(Boolean.TRUE);
		
	}

	/**
	 * Standard constructor
	 */
    public Client() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Client");
    }
    
    /**
     * Creates a new instance of "Client" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Client( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Client and inserted it into an editingContext");
        ec.insertObject( this );
    }
    
    /**
     * Return the active dataset pages for this client
     * 
     * @return
     */
    public NSArray activeDataSetPageTemplates () {
    	EOQualifier q = EOQualifier.qualifierWithQualifierFormat("active = %@ and client = %@", new NSArray<Object>(new Object[] {Boolean.TRUE, this}));
    	EOFetchSpecification fs = new EOFetchSpecification(DataSetPageTemplate.ENTITY_NAME, q, new NSArray(EVCMSConstants.orderNumberAscendingOrdering));
    	NSArray result = this.editingContext().objectsWithFetchSpecification(fs);
    	return result;
    }
 

}