// $LastChangedRevision: 4733 $ DO NOT EDIT.  Make changes to EVMailTemplate.java instead.
package net.events.mail.eo;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

import er.extensions.eof.*;
import er.extensions.foundation.*;

@SuppressWarnings("all")
public abstract class _EVMailTemplate extends  ERXGenericRecord {
	public static final String ENTITY_NAME = "EVMailTemplate";

	// Attributes
	public static final String SUBJECT_KEY = "subject";
	public static final ERXKey<String> SUBJECT = new ERXKey<String>(SUBJECT_KEY);
	public static final String TEMPLATE_KEY = "template";
	public static final ERXKey<String> TEMPLATE = new ERXKey<String>(TEMPLATE_KEY);
	public static final String TEMPLATE_DESCRIPTION_KEY = "templateDescription";
	public static final ERXKey<String> TEMPLATE_DESCRIPTION = new ERXKey<String>(TEMPLATE_DESCRIPTION_KEY);
	public static final String TEMPLATE_NAME_KEY = "templateName";
	public static final ERXKey<String> TEMPLATE_NAME = new ERXKey<String>(TEMPLATE_NAME_KEY);

	// Relationships

  private static Logger LOG = Logger.getLogger(_EVMailTemplate.class);

  public EVMailTemplate localInstanceIn(EOEditingContext editingContext) {
    EVMailTemplate localInstance = (EVMailTemplate)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String subject() {
    return (String) storedValueForKey("subject");
  }

  public void setSubject(String value) {
    if (_EVMailTemplate.LOG.isDebugEnabled()) {
    	_EVMailTemplate.LOG.debug( "updating subject from " + subject() + " to " + value);
    }
    takeStoredValueForKey(value, "subject");
  }

  public String template() {
    return (String) storedValueForKey("template");
  }

  public void setTemplate(String value) {
    if (_EVMailTemplate.LOG.isDebugEnabled()) {
    	_EVMailTemplate.LOG.debug( "updating template from " + template() + " to " + value);
    }
    takeStoredValueForKey(value, "template");
  }

  public String templateDescription() {
    return (String) storedValueForKey("templateDescription");
  }

  public void setTemplateDescription(String value) {
    if (_EVMailTemplate.LOG.isDebugEnabled()) {
    	_EVMailTemplate.LOG.debug( "updating templateDescription from " + templateDescription() + " to " + value);
    }
    takeStoredValueForKey(value, "templateDescription");
  }

  public String templateName() {
    return (String) storedValueForKey("templateName");
  }

  public void setTemplateName(String value) {
    if (_EVMailTemplate.LOG.isDebugEnabled()) {
    	_EVMailTemplate.LOG.debug( "updating templateName from " + templateName() + " to " + value);
    }
    takeStoredValueForKey(value, "templateName");
  }


  public static EVMailTemplate createEVMailTemplate(EOEditingContext editingContext, String subject
, String template
, String templateName
) {
    EVMailTemplate eo = (EVMailTemplate) EOUtilities.createAndInsertInstance(editingContext, _EVMailTemplate.ENTITY_NAME);    
		eo.setSubject(subject);
		eo.setTemplate(template);
		eo.setTemplateName(templateName);
    return eo;
  }

  public static NSArray<EVMailTemplate> fetchAllEVMailTemplates(EOEditingContext editingContext) {
    return _EVMailTemplate.fetchAllEVMailTemplates(editingContext, null);
  }

  public static NSArray<EVMailTemplate> fetchAllEVMailTemplates(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _EVMailTemplate.fetchEVMailTemplates(editingContext, null, sortOrderings);
  }

  public static NSArray<EVMailTemplate> fetchEVMailTemplates(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EVMailTemplate.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<EVMailTemplate> eoObjects = (NSArray<EVMailTemplate>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EVMailTemplate fetchEVMailTemplate(EOEditingContext editingContext, String keyName, Object value) {
    return _EVMailTemplate.fetchEVMailTemplate(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EVMailTemplate fetchEVMailTemplate(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<EVMailTemplate> eoObjects = _EVMailTemplate.fetchEVMailTemplates(editingContext, qualifier, null);
    EVMailTemplate eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EVMailTemplate)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one EVMailTemplate that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EVMailTemplate fetchRequiredEVMailTemplate(EOEditingContext editingContext, String keyName, Object value) {
    return _EVMailTemplate.fetchRequiredEVMailTemplate(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EVMailTemplate fetchRequiredEVMailTemplate(EOEditingContext editingContext, EOQualifier qualifier) {
    EVMailTemplate eoObject = _EVMailTemplate.fetchEVMailTemplate(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no EVMailTemplate that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EVMailTemplate localInstanceIn(EOEditingContext editingContext, EVMailTemplate eo) {
    EVMailTemplate localInstance = (eo == null) ? null : (EVMailTemplate)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
