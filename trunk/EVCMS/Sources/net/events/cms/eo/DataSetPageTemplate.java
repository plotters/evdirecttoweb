// DataSetPageTemplate.java

package net.events.cms.eo;

import net.events.cms.pages.*;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public class DataSetPageTemplate extends _DataSetPageTemplate {
	
	private static Logger log = Logger.getLogger( DataSetPageTemplate.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		this.setComponentName(EVDataSetPage.class.getSimpleName());
	}

	/**
	 * Standard constructor
	 */
    public DataSetPageTemplate() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetPageTemplate");
    }
    
    /**
     * Creates a new instance of "DataSetPageTemplate" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public DataSetPageTemplate( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class DataSetPageTemplate and inserted it into an editingContext");
        ec.insertObject( this );
    }
 

}