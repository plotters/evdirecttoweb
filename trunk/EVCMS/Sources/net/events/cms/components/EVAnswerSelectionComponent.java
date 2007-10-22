package net.events.cms.components;

import net.events.cms.eo.*;
import net.events.cms.extensions.*;

import com.webobjects.appserver.*;

/**
 * For the use with surveys - displays a set of radio buttons or a popup for a to one relationship
 *  
 * @author cug
 */
public class EVAnswerSelectionComponent extends DataSetAnswerComponent {
	
	/**
	 * For iterating over the options
	 */
	private DataSetSelectionOption option;
	
	/**
	 * the selected option
	 */
	private DataSetSelectionOption selectedOption;

	/**
	 * Standard constructor
	 * 
	 * @param context
	 */
	public EVAnswerSelectionComponent(WOContext context) {
		super(context);
	}

	/**
	 * @return the option
	 */
	public DataSetSelectionOption option() {
		return option;
	}

	/**
	 * @param option the option to set
	 */
	public void setOption(DataSetSelectionOption option) {
		this.option = option;
	}


	/**
	 * @return the selectedOption
	 */
	public DataSetSelectionOption selectedOption() {
		return selectedOption;
	}


	/**
	 * @param selectedOption the selectedOption to set
	 */
	public void setSelectedOption(DataSetSelectionOption selectedOption) {
		this.selectedOption = selectedOption;
	}
	
	/**
	 * Does the class cast to get rid of the errors in the component
	 * 
	 * @return a DataSetSelectionItem 
	 */
	public DataSetSelectionItem dataSetItem () {
		return (DataSetSelectionItem) this.value().dataSetItem();
	}
	
	/**
	 * Return either "radio" or "popup" as the style
	 * @return
	 */
	public String toOneUIStyle () {
		if (this.value().dataSetItem().interfaceElementType() != null && "Popup Button".equals(this.value().dataSetItem().interfaceElementType().elementName())) {
			return "popup";
		}
		else return "radio";
	}
}
