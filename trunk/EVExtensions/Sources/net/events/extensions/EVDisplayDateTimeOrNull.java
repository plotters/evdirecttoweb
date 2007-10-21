package net.events.extensions;


import com.webobjects.appserver.WOContext;

import er.extensions.ERXLocalizer;
import er.extensions.ERXStatelessComponent;

public class EVDisplayDateTimeOrNull extends ERXStatelessComponent {

    public EVDisplayDateTimeOrNull(WOContext context) {
        super(context);
    }
    
    public String dateformat () {
   		return stringValueForBinding("dateformat", (String) ERXLocalizer.currentLocalizer().valueForKey("ShortDateTimeString"));
    }
    
}