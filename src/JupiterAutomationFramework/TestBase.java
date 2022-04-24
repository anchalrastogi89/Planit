package JupiterAutomationFramework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	

	public TestBase(){
		try {
			prop = new Properties();
			//FileInputStream ip = new FileInputStream("/Users/anchalrastogi/eclipse-workspace/PlanitAutomation/bin/JupiterAutomationFramework/config.properties");
			FileInputStream input = new FileInputStream(Paths.get(".").toAbsolutePath().normalize().toString()+"/src/JupiterAutomationFramework/config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));		
		driver.get(prop.getProperty("url"));
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='btn btn-success btn-large']")).isDisplayed());	
}
}
