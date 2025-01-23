package com.comcast.Createcontac_AssertTest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.OR_Pom.CreatingNewOrganization;
import com.comcast.crm.OR_Pom.HomePage;
import com.comcast.crm.OR_Pom.OrganizationPage;

import crm.comcast.crm.basetest.BaseClass;

public class CreateOrganizationTest extends BaseClass {
	@Test(groups = "smokeTest")
	public void createOrgTest() throws Throwable {
		// read testScript data from excelFile

		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

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

		// verify header orgname info Expected Result

		String actualOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();

		if (actualOrgName.equals(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}
	}

	@Test(groups = "regresionTest")
	public void creatOrgWithInd() throws Throwable {
		// read testScript data from excelFile
		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organisation button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all the details and create new organization

		CreatingNewOrganization cnop = new CreatingNewOrganization(driver);
		cnop.createOrgWithIndu(orgName, industry);

		// verify the industry and type info
		Thread.sleep(3000);
		String actualIndustryDd = driver.findElement(By.id("mouseArea_Industry")).getText();

		if (actualIndustryDd.equals(industry)) {

			System.out.println(actualIndustryDd + " is verified==>PASS");
		} else {

			System.out.println(actualIndustryDd + " is not  verified==>FAIL");
		}

	}

	@Test(groups = "regresionTest")
	public void createOrgWithPhoneNumber() throws Throwable {
		// read testScript data from excelFile

		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organisation button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all the details and create new organization

		CreatingNewOrganization cnop = new CreatingNewOrganization(driver);
		cnop.createOrgWithPhone(orgName, phoneNumber);

		// verify header phonenumber Expected Result
		Thread.sleep(3000);
		String phoneNumberTxt = driver.findElement(By.id("mouseArea_Phone")).getText();
		if (phoneNumberTxt.contains(phoneNumber)) {
			System.out.println(phoneNumber + " is created==PASS");
		} else {
			System.out.println(phoneNumberTxt + " is not created==FAIL");
		}

	}
}
