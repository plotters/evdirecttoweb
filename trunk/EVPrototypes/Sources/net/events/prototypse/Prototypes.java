package net.events.prototypse;


import er.extensions.ERXExtensions;
import er.extensions.ERXFrameworkPrincipal;

/**
 * HACKALERT 
 * simple class that ensures correct framework reference with PBX 
 */

public class Prototypes extends ERXFrameworkPrincipal {

	public static Class[] REQUIRES = { ERXExtensions.class };

	static {
		setUpFrameworkPrincipalClass(Prototypes.class);
	}

	@Override
	public void finishInitialization() {
		// TODO Auto-generated method stub
		
	}

}
