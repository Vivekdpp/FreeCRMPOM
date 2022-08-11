package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	LoginPage loginpage; //Creating object of LoginPage at class level, so you can use it anywhere in class.
	HomePage homepage;

	public LoginPageTest() throws IOException {
		super(); //super keyword is used to call super class constructor. In this framework, it is TestBase, where we are reading data from prop file.
		 
	}
	
	@BeforeMethod
	public void setUp() throws IOException{
		initialization();
		loginpage = new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title = loginpage.validateLoginPageTitle();
		Assert.assertEquals(title, "Free CRM - CRM software for customer relationship management, sales, and support.");
	
	}
	@Test(priority=2)
	public void crmLogoImagetest() throws InterruptedException {
		boolean flag = loginpage.validateCRMImage();
		Assert.assertTrue(flag);
		
		Thread.sleep(2000);
	}
	
	
	@Test(priority=3)
	public void loginTest() throws IOException {
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	

	@AfterMethod
	public void tearDown() {
	driver.quit();
	
    }
	
}
