package uicontrols;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Vladimir Trandafilov on 27.12.2019.
 */
public class Table extends BaseControl {
	
	private By tableRootLocator;

	public Table(WebDriver driver, By tableRootLocator) {
		super(driver);
		this.tableRootLocator = tableRootLocator;
	}
	
	public WebElement getCell(int colNo, int rowNo) {
		try {
			List<WebElement> rows = getTableRootElement().findElements(By.tagName("tr"));
			WebElement row = rows.get(rowNo - 1);
			List<WebElement> columns = row.findElements(By.tagName("td"));
			return columns.get(colNo - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(
					String.format("Row %d or col %d doesn't exist", rowNo, colNo)
			);
		} catch (TimeoutException te) {
			throw new IllegalStateException("Table wasn't loaded");
		}
	}
	
	private WebElement getTableRootElement() {
		return $(tableRootLocator);
	}
}
