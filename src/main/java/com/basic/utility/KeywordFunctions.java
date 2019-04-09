package com.basic.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
public class KeywordFunctions extends ParentTest{
	private static final Logger LOGGER = Logger.getLogger(KeywordFunctions.class);
	public static long max=60;
	public static long med=30;
	public static long min=10;
	
	protected static void enterTextValue(WebElement element, String text) {
		LOGGER.info("Starting to Enter Text Value");
		element.sendKeys(new CharSequence[] { text });
		LOGGER.info("Entered " + text + " Successfully");
	}
	
	protected static void clickOn(WebDriver driver,WebElement element) {
		try{
			WebElement el=element;
			el.click();
		}catch(Exception e){
			e.printStackTrace();
			WebElement e2=element;
			e2.click();
		}
	}
	protected static void clickOnExactText(String textToFind) {
		try{
			WebElement el=driver.findElement(By.xpath("//*[@text='"+textToFind+"']"));
			el.click();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected static void clickOnContainsText(String textToFind) {
		try{
			WebElement el=driver.findElement(By.xpath("//*[contains(@text,'"+textToFind+"')]"));
			el.click();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected static boolean checkForVisiblity(WebElement locator)
    {

        boolean waitValue = false;
        try {
            waitValue = new WebDriverWait(driver,6)
                    .until(ExpectedConditions.visibilityOf(locator)).isDisplayed();
            
        } catch (Exception e) {
        }
        return waitValue;

    }
	protected static void swipe(WebDriver driver,String direction, String speed) throws InterruptedException
	{
		try{

			direction=direction.toLowerCase();
			Dimension size;
			int starty,startx,scrWidth,scrHeight,rateToSwipe;			
			size=driver.manage().window().getSize();
			scrWidth=size.getWidth();
			scrHeight=size.getHeight();
			startx=(int)(size.width*0.5);
			starty=(int)(size.height*0.5);
			String sDir;
			sDir=direction.toLowerCase();
			speed=speed.toLowerCase();
			if (speed=="slow") {
				rateToSwipe=250;
			}else if(speed=="medium"){
				rateToSwipe=150;
			}else{
				rateToSwipe=50;
			}
			switch (sDir) {
			case("up"):
				LOGGER.info("Swiping in "+direction+"  direction");
				((AndroidDriver) driver).swipe(startx,starty+(scrHeight-rateToSwipe-(starty)),startx,starty-(scrHeight-rateToSwipe-(starty)),3000);
				break;
			case("down"):
				LOGGER.info("Swiping in "+direction+"  direction");
				((AndroidDriver) driver).swipe(startx,starty-(scrHeight-rateToSwipe-(starty)),startx,starty-(scrHeight-rateToSwipe-(starty)),3000);			
				break;
			case("right"):
				LOGGER.info("Swiping in "+direction+"  direction");
				((AndroidDriver) driver).swipe(startx+(scrWidth-50-(startx)),starty,startx-(scrWidth-rateToSwipe-(startx)),starty,2000);		
				break;
			case("left"):
				LOGGER.info("Swiping in "+direction+"  direction");
				((AndroidDriver) driver).swipe(startx-(scrWidth-50-(startx)),starty,startx+(scrWidth-rateToSwipe-(startx)),starty,3000);
			break;
			
			default:
				break;
			}
		}
			catch(Exception e){
				LOGGER.info("Exception block inside Swipe Page");
				e.printStackTrace();
			}
	}

	public static Map<String, String> getDataFromFiles(String detailInstanceName,String sheetName) {
		XSSFWorkbook myWorkBook = null;
		Map<String, String> testdata = null;
		File myFile =null;
		try {
				 myFile = new File(Constants.dataSheetPath);
			FileInputStream fis = new FileInputStream(myFile);	
			myWorkBook = new XSSFWorkbook(fis);
			XSSFSheet mySheet;
			mySheet = myWorkBook.getSheet(sheetName);
			Iterator<Row> rowIterator = mySheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						break;
					case Cell.CELL_TYPE_NUMERIC:
						cell.setCellType(1);
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						cell.setCellType(1);
						break;
					default:
					}
				}
			}
			int testdataRowindex = 0;
			String TestColumn = "NotAvailable";
			Iterator<Row> rowIterator2 = mySheet.iterator();
			while (rowIterator2.hasNext()) {
				Row row = rowIterator2.next();
				if (row.getCell(0).getStringCellValue().contentEquals(detailInstanceName)) {
					testdataRowindex = row.getCell(0).getRowIndex();
					TestColumn = "Available";
					break;
				}
			}
			if (TestColumn.equalsIgnoreCase("NotAvailable")) {
				throw new RuntimeException("Data not supplied for " + detailInstanceName);
			}
			String[] firstRow = new String[1000];

			for (int colIndex = 0; colIndex <= mySheet.getRow(0).getLastCellNum() - 1; colIndex++) {
				firstRow[colIndex] = mySheet.getRow(0).getCell(colIndex).getStringCellValue();
			}

			String[] testdataRow2 = new String[1000];

			for (int colIndex = 0; colIndex <= mySheet.getRow(0).getLastCellNum() - 1; colIndex++) {
				try {
					testdataRow2[colIndex] = mySheet.getRow(testdataRowindex).getCell(colIndex).getStringCellValue();
				} catch (NullPointerException e) {
					testdataRow2[colIndex] = null;
				}
			}
			int i = 0;
			testdata = new HashMap<String, String>();
			for (String cellvalue : firstRow) {
				if (cellvalue == null || cellvalue.equalsIgnoreCase(""))
					break;
				try {
					testdata.put(cellvalue, testdataRow2[i++]);
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in fetching test data from Excel");
			e.printStackTrace();
		} finally {
			try {
				myWorkBook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return testdata;
	}
	protected static WebElement elemantToText(WebDriver driver,String text) {
    	WebElement element = null;
    	try {
    		 element=driver.findElement(By.xpath("//*[contains(@text,'"+text+"')]"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return element;
}
	public static String getScreenshot(WebDriver driver) {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String path=Constants.screenShotPath+"error.png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination, true);
		} catch (Exception e) {
			
		}
		return path;
	}
	public static String getScreenshot(WebDriver driver,String path) {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		path=Constants.screenShotPath+path+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination, true);
		} catch (Exception e) {
			
		}
		return path;
	}
	protected static String pressAndroidKeys(WebDriver driver,String keyName) {
		try {
			switch (keyName) {
			case "Enter":{
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.ENTER);
				LOGGER.info(keyName+" is pressed");
				break;
			}
			}
		} catch (Exception e) {
			
		}
		return "";
	}
}
