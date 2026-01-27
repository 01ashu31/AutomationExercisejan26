package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import automation_exercise.base.BaseTest;
import net.bytebuddy.asm.Advice.This;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListner implements ITestListener {

//	private static ExtentReports extent = ExtentManager.getReport();
//	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static final Logger log = LogManager.getLogger(This.class);

	@Override
	public void onTestStart(ITestResult result) {
		log.info("Test Started: {}", result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		BaseTest.extentTest.get().pass("Test Passed");
		log.info("Test Passed: {}", result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {

		log.error("Test Failed: {}", result.getMethod().getMethodName(), result.getThrowable());

		ExtentTest test = BaseTest.extentTest.get();
		test.fail(result.getThrowable());

		Object testClass = result.getInstance();
		BaseTest baseTest = (BaseTest) testClass;

		String screenshotPath = ScreenshotUtil.captureScreenshot(baseTest.driver, result.getMethod().getMethodName());

		test.addScreenCaptureFromPath(screenshotPath);
	}

	@Override
	public void onFinish(ITestContext context) {
		log.info("Test Execution Finished");
	}

}
