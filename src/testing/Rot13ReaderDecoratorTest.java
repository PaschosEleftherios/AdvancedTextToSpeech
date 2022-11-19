package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import input.DocumentReader;
import input.DocumentReaderFactory;
import input.Rot13ReaderDecorator;

public class Rot13ReaderDecoratorTest {
	
	private String filepath;
	private String docxFilepath;
	private String xlsxFilepath;
	
	private Rot13ReaderDecorator txtDecorator;
	private Rot13ReaderDecorator docxDecorator;
	private Rot13ReaderDecorator xlsxDecorator;

	
	@Before
	public void setUp() throws Exception {
		filepath = "src\\testing\\test_rot13.txt";
		docxFilepath = "src\\testing\\test_rot13.docx";
		xlsxFilepath = "src\\testing\\test_rot13.xlsx";
		
		DocumentReader txtReader = DocumentReaderFactory.createReader("txt");
		DocumentReader docxReader = DocumentReaderFactory.createReader("docx");
		DocumentReader xlsxReader = DocumentReaderFactory.createReader("xlsx");
		
		txtDecorator = new Rot13ReaderDecorator(txtReader);
		docxDecorator = new Rot13ReaderDecorator(docxReader);
		xlsxDecorator = new Rot13ReaderDecorator(xlsxReader);
	}

	@Test
	public void testRead() {
		// Txt
		ArrayList<String> list = txtDecorator.read(filepath);
		assertNotNull(list);
		assertEquals("Test of this class. Works fine.", list.get(0));
		
		//Docx
		ArrayList<String> docxList = docxDecorator.read(docxFilepath);
		assertNotNull(docxList);
		assertEquals("Test of this class. Works fine.", docxList.get(0));
		
		//Xlsx
		ArrayList<String> xlsxList = xlsxDecorator.read(xlsxFilepath);
		assertNotNull(xlsxList);
		assertEquals("Test of this class. Works fine.", xlsxList.get(0));
	}


	@Test
	public void testMapCharacter() {
		assertEquals('n', txtDecorator.mapCharacter('a'));
		assertEquals('o', txtDecorator.mapCharacter('b'));
		assertEquals('p', txtDecorator.mapCharacter('c'));
		assertEquals('q', txtDecorator.mapCharacter('d'));
		assertEquals('r', txtDecorator.mapCharacter('e'));
		assertEquals('s', txtDecorator.mapCharacter('f'));
		assertEquals('t', txtDecorator.mapCharacter('g'));
		assertEquals('u', txtDecorator.mapCharacter('h'));
		assertEquals('v', txtDecorator.mapCharacter('i'));
		assertEquals('w', txtDecorator.mapCharacter('j'));
		assertEquals('x', txtDecorator.mapCharacter('k'));
		assertEquals('y', txtDecorator.mapCharacter('l'));
		assertEquals('z', txtDecorator.mapCharacter('m'));
	
	}


}
