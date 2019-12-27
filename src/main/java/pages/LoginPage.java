package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Vladimir Trandafilov on 27.12.2019.
 */
public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void visit(){
		open("http://automationpractice.com/index.php?controller=authentication");
		waitForDocumentCompleteState();
	}

	public void logIn(String email, String pwd) {
		$(By.id("email")).sendKeys(email);
		$(By.id("passwd")).sendKeys(pwd);
		$(By.id("SubmitLogin")).click();
		waitForDocumentCompleteState();
	}
}