package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFun.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript {

	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	

	public void startTest() throws Throwable {
		ExcelFileUtil excel = new ExcelFileUtil();

		// Master TestCases Sheet

		for (int i = 1; i <=excel.rowCount("MasterTestCases"); i++) {
			String ModuleStatus = ""; // 2nd round
			if (excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				// Define Module Name
				String TCModule = excel.getData("MasterTestCases", i, 1);
				// screenshots
				report=new ExtentReports("C:\\Users\\shyam.r\\workspace\\MavenStockAccounting\\Reports\\" +TCModule+FunctionLibrary.generateDate()+".html");
				int rowcount = excel.rowCount(TCModule);
System.out.println(rowcount);
				// TCModule Sheet
				for (int j = 1; j<=rowcount; j++)
				{
					String Description = excel.getData(TCModule, j, 0);
					String Object_Type = excel.getData(TCModule, j, 1);
					System.out.println(Object_Type);
					String Locator_Type = excel.getData(TCModule, j, 2);
					String Locator_Value = excel.getData(TCModule, j, 3);
					String Test_Data = excel.getData(TCModule, j, 4);

					try {
						if (Object_Type.equalsIgnoreCase("startBrowser"))
						{
							driver = FunctionLibrary.startBrowser(driver);
							//logger.log(LogStatus.INFO, Description);
						}
						if (Object_Type.equalsIgnoreCase("openApplication")) 
						{
							FunctionLibrary.openApplication(driver);
							//logger.log(LogStatus.INFO, Description);
						}

						if (Object_Type.equalsIgnoreCase("typeAction")) {
							FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
							//logger.log(LogStatus.INFO, Description);
						}

						if (Object_Type.equalsIgnoreCase("clickAction")) {
							FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
							//logger.log(LogStatus.INFO, Description);
						}

						if (Object_Type.equalsIgnoreCase("closeBrowser")) {
							FunctionLibrary.closeBrowser(driver);
							//logger.log(LogStatus.INFO, Description);
						}

						if (Object_Type.equalsIgnoreCase("waitForElement")) {
							FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
							//logger.log(LogStatus.INFO, Description);
						}
						
						if (Object_Type.equalsIgnoreCase("pageDown")) {
							FunctionLibrary.pageDown(driver);
							//logger.log(LogStatus.INFO, Description);
						}
						
						if (Object_Type.equalsIgnoreCase("captureData")) {
							FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
							//logger.log(LogStatus.INFO, Description);
						}
						
						if (Object_Type.equalsIgnoreCase("tableValidation")) {
							FunctionLibrary.tableValidation(driver, Test_Data);
							//logger.log(LogStatus.INFO, Description);
						}
						
						// Status Updated in TCModule sheet "Pass"
						excel.setData(TCModule, j, 5, "PASS");
						ModuleStatus = "true";
					} catch (Exception e)
					{
						// Status Updated in TCModule sheet "Fail"
						excel.setData(TCModule, j, 5, "FAIL");
						ModuleStatus = "false";
						
			File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("C:\\Users\\shyam.r\\workspace\\MavenStockAccounting\\ScreenShots\\" +TCModule+" "+FunctionLibrary.generateDate()+".png"));
			break;
				}
				}
				if (ModuleStatus.equalsIgnoreCase("true"))
				{
					// Status updated in MasterTestCases Sheet "pass"
					excel.setData("MasterTestCases", i, 3, "PASS");
				} else 
					if (ModuleStatus.equalsIgnoreCase("false"))
					{
					// Status updated in MasterTestCases Sheet "fAILE"
					excel.setData("MasterTestCases", i, 3, "FAIL");
				}
			} 
			
			else
			{
				
				excel.setData("MasterTestCases", i, 3, "Not Executed");
			}

		}
		
	}

}
