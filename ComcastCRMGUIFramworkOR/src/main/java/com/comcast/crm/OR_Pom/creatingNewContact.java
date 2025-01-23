package com.comcast.crm.OR_Pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class creatingNewContact {
	WebDriver driver;

	public creatingNewContact(WebDriver driver) {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }
	WebDriverUtility wLib = new WebDriverUtility();
	
	@FindBy(name = "firstname")
	private WebElement firstNameEdt;
	
	@FindBy(name = "lastname")
	private WebElement lastNameEdt;
	
	@FindBy(name = "account_name")
	private WebElement orgNameEdt;
	
	@FindBy(xpath = "//input[@name='account_name']//following-sibling::img")
	private WebElement orgNameImg;
	
	@FindBy(name = "mobile")
	private WebElement phoneEdt;
	
	 @FindBy(name = "search_text")
	private WebElement orgSearchEdt;
	   
	 @FindBy(name = "search_field")
     private WebElement orgSearchDD;
	 
	 @FindBy(name = "search")
	 private WebElement searchBtn;
	   
	
	@FindBy(name ="support_start_date")
	private WebElement startDateEdt;
	
	@FindBy(name ="support_end_date")
	private WebElement endDateEdt;
	
	@FindBy(xpath = "//input[@title ='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath ="//span[@class='dvHeaderText']")
	private WebElement headerMsgcontact;
	
	
	
	public WebDriver getDriver() {
		return driver;
	}


	public WebDriverUtility getwLib() {
		return wLib;
	}


	public WebElement getFirstNameEdt() {
		return firstNameEdt;
	}


	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}


	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}


	public WebElement getOrgNameImg() {
		return orgNameImg;
	}


	public WebElement getPhoneEdt() {
		return phoneEdt;
	}


	public WebElement getOrgSearchEdt() {
		return orgSearchEdt;
	}


	public WebElement getOrgSearchDD() {
		return orgSearchDD;
	}


	public WebElement getSearchBtn() {
		return searchBtn;
	}


	public WebElement getStartDateEdt() {
		return startDateEdt;
	}


	public WebElement getEndDateEdt() {
		return endDateEdt;
	}


	public WebElement getSaveBtn() {
		return saveBtn;
	}


	public WebElement getHeaderMsgcontact() {
		return headerMsgcontact;
	}


	public void createContact(String lastName) {
		lastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}
	
	
	public void createContactWithOrg(String lastName, String orgName) {
		
		lastNameEdt.sendKeys(lastName);
		orgNameEdt.sendKeys(orgName);
		orgNameImg.click();
		//switch to child window
		wLib.switchToTabOnUrl(driver, "Accounts&action");
		orgSearchEdt.sendKeys(orgName);
		orgSearchDD.click();
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		//switch to parent window
		wLib.switchToTabOnUrl(driver, "Contacts&action");
		saveBtn.click();
		
	}
	
	public void createContactWithphone(String lastName, String phoneNum) {
		lastNameEdt.sendKeys(lastName);
		phoneEdt.sendKeys(phoneNum);
		saveBtn.click();
	}
	
	public void createContact(String lastName, String startDate, String endDate) throws InterruptedException {
		lastNameEdt.sendKeys(lastName);
		startDateEdt.clear();
		Thread.sleep(3000);
		startDateEdt.sendKeys(startDate);
		Thread.sleep(3000);
		endDateEdt.clear();
		Thread.sleep(3000);
		endDateEdt.sendKeys(endDate);
		saveBtn.click();
	}
	
	
	
}
