package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.CheckoutPage;
import org.example.pages.HardwarePage;
import org.example.pages.ProductsPage;
import org.example.pages.TarriffsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Telekom Hardware Page")
@Feature("Hardware Selection Functionality")
public class HardwarePageTest extends BaseTest {

    @Test
    @Story("Navigate to hardware page and select hardware options")
    @Description("Test hardware selection and checkout navigation after tariff selection")
    public void testHardwarePage() {
        // Navigate to tariff page and complete tariff selection
        TarriffsPage tarriffsPage = new TestHelper().getTariffPageAfterNavigation();

        // Navigate through tariff options
        tarriffsPage.clickNein();
        tarriffsPage.selectNeuenGlasfaserAnschluss();
        tarriffsPage.clickVerfuegbareProdukte();

        ProductsPage productsPage = new ProductsPage();
        productsPage.clickPrivatkunden();
        productsPage.selectGlasfaser2000();
        productsPage.selectGlasfaser2000MitMagentaTV();

        // Navigate to hardware page
        HardwarePage hardwarePage = productsPage.clickWeiter();
        Assert.assertTrue(hardwarePage.isBitteWaehlenDisplayed(), "'Bitte wählen' should be displayed");

        // Select hardware options
        hardwarePage.clickAvm();
        hardwarePage.clickMagentaTV();

        // Navigate to checkout
        CheckoutPage checkoutPage = hardwarePage.clickZur();
        Assert.assertTrue(checkoutPage.isTelekomLoginDisplayed(), "'Telekom Login' should be displayed");
    }
}
