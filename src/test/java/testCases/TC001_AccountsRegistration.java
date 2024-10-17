package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountsRegistration extends BaseClass{
	
	

	@Test(groups={"Regression","Master"})
	 public void accounts_registration()
	{
	 try
	{
		logger.info("******  Test Started *****");
		logger.info("****** Home page Started *****");
		
		HomePage home=new HomePage(driver);
		
		logger.info("****** Click On My account *****");
		
		home.clickMyAccount();
		
		logger.info("****** Click on register *****");
		
		home.clickRegister();
		
		logger.info("****** Registration page Started *****");
		
		RegistrationPage rp=new RegistrationPage(driver);
		
		logger.info("******Customer Details *****");
		
		rp.sendFName(randomString().toUpperCase());
		rp.sendLName(randomString().toLowerCase());
		rp.sendEmail(randomString()+"@gmail.com");
		rp.sendTelephone(randomNumber());
		String p=randomAplhaNumber();
		rp.sendPwd(p);
		rp.sendCPwd(p);
		rp.agree();
		rp.btnContinue();
		
		logger.info("****** Validating Message *****");
		
		String s=rp.message();
		
		Assert.assertEquals(s,"Your Account Has Been Created!");
		
		logger.info("*** Test Passed *****");
		
	}catch(Exception e) 
	 {
		 
		 Assert.fail();
		
	}finally {
		logger.info("*** TC001 Test finished *****");
	}
	}

	
	
}
