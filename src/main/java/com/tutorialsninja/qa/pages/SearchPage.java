package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	public WebDriver driver;
	
	@FindBy(linkText="iMac")
	private WebElement verifyResult;
	
	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criter')]")
	private WebElement noProductMessage;
	
	@FindBy(linkText="Samsung SyncMaster 941BW")
	private WebElement verifyMobileResult;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyCorrectResultIsDisplayed() {
		boolean verifyRes = verifyResult.isDisplayed();
		return verifyRes;
	}
	
	public String noProductMessageText() {
		String text = noProductMessage.getText();
		return text;
	}
	
	public boolean verifyCorrectMobileResultIsDisplayed() {
		boolean verifyResult = verifyMobileResult.isDisplayed();
		return verifyResult;
	}

}
