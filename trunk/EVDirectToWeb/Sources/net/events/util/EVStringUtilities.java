package net.events.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EVStringUtilities {
	
	/**
	 * Method for cutting a string to a shortened version with appended "..." characters. 
	 * Don't use this for displaying shortened strings in the interface, use ERXFixedLengthString there. This
	 * method is for use inside Java classes.
	 * 
	 * @param s - the string to shorten
	 * @param maxLength - the length (including the ... characters)
	 * 
	 * @return shortened string
	 */
	public static String shortenStringWithAppendingEllipse (String s, int maxLength) {
		if (s == null || s.length() <= maxLength) {
			return s;
		}
		
		if (s.indexOf(" ") < maxLength - 4) {
			while (s.length() > maxLength - 4) {
				s = s.substring(0, s.lastIndexOf(" "));
			}
			return s + " ...";
		}
		else if (s.length() > maxLength - 4){
			return s.substring(0, maxLength - 4) + " ...";
		}
		else {
			return s + " ...";
		}
	}

	/**
	 * Removes all html tags from a string by replacing them with a whitespace
	 * 
	 * @param s the string
	 * @return the string without html tags
	 */
	public static String removeTagsFromString (String s) {

		String content = s;

		// Replace anything between script or style tags
		// We should do some extra work to allow for white spaces in the HTML
		String scriptregex = "<(script|style)[^>]*>[^<]*</(script|style)>";
		Pattern p1 = Pattern.compile(scriptregex, Pattern.CASE_INSENSITIVE);
		Matcher m1 = p1.matcher(content);
		// For displaying results
		int count = 0;
		while (m1.find()) {
			// System.out.println(m1.group());
			count++;
		}
		// System.out.println("Removed " + count + " script & style tags");
		// Replace any matches with nothing
		content = m1.replaceAll("");

		// A Regex to match anything in between <>
		// Reads as: Match a "<"
		// Match one or more characters that are not ">"
		// Match "<";
		String tagregex = "<[^>]*>";
		Pattern p2 = Pattern.compile(tagregex);
		Matcher m2 = p2.matcher(content);
		count = 0;
		while (m2.find()) {
			// System.out.println(m.group());
			count++;
		}
		// System.out.println("Removed " + count + " other tags.");

		// Replace any matches with nothing
		content = m2.replaceAll(" ");

		// Oh what the hey, let's get rid of a lot of extra carriage returns
		// Matches two new lines, followed by zero or more white spaces
		// Followed by one or more new lines
		String multiplenewlines = "(\\n{2})\\s*\\n+"; // note: (\\n{2} should
														// work here, but it
														// doesn't?)
		// Replace with the original two new lines
		// Backreference not exactly necessary, but a nice demonstration
		content = content.replaceAll(multiplenewlines, "$1");

		// Create an output stream and file channel to write out a report
		// (Also print out report to screen)

		return content;
	}

}
