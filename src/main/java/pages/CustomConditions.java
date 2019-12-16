package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by Vladimir Trandafilov on 16.12.2019.
 */
public class CustomConditions {

	public static ExpectedCondition<WebElement> attributeContains(By locator, String attr, String value) {
		return new ExpectedCondition<WebElement>() {
			private String currentValue = null;

			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(locator);
				currentValue = element.getAttribute(attr);

				if (currentValue != null && !currentValue.isEmpty() && currentValue.contains(value))
					return element;
				else
					return null;
			}

			public String toString() {
				return String
						.format("found by %s attr='%s' to contain \"%s\". Current value: \"%s\"", locator, attr, value,
								this.currentValue);
			}
		};
	}
}
