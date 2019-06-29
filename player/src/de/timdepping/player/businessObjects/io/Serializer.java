package de.timdepping.player.businessObjects.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.timdepping.player.valueObjects.MediaFile;

public class Serializer {

	private ObjectOutputStream os;
	private ObjectInputStream is;

	public Serializer(String file) throws FileNotFoundException, NullPointerException {
		try {
			os = new ObjectOutputStream(new FileOutputStream(file));
		} catch (IOException e) {
			System.err.println("Output: io error" + e.getMessage());
			e.printStackTrace();
		}
		try {
			is = new ObjectInputStream(new FileInputStream(file));
		} catch (IOException e) {
			System.err.println("Input:  io error" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void serializeFile(MediaFile file) {
		try {
			os.writeObject(file);
		} catch (IOException e) {
			System.err.println(" io error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public MediaFile deserializeFile() {
		MediaFile file = null;
		try {
			file = (MediaFile) is.readObject();
		} catch (IOException ex) {
			System.err.println("Reading error: " + ex.getMessage());
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("Converting error: " + e.getMessage());
			e.printStackTrace();
		}
		return file;
	}

	public void closeInput() {
		try {
			is.close();
		} catch (IOException e) {
			System.err.println("Error closing input file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void closeOutput() {
		try {
			os.close();
		} catch (IOException e) {
			System.err.println("Error closing output file: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
