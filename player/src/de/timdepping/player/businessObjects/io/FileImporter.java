package de.timdepping.player.businessObjects.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import de.timdepping.player.businessObjects.exceptions.NotSupportedFileFormatException;
import de.timdepping.player.valueObjects.Format;
import de.timdepping.player.valueObjects.MediaFile;

public class FileImporter {

	public Hashtable<String, MediaFile> readFiles(String fileName)
			throws IOException, FileNotFoundException, NotSupportedFileFormatException {
		String line = null;
		String[] words = null;
		String[] key = null;
		FileReader reader = null;
		Hashtable<String, MediaFile> files = new Hashtable<String, MediaFile>();

		try {
			reader = new FileReader(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("\"" + fileName + "\" can not be found.");
		}
		BufferedReader textFile = new BufferedReader(reader);

		String nameOfFile = "";
		double sizeOfFile = 0.0;
		Format formatOfFile = null;

		while ((line = textFile.readLine()) != null) {

			if (!line.isEmpty()) {
				words = line.split(":");
				if (words.length != 2) {
					throw new NotSupportedFileFormatException(
							"\"" + fileName + "\" has a not supported file format. A line has a wrong format.");
				}
				for (String word : words) {
					if (word.isBlank()) {
						throw new NotSupportedFileFormatException(
								"\"" + fileName + "\" has a not supported file format. A line has no value.");
					}
				}
				key = words[0].split("\\.");
				String value = words[1];
				String fileId = key[0];
				String property = key[1];
				if (!property.equals("name") && !property.equals("size") && !property.equals("format")) {
					throw new NotSupportedFileFormatException(
							"\"" + fileName + "\" has a not supported file format. A property is unknown.");
				}
				if (property.equals("name")) {
					nameOfFile = value;
				}
				if (property.equals("size")) {
					sizeOfFile = Double.parseDouble(value);
				}
				if (property.equals("format")) {
					value = value.replaceAll(" ", "");
					formatOfFile = Format.UNKNOWN;
					if (value.equals("AIFF")) {
						formatOfFile = Format.AIFF;
					}
					if (value.equals("MP3")) {
						formatOfFile = Format.MP3;
					}
					if (value.equals("MP4")) {
						formatOfFile = Format.MP4;
					}
					if (value.equals("WAV")) {
						formatOfFile = Format.WAV;
					}
					if (value.equals("QUICKTIME")) {
						formatOfFile = Format.QUICKTIME;
					}
				}

				if (!nameOfFile.isEmpty() && sizeOfFile != 0.0 && formatOfFile != null) {
					files.put(fileId, new MediaFile(nameOfFile, sizeOfFile, formatOfFile));
					nameOfFile = "";
					sizeOfFile = 0.0;
					formatOfFile = null;
				}
			}
		}
		textFile.close();

		return files;
	}

}
