package practice.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import crm.comcast.crm.basetest.BaseClass;


public class InvoiceNetworkTest extends BaseClass {
 
	@Test(retryAnalyzer = com.comcast.crm.listenersUtility.RetryListenerImp.class)
	public void activateSim() {
		System.out.println("execute CreateInvoiceTest");
		Assert.assertEquals(" ", "Login");
		System.out.println("step -1");
		System.out.println("step -2");
		System.out.println("step -3");
		System.out.println("step -4");
	}
	
	
}
