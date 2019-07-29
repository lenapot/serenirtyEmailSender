package pages;

import model.Letter;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.Set;

@DefaultUrl("https://e.mail.ru/messages/sent/")
@At("https://*.*.*/compose/*")
public class NewLetterPage extends Page {

	@FindBy(xpath = "//textarea[@data-original-name = 'To']")
	WebElement toField;

	@FindBy(name = "Subject")
	WebElement subjectField;

	@FindBy(xpath = "//iframe[contains(@id, 'composeEditor_ifr')]")
	WebElement iframeText;

	@FindBy(id = "tinymce")
	WebElement textField;

	private static By fileDataBy = By.cssSelector(".compose-attachments__input");

	@WhenPageOpens
	public void waitToOpen() {
		element(toField).waitUntilVisible();
	}

	public void sendEmail(Letter letter) {
		typeInto(toField, letter.getTo());
		typeInto(subjectField, letter.getSubject());
		setFile(letter.getFiles());
		setText(letter.getText());
		clickCntlEnter(subjectField);
	}

	private void setText(String text) {
		switchToFrame(iframeText);
		typeInto(textField, text);
		switchToDefaultContent();
	}

	private void setFile(Set<String> pathsToFile) {
		if (pathsToFile == null || pathsToFile.isEmpty()) return;
		for (String filePath : pathsToFile) {
			File file = new File(filePath);
			System.out.println("path = " + file.getAbsolutePath());
			getDriver().findElement(fileDataBy).sendKeys(file.getAbsolutePath());
			//upload(path).to(fileData);
		}
	}
}
