package net.events.appserver;


import com.webobjects.appserver.*;
import com.webobjects.foundation.*;

import er.extensions.*;

/**
 * Base class for direct actions. This class only adds a try / catch block around 
 * <code>super.performActionNamed(String name)</code> to avoid deadlocks.
 * <br><br>
 * You should nevertheless be sure not to let exceptions through the code you 
 * do in "performActionNamed" if you implement this method in your DirectAction subclass. 
 * <br><br>
 * @author cug
 */
public class EVDirectAction extends ERXDirectAction {

	private String kEDITING_CONTEXT = "EDITING_CONTEXT_FOR_RRLOOP";
	
	protected ERXEC editingContext;

	/**
	 * Standard constructor
	 * 
	 * @param r
	 */
	public EVDirectAction(WORequest r) {
		super(r);
		
		this.editingContext = (ERXEC) ERXEC.newEditingContext();
		ERXThreadStorage.takeValueForKey(this.editingContext, this.kEDITING_CONTEXT );
	}
	
	/**
	 * We call super in a try/catch block to catch all exceptions during the execution of
	 * directActions to avoid deadlocks.
	 * 
	 * We also kick an editingContext into ERXThreadStorage, so we use only one editingContext
	 * throughout the request-response loop.
	 */
	public WOActionResults performActionNamed (String name) {
		this.existingSession();
		try {
			return super.performActionNamed (name);
		}
		catch (Exception e) {
			e.printStackTrace();
			return ERXApplication.application().handleException(e, this.context());
		}
		catch (Throwable t) {
			t.printStackTrace();
			return ERXApplication.application().handleException(new NSForwardException(t), this.context());
		}
	}

}
