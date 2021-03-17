package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id="logoutButton")
    private WebElement logout;

    private final WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void logout() {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", logout);
    }
}
