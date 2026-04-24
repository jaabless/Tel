package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class ProductsPage extends BasePage {

    private SelenideElement glasfaserHeadline = $(By.xpath("//*[contains(text(),'Glasfaser ')]"));
    private SelenideElement privatkundenButton = $(By.cssSelector("button[data-qa='private']"));
    private SelenideElement glasfaserTarifeText = $(By.xpath("//*[contains(text(),'Glasfaser-Tarife')]"));
//    private SelenideElement glasfaser2000Radio = $(By.xpath("scale-radio-button[contains(.,'Glasfaser 2.000') and not(contains(.,'MagentaTV'))]"));
    private SelenideElement glasfaser2000Radio = $(By.cssSelector("[data-qa='productItem-glasfaser2000_ftth']"));
    private SelenideElement glasfaser2000MagentaTVOption = $(By.cssSelector("[data-qa='tariffPrice-50219464__50217548__']"));


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

    public void selectGlasfaser2000() {
        logAction("Selecting 'Glasfaser 2.000' radio button");
        glasfaser2000Radio.shouldBe(Condition.visible).click();
    }

    public void selectGlasfaser2000MitMagentaTV() {
        logAction("Selecting 'Glasfaser 2.000 mit MagentaTV Smart'");
        glasfaser2000MagentaTVOption.shouldBe(Condition.visible).click();
    }

    public HardwarePage clickWeiter() {
        logAction("Clicking 'Weiter' scale-button");
        executeJavaScript(
            "Array.from(document.querySelectorAll('scale-button')).find(el => el.textContent.trim().includes('Weiter')).click();"
        );
        return new HardwarePage();
    }
}
