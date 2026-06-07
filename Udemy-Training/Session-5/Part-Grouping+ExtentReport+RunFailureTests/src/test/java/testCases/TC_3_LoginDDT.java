package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_3_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups={"DataDriven"})
	public void verify_loginDDT(String email, String password, String expect) {
		
		//valid data - login success - test passed - logout
		//valid data - login unsuccess - test failed
		
		//invalid data - login success - test failed - logout
		//invalid data - login unsuccess - test passed
		
		logger.info("******** Starting TC_3_LoginDDT ********");
		
		try
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount link ...");
			hp.clickLogin();
			logger.info("Clicked on Login ...");
			
			LoginPage lp = new LoginPage(driver);
			logger.info("Providing Credentiels: email and password");
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();
			logger.info("Clicked on Login Button ...");
			
			MyAccountPage maac = new MyAccountPage(driver);
			boolean targetPage = maac.isMyAccountPageExists();
			
			if(expect.equalsIgnoreCase("Valid"))
			{
				if(targetPage == true)
				{
					maac.clickLogout();
					Assert.assertTrue(true);
				}
				else
					Assert.assertTrue(false);
			}
			
			if(expect.equalsIgnoreCase("Invalid"))
			{
				if(targetPage == true)
				{
					maac.clickLogout();
					Assert.assertTrue(false);
				}
				else
					Assert.assertTrue(true);
			}
		}
		catch (Exception e) {
			logger.error("Test Failed: "+e.getMessage());
			Assert.fail("Test Failed: "+e.getMessage());
		}
		finally
		{
			logger.info("******** Finished TC_3_LoginDDT ********");
		}
	}
}
