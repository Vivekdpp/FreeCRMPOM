package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{

	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	
	public HomePageTest() throws IOException {
		super();
		
	}
	//test cases should be separated -- independent with each other
		//before each test case -- launch the browser and login
		//@test -- execute test case
		//after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() throws IOException{
		initialization();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		testutil = new TestUtil();
		contactspage = new ContactsPage();
	}

	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		String homePageTitle = homepage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO","Home page title is not matching");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest(){
		testutil.switchToFrame();
		Assert.assertTrue(homepage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() throws IOException{
		testutil.switchToFrame();
		contactspage = homepage.clickOnContactsLink();
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
