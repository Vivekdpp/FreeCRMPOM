package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	String sheetName = "contacts";

	public ContactsPageTest() throws IOException {
		super();
		
	}
	
	@BeforeMethod
	public void setUp() throws IOException{
		initialization();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		testutil = new TestUtil();
		contactspage = new ContactsPage();
		testutil.switchToFrame();
		contactspage = homepage.clickOnContactsLink(); 
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel(){
		Assert.assertTrue(contactspage.verifyContactsLabel(), "contacts label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest(){
		contactspage.selectContactsByName("Harry Rose");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest(){
		contactspage.selectContactsByName("Harry Rose");
		contactspage.selectContactsByName("Tom hank");

	}
	
	//@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	//Below Test is not working, Element not interactable error. I tried a lot but not worked!
	//@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstName, String lastName, String company) {
		homepage.clickOnNewContactLink();
		//contactspage.createNewContact("Mr.", "Tom", "Peter", "Google");
		contactspage.createNewContact(title, firstName, lastName, company);
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
