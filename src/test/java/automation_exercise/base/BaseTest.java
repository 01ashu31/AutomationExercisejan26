package automation_exercise.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.remote.NoSuchDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import automation_exercise.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.PropertyManager;

public class BaseTest {
//	 Properties prop;
	protected WebDriver driver;
	HomePage homePage;
	protected Properties config;

	@BeforeMethod
	public void setUp() {
		config = PropertyManager.getConfig();
		initilizeDriver();
		driver.manage().window().maximize();
		driver.get(config.getProperty("url"));
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}

	}

}
