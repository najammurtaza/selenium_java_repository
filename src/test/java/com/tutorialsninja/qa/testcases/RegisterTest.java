package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;


public class RegisterTest extends Base{
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage acpage;
	public RegisterTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenURL(prop.getProperty("browser"));
		HomePage home = new HomePage(driver);
		home.clickOnMyAccount();
		registerPage = home.clickOnRegister();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void registerAccountWithAllFields() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		Faker fake = new Faker();
		registerPage.enterEmail(fake.internet().emailAddress());
		registerPage.enterTelephone(dataProp.getProperty("telephone"));
		registerPage.enterPassword(dataProp.getProperty("password"));
		registerPage.enterConfirmPassword(dataProp.getProperty("confirmPassword"));
		registerPage.subscribeNewsLetter();
		registerPage.checkAgreePolicy();
		acpage = registerPage.clickOnContinueButton();
		String expectedHeading = "Your Account Has Been Created!";
		String actualHeading = acpage.getSuccessPageHeading();
		Assert.assertEquals(actualHeading, expectedHeading, "Unable to navigate to the Account Success Page");
		acpage.clickOnSuccessPageContinue();
	}
	
	@Test(description="Verify that user is able to register with already existing credentials")
	@Description("Verify Registaration with already existing account......")
	@Epic("User Registration")
	@Feature("Registration")
	@Story("Test Registration")
	@Step("Registration Verification")
	@Severity(SeverityLevel.CRITICAL)
	public void registerAccountWithAlreadyExistingCredentials() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(dataProp.getProperty("email"));
		registerPage.enterTelephone(dataProp.getProperty("telephone"));
		registerPage.enterPassword(dataProp.getProperty("password"));
		registerPage.enterConfirmPassword(dataProp.getProperty("confirmPassword"));
		registerPage.subscribeNewsLetter();
		registerPage.checkAgreePolicy();
		registerPage.clickOnContinueButton();
		String expectedHeading = "Warning: E-Mail Address is already registered!";
		String actualHeading = registerPage.accountAlreadyExistWarningMessage();
		Assert.assertEquals(actualHeading, expectedHeading, "Account Does not Exist");
		
	}

}
