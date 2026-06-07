package com.qa.opencart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class LoginPage {

	// Private By locators:
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgetPwdLink = By.xpath("(//a[text()='Forgotten Password'])[1]");
	private final By header = By.xpath("(//h2[text()='New Customer'])[1]");
	
	private final By register = By.xpath("(//a[text()='Register'])[2]");

	private WebDriver driver;
	private ElementUtils elUtils;

	// Public page class constructor:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elUtils = new ElementUtils(driver);
	}

	// Public Actions Methods:
	public String getLoginPageTitle() {
		String currentTitle = elUtils.waitForExactTitle(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Title of the page is: " + currentTitle);

		return currentTitle;
	}

	public String getLoginPageUrl() {
		String currentUrl = elUtils.waitForUrlContains(AppConstants.LOGIN_PAGE_FRACTION_URL,
				AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("URL of the page is: " + currentUrl);

		return currentUrl;
	}

	public boolean isForgetPwdLinkExists() {
		boolean flag = elUtils.isElementDisplayed(forgetPwdLink);
		return flag;
	}

	public boolean isHeaderExists() {
		System.out.println("Header of the page: " + driver.findElement(header).getText());
		boolean flag = elUtils.isElementDisplayed(header);

		return flag;
	}

	public AccountsPage doLogin(String uname, String pass) {
		System.out.println("App Credentials are: " + uname + " : " + pass);
		elUtils.waitForElementVisibility(email, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(uname);
		elUtils.waitForElementVisibility(password, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(pass);

		elUtils.doClick(loginBtn);
		elUtils.waitForExactTitle(AppConstants.ACC_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);

		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(register)).click();
		
		return new RegisterPage(driver);
	}
 }
