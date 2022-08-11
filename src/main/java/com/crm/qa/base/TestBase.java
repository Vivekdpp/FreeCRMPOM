package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;  //Global variables, child classes also can use them.
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	
	public TestBase() throws IOException{  //constructor
	     
		try{
		
        FileInputStream ip = new FileInputStream("/Users/vivekdpatel/Desktop/Selenium/Selenium_Workspace/FreeCRMTest/src/main/java/com/crm/qa/config/config.properties");
		
		prop = new Properties();
		prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
 }


//now after construction, create one Inilialization method to initialize the global variables.

   public static void initialization() throws IOException {
	   
	   String browserName = prop.getProperty("browser");
	   if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/Users/vivekdpatel/Desktop/Selenium/SeleniumJars/geckodriver");
			driver = new FirefoxDriver();
	   }else {
	       System.setProperty("webdriver.chrome.driver", "/Users/vivekdpatel/Desktop/Selenium/SeleniumJars/chromedriver");
	       driver = new ChromeDriver();//open Chrome
	}
	   e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
	   
	   driver.manage().window().maximize();
	   driver.manage().deleteAllCookies();
	   driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
	   
	   driver.get(prop.getProperty("url"));
	   
	   
   } 
}

  