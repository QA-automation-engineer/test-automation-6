package files;

import static org.hamcrest.CoreMatchers.is;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import de.redsix.pdfcompare.PdfComparator;
import pages.LoginPage;
import test.BaseGUITest;
import utils.FileDownloader;

/**
 * Created by Vladimir Trandafilov on 27.12.2019.
 */
public class FileDownloadTest extends BaseGUITest {

	@Test
	public void verifyDownloadMyOrder() throws Exception {
		// Given
		LoginPage loginPage = new LoginPage(driver);
		loginPage.visit();
		loginPage.logIn("trandafilov.vladimir@gmail.com", "password");
		$(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[1]/a/span")).click();
		waitFor(ExpectedConditions.titleContains("Order history"));
		// When
		FileDownloader fileDownloader = new FileDownloader(driver);
		fileDownloader.setURI($(By.xpath("//*[@id=\"order-list\"]/tbody/tr/td[6]/a")).getAttribute("href"));
		File actualFile = fileDownloader.downloadFile();
		int requestStatus = fileDownloader.getLastDownloadHTTPStatus();
		// Then
		assertAll(o -> Assert.assertThat("Check status.", requestStatus, is(200)),
				o -> {
					try {
						Assert.assertThat(new PdfComparator(new File("IN090063.pdf"), actualFile)
								.compare().writeTo("diffOutputOrder"), is(true));
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
	}

	@Test
	public void verifyDownloadMyOrderNegative() throws Exception {
		// Given
		LoginPage loginPage = new LoginPage(driver);
		loginPage.visit();
		loginPage.logIn("trandafilov.vladimir@gmail.com", "password");
		$(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[1]/a/span")).click();
		waitFor(ExpectedConditions.titleContains("Order history"));
		// When
		FileDownloader fileDownloader = new FileDownloader(driver);
		fileDownloader.setURI($(By.xpath("//*[@id=\"order-list\"]/tbody/tr/td[6]/a")).getAttribute("href"));
		File actualFile = fileDownloader.downloadFile();
		int requestStatus = fileDownloader.getLastDownloadHTTPStatus();
		// Then
		assertAll(o -> Assert.assertThat("Check status.", requestStatus, is(200)),
				o -> {
					try {
						Assert.assertThat(new PdfComparator(new File("IN090057.pdf"), actualFile)
								.compare().writeTo("diffOutputPass"), is(false));
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
	}
}
