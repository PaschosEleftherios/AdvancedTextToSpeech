package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import input.DocumentReaderFactory;
import output.AtbashWriterDecorator;
import output.DocumentWriter;
import output.DocumentWriterFactory;

public class AtbashWriterDecoratorTest {

	private String textToWrite;
	private String textToExpect;
	
	private String txtFilepath;
	private String docxFilepath;
	private String xlsxFilepath;
	
	private AtbashWriterDecorator txtDecorator;
	private AtbashWriterDecorator docxDecorator;
	private AtbashWriterDecorator xlsxDecorator;
	
	@Before
	public void setUp() throws Exception {
		txtFilepath = "src\\testing\\test_atbash.txt";
		docxFilepath = "src\\testing\\test_atbash.docx";
		xlsxFilepath = "src\\testing\\test_atbash.xlsx";
		
		DocumentWriter txtWriter = DocumentWriterFactory.createWriter("txt");
		DocumentWriter docxWriter = DocumentWriterFactory.createWriter("docx");
		DocumentWriter xlsxWriter = DocumentWriterFactory.createWriter("xlsx");
		
		txtDecorator = new AtbashWriterDecorator(txtWriter);
		docxDecorator = new AtbashWriterDecorator(docxWriter);
		xlsxDecorator = new AtbashWriterDecorator(xlsxWriter);
		
		textToWrite = "Test of this class. Works fine.";
		textToExpect = "Gvhg lu gsrh xozhh. Dliph urmv.";
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
