package com.Application.Pages.eBay;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.basic.utility.KeywordFunctions;


public class LoginPage extends KeywordFunctions implements LoginImpl{
	private static final java.util.logging.Logger LOGGER = Logger.getLogger(LoginPage.class);
	
	@FindBy(xpath="//android.widget.Button[contains(@text,'SIGN IN')]")
	private static WebElement signInBtn;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/edit_text_username']")
	private WebElement userName;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/edit_text_password']")
	private WebElement password;
	
	@FindBy(xpath="//*[contains(@text,'Home')]")
	private WebElement homeBtn;
	
	@FindBy(xpath="//*[contains(@text,'TEXT A TEMPORARY PASSWORD')]")
	private WebElement tempPassword;
	
	@FindBy(xpath="//*[contains(@text,'FORGOT PASSWORD')]")
	private WebElement frgtPassword;
	
	@FindBy(xpath="//*[contains(@text,'CREATE AN ACCOUNT')]")
	private WebElement crtAccount;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/sign_in_alert_text_view']")
	private WebElement loginErrorMsg;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/home']")
	private WebElement gridBtn;
	
	@FindBy(xpath="//android.widget.Button[contains(@text,'MAYBE LATER')]")
	private WebElement mayBeLater;
	
	@FindBy(xpath="//android.widget.Button[contains(@text,'NOT NOW')]")
	private WebElement notNow;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/textview_sign_in_status']")
	private WebElement signInStatus;
	
	@FindBy(xpath="//*[contains(@text,'NO THANKS')]")
	private WebElement noThanksBtn;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
/* -------------------------------------------------------------------------------------------------------------
 	Method Purpose - Sign in to Application
	Author - Praveen
	Date -  29th Mar 19
----------------------------------------------------------------------------------------------------------------*/
	public boolean logIn_Into_App(String usernameText, String passwordText){
		try {
			testStepStatus=false;
			if (checkForVisiblity(signInBtn)) {
				if (checkForVisiblity(userName)) {
					LOGGER.info("Landed in SignIn Page");
				}else {
					LOGGER.info("Sign In Button is Present");
					clickOn(driver,signInBtn);
				}
				if(checkForVisiblity(userName)) {
					LOGGER.info("Logging in with UserName "+usernameText);
					enterTextValue(userName, usernameText);
					enterTextValue(password, passwordText);
					LOGGER.info("Credentials entered Successfully");
					clickOn(driver, signInBtn);
					LOGGER.info("Clicked on SignIn Button");
				}
				if (checkForVisiblity(mayBeLater)) {
					clickOn(driver, mayBeLater);
				}else if (checkForVisiblity(notNow)) {
					clickOn(driver, notNow);
				}else if (checkForVisiblity(noThanksBtn)) {
					clickOn(driver, noThanksBtn);
				}
				
			}else {
				LOGGER.info("Sign In Button is Not Visible");
				return false;
			}
			if (checkForVisiblity(gridBtn)) {
				clickOn(driver, gridBtn);
				LOGGER.info("Clicked on Menu Button");
				if (checkForVisiblity(homeBtn)) {
					testStepStatus=true;
					clickOn(driver,homeBtn);
					LOGGER.info("User is logged in Successfully");
				}else{
					LOGGER.info("User is not logged in Successfully");
					return false;
				}
			}
			}catch (Exception e) {
				return false;
			}	
		return testStepStatus;
		}
	
	public boolean invalidLogin(String invalidUsernameText, String invalidPasswordText) {
		testStepStatus=false;
		try {
			if (checkForVisiblity(signInBtn)) {
				LOGGER.info("Sign In Button is Present");
				clickOn(driver,signInBtn);
				if(checkForVisiblity(userName)) {
					LOGGER.info("Logging in with UserName "+invalidUsernameText);
					enterTextValue(userName, invalidUsernameText);
					enterTextValue(password, invalidPasswordText);
					LOGGER.info("Credentials entered Successfully");
					clickOn(driver, signInBtn);
					LOGGER.info("Clicked on SignIn Button");
					
				}
				if (checkForVisiblity(loginErrorMsg)) {
					System.out.println("Login Message"+loginErrorMsg.getAttribute("text"));
					LOGGER.info("Unable to login due to invalid Credentials");
					testStepStatus=true;
				}
			}
		}catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		return testStepStatus;
	}
}


