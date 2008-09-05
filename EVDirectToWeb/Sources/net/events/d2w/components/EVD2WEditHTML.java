package net.events.d2w.components;

import net.events.appserver.EVApplication;

import com.webobjects.appserver.WOContext;

import er.directtoweb.components.ERDCustomEditComponent;

/**
 * Very basic rich text editor that uses FCKEditor
 * 
 * @author cug
 *
 */
public class EVD2WEditHTML extends ERDCustomEditComponent {
	
	/**
	 * Id for the textfield
	 */
	private String id = null;
	
    /**
     * Standard constructor
     * 
     * @param context
     * @author cug
     */
    public EVD2WEditHTML(WOContext context) {
        super(context);
    }
    
    /**
     * Just set the id to null
     *  
     * @see er.directtoweb.components.ERDCustomEditComponent#reset()
     * 
     * @author cug - Aug 21, 2008
     */
    public void reset () {
    	this.id = null;
    	super.reset();
    }
    
    /**
     * ID for the editor
     * 
     * @return
     * 
     * @author cug - Aug 21, 2008
     */
    public String editorId () {
    	if (this.id == null) {
    		this.id = "fck" + ((EVApplication) this.application()).randomInteger();
    	}
    	return this.id;
    }
}