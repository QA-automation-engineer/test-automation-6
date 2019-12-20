package lesson02.d_added_simple_api;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import test.BaseGUITest;

/**
 * Created by Vladimir Trandafilov on 06.12.2019.
 */
public class MyFirstTest extends BaseGUITest {
	
	@Test
	public void name() {
		// arrange
		open("https://www.google.com/");
		// act
		sendKeys(".gLFyf", "selenide");
		getActions()
				.sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.RETURN)
				.perform();
		// assertion
		waitFor(ExpectedConditions.titleContains("selenide"));
		assertAll(
				o -> Assert.assertEquals("Selenide", getDriver().getTitle()),
				o -> Assert.assertEquals("selenide - Пошук Google", getDriver().getTitle()),
				o -> Assert.assertEquals("selenide", getDriver().getTitle())
		);
	}
}
