package net.events.cms.extensions;

import net.events.cms.eo.*;
import net.events.extensions.*;

import com.webobjects.appserver.*;

public class DataSetAnswerComponent extends EVComponent {
	
	private DataSetItemValue value;

	/**
	 * Standard constructor
	 * 
	 * @param context
	 */
	public DataSetAnswerComponent (WOContext context) {
		super(context);
	}

	/**
	 * @return the value
	 */
	public DataSetItemValue value() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(DataSetItemValue value) {
		this.value = value;
	}
	
	
}
