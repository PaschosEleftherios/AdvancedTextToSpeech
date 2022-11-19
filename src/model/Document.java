package model;

import java.util.ArrayList;

import input.AtbashReaderDecorator;
import input.DocumentReader;
import input.DocumentReaderFactory;
import input.NoEncodingReaderDecorator;
import input.ReaderDecorator;
import input.Rot13ReaderDecorator;
import output.AtbashWriterDecorator;
import output.DocumentWriter;
import output.DocumentWriterFactory;
import output.NoEncodingWriterDecorator;
import output.Rot13WriterDecorator;
import output.WriterDecorator;


public class Document{

	public enum EncodingType{
		NO_ENCODING,
		ATBASH,
		ROT_13
	}
	
	private ArrayList<String> contents;
	private String fullPath;
	private String fileType;
	private EncodingType encoding;
	private TTSFacade audioManager;
	
	public Document() {
		contents = new ArrayList<String>();
		encoding = EncodingType.NO_ENCODING;
	}
	
	public Document(TTSFacade audioManager) {
		this();
		this.audioManager = audioManager;
	}
	
	// Actions
	public void open(String fullPath) {
		setFullPath(fullPath);
		
		try {
			DocumentReader reader = DocumentReaderFactory.createReader(fileType);
			ReaderDecorator readerDecorator = getTheRightReaderDecorator(reader);
			contents = readerDecorator.read(fullPath);
		}
		catch (IllegalArgumentException e) {
			contents = new ArrayList<String>();
			System.out.println("Unknown file type");
		}
	}
	
	public void save(String textContents) {
		// Check if has saved previously
		if (!hasSavedPreviously()) {
			System.out.println("The document doesn't have a path to save to.");
			return;
		}
		
		String[] contentsSplitted = textContents.split("\n");
		
		// Save contents to this object
		contents.clear();
		for(String line : contentsSplitted) {
			contents.add(line);
		}
				
		// Save contents to file
		try {
			DocumentWriter writer = DocumentWriterFactory.createWriter(fileType);
			WriterDecorator writerDecorator = getTheRightWriterDecorator(writer);
			writerDecorator.write(contents, fullPath);
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			return;
		}
		
		
	}
	
	// Play document
	public void playContents() {
		if (isEmpty()) {
			playEmpty();
			return;
		}
		
		StringBuilder contentsBuilder = new StringBuilder();
		
		for(String line : contents) {
			contentsBuilder.append(line).append(" ");
		}
		
		audioManager.play(contentsBuilder.toString());
	}
	
	public void playLine(int lineIndex) {

	}
	
	// Setters
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
		fileType = getFileTypeOf(fullPath);
	}

	public void setEncoding(EncodingType encoding) {
		this.encoding = encoding;
	}
	
	public void tuneAudio(float volume, float rate, float pitch) {
		audioManager.setVolume(volume);
		audioManager.setRate(rate);
		audioManager.setPitch(pitch);
	}
	
	public void setAudioManager(TTSFacade audioManager) {
		this.audioManager = audioManager;
	}
	
	public void addContents(String text) {
		contents.add(text);
	}

	// Getters
	@Override
	public String toString() {
		if (isEmpty()) {
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < contents.size(); i++) {
			if (i!= 0) {
				stringBuilder.append(System.lineSeparator());
			}
			
			stringBuilder.append(contents.get(i));
		}
		
		return stringBuilder.toString();
	}
	
	public TTSFacade getAudioManager() {
		return audioManager;
	}

	public boolean hasSavedPreviously() {
		return fullPath != null && !fullPath.isEmpty() && fileType != null && !fileType.isEmpty();
	}
	
	public boolean isEmpty() {
		return contents == null || contents.size() == 0;
	}
	
	// Helpers
	private void playEmpty() {
		audioManager.play("This is an empty document.");
	}

	private String getFileTypeOf(String path) {
		if (path == null) {
			return "";
		}
		
	    int dotIndex = path.lastIndexOf('.');
	    return (dotIndex == -1) ? "" : path.substring(dotIndex + 1);
	}

	private ReaderDecorator getTheRightReaderDecorator(DocumentReader componentReader) {
		switch (encoding) {
		case ATBASH:
			return new AtbashReaderDecorator(componentReader);
		case ROT_13:
			return new Rot13ReaderDecorator(componentReader);
		case NO_ENCODING:
		default:
			return new NoEncodingReaderDecorator(componentReader);
		}
	}

	private WriterDecorator getTheRightWriterDecorator(DocumentWriter componentWriter) {
		switch (encoding) {
		case ATBASH:
			return new AtbashWriterDecorator(componentWriter);
		case ROT_13:
			return new Rot13WriterDecorator(componentWriter);
		case NO_ENCODING:
		default:
			return new NoEncodingWriterDecorator(componentWriter);
		}
	}
	
}
