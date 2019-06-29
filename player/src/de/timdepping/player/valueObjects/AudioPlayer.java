package de.timdepping.player.valueObjects;

import de.timdepping.player.businessObjects.exceptions.NotSupportedFileFormatException;

public class AudioPlayer extends Medium implements Player {
	private MediaFile currentFile;

	public AudioPlayer(String name, double capacity) {
		super(name, capacity);
	}

	@Override
	public void play(MediaFile file) throws NotSupportedFileFormatException {
		currentFile = file;
		if (file.getFormat().isAudio()) {
			System.out.println("AudioPlayer plays file \"" + file.getName() + "\"");
		} else {
			throw new NotSupportedFileFormatException("An AudioPlayer can just play audio files.");
		}
	}

	@Override
	public void pause() throws NotSupportedFileFormatException {
		if (currentFile != null) {
			if (currentFile.getFormat().isAudio()) {
				System.out.println("AudioPlayer pauses file \"" + currentFile.getName() + "\"");
			} else {
				throw new NotSupportedFileFormatException("An AudioPlayer can just play audio files.");
			}
		}

	}

	@Override
	public void next() throws NotSupportedFileFormatException {
		MediaFile nextFile = getNextFile(currentFile);
		currentFile = nextFile;
		System.out.println("AudioPlayer to next file \"" + nextFile.getName() + "\"");
		if (!nextFile.getFormat().isAudio()) {
			throw new NotSupportedFileFormatException("An AudioPlayer can just play audio files.");
		}
	}

	@Override
	public void previous() throws NotSupportedFileFormatException {
		MediaFile previousFile = getPreviousFile(currentFile);
		currentFile = previousFile;
		System.out.println("AudioPlayer to previous file \"" + previousFile.getName() + "\"");
		if (!previousFile.getFormat().isAudio()) {
			throw new NotSupportedFileFormatException("An AudioPlayer can just play audio files.");
		}
	}

	@Override
	public MediaFile getCurrentFile() {
		return currentFile;
	}

}
