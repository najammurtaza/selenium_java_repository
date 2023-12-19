package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class SearchTest extends Base{
	
	
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	SearchPage searchPage;
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenURL(prop.getProperty("browser"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=2)
	@Description("Verify Search Results with existing Product Name......")
	@Epic("User Search")
	@Feature("Search")
	@Story("Test Search")
	@Step("Search Result Verification")
	@Severity(SeverityLevel.CRITICAL)
	public void searchWithExistingProductName() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.enterTextInSearch(dataProp.getProperty("existingproductname"));
		searchPage = homePage.clickOnSearch();
		Assert.assertTrue(searchPage.verifyCorrectResultIsDisplayed(), "Correct Product is not available");
		Thread.sleep(3000);
	}
	
	@Test(priority=1)
	@Description("Verify Search Results with non-existing Product Name......")
	@Epic("User Search")
	@Feature("Search")
	@Story("Test Search")
	@Step("Search Result Verification")
	@Severity(SeverityLevel.CRITICAL)
	public void searchWithNonExistingProductName() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.enterTextInSearch(dataProp.getProperty("nonexistingproductname"));
		searchPage = homePage.clickOnSearch();
		String expected = dataProp.getProperty("warningMessage");
		String actual = searchPage.noProductMessageText();
		Assert.assertEquals(actual, expected, "Product is not displayed");
		Thread.sleep(3000);
	}
	
	@Test(priority=3)
	@Description("Verify Search Results with Mobile Name......")
	@Epic("User Search")
	@Feature("Search")
	@Story("Test Search")
	@Step("Search Result Verification")
	@Severity(SeverityLevel.CRITICAL)
	public void searchWithMobileProduct() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.enterTextInSearch(dataProp.getProperty("demoproduct"));
		searchPage = homePage.clickOnSearch();
		Assert.assertTrue(searchPage.verifyCorrectMobileResultIsDisplayed(), "Samsung is available");
		Thread.sleep(3000);
	}

}
