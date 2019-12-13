package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Vladimir Trandafilov on 11.12.2019.
 */
public class MainPage {
	
	@FindBy(id = "search_query_top")
	@CacheLookup
	private WebElement searchInput;
	
	@FindBy(xpath = "//*[@id='index']/div[@class='ac_results']/ul/li[1]")
	//@CacheLookup don't cache everything
	public WebElement firstTip;

	public MainPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void searchFor(String query) {
		searchInput.click();
		searchInput.clear();
		searchInput.sendKeys(query);
	}
}
