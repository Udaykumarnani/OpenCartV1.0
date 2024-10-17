package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups= {"Sanity","Master"})
	public void verify_login()
	{
		logger.info("**** Test Started ****");
	
   // Home page	
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
	//Login page	
		LoginPage lp=new LoginPage(driver);
		lp.sendEmail(p.getProperty("userName"));
		lp.sendPwd(p.getProperty("password"));
		lp.Login();
		
	// MyAccount Page
		
		MyAccountPage ap=new MyAccountPage(driver);
		boolean targetpage=ap.isMyAccountpageExists();
		Assert.assertTrue(targetpage);
		
		logger.info("*** Test Finished ****");
		}catch(Exception e) {
			
			Assert.fail();
		}
	}

}
