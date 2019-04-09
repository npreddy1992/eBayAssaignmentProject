package com.basic.utility;

import java.io.File;

public interface Constants {
	
	public static final String dataSheetPath=System.getProperty("user.dir") + File.separator + "/src/test/resources/eBayTestData.xlsx";
	public static final String applicationPath=System.getProperty("user.dir") +"/eBay.apk";
	public static final String screenShotPath=System.getProperty("user.dir") +"/screenshots/";
	public static final String reportPath=System.getProperty("user.dir") +"/report/eBayAutomationReport.html";
	public static final String excelTestDataPage="eBayTestData";
	public static final String excelExecutionDataPage="DeviceCapabilities";
	public static final String creditCard="Credit Card";
	public static final String debitCard="Debit Card";
	public static final String creditCard_EMI="EMI - Credit Card";
	public static final String netBanking="Netbanking";
	public static final String UPI="UPI";
	public static final String wallets_CashCards="Wallets / Cash Cards";
	public static final String phonePe_BHIM="PhonePe / BHIM UPI";
	public static final String enterKey="Enter";
	public static final String bestMatch="Best Match";
	public static final String lowestPrice="Lowest Price +";
	public static final String highestPrice="Highest Price +";
	public static final String endingSoon="Ending Soon";
	public static final String newlyListed="Newly list";
	public static final String nearestFirst="Nearest First";
	
}
