package net.events.validation;


import com.webobjects.appserver.*;

public class EVValidatingLabel extends WOComponent {

    public EVValidatingLabel(WOContext context) {
        super(context);
    }

    public boolean synchronizesVariablesWithBindings() {
    	return false;
    }
    
    
}