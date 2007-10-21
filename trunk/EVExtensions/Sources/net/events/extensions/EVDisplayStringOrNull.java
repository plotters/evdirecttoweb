package net.events.extensions;


import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSKeyValueCoding;

import er.extensions.ERXSession;
import er.extensions.ERXStatelessComponent;
import er.extensions.ERXStringUtilities;
import er.extensions.ERXStringWithLineBreaks;

public class EVDisplayStringOrNull extends ERXStatelessComponent {

    public EVDisplayStringOrNull(WOContext context) {
        super(context);
    }
    
    public void setValue (String s) {
    	this.setValueForBinding(s, "value");
    } 
    
    public String value () {
    	if (!(this.valueForBinding("value") instanceof NSKeyValueCoding.Null)) {
    		return (String) this.valueForBinding("value");
    	} 
    	else return "-- " + (String) ((ERXSession) session()).localizer().valueForKey("empty") + " --";
    }

    public void setAddLineBreaks (Object flag) {
    	this.takeValueForKey(flag, "addLineBreaks");
    }
    
    public boolean addLineBreaks () {
    	return this.booleanValueForBinding("addLineBreaks", false);
    } 

    public String componentName () {
    	if (this.booleanValueForBinding("addLineBreaks", false)) {
    		return ERXStringUtilities.lastPropertyKeyInKeyPath(ERXStringWithLineBreaks.class.getName());
    	}
    	else return "WOString";
    } 

}