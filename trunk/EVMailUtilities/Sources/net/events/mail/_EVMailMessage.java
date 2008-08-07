// $LastChangedRevision: 4733 $ DO NOT EDIT.  Make changes to EVMailMessage.java instead.
package net.events.mail;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

import er.extensions.eof.*;
import er.extensions.foundation.*;

@SuppressWarnings("all")
public abstract class _EVMailMessage extends  ERXGenericRecord {
	public static final String ENTITY_NAME = "EVMailMessage";

	// Attributes
	public static final String ARCHIVE_SENT_MAIL_KEY = "archiveSentMail";
	public static final ERXKey<Boolean> ARCHIVE_SENT_MAIL = new ERXKey<Boolean>(ARCHIVE_SENT_MAIL_KEY);
	public static final String BCC_KEY = "bcc";
	public static final ERXKey<String> BCC = new ERXKey<String>(BCC_KEY);
	public static final String CC_KEY = "cc";
	public static final ERXKey<String> CC = new ERXKey<String>(CC_KEY);
	public static final String CREATION_TIME_KEY = "creationTime";
	public static final ERXKey<NSTimestamp> CREATION_TIME = new ERXKey<NSTimestamp>(CREATION_TIME_KEY);
	public static final String FAILURE_REASON_KEY = "failureReason";
	public static final ERXKey<String> FAILURE_REASON = new ERXKey<String>(FAILURE_REASON_KEY);
	public static final String FROM_KEY = "from";
	public static final ERXKey<String> FROM = new ERXKey<String>(FROM_KEY);
	public static final String HTML_BODY_KEY = "htmlBody";
	public static final ERXKey<String> HTML_BODY = new ERXKey<String>(HTML_BODY_KEY);
	public static final String IS_READ_KEY = "isRead";
	public static final ERXKey<Boolean> IS_READ = new ERXKey<Boolean>(IS_READ_KEY);
	public static final String PLAIN_TEXT_BODY_KEY = "plainTextBody";
	public static final ERXKey<String> PLAIN_TEXT_BODY = new ERXKey<String>(PLAIN_TEXT_BODY_KEY);
	public static final String REPLY_TO_KEY = "replyTo";
	public static final ERXKey<String> REPLY_TO = new ERXKey<String>(REPLY_TO_KEY);
	public static final String SENT_TIME_KEY = "sentTime";
	public static final ERXKey<NSTimestamp> SENT_TIME = new ERXKey<NSTimestamp>(SENT_TIME_KEY);
	public static final String STATUS_KEY = "status";
	public static final ERXKey<String> STATUS = new ERXKey<String>(STATUS_KEY);
	public static final String SUBJECT_KEY = "subject";
	public static final ERXKey<String> SUBJECT = new ERXKey<String>(SUBJECT_KEY);
	public static final String TO_KEY = "to";
	public static final ERXKey<String> TO = new ERXKey<String>(TO_KEY);
	public static final String X_MAILER_KEY = "xMailer";
	public static final ERXKey<String> X_MAILER = new ERXKey<String>(X_MAILER_KEY);

	// Relationships
	public static final String MAIL_QUEUE_KEY = "mailQueue";
	public static final ERXKey<net.events.mail.EVMailQueue> MAIL_QUEUE = new ERXKey<net.events.mail.EVMailQueue>(MAIL_QUEUE_KEY);

  private static Logger LOG = Logger.getLogger(_EVMailMessage.class);

  public EVMailMessage localInstanceIn(EOEditingContext editingContext) {
    EVMailMessage localInstance = (EVMailMessage)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Boolean archiveSentMail() {
    return (Boolean) storedValueForKey("archiveSentMail");
  }

  public void setArchiveSentMail(Boolean value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating archiveSentMail from " + archiveSentMail() + " to " + value);
    }
    takeStoredValueForKey(value, "archiveSentMail");
  }

  public String bcc() {
    return (String) storedValueForKey("bcc");
  }

  public void setBcc(String value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating bcc from " + bcc() + " to " + value);
    }
    takeStoredValueForKey(value, "bcc");
  }

  public String cc() {
    return (String) storedValueForKey("cc");
  }

  public void setCc(String value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating cc from " + cc() + " to " + value);
    }
    takeStoredValueForKey(value, "cc");
  }

  public NSTimestamp creationTime() {
    return (NSTimestamp) storedValueForKey("creationTime");
  }

  public void setCreationTime(NSTimestamp value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating creationTime from " + creationTime() + " to " + value);
    }
    takeStoredValueForKey(value, "creationTime");
  }

  public String failureReason() {
    return (String) storedValueForKey("failureReason");
  }

  public void setFailureReason(String value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating failureReason from " + failureReason() + " to " + value);
    }
    takeStoredValueForKey(value, "failureReason");
  }

  public String from() {
    return (String) storedValueForKey("from");
  }

  public void setFrom(String value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating from from " + from() + " to " + value);
    }
    takeStoredValueForKey(value, "from");
  }

  public String htmlBody() {
    return (String) storedValueForKey("htmlBody");
  }

  public void setHtmlBody(String value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating htmlBody from " + htmlBody() + " to " + value);
    }
    takeStoredValueForKey(value, "htmlBody");
  }

  public Boolean isRead() {
    return (Boolean) storedValueForKey("isRead");
  }

  public void setIsRead(Boolean value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating isRead from " + isRead() + " to " + value);
    }
    takeStoredValueForKey(value, "isRead");
  }

  public String plainTextBody() {
    return (String) storedValueForKey("plainTextBody");
  }

  public void setPlainTextBody(String value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating plainTextBody from " + plainTextBody() + " to " + value);
    }
    takeStoredValueForKey(value, "plainTextBody");
  }

  public String replyTo() {
    return (String) storedValueForKey("replyTo");
  }

  public void setReplyTo(String value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating replyTo from " + replyTo() + " to " + value);
    }
    takeStoredValueForKey(value, "replyTo");
  }

  public NSTimestamp sentTime() {
    return (NSTimestamp) storedValueForKey("sentTime");
  }

  public void setSentTime(NSTimestamp value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating sentTime from " + sentTime() + " to " + value);
    }
    takeStoredValueForKey(value, "sentTime");
  }

  public String status() {
    return (String) storedValueForKey("status");
  }

  public void setStatus(String value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating status from " + status() + " to " + value);
    }
    takeStoredValueForKey(value, "status");
  }

  public String subject() {
    return (String) storedValueForKey("subject");
  }

  public void setSubject(String value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating subject from " + subject() + " to " + value);
    }
    takeStoredValueForKey(value, "subject");
  }

  public String to() {
    return (String) storedValueForKey("to");
  }

  public void setTo(String value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating to from " + to() + " to " + value);
    }
    takeStoredValueForKey(value, "to");
  }

  public String xMailer() {
    return (String) storedValueForKey("xMailer");
  }

  public void setXMailer(String value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
    	_EVMailMessage.LOG.debug( "updating xMailer from " + xMailer() + " to " + value);
    }
    takeStoredValueForKey(value, "xMailer");
  }

  public net.events.mail.EVMailQueue mailQueue() {
    return (net.events.mail.EVMailQueue)storedValueForKey("mailQueue");
  }
  
  public void setMailQueue(net.events.mail.EVMailQueue value) {
    takeStoredValueForKey(value, "mailQueue");
  }

  public void setMailQueueRelationship(net.events.mail.EVMailQueue value) {
    if (_EVMailMessage.LOG.isDebugEnabled()) {
      _EVMailMessage.LOG.debug("updating mailQueue from " + mailQueue() + " to " + value);
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setMailQueue(value);
    }
    else if (value == null) {
    	net.events.mail.EVMailQueue oldValue = mailQueue();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "mailQueue");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "mailQueue");
    }
  }
  

  public static EVMailMessage createEVMailMessage(EOEditingContext editingContext, Boolean archiveSentMail
, NSTimestamp creationTime
, Boolean isRead
, String status
, String subject
, String to
) {
    EVMailMessage eo = (EVMailMessage) EOUtilities.createAndInsertInstance(editingContext, _EVMailMessage.ENTITY_NAME);    
		eo.setArchiveSentMail(archiveSentMail);
		eo.setCreationTime(creationTime);
		eo.setIsRead(isRead);
		eo.setStatus(status);
		eo.setSubject(subject);
		eo.setTo(to);
    return eo;
  }

  public static NSArray<EVMailMessage> fetchAllEVMailMessages(EOEditingContext editingContext) {
    return _EVMailMessage.fetchAllEVMailMessages(editingContext, null);
  }

  public static NSArray<EVMailMessage> fetchAllEVMailMessages(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _EVMailMessage.fetchEVMailMessages(editingContext, null, sortOrderings);
  }

  public static NSArray<EVMailMessage> fetchEVMailMessages(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EVMailMessage.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<EVMailMessage> eoObjects = (NSArray<EVMailMessage>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EVMailMessage fetchEVMailMessage(EOEditingContext editingContext, String keyName, Object value) {
    return _EVMailMessage.fetchEVMailMessage(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EVMailMessage fetchEVMailMessage(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<EVMailMessage> eoObjects = _EVMailMessage.fetchEVMailMessages(editingContext, qualifier, null);
    EVMailMessage eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EVMailMessage)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one EVMailMessage that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EVMailMessage fetchRequiredEVMailMessage(EOEditingContext editingContext, String keyName, Object value) {
    return _EVMailMessage.fetchRequiredEVMailMessage(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EVMailMessage fetchRequiredEVMailMessage(EOEditingContext editingContext, EOQualifier qualifier) {
    EVMailMessage eoObject = _EVMailMessage.fetchEVMailMessage(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no EVMailMessage that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EVMailMessage localInstanceIn(EOEditingContext editingContext, EVMailMessage eo) {
    EVMailMessage localInstance = (eo == null) ? null : (EVMailMessage)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
