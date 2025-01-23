package com.comcast.crm.OR_Pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	 WebDriver driver;
	   public ContactPage(WebDriver driver) {
			  this.driver=driver;
			  PageFactory.initElements(driver, this);
		  }
	   
	   @FindBy(xpath = "//img[@alt='Create Contact...']")
		private WebElement createNewcontactBtn;
	   
	   @FindBy(name = "search_text")
		private WebElement searchEdt;
	   
	   @FindBy(name = "search_field")
		private WebElement searchDroD;
		
	   @FindBy(name = "submit")
	   private WebElement searchBtn;
	   
	public WebElement getCreateNewcontactBtn() {
		return createNewcontactBtn;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDroD() {
		return searchDroD;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
		
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement actStartDate;
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement actEndDate;
	public WebElement getActStartDate() {
		return actStartDate;
	}

	public void setActStartDate(WebElement actStartDate) {
		this.actStartDate = actStartDate;
	}

	public WebElement getActEndDate() {
		return actEndDate;
	}

	public void setActEndDate(WebElement actEndDate) {
		this.actEndDate = actEndDate;
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	
	
	
		
}
