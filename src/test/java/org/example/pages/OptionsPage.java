package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class OptionsPage extends BasePage {

    private SelenideElement meineMoglichkeitenText = $(By.xpath("//*[contains(text(),'Möglichkeiten')]"));

    public boolean isMeineMoglichkeitenDisplayed() {
        logAction("Checking if 'Meine Möglichkeiten' is displayed");
        try {
            return meineMoglichkeitenText.isDisplayed();
        } catch (Exception e) {
            logAction("'Meine Möglichkeiten' text not found");
            return false;
        }
    }
}
