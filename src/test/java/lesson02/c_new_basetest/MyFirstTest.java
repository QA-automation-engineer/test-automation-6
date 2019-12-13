package lesson02.c_new_basetest;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.MainPage;
import test.BaseGUITest;

/**
 * Created by Vladimir Trandafilov on 06.12.2019.
 */
public class MyFirstTest extends BaseGUITest {
	
	@Before
	public void beforeEach() {
		driver.get("http://automationpractice.com/index.php");
	}	

	@Test
	public void verifyTipRefreshing_WithPageObject() {
		// arrange
		MainPage mainPage = new MainPage(driver);
		final String firstQueryText = "Dress";
		final String secondQueryText = "T-shirt";
		
		mainPage.searchFor(firstQueryText);
		// act
		mainPage.searchFor(secondQueryText);
		// assertion
		(new WebDriverWait(driver,10))
				.until(ExpectedConditions.textToBePresentInElement(mainPage.firstTip, secondQueryText));
		String secondTipText = mainPage.firstTip.getText();
		// and not(contains(text(), 'Dress'))
		Assert.assertThat(secondTipText, CoreMatchers.containsString(secondQueryText));
	}

	
}
