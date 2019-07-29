package steps;

import model.Letter;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.*;

import static org.junit.Assert.assertEquals;

public class EmailSenderSteps extends ScenarioSteps {

	MainPage mainPage;
	MailBoxPage mailBoxPage;
	NewLetterPage newLetterPage;
	SentLetterListPage sentLetterListPage;
	SentLetterPage sentLetterPage;

	@Step("Given loggined as {0} page")
	public void ensure_login(String login, String password) {
		mainPage.openPage();
		mainPage.ensureLogin(login, password);

	}

	@Step("When the letter created")
	public void create_letter(Letter letter) {
		if (!getPages().isCurrentPageAt(MailBoxPage.class)) {
			mailBoxPage.open();
		}
		mailBoxPage.clickNewLetter();
		newLetterPage.sendEmail(letter);
		sentLetterPage.isOpened();
	}

	@Step("Then it appears in sent folder")
	public void should_be_as_last_sent(Letter letter) {
		mailBoxPage.openSentListPage();
		assertEquals(letter, sentLetterListPage.getLastLetter());
	}
}
