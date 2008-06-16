package net.events.d2w.components;
// Generated by the WOLips Templateengine Plug-in at 19.02.2007 17:27:28

import com.webobjects.appserver.WOContext;

import er.extensions.appserver.navigation.ERXNavigationMenuItem;

public class EVNavigationMenuItem extends ERXNavigationMenuItem {

    public EVNavigationMenuItem(WOContext context) {
        super(context);
    }
    
    /**
     * Returns a css class according to our naming conventions
     */
    public String linkClass() {
        if(level() == 0) {
            return "";
        }
        return "d2w_navlev" + level() + (isSelected() ? "_selected" : (isDisabled() ? "_disabled" : ""));
    }
    
}