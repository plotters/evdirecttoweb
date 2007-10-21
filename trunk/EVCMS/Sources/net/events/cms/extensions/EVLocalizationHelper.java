package net.events.cms.extensions;

import com.webobjects.appserver.*;

import er.extensions.*;

public class EVLocalizationHelper {
	
	/**
	 * Returns a localized objectForKey on key, calling toString to convert it
	 * to a string. Needs the request to determine the browswer languages. If
	 * you have a session or are inside a component, use the localizer there.
	 * 
	 * @param key -
	 *            the string to localize
	 * @param request -
	 *            the current request
	 * 
	 * @return the localized string
	 */
	public static String localizedStringForKeyAndRequest (String key, WORequest request) {
		String localizedString = null;
		if (request != null && key != null) {
			ERXLocalizer localizer = ERXLocalizer.localizerForRequest(request);
				localizedString = localizer.localizedValueForKeyWithDefault(key).toString();
		}
		return localizedString;
	}

}
