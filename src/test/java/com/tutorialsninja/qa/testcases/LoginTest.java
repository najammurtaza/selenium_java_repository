package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class LoginTest extends Base{
	
	public LoginTest() {
		super();
	}
	LoginPage loginPage;
	public WebDriver driver;
	AccountPage accountPage;
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenURL(prop.getProperty("browser"));
		HomePage home = new HomePage(driver);
		home.clickOnMyAccount();
		loginPage = home.clickOnLogin();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=2, description="Verify that user is able to login with Valid Credentials")
	@Description("Verify Login with Valid Credentials......")
	@Epic("User Authentication")
	@Feature("Login")
	@Story("Test Login")
	@Step("Login Verification")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyLoginWithValidCredentials() throws InterruptedException
	{
		loginPage.enterEmail(dataProp.getProperty("email"));
		loginPage.enterPassword(prop.getProperty("validpassword"));
		accountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue(accountPage.accountHeadingDisplayed(), "Wrong Heading");
		Thread.sleep(3000);

	}
	@Test(priority=1, description="Verify that user is able to login with Invalid Credentials")
	@Description("Verify Login with Invalid Credentials......")
	@Epic("User Authentication")
	@Feature("Login")
	@Story("Test Login")
	@Step("Login Verification")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException 
	{
		loginPage.enterEmail(dataProp.getProperty("invalidEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.isWarningMessageDisplay(), "Warning Message is not Displayed");
		Thread.sleep(3000);
	
	}
}