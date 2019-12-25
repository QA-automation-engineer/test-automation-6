package utils;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/**
 * Created by Vladimir Trandafilov on 23.12.2019.
 */
public class EventHandler extends AbstractWebDriverEventListener {

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		super.beforeNavigateTo(url, driver);
		System.out.println("Browser going to navigate to: " + url);
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		super.beforeFindBy(by, element, driver);
		if(element == null) {
			System.out.println("Finding element: " + by);
		} else {
			System.out.println("Finding element: " + by + " within root: " + getElementLocator(element));
		}
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		super.beforeClickOn(element, driver);
		System.out.println("Going to click on element: " + getElementLocator(element));
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		super.beforeChangeValueOf(element, driver, keysToSend);
		System.out.println("Going to set value: " + Arrays.toString(keysToSend) + " for element: " + getElementLocator(element));
	}

	private String getElementLocator(WebElement element) {
		String elDescription = element.toString();
		int descriptionLength = elDescription.length();
		int startIndex = elDescription.indexOf("-> ") + 3;
		return "by " + elDescription.substring(startIndex, descriptionLength-1);
	}
}
