package com.comcast.crm.OR_Pom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * @author Urmila
 * 
 * Contains Login page elements and business lib like login()
 */

public class LoginPage{
	   //rule1 create a separate java classs
	  WebDriver driver;
	  //create a constructor
	  public LoginPage(WebDriver driver) {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }
	  
	  // rule2 :object creation
	  
	   @FindBy(name="user_name")
	  private WebElement usernameEdt;
	   
	   @FindBy(name="user_password")
	  private WebElement passwordEdt;
	   
	   @FindBy(id="submitButton")
	  private WebElement loginBtn;
	   
	   
	   //rule 3 : object initilization
	   
	   //rule 4 : object Encapsulation
	   
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	/**
	 * login to application based on username and password
	 * @param username
	 * @param password
	 */
	
	//rule 5: provide Actions
	public void loginToapp(String username,String password) {
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	   
	/**
	 * 
	 * login to application based on url ,username and password
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToappusingurl (String url,String username,String password) {
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	   
	   
}
