/*
 * $Id: EVPage.java,v 1.1 2007/06/04 05:16:00 cug Exp $ Created on May 23, 2007
 * 
 * $RCSFile: $ $Revision: 1.1 $ $Date: 2007/06/04 05:16:00 $ $Author: cug $
 */
package net.events.extensions;

import com.webobjects.appserver.*;

/**
 * Page level component
 * 
 * @author cug - May 23, 2007
 */
public abstract class EVPage extends EVComponent {

	/**
	 * Standard constructor
	 * 
	 * @author cug - May 23, 2007
	 * @param c
	 */
	public EVPage(WOContext c) {
		super(c);
	}
}
