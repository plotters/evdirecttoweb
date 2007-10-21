package net.events.extensions;


import com.webobjects.foundation.NSArray;

public class EVAuthorizedNavigationEntry extends EVNavigationEntry {
	
	private NSArray requiredAuthorizations; 
	
	private String keypathToPerformAdditionalCheck = null;
	
	public EVAuthorizedNavigationEntry(String displayName, String componentName, String actionName, String title, String alt, String accesskey, NSArray requiredAuthorizations) {
		super (displayName, componentName, actionName, title, alt, accesskey);
		
		this.requiredAuthorizations = requiredAuthorizations;
	}
	
	public EVAuthorizedNavigationEntry(String displayName, String componentName, String actionName, String title, String alt, String accesskey, NSArray requiredAuthorizations, String keypathToCheck) {
		super (displayName, componentName, actionName, title, alt, accesskey);

		this.requiredAuthorizations = requiredAuthorizations;
		this.keypathToPerformAdditionalCheck = keypathToCheck;
	}
	
	/**
	 * @return the requiredAuthorization
	 */
	public NSArray requiredAuthorizations() {
		return this.requiredAuthorizations;
	}

	/**
	 * @param requiredAuthorization the requiredAuthorization to set
	 */
	public void setRequiredAuthorization(NSArray a) {
		this.requiredAuthorizations = a;
	}

	/**
	 * @return the keypathToPerformAdditionalCheck
	 */
	public String keypathToPerformAdditionalCheck() {
		return this.keypathToPerformAdditionalCheck;
	}

	/**
	 * @param keypathToPerformAdditionalCheck the keypathToPerformAdditionalCheck to set
	 */
	public void setKeypathToPerformAdditionalCheck(String keypathToPerformAdditionalCheck) {
		this.keypathToPerformAdditionalCheck = keypathToPerformAdditionalCheck;
	}

}
