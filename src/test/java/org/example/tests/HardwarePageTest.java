package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.AvailabilityCheckPage;
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
        AvailabilityCheckPage availabilityPage = new AvailabilityCheckPage();
        TarriffsPage tarriffsPage = new TarriffsPage();

        availabilityPage.acceptCookies();
        availabilityPage.enterPlzOrderStadt("34277, Fuldabrück, Fuldabrück");
        availabilityPage.enterStrasse("Ahornweg");
        availabilityPage.enterHausnummer("1");
        availabilityPage.clickAdresse();

        tarriffsPage.isWirPlanenDisplayed();
        tarriffsPage.clickNein();
        tarriffsPage.selectNeuenGlasfaserAnschluss();
        tarriffsPage.clickVerfuegbareProdukte();

        ProductsPage productsPage = new ProductsPage();
        productsPage.clickPrivatkunden();
        productsPage.selectGlasfaser2000();
        productsPage.selectGlasfaser2000MitMagentaTV();

        HardwarePage hardwarePage = productsPage.clickWeiter();
        Assert.assertTrue(hardwarePage.isBitteWaehlenDisplayed(), "'Bitte wählen' should be displayed");

        hardwarePage.clickAvm();
        hardwarePage.clickMagentaTV();

        CheckoutPage checkoutPage = hardwarePage.clickZur();
        Assert.assertTrue(checkoutPage.isTelekomLoginDisplayed(), "'Telekom Login' should be displayed");
    }
}
