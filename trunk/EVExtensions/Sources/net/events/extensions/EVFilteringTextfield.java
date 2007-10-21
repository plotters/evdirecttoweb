package net.events.extensions;


import com.webobjects.appserver.WOContext;

import er.extensions.ERXNonSynchronizingComponent;
import er.extensions.ERXStringUtilities;

public class EVFilteringTextfield extends ERXNonSynchronizingComponent {

    public EVFilteringTextfield(WOContext context) {
        super(context);
    }

    public void setValue (String v) {
    	// filter certain characters
    	this.setValueForBinding(ERXStringUtilities.removeCharacters(v, "<>.+-!?\\/%*\"_="), "value");
    }
    
    public String value () {
    	// filter certain characters
    	return ERXStringUtilities.removeCharacters((String) this.valueForBinding("value"), "<>.+-!?\\/%*\"_=");
    }
    
    public String elementName () {
    	return this.stringValueForBinding("elementName");
    }
    
    public void setElementName (String s) {
    	this.setValueForBinding(s, "elementName");
    }
    
    public String type () {
    	return this.stringValueForBinding("type");
    }
    
    public void setType (String t) {
    	this.setValueForBinding(t, "type");
    }
    
    public String name () {
    	return this.stringValueForBinding("name");
    }
    
    public void setName (String n) {
    	this.setValueForBinding(n, "name");
    }
    
    public String cssClass () {
    	return this.stringValueForBinding("cssClass");
    }
    
    public void setCssClass (String c) {
    	this.setValueForBinding(c, "cssClass");
    }
    
    public String placeholder () {
    	return this.stringValueForBinding("placeholder");
    }
    
    public void setPlaceholder (String p) {
    	this.setValueForBinding(p, "placeholder");
    }
    
    public String autosave () {
    	return this.stringValueForBinding("autosave");
    }
    
    public void setAutosave (String a) {
    	this.setValueForBinding(a, "autosave");
    }
    
    public String results () {
    	return this.stringValueForBinding("results");
    }
    
    public void setResults (String r) {
    	this.setValueForBinding(r, "results");
    }
    
    public String allowedTags () {
    	return this.stringValueForBinding("allowedTags");
    }
    
    public void setAllowedTags (String at) {
    	this.setValueForBinding(at, "allowedTags");
    }
}