package de.timdepping.player.valueObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import de.timdepping.player.valueObjects.exceptions.OutOfMemoryException;

public class Medium {
	private String name;
	private double capacity;
	private ArrayList<MediaFile> files = new ArrayList<MediaFile>();

	public Medium(String name, double capacity) {
		setName(name);
		setCapacity(capacity);
	}

	public void addFile(MediaFile file) throws OutOfMemoryException {
		Objects.requireNonNull(file, "Tried to add File that is null");
		if (getFreeCapacity() < file.getSize()) {
			throw new OutOfMemoryException(
					String.format("Ran out of Memory while adding File \"%s \"", file.getName()));
		}
		files.add(file);
	}

	public void deleteFile(MediaFile file) {
		files.remove(file);
	};

	public double getFreeCapacity() {
		return getCapacity() - getUsedCapacity();
	}

	public double getUsedCapacity() {
		double usedCapacity = 0;
		for (int i = 0; i < files.size(); i++) {
			usedCapacity += files.get(i).getSize();
		}
		return usedCapacity;
	}

	public MediaFile getNextFile(MediaFile file) {
		if (file == null) {
			file = getFiles().get(getFiles().size() - 1);
		}
		int currentIndex = files.indexOf(file);
		return currentIndex == (files.size() - 1) ? files.get(0) : files.get(currentIndex + 1);
	}

	public MediaFile getPreviousFile(MediaFile file) {
		Objects.requireNonNull(file, "getPreviousFile: file must not be null.");
		int currentIndex = files.indexOf(file);
		return currentIndex == 0 ? files.get(files.size() - 1) : files.get(currentIndex - 1);
	}

	public MediaFile getLastFile(MediaFile file) {
		Objects.requireNonNull(file, "getLastFile: file must not be null.");
		MediaFile lastFile = files.get(files.size());
		return lastFile;
	}

	public void sortFilesByName() {
		Collections.sort(files);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(
				String.format("Medium: %s\n\tCapacity: \t%s\n\tUsed: \t\t%s", name, capacity, getUsedCapacity()));
		if (files != null) {
			sb.append("\n\tFiles:");
			for (int i = 0; i < files.size(); i++) {
				sb.append("\n\t\t" + files.get(i).toString());
			}
		}
		return sb.toString();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getCapacity() {
		return this.capacity;
	}

	public ArrayList<MediaFile> getFiles() {
		return files;
	}

}
