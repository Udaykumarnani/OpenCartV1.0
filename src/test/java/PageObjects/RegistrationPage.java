package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
	
	public RegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
// Locators
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFName;
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLName;
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPwd;
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtCPwd;
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkAgree;
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement message;
	
// Action Methods
	
	public void sendFName(String fname)
	{
		txtFName.sendKeys(fname);
	}
	
	public void sendLName(String lname)
	{
		txtLName.sendKeys(lname);
	}
	
	public void sendEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	
	public void sendTelephone(String tel)
	{
		txtTelephone.sendKeys(tel);
	}

	
	public void sendPwd(String pwd)
	{
		txtPwd.sendKeys(pwd);
	}
	
	public void sendCPwd(String cpwd)
	{
		txtCPwd.sendKeys(cpwd);
	}
	
	public void agree()
	{
		chkAgree.click();
	}
	
	public void btnContinue()
	{
		btnContinue.click();
	}
	
	
	public String message()
	{
		try
		{
		return (message.getText());
		}catch(Exception e){
			return (e.getMessage());
		}
	}
}
