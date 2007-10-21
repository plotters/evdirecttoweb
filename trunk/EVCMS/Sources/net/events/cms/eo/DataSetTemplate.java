// DataSetTemplate.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

public abstract class DataSetTemplate extends _DataSetTemplate {
	
	private static Logger log = Logger.getLogger( DataSetTemplate.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		this.setCssClassForThankYouMessage("b_400 centered border_red border_double");
	}

	/**
	 * Creates a new DataSetEntry with this template
	 *  
	 * @param editingContext
	 * 
	 * @return
	 */
	public abstract DataSetEntry createNewInstanceForTemplateInEditingContext (EOEditingContext editingContext);
	
	public AbstractPage pageForDisplay () {
		return this.dataSetPageTemplate();
	}
	

}