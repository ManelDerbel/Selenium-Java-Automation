package com.qa.opencart.pages;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductInfoPage {

	// Private By locators:
	private final By header = By.tagName("h1");
	private final By images = By.xpath("//a[@class='thumbnail']");

	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPrice = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	private WebDriver driver;
	private HashMap<String, String> productMap;

	// Public page class constructor:
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions Methods:
	public String getProductHeader() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement headerProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(header));
		String headerText = headerProduct.getText();
		System.out.println("Product header value is: " + headerText);

		return headerText;
	}

	public int getProductImagesCount() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		List<WebElement> imagesElts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(images));
		int imageCount = imagesElts.size();
		System.out.println("Total images on the page product Info page is: " + imageCount);

		return imageCount;
	}

	private void getProductMetaData() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> metaList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productMetaData));

		System.out.println("Total product meta data size is: " + metaList.size());

		for (WebElement e : metaList) {
			String text = e.getText();
			String meta[] = text.split(":");

			String key = meta[0];
			String value = meta[1].trim();

			productMap.put(key, value);
		}
	}

	private void getProductPrice() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> priceList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrice));

		System.out.println("Total product price is: " + priceList.size());

		String productPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();

		productMap.put("Product Price", productPrice);
		productMap.put("Ex Tax Price", exTaxPrice);
	}

	public HashMap<String, String> getCompleteProductDetails() {
		productMap = new HashMap<String, String>();

		getProductMetaData();
		getProductPrice();

		System.out.println("Complete product details are: \n" + productMap);
		return productMap;
	}
}
