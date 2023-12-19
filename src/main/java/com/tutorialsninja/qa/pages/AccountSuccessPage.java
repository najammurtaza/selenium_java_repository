package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.base.Base;

public class AccountSuccessPage extends Base{
	
	public WebDriver driver; 
	@FindBy(xpath="//div[@id='content']//h1")
	private WebElement successPageHeading;
	
	@FindBy(xpath="//a[normalize-space()='Continue']")
	private WebElement successPageContinue;

	public AccountSuccessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getSuccessPageHeading() {
		String headingText = successPageHeading.getText();
		return headingText;
	}
	
	public void clickOnSuccessPageContinue() {
		successPageContinue.click();
	}

}
