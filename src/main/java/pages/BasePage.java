package pages;

import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Vladimir Trandafilov on 13.12.2019.
 */
public abstract class BasePage {

	protected final Function<By, ExpectedCondition<WebElement>> VISIBLE = ExpectedConditions::visibilityOfElementLocated;
	protected final Function<By, ExpectedCondition<WebElement>> CLICKABLE = ExpectedConditions::elementToBeClickable;
	
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	protected WebElement $(By locator, Function<By, ExpectedCondition<WebElement>> condition) {
		return waitFor(condition.apply(locator));
	}
	
	protected WebElement $(By locator) {
		return waitFor(ExpectedConditions.presenceOfElementLocated(locator));
	}

	protected WebElement $(String css) {
		return $(By.cssSelector(css));
	}

	protected List<WebElement> $$(By locator) {
		return waitFor(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	protected List<WebElement> $$(By locator, int number) {
		return waitFor(ExpectedConditions.numberOfElementsToBe(locator, number));
	}

	protected List<WebElement> $$(By locator, int number, boolean isMoreThan) {
		return waitFor(isMoreThan ? ExpectedConditions.numberOfElementsToBeMoreThan(locator, number) :
				ExpectedConditions.numberOfElementsToBeLessThan(locator, number));
	}

	protected List<WebElement> $$(String css) {
		return $$(By.cssSelector(css));
	}
	
	protected <T> T waitFor(ExpectedCondition<T> condition, long timeout) {
		return new WebDriverWait(driver, timeout).until(condition);
	}

	protected <T> T waitFor(ExpectedCondition<T> condition) {
		return waitFor(condition, 10l);
	}
}
