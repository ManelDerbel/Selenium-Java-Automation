package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtils;
import com.qa.opencart.utils.ExcelUtils;
import com.qa.opencart.utils.StringUtils;

public class RegistrationPageDataDrivenTest extends BaseTest {

	@BeforeClass
	public void userRegistrationDDTSetUp() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getDataFromSheet() {
		Object[][] obj = ExcelUtils.getTestData("user_registration");
		return obj;
	}

	@Test(dataProvider = "getDataFromSheet")
	public void userRegistrationDDTFromSheet(String fName, String lName, String phone, String pass, String sub) {
		String eMail = StringUtils.randomString() + "@gmail.com";

		boolean flag = registerPage.userRegistration(fName, lName, eMail, phone, pass, sub);
		Assert.assertTrue(flag);
	}

	@DataProvider
	public Object[][] getDataFromCsv() {
		Object[][] obj = CSVUtils.getDataCSV("registration");
		return obj;
	}

	@Test(dataProvider = "getDataFromCsv")
	public void userRegistrationDDTFromCsv(String fName, String lName, String phone, String pass, String sub) {
		String eMail = StringUtils.randomString() + "@gmail.com";

		boolean flag = registerPage.userRegistration(fName, lName, eMail, phone, pass, sub);
		Assert.assertTrue(flag);
	}
}
