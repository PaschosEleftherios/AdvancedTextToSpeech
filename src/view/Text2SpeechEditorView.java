package view;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import commands.CommandsFactory;
import commands.CommandsFactory.Command;
import model.Document;
import model.Document.EncodingType;
import model.TTSFacade;

import java.awt.Dimension;
import javax.swing.JSlider;
import java.awt.Rectangle;

public class Text2SpeechEditorView {
	
	private static Text2SpeechEditorView window;
	private Document currentDocument;
	
	private JFrame frame;
	private JTextArea documentTextArea;
	private JSlider sliderRate;
	private JSlider sliderPitch;
	private JSlider sliderVolume;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Text2SpeechEditorView();
					window.initialize();
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}		});
	}

	/**
	 * Create the application.
	 */
	public Text2SpeechEditorView() {
		currentDocument = new Document(new TTSFacade());
	}
	
	public static Text2SpeechEditorView getInstance() {
		return window;
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Text 2 Speech");
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		// Main Panel
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(1, 2));
		
		documentTextArea = new JTextArea();
		documentTextArea.setMargin(new Insets(16, 16, 16, 16));
		documentTextArea.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(8, 8, 8, 8)));
		mainPanel.add(documentTextArea);
		
		// Menus
		initializeMenu();
		initializeSecondaryMenu();
		initializeBottomMenu();
	}

	private void initializeMenu() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// File
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);

		JMenuItem menuItemOpen = new JMenuItem("Open Document");
		menuItemOpen.addActionListener(CommandsFactory.createCommand(Command.OPEN_DOCUMENT, currentDocument));
		menuFile.add(menuItemOpen);

		JSeparator separator = new JSeparator();
		menuFile.add(separator);

		JMenuItem menuItemSave = new JMenuItem("Save");
		menuItemSave.addActionListener(CommandsFactory.createCommand(Command.SAVE_DOCUMENT, currentDocument));
		menuFile.add(menuItemSave);

		JMenuItem menuItemSaveAs = new JMenuItem("Save As");
		menuItemSaveAs.addActionListener(CommandsFactory.createCommand(Command.SAVE_AS_DOCUMENT, currentDocument));
		menuFile.add(menuItemSaveAs);

		// Last Commands
		JMenu menuReplayManager = new JMenu("Replay Manager");
		menuBar.add(menuReplayManager);
		
		JMenuItem menuItemStartRecording = new JMenuItem("Start recording");
		menuItemStartRecording.addActionListener(CommandsFactory.createCommand(Command.START_RECORDING, currentDocument));
		menuReplayManager.add(menuItemStartRecording);
		
		JMenuItem menuItemStopRecoring = new JMenuItem("Stop recording");
		menuItemStopRecoring.addActionListener(CommandsFactory.createCommand(Command.END_RECORDING, currentDocument));
		menuReplayManager.add(menuItemStopRecoring);
		
		JMenuItem menuItemPlayRecordedCommands = new JMenuItem("Play recorded commands");
		menuItemPlayRecordedCommands.addActionListener(CommandsFactory.createCommand(Command.REPLAY_COMMANDS, currentDocument));
		menuReplayManager.add(menuItemPlayRecordedCommands);
	}
	
	private void initializeSecondaryMenu() {
		JMenuBar secondaryMenu = new JMenuBar();
		frame.getContentPane().add(secondaryMenu, BorderLayout.NORTH);
		
		ButtonGroup radioGroup = new ButtonGroup();
		
		JRadioButton rdbtnAtbash = new JRadioButton("Atbash");
		rdbtnAtbash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentDocument.setEncoding(EncodingType.ATBASH);				
			}
		});
		radioGroup.add(rdbtnAtbash);
		
		JRadioButton rdbtnRot13 = new JRadioButton("Rot-13");
		rdbtnRot13.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentDocument.setEncoding(EncodingType.ROT_13);				
			}
		});
		radioGroup.add(rdbtnRot13);
		
		JRadioButton rdbtnNoEncoding = new JRadioButton("No encoding");
		rdbtnNoEncoding.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentDocument.setEncoding(EncodingType.NO_ENCODING);				
			}
		});
		rdbtnNoEncoding.setSelected(true);
		radioGroup.add(rdbtnNoEncoding);
		
		JLabel lblEncodingTechnique = new JLabel("Encoding technique:");
		lblEncodingTechnique.setBorder(new EmptyBorder(0, 0, 0, 8));
		lblEncodingTechnique.setToolTipText("");
		secondaryMenu.add(lblEncodingTechnique);
		
		secondaryMenu.add(rdbtnAtbash);
		secondaryMenu.add(rdbtnRot13);
		secondaryMenu.add(rdbtnNoEncoding);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setMaximumSize(new Dimension(10, 32767));
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBorder(new EmptyBorder(0, 16, 0, 16));
		secondaryMenu.add(separator_2);
		
		JButton btnPlay = new JButton("Play Audio");
		btnPlay.addActionListener(CommandsFactory.createCommand(Command.PLAY_AUDIO, currentDocument));
		secondaryMenu.add(btnPlay);
	}
	
	private void initializeBottomMenu() {
		JMenuBar bottomMenu = new JMenuBar();
		frame.getContentPane().add(bottomMenu, BorderLayout.SOUTH);
		
		// Rate
		JLabel lblRate = new JLabel("Rate:");
		bottomMenu.add(lblRate);
		
		sliderRate = new JSlider();
		sliderRate.setPreferredSize(new Dimension(100, 26));
		sliderRate.setMajorTickSpacing(50);
		sliderRate.setMinorTickSpacing(5);
		sliderRate.setMaximumSize(new Dimension(100, 26));
		sliderRate.setMaximum(200);
		sliderRate.setMinimum(100);
		sliderRate.setValue(150);
		bottomMenu.add(sliderRate);
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(10, 2));
		separator.setBounds(new Rectangle(10, 10, 10, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setMaximumSize(new Dimension(10, 32767));
		bottomMenu.add(separator);
		
		// Pitch
		JLabel lblPitch = new JLabel("Pitch:");
		bottomMenu.add(lblPitch);
		
		sliderPitch = new JSlider();
		sliderPitch.setPreferredSize(new Dimension(100, 26));
		sliderPitch.setMaximumSize(new Dimension(100, 26));
		sliderPitch.setMinorTickSpacing(5);
		sliderPitch.setMajorTickSpacing(50);
		sliderPitch.setMinimum(85);
		sliderPitch.setMaximum(255);
		sliderPitch.setValue(150);
		bottomMenu.add(sliderPitch);
		
		JSeparator separatorVolume = new JSeparator();
		separatorVolume.setPreferredSize(new Dimension(10, 2));
		separatorVolume.setOrientation(SwingConstants.VERTICAL);
		separatorVolume.setMaximumSize(new Dimension(5, 32767));
		bottomMenu.add(separatorVolume);
		
		// Volume
		JLabel lblVolume = new JLabel("Volume:");
		bottomMenu.add(lblVolume);
		
		sliderVolume = new JSlider();
		sliderVolume.setPreferredSize(new Dimension(100, 26));
		sliderVolume.setMaximumSize(new Dimension(100, 26));
		sliderVolume.setMinimum(0);
		sliderVolume.setMaximum(100);
		sliderVolume.setValue(100);
		bottomMenu.add(sliderVolume);
	}
	
	public String getText() {
		return documentTextArea.getText();
	}

	public float getRate() {
		return sliderRate.getValue();
	}

	public float getPitch() {
		return sliderPitch.getValue();
	}

	public float getVolume() {
		int value = sliderVolume.getValue();
		
		return (float) value / 100.0f;
	}
	
	public void setText(String text) {
		documentTextArea.setText(text);
	}
}
