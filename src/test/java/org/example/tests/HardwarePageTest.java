package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.CheckoutPage;
import org.example.pages.HardwarePage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Telekom Hardware Page")
@Feature("Hardware Selection Functionality")
public class HardwarePageTest extends BaseTest {

    @Test
    @Story("Navigate to hardware page and select hardware options")
    @Description("Test hardware selection and checkout navigation after tariff selection")
    public void testHardwarePage() {
        HardwarePage hardwarePage = new TestHelper().navigateToHardwarePage();
        Assert.assertTrue(hardwarePage.isBitteWaehlenDisplayed(), "'Bitte wählen' should be displayed");

        // Select hardware options
        hardwarePage.clickAvm();
        hardwarePage.clickMagentaTV();

        // Navigate to checkout
        CheckoutPage checkoutPage = hardwarePage.clickZur();
        Assert.assertTrue(checkoutPage.isTelekomLoginDisplayed(), "'Telekom Login' should be displayed");
    }
}
