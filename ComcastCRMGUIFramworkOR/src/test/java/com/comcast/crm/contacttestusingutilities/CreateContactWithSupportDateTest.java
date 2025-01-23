package com.comcast.crm.contacttestusingutilities;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDateTest {
	public static void main(String[] args) throws Throwable {
		//create a object
		FileUtility fLib= new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib= new JavaUtility();
		
	    //read common data from properties file
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD= fLib.getDataFromPropertiesFile("password");
		
		//read data from excel file
		String lastName = eLib.getDataFromExcel("org", 4, 2) +jLib.getRandomNumber();
		

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();

		} else {
			driver = new ChromeDriver();
		}

		// step1 Login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
        //navigate to contact module
		driver.findElement(By.partialLinkText("Contacts")).click();
		
       //click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
       //Enter all the details to create a contact
		
		//get system date format
		String startDate= jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);
		
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify the lastDate

		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actLastName.equals(lastName)) {
			System.out.println(lastName+ "Information is created==PASS");
		} else {
			System.out.println(lastName + "Information is not created==FAIL");
		}

		// Verify the startDate

				String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
				if (actStartDate.equals(startDate)) {
					System.out.println(startDate+ "Information is created==PASS");
				} else {
					System.out.println(startDate + "Information is not created==FAIL");
				}	
				
				// Verify the endDate

				String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
				if (actEndDate.equals(endDate)) {
					System.out.println(endDate+ "Information is created==PASS");
				} else {
					System.out.println(endDate + "Information is not created==FAIL");
				}					
		
		// logout
		Thread.sleep(3000);

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();

		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
