package net.events.page;


import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.foundation.NSArray;

public class EVVerifyDeletePage extends EVPageLevelComponent {
	
	private String message;
	private EOEnterpriseObject object;
	private EVPageLevelEditComponent caller;

    public EVVerifyDeletePage(WOContext context) {
        super(context);
    }
    
    public WOComponent delete () {
    	return caller().deleteObject();
    }
    
    public WOComponent cancel () {
    	return caller().cancelDeletion();
    }

	/**
	 * @return the message
	 */
	public String message() {
		return this.message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the object
	 */
	public EOEnterpriseObject object() {
		return this.object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(EOEnterpriseObject object) {
		this.object = object;
	}

	/**
	 * @return the caller
	 */
	public EVPageLevelEditComponent caller() {
		return this.caller;
	}

	/**
	 * @param caller the caller to set
	 */
	public void setCaller(EVPageLevelEditComponent caller) {
		this.caller = caller;
	}

	public String mainNavigationArea() {
		return null;
	}

	public NSArray subNavigation() {
		return null;
	}
    
}