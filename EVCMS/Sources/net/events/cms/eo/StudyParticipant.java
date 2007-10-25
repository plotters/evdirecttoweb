// StudyParticipant.java

package net.events.cms.eo;

import net.events.cms.extensions.*;

import org.apache.log4j.*;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

public class StudyParticipant extends _StudyParticipant {
	
	private static Logger log = Logger.getLogger( StudyParticipant.class );

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
    public StudyParticipant() {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class StudyParticipant");
    }
    
    /**
     * Creates a new instance of "StudyParticipant" and inserts it into the given editingContext.
     * This is a convenient shortcut for "EOUtilities.createAndInsertInstance"
     */
    public StudyParticipant( EOEditingContext ec ) {
        super();
    	if (log.isDebugEnabled()) log.debug ("Created an object of class StudyParticipant and inserted it into an editingContext");
        ec.insertObject( this );
    }
    
    /* (non-Javadoc)
     * @see er.extensions.ERXGenericRecord#validateForSave()
     */
    public void validateForSave () {
    	super.validateForSave();
    	if (this.participantId() != null && this.login() != null && !this.participantId().equals(this.login())) {
    		throw new NSValidation.ValidationException("StudyParticipant.PARTICIPANT_ID_AND_LOGIN_ARE_NOT_EQUAL");
    	}
    	if (this.participantId() != null && this.clinicalTrial() != null) {
    		try {
    			StudyParticipant other = (StudyParticipant) EOUtilities.objectWithQualifierFormat(this.editingContext(), StudyParticipant.ENTITY_NAME,"participantId = %@ and clinicalTrial = %@", new NSArray (new Object[] {this.participantId(), this.clinicalTrial()}));
    			// is not new, but is the same pk as the one in the database => no problem
    			if (this.isNewObject() && other != null) {
    				log.fatal("Cannot save: " + this);
    				log.fatal("Other object: " + other);
    				throw new NSValidation.ValidationException("StudyParticipant.ID_NOT_VALID");
    			}
    			else if (!this.isNewObject() && !this.primaryKey().equals(other.primaryKey())){
    				log.fatal("Cannot save: " + this);
    				log.fatal("Other object: " + other);
    				throw new NSValidation.ValidationException("StudyParticipant.ID_NOT_VALID");
    			}
    		}
    		catch (EOObjectNotAvailableException ona) {
			}
    	}
    	
    	if (EVCMSConstants.OTHER_ETHNIC_GROUP.equals(this.ethnicGroup())) {
    		if (this.otherEthnicGroup() == null || this.otherEthnicGroup().length() == 0) {
    			throw new NSValidation.ValidationException("StudyParticipant.SPECIFY_ETHNIC_GROUP");
    		}
    	}
    }
    
    public String toString () {
    	return this.participantId() + " -- " + this.firstnameLastname();
    }
    
}