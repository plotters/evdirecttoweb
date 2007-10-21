package net.events.designer.components;

// Generated by the WOLips Templateengine Plug-in at 11.02.2007 19:25:05

import net.events.cms.eo.*;
import net.events.designer.appserver.*;

import com.webobjects.appserver.*;
import com.webobjects.directtoweb.*;
import com.webobjects.eoaccess.*;
import com.webobjects.foundation.*;

import er.extensions.*;

public class Main extends WOComponent {

	// used for the login textfield
	private String login;

	// used for the password textfield
	private String password;

	// used in the conditional to show, whether the login faild
	private boolean messageAvailable = false;

	// the message to display to the user
	private String message;

	// Editing context
	ERXEC editingContext = (ERXEC) ERXEC.newEditingContext();

	public Main(WOContext aContext) {
		super(aContext);
	}

	/*
	 * Resetting some values, call to super
	 * @see com.webobjects.appserver.WOComponent#awake()
	 */
	public void awake() {
		super.awake();
		this.setMessageAvailable(false);

	}

	public void appendToResponse (WOResponse r, WOContext c) {
		super.appendToResponse(r, c);
	}
	
	/**
	 * Login action
	 */
	public WOComponent submit() {

		// no empty entries allowed
		if (login == null || password == null || login.equals("") || password.equals("")) {
			this.setMessage("Login failed. Please fill both fields.");
			this.setMessageAvailable(true);
			return null;
		}

		NSDictionary values = new NSDictionary(new String[] { this.login, this.password }, new String[] { "login", "password" });
		try {
			Person user = (Person) EOUtilities.objectMatchingValues(editingContext, Person.ENTITY_NAME, values);
			
			if (!(user instanceof EventsUser)) {
				this.setMessageAvailable(true);
				this.setMessage("Login.LOGIN_DENIED_LOGIN_ONLY_FOR_USERS");
				return null;
			}
			else if (user instanceof EventsUser) {
				EventsUser eu = (EventsUser) user;
				if (!eu.active()) {
					this.setMessage("Login.ACCOUNT_NOT_ACTIVE_MESSAGE");
					this.setMessageAvailable(true);
					return null;
				}
			}
			
			((Session) this.session()).setCurrentUser(user);
			((Session) this.session()).setCurrentClient(user.client());
			
			return D2W.factory().defaultPage(session());
		} catch (Exception e) {
			e.printStackTrace();

			this.setMessageAvailable(true);
			this.setMessage("Login failed. Login or password wrong.");

			return null;
		}
	}

	public WOComponent defaultPage() {
		if (isAssistantCheckboxVisible()) {
			D2W.factory().setWebAssistantEnabled(false);
		}
		return D2W.factory().defaultPage(session());
	}

	public boolean isAssistantCheckboxVisible() {
		String s = System.getProperty("D2WWebAssistantEnabled");
		return s == null || NSPropertyListSerialization.booleanForString(s);
	}

	/**
	 getter for login instance var
	 */
	public String login() {
		return login;
	}

	/**
	 setter for login instance var
	 */
	public void setLogin(String newLogin) {
		login = newLogin;
	}

	/**
	 getter for password instance var
	 */
	public String password() {
		return password;
	}

	/**
	 setter for password instance var
	 */
	public void setPassword(String newPassword) {
		password = newPassword;
	}

	/**
	 * @return the messageAvailable
	 */
	public boolean messageAvailable() {
		return this.messageAvailable;
	}

	/**
	 * @param messageAvailable the messageAvailable to set
	 */
	public void setMessageAvailable(boolean flag) {
		this.messageAvailable = flag;
	}

	/**
	 * @return the message
	 */
	public String message() {
		if (message != null) {
			return this.message;
		} else
			return "No message available - Sorry";
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
