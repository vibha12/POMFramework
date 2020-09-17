package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.OutputType;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public static WebDriver driver;
	Properties prop;
	//OptionsManager optionsManager;
	//public static String flashElement;
	/*
	 * public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	 */

	/**
	 * This method is used to initialize the webdriver on the basis of given
	 * browser name
	 * 
	 * @param browserName
	 * @return this returns driver
	 */
	public WebDriver init_driver(Properties prop) {

	//	flashElement = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").trim();

		System.out.println("Browser Name is : " + browserName);
		//optionsManager = new OptionsManager(prop);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
			//tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			//tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
			//tlDriver.set(new SafariDriver());
		}

		else {
			System.out.println("Please pass the correct browser name " + browserName);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));

		return driver;
	}

	/**
	 * getDriver using ThreadLocal
	 */
	/*
	 * public static synchronized WebDriver getDriver() { return tlDriver.get(); }
	 */

	/**
	 * This method is used to get the properties values from config file
	 * 
	 * @return it returns prop
	 */
	public Properties init_prop() {
		prop = new Properties();
		String path = null;
		String env = null;

		try {

			env = System.getProperty("env");
			System.out.println("Running on Environment: " + env);
			if (env == null) {
				path = "./src/main/java/com/qa/hubspot/config/config.properties";
				System.out.println("Running on Environment: " + "PROD");
			} 
			else {
				switch (env) {
				case "qa":
					path = "./src/main/java/com/qa/hubspot/config/config.qa.properties";
					break;
				case "dev":
					path = "./src/main/java/com/qa/hubspot/config/config.dev.properties";
					break;
				case "stage":
					path = "./src/main/java/com/qa/hubspot/config/config.stage.properties";
					break;
				default:
					System.out.println("Please pass the correct env value....");
					break;
				}

			}

			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * This method is used to take screenshot
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}