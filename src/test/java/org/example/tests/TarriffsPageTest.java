package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.AvailabilityCheckPage;
import org.example.pages.ProductsPage;
import org.example.pages.TarriffsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Telekom Tariff Page")
@Feature("Tariff Page Functionality")
public class TarriffsPageTest extends BaseTest {

    @Test
    @Story("Navigate to tariff page and verify elements")
    @Description("Test the tariff page display and navigation after availability check")
    public void testTariffPage() {
        AvailabilityCheckPage availabilityPage = new AvailabilityCheckPage();
        TarriffsPage tarriffsPage = new TarriffsPage();

        availabilityPage.acceptCookies();
        availabilityPage.enterPlzOrderStadt("34277, Fuldabrück, Fuldabrück");
        availabilityPage.enterStrasse("Ahornweg");
        availabilityPage.enterHausnummer("1");
        availabilityPage.clickAdresse();

        Assert.assertTrue(tarriffsPage.isWirPlanenDisplayed(), "'Wir planen' should be displayed");

        tarriffsPage.clickNein();
        tarriffsPage.selectNeuenGlasfaserAnschluss();
        tarriffsPage.clickVerfuegbareProdukte();

        ProductsPage productsPage = new ProductsPage();
        Assert.assertTrue(productsPage.isGlasfaserDisplayed(), "'Glasfaser' should be displayed");

        productsPage.clickPrivatkunden();
        Assert.assertTrue(productsPage.isGlasfaserTarifeDisplayed(), "'Glasfaser-Tarife' should be displayed");

        productsPage.selectGlasfaser2000();
        productsPage.selectGlasfaser2000MitMagentaTV();
    }
}
