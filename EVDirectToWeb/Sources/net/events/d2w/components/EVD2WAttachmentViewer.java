package net.events.d2w.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2WStatelessComponent;

import er.attachment.model.ERAttachment;

public class EVD2WAttachmentViewer extends D2WStatelessComponent {
	
    public EVD2WAttachmentViewer(WOContext context) {
        super(context);
    }
    
    public ERAttachment attachment () {
    	return (ERAttachment) object().valueForKey(propertyKey());
    }
    
    public void setAttachment (ERAttachment a) {
    	object().addObjectToBothSidesOfRelationshipWithKey(a, propertyKey());
    }
}