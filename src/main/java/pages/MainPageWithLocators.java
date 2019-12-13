package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Vladimir Trandafilov on 11.12.2019.
 */
public class MainPageWithLocators {
	
	WebDriver driver;
	
	private By searchInput = By.id("search_query_top");
	public By firstTip = By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]");

	public MainPageWithLocators(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchFor(String query) {
		$(searchInput).click();
		$(searchInput).clear();
		$(searchInput).sendKeys(query);
	}
	
	private WebElement $(By locator) {
		return driver.findElement(locator);
	}

	private WebElement $(String css) {
		return $(By.cssSelector(css));
	}

	private List<WebElement> $$(By locator) {
		return driver.findElements(locator);
	}

	private List<WebElement> $$(String css) {
		return $$(By.cssSelector(css));
	}
}
