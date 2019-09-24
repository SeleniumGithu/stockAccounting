package CommonFun;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import Utilities.PropertyFile1;

public class CommonFucntions {

	static WebDriver driver;
	String res;

	// Launching Stock Accounting Applications

	public String appLaunch(String url) {
		System.setProperty("webdriver.chrome.driver", "D:\\Shyam\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();

		if (driver.findElement(By.id("username")).isDisplayed()) {
			res = "Pass";
		} else {
			res = "Fail";

		}

		return res;

	}

	// Login into Stockaccounting

	public String appLogin(String uname, String pwd) {

		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.id("btnsubmit")).click();

		if (driver.findElement(By.id("logout")).isDisplayed()) {
			res = "Pass";
		} else {
			res = "Fail";

		}

		return res;
	}

	// Logout from stock accounting

	public String appLogout() throws InterruptedException {
		driver.findElement(By.id("logout")).click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='OK!']")).click();

		Thread.sleep(4000);
		if (driver.findElement(By.id("username")).isDisplayed()) {
			res = "Pass";

		} else {
			res = "Fail";
		}

		return res;

	}

	// ******************** Supplier Functions
	// **************************************

	public String supplier(String name, String address, String city, String country, String person, String phoneno,
			String Email, String mobileno, String note) throws Throwable {

		driver.findElement(By.linkText("Suppliers")).click();

		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();

		String exp_value = driver.findElement(By.xpath("//*[@id='x_Supplier_Number']")).getAttribute("value");

		System.out.println(exp_value);

		// driver.findElement(By.id("x_Supplier_Name")).sendKeys(name); //
		// //*[@id="x_Supplier_Name"]

		driver.findElement(By.xpath("//*[@id='x_Supplier_Name']")).sendKeys(name);

		driver.findElement(By.id("x_Address")).sendKeys(address);
		driver.findElement(By.id("x_City")).sendKeys(city);
		driver.findElement(By.name("x_Country")).sendKeys(country);

		driver.findElement(By.id("x_Contact_Person")).sendKeys(person);
		driver.findElement(By.id("x_Phone_Number")).sendKeys(phoneno);

		driver.findElement(By.id("x__Email")).sendKeys(Email);
		driver.findElement(By.id("x_Mobile_Number")).sendKeys(mobileno);
		driver.findElement(By.id("x_Notes")).sendKeys(note);

		Thread.sleep(3000);
		Actions pageDown = new Actions(driver);
		pageDown.sendKeys(Keys.PAGE_DOWN).build().perform();

		// driver.findElement(By.xpath("//*[@id='btnAction']")).click();
		// ////*[@id="btnAction"]
		Thread.sleep(2000);
		driver.findElement(By.id("btnAction")).sendKeys(Keys.ENTER);

		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='OK!']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();

		Thread.sleep(5000);

		if (driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).isDisplayed()) {

			driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
			driver.findElement(By.id("psearch")).clear();
			driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(exp_value);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();

		} else {
			Thread.sleep(3000);
			driver.findElement(By.id("psearch")).clear();
			driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(exp_value);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();

		}

		String act_data = driver.findElement(By.xpath("//*[@id='r1_a_suppliers']/td[6]")).getText();

		System.out.println(act_data);

		if (exp_value.equals(act_data)) {
			res = "Pass";
		} else {
			res = "Fail";

		}

		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
		return res;
		// driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[2]/form/div[2]/div/div/div[2]/a[2]")).click();

		// String
		// sname=driver.findElement(By.xpath("//*[@id='r5_a_suppliers']/td[8]")).getText();

	}

	public String addStockItem(String iname) throws InterruptedException {

		Actions ac = new Actions(driver);
		WebElement st = driver.findElement(By.xpath("//*[@id='mi_a_stock_items']/a"));
		ac.moveToElement(st).build().perform();

		ac.moveToElement(driver.findElement(By.linkText("Stock Categories"))).click().build().perform();

		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();

		driver.findElement(By.xpath(" //*[@id='x_Category_Name']")).sendKeys(iname);

		driver.findElement(By.xpath("//*[@id='btnAction']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='OK!']")).click();

		Thread.sleep(5000);

		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();

		Thread.sleep(3000);

		if (driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).isDisplayed()) {

			driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
			driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(iname);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();

		} else {

			driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(iname);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();

		}

		Thread.sleep(5000);

		String ac_data = driver.findElement(By.xpath("//*[@id='r1_a_stock_categories']/td[4]")).getText();

		if (iname.equals(ac_data)) {
			res = "Pass";

		} else {
			res = "Fail";

		}

		return res;
	}

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub

		CommonFucntions obj = new CommonFucntions();

		// obj.appLaunch("www.google.com");

		PropertyFile1 propobj = new PropertyFile1();

		String Result = obj.appLaunch(propobj.getValueForKey("URL"));
		System.out.println(Result);
		String login = obj.appLogin(propobj.getValueForKey("UserName"), propobj.getValueForKey("PassWord"));
		System.out.println(login);

		// String result=obj.supplier("HP", "Malad", "Mumbai", "India",
		// "ShyamRats", "7718090103", "abc.xyz.rat@gmail.com", "7718090103",
		// "Note1234");

		// System.out.println(result);

		// String result=obj.addStockItem("shyamsinghr");
		// System.out.println(result);

		// supplier("HP", "Malad", "Mumbai", "India", "ShyamRathod",
		// "7718090103", "abc.xyz.rat@gmail.com", "7718090103", "Note1234" );

		// System.out.println(supp);

		// String logout=obj.appLogout();

		// System.out.println(logout);

	}

}
