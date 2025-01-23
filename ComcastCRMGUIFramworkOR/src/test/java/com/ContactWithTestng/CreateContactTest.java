package com.ContactWithTestng;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.OR_Pom.ContactPage;
import com.comcast.crm.OR_Pom.CreatingNewOrganization;
import com.comcast.crm.OR_Pom.HomePage;
import com.comcast.crm.OR_Pom.OrganizationPage;
import com.comcast.crm.OR_Pom.creatingNewContact;

import crm.comcast.crm.basetest.BaseClass;

/**
 * 
 * @author Urmila koktare
 */
public class CreateContactTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void CreateContact() throws Throwable {
		/* read testScript data from excelFile */

		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		/* navigate to Contact module */
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();

		// click on create Contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewcontactBtn().click();

		// enter all the details and create new organization
		creatingNewContact cnp = new creatingNewContact(driver);
		cnp.createContact(lastName);

		// verify header lastname Expected Result

		String actLastName = driver.findElement(By.id("mouseArea_Last Name")).getText();
		if (actLastName.contains(lastName)) {
			System.out.println(lastName + " is created==PASS");
		} else {
			System.out.println(lastName + " is not created==FAIL");
		}

	}

	@Test(groups = "regresionTest")
	public void CreateContactWithSupportDate() throws Throwable {
		// read testScript data from excelFile
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		// navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();

		// click on create Contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewcontactBtn().click();

		// enter all the details and create new organization

		String startDate = jLib.getSystemDateYYYYDDMM();
		String EndDate = jLib.getRequiredDateYYYYDDMM(30);

		System.out.println(EndDate);
		System.out.println(startDate);

		creatingNewContact cnp = new creatingNewContact(driver);
		cnp.createContact(lastName, startDate, EndDate);

		// verify startdate and enddate in Expected Result
		Thread.sleep(3000);
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actStartDate.contains(startDate)) {
			System.out.println(startDate + " is created==PASS");
		} else {
			System.out.println(startDate + " is not created==FAIL");
		}

		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		System.out.println(EndDate);
		System.out.println(actEndDate);
		if (actEndDate.contains(EndDate)) {
			System.out.println(EndDate + " is created==PASS");
		} else {
			System.out.println(EndDate + " is not created==FAIL");
		}

	}

	@Test(groups = "regresionTest")
	public void CreateContactWithOrg() throws Throwable {
		// read testScript data from excelFile
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organisation button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all the details and create new organization

		CreatingNewOrganization cnop = new CreatingNewOrganization(driver);
		cnop.createOrg(orgName);

		// verify header msg Expected Result
		Thread.sleep(3000);
		String headerMsg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerMsg.contains(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}

		// navigate to Contact module
		hp.getContactlink().click();

		// click on create Contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewcontactBtn().click();

		// enter all the details and create new organization
		creatingNewContact cnp = new creatingNewContact(driver);
		cnp.createContact(lastName);

		// verify orgname info Expected Result
		Thread.sleep(3000);
		String actualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actualOrgName);
		if (actualOrgName.trim().equals(orgName))// trim for ignoring the space
		{
			System.out.println(orgName + " is matching==PASS");
		} else {
			System.out.println(orgName + " is not matching==FAIL");
		}

	}
}
