package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_1_AccountRegistrationTest extends BaseClass {

	@Test
	public void verifyAccountRegistration() {
		logger.info("**** Starting TC_1_AccountRegistrationTest ****");
		
		try
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount link ...");
			hp.clickRegister();
			logger.info("Clicked on Register link ...");
			
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			logger.info("Providing Customer details ...");
			regPage.setFirstName(randomString().toUpperCase());
			regPage.setLastName(randomString().toUpperCase());
			regPage.setEmail(randomString()+"@gmail.com");
			regPage.setTelephone(randomNumeric());
			
			String password = randomAlphaNumeric();
			
			regPage.setPassword(password);
			regPage.setConfirmPassword(password);
			
			regPage.setPrivacyPolicy();
			regPage.clickContinue();
			
			logger.info("Validating expected message ...");
			String confmsg = regPage.getConfirmationMsg();
			if(confmsg.equals("Your Account Has Been Created!")) {
				logger.info("Test Passed !!!");
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Test Failed: "+"Confirmation message mismatch");
				logger.debug("This is a debug logs message");
				Assert.assertTrue(false, "Confirmation message mismatch");
			}
			//Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation message mismatch");
			//logger.info("Test Passed");
		}
		catch (Exception e){
			logger.error("Test Failed: "+e.getMessage());
			Assert.fail("Test Failed: "+e.getMessage());
		}
		finally
		{
			logger.info("**** Finished TC_1_AccountRegistrationTest ****");
		}
	}
}
