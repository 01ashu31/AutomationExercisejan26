package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import automation_exercise.base.BaseTest;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListner extends BaseTest implements ITestListener {

	private static ExtentReports extent = ExtentManager.getReport();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		test.set(extent.createTest(result.getMethod().getMethodName()));
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test case is Pass");
	}
	
	public void onTestFailure(ITestResult result) {
		String path=ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
		test.get().fail(result.getThrowable());
		test.get().addScreenCaptureFromPath(path);
	}
	
	public void onFinish(ITestContext contect) {
		extent.flush();
	}

}
