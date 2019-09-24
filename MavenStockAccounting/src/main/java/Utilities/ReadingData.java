package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadingData {

	public static void main(String[] args) throws Throwable {
	
		Properties configproperties=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\shyam.r\\workspace\\methoDemo\\Utilitesfiles\\Evironment.properties");
		configproperties.load(fis);
		
		System.out.println(configproperties.getProperty("Browser"));
		System.out.println(configproperties.getProperty("URL"));
		System.out.println(configproperties.getProperty("UserName"));
		System.out.println(configproperties.getProperty("PassWord"));
		// supp
		
		
		ExcelFileUtil obj=new ExcelFileUtil();
		int rowcnt=obj.rowCount("supp");
		
		//String rc=String.valueOf(rowcnt);
		
		int colcnt=obj.colCount("supp", rowcnt);
		
	}

}
