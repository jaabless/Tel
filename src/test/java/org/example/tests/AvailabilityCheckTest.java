package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.AvailabilityCheckPage;
import org.example.pages.OptionsPage;
import org.example.pages.PlanningPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Telekom Availability Check")
@Feature("Address Availability")
public class AvailabilityCheckTest extends BaseTest {

    @Test
    @Story("Check availability and navigate through options")
    @Description("Test the availability check flow from entering address to viewing options")
    public void testAvailabilityCheck() {
        AvailabilityCheckPage availabilityPage = new AvailabilityCheckPage();
        PlanningPage planningPage = new PlanningPage();
        OptionsPage optionsPage = new OptionsPage();

        availabilityPage.acceptCookies();
        availabilityPage.enterPlzOrderStadt("34277, Fuldabrück, Fuldabrück");
        availabilityPage.enterStrasse("Ahornweg");
        availabilityPage.enterHausnummer("1");
        availabilityPage.clickAdresse();

        Assert.assertTrue(planningPage.isWirPlanenDisplayed(), "'Wir planen' should be displayed");

        planningPage.clickNein();

        Assert.assertTrue(optionsPage.isMeineMoglichkeitenDisplayed(), "'Meine Möglichkeiten' should be displayed");
    }
}
