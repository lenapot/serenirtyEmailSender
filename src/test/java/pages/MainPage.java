package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://e.mail.ru/")
public class MainPage extends Page {

	@FindBy(xpath = "//iframe[contains(@src, 'login')]")
	WebElementFacade iframeLogin;

	@FindBy(xpath = "//i[@id='PH_user-email' and not(contains(text(),'undefined'))]")
	WebElementFacade loginLink;

	@FindBy(xpath = "//input[@name='Login']")
	WebElement loginField;

	@FindBy(name = "Password")
	WebElement passwordField;

	@FindBy(css = "[data-test-id='submit-button']")
	WebElementFacade submitButton;


	@WhenPageOpens
	public void maximizeScreen() {
		getDriver().manage().window().maximize();
	}


	public void openPage() {
		this.open();
	}

	public boolean isLoggined() {
		System.out.println("isLoggined " + loginLink.isPresent());
		return loginLink.isPresent();
	}

	public void ensureLogin(String login, String password) {
		if (isLoggined()) return;
		switchToFrame(iframeLogin);
		typeInto(loginField, login);
		typeInto(passwordField, password);
		submitButton.waitUntilClickable();
		submitButton.click();
		switchToDefaultContent();
	}

}
