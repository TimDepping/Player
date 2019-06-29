package de.timdepping.player.valueObjects;

import java.util.Objects;

public class MediaFile {

	private static int counter = 0;
	private static int id = 0;
	private final int fileId;
	private String name;
	private double size;
	private Format format;

	public MediaFile(String name, double size, Format format) {
		counter++;
		id += 1;
		fileId = id;
		setName(name);
		setSize(size);
		setFormat(format);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fileId;
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(size);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MediaFile other = (MediaFile) obj;
		if (fileId != other.fileId)
			return false;
		if (format != other.format)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(
				String.format("MediaFile %s \" %s \"\t%s ", getFileId(), getFileName(), getSize()));
		return sb.toString();
	}

	public String getFileName() {
		return String.format("%s%s", name, getFormat().toString());
	}

	public int getFileId() {
		return fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Objects.requireNonNull(name, "name must not be null");
		this.name = name;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public static int getCounter() {
		return counter;
	}

}
