package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import api.BaseAPI;

/**
 * Created by Vladimir Trandafilov on 13.12.2019.
 */
public abstract class BasePage implements BaseAPI {
	
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	public WebDriver getDriver() {
		return driver;
	}
}
