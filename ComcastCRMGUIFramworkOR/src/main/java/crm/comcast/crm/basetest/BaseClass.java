package crm.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.OR_Pom.HomePage;
import com.comcast.crm.OR_Pom.LoginPage;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class BaseClass {
	public WebDriver driver;
	
	public static WebDriver sdriver;

	// object creation for utility
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();

	@BeforeSuite(groups = { "smokeTest", "regresionTest" })
	public void configBS() {
		System.out.println("=== Connect to DB , Report Config=====");
	}

	@Parameters("BROWSER")
	@BeforeClass(groups = { "smokeTest", "regresionTest" })
	public void configBC() throws Throwable {
		System.out.println("===Launch the Browser===");
		// String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String BROWSER = System.getProperty("browser", fLib.getDataFromPropertiesFile("browser"));

		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
	}

	@BeforeMethod(groups = { "smokeTest", "regresionTest" })
	public void configBM() throws Throwable {
		System.out.println("====Login===");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(3000);
		lp.loginToappusingurl(URL, USERNAME, PASSWORD);
		Thread.sleep(2000);

	}

	@AfterMethod(groups = { "smokeTest", "regresionTest" })
	public void configAM() throws InterruptedException {
		System.out.println("====LogOut====");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	@AfterClass(groups = { "smokeTest", "regresionTest" })
	public void configAC() {
		System.out.println("=====close the Browser===");
		driver.quit();
	}

	@AfterSuite(groups = { "smokeTest", "regresionTest" })
	public void configAS() {
		System.out.println("====Close DB , report Backup====");
		
	}

}
