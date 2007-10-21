// DataSetItem.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;

/**
 * And item (question, data entry, whatever) of a dataset (survey, medical dataset, ...)
 * 
 * @author cug
 */
public class DataSetItem extends _DataSetItem {
	
	private static Logger log = Logger.getLogger( DataSetItem.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		this.setActive(Boolean.TRUE);
	}
	
	/**
	 * Make sure that the template is set - it might not be set automatically if
	 * we create this object from the section edit page.
	 * 
	 * Calls super to set the section and sets the template if the template is null.
	 */
	public void setSection (DataSetSection section) {
		super.setSection(section);
		if (this.dataSetTemplate() == null && section != null) {
			this.addObjectToBothSidesOfRelationshipWithKey(section.dataSetTemplate(), DATASETTEMPLATE);
		}
	}

	/**
	 * set isRequired to false, if active is set to false
	 */
	public void validateForSave () {
		super.validateForSave();
		if (this.active() != null && this.active().booleanValue() == false) {
			this.setIsRequired(Boolean.FALSE);
		}
	}

}