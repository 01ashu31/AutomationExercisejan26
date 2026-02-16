package automation_exercise.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import javax.management.RuntimeErrorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.remote.NoSuchDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import automation_exercise.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentManager;
import utils.PropertyManager;

public class BaseTest {
//	 Properties prop;
	public WebDriver driver;
	protected HomePage homePage;
	protected Properties config;
	protected Properties testData;

	protected static final Logger log = LogManager.getLogger(BaseTest.class);
	protected static ExtentReports extent;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@BeforeSuite(alwaysRun = true)
	public void setUpReport() {
		extent = ExtentManager.getReport();
		log.info("Extent Report initialized");
	}

	@AfterSuite(alwaysRun = true)
	public void tearDownReport() {
		if (extent != null) {
			extent.flush();
			log.info("Extent Report flushed after suite execution");
		}
	}


	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method) {
		// 🔥 Create ExtentTest BEFORE test execution
	    ExtentTest test = extent.createTest(method.getName());
	    extentTest.set(test);
		config = PropertyManager.getConfig();
		testData = PropertyManager.getTestData();
		initilizeDriver();
		driver.manage().window().maximize();
		driver.get(config.getProperty("url"));
		log.info("Browser launched and URL loaded");
//		 return homePage;
	}

//	private void loadConfig()  {
//		prop=new Properties();
//		try {
//			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +"//src//main//java//resources//config.properties");
//			prop.load(fis);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeErrorException(null, "Failed to load config.Properties");
//			
//		}

//		InputStream input= getClass().getClassLoader().getResourceAsStream("config.properties");
//		prop.load(input);
//	}

	private void initilizeDriver() {
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: config.getProperty("browser");

		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}

			else if (browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else {
				throw new IllegalArgumentException("Unsupported browser" + browserName);
			}
		} catch (Exception e) {
			throw new NoSuchDriverException("Browser initialzed failed: " + browserName, e);
		}

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			log.info("Browser closed successfully");
		}
	}

}
