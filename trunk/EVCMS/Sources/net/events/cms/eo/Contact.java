// Contact.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

public abstract class Contact extends _Contact {
	
	private static Logger log = Logger.getLogger( Contact.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		this.setCreationTime(new NSTimestamp());
		this.setDeleted(Boolean.FALSE);
	}


}