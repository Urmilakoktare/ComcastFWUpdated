package com.comcast.crm.OR_Pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganization {
	WebDriver driver;

	public CreatingNewOrganization(WebDriver driver) {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }
	
	@FindBy(name = "accountname")
	private WebElement orgNameEdt;
	
	@FindBy(name = "phone")
	private WebElement phoneEdt;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getPhoneEdt() {
		return phoneEdt;
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	@FindBy(xpath = "//input[@title ='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement industryDD;

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrgWithIndu(String orgName, String industry) {
		orgNameEdt.sendKeys(orgName);
		Select sel = new Select(industryDD);
		sel.selectByVisibleText(industry);
		saveBtn.click();
	}
	
	public void createOrgWithPhone(String orgName, String phone) {
		orgNameEdt.sendKeys(orgName);
		phoneEdt.sendKeys(phone);
		saveBtn.click();
	}
	
	
	
}
