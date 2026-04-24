package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class HardwarePage extends BasePage {

    private SelenideElement bitteWaehlenTitle = $(By.cssSelector("[data-qa='pageTitle-basic']"));

    public boolean isBitteWaehlenDisplayed() {
        logAction("Checking if 'Bitte wählen' is displayed");
        try {
            return bitteWaehlenTitle.shouldBe(Condition.visible, Duration.ofSeconds(60)).isDisplayed();
        } catch (Exception e) {
            logAction("'Bitte wählen' title not found: " + e.getMessage());
            return false;
        }
    }
}
