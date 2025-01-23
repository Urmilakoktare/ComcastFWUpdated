package com.comcast.crm.OR_contact_Pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.OR_Pom.ContactPage;
import com.comcast.crm.OR_Pom.CreatingNewOrganization;
import com.comcast.crm.OR_Pom.HomePage;
import com.comcast.crm.OR_Pom.LoginPage;
import com.comcast.crm.OR_Pom.OrganizationInfoPage;
import com.comcast.crm.OR_Pom.OrganizationPage;
import com.comcast.crm.OR_Pom.creatingNewContact;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrganizationTest {
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
	String orgName = eLib.getDataFromExcel("contact", 7, 2) +jLib.getRandomNumber();
	String lastName =eLib.getDataFromExcel("contact", 7, 3);
		
			
			WebDriver driver = null;
			
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
	    	
			wLib.waitForPageToLoad(driver);
	    	driver.get(URL);
	    	
	    	LoginPage lp =new LoginPage(driver);
		    
		    lp.loginToapp("admin", "admin");
	    	
	    	//navigate the organization module
			HomePage hp= new HomePage(driver);
			hp.getOrgLink().click();
			
			// click on organization button
             OrganizationPage cnp = new OrganizationPage(driver);
             cnp.getCreateNewOrgBtn().click();

			//enter all the details and create new organization
             CreatingNewOrganization cnop = new CreatingNewOrganization(driver);
             cnop.createOrg(orgName);
			
			
			//verify Header msg expected Result
			OrganizationInfoPage oip = new OrganizationInfoPage(driver);
			String actOrgName = oip.getHeaderMsg().getText();
			if(actOrgName.contains(orgName)) {
				System.out.println(orgName + "name is verified ==PASS");
			}else {
				System.out.println(orgName + "name is verified ==FAIL");
			}
	
			//navigate the organization module
			
			hp.getContactlink().click();
			
			// click on organization button
	         ContactPage cp = new ContactPage(driver);
	         cp.getCreateNewcontactBtn().click();

			//enter all the details and create new organization
	         creatingNewContact cncp = new creatingNewContact(driver);
	         cncp.createContactWithOrg(lastName, orgName);
			
				
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
