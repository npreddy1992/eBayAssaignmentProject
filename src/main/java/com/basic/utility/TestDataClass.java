package com.basic.utility;

import java.util.Map;

public class TestDataClass implements TestDataClassImpl{

	private String userName;
	private String passWord;
	private String invalidUserName;
	private String invalidPassword;
	private String searchText;
	private String sortType;
	private String minPriceRange;
	private String maxPriceRange;
	private String paymentType;
	private String UPI;
	private String creditCardNumber;
	private String creditCardName;
	private String creditCardExpiryMonth;
	private String creditCardExpiryYear;
	private String creditCardCvv;

	public TestDataClass(String page) {
		Map<String, String> testData = KeywordFunctions.getDataFromFiles(page,Constants.excelTestDataPage);
		this.userName=testData.get("userName");
		this.passWord=testData.get("passWord");
		this.invalidUserName=testData.get("invalidUserName");
		this.invalidPassword=testData.get("invalidPassword");
		this.searchText=testData.get("searchText");
		this.sortType=testData.get("sortType");
		this.paymentType=testData.get("paymentType");
		this.UPI=testData.get("UPI");
		this.minPriceRange=testData.get("minPriceRange");
		this.maxPriceRange=testData.get("maxPriceRange");
		this.creditCardNumber=testData.get("creditCardNumber");
		this.creditCardName=testData.get("creditCardName");
		this.creditCardExpiryMonth=testData.get("creditCardExpiryMonth");
		this.creditCardExpiryYear=testData.get("creditCardExpiryYear");
		this.creditCardCvv=testData.get("creditCardCvv");
	}
	
	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getMinPriceRange() {
		return minPriceRange;
	}

	public void setMinPriceRange(String minPriceRange) {
		this.minPriceRange = minPriceRange;
	}

	public String getMaxPriceRange() {
		return maxPriceRange;
	}

	public void setMaxPriceRange(String maxPriceRange) {
		this.maxPriceRange = maxPriceRange;
	}
	
	public String getMinPrice() {
		return minPriceRange;
	}

	public void setMinPrice(String minPrice) {
		this.minPriceRange = minPrice;
	}

	public String getMaxPrice() {
		return maxPriceRange;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPriceRange = maxPrice;
	}

	public String getCreditCardExpiryMonth() {
		return creditCardExpiryMonth;
	}

	public void setCreditCardExpiryMonth(String creditCardExpiryMonth) {
		this.creditCardExpiryMonth = creditCardExpiryMonth;
	}

	public String getCreditCardExpiryYear() {
		return creditCardExpiryYear;
	}

	public void setCreditCardExpiryYear(String creditCardExpiryYear) {
		this.creditCardExpiryYear = creditCardExpiryYear;
	}

	public String getCreditCardCvv() {
		return creditCardCvv;
	}

	public void setCreditCardCvv(String creditCardCvv) {
		this.creditCardCvv = creditCardCvv;
	}
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCreditCardName() {
		return creditCardName;
	}

	public void setCreditCardName(String creditCardName) {
		this.creditCardName = creditCardName;
	}
	
	public String getUPI() {
		return UPI;
	}

	public void setUPI(String uPI) {
		this.UPI = uPI;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getInvalidUserName() {
		return invalidUserName;
	}

	public void setInvalidUserName(String invalidUserName) {
		this.invalidUserName = invalidUserName;
	}

	public String getInvalidPassword() {
		return invalidPassword;
	}

	public void setInvalidPassword(String invalidPassword) {
		this.invalidPassword = invalidPassword;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}	
}
