package com.Application.Pages.eBay;


import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.basic.utility.Constants;
import com.basic.utility.KeywordFunctions;


public class ProductPage extends KeywordFunctions implements ProductImpl{
	private static final Logger LOGGER = Logger.getLogger(ProductPage.class);
	private static String productName,itemPrice;
	
	@FindBy(xpath="//android.widget.Button[contains(@text,'BUY IT NOW')]")
	private WebElement buyItNowBtn;
	
	@FindBy(xpath="//android.widget.Button[contains(@text,'REVIEW')]")
	private WebElement reviewBtn;
	
	@FindBy(xpath="//android.widget.Button[contains(@text,'Proceed to Pay')]")
	private WebElement proceedToPayBtn;
	
	@FindBy(xpath="//*[contains(@text,'FILTER')]")
	private WebElement filterBtn;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/search_box']")
	private WebElement searchBar;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/text_slot_1']")
	private WebElement msg_Info;

	@FindBy(xpath="//android.widget.RelativeLayout[3]//*[@resource-id='com.ebay.mobile:id/textview_item_price']")
	private WebElement searchSelect;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/textview_item_count']")
	private WebElement searchResults;
	
	@FindBy(xpath="//*[contains(@text,'Price range')]")
	private WebElement priceRange;
	
	@FindBy(xpath="//*[contains(@text,'Custom price range')]")
	private WebElement customPriceRange;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/minimum_price_view']")
	private WebElement minPriceRange;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/maximum_price_view']")
	private WebElement maxPriceRange;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/button_sort']")
	private WebElement sortBtn;

	@FindBy(xpath="//android.widget.Button[@text='OK']")
	private WebElement filterOkBtn;
	
	@FindBy(xpath="//android.widget.Button[@text='DONE']")
	private WebElement filterDoneBtn;
	
	@FindAll(@FindBy(xpath = "//*[@resource-id='com.ebay.mobile:id/cell_collection_item']"))
	private List<WebElement> totalResults;
	
	public ProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	

/* -------------------------------------------------------------------------------------------------------------
 	Method Purpose - Search for the product
	Author - Praveen
	Date -  29th Mar 19
----------------------------------------------------------------------------------------------------------------*/
public boolean searchProduct(String searchText){
		
		
		try {
			testStepStatus = false;
			
				if (checkForVisiblity(searchBar)) {
					enterTextValue(searchBar, searchText);
					pressAndroidKeys(driver, Constants.enterKey);
				}						
				if (checkForVisiblity(msg_Info)) {
					clickOn(driver, msg_Info);
				}
					String searchResult=searchResults.getAttribute("text");
					String[] resVal=searchResult.split(" ");
					int searchVal=Integer.parseInt(resVal[0]);
						if (searchVal>0) {
							testStepStatus=true;
							LOGGER.info(searchVal+ " Results are displayed for search text entered");
						}else{
							LOGGER.info("No Results are displayed for search text");
						}
			}catch (Exception e) {
				return testStepStatus=false;
			}
			
	return testStepStatus;
			}

/* -------------------------------------------------------------------------------------------------------------
Method Purpose - Select product from the list
Author - Praveen
Date -  29th Mar 19
----------------------------------------------------------------------------------------------------------------*/
public boolean selectingSearchResults(){
	
	
	try {
		testStepStatus = false;
		if (checkForVisiblity(msg_Info)) {
			clickOn(driver, msg_Info);
		}
		int minVal=2;
		int maxVal=totalResults.size();
		LOGGER.info("Maximum results in page "+maxVal);
		int randomResult=(int) (Math.random()*(maxVal-minVal))+minVal;
		if (randomResult==1) {
			swipe(driver, "up", "medium");
		}
		LOGGER.info("Going to select "+randomResult);
		WebElement itemName=driver.findElement(By.xpath("//*[@resource-id='com.ebay.mobile:id/recycler']//android.widget.RelativeLayout["+randomResult+"]//android.widget.RelativeLayout[1]//*[@resource-id='com.ebay.mobile:id/textview_item_title']"));
		productName=itemName.getAttribute("text");
		itemPrice=driver.findElement(By.xpath("//*[@resource-id='com.ebay.mobile:id/recycler']//android.widget.RelativeLayout["+randomResult+"]//android.widget.RelativeLayout[1]//*[@resource-id='com.ebay.mobile:id/textview_item_price']")).getAttribute("text");	
		clickOn(driver, itemName);
		LOGGER.info("Selected product's Name is : "+productName);
		LOGGER.info("Selected product's price is : "+itemPrice);
		testStepStatus=true;
	}catch (Exception e) {
		return testStepStatus=false;
		}
		
return testStepStatus;
		}



/* -------------------------------------------------------------------------------------------------------------
Method Purpose - Verify Product and checkout page
Author - Praveen
Date -  29th Mar 19
----------------------------------------------------------------------------------------------------------------*/
public boolean verifyProductPage(){
	try {
		testStepStatus = false;
		if(checkForVisiblity(proceedToPayBtn)) {
			LOGGER.info("Successfully Landed in Product Page");
		}else {
			
		}	
		WebElement itemName=elemantToText(driver, productName);
		WebElement productPrice=elemantToText(driver, itemPrice);
		if (checkForVisiblity(itemName) && checkForVisiblity(productPrice))  {
			testStepStatus=true;
			LOGGER.info("The product name "+itemName+" matches with the details in the  product page");
		}else{
			LOGGER.info("The product name "+itemName+" do not matches with the details in the  product page");
		}
		if (checkForVisiblity(buyItNowBtn)) {
			LOGGER.info("buy It Now is Present");
			clickOn(driver, buyItNowBtn);
			if (checkForVisiblity(reviewBtn) & testStepStatus) {
				LOGGER.info("reviewBtn is Present");
				clickOn(driver, reviewBtn);
			}else {
				LOGGER.info("Product information doesn't match");
			}
		}
		
		
		
	}catch (Exception e) {
			return testStepStatus=false;
		}
		
return testStepStatus;
		}

/* -------------------------------------------------------------------------------------------------------------
Method Purpose - Verify Checkout Page and loads Payment Page
Author - Praveen
Date -  29th Mar 19
----------------------------------------------------------------------------------------------------------------*/
public boolean verifyCheckoutPage(){
	
	
	try {
		testStepStatus = false;
		if(checkForVisiblity(proceedToPayBtn)) {
			LOGGER.info("Successfully landed in Checkout Page");
		}else if (checkForVisiblity(proceedToPayBtn)) {
			LOGGER.info("Successfully landed in Checkout Page");
		}
		swipe(driver, "up", "fast");
		WebElement itemName=elemantToText(driver, productName.substring(3, 24));
		WebElement productPrice=elemantToText(driver, itemPrice.substring(2));
		if (checkForVisiblity(itemName) && checkForVisiblity(productPrice))  {
			testStepStatus=true;
			LOGGER.info("The product name "+itemName+" matches with the details in the checkout page");
		}else{
			LOGGER.info("The product name "+itemName+" do not matches with the details in the checkout page");
		}
		if (checkForVisiblity(proceedToPayBtn) & testStepStatus) {
			swipe(driver, "up", "fast");
			LOGGER.info("Proceed to Pay is Present");
			clickOn(driver, proceedToPayBtn);
			testStepStatus=true;
		}
		
	}catch (Exception e) {
		return testStepStatus=false;
		}
		
return testStepStatus;
		}

/* -------------------------------------------------------------------------------------------------------------
Method Purpose - Set Price Filter Range
Author - Praveen
Date -  29th Mar 19
----------------------------------------------------------------------------------------------------------------*/
public boolean sortProducts(String sortingType){
	
	try {
		testStepStatus = false;
		if (checkForVisiblity(sortBtn)) {
			LOGGER.info("Sort Button is Visible");
			clickOn(driver, sortBtn);
		}
		switch (sortingType) {
		case "Highest Price":{
			clickOnContainsText(Constants.highestPrice);
			testStepStatus=true;
			break;
		}
		case "Lowest Price":{
			clickOnContainsText(Constants.lowestPrice);
			testStepStatus=true;
			break;
		}
		case "Best Match":{
			clickOnContainsText(Constants.bestMatch);
			testStepStatus=true;
			break;
		}
		case "Ending Soon":{
			clickOnContainsText(Constants.endingSoon);
			testStepStatus=true;
			break;
		}
		case "Newly listed":{
			clickOnContainsText(Constants.newlyListed);
			testStepStatus=true;
			break;
		}
		case "Nearest":{
			clickOnContainsText(Constants.nearestFirst);
			testStepStatus=true;
			break;
		}
		}
	
	}catch (Exception e) {
		e.printStackTrace();
		return testStepStatus=false;
		}
		
return testStepStatus;
		}

public boolean calibratePriceFilter(String minPrice, String maxPrice){
	
	try {
		testStepStatus = false;
		if (checkForVisiblity(filterBtn)) {
			clickOn(driver, filterBtn);
			LOGGER.info("Going to Calibrate Filter");
			if (checkForVisiblity(priceRange)) {
				clickOn(driver, priceRange);
				clickOn(driver, customPriceRange);
				if (checkForVisiblity(minPriceRange)) {
					enterTextValue(minPriceRange, minPrice);
					enterTextValue(maxPriceRange, maxPrice);
				}
				if (checkForVisiblity(filterOkBtn)) 
					clickOn(driver, filterOkBtn);
				if (checkForVisiblity(filterDoneBtn)) {
					clickOn(driver, filterDoneBtn);
					LOGGER.info("Filter Calibrated");
					testStepStatus=true;
				}else {
					LOGGER.info("Filter Done Button is not clicked");
				}	
			}
		}	
	}catch (Exception e) {
		return testStepStatus=false;
		}
	return testStepStatus;
		}
}