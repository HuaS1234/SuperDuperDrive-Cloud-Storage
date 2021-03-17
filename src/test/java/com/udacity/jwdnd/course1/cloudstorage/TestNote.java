package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestNote {
    @LocalServerPort
    private int port;

    private WebDriver driver;

    private String baseURL;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private NotePage notePage;


    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        this.baseURL = "http://localhost:" + this.port;

        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        notePage = new NotePage(driver);

        //signup and login
        String username = "newuser";
        String password = "password";
        driver.get(baseURL + "/signup");
        signupPage.signup("firstName", "lastName", username, password);
        driver.get(baseURL + "/login");
        loginPage.login(username, password);
        driver.get(baseURL + "/home?tabOption=notes");

    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {

            driver.quit();
        }
    }

    //Write a test that creates a note, and verifies it is displayed.
    @Test
    public void testUnauthorizedUser() {
        //create a new note
        String title = "title - test";
        String description = "description - test";
        notePage.CreateNote(title, description);

        //verify it's displayed
        Assertions.assertEquals(title, notePage.getTitle());
        Assertions.assertEquals(description, notePage.getDescription());
    }

    //Write a test that edits an existing note and verifies that the changes are displayed.
    @Test
    public void testEditNote() {
        //create a new note
        String title = "title - test";
        String description = "description - test";
        notePage.CreateNote(title, description);

        //edit existing note
        title = "newTitle";
        description = "newDescription";
        notePage.EditNote(title, description);

        //verify it's displayed
        Assertions.assertEquals(title, notePage.getTitle());
        Assertions.assertEquals(description, notePage.getDescription());

    }

    //Write a test that deletes a note and verifies that the note is no longer displayed.
    @Test
    public void testDeleteNote() {
        //create a new note
        String title = "title - test";
        String description = "description - test";
        notePage.CreateNote(title, description);

        //delete existing note
        notePage.DeleteNote();

        //verify it's no longer displayed
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            notePage.getTitle();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            notePage.getDescription();
        });

    }

}
