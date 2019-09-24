package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile1 {

	public static String getValueForKey(String key) throws IOException
	{
		
		Properties configproperties=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\shyam.r\\workspace\\MavenStockAccounting\\Utilitesfiles\\Evironment.properties");
		configproperties.load(fis);		
		return configproperties.getProperty(key);
		
		
		
	}
	
}
