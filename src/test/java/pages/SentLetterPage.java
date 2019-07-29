package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.support.FindBy;


public class SentLetterPage extends Page {
	@FindBy(css = ".message-sent__title")
	WebElementFacade sent;

	@WhenPageOpens
	public void isOpened() {
		element(sent).waitUntilVisible();
	}
}
