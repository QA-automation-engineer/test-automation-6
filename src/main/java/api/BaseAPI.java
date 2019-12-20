package api;

import static pages.Condition.CLICKABLE;
import static pages.Condition.PRESENCE;

import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.Condition;

/**
 * Created by Vladimir Trandafilov on 20.12.2019.
 */
public interface BaseAPI {
	
	WebDriver getDriver();

	default WebElement $(By locator, Function<By, ExpectedCondition<WebElement>> condition) {
		return waitFor(condition.apply(locator));
	}

	default WebElement $(By locator, Condition condition) {
		return waitFor(condition.getCondition().apply(locator));
	}

	default WebElement $(By locator) {
		return $(locator, PRESENCE);
	}

	default WebElement $(String css) {
		return $(By.cssSelector(css));
	}

	default WebElement $(String css, Condition condition) {
		return $(By.cssSelector(css), condition);
	}
	
	default void click(By locator) {
		$(locator, CLICKABLE).click();
	}

	default void click(String css) {
		click(By.cssSelector(css));
	}
	
	default void sendKeys(By locator, CharSequence... keys) {
		$(locator,CLICKABLE).sendKeys(keys);
	}

	default void sendKeys(String css, CharSequence... keys) {
		sendKeys(By.cssSelector(css), keys);
	}

	default List<WebElement> $$(By locator) {
		return waitFor(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	default List<WebElement> $$(By locator, int number) {
		return waitFor(ExpectedConditions.numberOfElementsToBe(locator, number));
	}

	default List<WebElement> $$(By locator, int number, boolean isMoreThan) {
		return waitFor(isMoreThan ? ExpectedConditions.numberOfElementsToBeMoreThan(locator, number) :
				ExpectedConditions.numberOfElementsToBeLessThan(locator, number));
	}

	default List<WebElement> $$(String css) {
		return $$(By.cssSelector(css));
	}

	default <T> T waitFor(ExpectedCondition<T> condition, long timeout) {
		return new WebDriverWait(getDriver(), timeout).until(condition);
	}

	default <T> T waitFor(ExpectedCondition<T> condition) {
		return waitFor(condition, 10l);
	}

	default void open(String url) {
		getDriver().get(url);
	}
	
	default Actions getActions() {
		return new Actions(getDriver());
	}
}
