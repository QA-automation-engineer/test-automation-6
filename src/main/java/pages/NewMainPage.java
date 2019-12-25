package pages;

import static pages.Condition.CLICKABLE;
import static pages.Condition.VISIBLE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Vladimir Trandafilov on 13.12.2019.
 */
public class NewMainPage extends BasePage {
	
	private By searchInput = By.id("search_query_top");
	public By firstTip = By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]");

	public NewMainPage(WebDriver driver) {
		super(driver);
	}

	public void searchFor(String query) {
		$(searchInput, CLICKABLE).click();
		$(searchInput).clear();
		$(searchInput).sendKeys(query);
	}
	
	public String getFirstTipText() {
		return $(firstTip, VISIBLE).getText();
	}
	
	public WebElement waitForHoverOnFirstTip() {
		return waitFor(CustomConditions.attributeContains(firstTip, "class", "ac_over"));
	}
	
	public void moveToFacebook() {
		executeScript("arguments[0].scrollIntoView(true);", $("#facebook_block"));
	}
	
	public void visit() {
		open("http://automationpractice.com/index.php");
		waitForDocumentCompleteState();
	}
}
