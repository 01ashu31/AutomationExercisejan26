package utils;

import java.io.File;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtil {
	
	public static String captureScreenshot(WebDriver driver, String TestCaseName) {
		
		String dirPath = System.getProperty("user.dir")
	            + "/reports/screenshots/";
		
		FileUtils.createDirectoryIfNotExists("screenshots");
		
		try {
			File src= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String filePath=dirPath +TestCaseName +System.currentTimeMillis()+".png";
			FileHandler.copy(src,new File(filePath));
			return filePath;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

}
