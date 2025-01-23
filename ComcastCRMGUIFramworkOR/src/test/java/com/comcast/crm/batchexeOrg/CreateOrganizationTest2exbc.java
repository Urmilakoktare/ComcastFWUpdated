package com.comcast.crm.batchexeOrg;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.OR_Pom.CreatingNewOrganization;
import com.comcast.crm.OR_Pom.HomePage;
import com.comcast.crm.OR_Pom.OrganizationInfoPage;
import com.comcast.crm.OR_Pom.OrganizationPage;

import crm.comcast.crm.basetest.BaseClass;


public class CreateOrganizationTest2exbc extends BaseClass {
	
	@Test(groups ="smokeTest")
	public void createOrgTest() throws Throwable {
		
		// read data from excel file
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		// step 2 navigate the organization module
		HomePage hp= new HomePage(driver);
		hp.getOrgLink().click();
		
		//step 3 click on organization button
         OrganizationPage cnp = new OrganizationPage(driver);
         cnp.getCreateNewOrgBtn().click();

		//enter all the details and create new organization
         CreatingNewOrganization cnop = new CreatingNewOrganization(driver);
         cnop.createOrg(orgName);
         
         Thread.sleep(4000);
		
		
		//verify Header msg expected Result
		//OrganizationInfoPage oip = new OrganizationInfoPage(driver);
         //String actOrgName = oip.getHeaderMsg().getText();
		String actOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName + "name is verified ==PASS");
		}else {
			System.out.println(orgName + "name is verified ==FAIL");
		}
					

	}
}
