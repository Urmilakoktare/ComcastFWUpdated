package com.comcast.crm.batchexcreatecontact;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.comcast.crm.OR_Pom.ContactPage;
import com.comcast.crm.OR_Pom.HomePage;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

import crm.comcast.crm.basetest.BaseClass;

public class CreateContactTest extends BaseClass{
	@Test
	public void createContactTest() throws Throwable {
		
		//read data from excel file
		String lastName = eLib.getDataFromExcel("contact", 1, 2) +jLib.getRandomNumber();
		
		// step 2 navigate the organization module
				HomePage hp= new HomePage(driver);
				hp.getContactlink().click();
				
		//step 3 click on create contact button
				ContactPage cp = new ContactPage(driver);
			    cp.getCreateNewcontactBtn().click();
			    
	   
				
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
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				
				driver.findElement(By.linkText("Sign Out")).click();
		       
				driver.quit();
		
		
	}
}
