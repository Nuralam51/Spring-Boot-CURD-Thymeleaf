package com.example.StudentDiary.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Phaser;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.UnderlineAction;

import com.example.StudentDiary.entity.Student;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class StudentPdfExporter {

	private List<Student> theStudent;

	public StudentPdfExporter(List<Student> theStudent) {
		this.theStudent = theStudent;
	}
	
	private void tableHeader(PdfPTable theTable) {
		PdfPCell theCell = new PdfPCell();
		theCell.setBackgroundColor(Color.BLACK);
		theCell.setHorizontalAlignment(1);
		
		Font theFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		theFont.setColor(Color.WHITE);
		theFont.setSize(10);
		
		theCell.setPhrase(new Phrase("First Name", theFont));
		theTable.addCell(theCell);
		
		theCell.setPhrase(new Phrase("Last Name", theFont));
		theTable.addCell(theCell);

		theCell.setPhrase(new Phrase("Email", theFont));
		theTable.addCell(theCell);
		
		theCell.setPhrase(new Phrase("ID", theFont));
		theTable.addCell(theCell);
	}
	
	private void tableData(PdfPTable theTable) {
		Font theFont = FontFactory.getFont(FontFactory.HELVETICA);
		theFont.setSize(9);
		for (Student student : theStudent) {
			theTable.addCell(new PdfPCell(new Phrase(student.getFirstName(), theFont)));
			theTable.addCell(new PdfPCell(new Phrase(student.getLastName(), theFont)));
			theTable.addCell(new PdfPCell(new Phrase(student.getEmail(), theFont)));
			theTable.addCell(new PdfPCell(new Phrase(student.getRoll(), theFont)));
		}
	}
	
	public void exportPdf(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font theFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
		theFont.setSize(16);
		Paragraph theTitle = new Paragraph("Student Diary", theFont);
		
		Font theSubFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
		theSubFont.setSize(11);
		Paragraph theSubTitle = new Paragraph("List of all students", theSubFont);
		
		theTitle.setAlignment(Paragraph.ALIGN_CENTER);
		theSubTitle.setAlignment(Paragraph.ALIGN_CENTER);
		theSubTitle.setSpacingAfter(7);
		
		document.add(theTitle);
		document.add(theSubTitle);
		
		PdfPTable theTable = new PdfPTable(4);
		theTable.setWidthPercentage(100);
		theTable.setSpacingBefore(10);
		
		tableHeader(theTable);
		tableData(theTable);
		
		document.add(theTable);
		
		document.close();
	}
	
}
