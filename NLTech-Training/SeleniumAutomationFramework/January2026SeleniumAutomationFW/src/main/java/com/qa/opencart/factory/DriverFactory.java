package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		String browser = prop.getProperty("browser");

		System.out.println("Browser name is: " + browser);

		switch (browser.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver());
			break;

		case "firefox":
			tlDriver.set(new FirefoxDriver());
			break;

		case "edge":
			tlDriver.set(new EdgeDriver());
			break;

		case "safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println(AppErrors.INVALID_BROWSER_MSG);
			throw new FrameworkException("===BROWSER INVALID===");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public Properties initProp() {
		prop = new Properties();

		try {
			FileInputStream file = new FileInputStream("src/test/resources/config/config.properties");

			try {
				prop.load(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static File getScreenshotAsFile() {
		TakesScreenshot sc = (TakesScreenshot) getDriver();
		File file = sc.getScreenshotAs(OutputType.FILE);
		return file;
	}
}
