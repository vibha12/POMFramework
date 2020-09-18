package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class LoginPage extends BasePage {
	private WebDriver driver;
	ElementUtil elementutil;
	
	
	//By locators- OR
	
	By emailId= By.id("username");
	By password= By.id("password");
	By loginButton=By.id("loginBtn");
	By signUpLink=By.linkText("Sign up");
	By loginCode= By.id("code");
	//Constructor of the page
	public LoginPage(WebDriver driver)
	{
		elementutil=new ElementUtil(driver);	
		this.driver =driver;
		
	}
	//page actions
	public String getTitle()
	{
		return elementutil.waitForTitlePresent(Constants.Login_Page_Title, 10);
		
	}
	
	public boolean isSignupLinkExist()
	{
		return elementutil.doDoIsDisplayed(signUpLink);
        //return	driver.findElement(signUpLink).isDisplayed();
	}
	
	public HomePage doLigin(String un,String pass) {
		
         elementutil.doSendKeys(emailId, un);
         elementutil.doSendKeys(password, pass);
         elementutil.doClick(loginButton);
         System.out.println("logedin");
               elementutil.waitForElementToBeVisible(loginCode, 30);       
		/*driver.findElement(emailId).sendKeys(un);
		driver.findElement(password).sendKeys(pass);
		driver.findElement(loginButton).click();*/
		return new HomePage(driver);
		
	}

}
