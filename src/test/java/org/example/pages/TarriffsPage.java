package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TarriffsPage extends BasePage {

    private SelenideElement wirPlanenText = $(By.cssSelector("[data-qa='changeAddressBlockTitle']"));
    private SelenideElement neinButton = $(By.cssSelector("scale-button[data-qa='newCustomerButton']"));
    private SelenideElement neuenGlasfaserRadio = $(By.cssSelector("scale-radio-button[data-qa='newCustomerNewConnectionOption']"));
    private SelenideElement verfuegbareProdukteButton = $(By.cssSelector("[data-qa='buttonCheckAvailability']"));

    public boolean isWirPlanenDisplayed() {
        logAction("Checking if 'Wir planen' is displayed");
        try {
            return wirPlanenText.shouldBe(Condition.visible).isDisplayed();
        } catch (Exception e) {
            logAction("'Wir planen' text not found: " + e.getMessage());
            return false;
        }
    }

    public void clickNein() {
        logAction("Clicking Nein button");
        neinButton.shouldBe(Condition.visible).click();
    }

    public void selectNeuenGlasfaserAnschluss() {
        logAction("Selecting 'Neuen Glasfaser-Anschluss' radio button");
        neuenGlasfaserRadio.shouldBe(Condition.visible).click();
    }

    public void clickVerfuegbareProdukte() {
        logAction("Clicking 'Verfügbare Produkte anzeigen' button");
        verfuegbareProdukteButton.shouldBe(Condition.visible).click();
    }
}
