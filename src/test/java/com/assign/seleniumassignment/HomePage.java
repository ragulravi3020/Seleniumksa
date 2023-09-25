package com.assign.seleniumassignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {
    private final By countrySelectionButton = By.id("country-btn");
    private final By saCountryButton = By.id("saCountryButton");
    private final By saPackage = By.id("sa");
    private final By kuwaitPackage = By.id("kw");
    private final By bahrainPackage = By.id("bh");
    private final By englishLanguageButton = By.id("translation-btn");
    private final By arabicLanguageButton = By.id("arabicButton");
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        driver.get("https://subscribe.stctv.com/");
    }

    public void selectLanguage(String language) {
        WebElement languageButton = switch (language) {
            case "English", "Arabic" -> driver.findElement(englishLanguageButton);
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        };
        languageButton.click();
    }

    public String getSelectedLanguage() {
        if (driver.findElement(englishLanguageButton).isSelected()) {
            return "English";
        } else if (driver.findElement(arabicLanguageButton).isSelected()) {
            return "Arabic";
        } else {
            return "Unknown";
        }
    }

    public void openCountrySelectionPopup() {
        WebElement countrySelectionButtonElement = driver.findElement(countrySelectionButton);
        countrySelectionButtonElement.click();
    }

    public void selectCountry(String country) {
        openCountrySelectionPopup();

        WebElement countryButton = switch (country) {
            case "KSA", "Kuwait", "Bahrain" -> driver.findElement(saCountryButton);
            default -> throw new IllegalArgumentException("Unsupported country: " + country);
        };
        countryButton.click();
    }

    public SubscriptionPackage getSubscriptionPackageForCountry(String country) {
        WebElement packageElement = switch (country) {
            case "KSA" -> driver.findElement(saPackage);
            case "Kuwait" -> driver.findElement(kuwaitPackage);
            case "Bahrain" -> driver.findElement(bahrainPackage);
            default -> null;
        };
        return new SubscriptionPackage(packageElement);
    }
}
