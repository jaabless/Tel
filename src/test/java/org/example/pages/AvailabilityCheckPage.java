package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AvailabilityCheckPage extends BasePage {

    // Updated locators based on best available selectors (assuming common patterns; adjust as needed)
    private SelenideElement acceptCookiesButton = $(By.xpath("//button[contains(text(),'Akzeptieren') or contains(text(),'Accept')]"));
    private SelenideElement plzOrderStadtField = $(By.cssSelector("input[placeholder*='PLZ']"));
    private SelenideElement strasseField = $(By.cssSelector("input[placeholder*='Straße']"));
    private SelenideElement hausnummerField = $(By.cssSelector("input[placeholder*='Hausnummer']"));
    private SelenideElement adresseButton = $(By.xpath("//scale-button//*[contains(text(),'Adresse überprüfen')]"));

    public void acceptCookies() {
        logAction("Accepting cookies");
        try {
            acceptCookiesButton.shouldBe(Condition.visible, Duration.ofSeconds(30)).click();
        } catch (Exception e) {
            logAction("Cookies dialog did not appear, continuing");
        }
    }

    public void enterPlzOrderStadt(String value) {
        logAction("Entering PLZ order Stadt: " + value);
        plzOrderStadtField.setValue(value);
        // No need to click suggestion
    }

    public void enterStrasse(String value) {
        logAction("Entering Straße: " + value);
        strasseField.setValue(value);
        // No need to click suggestion
    }

    public void enterHausnummer(String value) {
        logAction("Entering Hausnummer: " + value);
        hausnummerField.setValue(value); // Using the separate hausnummerField
        // No need to select from dropdown
    }

    public TarriffsPage clickAdresse() {
        logAction("Clicking Adresse button");
        // Since the button is inside a custom element (shadow DOM), we use JavaScript to click it
        com.codeborne.selenide.Selenide.executeJavaScript("document.querySelector('scale-button').click();");
        return new TarriffsPage();
    }
}
