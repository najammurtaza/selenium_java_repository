package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.util.TestUtil;

public class Base {
	
		public WebDriver driver;
		public Properties prop;
		public Properties dataProp;
		
		public Base() {
			//Reading the data from config.properties file
			prop = new Properties();
			File propfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
			//Reading the data from testdata.properties file
			dataProp = new Properties();
			File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
			FileInputStream fis2;
			try {
				fis2 = new FileInputStream(dataPropFile);
				dataProp.load(fis2);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				FileInputStream fis = new FileInputStream(propfile);
				prop.load(fis);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		//Browser Initialization
		public WebDriver initializeBrowserAndOpenURL(String browser) {
			if(browser.equals("chrome")) {
				driver = new ChromeDriver();
			}
			else if(browser.equals("firefox")) {
				driver = new FirefoxDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
			driver.get(prop.getProperty("url"));
			
			return driver;
		}


}
