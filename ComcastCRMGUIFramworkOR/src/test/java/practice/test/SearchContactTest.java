package practice.test;
/**
 * test class for Contact module
 * @author Urmila 
 */

import org.testng.annotations.Test;

import com.comcast.crm.OR_Pom.LoginPage;

import crm.comcast.crm.basetest.BaseClass;

public class SearchContactTest extends BaseClass {
    /**
     * Scenario : login()====> navigateContact===>createcontact()=====>verify
     */
	@Test
	public void searchcontactTest() {
		/*step1 : login to app */
		LoginPage lp= new LoginPage(driver);
		lp.loginToappusingurl("url","username", "password");
	}
}
