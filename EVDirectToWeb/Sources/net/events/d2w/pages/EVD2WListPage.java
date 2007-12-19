package net.events.d2w.pages;

import net.events.d2w.extensions.EVD2WApplication;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.NextPageDelegate;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EODataSource;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;

import er.directtoweb.ERD2WListPageTemplate;
import er.extensions.ERXGenericRecord;

public class EVD2WListPage extends ERD2WListPageTemplate {
	
	/**
	 * used for restricting fetch specs
	 */
	private boolean isQualified = false;
	
	/**
	 * Logging support
	 */
	private Logger log = Logger.getLogger (EVD2WListPage.class);
	
	/**
	 * Id for the container with the list, created as needed
	 */
	private String updateContainerId;
	
	public EVD2WListPage(WOContext wocontext) {
		super(wocontext);
	}
	
	public NextPageDelegate nextPageDelegate () {
		if (_nextPageDelegate == null && this.d2wContext().valueForKey("nextPageDelegate") != null) {
			try {
				Class delegateClass = Class.forName((String) this.d2wContext().valueForKey("nextPageDelegate"));
				if (delegateClass != null) {
					super.setNextPageDelegate((NextPageDelegate) delegateClass.newInstance());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return super.nextPageDelegate();
	}

	/**
	 * Creates a new object for the displayed entity, switches to its edit page
	 * 
	 * @return edit page for new object of the currently displayed entity
	 */
	public WOComponent createNewObject () {
		if (log.isDebugEnabled()) log.debug("Creating object: " + this.entityName());
		
		EditPageInterface epi = D2W.factory().editPageForNewObjectWithEntityNamed(entity().name(), this.session());
		epi.setNextPage(this.context().page());
		
		try {
			if (this.context().page() != null && this.context().page().valueForKey("object") != null) {
				ERXGenericRecord nextObject = (ERXGenericRecord) ((WOComponent) epi).valueForKey ("object");
				ERXGenericRecord thisObject = (ERXGenericRecord) this.context().page().valueForKey("object");
				
				String relationshipName = (String)this.d2wContext().valueForKey("reverseRelationshipKey");
				nextObject.addObjectToBothSidesOfRelationshipWithKey(
						thisObject.localInstanceIn(nextObject.editingContext()), 
						relationshipName);
			}
		}
		catch (Exception e) {
		}

		
		return (WOComponent) epi;
	}
	
	/**
	 * use the key "restrictingObject" in your rules to set a keypath to your restricting object
	 * use the key "restrictingObjectKey" to determine how the object is used in a qualifier
	 * <br><br>
	 * example:
	 * <br><br>
	 * pageConfiguration = "ListObjects" ==> restrictingObject = "session.currentUser.haircolor"
	 * <br><br>
	 * and
	 * <br><br>
	 * pageConfiguration = "ListObjects" ==> restrictingObjectKey = "haircolor" 
	 * <br><br>
	 * if the object has an attribute "haircolor"
	 */
	public void appendToResponse(WOResponse r, WOContext c) {
		
		EOEnterpriseObject restrictingObject = (EOEnterpriseObject) this.valueForKeyPath((String) d2wContext().valueForKey("restrictingObject"));

		boolean applyQualifier = this.parent() != null || !this.isQualified;
		if (applyQualifier && restrictingObject != null) {
			EODataSource ds = super.dataSource();
			if (ds != null && (ds instanceof EODatabaseDataSource)) {
				// get the original qualifier
				EOFetchSpecification fs = ((EODatabaseDataSource) ds).fetchSpecification(); 
				EOQualifier q = fs.qualifier();
				
				// add a qualifier
				EOQualifier kq = EOQualifier.qualifierWithQualifierFormat(d2wContext().valueForKey("restrictingObjectKey") + " = %@", new NSArray (restrictingObject));
				
				// if original qualifier == null, add only our qualifier
				if (q != null) {
					EOAndQualifier aq = new EOAndQualifier(new NSArray (new Object[] {q, kq}));
					fs.setQualifier(aq);
				}
				else {
					fs.setQualifier(kq);
				}

				isQualified = true;
			}
		}
		super.appendToResponse(r, c);
	}

	public String updateContainerId () {
		if (this.updateContainerId == null) {
			this.updateContainerId = (String) this.d2wContext().valueForKey("updateContainerId");
			if (updateContainerId == null) {
				this.updateContainerId = "UDC_" + this.d2wContext().entity().name() + "_" + Math.abs(((EVD2WApplication) this.application()).randomInteger());
			}
		}
		return this.updateContainerId;
	}
}
