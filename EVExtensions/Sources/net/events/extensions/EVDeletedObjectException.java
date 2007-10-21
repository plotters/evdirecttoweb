package net.events.extensions;


public class EVDeletedObjectException extends RuntimeException {

	public static String DELETED_OBJECT_MESSAGE = "One of the objects we wanted to save was deleted by someone else!\n" +
													"This might have happened in a different context from another user, perhaps even " +
													"on another application.\n" +
													"There is nothing we can do about that. Sorry!";
	
	public EVDeletedObjectException(String string) {
		super(string);
	}

}
