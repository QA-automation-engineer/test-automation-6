package uicontrols;

import org.openqa.selenium.WebDriver;

import api.BaseAPI;

/**
 * Created by Vladimir Trandafilov on 27.12.2019.
 */
public class BaseControl implements BaseAPI {
	
	protected WebDriver driver;

	public BaseControl(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public WebDriver getDriver() {
		return driver;
	}
}
