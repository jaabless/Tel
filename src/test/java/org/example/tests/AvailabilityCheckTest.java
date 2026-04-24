package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("Telekom Availability Check")
@Feature("Address Availability")
public class AvailabilityCheckTest extends BaseTest {

    @Test
    @Story("Check availability and navigate through options")
    @Description("Test the availability check flow from entering address to viewing options")
    public void testAvailabilityCheck() {
        new TestHelper().getTariffPageAfterNavigation();
    }
}
