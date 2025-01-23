package com.comcast.crm.OR_Pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	 WebDriver driver;

	public HomePage(WebDriver driver) {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }

   @FindBy(linkText = "Organizations")
   private WebElement orgLink;
   
   @FindBy(linkText = "Contacts")
   private WebElement contactlink;
   
   @FindBy(linkText = "Products")
   private WebElement productlink;
   
   @FindBy(linkText = "Campaigns")
   private WebElement Campaignslink;
   
   @FindBy(linkText = "More")
   private WebElement morelink;
   
   @FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
   private WebElement adminImg;
   
   @FindBy(linkText = "Sign Out")
   private WebElement SignOutlink;
   
    public WebElement getOrgLink() {
	return orgLink;
     }

    public WebElement getContactlink() {
	return contactlink;
    }
    

	public WebElement getCampaignslink() {
		return Campaignslink;
	}

	public WebElement getMorelink() {
		return morelink;
	}
	

	public WebElement getAdminImg() {
		return adminImg;
	}
	
	

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getProductlink() {
		return productlink;
	}

	public WebElement getSignOutlink() {
		return SignOutlink;
	}

	public void navigateToCampaignPage() {
    	Actions act = new Actions(driver);
    	act.moveToElement(morelink).perform();
    	Campaignslink.click();
    }
	
	public void navigateToContactPage() {
    	Actions act = new Actions(driver);
    	act.moveToElement(contactlink).perform();
    	Campaignslink.click();
    }
	
	public void logout() throws InterruptedException {
		Thread.sleep(4000);
		Actions act = new Actions(driver);
		act.moveToElement(adminImg).perform();
		SignOutlink.click();
	}
   
  
}
