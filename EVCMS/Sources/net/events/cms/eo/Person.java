// Person.java

package net.events.cms.eo;

import java.util.*;

import net.events.cms.extensions.*;

import org.apache.log4j.*;

import com.ibm.icu.math.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public class Person extends _Person {
	
	/**
	 * generated serial id
	 */
	private static final long serialVersionUID = 3336911825184728910L;

	private static Logger log = Logger.getLogger( Person.class );
	
	
	/** 
	 * Initialization of the instance while inserting it into an editing context
	 */
	public void awakeFromInsertion (EOEditingContext editingContext) {
		super.awakeFromInsertion (editingContext);

		// initialize your object here
		this.setPasswordAttempts(ERXConstant.ZeroInteger);
		this.setGender(Gender.unknownGender(editingContext));
	}

	/**
	 * Standard constructor
	 */
    public Person() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Person");
    }
    
    /**
     * Creates a new instance of "Person" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public Person( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class Person and inserted it into an editingContext");
        ec.insertObject( this );
    }
    
    public BigDecimal age () {
    	BigDecimal result = null;
    	if (this.dateOfBirth() != null) {
    		long dob = this.dateOfBirth().getTime();
    		long now = new NSTimestamp().getTime();
    		
    		long difference = now - dob;
    		double days = difference / 86400000.00;
    		double years = days / 365.00;
    		
    		result = new BigDecimal(years);
    	}
    	return result;
    	
    }
    
    /**
     * Returns whether this user is a super admin (means: has access to everything)
     * 
     * @return Boolean.TRUE if the current user is a super admin
     */
    public Boolean isSuperAdmin() {
		if (this instanceof EventsUser) {
			if (this.valueForKey("usergroup") != null && ((Usergroup) this.valueForKey("usergroup")).name().equals(EVCMSConstants.SUEPR_ADMIN_USERGROUP_NAME)) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		} else
			return false;
	}

    /**
     * Returns the name as "Lisa Miller"
     * 
     * @return
     */
    public String firstnameLastname () {
    	return this.firstname() + " " + this.lastname();
    }

	public DataSetItemActionTrigger triggerForDataSetItem(DataSetItem dataSetItem) {
		Enumeration<DataSetItemActionTrigger> enumeration = this.dataSetItemActionTriggers().objectEnumerator();
		while (enumeration.hasMoreElements()) {
			DataSetItemActionTrigger trigger = enumeration.nextElement();
			if (trigger.dataSetItem().primaryKey().equals(dataSetItem.primaryKey())) {
				return trigger;
			}
		}
		return null;
	}
    
}