package net.events.extensions;


import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;

import er.extensions.ERXSession;

/**
 * Small search component for the use with EmbeddedList - it pushes a qualifier
 * back to the parent component. The parent can fetch objects from the database or
 * a displaygroup with this. 
 * 
 * @author cug
 *
 */

public class EVEmbeddedListSearch extends WOComponent {
	
	/**
	 * Contanst for "attributes" string
	 */
	public static final String kATTRIBUTES = "attributes";
	
	/**
	 * Constant for "qualifierMethods" string
	 */
	public static final String kQUALIFIER_METHODS = "qualifierMethods";
	
	/**
	 * The currently selected attribute from the popup
	 */
	private String selectedAttribute;
	
	/**
	 * The currently selected method from the methods popup
	 */
	private String selectedMethod;
	
	/**
	 * The value from the search field
	 */
	private String value;
	
	/**
	 * Helper for iterating over the attributes
	 */
	private String attribute;
	
	/**
	 * Helper for iterating over the methods
	 */
	private String method;

	/**
	 * Standard constructor
	 * @param context
	 */
    public EVEmbeddedListSearch(WOContext context) {
        super(context);
    }
    
    /**
     * This component checks the parent component. If the parent is NOT an EmbeddedList (or subclass) it
     * will throw a RuntimeException.
     */
    public void awake () {
    	super.awake();
        // we check carefully, that we are embedded in a component we can work with
        if (!(this.parent() instanceof EVEmbeddedList) ) {
        	throw new RuntimeException("An EVEmbeddedListSearch can only be used in a subclass of EmbeddedList");
        }
    }
    
    /**
     * Returns the attributes that can be used for searching.
     * 
     * @return NSArray of attributes
     */
    public NSArray attributes () {
    	return (NSArray) this.valueForBinding(kATTRIBUTES);
    }

    /**
     * Set the attributes that can be used for searching.
     * 
     * @param array NSArry of strings 
     */
    public void setAttributes (NSArray array) {
    	this.setValueForBinding(array, kATTRIBUTES);
    }
    
    /**
     * The possible qualifierMethods, currently this is not really supported - you should just
     * leave this part alone.
     * 
     * @return NSArray of qualifierMethods (currently "starts with" and "contains").
     */
    public NSArray qualifierMethods () {
    	if (this.valueForBinding(kQUALIFIER_METHODS) != null) {
    		return (NSArray) this.valueForBinding(kQUALIFIER_METHODS);
    	}
    	else return new NSArray (new String[] {"starts with", "contains"}); 
    }
    
    /**
     * Don't use!
     */
    public void setQualifierMethods (NSArray array) {
    	this.setValueForBinding(array, kQUALIFIER_METHODS);
    }

    /**
     * This is called by the included search button. It returns null, because it wants
     * to stay on the page with the embedded list it is attached to.  
     * <br><br>
     * This method only builds a qualifier based on the user input in the form and
     * pushes this qualifier to the parent component.
     * 
     * @return same page, hopefully with a search result.
     */
    public WOComponent search () {
    	// we can be sure, that this works, as we have tested in at creation time
    	EVEmbeddedList parent = (EVEmbeddedList) this.parent();
    	parent.resetQualifier();
    	
    	if (this.value() != null && !this.value().equals("")) {
    		String searchValue = this.value();
    		if (selectedMethod != null && selectedMethod.equals("starts with")) {
    			searchValue += "*";
    		}
    		else if (selectedMethod != null && selectedMethod.equals("contains")) {
    			searchValue = "*" + searchValue + "*";
    		}
    		
	    	EOQualifier qualifier = EOQualifier.qualifierWithQualifierFormat(this.selectedAttribute() + " caseInsensitiveLike %@", new NSArray(searchValue));
	    	parent.addQualifierToQualifierWithAnd(qualifier);
    	}
    	
    	return null;
    }
    
    // Getters and setters.

	/**
	 * @return the selectedAttribute
	 */
	public String selectedAttribute() {
		return selectedAttribute;
	}

	/**
	 * @param selectedAttribute the selectedAttribute to set
	 */
	public void setSelectedAttribute(String selectedAttribute) {
		this.selectedAttribute = selectedAttribute;
	}

	/**
	 * @return the value
	 */
	public String value() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the attribute
	 */
	public String attribute() {
		return this.attribute;
	}
	
	public String localizedAttribute () {
		return ((ERXSession) this.session()).localizer().localizedStringForKeyWithDefault(this.attribute);
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * @return the method
	 */
	public String method() {
		return method;
	}
	
	public String localizedMethod () {
		return ((ERXSession) this.session()).localizer().localizedStringForKeyWithDefault(this.method);
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the selectedMethod
	 */
	public String selectedMethod() {
		return selectedMethod;
	}

	/**
	 * @param selectedMethod the selectedMethod to set
	 */
	public void setSelectedMethod(String selectedMethod) {
		this.selectedMethod = selectedMethod;
	}

}