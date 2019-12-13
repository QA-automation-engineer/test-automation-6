package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Vladimir Trandafilov on 13.12.2019.
 */
public class NewMainPage extends BasePage {
	
	private By button = By.xpath("fdfdfdf");
	private By text = By.xpath("fdfdfdfdfdfdfdf");
	
	public NewMainPage(WebDriver driver) {
		super(driver);
	}
	
	public String getSomeText() {
		return $(text, VISIBLE).getText();
	}
	
	public void clickSomeBtn() {
		$(button, CLICKABLE).click();
	}
}
