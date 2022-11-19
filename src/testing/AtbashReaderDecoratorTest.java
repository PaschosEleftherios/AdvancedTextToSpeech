package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import input.DocumentReader;
import input.DocumentReaderFactory;
import input.AtbashReaderDecorator;

public class AtbashReaderDecoratorTest {
	
	private String txtFilepath;
	private String docxFilepath;
	private String xlsxFilepath;
	
	private AtbashReaderDecorator txtDecorator;
	private AtbashReaderDecorator docxDecorator;
	private AtbashReaderDecorator xlsxDecorator;

	@Before
	public void setUp() throws Exception {
		txtFilepath = "src\\testing\\test_atbash.txt";
		docxFilepath = "src\\testing\\test_atbash.docx";
		xlsxFilepath = "src\\testing\\test_atbash.xlsx";
		
		DocumentReader txtReader = DocumentReaderFactory.createReader("txt");
		DocumentReader docxReader = DocumentReaderFactory.createReader("docx");
		DocumentReader xlsxReader = DocumentReaderFactory.createReader("xlsx");
		
		txtDecorator = new AtbashReaderDecorator(txtReader);
		docxDecorator = new AtbashReaderDecorator(docxReader);
		xlsxDecorator = new AtbashReaderDecorator(xlsxReader);
	}

	@Test
	public void testRead() {
		// Txt
		ArrayList<String> list = txtDecorator.read(txtFilepath);
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
		assertEquals('z', txtDecorator.mapCharacter('a'));
		assertEquals('y', txtDecorator.mapCharacter('b'));
		assertEquals('x', txtDecorator.mapCharacter('c'));
		assertEquals('w', txtDecorator.mapCharacter('d'));
		assertEquals('v', txtDecorator.mapCharacter('e'));
		assertEquals('u', txtDecorator.mapCharacter('f'));
		assertEquals('t', txtDecorator.mapCharacter('g'));
		assertEquals('s', txtDecorator.mapCharacter('h'));
		assertEquals('r', txtDecorator.mapCharacter('i'));
		assertEquals('q', txtDecorator.mapCharacter('j'));
		assertEquals('p', txtDecorator.mapCharacter('k'));
		assertEquals('o', txtDecorator.mapCharacter('l'));
		assertEquals('n', txtDecorator.mapCharacter('m'));
	}

}
