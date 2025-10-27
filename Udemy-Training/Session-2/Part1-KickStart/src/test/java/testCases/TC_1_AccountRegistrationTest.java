package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_1_AccountRegistrationTest extends BaseClass {

	@Test
	public void verifyAccountRegistration() {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLastName(randomString().toUpperCase());
		regPage.setEmail(randomString()+"@gmail.com");
		regPage.setTelephone(randomNumeric());
		
		String password = randomAlphaNumeric();
		
		regPage.setPassword(password);
		regPage.setConfirmPassword(password);
		
		regPage.setPrivacyPolicy();
		regPage.clickContinue();
		
		String confmsg = regPage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	}
}
