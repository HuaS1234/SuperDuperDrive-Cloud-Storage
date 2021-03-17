package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialPage {
    @FindBy(id="newCredentialButton")
    private WebElement newCredentialButton;

    @FindBy(id="credential-url")
    private WebElement urlField;

    @FindBy(id="credential-username")
    private WebElement usernameField;

    @FindBy(id="credential-password")
    private WebElement passwordField;

    @FindBy(id="credentialSubmit")
    private WebElement credentialSubmit;

    @FindBy(id="eachCredentialUrl")
    private WebElement eachCredentialUrl;

    @FindBy(id="eachCredentialUsername")
    private WebElement eachCredentialUsername;

    @FindBy(id="eachCredentialPassword")
    private WebElement eachCredentialPassword;

    @FindBy(id="credentialEditButton")
    private WebElement credentialEditButton;

    @FindBy(id="credentialDeleteButton")
    private WebElement credentialDeleteButton;

    private final WebDriver webDriver;
    public CredentialPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void CreateCredential() {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", newCredentialButton);
    }

    public void EditCredential(String url, String username, String password) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + url + "';", urlField);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + username + "';", usernameField);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + password + "';", passwordField);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", credentialSubmit);
    }

    public String getUrl() {
        return eachCredentialUrl.getAttribute("innerHTML");
    }

    public String getUsername() {
        return eachCredentialUsername.getAttribute("innerHTML");
    }

    public String getPassword() {
        return eachCredentialPassword.getAttribute("innerHTML");
    }

    public void viewCredential() {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", credentialEditButton);
    }

    public String getOriginalPassword() {
        return passwordField.getAttribute("value");
    }

    public void deleteCredential() {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", credentialDeleteButton);
    }
}
