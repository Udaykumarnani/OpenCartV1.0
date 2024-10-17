package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter; // UI(Look and feel of the report) of the report
	public ExtentReports extent;  // Populate common info on the report
	public ExtentTest test;  //creating test entries in the report and updating status of the methods
	String repName;
	
	 public void onStart(ITestContext context) {
		 
		 /*
		  SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 Date dt=new Date();
		 String currentdatetimestamp=df.format(dt);
		 */
		 
		 String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // date and time stamp
		 repName="Test-Report"+timestamp+".html"; // report name and format
		 sparkReporter =new ExtentSparkReporter(".\\reports\\"+repName); // specifying Location
		 
		 sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); // Title of the report
		 sparkReporter.config().setReportName("OpenCart Functional Testing");// Name of the report
		 sparkReporter.config().setTheme(Theme.STANDARD);
		 
		 extent =new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 
		 extent.setSystemInfo("Application", "OpenCart");
		 extent.setSystemInfo("Module", "Admin");
		 extent.setSystemInfo("Sub-Module", "Customers");
		 extent.setSystemInfo("User Name", System.getProperty("user.name"));
		 extent.setSystemInfo("Environment", "QA");
		 
		 String os=context.getCurrentXmlTest().getParameter("os");
		 extent.setSystemInfo("Operating System", os);
		 
		 String browser=context.getCurrentXmlTest().getParameter("browser");
		 extent.setSystemInfo("Browser", browser);
		 
		 List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();
		 if(!includedGroups.isEmpty())
		 {
			 extent.setSystemInfo("Groups", includedGroups.toString());
		 }
     }
	 
	 
	 public void onTestSuccess(ITestResult result) {

		 test=extent.createTest(result.getTestClass().getName()); // create new entry in the report
		 test.assignCategory(result.getMethod().getGroups()); // To Display groups in report
		 test.log(Status.PASS,result.getName()+" got executed successfully");
		 
		 
		  }
	 
	 
    public void onTestFailure(ITestResult result) {
		 
    	 test=extent.createTest(result.getTestClass().getName()); // create new entry in the report
    	 test.assignCategory(result.getMethod().getGroups()); // To display groups in report
    	 
		 test.log(Status.FAIL,result.getName()+" got failed test");
		 test.log(Status.INFO,result.getThrowable().getMessage());
		 
		 try 
		 {
		 String imgpath=new BaseClass().captureScreen(result.getName());
		 test.addScreenCaptureFromPath(imgpath);
		 }catch(IOException e1) {
			 e1.printStackTrace();
		 }
	   }

    
    public void onTestSkipped(ITestResult result) {
		 
    	 test=extent.createTest(result.getTestClass().getName()); // create new entry in the report
    	 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP,result.getName()+" got Skipped");
		 test.log(Status.INFO, result.getThrowable().getMessage());
		 
		  }
    
    
    public void onFinish(ITestContext context) {
		 
		 extent.flush();
		 
		 String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		 File extentReport=new File(pathOfExtentReport);
		 
		 try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
//		 try {
//		     
//			URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
//			
//		//Creating Email message
//			
//			ImageHtmlEmail email=new ImageHtmlEmail();
//			
//			/*
//			DataSourceUrlResolver dsr=new DataSourceUrlResolver(url);
//			email.setDataSourceResolver(dsr);
//		    */
//			
//			email.setDataSourceResolver(new DataSourceUrlResolver(url));
//			email.setHostName("smtp.googlemail.com");
//			email.setSmtpPort(465);
//			email.setAuthenticator(new DefaultAuthenticator("udaykumarmedi24@gmail.com","Uday@1995"));
//			email.setSSLOnConnect(true);
//			email.setFrom("udaykumarmedi24@gmail.com");  // Sender
//			email.setSubject("Test Results");
//			email.setMsg("Find the Attached Report");
//			email.addTo("udaykumarmedi24@outlook.com"); // Receiver
//			email.attach(url,"Extent Report","Please Check Report.....");
//			email.send();
//			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 
		
	 

    }

}
