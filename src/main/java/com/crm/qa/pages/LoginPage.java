package com.crm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {


	 //page factory or object repository
	
	@FindBy(name = "username")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//input[@value='Login']")
    WebElement LoginButton; 
	
	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	WebElement signUpButton;
	
	@FindBy(xpath = "//img[@class='img-responsive']")
	WebElement crmLogo;
	
	//Initializing the Page Object
	
	public LoginPage() throws IOException{
		
		PageFactory.initElements(driver, this);
		
	}
	
	//Actions: 
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
		
	}
	public boolean validateCRMImage() {
		 return crmLogo.isDisplayed();
	}
	public HomePage login(String un, String pwd) throws IOException {
		username.sendKeys(un);
		password.sendKeys(pwd);
		LoginButton.click();  //This method is landing method of LoginPage. it will take you too homepage. means it is returning homepage objects.
		
		return new HomePage();
	}
}
