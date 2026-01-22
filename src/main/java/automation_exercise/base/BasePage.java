package automation_exercise.base;

import org.openqa.selenium.WebDriver;

public class BasePage {
	
	protected WebDriver driver;
	
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		
	}
	
	protected String getPageTitle() {
		return driver.getTitle();
	}
	
	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

}
