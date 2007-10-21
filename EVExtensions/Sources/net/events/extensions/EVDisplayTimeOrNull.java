package net.events.extensions;


import com.webobjects.appserver.WOContext;

import er.extensions.ERXStatelessComponent;

public class EVDisplayTimeOrNull extends ERXStatelessComponent {

    public EVDisplayTimeOrNull(WOContext context) {
        super(context);
    }

    /**
     * We return either a value set by the binding "dateformat" or a default value "%H:%M" - this
     * should go through localization and return a readable value for every available localization
     * 
     * @return dateformat
     */
    public String dateformat () {
    	return this.stringValueForBinding("dateformat", "%H:%M");
    }
    
    public void setDateformat (String dateformat) {
    	this.setValueForBinding(dateformat, "dateformat");
    }

}