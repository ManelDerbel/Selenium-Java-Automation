package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {

	// Private By locators:
	private final By searchResults = By.xpath("//div[@class='product-thumb']");
	private final By resultsHeader = By.tagName("h1");

	private WebDriver driver;

	// Public page class constructor:
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions Methods:
	public int getSearchResultsCount() {
		int count = driver.findElements(searchResults).size();
		System.out.println("Total count is :" + count);

		return count;
	}

	public String getResultsHeaderValue() {
		String headerValue = driver.findElement(resultsHeader).getText();
		System.out.println("Header value of the search results page is :" + headerValue);

		return headerValue;
	}

	public ProductInfoPage selectAndClickProduct(String productName) {
		System.out.println("Clicked product is: " + productName);
		driver.findElement(By.linkText(productName)).click();

		return new ProductInfoPage(driver);
	}
}
