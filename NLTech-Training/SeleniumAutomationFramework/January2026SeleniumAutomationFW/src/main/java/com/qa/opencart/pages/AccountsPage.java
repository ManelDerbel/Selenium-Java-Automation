package com.qa.opencart.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.utils.ElementUtils;

public class AccountsPage {

	// Private By locators:
	private final By headers = By.tagName("h2");
	private final By logoutLink = By.linkText("Logout");
	private final By searchIcon = By.xpath("//div[@id='search']//button");
	private final By searchBar = By.name("search");

	private WebDriver driver;
	private ElementUtils elUtils;

	// Public page class constructor:
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elUtils = new ElementUtils(driver);
	}

	// Actions methods:
	public List<String> getAccPageHaeaders() {
		List<String> headerTextList = elUtils.getElementsTextList(headers);
		return headerTextList;
	}

	public boolean isLogoutLinkExists() {
		boolean flag = elUtils.isElementDisplayed(logoutLink);
		return flag;
	}

	public SearchResultsPage doSearch(String valueSearch) {
		System.out.println("Searched Product is : "+valueSearch);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(searchBar));
		el.clear();
		
		elUtils.getElement(searchBar).sendKeys(valueSearch);
		elUtils.doClick(searchIcon);
		
		return new SearchResultsPage(driver);
	}
}
