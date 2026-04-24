package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage extends BasePage {

    private SelenideElement telekomLoginText = $(By.xpath("//*[contains(text(),'Telekom Login')]"));

    public boolean isTelekomLoginDisplayed() {
        logAction("Checking if 'Telekom Login' is displayed");
        try {
            return telekomLoginText.shouldBe(Condition.visible, Duration.ofSeconds(60)).isDisplayed();
        } catch (Exception e) {
            logAction("'Telekom Login' not found: " + e.getMessage());
            return false;
        }
    }
}
