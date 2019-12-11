package lesson02.a_implicit_waits_help_us;

import java.util.concurrent.TimeUnit;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public void verifyFirstTip() {
		// arrange
		final String queryText = "Dress";
		// act
		driver.findElement(By.id("search_query_top")).click();
		driver.findElement(By.id("search_query_top")).sendKeys(queryText);
		// assertion
		String firstTipText = driver.findElement(By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]"))
				.getText();
		Assert.assertThat(firstTipText, CoreMatchers.containsString(queryText));
	}

	@Test
	public void verifyTipRefreshing() {
		// arrange
		final String firstQueryText = "Dress";
		final String secondQueryText = "T-shirt";
		driver.findElement(By.id("search_query_top")).click();
		driver.findElement(By.id("search_query_top")).sendKeys(firstQueryText);
		// act
		driver.findElement(By.id("search_query_top")).click();
		driver.findElement(By.id("search_query_top")).clear();		
		driver.findElement(By.id("search_query_top")).sendKeys(secondQueryText);
		// assertion
		String secondTipText = driver.findElement(By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]"))
				.getText();// and not(contains(text(), 'Dress'))
		Assert.assertThat(secondTipText, CoreMatchers.containsString(secondQueryText));
	}

	@Test
	public void verifyTipRefreshingFIXED() {
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
		(new WebDriverWait(driver,10)).until(ExpectedConditions.stalenessOf(firstTip));
		//waitForElementDisappearing(By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[position()=1 and contains(text(), 'Dress')]"), 5000l);
		// assertion
		String secondTipText = driver.findElement(By.xpath("//*[@id='index']/div[@class='ac_results']/ul/li[1]"))
				.getText();// and not(contains(text(), 'Dress'))
		Assert.assertThat(secondTipText, CoreMatchers.containsString(secondQueryText));
	}

	void waitForElementDisappearing(By locator, long timeout) {
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
		long startTime = System.currentTimeMillis();
		while(!driver.findElements(locator).isEmpty()) {
			if(System.currentTimeMillis() - startTime > timeout) {
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				throw new TimeoutException("Element is still present");
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
}
