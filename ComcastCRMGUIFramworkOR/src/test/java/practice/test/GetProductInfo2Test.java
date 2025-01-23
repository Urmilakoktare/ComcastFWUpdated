package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfo2Test {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");

		// search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);

		// capture product info
		String x = "//span[text()='" + productName
				+ "']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span[1]/span[2]/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		driver.quit();

	}

	@DataProvider
	public Object[][] getData() throws Throwable {
		ExcelUtility eLib = new ExcelUtility();
		int rowCount = eLib.getRowCount("product");
		Object[][] objArr = new Object[rowCount][2];

		// objArr[0][0]="iphone";
		// objArr[0][1]="Apple iPhone 15 (128 GB) - Black";

		// objArr[1][0]="iphone";
		// objArr[1][1]="iPhone 16 128 GB: 5G Mobile Phone with Camera Control, A18 Chip
		// and a Big Boost in Battery Life. Works with AirPods; Teal";

		// objArr[2][0]="iphone";
		// objArr[2][1]="Apple iPhone 13 (128GB) - Starlight";

		// objArr[3][0]="iphone";
		// objArr[3][1]="Apple iPhone 13 (128GB) - Pink";

		for (int i = 0; i < rowCount; i++) {
			objArr[i][0] = eLib.getDataFromExcel("product", i + 1, 0);
			objArr[i][1] = eLib.getDataFromExcel("product", i + 1, 1);

		}
		return objArr;
	}
}
