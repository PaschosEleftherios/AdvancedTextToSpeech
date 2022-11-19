package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import input.DocumentReaderFactory;
import output.*;

public class Rot13WriterDecoratorTest {

	private String textToWrite;
	private String textToExpect;
	
	private String txtFilepath;
	private String docxFilepath;
	private String xlsxFilepath;
	
	private Rot13WriterDecorator txtDecorator;
	private Rot13WriterDecorator docxDecorator;
	private Rot13WriterDecorator xlsxDecorator;
	
	@Before
	public void setUp() throws Exception {
		txtFilepath = "src\\testing\\test_rot13.txt";
		docxFilepath = "src\\testing\\test_rot13.docx";
		xlsxFilepath = "src\\testing\\test_rot13.xlsx";
		
		DocumentWriter txtWriter = DocumentWriterFactory.createWriter("txt");
		DocumentWriter docxWriter = DocumentWriterFactory.createWriter("docx");
		DocumentWriter xlsxWriter = DocumentWriterFactory.createWriter("xlsx");
		
		txtDecorator = new Rot13WriterDecorator(txtWriter);
		docxDecorator = new Rot13WriterDecorator(docxWriter);
		xlsxDecorator = new Rot13WriterDecorator(xlsxWriter);
		
		textToWrite = "Test of this class. Works fine.";
		textToExpect = "Grfg bs guvf pynff. Jbexf svar.";
	}

	@Test
	public void testWrite() {
		ArrayList<String> content = new ArrayList<String>();
		content.add(textToWrite);
		
		// Txt
		txtDecorator.write(content, txtFilepath);
		assertEquals(textToExpect, DocumentReaderFactory.createReader("txt").read(txtFilepath).get(0));
		
		// Docx
		docxDecorator.write(content, docxFilepath);
		assertEquals(textToExpect, DocumentReaderFactory.createReader("docx").read(docxFilepath).get(0));
		
		// Xlsx
		xlsxDecorator.write(content, xlsxFilepath);
		assertEquals(textToExpect, DocumentReaderFactory.createReader("xlsx").read(xlsxFilepath).get(0));
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
