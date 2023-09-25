package com.assign.seleniumassignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SubscriptionTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\SPURGE\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

    @Test
    public void validateSubscriptionPackages() {
        // Step 1: Go to the homepage
        homePage.goTo();

        homePage.selectLanguage("English");

        // Verify that the language is set to English
        Assert.assertEquals(homePage.getSelectedLanguage(), "English", "Language selection is not as expected.");

        // Step 2: Validate Subscription Packages for SA
        SubscriptionPackage saPackage = homePage.getSubscriptionPackageForCountry("SA");
        validateSubscriptionPackage(saPackage, "SA");

        // Step 3: Validate Subscription Packages for Kuwait
        SubscriptionPackage kuwaitPackage = homePage.getSubscriptionPackageForCountry("Kuwait");
        validateSubscriptionPackage(kuwaitPackage, "Kuwait");

        // Step 4: Validate Subscription Packages for Bahrain
        SubscriptionPackage bahrainPackage = homePage.getSubscriptionPackageForCountry("Bahrain");
        validateSubscriptionPackage(bahrainPackage, "Bahrain");


    }

    private void validateSubscriptionPackage(SubscriptionPackage subscriptionPackage, String country) {
        String type = subscriptionPackage.getType();
        String price = subscriptionPackage.getPrice();
        String currency = subscriptionPackage.getCurrency();

        Assert.assertFalse(type.isEmpty(), "Type is empty for " + country);
        Assert.assertFalse(price.isEmpty(), "Price is empty for " + country);
        Assert.assertFalse(currency.isEmpty(), "Currency is empty for " + country);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
