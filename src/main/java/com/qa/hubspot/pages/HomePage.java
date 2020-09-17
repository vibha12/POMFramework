package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage{
	private WebDriver driver;
	ElementUtil elementutil;
	//By Locators :OR
	
	By header=By.tagName("h1");
	By accountName=By.xpath("(//div[@class='navAccount-accountName'])[1]");
	By settingIcon= By.id("navSetting");

	By accountMenu= By.id("account-menu");
	By contactParentMenu=By.id("nav-primary-contacts-branch");
	By contactSubMenu=By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver)
	{
		elementutil=new ElementUtil(driver);	
		this.driver =driver;
		
	}
	
	//page Actions:
	
	public String getHomePageTitle()
	{
		 String title=driver.getTitle();
		return elementutil.waitForTitlePresent(title, 10);
		
	}
	
	public String  getHeaderValue()
	{
		if(elementutil.doDoIsDisplayed(header))
		{
			return elementutil.doGetText(header);
		}
		
		return null;
	}
	
	public String  getAccountNameText()
	{
		
		elementutil.doClick(accountMenu);
		if(elementutil.doDoIsDisplayed(accountName))
		{
			return elementutil.doGetText(accountName);
		}
		
		return null;
	}
	
	private void clickContactLinkMenu()
     {
		elementutil.waitForElementPresent(contactParentMenu, 10);
		elementutil.doClick(contactParentMenu);
		elementutil.waitForElementPresent(contactSubMenu, 5);
		elementutil.doClick(contactSubMenu);
	}
	
	public ContactsPage gotToContactPage()
	{
		clickContactLinkMenu();
		return new ContactsPage(driver);
	}
	public boolean getSettingIcon()
	{
		return elementutil.doDoIsDisplayed(settingIcon);
		
	}

}
