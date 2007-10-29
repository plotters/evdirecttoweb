// MedicalDataSetEntry.java

package net.events.cms.eo;

import java.util.*;

import net.events.cms.extensions.*;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public class MedicalDataSetEntry extends _MedicalDataSetEntry {
	
	private static Logger log = Logger.getLogger( MedicalDataSetEntry.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
	}

	/**
	 * Standard constructor
	 */
    public MedicalDataSetEntry() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class MedicalDataSetEntry");
    }
    
    /**
     * Creates a new instance of "MedicalDataSetEntry" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public MedicalDataSetEntry( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class MedicalDataSetEntry and inserted it into an editingContext");
        ec.insertObject( this );
    }
 
    /**
     * Create a set of values for a given template inside an editingContext
     * 
     * @param template
     * @param editingContext
     * @return
     */
    public static MedicalDataSetEntry createNewInstanceForTemplateInEditingContext(DataSetTemplate template, EOEditingContext editingContext) {
    	MedicalDataSetEntry entry = new MedicalDataSetEntry (editingContext);
    	
    	entry.addObjectToBothSidesOfRelationshipWithKey(template, DATASETTEMPLATE);
    	StudyParticipant sp;
    	if (ERXThreadStorage.valueForKey("CURRENT_PARTICIPANT") != null) {
    		sp = (StudyParticipant) ERXThreadStorage.valueForKey(editingContext, "CURRENT_PARTICIPANT");
    	}
    	else {
    		sp = (StudyParticipant) ERXThreadStorage.valueForKey(editingContext, EVCMSConstants.CURRENT_USER);
    	}
		entry.addObjectToBothSidesOfRelationshipWithKey(sp, PERSON);
		entry.addObjectToBothSidesOfRelationshipWithKey(template.client(), CLIENT);

		NSArray items = ERXArrayUtilities.sortedArraySortedWithKey(template.dataSetItems(), "orderNumber");
    	Enumeration enumeration = items.objectEnumerator();
    	while (enumeration.hasMoreElements()) {
    		// create answer objects for all questions from the template
    		Object item = enumeration.nextElement();
    		if (((DataSetItem) item).active() != null && ((DataSetItem) item).active().booleanValue()) {
	    		DataSetItemValue value = null;
	    		if (item instanceof DataSetNumberItem) {
	    			value = new DataSetNumberItemValue(editingContext);
	    		}
	    		else if (item instanceof DataSetDecimalItem) {
	    			value = new DataSetDecimalItemValue(editingContext);
	    		}
	    		else if (item instanceof DataSetTextItem) {
	    			value = new DataSetTextItemValue(editingContext);
	    		}
	    		else if (item instanceof DataSetSelectionItem) {
	    			value = new DataSetSelectionItemValue(editingContext);
	    		}
	    		else if (item instanceof DataSetDateItem) {
	    			value = new DataSetDateItemValue(editingContext);
	    		}
	    		value.addObjectToBothSidesOfRelationshipWithKey(entry, DataSetItemValue.DATASETENTRY);
	    		value.addObjectToBothSidesOfRelationshipWithKey((EORelationshipManipulation) item, DataSetItemValue.DATASETITEM);
	    		value.addObjectToBothSidesOfRelationshipWithKey(template.client(), DataSetItemValue.CLIENT);
    		}
    	}
    	
    	return entry;
    }

   
}