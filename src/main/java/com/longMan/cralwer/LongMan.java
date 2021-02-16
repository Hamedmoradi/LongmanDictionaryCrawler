package com.longMan.cralwer;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class LongMan {
public static void main(String[] args) throws IOException {
//	System.out.println("plaese enter a word:");
//	Scanner scanner = new Scanner(System.in);
//	String word = scanner.next();
	String fileName = "C:\\Users\\USER\\Desktop\\asd.doc";
	String inFileName = "C:\\Users\\USER\\Desktop\\qaz.doc";
	FileOutputStream out = new FileOutputStream(fileName);
	FileOutputStream in = new FileOutputStream(inFileName);
	String asdasd ;
	XWPFDocument doc = new XWPFDocument();
	XWPFDocument ambugityWords = new XWPFDocument();
	try {
		
		List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\USER\\Desktop\\asd.txt"));
		for (String line : lines) {
			// ...
			
			
			String url = "https://www.ldoceonline.com/dictionary/".concat(line);
//	System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Desktop\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\USER\\Desktop\\geckodriver.exe");
//	WebDriver driver = new ChromeDriver();
			WebDriver driver = new FirefoxDriver();
			driver.get(url);
			driver.manage().window().maximize();
			String content = driver.getPageSource();
			if (driver.findElement(By.id("onetrust-accept-btn-handler")).isDisplayed()) {
				driver.findElement(By.id("onetrust-accept-btn-handler")).click();
			}
			if (line.equals("")) {
				driver.close();
				break;
			}
			if (driver.getCurrentUrl().contains("https://www.ldoceonline.com/spellcheck/english/?")) {
				driver.close();
				asdasd=line;
				if (!asdasd.isEmpty()) {
					XWPFParagraph pn = ambugityWords.createParagraph();
					pn.setAlignment(ParagraphAlignment.LEFT);
					// set font
					XWPFRun rn = pn.createRun();
					rn.setBold(true);
					rn.setFontSize(19);
					rn.setFontFamily("Arial");
					rn.setColor("ff0000");
//					for (int i = 0; i < arrayList.size(); i++) {
						
						rn.setText(asdasd);
						
//					}
					rn.addBreak();
				}
			} else {
				String pronounceCode = driver.findElement(By.className("PronCodes")).getText();
				List<WebElement> englishDefinitionElements = driver.findElements(By.className("DEF"));
				ArrayList<String> englishDefinition = new ArrayList<>();
				for (int i = 0; i < englishDefinitionElements.size(); i++) {
					englishDefinition.add(englishDefinitionElements.get(i).getText());
				}
				driver.get("https://translate.google.com/?sl=en&tl=fa&" + "text=" + line + "+&op=translate");
				String persianMainDefinition = driver.findElement(By.className("J0lOec")).getText();
				List<WebElement> otherPersianMeaningElements = driver.findElements(By.className("kgnlhe"));
				ArrayList<String> otherPersianMeaning = new ArrayList<>();
				for (int i = 0; i < otherPersianMeaningElements.size(); i++) {
					otherPersianMeaning.add(otherPersianMeaningElements.get(i).getText());
				}
				System.out.println(line);
				System.out.println(persianMainDefinition);
				System.out.println(otherPersianMeaning);
				
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
				r1.setText(line);
				r1.addTab();
				XWPFRun r2 = p1.createRun();
//		r2.setBold(true);
//		r2.setItalic(true);
				r2.setFontSize(12);
				r2.setFontFamily("Arial");
				r2.setColor("000000");
				r2.setText(pronounceCode);
				r2.addTab();
				XWPFRun r = p1.createRun();
				r.setText(persianMainDefinition);
				r.addBreak();
				XWPFRun r3 = p1.createRun();
				r3.setBold(true);
//		r3.setItalic(true);
				r3.setFontSize(12);
				r3.setFontFamily("Arial");
				r3.setColor("000000");
				for (int i = 0; i < englishDefinition.size(); i++) {
					r3.setText(i + 1 + " ");
					r3.setText(englishDefinition.get(i));
					r3.addBreak();
				}
				XWPFParagraph p2 = doc.createParagraph();
				p2.setAlignment(ParagraphAlignment.RIGHT);
				CTP ctp = p2.getCTP();
				CTPPr ctppr;
				if ((ctppr = ctp.getPPr()) == null) ctppr = ctp.addNewPPr();
				ctppr.addNewBidi().setVal(STOnOff.ON);
				XWPFRun r4 = p2.createRun();
				r4.setBold(true);
				r4.setFontSize(12);
				r4.setFontFamily("Calibri");
				r4.setColor("000000");
				for (int i = 0; i < otherPersianMeaning.size(); i++) {
					r4.setText(otherPersianMeaning.get(i));
					if (i + 1 < otherPersianMeaning.size()) {
						r4.setText("،");
						
					}
				}
				r4.addBreak();
				// save it to .docx file
				
				
				driver.quit();
				
			}
			
		}
	} finally {
		
		doc.write(out);
		ambugityWords.write(in);
		doc.close();
	}
}
}


