package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.hubspot.pages.LoginPage;

public class BaseTest  {
	
	BasePage basepage;
	static WebDriver driver;
	public LoginPage loginpage;
	public static Properties prop = new Properties();
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName)
	{
		System.out.println("Browser Name : "+browserName);
		basepage=new BasePage();
		prop=basepage.init_prop();
		prop.setProperty("browser",browserName);
		driver= basepage.init_driver(prop);
		loginpage=new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
