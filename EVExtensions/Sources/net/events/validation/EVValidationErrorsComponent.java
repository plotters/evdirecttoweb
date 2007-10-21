package net.events.validation;


import com.webobjects.appserver.*;

public class EVValidationErrorsComponent extends WOComponent {

	private String error;

    public EVValidationErrorsComponent(WOContext context) {
        super(context);
    }
    
    public void awake () {
    	error = "";
    }
    
    public boolean isStateless () {
    	return true;
    }
    
    public boolean synchronizesVariablesWithBindings() {
    	return false;
    }

	/**
	 * @return the error
	 */
	public String getError() {
		return this.error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

}