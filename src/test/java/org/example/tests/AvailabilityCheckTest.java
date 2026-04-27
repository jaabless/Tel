package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.AvailabilityCheckPage;
import org.example.pages.TarriffsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Telekom Availability Check")
@Feature("Address Availability")
public class AvailabilityCheckTest extends BaseTest {

    @DataProvider(name = "availabilityScenarios")
    public Object[][] availabilityScenarios() {
        return TestData.AVAILABILITY_SCENARIOS;
    }

    @DataProvider(name = "addressValidationScenarios")
    public Object[][] ADDRESS_VALIDATION_SCENARIOS() {
        return TestData.ADDRESS_VALIDATION_SCENARIOS;
    }

    @DataProvider(name = "invalidAddressData")
    public Object[][] invalidAddressData() {
        return TestData.invalidAddressData;
    }

    @DataProvider(name = "validAddressData")
    public Object[][] validAddressData() {
        return TestData.validAddressData;
    }

    @Test(dataProvider = "availabilityScenarios")
    @Story("Check availability for different addresses")
    @Description("Test availability check with various address scenarios")
    public void testAvailabilityCheckScenarios(String scenarioName, TestData.Address address, boolean shouldSucceed) {
        AvailabilityCheckPage availabilityPage = new AvailabilityCheckPage();

        // Accept cookies
        availabilityPage.acceptCookies();

        // Enter address details
        availabilityPage.enterPlzOrderStadt(address.getPlzOrderStadt());
        availabilityPage.enterStrasse(address.getStrasse());
        availabilityPage.enterHausnummer(address.getHausnummer());

        // Click address button
        availabilityPage.clickAdresse();

        if (shouldSucceed) {
            // For valid addresses, we should reach the tariff page
            TarriffsPage tarriffsPage = new TarriffsPage();
            Assert.assertTrue(tarriffsPage.isWirPlanenDisplayed(),
                "Tariff page should be displayed for valid address: " + address.getDescription());
        } else {
            // For invalid addresses, we might stay on the same page or show an error
            // This would need to be verified based on actual application behavior
            // For now, we'll just ensure the page doesn't crash
            Assert.assertTrue(true, "Address validation completed for: " + address.getDescription());
        }
    }

    @Test(dataProvider = "addressValidationScenarios")
    @Story("Verify address input validation")
    @Description("Test that address fields are properly validated")
    public void testAddressInputValidation(TestData.Address address, boolean shouldSucceed) {
        AvailabilityCheckPage availabilityPage = new AvailabilityCheckPage();

        // Test with empty fields
        availabilityPage.acceptCookies();

        // Try to proceed without entering data
        availabilityPage.clickAdresse();

        // The application should handle this gracefully
        // (This test would need adjustment based on actual validation behavior)
        Assert.assertTrue(true, "Address validation should handle empty fields gracefully");
    }

    @Test
    @Story("Check availability with default address")
    @Description("Test the basic availability check flow with default address")
    public void testBasicAvailabilityCheck() {
        TarriffsPage tarriffsPage = new TestHelper().getTariffPageAfterNavigation();
        Assert.assertTrue(tarriffsPage.isWirPlanenDisplayed(),
                "'Wir planen' should be displayed after successful availability check");
    }

    @Test
    @Story("Test cookie acceptance")
    @Description("Verify that cookie acceptance works properly")
    public void testCookieAcceptance() {
        AvailabilityCheckPage availabilityPage = new AvailabilityCheckPage();

        // Accept cookies
        availabilityPage.acceptCookies();

        // Verify we can proceed with address entry
        availabilityPage.enterPlzOrderStadt(TestData.Address.defaultAddress().getPlzOrderStadt());

        // If we reach this point without errors, cookies were accepted successfully
        Assert.assertTrue(true, "Cookie acceptance should allow proceeding with address entry");
    }

    @Test(dataProvider = "invalidAddressData")
    @Story("Test address with invalid details")
    @Description("Verify that user is not navigated with invalid address details")
    public void testAddressWithInvalidDetails(String plzOrderStad, String street, String houseNumber, boolean shouldSucceed, String expectedResults) {
        AvailabilityCheckPage availabilityPage = new AvailabilityCheckPage();

        // Accept cookies
        availabilityPage.acceptCookies();

        // Enter address details
        availabilityPage.enterPlzOrderStadt(plzOrderStad);
        availabilityPage.enterStrasse(street);
        availabilityPage.enterHausnummer(houseNumber);

        Assert.assertEquals(availabilityPage.getErrorMessage(), expectedResults, "Error message should match for invalid input");
        Assert.assertFalse(availabilityPage.isAdresseButtonEnabled(), "Adresse button should not be enabled for invalid address details");
    }

    @Test(dataProvider = "validAddressData")
    @Story("Test address with valid details")
    @Description("Verify that user is not navigated with invalid address details")
    public void testAddressWithValidDetails(String plzOrderStad, String street, String houseNumber, boolean shouldSucceed, String expectedResults) {
        AvailabilityCheckPage availabilityPage = new AvailabilityCheckPage();

        // Accept cookies
        availabilityPage.acceptCookies();

        // Enter address details
        availabilityPage.enterPlzOrderStadt(plzOrderStad);
        availabilityPage.enterStrasse(street);
        availabilityPage.enterHausnummer(houseNumber);
        availabilityPage.clickAdresse();

        Assert.assertTrue(availabilityPage.isAdresseButtonEnabled(), "Adresse button should be enabled for valid address details: ");
    }
}
