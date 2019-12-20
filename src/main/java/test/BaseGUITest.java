package test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import api.BaseAPI;

/**
 * Created by Vladimir Trandafilov on 13.12.2019.
 */
public abstract class BaseGUITest extends BaseTest implements BaseAPI {
	
	protected WebDriver driver;
	
	@Before
	public void openBrowser() {
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920,1080));
	}
	
	@After
	public void closeBrowser() {
		driver.quit();
	}

	@Override
	public WebDriver getDriver() {
		return driver;
	}
}
