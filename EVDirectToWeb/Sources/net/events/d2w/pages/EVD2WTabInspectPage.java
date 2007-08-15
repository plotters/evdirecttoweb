package net.events.d2w.pages;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOObjectStoreCoordinator;
import com.webobjects.foundation.NSArray;

import er.directtoweb.ERD2WFactory;
import er.directtoweb.ERD2WTabInspectPage;
import er.extensions.ERXEOControlUtilities;
import er.extensions.ERXValueUtilities;

public class EVD2WTabInspectPage extends ERD2WTabInspectPage {
	/**
	 * 
	 * to get rid of the warning ...
	 * 
	 */
	private static final long serialVersionUID = -2096810900030197058L;

	public static String IMAGE_TAB_COMPONENT_NAME = "ERXImageTabPanel";

	public static String TEXT_TAB_COMPONENT_NAME = "EVTabPanel";

	public EVD2WTabInspectPage(WOContext context) {
		super(context);
	}

	/**
	 * Overridden because we might want to stay on the same page
	 */
	public WOComponent editAction() {

		WOComponent returnPage = null;

		if (ERXValueUtilities.booleanValue(this.d2wContext().valueForKey("stayOnInspectPageAfterInspectEdit"))) {
			String editConfigurationName = (String) d2wContext().valueForKey("editConfigurationName");
			EditPageInterface editPage;
			if (editConfigurationName != null && editConfigurationName.length() > 0) {
				editPage = (EditPageInterface) D2W.factory().pageForConfigurationNamed(editConfigurationName, session());
			} else {
				editPage = D2W.factory().editPageForEntityNamed(object().entityName(), session());
			}
			Object value = d2wContext().valueForKey("useNestedEditingContext");
			boolean createNestedContext = ERXValueUtilities.booleanValue(value);
			EOEnterpriseObject object = ERXEOControlUtilities.editableInstanceOfObject(object(), createNestedContext);
			editPage.setObject(object);
			// editPage.setNextPage(nextPage());
			editPage.setNextPage(this);
			returnPage = (WOComponent) editPage;
		}

		return returnPage != null ? returnPage : previousPage();
	}
	
	public String defaultRowspan() {
		return "" + (currentSection() != null && currentSection().keys != null ? currentSection().keys.count() : 0) + 2;
	}

	public WOComponent printerFriendlyVersion() {
		WOComponent result = ERD2WFactory.erFactory().printerFriendlyPageForD2WContext(d2wContext(), session());
		((EditPageInterface) result).setObject(object());
		return result;
	}

	public String currentSectionImageName() {
		String name = currentSection().name;
		name = (NSArray.componentsSeparatedByString(name, " ")).componentsJoinedByString("");
		return "/nsi/section" + name + ".gif";
	}

	public String saveButtonFileName() {
		return object() != null && object().editingContext() != null ? object().editingContext().parentObjectStore() instanceof EOObjectStoreCoordinator ? "/nsi/buttonSave.gif" : "/nsi/buttonOK.gif" : "/nsi/buttonSave.gif";
	}

	public boolean shouldShowReturnButton() {
		Integer i = (Integer) d2wContext().valueForKey("shouldShowReturnButton");
		return i != null && i.intValue() == 1;
	}

	public String cancelButtonFileName() {
		return shouldShowReturnButton() ? "/nsi/buttonReturn.gif" : "/nsi/buttonCancel.gif";
	}

	public boolean useTabImages() {
		return ERXValueUtilities.booleanValue(d2wContext().valueForKey("useTabImages"));
	}

	public boolean useTabSectionImages() {
		return ERXValueUtilities.booleanValue(d2wContext().valueForKey("useTabSectionImages"));
	}

	public String tabComponentName() {
		return useTabImages() ? IMAGE_TAB_COMPONENT_NAME : TEXT_TAB_COMPONENT_NAME;
	}
	
	public boolean isEditing () {
		return this.d2wContext().task().equals("edit");
	}
    
    public Object selectedTabIdentifier() {
    	int id = currentTab().hashCode();
    	return new Integer(id < 0 ? -id : id);
    }
}
