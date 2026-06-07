package com.qa.opencart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.constants.AppConstants;

public class RegisterPage {

	// Private By locators:
	private final By firstName = By.id("input-firstname");
	private final By lastName = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.id("input-telephone");
	private final By password = By.id("input-password");
	private final By confirmPassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMsg = By.xpath("//h1[text()='Your Account Has Been Created!']");
	
	private By registerLink = By.xpath("(//a[text()='Register'])[2]");
	private By logoutLink = By.xpath("(//a[text()='Logout'])[2]");

	private WebDriver driver;

	// Public page class constructor:
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions methods:
	public boolean userRegistration(String fName, String lName, String eMail, String phone, String pass,
			String subscribe) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.presenceOfElementLocated(firstName)).sendKeys(fName);

		driver.findElement(lastName).sendKeys(lName);
		driver.findElement(email).sendKeys(eMail);
		driver.findElement(telephone).sendKeys(phone);
		driver.findElement(password).sendKeys(pass);
		driver.findElement(confirmPassword).sendKeys(pass);

		if (subscribe.trim().equals("yes")) {
			driver.findElement(subscribeYes).click();
		} else {
			driver.findElement(subscribeNo).click();
		}

		driver.findElement(agreeCheckBox).click();
		driver.findElement(continueButton).click();

		String actualSuccessMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).getText();
		System.out.println("Registration Success Message is: " + actualSuccessMsg);
		
		driver.findElement(logoutLink).click();
		driver.findElement(registerLink).click();

		if (actualSuccessMsg.equals(AppConstants.USER_REGISTRATION_SUCCESS_MSG)) {
			return true;
		} else {
			return false;
		}
	}
}
