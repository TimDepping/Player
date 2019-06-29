package de.timdepping.player.valueObjects.exceptions;

public class OutOfMemoryException extends Exception {

	private static final long serialVersionUID = 5466181567513976325L;

	public OutOfMemoryException() {
		super();
	}

	public OutOfMemoryException(String e) {
		super(e);
	}
}
