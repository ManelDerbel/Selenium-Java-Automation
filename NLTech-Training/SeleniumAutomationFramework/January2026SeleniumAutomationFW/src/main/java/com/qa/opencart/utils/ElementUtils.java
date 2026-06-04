package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {
	
	private WebDriver driver;
	
	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public List<String> getElementsTextList(By locator) {
		List<WebElement> elList = getElements(locator);
		
		List<String> textList = new ArrayList<String>();
		
		for(WebElement e : elList) {
			String text = e.getText();
			if(text.length()!=0) {
				textList.add(text);
			}
		}
		return textList;
	}
	
	public boolean isElementDisplayed(By locator) {
		try {
			getElement(locator).isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed on the page");
			e.printStackTrace();
			return false;
		}
	}
	
//	************************ Utility with Waits ************************
	public WebElement waitForElementVisibility(By locator, int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public String waitForExactTitle(String expectedValue, int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
			wait.until(ExpectedConditions.titleIs(expectedValue));
		} catch (TimeoutException e) {
			System.out.println("Expected Title is not matching");
			e.printStackTrace();
		}
		
		return driver.getTitle();
	}
	
	public String waitForUrlContains(String expectedValue, int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		
		try {
			wait.until(ExpectedConditions.urlContains(expectedValue));
		} catch (Exception e) {
			System.out.println("Expected Url is not matching");
			e.printStackTrace();
		}
		
		return driver.getCurrentUrl();
	}

}
