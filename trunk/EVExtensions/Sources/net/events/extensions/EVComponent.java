package net.events.extensions;

import org.apache.log4j.*;

import com.webobjects.appserver.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

import er.extensions.*;

/**
 * Base component for all pages / components 
 * 
 * @author cug - May 8, 2007
 */
public abstract class EVComponent extends WOComponent {

	/**
	 * Logging support for generic component logging. Set
	 * "net.events.components" to Debug e.g. to get component constructor
	 * messages.
	 * 
	 * This logger is useful when you want to see which components are created.
	 */
	protected Logger componentLogger = Logger.getLogger("net.events.components");
	
	/**
	 * key value coding logging
	 */
	protected Logger kvcLogger = Logger.getLogger("net.events.kvc");

	/**
	 * For lazy binding
	 */
	private NSMutableDictionary _dynamicBindings;
	
	/**
	 * For collecting and displaying error messages on a page
	 */
	private NSMutableArray _errors;

	/**
	 * Default timestamp formatter
	 */
	private NSTimestampFormatter timestampFormatter;
	
	/**
	 * Standard constructor
	 * @author cug - May 8, 2007
	 * @param c
	 */
	public EVComponent(WOContext c) {
		super(c);

		componentLogger.debug("Created component: " + this.getClass().getName());
		
		timestampFormatter = new NSTimestampFormatter("%Y-%m-%d %H:%M:%S");
		timestampFormatter.setDefaultFormatTimeZone(NSTimeZone.defaultTimeZone());

	}
	
	/**
	 * Logs awake calls with component logger, adds information about sessionId,
	 * currentUser, and ipAddress
	 * 
	 * @author cug - May 15, 2007
	 */
	public void awake () {
		super.awake();
		
		componentLogger.debug ("Awake called in: " + this.getClass().getName());
		
		// reset the errors, good luck for the next run ... 
		this.setErrors(null);
	}
	
	/**
	 * Override this   
	 * 
	 * @author cug - May 23, 2007
	 * @return
	 */
	public WOComponent deleteObject () {
		return this;
	}
	
	/**
	 * Override this 
	 * @author cug - May 23, 2007
	 * @return
	 */
	public WOComponent cancelDeletion() {
		return this;
	}
	
	// =================================================================================
	// copied from ERXNonSynchronizing component
	//
	// just a lot of convenience methods

	/**
	 * Resolves a given binding as a int value. Useful for image sizes and the
	 * like.
	 * @param binding
	 *            binding to be resolved as a int value.
	 * @param defaultValue
	 *            default int value to be used if the binding is not bound.
	 * @return result of evaluating binding as a int.
	 */
	public int intValueForBinding(String binding, int defaultValue) {
		return ERXValueUtilities.intValueForBindingOnComponentWithDefault(binding, this, defaultValue);
	}

	/**
	 * Resolves a given binding as a boolean value. Defaults to false.
	 * @param binding
	 *            binding to be resolved as a boolean value.
	 * @return result of evaluating binding as a boolean.
	 */
	public boolean booleanValueForBinding(String binding) {
		return booleanValueForBinding(binding, false);
	}

	/**
	 * Resolves a given binding as a boolean value.
	 * @param binding
	 *            binding to be resolved as a boolean value.
	 * @param defaultValue
	 *            default boolean value to be used if the binding is not bound.
	 * @return result of evaluating binding as a boolean.
	 */
	// CHECKME: from the name of the method, one would think that
	// ERXValueUtilities.booleanValueForBindingOnComponentWithDefault
	// would be the correct method to use, but after reading the comment there,
	// I'm not sure.
	public boolean booleanValueForBinding(String binding, boolean defaultValue) {
		if (hasBinding(binding)) {
			return ERXValueUtilities.booleanValueWithDefault(valueForBinding(binding), false);
		} else {
			return defaultValue;
		}
	}

	/**
	 * Resolves a given binding as a boolean value with the option of specifing
	 * a boolean operator as the default value.
	 * @param binding
	 *            name of the component binding.
	 * @param defaultValue
	 *            boolean operator to be evaluated if the binding is not
	 *            present.
	 * @return result of evaluating binding as a boolean.
	 */
	public boolean booleanValueForBinding(String binding, ERXUtilities.BooleanOperation defaultValue) {
		if (hasBinding(binding)) {
			return booleanValueForBinding(binding, false);
		} else {
			return defaultValue.value();
		}
	}

	/**
	 * Resolves a given binding as an object in the normal fashion of calling
	 * <code>valueForBinding</code>. This has the one advantage of being able
	 * to resolve the resulting object as a {link ERXUtilities$Operation} if it
	 * is an Operation and then returning the result as the evaluation of that
	 * operation.
	 * @param binding
	 *            name of the component binding.
	 * @return the object for the given binding and in the case that it is an
	 *         instance of an Operation the value of that operation.
	 */
	public Object objectValueForBinding(String binding) {
		return objectValueForBinding(binding, null);
	}

	/**
	 * Resolves a given binding as an object in the normal fashion of calling
	 * <code>valueForBinding</code>. This has the one advantage of being able
	 * to resolve the resulting object as a {link ERXUtilities$Operation} if it
	 * is an Operation and then returning the result as the evaluation of that
	 * operation.
	 * @param binding
	 *            name of the component binding.
	 * @param defaultValue
	 *            value to be used if <code>valueForBinding</code> returns
	 *            null.
	 * @return the object for the given binding and in the case that it is an
	 *         instance of an Operation the value of that operation.
	 */
	public Object objectValueForBinding(String binding, Object defaultValue) {
		Object result = null;
		if (hasBinding(binding)) {
			Object o = valueForBinding(binding);
			result = (o == null) ? defaultValue : o;
		} else {
			result = defaultValue;
		}
		if (result instanceof ERXUtilities.Operation) {
			result = ((ERXUtilities.Operation) result).value();
		}
		return result;
	}

	/**
	 * Retrieves a given binding and if it is not null then returns
	 * <code>toString</code> called on the bound object.
	 * @param binding
	 *            to be resolved
	 * @return resolved binding in string format
	 */
	public String stringValueForBinding(String binding) {
		return stringValueForBinding(binding, null);
	}

	/**
	 * Retrieves a given binding and if it is not null then returns
	 * <code>toString</code> called on the bound object.
	 * @param binding
	 *            to be resolved
	 * @param defaultValue
	 *            value to be used if <code>valueForBinding</code> returns
	 *            null.
	 * @return resolved binding in string format
	 */
	public String stringValueForBinding(String binding, String defaultValue) {
		Object v = objectValueForBinding(binding, defaultValue);
		return v != null ? v.toString() : null;
	}

	/**
	 * Convenience method to get the localizer.
	 * @return
	 */
	public ERXLocalizer localizer() {
		if (this.context().hasSession()) {
			return ERXLocalizer.currentLocalizer();
		}
		else {
			return ERXLocalizer.localizerForLanguages(this.context().request().browserLanguages()); 
		}
	}

	/**
	 * Lazily initialized dictionary which can be used for the 'item' binding in
	 * a repetition for example: 'item = dynamicBindings.myVariable'. Useful in
	 * rapid turnaround modes where adding a iVar would cause hot code swapping
	 * to stop working.
	 * 
	 * @return
	 */
	public NSMutableDictionary dynamicBindings() {
		if (_dynamicBindings == null) {
			_dynamicBindings = new NSMutableDictionary();
		}
		return _dynamicBindings;
	}

	/**
	 * Resets the dynamic bindings and calls super
	 * 
	 * @author cug - May 11, 2007
	 * @see com.webobjects.appserver.WOComponent#reset()
	 */
	public void reset() {
		super.reset();
		if (_dynamicBindings != null) {
			_dynamicBindings.removeAllObjects();
		}
		if (_errors != null) {
			_errors = new NSMutableArray();
		}
	}

	/**
	 * @author cug - May 14, 2007
	 * @return Returns the _errors.
	 */
	public NSMutableArray errors() {
		return _errors;
	}

	/**
	 * @author cug - May 14, 2007
	 * @param _errors The _errors to set.
	 */
	public void setErrors(NSMutableArray _errors) {
		this._errors = _errors;
	}

	/**
	 * Convenience method
	 * 
	 * @author cug - May 14, 2007
	 * @param message
	 */
	public void addToErrors (String message) {
		if (message == null) return; 
		if (this.errors() == null) {
			this.setErrors(new NSMutableArray());
		}
		this.errors().add(message);
	}
	
}
