package net.events.designer.appserver;

import net.events.d2w.extensions.*;
import net.events.designer.delegates.*;

import com.webobjects.appserver.*;
import com.webobjects.directtoweb.*;

import er.directtoweb.*;

public class EVDesignerD2WFactory extends ERD2WFactory {
	
	public EVDesignerD2WFactory () {
		super();
	}
	
	public WOComponent defaultPage(WOSession session) {
		D2WContext c = new D2WContext(session);
		
		String entityName = (String) c.valueForKey("startEntity");
		return ((EVD2WSession) session).d2wListPageForEntity(entityName);
	}
	
	public NextPageDelegate defaultListBranchDelegate (WOComponent component) {
		return new ListPageBranchDelegate();
	}

}
