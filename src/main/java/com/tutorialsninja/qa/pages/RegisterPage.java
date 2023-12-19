package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.base.Base;

public class RegisterPage extends Base{
	
	public WebDriver driver;
	
	@FindBy(xpath="//input[@id='input-firstname']")
	private WebElement firstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	private WebElement lastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	private WebElement telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	private WebElement confirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement agreePolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	private WebElement newsLetter;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement accountAlreadyExistWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstnametext) {
		firstName.sendKeys(firstnametext);
	}
	
	public void enterLastName(String lastnametext) {
		lastName.sendKeys(lastnametext);
	}
	
	public void enterEmail(String emailText) {
		email.sendKeys(emailText);
	}
	
	public void enterTelephone(String telephonetext) {
		telephone.sendKeys(telephonetext);
	}
	
	public void enterPassword(String passwordtext) {
		password.sendKeys(passwordtext);
	}
	
	public void enterConfirmPassword(String confirmpasswordtext) {
		confirmPassword.sendKeys(confirmpasswordtext);
	}
	
	public void subscribeNewsLetter() {
		newsLetter.click();
	}
	public void checkAgreePolicy() {
		agreePolicy.click();
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public String accountAlreadyExistWarningMessage() {
		String warningMessage = accountAlreadyExistWarning.getText();
		return warningMessage;
	}
}
