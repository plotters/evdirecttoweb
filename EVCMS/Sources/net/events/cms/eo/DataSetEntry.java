// DataSetEntry.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public abstract class DataSetEntry extends _DataSetEntry {
	
	private static Logger log = Logger.getLogger( DataSetEntry.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
	}
	
	public static DataSetEntry createNewInstanceForTemplateInEditingContext(DataSetTemplate template, EOEditingContext editingContext) {
		return null;
	}
	
	
    /**
     * The existing sections
     * 
     * @return
     */
    public NSArray sections () {
    	return this.dataSetTemplate().sections();
    }
    
    /**
     * Return the specific values for a given section
     *  
     * @param aSection
     * @return
     */
    public NSArray valuesForSection (DataSetSection aSection) {
    	if (aSection != null) {
    		EOQualifier q = EOQualifier.qualifierWithQualifierFormat("dataSetItem.section = %@", new NSArray<DataSetSection>(aSection));
    		return ERXArrayUtilities.filteredArrayWithQualifierEvaluation(this.values(), q);
    	}
    	return null;
    }

	/**
	 * All values that are not going to be displayed in sections
	 * 
	 * @return
	 */
	public NSArray valuesNotInASection() {
   		EOQualifier q = EOQualifier.qualifierWithQualifierFormat("dataSetItem.section = null", null);
		return ERXArrayUtilities.filteredArrayWithQualifierEvaluation(this.values(), q);
	}
}