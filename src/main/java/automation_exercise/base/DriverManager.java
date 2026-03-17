package automation_exercise.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.NoSuchDriverException;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverManager {

	private static WebDriver driver;

	private DriverManager() {
	}

	public static synchronized WebDriver getDriver(String browserName) {
		if (driver == null) {
			driver = createDriver(browserName);
		}
		return driver;
	}

	public static synchronized WebDriver getDriver() {
		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized. Call getDriver(browserName) first.");
		}
		return driver;
	}

	public static synchronized void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	private static WebDriver createDriver(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				return new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				return new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				return new EdgeDriver();
			}

			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		} catch (Exception e) {
			throw new NoSuchDriverException("Browser initialization failed: " + browserName, e);
		}
	}
}
