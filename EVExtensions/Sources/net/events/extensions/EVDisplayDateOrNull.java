package net.events.extensions;


import com.webobjects.appserver.WOContext;

import er.extensions.ERXStatelessComponent;

/**
 * This component acts as a small helper for the use in a WOSwitchComponent when you don't know whether 
 * you have a dateformat (or can't set one, because you have other components you use there which can't take
 * a dateformat binding.
 * <br><br>
 * A dateformat is automatically applied if you don't set one. Format is "%d.%m.%Y", which should get 
 * localized automatically.
 * <br><br>
 * If you apply a dateformat, yours is used. 
 * 
 * @author cug
 */
public class EVDisplayDateOrNull extends ERXStatelessComponent {
	
    public EVDisplayDateOrNull(WOContext context) {
        super(context);
    }

    /**
     * We return either a value set by the binding "dateformat" or a default value "%d.%m.%Y" - this
     * should go through localization and return a readable value for every available localization
     * 
     * @return dateformat
     */
    public String dateformat () {
    	return this.stringValueForBinding("dateformat", "%d.%m.%Y");
    }
    
    public void setDateformat (String dateformat) {
    	this.setValueForBinding(dateformat, "dateformat");
    }
}