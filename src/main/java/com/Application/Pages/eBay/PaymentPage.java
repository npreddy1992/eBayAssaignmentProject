package com.Application.Pages.eBay;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.basic.utility.Constants;
import com.basic.utility.KeywordFunctions;

public class PaymentPage extends KeywordFunctions implements PaymentImpl{
	private static final Logger LOGGER = Logger.getLogger(PaymentPage.class);
	
	@FindBy(xpath=".//*[contains(@text,'Choose your payment method')]​")
	private WebElement paymentPage;
	
	@FindBy(xpath="//*[@resource-id='btnPay']")
	private WebElement payBtn;

	@FindBy(xpath="//android.widget.RadioButton")
	private WebElement UPIRadioBtn;
	
	@FindBy(xpath="//*[@resource-id='vpAddress']")
	private WebElement VPA;
	
	@FindBy(xpath="//*[@resource-id='vpAddress']//parent::android.view.View//following::android.view.View[1]/android.widget.Button")
	private WebElement makePaymentBtn;
	
	@FindBy(xpath="//*[@resource-id='vpAddress']//parent::android.view.View//preceding::android.view.View[1]")
	private WebElement errorMsg;
	

	
	public PaymentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
/* -------------------------------------------------------------------------------------------------------------
 	Method Purpose - Navigate to the mentioned Payment type
	Author - Praveen
	Date -  1st Apr 19
----------------------------------------------------------------------------------------------------------------*/
	public Boolean navigateToCardPaymnetType(String paymentType) {
		testStepStatus=false;
		if(checkForVisiblity(paymentPage)) {
			LOGGER.info("Landed in Payment Page");
		}
		try {
			LOGGER.info("Proceeding with the payment type "+paymentType);
			switch (paymentType) {
			case "CreditCard":{
				clickOnExactText(Constants.creditCard);
				testStepStatus=true;
				break;
			}
			case "DebitCard":{
				clickOnExactText(Constants.debitCard);
				testStepStatus=true;
				break;
			}
			case "NetBanking":{
				clickOnExactText(Constants.netBanking);
				testStepStatus=true;
				break;
			}
			case "CreditCardEMI":{
				clickOnExactText(Constants.creditCard_EMI);
				testStepStatus=true;
				break;
			}
			case "UPI":{
				clickOnExactText(Constants.UPI);
				testStepStatus=true;
				break;
			}
			case "Wallets_CashCards":{
				clickOnExactText(Constants.wallets_CashCards);
				testStepStatus=true;
				break;
			}
			case "PhonePe_BHIM":{
				clickOnExactText(Constants.phonePe_BHIM);
				testStepStatus=true;
				break;
			}
			}
		} catch (Exception e) {
			return testStepStatus=false;
		}
		return testStepStatus;
	}
/* -------------------------------------------------------------------------------------------------------------
 	Method Purpose - This method handles UPI Payment Part
	Author - Praveen
	Date -  1st Apr 19
----------------------------------------------------------------------------------------------------------------*/
	public Boolean inavlidUPIPayment(String UPI) {
		testStepStatus=false;
		try {
			if(checkForVisiblity(UPIRadioBtn)) {
				clickOn(driver, UPIRadioBtn);
				clickOn(driver, payBtn);
			}if(checkForVisiblity(VPA)) {
			}else if (checkForVisiblity(VPA)) {
				LOGGER.info("Landed in UPI Payment");
			}
				enterTextValue(VPA, UPI);
				clickOn(driver, makePaymentBtn);
				if (checkForVisiblity(makePaymentBtn)) {
					clickOn(driver, makePaymentBtn);
				}
			
			if (checkForVisiblity(errorMsg)) {
				LOGGER.error("The error occured is "+errorMsg.getAttribute("text"));
				LOGGER.info("Error message popped Up");
				testStepStatus=true;
			}else {
				LOGGER.info("Error message not popped Up");
				testStepStatus=false;
			}
		} catch (Exception e) {
			return testStepStatus=false;
		}
		return testStepStatus;
	}
}


