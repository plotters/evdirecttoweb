// DataSetEntry.java

package net.events.cms.eo;

import java.math.*;
import java.util.*;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public abstract class DataSetEntry extends _DataSetEntry {
	
	private static Logger log = Logger.getLogger( DataSetEntry.class );

	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
	}
	
	public static DataSetEntry createNewInstanceForTemplateInEditingContext(DataSetTemplate template, EOEditingContext editingContext) {
		return null;
	}
	
	
    /**
     * The existing sections
     * 
     * @return
     */
    public NSArray sections () {
    	return this.dataSetTemplate().sections();
    }
    
    /**
     * Return the specific values for a given section
     *  
     * @param aSection
     * @return
     */
    public NSArray valuesForSection (DataSetSection aSection) {
    	if (aSection != null) {
    		EOQualifier q = EOQualifier.qualifierWithQualifierFormat("dataSetItem.section = %@", new NSArray<DataSetSection>(aSection));
    		return ERXArrayUtilities.filteredArrayWithQualifierEvaluation(this.values(), q);
    	}
    	return null;
    }

	/**
	 * All values that are not going to be displayed in sections
	 * 
	 * @return
	 */
	public NSArray valuesNotInASection() {
   		EOQualifier q = EOQualifier.qualifierWithQualifierFormat("dataSetItem.section = null", null);
		return ERXArrayUtilities.filteredArrayWithQualifierEvaluation(this.values(), q);
	}
	
	public BigDecimal averageOverSelectionScores (NSArray selectionValues) {
		Enumeration<DataSetSelectionItemValue> enumeration = selectionValues.objectEnumerator();
		int sum = 0;
		int count = 0;
		while (enumeration.hasMoreElements()) {
			DataSetSelectionItemValue v = enumeration.nextElement();
			if (v.selectedOption() != null && v.selectedOption().score() != null) {
				sum += v.selectedOption().score().intValue();
				count++;
			}
		}
		BigDecimal result = null;
		if (sum != 0 && count != 0) {
			BigDecimal bigSum = new BigDecimal(sum);
			result = bigSum.divide(new BigDecimal(count), 2, BigDecimal.ROUND_HALF_UP);
		}
		
		return result;
	}
	
	public BigDecimal averageOverAllSelectionScores () {
		return this.averageOverSelectionScores(this.selectionItemValues());
	}
	
	@SuppressWarnings("unchecked")
	public String averageOverSectionSelectionScores () {
		StringBuffer sb = new StringBuffer();
		NSDictionary grouped = ERXArrayUtilities.arrayGroupedByKeyPath(this.selectionItemValues(), "dataSetItem.section.sectionName");
		
		NSArray keys = grouped.allKeys();
		sb.append ("<table>");
		for (int i = 0; i < keys.count(); i++) {
			NSArray tmp = (NSArray) grouped.valueForKey((String) keys.objectAtIndex(i));
			if (tmp.count() != 0 && ((DataSetSelectionItemValue) tmp.objectAtIndex(0)).dataSetItem().section().calculateAverages() != null && 
					((DataSetSelectionItemValue) tmp.objectAtIndex(0)).dataSetItem().section().calculateAverages().booleanValue()) {

				BigDecimal average = this.averageOverSelectionScores(tmp);
				sb.append("<tr><td style=\"padding: 2px;\">" + keys.objectAtIndex(i) + "</td><td style=\"padding: 2px; text-align: right;\">&nbsp;" + average + "</td></tr>");
				
			}
		}
		sb.append("</table>");
		
		return sb.toString();
	}
	
	protected NSArray<DataSetSelectionItemValue> selectionItemValues () {
		NSMutableArray<DataSetSelectionItemValue> result = new NSMutableArray<DataSetSelectionItemValue>();
		Enumeration<DataSetItemValue> enumeration = this.values().objectEnumerator();
		while (enumeration.hasMoreElements()) {
			DataSetItemValue v = enumeration.nextElement();
			if (v instanceof DataSetSelectionItemValue) {
				result.addObject((DataSetSelectionItemValue) v);
			}
		}
		return result.immutableClone();
	}
}