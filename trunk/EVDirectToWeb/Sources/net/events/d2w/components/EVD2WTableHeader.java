package net.events.d2w.components;
// Generated by the WOLips Templateengine Plug-in at Mar 4, 2007 2:44:05 PM

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EODataSource;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSNotificationCenter;

import er.ajax.AjaxUtils;
import er.directtoweb.ERD2WContextDictionary;
import er.directtoweb.ERD2WPropertyName;
import er.directtoweb.ERDirectToWeb;
import er.extensions.ERXSortOrder;
import er.extensions.ERXWOContext;

public class EVD2WTableHeader extends ERXSortOrder {

    public static final Logger log = Logger.getLogger(ERD2WPropertyName.class);

    protected String _displayNameForProperty;
    protected NSDictionary _contextDictionary;
    public String currentKey;

	public EVD2WTableHeader(WOContext context) {
        super(context);
    }
    
    public String customImageSrcForCurrentState() {
        String src = null;
        switch(currentState()) {
            case Unsorted:
                src = null; 
                break;
            case SortedAscending:
                src = "/events/d2w/img/ArrowUp.gif"; 
                break;
            case SortedDescending:
                src =  "/events/d2w/img/ArrowDown.gif";
                break;
        }
        return src;
    }
    
    public boolean d2wComponentNameDebuggingEnabled() {
        return ERDirectToWeb.d2wComponentNameDebuggingEnabled(session());
    }

    public boolean d2wDebuggingEnabled() {
        return ERDirectToWeb.d2wDebuggingEnabled(session());
    }

    public Object currentValue() {
        return contextDictionaryForPropertyKey().valueForKey(currentKey);
    }

    public NSDictionary contextDictionary() {
        if(_contextDictionary == null) {
            String key = "contextDictionary." + d2wContext().dynamicPage();
            _contextDictionary = (NSDictionary)ERXWOContext.contextDictionary().objectForKey(key);
            if(_contextDictionary == null) {
            	ERD2WContextDictionary dict = new ERD2WContextDictionary(d2wContext().dynamicPage(), null, null);
            	_contextDictionary = dict.dictionary();
            	ERXWOContext.contextDictionary().setObjectForKey(_contextDictionary, key);
            }
        }
        return _contextDictionary;
    }
    
    public NSDictionary contextDictionaryForPropertyKey() {
        Object o = contextDictionary().valueForKeyPath("componentLevelKeys." + propertyKey());
        if(o instanceof NSDictionary) {
            return (NSDictionary)o;
        }
        return NSDictionary.EmptyDictionary;
    }
    
    protected D2WContext d2wContext () {
    	return (D2WContext) this.valueForBinding("d2wContext");
    }
    
    protected String propertyKey () {
    	return this.d2wContext().propertyKey();
    }
    
    // FIXME: Should post a notification even if d2wContext isn't bound.
    public WOComponent toggleClicked() {
        super.toggleClicked();
        if (log.isDebugEnabled()) log.debug("toggleClicked "+valueForBinding("d2wContext"));
        if (valueForBinding("d2wContext") != null) {
            NSNotificationCenter.defaultCenter().postNotification(SortOrderingChanged,
                                                                  displayGroup().sortOrderings(),
                                                                  new NSDictionary(valueForBinding("d2wContext"), "d2wContext"));
        }
        if (AjaxUtils.isAjaxRequest(this.context().request())) {
    		EOEnterpriseObject restrictingObject = (EOEnterpriseObject) this.valueForKeyPath((String) d2wContext().valueForKey("restrictingObject"));
    		
    		if (restrictingObject != null) {
    			EODataSource ds = displayGroup().dataSource();
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
    			}
    		}
    		displayGroup().qualifyDataSource();
        }
        return null;
    }
}