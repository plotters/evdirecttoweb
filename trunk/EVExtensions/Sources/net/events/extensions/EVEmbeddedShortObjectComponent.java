package net.events.extensions;


import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOGenericRecord;

import er.extensions.ERXStatelessComponent;

public class EVEmbeddedShortObjectComponent extends ERXStatelessComponent {

    public EVEmbeddedShortObjectComponent(WOContext context) {
        super(context);
    }

	/**
	 * @return the object
	 */
	public EOGenericRecord object () {
		return (EOGenericRecord) this.valueForBinding("object");
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(EOGenericRecord object) {
		this.setValueForBinding(object, "object");
	}

}