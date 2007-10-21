package net.events.cms.eo;

import net.events.eof.*;

public class EVCMSGenericRecord extends EVGenericRecord {
	
	/**
	 * Records updates to the objects
	 */
	public void willUpdate () {
		super.willUpdate();
		this.createModificationsEntry();
	}

}
