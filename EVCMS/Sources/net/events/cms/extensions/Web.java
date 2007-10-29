package net.events.cms.extensions;

import java.util.*;

import net.events.appserver.*;
import net.events.cms.eo.*;
import net.events.cms.pages.*;

import com.webobjects.appserver.*;
import com.webobjects.eoaccess.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public class Web extends EVDirectAction {
	
	private AbstractPage pageForRequest;

	/**
	 * Standard constructor
	 * 
	 * @param r
	 */
	public Web(WORequest r) {
		super(r);
	}
	
	/**
	 * check whether we have a session, then determine the current site, than do all the rest ...
	 */
	public WOActionResults performActionNamed (String name) {
		try {
			this.existingSession();
			this.determineSite();
			
			return super.performActionNamed (name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
		
		// something went badly wrong
		WOResponse response = new WOResponse();
		response.setStatus(500);  // internal server error
  		return response;
	}
	
	/**
	 * Returns a page.
	 * 
	 * @return
	 */
	public WOActionResults pageAction () {
		try {
			Number pageId = this.context().request().numericFormValueForKey(EVCMSConstants.PAGE_ID_KEY, EVCMSConstants.SIMPLE_NUMBER_FORMATTER);
			Long id = new Long(pageId.intValue());
			AbstractPage pageObject = (AbstractPage) EOUtilities.objectWithPrimaryKeyValue(editingContext, AbstractPage.ENTITY_NAME, id);
			ERXThreadStorage.takeValueForKey(pageObject, EVCMSConstants.PAGE_OBJECT_KEY);
			
			EVCMSPage page = (EVCMSPage) this.pageWithName(pageObject.componentName());
			
			return page;
		}
		catch (Exception e) {
			return this.pageNotFoundAction();
		}
	}
	
	/**
	 * Blog detail page for the blog entry specified by the URL parameter "entryId" 
	 * 
	 * @return detail page for blog entry given in URL parameter
	 */
	public WOActionResults blogEntryAction () {
		
		AbstractPage page = this.pageForRequest();
		if (page != null) {
			BlogEntry entry = blogEntryForRequest();
			if (entry != null) {
				EVBlogDetailPage nextpage = (EVBlogDetailPage) this.pageWithName(EVBlogDetailPage.class.getName());
				nextpage.setBlogEntry(entry);
				nextpage.setPage(page);
				
				return nextpage;
			}
		}
		return this.pageNotFoundAction();
	}

	private BlogEntry blogEntryForRequest() {
		BlogEntry entry;
		Number entryId = this.context().request().numericFormValueForKey(EVCMSConstants.BLOG_ENTRY_ID_KEY, EVCMSConstants.SIMPLE_NUMBER_FORMATTER);
		Long id = new Long(entryId.intValue());
		entry = (BlogEntry) EOUtilities.objectWithPrimaryKeyValue(editingContext, BlogEntry.ENTITY_NAME, id);
		return entry;
	}
	
	public WOActionResults blogCatAction () {
		AbstractPage page = this.pageForRequest();
		if (page != null) {
			try {
				Number catId = this.context().request().numericFormValueForKey(EVCMSConstants.BLOG_CAT_ID_KEY, EVCMSConstants.SIMPLE_NUMBER_FORMATTER);
				Long id = new Long (catId.longValue());
				BlogEntryCategory category = (BlogEntryCategory) EOUtilities.objectWithPrimaryKeyValue(editingContext, BlogEntryCategory.ENTITY_NAME, id);
				
				// FIXME cug: not done here! 
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public WOActionResults surveyAction () {
		EVCMSSession session = (EVCMSSession) this.existingSession();
		if (session == null) session = (EVCMSSession) this.session();
		WOComponent nextpage = null;
        Number dataSetId = this.context().request().numericFormValueForKey(EVCMSConstants.DATA_SET_ID_KEY, EVCMSConstants.SIMPLE_NUMBER_FORMATTER);
        if (dataSetId != null) {
        	Long id = new Long (dataSetId.longValue());
        	try {
        		DataSetTemplate template = (DataSetTemplate) ERXEOControlUtilities.objectWithPrimaryKeyValue(this.editingContext, DataSetTemplate.ENTITY_NAME, id, null);
        		session.setRequestedObject(template);
        		
        		if (template != null && template.adminOnly() != null && template.adminOnly().booleanValue()) {
        			return this.messagePageWithMessageAndStyle("The survey you requested could not be found!", "d2w_rns_message");
        		}
        		
        		if (template != null && template.loginRequired() && session.currentUser() == null) {
        			nextpage = this.pageWithName(EVSiteLoginPage.class.getName());
        			
        			if (template instanceof MedicalDataSetTemplate) {
        				
        				((EVSiteLoginPage) nextpage).setObjectToCheckForUser(((MedicalDataSetTemplate) template).clinicalTrial());
        				((EVSiteLoginPage) nextpage).setKeyToCheckForUser(ClinicalTrial.PARTICIPANTS);
        			}
        		}
        		else if (template != null && !template.loginRequired()) {
        			nextpage = (EVDataSetPage) this.pageWithName(EVDataSetPage.class.getName());
        		}
        	}
        	catch (Exception e) {
        		e.printStackTrace();
        	}
        }

        if (nextpage == null) {
        	return this.messagePageWithMessageAndStyle("The survey you requested could not be found!", "d2w_rns_message");
        }
        return nextpage;
	}
	
	public WOActionResults addBlogCommentAction () {
		WOComponent nextpage = null;
		nextpage = this.pageWithName(EVBlogCommentPage.class.getName());

		return nextpage;
	}
	
	/**
	 * Finds the page for the current request, caches it for multiple calls
	 * 
	 * @return
	 */
	private AbstractPage pageForRequest () {
		if (this.pageForRequest == null) {
			try {
				Number pageId = this.context().request().numericFormValueForKey(EVCMSConstants.PAGE_ID_KEY, EVCMSConstants.SIMPLE_NUMBER_FORMATTER);
				Long id = new Long(pageId.intValue());
				AbstractPage pageObject = (AbstractPage) EOUtilities.objectWithPrimaryKeyValue(editingContext, AbstractPage.ENTITY_NAME, id);
				ERXThreadStorage.takeValueForKey(pageObject, EVCMSConstants.PAGE_OBJECT_KEY);
				
				this.pageForRequest = pageObject;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return this.pageForRequest;
	}

	private void determineSite() {
		EVCMSSession session = (EVCMSSession) this.existingSession();
		
		// first try check whether we got something in the properties (like
		// LaunchConfig, or instance properties
		
		String siteId = ERXProperties.stringForKey("net.events.siteId"); 
		if (siteId != null && !siteId.equals("")) {
			log.debug("Trying with the primary key given in the properties/arguments");

			try {
				Site site = (Site) ERXEOControlUtilities.objectWithPrimaryKeyValue(editingContext, Site.ENTITY_NAME, new Integer(siteId), null);
				setSiteForRequest(session, site);
				return;
			}
			catch (Exception e) {}
		}
		
		// Do we have a page object for this request? If yes, get the site from the page!
		
		AbstractPage page = this.pageForRequest();
		if (page != null) {
			if (page.site() != null) {
				this.setSiteForRequest(session, page.site());
				return;
			}
		}
		
		// Nothing found yet? Okay, try the host name and see whether we find a site for it.
		String domain = "";
		if (request().headerForKey("host") != null) {
			domain = "*" + request().headerForKey("host") + "*";
		}
		else if (request().headerForKey("http_host") != null) {
			domain = "*" + request().headerForKey("http_host") + "*";
		}
		
		try {
			Site site = (Site) EOUtilities.objectWithQualifierFormat(editingContext, Site.ENTITY_NAME, "domains.url like %@", new NSArray (domain));
			setSiteForRequest(session, site);
			return;
		}
		catch (Exception e) {
			return;
		}

	}

	/**
	 * Puts the site into the session (if it exists) and the ERXThreadStorage
	 * 
	 * @param session
	 * @param site
	 */
	private void setSiteForRequest(EVCMSSession session, Site site) {
		if (session != null) {
			session.setCurrentSite(site);
		}
		ERXThreadStorage.takeValueForKey(site, EVCMSConstants.CURRENT_SITE);
		return;
	}
	
	
	/**
	 * Should get called, when the page could not be found
	 * 
	 * @return
	 */
	public WOActionResults pageNotFoundAction () {
		return this.messagePageWithMessageAndStyle("We are sorry for the inconvenience<br>but your request produced an error!<br>" +
				"<br>The page you requested could not be found!", "d2w_rns_message");
	}
	
	/**
	 * Returns a generic error page with the message and the message style set
	 * 
	 * @param message
	 * @param style
	 * @return
	 */
	private WOActionResults messagePageWithMessageAndStyle (String message, String style) {
		if (style == null) {
			style = "d2w_rns_message";
		}
		EVGenericMessagePage errorPage = (EVGenericMessagePage) this.pageWithName(EVGenericMessagePage.class.getName());
		
		this.determineSite();
		
		errorPage.setMessage(message);
		errorPage.setCssClassForMessage(style);
		
		return errorPage;
	}
}
