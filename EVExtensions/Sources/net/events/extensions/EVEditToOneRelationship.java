package net.events.extensions;


import net.events.page.EVPageLevelComponent;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EORelationship;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.foundation.NSArray;

import er.extensions.ERXGenericRecord;
import er.extensions.ERXLocalizer;
import er.extensions.ERXProperties;
import er.extensions.ERXStatelessComponent;
import er.extensions.ERXUtilities;

/**
 * This component can be used to edit / inspect to one relationships. It uses an ERXToOneRelationship for editing
 * and a simple WOString in combination with a WOHyperlink for displaying the content on the relationship and switching
 * to an appropriate inspect page.
 * <br><br>
 * You can provide your own array of objects to use, the objects are automatically transferred to the correct 
 * editing context. 
 * <br><br>
 * <b>Bindings:</b>
 * <ul>
 * 	<li><b>destinationDisplayKey</b> - the key used at the destination object to retrieve the attribute that is displayed in the choices</li>
 * 	<li><b>destinationSortKey</b> - sort the choices using this key</li>
 * 	<li><b>editable</b> - true for editing mode, false for display mode</li>
 * 	<li><b>inspectable</b> - true when a link should be shown when in display mode; you have to define a component name for inspecting the destination 
 * 		entity, do this in the Properties file like this: <code>net.events.pageForObjectDetail.EntityName = InspectEntityName</code></li>
 * 	<li><b>isMandatory</b> - to make an empty selection possible, set this to false</li>
 * 	<li><b>localizeDisplayKeys</b> - set this to true, to send the list through the localizer</li>
 * 	<li><b>maxColumns</b> - layout option for some of the layout style</li>
 * 	<li><b>noSelectionString</b> - the string to display for empty selection, this is also used for display when the relationship is empty (null)</li>
 * 	<li><b>popupName</b> - name used for popup element</li>
 * 	<li><b>relationshipKey</b> - provide the key that is used to follow the relationship from the sourceObject 
 * 		to the destination object, in short, the relationship name</li>
 * 	<li><b>restrictedChoiceList</b> - provide an array with your own objects you want to have as choices</li> 
 * 	<li><b>size</b> - layout option</li>
 * 	<li><b>sortCaseInsensitive</b> - define whether the list should be sorted case insensitive</li>
 * 	<li><b>sourceObject</b> - the source object to work on; this defines also the editing context to work on</li>
 * 	<li><b>uiStyle</b> - the style that should be used to display the selection list (e.g. "popup")<li>
 * 	<li><b>uniqueID</b> - a unique id for the tag</li>
 * </ul>
 * 
 * <b>uiStyle</b>
 * <br><br>
 * For uiStyle you can use "popup", "radio" or browser to use one of the styles for selecting a single object
 * from a list of choices.
 *  
 * 
 * @author cug
 *
 */

public class EVEditToOneRelationship extends ERXStatelessComponent {

    private Logger log = Logger.getLogger(EVEditToOneRelationship.class);

	public EVEditToOneRelationship(WOContext context) {
        super(context);
    }
    
    /**
     * Set this binding, if you want to have the choices restricted to the list you provide
     * 
     * @return NSArray of choices
     */
    public NSArray restrictedChoiceList () {
    	if (this.valueForBinding("restrictedChoiceList") != null && this.valueForBinding("restrictedChoiceList") instanceof NSArray) {
    		return (NSArray) this.valueForBinding("restrictedChoiceList");
    	}
    	else {
    		return null;
    	}
    }
    
    /**
     * Set this binding, if you want to have the choices restricted to the list you provide
     * 
     * @param array of choices
     */
    public void setRestrictedChoiceList(NSArray array) {
    	this.setValueForBinding(array, "restrictedChoiceList");
    }
    
    /**
     * The value at the end of the keypath for the relationship as string
     */
    public String value () {
    	if (this.sourceObject() != null && this.sourceObject().valueForKeyPath(this.relationshipKey() + "." + this.destinationDisplayKey()) != null) {
    		return this.valueForKeyPath("sourceObject." + this.relationshipKey() + "." + this.destinationDisplayKey()).toString();
    	}
    	else if (this.noSelectionString() != null) {
    		return (String) ERXLocalizer.currentLocalizer().localizedStringForKeyWithDefault(this.noSelectionString());
    	}
   		return null;
    }
    
    /**
     * Returns a component for inspecting the the object at the destination. Uses the PageLevelComponent method for inspecting objects
     * so lastPage, nextPage and object are set automatically.
     * 
     * @return inspect component for the related object
     */
    public WOComponent inspectObject () {
    	if (this.inspectComponentName() != null && this.inspectable()) {
    		WOComponent page = this.context().page();
    		if (log.isDebugEnabled()) log.debug ("Page: " + page);
    		if (page instanceof EVPageLevelComponent) {
    			EVPageLevelComponent pl = (EVPageLevelComponent) page;
    			return pl.inspectObject((ERXGenericRecord) this.sourceObject().valueForKeyPath(this.relationshipKey()), this.inspectComponentName());
    		}
    	}
    	
    	return null;
    }
    
    /**
     * This returns a value for disabling the WOHyperlink when one of the following conditions is true:
     * <br><br>
     * - we have no known inspect component for the destination object
     * - the relationship is empty (null)
     * - the binding "inspectable" is set to false
     * - or our source object is the same object as the destination object (points to self, may happen when inspecting your own user object)
     * 
     * @return true when the hyperlink should be disabled
     */
    public boolean disabled () {
    	return this.inspectComponentName() == null || this.sourceObject().valueForKey(this.relationshipKey()) == null || !this.inspectable()
    		|| this.sourceObject().equals(this.sourceObject().valueForKey(this.relationshipKey()));
    }
    
    public boolean inspectable () {
    	return this.booleanValueForBinding("inspectable", false);
    }
    
    public void setInspectable (Boolean b) {
    	this.setValueForBinding(b, "inspectable");
    }

    /**
     * The sourceObject of the relationship
     * 
     * @return {@link EOEnterpriseObject} object
     */
    public EOEnterpriseObject sourceObject () {
    	return (EOEnterpriseObject) this.valueForBinding("sourceObject");
    }
    
    
    /**
     * Set the sourceObject
     * 
     * @param object {@link EOEnterpriseObject}
     */
    public void setSourceObject (EOEnterpriseObject object) {
    	this.setValueForBinding(object, "sourceObject");
    }
    
    /**
     * The key to resolve the relationship from the source to the destination entity
     * @return
     */
    public String relationshipKey () {
    	return (String) this.valueForBinding("relationshipKey");
    }
    
    /**
     * Set the relationship key
     * 
     * @param s
     */
    public void setRelationshipKey (String s) {
    	this.setValueForBinding(s, "relationshipKey");
    }

    /**
     * Use value for this key to display the possible objects you can select
     * 
     * @return
     */
    public String destinationDisplayKey () {
    	return this.valueForBinding("destinationDisplayKey").toString();
    }
    
    /**
     * Set the key
     * @param s
     */
    public void setDestinationDisplayKey (String s) {
    	this.setValueForBinding(s, "destinationDisplayKey");
    }
  
    /**
     * This is used by the editing components (like popup) to display the empty selection
     * and also by the string that is displayed when the component is not currently editable.
     * 
     * @return
     */
    public String noSelectionString () {
    	if (this.valueForBinding("noSelectionString") != null) {
    		return (String) this.valueForBinding("noSelectionString");
    	}
    	else {
    		return "";
    	}
    }
    
    /**
     * Set the string
     * @param s
     */
    public void setNoSelectionString (String s) {
    	this.setValueForBinding(s, "noSelectionString");
    }
    
    /**
     * Get a component name from the application properties. Set a property for each Entity you want to 
     * use with this class like this:<<br><br>
     * <code>net.events.pageForObjectDetail.EntityName = InspectEntityName</code>
     * <br><br>
     * where "EntityName" defines the entity and "EditEntityName" defines the component to inspect the defined entity.
     * 
     * @return the component name used for inspecting the entity at the other end of our relationship
     */
    public String inspectComponentName() {
    	return ERXProperties.stringForKeyWithDefault("net.events.pageForObjectDetail." + this.destinationEntityName(), null);
    }
    
    
    /**
     * Convenience method, so we don't have to set a binding for it. This method uses EORelationship's method
     * for getting the destination entity, and asks this entity for "name".
     * 
     * @return name of the destination entity
     */
    public String destinationEntityName () {
    	EORelationship relationship = ERXUtilities.relationshipWithObjectAndKeyPath(this.sourceObject(), this.relationshipKey());
    	return relationship.destinationEntity().name();
    }
    
    /**
     * Convenience method, so wen don't have to set a binding for it. This method uses <code>object.entityName</code> to
     * determine the entity name.
     * 
     * @return name of the source entity
     */
    public String sourceEntityName () {
    	return this.sourceObject().entityName();
    }
    

 }