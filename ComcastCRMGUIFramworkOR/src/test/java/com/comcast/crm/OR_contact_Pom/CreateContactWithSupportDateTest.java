package com.comcast.crm.OR_contact_Pom;

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

import com.comcast.crm.OR_Pom.ContactPage;
import com.comcast.crm.OR_Pom.HomePage;
import com.comcast.crm.OR_Pom.LoginPage;
import com.comcast.crm.OR_Pom.creatingNewContact;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithSupportDateTest {
	public static void main(String[] args) throws Throwable {
		//create a object
		FileUtility fLib= new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib= new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
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

		// Login to app
		 //step1 Login to application
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
	    LoginPage lp =new LoginPage(driver);
	    
	    lp.loginToapp("admin", "admin");
	    
		//step 2 navigate the organization module
		HomePage hp= new HomePage(driver);
		hp.getContactlink().click();
		
		//step 3 click on organization button
         ContactPage cp = new ContactPage(driver);
         cp.getCreateNewcontactBtn().click();
         
         //Enter all details and create an contact
         String startDate = jLib.getSystemDateYYYYDDMM();
         String endDate = jLib.getRequiredDateYYYYDDMM(30);
         System.out.println(startDate);
         System.out.println(endDate);
         
		//enter all the details and create new organization
         creatingNewContact cncp = new creatingNewContact(driver);
         cncp.createContact(lastName, startDate, endDate);
         
         Thread.sleep(3000);
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
		
	    Thread.sleep(3000);
		// logout
        hp.logout();
		driver.quit();
	}

}
