package com.qa.hubspot.tests;




import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;

import com.qa.hubspot.utils.Constants;

public class HomePageTest extends BaseTest {

	HomePage homepage;
	@BeforeClass
	public void homePagesetUp()
	{
		homepage=loginpage.doLigin(prop.getProperty("username"), prop.getProperty("password"));
		
	   
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest()
	{
		String title=homepage.getHomePageTitle();
		System.out.println("Home Page Title is :"+title);
		Assert.assertEquals(title, Constants.Home_Page_Title);
	}
	
	@Test(priority=2)
	public void getHeaderNameTest()
	{
		String header=homepage.getHeaderValue();
		System.out.println("Header text is :"+header);
		Assert.assertEquals(header,Constants.Home_Page_Header);
	}
	
	@Test(priority=3)
	public void verifySettingIconTest()
	{
		Assert.assertTrue(homepage.getSettingIcon());
	}
	
	@Test(priority=4)
	public void verifyAccountNameTest()
	{
		String account=homepage.getAccountNameText();
		System.out.println("Account Name is : "+account);
		Assert.assertEquals(account.trim(),prop.getProperty("accountname").trim());
	}
	
	
	
	


}
