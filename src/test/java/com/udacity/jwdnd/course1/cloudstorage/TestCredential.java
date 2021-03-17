package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Pages.CredentialPage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.security.SecureRandom;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCredential {
    @LocalServerPort
    private int port;

    private WebDriver driver;

    private String baseURL;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private CredentialPage credentialPage;


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
        credentialPage = new CredentialPage(driver);

        //signup and login
        SecureRandom random = new SecureRandom();

        String username = String.valueOf(random.nextInt(1000));
        String password = String.valueOf(random.nextInt(1000));
        driver.get(baseURL + "/signup");
        signupPage.signup("firstName", "lastName", username, password);
        driver.get(baseURL + "/login");
        loginPage.login(username, password);
        driver.get(baseURL + "/home?tabOption=credentials");

    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {

            driver.quit();
        }
    }

    //Write a test that creates a set of credentials, verifies that they are displayed,
    // and verifies that the displayed password is encrypted.
    @Test
    public void testNewCredential() {
        //create a credential
        String url = "http://localhost:8080/login";
        String username = "myUsername";
        String password = "myPassword";
        credentialPage.CreateCredential();
        credentialPage.EditCredential(url, username, password);

        //verify it's displayed
        Assertions.assertEquals(url, credentialPage.getUrl());
        Assertions.assertEquals(username, credentialPage.getUsername());
        Assertions.assertNotEquals(password, credentialPage.getPassword());
    }

    //Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted,
    // edits the credentials, and verifies that the changes are displayed.
    @Test
    public void testEditCredential() {
        //create a credential
        String url = "http://localhost:8080/login";
        String username = "myUsername";
        String password = "myPassword";
        credentialPage.CreateCredential();
        credentialPage.EditCredential(url, username, password);

        //view existing credential, and verify password is unencrypted
        credentialPage.viewCredential();
        Assertions.assertEquals(password, credentialPage.getOriginalPassword());

        //edit credential
        username += "New";
        password += "New";
        credentialPage.EditCredential(url, username, password);

        //verify changes are displayed
        Assertions.assertEquals(username, credentialPage.getUsername());
        Assertions.assertNotEquals(password, credentialPage.getPassword());

    }

    //Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
    @Test
    public void testDeleteCredential() {
        //create a credential
        String url = "http://localhost:8080/login";
        String username = "guessMyname";
        String password = "guessMypassword";
        credentialPage.CreateCredential();
        credentialPage.EditCredential(url, username, password);

        //delete an existing credential
        credentialPage.deleteCredential();

        //verify it's no longer displayed
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            credentialPage.getUrl();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            credentialPage.getUsername();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            credentialPage.getPassword();
        });
    }
}