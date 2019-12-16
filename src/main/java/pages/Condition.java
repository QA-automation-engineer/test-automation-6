package pages;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Vladimir Trandafilov on 16.12.2019.
 */
public enum Condition {
	
	PRESENCE(ExpectedConditions::presenceOfElementLocated),
	VISIBLE(ExpectedConditions::visibilityOfElementLocated),
	CLICKABLE(ExpectedConditions::elementToBeClickable);

	Condition(Function<By, ExpectedCondition<WebElement>> condition) {
		this.condition = condition;
	}

	private final Function<By, ExpectedCondition<WebElement>> condition;

	public Function<By, ExpectedCondition<WebElement>> getCondition() {
		return condition;
	}
}
