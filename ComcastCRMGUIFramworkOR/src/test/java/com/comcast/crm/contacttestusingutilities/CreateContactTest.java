package com.comcast.crm.contacttestusingutilities;

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
		String contactlastName = eLib.getDataFromExcel("contact", 1, 2);
		
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
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		Thread.sleep(5000);
		//navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		
       //click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//enter all the details and create a contact
		driver.findElement(By.name("lastname")).sendKeys(contactlastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		// Verify the lastname

				String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
				if (actLastName.equals(contactlastName)) {
					System.out.println(contactlastName+ "Information is created==PASS");
				} else {
					System.out.println(contactlastName + "Information is not created==FAIL");
				}
		
		//logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click();
		
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
}
		
	}

