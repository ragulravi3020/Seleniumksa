package com.assign.seleniumassignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SubscriptionPackage {
    private final WebElement packageElement;
    private final By type = By.className("plan-names");
    private final By price = By.className("price");
    private final By currency = By.className("currency");

    public SubscriptionPackage(WebElement packageElement) {
        this.packageElement = packageElement;
    }

    public String getType() {
        return packageElement.findElement(type).getText();
    }

    public String getPrice() {
        return packageElement.findElement(price).getText();
    }

    public String getCurrency() {
        return packageElement.findElement(currency).getText();
    }

}
