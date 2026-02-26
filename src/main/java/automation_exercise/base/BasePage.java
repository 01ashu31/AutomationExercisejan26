package automation_exercise.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	protected Logger log= LogManager.getLogger(this.getClass());
	
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
		
	}
	
	protected abstract String getPageTitle() ;
	
	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	
	protected WebElement waitForVisibility(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	protected WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
	
	protected Boolean waitForTextToBePresent(WebElement element , String text) {
		return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	protected void waitForUrlContains(String fraction) {
        wait.until(ExpectedConditions.urlContains(fraction));
    }
	
	

}
