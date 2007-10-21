package net.events.page;



import net.events.extensions.EVNavigationEntry;

import com.webobjects.appserver.WOComponent;
import com.webobjects.foundation.NSArray;

public interface EVPageLevelInterface {

	public WOComponent navigate ();
	public EVNavigationEntry subNaviItem();
	public void setSubNaviItem(EVNavigationEntry subNaviItem);
	public NSArray subNaviList();
	public void setSubNaviList(NSArray subNaviList);

	public NSArray subNavigation();
	public String mainNavigationArea();

}
