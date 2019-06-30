package de.timdepping.player.businessObjects.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import de.timdepping.player.businessObjects.exceptions.NotSupportedFileFormatException;
import de.timdepping.player.valueObjects.Format;
import de.timdepping.player.valueObjects.MediaFile;

public class TestDriverIO {

	private static MediaFile[] files = new MediaFile[3];
	private static ArrayList<MediaFile> files2 = new ArrayList<MediaFile>();

	public static void main(String[] args) {

		files[0] = new MediaFile("Purple Noise", 5.4, Format.MP3);
		files[1] = new MediaFile("Madness", 5.9, Format.QUICKTIME);
		files[2] = new MediaFile("Way To Heaven", 4.9, Format.WAV);

		Serializer se;
		try {
			se = new Serializer("Files.ser");
			se.serializeFile(files[0]);
			se.serializeFile(files[1]);
			se.serializeFile(files[2]);
			se.closeOutput();

			for (int i = 0; i < files.length; i++) {
				files2.add(se.deserializeFile());
				System.out.println(files2.get(i));
				System.out.println("Equal to original?: " + files2.get(i).equals(files[i]));
			}
			se.closeInput();
		} catch (FileNotFoundException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err.println(e);
			e.printStackTrace();
		}

		FileImporter importer = new FileImporter();
		ArrayList<MediaFile> importedFiles = new ArrayList<MediaFile>();

		try {
			Hashtable<String, MediaFile> files = importer.readFiles("MyFiles.txt");
			Iterator<Entry<String, MediaFile>> iterator = files.entrySet().iterator();
			while (iterator.hasNext()) {
				MediaFile currentFile = iterator.next().getValue();
				importedFiles.add(currentFile);
			}
		} catch (IOException e) {
			System.out.println(e);
		} catch (NotSupportedFileFormatException e) {
			System.out.println(e);
		}

		for (MediaFile file : importedFiles) {
			System.out.println(file.toString());
		}

	}
}
