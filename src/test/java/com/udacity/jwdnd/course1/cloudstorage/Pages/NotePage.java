package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotePage {
    @FindBy(id="newNoteButton")
    private WebElement newNoteButton;

    @FindBy(id="note-title")
    private WebElement titleField;

    @FindBy(id="note-description")
    private WebElement descField;

    @FindBy(id="saveChangesButton")
    private WebElement submitButton;

    @FindBy(id="eachNoteTitle")
    private WebElement noteTitle;

    @FindBy(id="eachNoteDesc")
    private WebElement noteDescription;

    @FindBy(id="noteEditButton")
    private WebElement noteEditButton;

    @FindBy(id="noteDeleteButton")
    private WebElement noteDeleteButton;

    private final WebDriver webDriver;
    public NotePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void CreateNote(String title, String description) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", newNoteButton);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + title + "';", titleField);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + description + "';", descField);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", submitButton);
    }

    public String getTitle() {
        return noteTitle.getAttribute("innerHTML");
    }

    public String getDescription() {
        return noteDescription.getAttribute("innerHTML");
    }
    public void EditNote(String title, String description) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", noteEditButton);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + title + "';", titleField);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + description + "';", descField);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", submitButton);
    }

    public void DeleteNote() {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", noteDeleteButton);
    }
}
