package net.events.localization;


import com.webobjects.foundation.*;

import er.extensions.*;

public class EVLocalizerProxy implements NSKeyValueCoding {
	
	ERXSession session;

	public EVLocalizerProxy(ERXSession session) {
		super();
		
		this.session = session;
	}

	/**
	 * This method should be used for building a proxy to determine whether a user has specific settings
	 * for Localizable.strings such as self defined date formats, which may be saved in the users
	 * preferences.
	 * <br><br>
	 * Have to work out, how I can make this re-usable without too much dependencies. Perhaps going 
	 * with "convention" and some "configuration" (see "Agile Development with Ruby on Rails") might be
	 * a good idea.
	 * <br><br>
	 * Current implementation just forwards to the sessions localizer. 
	 * 
	 * @param key
	 * @return
	 */
	public Object valueForKey (String key) {
		
		return (String) this.session.localizer().valueForKeyPath(key);
	}

	public void takeValueForKey(Object value, String key) {
	}
}
