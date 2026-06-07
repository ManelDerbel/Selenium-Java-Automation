package testBase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger; //log4j

public class BaseClass {
	
	public WebDriver driver;
	public Properties p;
	public Logger logger;
	
	@Parameters({"os", "browser"})
	@BeforeClass
	public void setup(String os, String br) throws IOException {
		//Loading Properties file
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		
		//Loading Log4j file
		logger = LogManager.getLogger(this.getClass());
		
		//Launching browser based on condition
		switch(br.toLowerCase())
		{
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driver\\msedgedriver141.0.3537.99.exe");
			driver = new EdgeDriver();
			break;
			case "firefox": driver = new FirefoxDriver(); break;
			default: System.out.println("No matching browser..");
			return;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void teardown() {
		driver.close();
	}
	
	public String randomString() {
		String generatedStr = RandomStringUtils.randomAlphabetic(5);
		return generatedStr;
	}
	
	public String randomNumeric() {
		String generatedNum = RandomStringUtils.randomNumeric(10);
		return generatedNum;
	}
	
	public String randomAlphaNumeric() {
		String str = RandomStringUtils.randomAlphabetic(3);
		String num = RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
}
