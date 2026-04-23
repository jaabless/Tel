package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductsPage extends BasePage {

    private SelenideElement glasfaserHeadline = $(By.xpath("//*[contains(text(),'Glasfaser ')]"));
    private SelenideElement privatkundenButton = $(By.cssSelector("button[data-qa='private']"));
    private SelenideElement glasfaserTarifeText = $(By.xpath("//*[contains(text(),'Glasfaser-Tarife')]"));

    public boolean isGlasfaserDisplayed() {
        logAction("Checking if 'Glasfaser' is displayed");
        try {
            return glasfaserHeadline.shouldBe(Condition.visible).isDisplayed();
        } catch (Exception e) {
            logAction("'Glasfaser' text not found: " + e.getMessage());
            return false;
        }
    }

    public void clickPrivatkunden() {
        logAction("Clicking 'Privatkunden' button");
        privatkundenButton.click();
    }

    public boolean isGlasfaserTarifeDisplayed() {
        logAction("Checking if 'Glasfaser-Tarife' is displayed");
        try {
            return glasfaserTarifeText.shouldBe(Condition.visible).isDisplayed();
        } catch (Exception e) {
            logAction("'Glasfaser-Tarife' text not found: " + e.getMessage());
            return false;
        }
    }
}
