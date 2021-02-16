package com.longMan.cralwer;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.*;

public class Inverseschool {


public static void main(String[] args) throws  IOException {
	String url = "https://inverseschool.com/inverse-world/teachers";
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Desktop\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	// Navigate to URL
	driver.get(url);
	driver.manage().window().maximize();
	// Read page content
	String content = driver.getPageSource();
	// Print the page content
	
	List<WebElement> elements = driver.findElements(By.tagName("button"));
	for (int i = 0; i < elements.size(); i++) {
		String title = elements.get(i).getText();
		if (title.equals("نمایش بیشتر")) {
			System.out.println("****************************************RESULT************************************************");
			try {
				elements.get(i).click();
				for (int j = 0; j < 20; j++) {
					JavascriptExecutor js = ((JavascriptExecutor) driver);
					js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
					Thread.sleep(5000);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Assume driver is a valid WebDriver instance that
			// has been properly instantiated elsewhere.
		}
	}
	List<WebElement> teacherLinks = driver.findElements(By.tagName("a"));
	Map<String, String> teachersLinks = new HashMap<>();
	ArrayList<String> list = new ArrayList<>();
	for (int i = 0; i < teacherLinks.size(); i++) {
		if (teacherLinks.get(i).getAttribute("href").contains("https://inverseschool.com/inverse-world/teachers/")) {
			removeDuplicate(list);
		} else {
			continue;
		}
		teachersLinks.put(teacherLinks.get(i).getAttribute("href"), teacherLinks.get(i).getText());
		
	}
	
	printMap(teachersLinks);
	driver.quit();
}


public static void removeDuplicate(ArrayList<String> list) {
	int i = list.size() - 1;
	
	while (i > -1) {
		// check for duplicate
		for (int j = 0; j < i; j++) {
			if (list.get(i) == list.get(j)) {
				// is duplicate: remove
				list.remove(i);
				break;
			}
		}
		i--;
	}
	
}

public static void printMap(Map mp) {
	
	Iterator it = mp.entrySet().iterator();
	int counter = 1;
	while (it.hasNext()) {
		Map.Entry pair = (Map.Entry) it.next();
		System.out.println(counter + " ::::: " + pair.getKey() + " = " + pair.getValue());
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Desktop\\chromedriver.exe");
		WebDriver driver2 = new ChromeDriver();
		driver2.get((String) pair.getKey());
		
		System.out.println("=====================================================");
		
		if (driver2.findElement(By.cssSelector(".container.mt-5")).getText() != null) {
			System.out.println(driver2.findElement(By.cssSelector(".container.mt-5")).getText());
			System.out.println("=====================================================");
		} else {
		}
		counter++;
		driver2.quit();
	}
	
}
}


