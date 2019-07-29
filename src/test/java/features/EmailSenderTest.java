package features;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Letter;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Steps;

import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.EmailSenderSteps;
import utils.PropertyReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static net.thucydides.core.annotations.ClearCookiesPolicy.Never;

@RunWith(SerenityParameterizedRunner.class)
public class EmailSenderTest {

	@TestData
	public static Collection<Object[]> testData() throws IOException {
		String dataFile= PropertyReader.getInstance().getProperty("lettersData");
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(dataFile)))) {
			String json = "";
			String line = reader.readLine();
			while (line != null) {
				json += line;
				line = reader.readLine();
			}
			Gson gson = new Gson();
			List<Letter> letters = gson.fromJson(json, new TypeToken<List<Letter>>() {}.getType()); //List<Letter>.class
			Object[] lettersArray = letters.toArray(new Object[letters.size()]);
			Object[][] letters2d = new Object[letters.size()][1];
			for (int i = 0; i < letters.size(); i++) {
				letters2d[i][0] = lettersArray[i];
			}
			return Arrays.asList(letters2d);
		}
	}

	private final Letter letter;

	public EmailSenderTest(Letter letter) {
		this.letter = letter;
	}

	@Steps
	EmailSenderSteps sender;

	@Managed(uniqueSession=true, clearCookies=Never)
	WebDriver driver;

	@Test
	@Title("The letter is created and appears in the sent")
	@Screenshots(onlyOnFailures=true)
	public void send_email() {
		// GIVEN
		String login = PropertyReader.getInstance().getProperty("login");
		String password = PropertyReader.getInstance().getProperty("password");
		sender.ensure_login(login, password);

		// WHEN
		sender.create_letter(letter);

		// THEN
		sender.should_be_as_last_sent(letter);
	}

}
