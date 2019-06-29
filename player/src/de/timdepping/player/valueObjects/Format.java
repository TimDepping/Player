package de.timdepping.player.valueObjects;

public enum Format {
	MP3(0), MP4(1), WAV(2), AIFF(3), QUICKTIME(4), UNKNOWN(5);

	private final int number;

	private Format(int nr) {
		number = nr;
	}

	public String toString() {
		if (number == 0) {
			return ".MP3";
		}
		if (number == 1) {
			return ".MP4";
		}
		if (number == 2) {
			return ".WAV";
		}
		if (number == 3) {
			return ".AIFF";
		}
		if (number == 4) {
			return ".QuickTime";
		}
		return ".UNKNOWN";
	}

	public boolean isAudio() {
		if (number == 0 || number == 2 || number == 3) {
			return true;
		}
		return false;
	}

	public boolean isVideo() {
		if (number == 1 || number == 4) {
			return true;
		}
		return false;
	}
}
