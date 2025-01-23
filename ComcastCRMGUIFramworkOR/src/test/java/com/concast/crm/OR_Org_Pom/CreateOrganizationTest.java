package com.concast.crm.OR_Org_Pom;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.OR_Pom.CreatingNewOrganization;
import com.comcast.crm.OR_Pom.HomePage;
import com.comcast.crm.OR_Pom.LoginPage;
import com.comcast.crm.OR_Pom.OrganizationInfoPage;
import com.comcast.crm.OR_Pom.OrganizationPage;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationTest {
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
				String orgName = eLib.getDataFromExcel("org", 1, 2) +jLib.getRandomNumber();
				
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
						hp.getOrgLink().click();
						
						//step 3 click on organization button
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
											
						//logout
						hp.logout();
						driver.quit();
				
	}
}
