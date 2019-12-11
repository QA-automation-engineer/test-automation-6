package lesson01;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import test.BaseTest;

/**
 * Created by Vladimir Trandafilov on 13.11.2019.
 */

@RunWith(JUnit4.class)
public class MyFirstTestSuiteTest extends BaseTest {

	@BeforeClass
	public static void suiteSetUp() {
		System.out.println("Suite pre-conditions");
	}

	@AfterClass
	public static void suiteTearDown() {
		System.out.println("Suite post-conditions");
	}

	@Before
	public void beforeEach() {
		System.out.println("Pre-condition for each test");
	}

	@After
	public void afterEach() {
		System.out.println("Post-condition for each test");
	}

	@Test
	public void firstTest() {
		// arrange
		// act
		// assert
		Assert.assertTrue("Some Page wasn't opened", false);
	}

	@Test
	public void secondTest() {
		// arrange
		String expected = "Hello";
		String actual = "Hello1";
		// act
		// assertion
		Assert.assertTrue(expected.equals(actual));
	}

	@Test
	public void thirdTest() {
		// arrange
		String expected = "Hello";
		String actual = "Hello1";
		// act
		// assertion
		Assert.assertEquals(
				String.format("Actual '%s' wasn't equal to expected '%s'", actual, expected),
				expected,
				actual
		);
	}

	@Test
	@Ignore
	public void fourthTest() {
		// arrange
		String expectedPart = "Hello1";
		String actual = "Hello";
		// act
		// assertion
		Assert.assertTrue(actual.contains(expectedPart));
	}

	@Test
	public void fifthTest() {
		// arrange
		String expectedPart = "Hello1";
		String actual = "Hello";
		// act
		// assertion
		Assert.assertThat(actual, CoreMatchers.containsString(expectedPart));
	}

	@Test(timeout = 1000L)
	public void timeoutTest() throws InterruptedException {
		// arrange
		// act
		Thread.sleep(1500);
		// assertion
	}
}
