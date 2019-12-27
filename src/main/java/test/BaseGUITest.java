package test;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import api.BaseAPI;
import utils.EventHandler;

/**
 * Created by Vladimir Trandafilov on 13.12.2019.
 */
public abstract class BaseGUITest extends BaseTest implements BaseAPI {

	private final Logger LOG = LogManager.getLogger(BaseGUITest.class);
	
	protected WebDriver driver;
	
	@Before
	public void openBrowser() {
		EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(new ChromeDriver());
		eventFiringWebDriver.register(new EventHandler());
		driver = eventFiringWebDriver;
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920,1080));
	}

	@Override
	public WebDriver getDriver() {
		return driver;
	}

	@Override
	protected TestWatcher getWatcher() {
		return new TestWatcher() {
			@Override
			protected void succeeded(final Description description) {
				LOG.info("Test '{}' - PASSED\n", description.getMethodName());
				super.succeeded(description);
			}

			@Override
			protected void failed(final Throwable e, final Description description) {
				captureScreenshot(description.getMethodName());
				LOG.error("Test '{}' - FAILED\nReason: {}\n", description.getMethodName(), e.getMessage());
				super.failed(e, description);
			}

			@Override
			protected void starting(final Description description) {
				LOG.info("Test '{}' - starting...", description.getMethodName());
				super.starting(description);
			}

			@Override
			protected void finished(Description description) {
				super.finished(description);
				driver.quit();
			}
		};
	}
}
