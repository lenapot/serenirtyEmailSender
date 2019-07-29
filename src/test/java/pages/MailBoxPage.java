package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://e.mail.ru/messages/inbox/")
@At("https://*.*.*/messages/inbox/*")
public class MailBoxPage extends Page {

	@FindBy(id = "b-nav_folders")
	WebElement navigator;

	@FindBy(css = "[data-name='compose']")
	WebElementFacade newLetterButton;

	@FindBy(xpath = "//a[@href='/messages/sent/']")
	WebElementFacade sentHref;

	private static By sentHrefBy = By.xpath("//a[@href='/messages/sent/']");

	@WhenPageOpens
	public void waitToOpen() {
		newLetterButton.waitUntilClickable();
	}

	public NewLetterPage clickNewLetter() {
		newLetterButton.click();
		return new NewLetterPage();
	}

	public SentLetterListPage openSentListPage() {
		clickTryTwice(sentHrefBy);
		return new SentLetterListPage();
	}
}
