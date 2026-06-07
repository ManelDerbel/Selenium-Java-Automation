package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetUp() throws InterruptedException {
		actPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public boolean isLogoutLinkExistsTest() {
		boolean flag = actPage.isLogoutLinkExists();
		return flag;
	}

	@Test
	public void searchProductTest() {
		actPage.doSearch("iMac");
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> accPageHeaders = actPage.getAccPageHaeaders();
		Assert.assertEquals(accPageHeaders.size(), AppConstants.EXPECTED_HEADERS);
	}
}
