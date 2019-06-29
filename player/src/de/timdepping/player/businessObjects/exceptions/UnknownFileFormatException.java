package de.timdepping.player.businessObjects.exceptions;

public class UnknownFileFormatException extends Exception {

	private static final long serialVersionUID = -5469168827066811151L;

	public UnknownFileFormatException() {
		super();
	}

	public UnknownFileFormatException(String e) {
		super(e);
	}
}
