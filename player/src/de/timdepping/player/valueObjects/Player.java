package de.timdepping.player.valueObjects;

import de.timdepping.player.businessObjects.exceptions.NotSupportedFileFormatException;

public interface Player {

	public default void madeIn() {
		System.out.println("Made in China.");
	}

	public void play(MediaFile file) throws NotSupportedFileFormatException;

	public void pause() throws NotSupportedFileFormatException;

	public void next() throws NotSupportedFileFormatException;

	public void previous() throws NotSupportedFileFormatException;

	public MediaFile getCurrentFile();

}
