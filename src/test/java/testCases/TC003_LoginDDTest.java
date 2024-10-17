package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 
Data is valid - login success - Test Pass - logout
Data is valid - login fail - Test fail

invalid Data - login success - Test fail - logout
invalid Data - login fail - Test Pass

 */

public class TC003_LoginDDTest extends BaseClass{
	
	@Test(dataProvider="loginData", dataProviderClass=DataProviders.class,groups="DataDriven") // data provider getting from different class
	public void verify_loginDDT(String Email,String Password,String Result)
	{
	
	  
	 try
	 {
      logger.info("**** TC003_Started ****");
	  HomePage hp=new HomePage(driver);
	  hp.clickMyAccount();
	  hp.clickLogin();
	
  //Login page	
	  LoginPage lp=new LoginPage(driver);
	  lp.sendEmail(Email);
	  lp.sendPwd(Password);
	  lp.Login();
	
  // MyAccount Page
	
	  MyAccountPage ap=new MyAccountPage(driver);
	  boolean targetpage=ap.isMyAccountpageExists();
	  
	  if(Result.equalsIgnoreCase("valid"))
	  {
		  if(targetpage==true)
		  {
			  ap.clickLogout();
			  Assert.assertTrue(true); 
			  
		  }else {
			  
			  Assert.assertTrue(false);
		  }
	  }
	  
	  if(Result.equalsIgnoreCase("invalid"))
	  {
		  if(targetpage==true)
		  {
			  ap.clickLogout();
			  Assert.assertTrue(false); 
			  
		  }else {
			  
			  Assert.assertTrue(true);
		  }
	  }
	 }catch(Exception e) {
		 
		 Assert.fail();
	 }
	  logger.info("**** TC003_Finished ****");
	}
	
	

}
