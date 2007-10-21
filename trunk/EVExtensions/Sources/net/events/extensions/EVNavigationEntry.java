package net.events.extensions;


import er.extensions.ERXLocalizer;


public class EVNavigationEntry {
	
	private String displayName;
	private String componentName;
	private String actionName;
	private String alt;
	private String title;
	private String accesskey;
	

	public EVNavigationEntry (String displayName, String componentName, String actionName, String title, String alt, String accesskey) {
		this.displayName = displayName;
		this.componentName = componentName;
		this.actionName = actionName;
		this.alt = alt;
		this.title = title;
		this.accesskey = accesskey;
	}

	public EVNavigationEntry (String displayName, String componentName, String actionName, String title) {
		this (displayName, componentName, actionName, title, title, null);
	}


	public EVNavigationEntry (String displayName, String componentName, String actionName) {
		this (displayName, componentName, actionName, null, null, null);
	}

	/**
	 * @return the actionName
	 */
	public String actionName() {
		return this.actionName;
	}

	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return the componentName
	 */
	public String componentName() {
		return this.componentName;
	}

	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	/**
	 * @return the displayName
	 */
	public String displayName() {
		return this.displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the alt
	 */
	public String alt() {
		return ERXLocalizer.defaultLocalizer().localizedStringForKey(this.alt);
	}

	/**
	 * @param alt the alt to set
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 * @return the title
	 */
	public String title() {
		return ERXLocalizer.defaultLocalizer().localizedStringForKey(this.title);
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the accesskey
	 */
	public String accesskey() {
		return this.accesskey;
	}

	/**
	 * @param accesskey the accesskey to set
	 */
	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

}
