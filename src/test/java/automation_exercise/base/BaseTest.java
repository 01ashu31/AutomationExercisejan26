package automation_exercise.base;

import java.lang.reflect.Method;
import java.util.Properties;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import automation_exercise.pages.HomePage;
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
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: config.getProperty("browser");
		driver = DriverManager.getDriver(browserName);
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


	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		DriverManager.quitDriver();
		log.info("Browser closed successfully");
	}

}
