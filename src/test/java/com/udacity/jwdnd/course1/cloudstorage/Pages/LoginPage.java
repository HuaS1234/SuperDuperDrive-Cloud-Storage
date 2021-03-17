package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(css="#inputUsername")
    private WebElement usernameField;

    @FindBy(css="#inputPassword")
    private WebElement passwordField;

    @FindBy(css="#submit-button")
    private WebElement submitButton;

    @FindBy(id = "error-msg")
    private WebElement errorMsg;

    @FindBy(id = "logout-msg")
    private WebElement logoutMsg;

    private final WebDriver webDriver;
    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" +username + "';", usernameField);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" +password + "';", passwordField);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", submitButton);
    }



    public boolean isNotAuthorized() {
        return this.errorMsg.isDisplayed();
    }

    public boolean loggedout() {
        return this.logoutMsg.isDisplayed();
    }

}
