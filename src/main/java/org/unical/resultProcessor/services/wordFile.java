package org.unical.resultProcessor.services;

import java.io.File;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.stereotype.Service;

@Service
public class wordFile {
	
	public WordprocessingMLPackage wordDoc() throws Docx4JException {
		WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
		MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
		mainDocumentPart.addStyledParagraphOfText("Title", "Hello World!");
		mainDocumentPart.addParagraphOfText("Welcome To Baeldung");
		File exportFile = new File("welcome.docx");
		wordPackage.save(exportFile);
		
		return wordPackage;
	}
	
}
