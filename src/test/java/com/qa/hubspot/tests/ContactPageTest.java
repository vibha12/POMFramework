package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactPageTest extends BaseTest {

	HomePage homepage;
	ContactsPage contactspage;
	
	@BeforeClass
	public void homePageSetup() {
		homepage = loginpage.doLigin(prop.getProperty("username"), prop.getProperty("password"));
		contactspage = homepage.gotToContactPage();
	}

	@Test
	public void verifyContactsPageTitleTest() {
		String title = contactspage.getContactsPageTitle();
		System.out.println("contacts page title is: " + title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}

	@Test
	public void verifyContactsPageHeaderTest() {
		String header = contactspage.getContactsPageHeader();
		System.out.println("header is: " + header);
		Assert.assertTrue(header.contains(Constants.CONTACTS_PAGE_HEADER));
	}

	@DataProvider()
	public Object[][] getContactsTestData(){
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	@Test(dataProvider="getContactsTestData")
	public void createContactTest(String emailId, String firstName, String lastName, String jobTitle) {
		contactspage.createContact(emailId, firstName, lastName, jobTitle);
	}
	


}
