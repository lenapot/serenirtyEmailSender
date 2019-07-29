package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.*;

import java.util.logging.Logger;

public class Page extends PageObject {

	protected static final Logger log = Logger.getLogger(Page.class.getName());

	public Page(WebDriver driver) {
		super(driver);
	}

	public Page() {
		super();
	}

	public void clickTryTwice(By element) {
		try {
			find(element).click();
		} catch (StaleElementReferenceException e) {
			log.info("second try to click " + element);
			find(element).click();
		}
		log.info("click by " + element);
	}

	public String getText(WebElement element, By childBy) {
		return element.findElement(childBy).getText();
	}

	public void clickCntlEnter(WebElement element) {
		String keysPressed = Keys.chord(Keys.CONTROL, Keys.RETURN);
		element.sendKeys(keysPressed);
	}

	public void switchToFrame(WebElement frame) {
		getDriver().switchTo().frame(frame);
	}

	protected void switchToDefaultContent() {
		getDriver().switchTo().defaultContent();
	}
}
