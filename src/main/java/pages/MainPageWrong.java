package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Vladimir Trandafilov on 11.12.2019.
 */
public class MainPageWrong {
	
	WebDriver driver;
	
	// Don't do it cause it throws NoSuchElementException
	private WebElement searchInput = driver.findElement(By.id("search_query_top"));
	public WebElement firstTip = driver.findElement(By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]"));

	public MainPageWrong(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchFor(String query) {
		searchInput.click();
		searchInput.clear();
		searchInput.sendKeys(query);
	}
}
