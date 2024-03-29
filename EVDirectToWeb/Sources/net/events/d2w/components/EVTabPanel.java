package net.events.d2w.components;
// Generated by the WOLips Templateengine Plug-in at 07.12.2006 10:52:09

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;

import er.extensions.components.ERXTabPanel;
import er.extensions.foundation.ERXValueUtilities;

/**
 * Overriden to support tabs with link in other browsers than just IE.
 */
public class EVTabPanel extends ERXTabPanel {
	
	private Boolean _useLinkForTabSwitch;

    public EVTabPanel(WOContext context) {
        super(context);
    }
    
    /**
     * Overriden to support tabs with links in other browsers than just IE.
     */
    public boolean useLinkForTabSwitch() {
        if (_useLinkForTabSwitch == null) {
            _useLinkForTabSwitch = ERXValueUtilities.booleanValue(session().valueForKey("javaScriptEnabled")) ? Boolean.TRUE : Boolean.FALSE;
        }
        return _useLinkForTabSwitch.booleanValue();
    }

    /**
     * Overriden to support tabs with links in other browsers than just IE.
     */
    public void appendToResponse(WOResponse aResponse, WOContext aContext)  {
        _useLinkForTabSwitch=null;
        super.appendToResponse(aResponse, aContext);
    }
    
    public String updateContainerId () {
    	return (String) this.valueForBinding("updateContainerId");
    }
    
    public void setUpdateContainerId (String id) {
    	this.setValueForBinding(id, "updateContainerId");
    }
    
    /**
     * Overridden to get localization back without rewriting all strings
     */
    public Object currentTabNameWithoutSpaces() {
    	return NSArray.componentsSeparatedByString((String)valueForKey("currentTabName"), " ").componentsJoinedByString("");    
    }
}