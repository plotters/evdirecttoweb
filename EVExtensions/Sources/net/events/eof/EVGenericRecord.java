package net.events.eof;


import java.util.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public abstract class EVGenericRecord extends ERXGenericRecord {
	
	/**
	 * Integer 1
	 */
	public static final Integer YES = new Integer(1);
	
	/**
	 * Integer 0
	 */
	public static final Integer NO = new Integer(0);
	
	/**
	 * Used for GenericLists
	 */
	public static final String kATTRIBUTE_KEYS_FOR_LIST = "attributeKeysForList";
	
	/**
	 * Used for GenericLists
	 */
	public static final String kNOT_SORTABLE_ATTRIBUTES = "notSortableAttributes";
	
	public static final String ATTRIBUTE_NAME_FOR_MODIFICATION_PK_NAME = "primaryKeyName";
	public static final String ATTRIBUTE_NAME_FOR_MODIFICATION_PK_VALUE = "primaryKeyValue";
	
	/**
	 * The attribute name for storing the modifications array
	 */
	private static final String MODIFICATIONSARRAY = "modificationsArray";

	
	/**
	 * Stand constructor
	 */
	public EVGenericRecord () {
		super();
	}
	
	/**
	 * Checks the Properties, whether we get an array of attributes, we should NOT include when
	 * checking for changes 
	 *  
	 * @return array of attribute names
	 */
	public NSArray attributesNotToTrack () {
		return ERXProperties.arrayForKeyWithDefault(this.getClass().getName() + ".AttributesNotToTrack", new NSArray());
	}

	/**
	 * Checks the Properties, which entities are to track and which are not
	 * @return
	 */
	public NSArray entitiesNotToTrack () {
		return ERXProperties.arrayForKeyWithDefault("net.events.eo.EntitiesNotToTrack", new NSArray());
	}
	
	/**
	 * This methods runs over the changes made to this object compared to its committed snapshot.
	 * It returns an {@link ERXMutableDictionary} which can be placed in the database. 
	 * <br><br>
	 * Relationships are resolved by using <code>userPresentableDescription</code> and added 
	 * to the dict just as strings.
	 * <br><br>
	 * ToMany relationships aren't handled yet - we have no testing object
	 * <br><br>
	 * Dictionaryformat: {"attributeKey" = {"currentValue" = String; "lastValue" = String;};}
	 *  
	 * @return a dictionary with changes in readable format
	 */
	protected ERXMutableArray collectedChangesFromSnapshot () {

		// if we don't validate our current content, it may be that we insert values
		// into the "history" that don't go through validation, therefor aren't saved,
		// but if the user corrects it, the editing context is saved - we take care
		// that we don't insert anything, if we can't save this object
		
		this.validateForSave();
		
		// we use ERXMutableDictionary to serialize a dict in the database
		NSDictionary changesDict = this.changesFromCommittedSnapshot();
		ERXMutableArray mutableArray = new ERXMutableArray();
		
		Enumeration enumeration = changesDict.keyEnumerator();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			
			
			if (this.attributeKeys().containsObject(key) && !this.attributesNotToTrack().containsObject(key)) {
				if (log.isDebugEnabled()) log.debug ("Found an attribute key: " + key);
				
				Object origValue = this.committedSnapshotValueForKey(key);
				Object newValue = changesDict.valueForKey(key);
				
				if (!newValue.equals(NSKeyValueCoding.NullValue) && !origValue.equals(NSKeyValueCoding.NullValue)) {
					// both values are not null, so we just write an entry
					
					mutableArray.addObject(new NSDictionary (
									new Object[] {key, origValue, newValue}, new Object[] {"attributeName", "lastValue", "currentValue"}));
					
				}
				else if (newValue.equals(NSKeyValueCoding.NullValue)) {
					if (!(origValue instanceof String && origValue.equals(""))) {
						// if the old value was an empty string, we don't write a change for "" --> null
						mutableArray.addObject(new NSDictionary (
									new Object[] {key, origValue, NSKeyValueCoding.NullValue}, new Object[] {"attributeName", "lastValue", "currentValue"}));
					}
					
				}
				else {
					mutableArray.addObject(new NSDictionary (
									new Object[] {key ,NSKeyValueCoding.NullValue, newValue}, new Object[] {"attributeName", "lastValue", "currentValue"}));
				}
			}
			else if (this.toOneRelationshipKeys().containsObject(key)  && !this.attributesNotToTrack().containsObject(key)) {
				if (log.isDebugEnabled()) log.debug ("Found an toOneRelationship key: " + key);
				
				Object origValue = this.committedSnapshotValueForKey(key);
				Object newValue = changesDict.objectForKey(key);
				
				if (!newValue.equals(NSKeyValueCoding.NullValue) && !origValue.equals(NSKeyValueCoding.NullValue)) {
					mutableArray.addObject(
									new NSDictionary (new Object[] {
													key,
													((EOGenericRecord)origValue).valueForKey("userPresentableDescription"),
													((EOGenericRecord) newValue).valueForKey("userPresentableDescription") },
													new Object[] {"attributeName", "lastValue", "currentValue"}));
				}
				else if (!origValue.equals(NSKeyValueCoding.NullValue) && newValue.equals(NSKeyValueCoding.NullValue)) {
					mutableArray.addObject(
									new NSDictionary (new Object[] {
													key,
													((EOGenericRecord)origValue).valueForKey("userPresentableDescription"),
													NSKeyValueCoding.NullValue },
													new Object[] {"attributeName", "lastValue", "currentValue"}));

				}
				else if (origValue.equals(NSKeyValueCoding.NullValue) && !newValue.equals(NSKeyValueCoding.NullValue)){
					mutableArray.addObject(
									new NSDictionary (new Object[] {
													key,
													NSKeyValueCoding.NullValue,
													((EOGenericRecord) newValue).valueForKey("userPresentableDescription") },
													new Object[] {"attributeName", "lastValue", "currentValue"}));

				}
			}
			
			// We don't have changes on toMany relationships because there is nothing to write to the database
			// when this happens.
			
			// TODO Check, whether we can do something in "addTo..." methods.
		}
		
		if (mutableArray.count() > 0) {
			return mutableArray;
		}
		else {
			// no changes
			return null;
		}
	}
	
	/**
	 * Creates a modification object according to the given parameters. 
	 */
	public void createModificationsEntry () {
		
		// don't do anything for these entities
		if (this.entitiesNotToTrack().containsObject(this.entityName())) {
			log.fatal("Don't track " + this.entityName());
			return;
		}
			
		ERXMutableArray changes = this.collectedChangesFromSnapshot(); 
		if (changes != null && changes.count() > 0) {
			EVGenericRecord mod = (EVGenericRecord) ERXEOControlUtilities.createAndInsertObject(this.editingContext(), this.modificationEntityName());
			mod.takeValueForKey(this.primaryKey(), ATTRIBUTE_NAME_FOR_MODIFICATION_PK_VALUE);
			mod.takeValueForKey(this.primaryKeyAttributeNames().objectAtIndex(0), ATTRIBUTE_NAME_FOR_MODIFICATION_PK_NAME);
			mod.takeValueForKey(this.entityName(), "type");
			
			// set the necessary values
			mod.takeValueForKey(changes, this.modificationsArrayAttributeName());
		}
	}
	
	/**
	 * Gets the modifications for this object from the database
	 * 
	 * @return array of Modification objects or null
	 */
	public NSArray modifications () {
		NSArray result = null;
		EOFetchSpecification fs = new EOFetchSpecification();
		fs.setRefreshesRefetchedObjects(true);
		
		fs.setEntityName(this.modificationEntityName());
		
		// qualifier
		String formatString = "primaryKeyValue = '" + this.primaryKey() + "' and type = %@";
		EOQualifier q = EOQualifier.qualifierWithQualifierFormat(formatString, new NSArray(this.entityName()));
		fs.setQualifier(q);

		// sorting
		EOSortOrdering sortOrdering = EOSortOrdering.sortOrderingWithKey("creationTime", EOSortOrdering.CompareDescending);
		fs.setSortOrderings(new NSArray(sortOrdering));
		
		// get the stuff
		result = this.editingContext().objectsWithFetchSpecification(fs);
		if (result != null && result.count() == 0) {
			result = null;
		}
		
		return result;
	}

	/**
	 * The Modification entity name, defaults to "Modification"
	 * 
	 * @return
	 */
	public String modificationEntityName() {
		return "Modification";
	}
	
	/**
	 * The name of the attribute, where the array of modifications is stored in. Defaults to "modificationsArray".
	 * 
	 * @return the name
	 */
	public String modificationsArrayAttributeName () {
		return EVGenericRecord.MODIFICATIONSARRAY; 
	}
	
	private NSDictionary _primaryKeyDictionary = null;

	public NSDictionary primaryKeyDictionary(boolean inTransaction) {
		if (_primaryKeyDictionary == null) {
			_primaryKeyDictionary = ERXLongPrimaryKeyFactory.primaryKeyDictionary(this);
		}
		return _primaryKeyDictionary;
	}
 }
