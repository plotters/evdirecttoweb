package net.events.extensions;


import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.eocontrol.EOGenericRecord;

import er.extensions.ERXNonSynchronizingComponent;

public class EVObjectTableComponent extends ERXNonSynchronizingComponent {
	
	private boolean odd = true;
	
	public EVObjectTableComponent (WOContext context) {
        super(context);
    }
	
	public void awake () {
		super.awake();
		odd = true;
	}
    
    public WODisplayGroup displayGroup () {
    	return (WODisplayGroup) valueForBinding("displayGroup");
    }
    
    public void setDisplayGroup (WODisplayGroup displayGroup) {
    	this.setValueForBinding(displayGroup, "displayGroup");
    }
    
    public EOGenericRecord object() {
    	return (EOGenericRecord) this.valueForBinding("object");
    }
    
    public void setObject(EOGenericRecord eo) {
    	this.setValueForBinding(eo, "object");
    }
    
    public String inspectObjectMethod () {
    	return (String) this.valueForBinding("inspectObjectMethod");
    }
    
    public void setInspectObjectMethod (String method) {
    	this.setValueForBinding(method, "inspectObjectMethod");
    }
    
    public WOComponent inspectObjectAction () {
    	return (WOComponent) this.performParentAction(this.inspectObjectMethod());
    }
        
    public String rowClass () {
    	odd = !odd;
    	if (odd) {
    		return "tl_odd";
    	}
    	else return "tl_even"; 
    }
	
}
