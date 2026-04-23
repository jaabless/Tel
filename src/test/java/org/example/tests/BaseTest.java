package org.example.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.headless = false; // Set to true for headless
        Configuration.timeout = 15000; // Increased timeout to 15 seconds
        Configuration.pageLoadTimeout = 60000; // 60 second page load timeout
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @BeforeMethod
    public void setUp() {
        logger.info("Starting test");
        open("https://portal-proxy-pacman-03.pub.cl01.tmagic-dev.telekom.de/ausbau/checkout/availability-check");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Ending test");
        closeWebDriver();
    }
}
