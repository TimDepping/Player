package de.timdepping.player.valueObjects;

import de.timdepping.player.businessObjects.exceptions.NotSupportedFileFormatException;

public class MultiMediaPlayer extends Medium implements Player {
	private MediaFile currentFile;

	public MultiMediaPlayer(String name, double capacity) {
		super(name, capacity);
	}

	@Override
	public void play(MediaFile file) throws NotSupportedFileFormatException {
		if (file.getFormat().isAudio() || file.getFormat().isVideo()) {
			System.out.println("MultiMediaPlayer plays file \"" + file.getName() + "\"");
			currentFile = file;
		} else {
			throw new NotSupportedFileFormatException("An MultiMediaPlayer can just play audio and video files.");
		}

	}

	@Override
	public void pause() throws NotSupportedFileFormatException {
		if (currentFile != null) {
			if (currentFile.getFormat().isAudio() || currentFile.getFormat().isVideo()) {
				System.out.println("MultiMediaPlayer pause file \"" + currentFile.getName() + "\"");
			} else {
				throw new NotSupportedFileFormatException("An MultiMediaPlayer can just play audio and video files.");
			}
		}
	}

	@Override
	public void next() throws NotSupportedFileFormatException {
		MediaFile nextFile = getNextFile(currentFile);
		if (nextFile.getFormat().isAudio() || nextFile.getFormat().isVideo()) {
			System.out.println("MultiMediaPlayer to next file \"" + nextFile.getName() + "\"");
			currentFile = nextFile;
		} else {
			throw new NotSupportedFileFormatException("An MultiMediaPlayer can just play audio and video files.");
		}
	}

	@Override
	public void previous() throws NotSupportedFileFormatException {
		MediaFile previousFile = getPreviousFile(currentFile);
		if (previousFile.getFormat().isAudio() || previousFile.getFormat().isVideo()) {
			System.out.println("MultiMediaPlayer to previous file \"" + previousFile.getName() + "\"");
			currentFile = previousFile;
		} else {
			throw new NotSupportedFileFormatException("An MultiMediaPlayer can just play audio and video files.");
		}

	}

	@Override
	public MediaFile getCurrentFile() {
		return currentFile;
	}

}
