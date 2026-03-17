package automation_exercise.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.NoSuchDriverException;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverManager {

	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

	private DriverManager() {
	}

	public static WebDriver getDriver(String browserName) {
		WebDriver driver = DRIVER.get();
		if (driver == null) {
			driver = createDriver(browserName);
			DRIVER.set(driver);
		}
		return driver;
	}

	public static WebDriver getDriver() {
		WebDriver driver = DRIVER.get();
		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized. Call getDriver(browserName) first.");
		}
		return driver;
	}

	public static void quitDriver() {
		WebDriver driver = DRIVER.get();
		if (driver != null) {
			driver.quit();
			DRIVER.remove();
		}
	}

	private static WebDriver createDriver(String browserName) {
		String normalizedBrowser = browserName == null ? "" : browserName.trim().toLowerCase();
		boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
		try {
			switch (normalizedBrowser) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				if (headless) {
					chromeOptions.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
				}
				return new ChromeDriver(chromeOptions);
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				if (headless) {
					firefoxOptions.addArguments("-headless");
				}
				return new FirefoxDriver(firefoxOptions);
			case "edge":
				WebDriverManager.edgedriver().setup();
				EdgeOptions edgeOptions = new EdgeOptions();
				if (headless) {
					edgeOptions.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
				}
				return new EdgeDriver(edgeOptions);
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browserName);
			}
		} catch (Exception e) {
			throw new NoSuchDriverException("Browser initialization failed: " + browserName, e);
		}
	}
}
