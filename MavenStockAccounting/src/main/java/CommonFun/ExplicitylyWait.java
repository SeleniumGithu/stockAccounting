package CommonFun;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitylyWait {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "D:\\Shyam\\chromedriver.exe");
  
		WebDriver driver = new ChromeDriver();
		driver.get("Http://gmail.com");
		driver.manage().window().maximize();
		
		WebDriverWait myWait=new WebDriverWait(driver, 20);
		
		driver.findElement(By.id("identifierId")).sendKeys("abc.xyz.rat@gmail.com");
		driver.findElement(By.id("identifierNext")).click();
		
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));		
		driver.findElement(By.name("password")).sendKeys("Password@123");
		
	}

}
