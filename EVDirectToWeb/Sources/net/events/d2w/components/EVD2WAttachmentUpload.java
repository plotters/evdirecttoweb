package net.events.d2w.components;

import net.events.appserver.EVApplication;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEnterpriseObject;

import er.attachment.model.ERAttachment;
import er.directtoweb.components.ERDCustomEditComponent;

/**
 * D2W Component for uploading attachments
 * 
 * @author cug
 *
 */
public class EVD2WAttachmentUpload extends ERDCustomEditComponent {
		
    private String updateContainerId;

	/**
     * Standard constructor
     * 
     * @param context
     * @author cug
     */
    public EVD2WAttachmentUpload(WOContext context) {
        super(context);
    }
    
    public void setAttachment (ERAttachment attachment) {
    	object().addObjectToBothSidesOfRelationshipWithKey(attachment, d2wContext().propertyKey());
    }
    
    public ERAttachment attachment () {
    	return (ERAttachment) object().valueForKey(d2wContext().propertyKey());
    }
    
	public WOActionResults deleteAttachment() {
		EOEnterpriseObject attachment = attachment();
		object().removeObjectFromBothSidesOfRelationshipWithKey(attachment, d2wContext().propertyKey());
		object().editingContext().deleteObject(attachment);
		return null;
	}
	
	public String updateContainerId () {
		if (updateContainerId == null) {
			updateContainerId = (String) d2wContext().valueForKey("updateContainerId");
			if (updateContainerId == null) {
				updateContainerId = "UDC_" + d2wContext().entity().name() + "_" + Math.abs(((EVApplication) application()).randomInteger());
			}
		}
		return updateContainerId;
	}

   
}