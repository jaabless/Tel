package org.example.tests;

import org.example.pages.AvailabilityCheckPage;
import org.example.pages.TarriffsPage;

public class TestHelper extends BaseTest {

    public void navigateToTariffPage() {
        navigateToTariffPage(TestData.Address.defaultAddress());
    }

    public void navigateToTariffPage(TestData.Address address) {
        AvailabilityCheckPage availabilityPage = new AvailabilityCheckPage();

        availabilityPage.acceptCookies();
        availabilityPage.enterPlzOrderStadt(address.getPlzOrderStadt());
        availabilityPage.enterStrasse(address.getStrasse());
        availabilityPage.enterHausnummer(address.getHausnummer());
        availabilityPage.clickAdresse();
    }

    public TarriffsPage getTariffPageAfterNavigation() {
        navigateToTariffPage();
        return new TarriffsPage();
    }

    public TarriffsPage getTariffPageAfterNavigation(TestData.Address address) {
        navigateToTariffPage(address);
        return new TarriffsPage();
    }
}
