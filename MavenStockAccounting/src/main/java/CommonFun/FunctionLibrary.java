package CommonFun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFile1;


public class FunctionLibrary {

	WebDriver driver;

	// startBrowser

	public static WebDriver startBrowser(WebDriver driver) throws Throwable {
		if (PropertyFile1.getValueForKey("Browser").equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "");
			driver = new FirefoxDriver();

		} else 
			if (PropertyFile1.getValueForKey("Browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\shyam.r\\workspace\\MavenStockAccounting\\ExecutableFiles\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println("Not navigating to URL");

		} else
			if (PropertyFile1.getValueForKey("Browser").equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\shyam.r\\workspace\\MavenStockAccounting\\ExecutableFiles\\chromedriver.exe");
			driver = new ChromeDriver();

		}
		return driver;
	}

	// openApplication

	public static void openApplication(WebDriver driver) throws Throwable 
	{
		driver.get(PropertyFile1.getValueForKey("URL"));
		driver.manage().window().maximize();
	}

	// clickAction

	public static void clickAction(WebDriver driver, String locatorType, String locatorValue) {
		if (locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locatorValue)).click();
		} else if (locatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locatorValue)).click();
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locatorValue)).click();
		} else if (locatorType.equalsIgnoreCase("linkText")) {
			driver.findElement(By.linkText(locatorValue)).click();
		}
	}

	// typeAction

	public static void typeAction(WebDriver driver, String locatorType, String locatorValue, String data) {

		if (locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locatorValue)).clear();
			driver.findElement(By.id(locatorValue)).sendKeys(data);

		} else if (locatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locatorValue)).clear();
			driver.findElement(By.name(locatorValue)).sendKeys(data);
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locatorValue)).clear();
			driver.findElement(By.xpath(locatorValue)).sendKeys(data);
		} else if (locatorType.equalsIgnoreCase("linkText")) {
			driver.findElement(By.xpath(locatorValue)).clear();
			driver.findElement(By.xpath(locatorValue)).sendKeys(data);
		}

	}

	// closeBrowser

	public static void closeBrowser(WebDriver driver) {
		driver.close();
	}

	// waitForElement

	public static void waitForElement(WebDriver driver, String locatorType, String locatorValue, String waittime) {
		WebDriverWait myWait = new WebDriverWait(driver, Integer.parseInt(waittime));

		if (locatorType.equalsIgnoreCase("id")) {

			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));

		} else if (locatorType.equalsIgnoreCase("name")) {
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
		} else if (locatorType.equalsIgnoreCase("linkText")) {
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
		}

	}

	// Page Down

	public static void pageDown(WebDriver driver) {
		Actions pageDown = new Actions(driver);
		pageDown.sendKeys(Keys.PAGE_DOWN).build().perform();
	}

	// Capturing Data in Note pad

	public static void captureData(WebDriver driver, String locatorType, String locatoryValue) throws Throwable {
		String data = "";
		if (locatorType.equalsIgnoreCase("id")) {
			data = driver.findElement(By.id(locatoryValue)).getAttribute("value");
		} else if (locatorType.equalsIgnoreCase("name")) {
			data = driver.findElement(By.name(locatoryValue)).getAttribute("value");
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			data = driver.findElement(By.xpath(locatoryValue)).getAttribute("value");
		}

		FileWriter fw = new FileWriter("C:\\Users\\shyam.r\\workspace\\MavenStockAccounting\\CaptureData\\Data.txt");
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(data);
		bw.flush();
		bw.close();

	}

	// Table Validations
	public static void tableValidation(WebDriver driver, String column) throws Throwable
	{
		// Reading the Data from Text file
		FileReader fr=new FileReader("C:\\Users\\shyam.r\\workspace\\MavenStockAccounting\\CaptureData\\Data.txt");
		
		BufferedReader br=new BufferedReader(fr);
		String exp_data=br.readLine();
		
		// Converting String value in Integer
		int colNum=Integer.parseInt(column);
		
		//Search Panel
		
		if(driver.findElement(By.xpath(PropertyFile1.getValueForKey("Search.Panel"))).isDisplayed())
				{
			
			driver.findElement(By.xpath(PropertyFile1.getValueForKey("Search.Panel"))).click();
			Thread.sleep(3000);
			driver.findElement(By.id(PropertyFile1.getValueForKey("Search.Box"))).clear();
			driver.findElement(By.id(PropertyFile1.getValueForKey("Search.Box"))).sendKeys(exp_data);
			driver.findElement(By.id(PropertyFile1.getValueForKey("Search.Button"))).click();
			
				} else
				{
					//seach Box
					
					driver.findElement(By.id(PropertyFile1.getValueForKey("Search.Box"))).clear();
					driver.findElement(By.id(PropertyFile1.getValueForKey("Search.Box"))).sendKeys(exp_data);
					driver.findElement(By.id(PropertyFile1.getValueForKey("Search.Button"))).click();
									
				}
		
		//Supplier WebTablees
		
		WebElement webtable=driver.findElement(By.xpath(PropertyFile1.getValueForKey("webtable")));
		
		//Row count
		
		List<WebElement> rows=webtable.findElements(By.tagName("tr"));
		
		for(int i=1; i<=rows.size(); i++)
		{
			// Capturing Supplier Number
			String act_data=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div//table[@id=tb1_a_supplierslist']/tbody/tr["+i+"]/td["+colNum+"]/div/span")).getText();
			System.out.println(act_data);	
			
			//Validations
			Assert.assertEquals(exp_data, act_data);
			break;
		}
		
	}
		public static String generateDate()
		{
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
			return sdf.format(date);
		}
		
		//Mouse Hover and Click
		
		public static void mouse(WebDriver driver)
		{
			Actions mouse=new Actions(driver);
			mouse.moveToElement(driver.findElement(By.id("mi_a_stock_items")));
			
		}
		
	

		
	}

