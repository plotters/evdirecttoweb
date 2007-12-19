package net.events.d2w.components;

import net.events.d2w.extensions.*;

import com.webobjects.appserver.*;

import er.ajax.*;
import er.directtoweb.*;

public class EVD2WEditHTML extends ERDEditHTML {
	
	private String id = null;
	
    public EVD2WEditHTML(WOContext context) {
        super(context);
    }
    
    public void reset () {
    	this.id = null;
    	super.reset();
    }
    
    public void appendToResponse (WOResponse response, WOContext context) {
    	super.appendToResponse(response, context);
    }
    
    public String editorId () {
    	if (this.id == null) {
    		this.id = "fck" + ((EVD2WApplication) this.application()).randomInteger();
    	}
    	return this.id;
    }
}