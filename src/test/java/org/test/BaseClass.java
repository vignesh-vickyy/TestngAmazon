package org.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BaseClass
{
public static WebDriver driver;

public static void launchBrowser(String browsername) {
	switch (browsername) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Vignesh\\eclipse-workspace\\Selenium\\Driver-exe\\chromedriver.exe");
            driver = new ChromeDriver();
			break;
		case "Firefox":
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Vignesh\\eclipse-workspace\\Selenium\\Driver-exe\\chromedriver.exe");
            driver =new FirefoxDriver();
			break;
		case "Ie":	
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Vignesh\\eclipse-workspace\\Selenium\\Driver-exe\\chromedriver.exe");
            driver =new InternetExplorerDriver();
			break;
			default:
				System.err.println("Invalid browser name========");
	        break;
	}  
}
       public static void quitBrowser() {
    	   driver.quit();
       }
       public static void sendkeys(WebElement e,String value) {
    	   e.sendKeys(value);
       }
       public static void launchUrl(String url) {
    	   driver.get(url);
    	   driver.manage().window().maximize();
       }
       public static void implicitWait() {
    	   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
       }
       public static void fillTextBox(WebElement e,String value) {
    	   e.sendKeys(value);
       }
       public static void enter(WebElement e,String value) {
    	   e.sendKeys(value);
       }
       public static void btnClick(WebElement e) {
    	   e.click();
       }
       public static String getCurrentURL() {
    	   return driver.getCurrentUrl();
       }
       public static String getAttribute(WebElement e) {
    	   return e.getAttribute("value");
       }
      
       public static String getText(WebElement element) {
    	   return element.getText();
       }
       public static void moveToElement(WebElement target) {
    	   Actions a=new Actions(driver);
    	   a.moveToElement(target).perform();
       }
       public static void selectByIndex(WebElement element,int index) {
    	   Select s=new Select(element);
    	   s.selectByIndex(index);
       }
       public static void selectByVisibleText(WebElement element,String value) {
    	   Select s=new Select(element);
    	   s.selectByVisibleText(value);
       }
       public static void selectByValue(WebElement element, String value) {
    	   Select s=new Select(element);
    	   s.selectByValue(value);
       }
       public static WebElement findElementById(String id) {
    	   WebElement element=driver.findElement(By.id(id));
    	   return element;
       }
       public static void clear(WebElement e) {
    	   e.clear();
       }
       
       public static void takeScreenShot(String imagename) {
    	   TakesScreenshot tk=(TakesScreenshot) driver;
    	   File src=tk.getScreenshotAs(OutputType.FILE);
    	   File des =new File("C:\\Selenium\\Photon Workspace\\Testngnew\\src\\test\\resources\\ScreenShots\\"+imagename+".png");
    	   try {
    		   FileUtils.copyFile(src, des);
    	   }catch (IOException e) {
    		   e.printStackTrace();
    	   }
    	   
       }
       public static void jsSendkeys(String input,WebElement e) {
    	   JavascriptExecutor js=(JavascriptExecutor) driver;
           js.executeScript("arguements[0].setAttribute('value','" +input+"')",e);
        }
       public static void switchWindows(int index) {
    	   Set<String> alld =driver.getWindowHandles();
    	   List<String> li =new LinkedList<String>();
    	   li.addAll(alld);
    	   driver.switchTo().window(li.get(index));
       }
       public static String getExcelData(String sheetname,int rowNo,int cellNo) throws IOException {
       File f=new File("Excel path");// add pth respective
       FileInputStream st= new FileInputStream(f);
       Workbook w =new XSSFWorkbook(st);
       Sheet sheet = w.getSheet(sheetname);
       Row row =sheet.getRow(rowNo);
       Cell c =row.getCell(cellNo);
       int type =c.getCellType();
       String value = null;
       if (type== 1) {
    	   value= c.getStringCellValue();
       }else {
    	   if(DateUtil.isCellDateFormatted(c)) {
    		   value=new SimpleDateFormat("dd-mmm-yyyy").format(c.getDateCellValue());
    	   }else {
    		   value=String.valueOf((long) c.getNumericCellValue());
    	   }
       }
       return value;
}
public static void main(String[] args) throws IOException {
	String s= getExcelData("Datas",1,0);
	System.out.println(s);
}
}

