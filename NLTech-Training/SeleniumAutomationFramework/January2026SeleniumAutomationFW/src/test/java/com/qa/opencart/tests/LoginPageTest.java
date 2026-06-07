package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void LoginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void LoginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}

	@Test(priority = 3)
	public void isForgetPwdLinkExistsTest() {
		boolean flag = loginPage.isForgetPwdLinkExists();
		Assert.assertEquals(flag, true);
	}

	@Test(priority = 4)
	public void isHeaderTest() {
		boolean flag = loginPage.isHeaderExists();
		Assert.assertEquals(flag, true);
	}
	
	@Test(priority = 5)
	public void loginTest() {
		actPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(actPage.isLogoutLinkExists());
	}
}
