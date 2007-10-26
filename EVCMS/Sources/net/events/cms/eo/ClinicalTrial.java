// ClinicalTrial.java

package net.events.cms.eo;

import org.apache.log4j.*;

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public class ClinicalTrial extends _ClinicalTrial {
	
	private static Logger log = Logger.getLogger( ClinicalTrial.class );

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
    public ClinicalTrial() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class ClinicalTrial");
    }
    
    /**
     * Creates a new instance of "ClinicalTrial" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public ClinicalTrial( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class ClinicalTrial and inserted it into an editingContext");
        ec.insertObject( this );
    }
     
    /**
     * Returns the active number of participants for the trial
     * 
     * @return
     */
    public Integer activeParticipants () {
    	return this.countForStatus("active");
    }
    
    /**
     * Returns the number of out participants for the trial
     * 
     * @return
     */
    public Integer outParticipants () {
    	return this.countForStatus("out");
    }
    
    /**
     * Returns the number of withdrawn participants for the trial
     * 
     * @return
     */
    public Integer withdrawnParticipants () {
    	return this.countForStatus("withdrawn");
    }
    
    /**
     * Returns the number of not accepted participants for the trial
     * 
     * @return
     */
    public Integer notAcceptedParticipants () {
    	return this.countForStatus("not accepted");
    }

    /**
     * Returns the number of participants for status "s"
     * 
     * @param s - the status
     * 
     * @return the number
     */
    private Integer countForStatus (String s) {
    	if (s != null) {
	    	EOQualifier q = EOQualifier.qualifierWithQualifierFormat("status = '" + s + "' and clinicalTrial = %@", new NSArray<ClinicalTrial>(this));
	    	return ERXEOControlUtilities.objectCountWithQualifier(this.editingContext(), StudyParticipant.ENTITY_NAME, q);
    	}
    	else return new Integer(0);
    }
    
    /**
     * Return the number of randomized participants
     * @return
     */
    public Integer numberOfRandomizedParticipants () {
    	EOQualifier q = EOQualifier.qualifierWithQualifierFormat("randomized = %@ and clinicalTrial = %@", new NSArray<Object>(new Object[] {Boolean.TRUE, this}));
    	return ERXEOControlUtilities.objectCountWithQualifier(this.editingContext(), StudyParticipant.ENTITY_NAME, q);
    }
}