 package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	
	// Private constructor prevents instantiation from outside
	//Make the constructor of the class private, so that no other instances can be created.
    private ExtentManager() {
        // Prevent reflection attacks
        if (extent != null) {
            throw new RuntimeException("Use getReport() method to create instance");
        }
    }
    
	public static synchronized  ExtentReports getReport() {
		if(extent == null) {
			
			String reportDir =
	                System.getProperty("user.dir") + "/reports/";
			
			FileUtils.createDirectoryIfNotExists(reportDir);
			ExtentSparkReporter spark= new ExtentSparkReporter(reportDir + "ExtentReport.html");
			spark.config().setReportName("Automation Report for Automation Exercise 26");
			spark.config().setDocumentTitle("Test execution Report");
			
			extent= new ExtentReports();
			extent.attachReporter(spark);
		}
		return extent;
	}

}
