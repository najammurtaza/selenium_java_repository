package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public WebDriver driver;
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	private WebElement myAccountMenu;
	
	@FindBy(linkText="Login")
	private WebElement login;
	
	@FindBy(linkText="Register")
	private WebElement register;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	private WebElement search;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchIcon;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMyAccount() {
		myAccountMenu.click();
	}
	
	public LoginPage clickOnLogin() {
		login.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage clickOnRegister() {
		register.click();
		return new RegisterPage(driver);
		}
	
	public void enterTextInSearch(String entersearchText) {
		search.sendKeys(entersearchText);
	}
	
	public SearchPage clickOnSearch() {
		searchIcon.click();
		return new SearchPage(driver);
	}

}
