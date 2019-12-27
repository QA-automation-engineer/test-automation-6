package lesson02.b_explicit_waits_work_with_proxy_elements;

import java.util.concurrent.TimeUnit;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.MainPage;
import test.BaseTest;

/**
 * Created by Vladimir Trandafilov on 06.12.2019.
 */
public class MyFirstTest extends BaseTest {
	
	WebDriver driver;
	
	@Before
	public void beforeEach() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();
	}
	
	@After
	public void afterEach() {
		driver.quit();
		driver = null;
	}

	@Test
	public void verifyTipRefreshing_WaitWithElement() {
		// arrange
		final String firstQueryText = "Dress";
		final String secondQueryText = "T-shirt";
		driver.findElement(By.id("search_query_top")).click();
		driver.findElement(By.id("search_query_top")).sendKeys(firstQueryText);
		// act
		driver.findElement(By.id("search_query_top")).click();
		driver.findElement(By.id("search_query_top")).clear();
		driver.findElement(By.id("search_query_top")).sendKeys(secondQueryText);
		WebElement firstTip = driver.findElement(By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]"));
		// assertion
		(new WebDriverWait(driver,10))
				.until(ExpectedConditions.textToBePresentInElement(firstTip, secondQueryText));
		String secondTipText = driver.findElement(By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]"))
				.getText();// and not(contains(text(), 'Dress'))
		Assert.assertThat(secondTipText, CoreMatchers.containsString(secondQueryText));
	}
	
	@Test
	public void verifyTipRefreshing_WaitWithLocator() {
		// arrange
		final String firstQueryText = "Dress";
		final String secondQueryText = "T-shirt";
		driver.findElement(By.id("search_query_top")).click();
		driver.findElement(By.id("search_query_top")).sendKeys(firstQueryText);
		// act
		driver.findElement(By.id("search_query_top")).click();
		driver.findElement(By.id("search_query_top")).clear();
		driver.findElement(By.id("search_query_top")).sendKeys(secondQueryText);
		WebElement firstTip = driver.findElement(By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]"));
		// assertion
		(new WebDriverWait(driver,10))
				.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]"), secondQueryText));
		String secondTipText = driver.findElement(By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]"))
				.getText();// and not(contains(text(), 'Dress'))
		Assert.assertThat(secondTipText, CoreMatchers.containsString(secondQueryText));
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
