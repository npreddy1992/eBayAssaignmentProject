package com.basic.utility;


import java.io.File;
import java.net.URL;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.basic.factory.EbayAutomationFactory;
import io.appium.java_client.android.AndroidDriver;


public class ParentTest {
	protected static final Logger LOGGER = Logger.getLogger(ParentTest.class);
	public static WebDriver driver=null;
	public String application=System.getProperty("application");
	private DeviceDataClassImpl deviceData;
	public boolean testStepStatus;
	protected ExtentHtmlReporter htmlReporter;
	protected ExtentReports extent;
	protected ExtentTest logger;
	

	public WebDriver getBaseDriver() {
		return driver;
	}
	
	 public ParentTest() {
			 deviceData=EbayAutomationFactory.createDeviceDataClassInstance(application);
	}
	 public void startReportingInstance() {
		 	htmlReporter = new ExtentHtmlReporter(Constants.reportPath);
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
	}
	
	 public void startReportingTest(String methodInstance) {
		 logger = extent.createTest(methodInstance);	
	}
	 public void endReportingTest(String methodInstance) throws Exception {
			logger.info("Step Snapshot", MediaEntityBuilder.createScreenCaptureFromPath(KeywordFunctions.getScreenshot(driver, methodInstance)).build());
	}
public  WebDriver getDriver() throws InterruptedException, Exception {
	
	
		try {
					DesiredCapabilities capability =new DesiredCapabilities();
					capability .setCapability("autoAcceptAlerts",true);
					capability.setCapability("platformName",deviceData.getOsName());
					capability.setCapability("deviceName", deviceData.getUDID());
					capability.setCapability("platformVersion",deviceData.getOsVersion());
					capability.setCapability("newCommandTimeout",300);
					capability.setCapability("noReset",false);
					capability.setCapability("fullReset",true);
					capability.setCapability("setWebContentsDebuggingEnabled",true);
					capability.setCapability("app", new File(Constants.applicationPath));
					capability.setCapability("appPackage",deviceData.getAppPackage());
					capability.setCapability("appActivity",deviceData.getAppActivity());
					capability.setCapability("unicodeKeyboard", true);
					capability.setCapability("resetKeyboard", true);
					LOGGER.info("Driver instantiated with port no "+deviceData.getPortNo());
					driver = new AndroidDriver(new URL("http://0.0.0.0:"+deviceData.getPortNo()+"/wd/hub"),capability);
		}catch(Exception e) {
			e.printStackTrace();
			
			
		}
		return driver;
	}



}
