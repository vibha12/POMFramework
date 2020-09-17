package com.qa.hubspot.tests;


import org.testng.Assert;

import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;

import com.qa.hubspot.utils.Constants;



public class LoginPageTest extends BaseTest{
	
	
	
	
    @Test(priority= 2)
	public void verifyLoginPageTitleTest()
	{

		String pageTitle=loginpage.getTitle();
		System.out.println("Page title of Login Page :"+pageTitle);
		
		Assert.assertEquals(pageTitle, Constants.Login_Page_Title);
	}
	
	@Test(priority=1)
	public void verifySignupLink()
	{
       Assert.assertTrue(loginpage.isSignupLinkExist());

	}
	
	@Test(priority=3)
	public void loginTest()
	{
		loginpage.doLigin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	

}
