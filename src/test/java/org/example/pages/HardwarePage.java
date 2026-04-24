package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class HardwarePage extends BasePage {

    private SelenideElement bitteWaehlenTitle = $(By.cssSelector("[data-qa='pageTitle-basic']"));
    private SelenideElement avmRouter = $(By.cssSelector("[data-qa='hardwarefritzbox5590-private']"));
    private SelenideElement magentaTV = $(By.cssSelector("[data-qa='hardwaremagentatvone_v2_bf']"));

    public boolean isBitteWaehlenDisplayed() {
        logAction("Checking if 'Bitte wählen' is displayed");
        try {
            return bitteWaehlenTitle.shouldBe(Condition.visible, Duration.ofSeconds(60)).isDisplayed();
        } catch (Exception e) {
            logAction("'Bitte wählen' title not found: " + e.getMessage());
            return false;
        }
    }

    public void clickAvm() {
        logAction("Clicking AVM router option");
        avmRouter.shouldBe(Condition.visible).click();
    }

    public void clickMagentaTV() {
        logAction("Clicking MagentaTV option");
        magentaTV.shouldBe(Condition.visible).click();
    }

    public CheckoutPage clickZur() {
        logAction("Clicking 'Zur' scale-button");
        executeJavaScript(
            "Array.from(document.querySelectorAll('scale-button')).find(el => el.textContent.trim().startsWith('Zur')).click();"
        );
        return new CheckoutPage();
    }
}
