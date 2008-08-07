// $LastChangedRevision: 4733 $ DO NOT EDIT.  Make changes to EVMailQueue.java instead.
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
public abstract class _EVMailQueue extends  ERXGenericRecord {
	public static final String ENTITY_NAME = "EVMailQueue";

	// Attributes
	public static final String CREATION_TIME_KEY = "creationTime";
	public static final ERXKey<NSTimestamp> CREATION_TIME = new ERXKey<NSTimestamp>(CREATION_TIME_KEY);
	public static final String QUEUE_NAME_KEY = "queueName";
	public static final ERXKey<String> QUEUE_NAME = new ERXKey<String>(QUEUE_NAME_KEY);
	public static final String SCHEDULED_TIME_KEY = "scheduledTime";
	public static final ERXKey<NSTimestamp> SCHEDULED_TIME = new ERXKey<NSTimestamp>(SCHEDULED_TIME_KEY);

	// Relationships

  private static Logger LOG = Logger.getLogger(_EVMailQueue.class);

  public EVMailQueue localInstanceIn(EOEditingContext editingContext) {
    EVMailQueue localInstance = (EVMailQueue)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public NSTimestamp creationTime() {
    return (NSTimestamp) storedValueForKey("creationTime");
  }

  public void setCreationTime(NSTimestamp value) {
    if (_EVMailQueue.LOG.isDebugEnabled()) {
    	_EVMailQueue.LOG.debug( "updating creationTime from " + creationTime() + " to " + value);
    }
    takeStoredValueForKey(value, "creationTime");
  }

  public String queueName() {
    return (String) storedValueForKey("queueName");
  }

  public void setQueueName(String value) {
    if (_EVMailQueue.LOG.isDebugEnabled()) {
    	_EVMailQueue.LOG.debug( "updating queueName from " + queueName() + " to " + value);
    }
    takeStoredValueForKey(value, "queueName");
  }

  public NSTimestamp scheduledTime() {
    return (NSTimestamp) storedValueForKey("scheduledTime");
  }

  public void setScheduledTime(NSTimestamp value) {
    if (_EVMailQueue.LOG.isDebugEnabled()) {
    	_EVMailQueue.LOG.debug( "updating scheduledTime from " + scheduledTime() + " to " + value);
    }
    takeStoredValueForKey(value, "scheduledTime");
  }


  public static EVMailQueue createEVMailQueue(EOEditingContext editingContext, NSTimestamp creationTime
, String queueName
, NSTimestamp scheduledTime
) {
    EVMailQueue eo = (EVMailQueue) EOUtilities.createAndInsertInstance(editingContext, _EVMailQueue.ENTITY_NAME);    
		eo.setCreationTime(creationTime);
		eo.setQueueName(queueName);
		eo.setScheduledTime(scheduledTime);
    return eo;
  }

  public static NSArray<EVMailQueue> fetchAllEVMailQueues(EOEditingContext editingContext) {
    return _EVMailQueue.fetchAllEVMailQueues(editingContext, null);
  }

  public static NSArray<EVMailQueue> fetchAllEVMailQueues(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _EVMailQueue.fetchEVMailQueues(editingContext, null, sortOrderings);
  }

  public static NSArray<EVMailQueue> fetchEVMailQueues(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EVMailQueue.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<EVMailQueue> eoObjects = (NSArray<EVMailQueue>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EVMailQueue fetchEVMailQueue(EOEditingContext editingContext, String keyName, Object value) {
    return _EVMailQueue.fetchEVMailQueue(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EVMailQueue fetchEVMailQueue(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<EVMailQueue> eoObjects = _EVMailQueue.fetchEVMailQueues(editingContext, qualifier, null);
    EVMailQueue eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EVMailQueue)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one EVMailQueue that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EVMailQueue fetchRequiredEVMailQueue(EOEditingContext editingContext, String keyName, Object value) {
    return _EVMailQueue.fetchRequiredEVMailQueue(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EVMailQueue fetchRequiredEVMailQueue(EOEditingContext editingContext, EOQualifier qualifier) {
    EVMailQueue eoObject = _EVMailQueue.fetchEVMailQueue(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no EVMailQueue that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EVMailQueue localInstanceIn(EOEditingContext editingContext, EVMailQueue eo) {
    EVMailQueue localInstance = (eo == null) ? null : (EVMailQueue)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
