package utils;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/**
 * Created by Vladimir Trandafilov on 23.12.2019.
 */
public class EventHandler extends AbstractWebDriverEventListener {

	private final Logger LOG = LogManager.getLogger(EventHandler.class);

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		super.beforeNavigateTo(url, driver);
		LOG.info("Browser going to navigate to: {}", url);
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		super.beforeFindBy(by, element, driver);
		if(element == null) {
			LOG.debug("Finding element: {}", by);
		} else {
			LOG.debug("Finding element: {} within root: {}", by, getElementLocator(element));
		}
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		super.beforeClickOn(element, driver);
		LOG.debug("Going to click on element: {}", getElementLocator(element));
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		super.beforeChangeValueOf(element, driver, keysToSend);
		LOG.info("Going to set value: {} for element: {}", Arrays.toString(keysToSend), getElementLocator(element));
	}

	private String getElementLocator(WebElement element) {
		String elDescription = element.toString();
		int descriptionLength = elDescription.length();
		int startIndex = elDescription.indexOf("-> ") + 3;
		return "by " + elDescription.substring(startIndex, descriptionLength-1);
	}
}
