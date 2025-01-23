package com.comcast.crm.OR_contact_Pom;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.OR_Pom.ContactPage;
import com.comcast.crm.OR_Pom.CreatingNewOrganization;
import com.comcast.crm.OR_Pom.HomePage;
import com.comcast.crm.OR_Pom.LoginPage;
import com.comcast.crm.OR_Pom.OrganizationPage;
import com.comcast.crm.OR_Pom.creatingNewContact;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactTest {
	public static void main(String[] args) throws Throwable {
		//create a object
		FileUtility fLib= new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		
	    //read common data from properties file
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD= fLib.getDataFromPropertiesFile("password");
		
		
		//read data from excel file
		String lastName = eLib.getDataFromExcel("contact", 1, 2);
		
		WebDriver driver= null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		
		}
		else {
			driver = new ChromeDriver();
		}
		
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

		//enter all the details and create new organization
         creatingNewContact cncp = new creatingNewContact(driver);
         cncp.createContact(lastName);
		
			
		//verify header info of contact
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(lastName))
		   {
			System.out.println(lastName + "is created==PASS");
		     }
			else {
				System.out.println(lastName + "is not created==FAIL");
			}
				
		//logout
		hp.logout();
		driver.quit();

		
	}
}
