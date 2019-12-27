package files;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.By;

import test.BaseGUITest;

/**
 * Created by Vladimir Trandafilov on 27.12.2019.
 */
public class FileUploadTest extends BaseGUITest {

	@Test
	public void verifyUploadingImageOnGoogle() {
		// Given
		open("https://www.google.com.ua/imghp?hl=uk&tab=wi");
		waitFor(titleContains("Google"));
		// When
		$(By.xpath("//*[@id=\"sbtc\"]/div/div[3]/div[1]/span")).click();
		$(By.linkText("Завантажте зображення")).click();
		String filePath = new File("").getAbsolutePath().concat("\\").concat("download.jpg");

		$(By.id("qbfile")).sendKeys(filePath);
		// Then
		waitFor(textToBePresentInElementLocated(By.xpath("//*[@id=\"topstuff\"]/div/div[2]/a"),
				"milliner (la modiste - renée vert)"));
	}
}
