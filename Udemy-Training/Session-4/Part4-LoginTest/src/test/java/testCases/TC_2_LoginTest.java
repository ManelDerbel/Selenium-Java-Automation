package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_2_LoginTest extends BaseClass {

	@Test
	public void verif_Login() {
		logger.info("**** Starting TC_2_LoginTest ****");
		logger.debug("Capturing application debug logs ...");
		try
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount link ...");
			hp.clickLogin();
			logger.info("Clicked on Login link ...");
			
			LoginPage lp = new LoginPage(driver);
			logger.info("Entering valid email and password ...");
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			logger.info("Clicked on Login button ...");
			
			logger.info("Validating Successfull Login ...");
			MyAccountPage maac = new MyAccountPage(driver);
			boolean targetPage = maac.isMyAccountPageExists();
			Assert.assertEquals(targetPage, true, "Login Failed");
			logger.info("Test Passed !!!");
		}
		catch (Exception e) {
			logger.error("TestFailed: "+ e.getMessage());
			Assert.fail("TestFailed: "+e.getMessage());
		}
		finally {
			logger.info("**** Finished TC_2_LoginTest ****");
		}
	}
}
