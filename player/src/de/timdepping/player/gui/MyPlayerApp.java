package de.timdepping.player.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import de.timdepping.player.businessObjects.exceptions.NotSupportedFileFormatException;
import de.timdepping.player.businessObjects.io.FileImporter;
import de.timdepping.player.valueObjects.AudioPlayer;
import de.timdepping.player.valueObjects.MediaFile;
import de.timdepping.player.valueObjects.exceptions.OutOfMemoryException;

public class MyPlayerApp {

	public static void main(String[] args) {

		AudioPlayer ipodNano = new AudioPlayer("iPod Nano", 1024.0);

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
			}
		} catch (IOException e) {
			System.out.println(e);
		} catch (NotSupportedFileFormatException e) {
			System.out.println(e);
		}

		JFrame frame = new JFrame(ipodNano.getName());
		frame.setSize(500, 500);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		JPanel displayPanel = new JPanel();

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());

		GridBagLayout layout = new GridBagLayout();

		JLabel songsText = new JLabel(ipodNano.getName());
		displayPanel.add(songsText);

		JButton playButton = new JButton(">");
		playButton.addActionListener((ActionEvent event) -> {
			if (playButton.getText().equals(">")) {
				if (ipodNano.getCurrentFile() != null) {
					try {
						ipodNano.play(ipodNano.getCurrentFile());
						playButton.setText("||");
					} catch (NotSupportedFileFormatException e) {
						System.out.println(e);
					}
				} else {
					try {
						ipodNano.play(ipodNano.getFiles().get(0));
						playButton.setText("||");
					} catch (NotSupportedFileFormatException e) {
						System.out.println(e);
					}
				}
				songsText.setText(ipodNano.getCurrentFile().getFileName());
			} else {
				try {
					ipodNano.pause();
					playButton.setText(">");
				} catch (NotSupportedFileFormatException e) {
					System.out.println(e);
				}
			}
		});
		buttonsPanel.add(playButton, BorderLayout.NORTH);

		class PreviousButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					ipodNano.previous();
				} catch (NotSupportedFileFormatException e) {
					System.out.println(e);
				}
				songsText.setText(ipodNano.getCurrentFile().getFileName());
			}
		}

		JButton previousButton = new JButton("<<");
		previousButton.setPreferredSize(new Dimension(40, 40));
		previousButton.addActionListener(new PreviousButtonActionListener());
		buttonsPanel.add(previousButton, BorderLayout.WEST);

		JButton nextButton = new JButton(">>");
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					ipodNano.next();
				} catch (NotSupportedFileFormatException e) {
					playButton.setText(">");
					System.out.println(e);
				}
				songsText.setText(ipodNano.getCurrentFile().getFileName());
			}
		});
		buttonsPanel.add(nextButton, BorderLayout.EAST);

		JButton playFirstButton = new JButton("-");
		playFirstButton.addActionListener((event) -> {
			try {
				ipodNano.play(ipodNano.getFiles().get(0));
				playButton.setText("||");
				songsText.setText(ipodNano.getCurrentFile().getFileName());
			} catch (NotSupportedFileFormatException e) {
				System.out.println(e);
			}
		});
		buttonsPanel.add(playFirstButton, BorderLayout.SOUTH);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				frame.setVisible(false);
				frame.dispose();
				System.exit(0);
			}
		});

		frame.setLayout(new GridBagLayout());
		frame.getContentPane().add(displayPanel);
		frame.getContentPane().add(buttonsPanel);
		frame.setVisible(true);
	}
}
