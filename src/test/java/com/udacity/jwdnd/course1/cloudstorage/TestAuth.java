package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestAuth {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	private String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		this.baseURL = "http://localhost:" + this.port;
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {

			driver.quit();
		}
	}

	//1. Write a test that verifies that an unauthorized user can only access the login and signup pages.
	@Test
	public void testUnauthorizedUser() {
		driver.get(baseURL + "/login");

		//check if unauthorized user stay at login page
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("username", "password");
		Assertions.assertEquals("Login", driver.getTitle());

		//check if error message displayed
		Assertions.assertTrue(loginPage.isNotAuthorized());
	}

	//2. Write a test that signs up a new user, logs in, verifies that the home page is accessible,
	// logs out, and verifies that the home page is no longer accessible.
	@Test
	public void testSignupLoginLogout() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String username = "newuser";
		String password = "password";
		driver.get(baseURL + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("firstName", "lastName", username, password);
		driver.get(baseURL + "/login");

		//check if signed up successfully
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		Assertions.assertEquals("Home", driver.getTitle());

		//logout
		HomePage homePage = new HomePage(driver);
		homePage.logout();
		wait.until(ExpectedConditions.titleContains("Login"));
		Assertions.assertTrue(loginPage.loggedout());

		//home page is no longer accessible
		driver.get(baseURL + "/home");
		Assertions.assertNotEquals("Home", driver.getTitle());
	}

}
