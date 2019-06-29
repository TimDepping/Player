package de.timdepping.player.valueObjects;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import de.timdepping.player.businessObjects.exceptions.NotSupportedFileFormatException;
import de.timdepping.player.businessObjects.io.FileImporter;
import de.timdepping.player.valueObjects.exceptions.OutOfMemoryException;

public class TestDriver {

	public static void main(String[] args) {

		AudioPlayer ipodNano = new AudioPlayer("iPod Nano", 1024.0);
		System.out.println(ipodNano.toString());

		MultiMediaPlayer ipodTouch = new MultiMediaPlayer("iPod Touch", 2048.0);
		System.out.println(ipodTouch.toString());

		FileImporter importer = new FileImporter();

		try {
			Hashtable<String, MediaFile> files = importer.readFiles("MyFiles.txt");
			Iterator<Entry<String, MediaFile>> iterator = files.entrySet().iterator();
			while (iterator.hasNext()) {
				MediaFile currentFile = iterator.next().getValue();
				try {
					ipodNano.addFile(currentFile);
				} catch (OutOfMemoryException e) {
					System.out.println(e);
				}
				try {
					ipodTouch.addFile(currentFile);
				} catch (OutOfMemoryException e) {
					System.out.println(e);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		} catch (NotSupportedFileFormatException e) {
			System.out.println(e);
		}

		System.out.println(ipodNano.toString());
		System.out.println(ipodTouch.toString());

		try {
			ipodNano.play(new MediaFile("Purple Noise", 3.8, Format.MP3));
			ipodNano.next();
			ipodNano.previous();
			ipodNano.pause();
			ipodNano.play(new MediaFile("How To Sell Drugs Online (Fast)", 893.2, Format.QUICKTIME));
		} catch (NotSupportedFileFormatException e) {
			System.out.println(e);
		}

		try {
			ipodTouch.play(new MediaFile("Purple Noise", 3.8, Format.MP3));
			ipodTouch.next();
			ipodTouch.previous();
			ipodTouch.pause();
			ipodTouch.play(new MediaFile("How To Sell Drugs Online (Fast)", 893.2, Format.QUICKTIME));
		} catch (NotSupportedFileFormatException e) {
			System.out.println(e);
		}

		ipodNano.madeIn();

	}

}
