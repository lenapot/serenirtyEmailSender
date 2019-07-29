package pages;

import model.Letter;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://e.mail.ru/messages/sent/")
@At("https://*.*.*/messages/sent/*")
public class SentLetterListPage extends Page {

	@FindBy(css = "div.b-nav__item_active a[href='/messages/sent/']")
	WebElementFacade sentLinkActive;

	@FindBy(xpath = "(//div[@id='b-letters']//div[contains(@class, 'b-datalist_letters_compact_to')]//div[@data-bem='b-datalist__item'])[1]//div[@class='b-datalist__item__addr']")
	WebElement lastLetterTo;

	@FindBy(xpath = "(//div[@id='b-letters']//div[contains(@class, 'b-datalist_letters_compact_to')]//div[@data-bem='b-datalist__item'])[1]//div[@class='b-datalist__item__subj']")
	WebElement lastLetterSubject;

	@WhenPageOpens
	public void isOpened() {
		sentLinkActive.waitUntilPresent();
	}

	public Letter getLastLetter() {
		Letter last = new Letter();
		last.setTo(lastLetterTo.getText());
		last.setSubject(getSubject());
		return last;
	}

	private String getSubject() {
		String allText = lastLetterSubject.getText();
		return allText;
	}
}
