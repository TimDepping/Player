package de.timdepping.player.businessObjects.exceptions;

public class NotSupportedFileFormatException extends Exception {

	private static final long serialVersionUID = -1160587418456139130L;

	public NotSupportedFileFormatException() {
		super();
	}

	public NotSupportedFileFormatException(String e) {
		super(e);
	}
}
