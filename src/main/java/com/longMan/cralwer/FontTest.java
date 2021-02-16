package com.longMan.cralwer;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.xml.transform.Source;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;

public class FontTest {
public static void main(String[] args) throws IOException, FontFormatException {
	
	String fileName = "C:\\Users\\USER\\Desktop\\asd.doc";
	
	try (XWPFDocument doc = new XWPFDocument()) {
		
		// create a paragraph
		XWPFParagraph p1 = doc.createParagraph();
		p1.setAlignment(ParagraphAlignment.LEFT);
		
		// set font
		XWPFRun r1 = p1.createRun();
		r1.setBold(true);
//		r1.setItalic(true);
		r1.setFontSize(19);
		r1.setFontFamily("Arial");
		r1.setColor("ff0000");
		r1.setText("I am first paragraph.");
		r1.addTab();
		XWPFRun r2 = p1.createRun();
//		r2.setBold(true);
//		r2.setItalic(true);
		r2.setFontSize(12);
		r2.setFontFamily("Arial");
		r2.setColor("000000");
		r2.setText("asd");
		r2.addBreak();
		
		XWPFRun r3 = p1.createRun();
		r3.setBold(true);
//		r3.setItalic(true);
		r3.setFontSize(12);
		r3.setFontFamily("Arial");
		r3.setColor("000000");
		r3.setText("qaz");
		r3.addBreak();
		// save it to .docx file
		try (FileOutputStream out = new FileOutputStream(fileName)) {
			doc.write(out);
		}
		
	}
}
}