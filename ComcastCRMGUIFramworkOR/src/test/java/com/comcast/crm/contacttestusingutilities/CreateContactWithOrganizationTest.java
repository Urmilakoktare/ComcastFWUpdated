package com.comcast.crm.contacttestusingutilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrganizationTest {
	public static void main(String[] args) throws Throwable {
		// create a object
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		// read common data from properties file
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		// read data from excel file
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String ContactLastName = eLib.getDataFromExcel("contact", 7, 3);

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

		wLib.waitForPageToLoad(driver);
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// step 2 navigate the organization module
		driver.findElement(By.partialLinkText("Organizations")).click();

		// step 3 click on organization button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		// enter all the details and create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify Header msg expected Result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "is created==PASS");
		} else {
			System.out.println(orgName + "is not created==FAIL");
		}

		Thread.sleep(5000);
		// navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();

		// click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// enter all the details and create a contact
		driver.findElement(By.name("lastname")).sendKeys(ContactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// switch to child window
		wLib.switchToTabOnUrl(driver, "module=Accounts");

		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click(); // dynamic xpath

		// switch to parent window
		wLib.switchToTabOnUrl(driver, "Contacts&action");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify the header information

		headerInfo = driver.findElement(By.xpath("//span[@class='tvHeaderText']")).getText();
		if (headerInfo.equals(orgName)) {
			System.out.println(orgName + "headerInfo is created==PASS");
		} else {
			System.out.println(orgName + "headerInfo is not created==FAIL");
		}

		// Verify Header orgName info Expected Result
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (actOrgName.equals(orgName)) {
			System.out.println(orgName + "Information is created==PASS");
		} else {
			System.out.println(orgName + "Information is not created==FAIL");
		}

		// logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click();

		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
}
