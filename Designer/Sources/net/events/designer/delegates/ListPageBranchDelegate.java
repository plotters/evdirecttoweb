package net.events.designer.delegates;

import com.webobjects.appserver.*;
import com.webobjects.directtoweb.*;
import com.webobjects.foundation.*;

import er.extensions.*;

/**
 * Functions for list pages.
 * 
 * @author cug
 *
 */
public class ListPageBranchDelegate extends BranchDelegate {
	
	/**
	 * The branch choices.
	 */
	public NSArray<Object> branchChoicesForContext(D2WContext context) {
		return super.branchChoicesForContext(context);
	}

	/**
	 * Gets the entity name for the current context, creates a new edit page for
	 * a new object for it, and returns that page.
	 * 
	 * Use this method with the EVD2WListPage and the action bar in there.
	 * 
	 * @param sender
	 * @return
	 */
	public WOComponent createNew (WOComponent sender) {
		String entityName = (String) sender.valueForKeyPath("branch.entityName");
		if (entityName == null) {
			entityName = (String) sender.valueForKeyPath("d2wContext.entity.name");
		}

		EditPageInterface epi = D2W.factory().editPageForNewObjectWithEntityNamed(entityName, sender.session());
		epi.setNextPage(sender.context().page());
		
		try {
			if (sender.context().page() != null && sender.context().page().valueForKey("object") != null) {
				ERXGenericRecord nextObject = (ERXGenericRecord) ((WOComponent) epi).valueForKey ("object");
				ERXGenericRecord thisObject = (ERXGenericRecord) sender.context().page().valueForKey("object");
			
				String relationshipName = (String)d2wContext(sender).valueForKey("reverseRelationshipKey");
				nextObject.addObjectToBothSidesOfRelationshipWithKey(
						thisObject.localInstanceIn(nextObject.editingContext()), 
						relationshipName);
			}
		}
		catch (Exception e) {
		}

		
		return (WOComponent) epi;	}
}
