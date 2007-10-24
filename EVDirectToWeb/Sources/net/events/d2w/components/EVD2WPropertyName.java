package net.events.d2w.components;
// Generated by the WOLips Templateengine Plug-in at Apr 8, 2007 9:52:59 PM

import com.webobjects.appserver.WOContext;

import er.directtoweb.ERD2WPropertyName;
import er.extensions.ERXSession;

/**
 * Cleaned up component 
 * 
 * @author cug
 */
public class EVD2WPropertyName extends ERD2WPropertyName {

    public EVD2WPropertyName(WOContext context) {
        super(context);
    }
    
    /**
     * Localize also names we get from the rules
     */
    public String displayNameForProperty() {
        if(_displayNameForProperty == null) {
        	String nameFromRules = (String)d2wContext().valueForKey("displayNameForProperty");
        	if (nameFromRules != null) {
        		_displayNameForProperty = ((ERXSession) session()).localizer().localizedStringForKeyWithDefault(nameFromRules);
        	}
        }
        return _displayNameForProperty;
    }

}