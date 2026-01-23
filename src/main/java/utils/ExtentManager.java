package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getReport() {
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
