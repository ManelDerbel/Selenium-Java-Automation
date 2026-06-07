package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.StringUtils;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void userRegistrationSetUp() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	@Test
	public void userRegistrationTest() {

		String fName = StringUtils.randomString();
		String lName = StringUtils.randomString();
		String eMail = StringUtils.randomString() + "@gmail.com";
		String phone = StringUtils.randomNumeric();
		String pass = StringUtils.randomAlphaNumeric();
		boolean flag = registerPage.userRegistration(fName, lName, eMail, phone, pass, "Yes");

		Assert.assertTrue(flag);
	}
}
