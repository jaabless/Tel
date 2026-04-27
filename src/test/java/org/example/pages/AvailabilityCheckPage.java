package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class AvailabilityCheckPage extends BasePage {

    // Updated locators based on best available selectors (assuming common patterns; adjust as needed)
    private SelenideElement acceptCookiesButton = $(By.xpath("//button[contains(text(),'Akzeptieren') or contains(text(),'Accept')]"));
    private SelenideElement plzOrderStadtField = $(By.cssSelector("input[placeholder*='PLZ']"));
    private SelenideElement strasseField = $(By.cssSelector("input[placeholder*='Straße']"));
    private SelenideElement hausnummerField = $(By.cssSelector("input[placeholder*='Hausnummer']"));
    private SelenideElement adresseScaleButton = $(By.cssSelector("scale-button.magentaButton"));

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

    public boolean isAdresseButtonEnabled() {
        logAction("Checking if Adresse button is enabled");
        return !adresseScaleButton.has(Condition.attribute("disabled"));
    }

    public TarriffsPage clickAdresse() {
        logAction("Clicking Adresse button");
        // Since the button is inside a custom element (shadow DOM), we use JavaScript to click it
        com.codeborne.selenide.Selenide.executeJavaScript("document.querySelector('scale-button').click();");
        return new TarriffsPage();
    }

    public String getErrorMessage() {
        logAction("Getting error message from shadow DOM");
        // Trigger blur on the active element so Scale component validation fires
        executeJavaScript("document.activeElement && document.activeElement.blur();");
        String message = executeJavaScript(
            // First: read helper-text attribute reflected on the scale-text-field host element
            "const fields = document.querySelectorAll('scale-text-field');" +
            "for (const field of fields) {" +
            "  const helperText = field.getAttribute('helper-text');" +
            "  if (helperText && helperText.trim()) return helperText.trim();" +
            "}" +
            // Fallback: traverse shadow roots for rendered helper text elements
            "for (const field of fields) {" +
            "  if (field.shadowRoot) {" +
            "    const helper = field.shadowRoot.querySelector('.helper-message, [class*=\"error\"], [class*=\"helper\"]');" +
            "    if (helper && helper.textContent.trim()) return helper.textContent.trim();" +
            "  }" +
            "}" +
            "return '';"
        );
        return message != null ? message.trim() : "";
    }
}
