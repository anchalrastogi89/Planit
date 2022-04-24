package JupiterAutomationTestcases;

import java.time.Duration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import JupiterAutomationFramework.TestBase;
import JupiterAutomationFramework.Utilities;
import JupiterAutomationPages.ContactPage;
import JupiterAutomationPages.HomePage;
import JupiterAutomationPages.ShopPage;

public class ContactPageTestcase extends TestBase {
	
	HomePage homePageObj;
	ContactPage contactPageObj;
	Utilities utility;
	ShopPage shopPageObj;

	public ContactPageTestcase() {
		super();
	}

	@DataProvider
	public Object[][] getContactDetail() throws org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		Object data[][] = Utilities.getTestData("Contact");
		return data;
	}
	
	@DataProvider
	public Object[][] getContactDetailMul() throws org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		Object data[][] = Utilities.getTestData("MultiContact");
		return data;
	}
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		homePageObj = new HomePage();
		contactPageObj = new ContactPage();
		shopPageObj = new ShopPage();
	}
	
	@Test(dataProvider="getContactDetail")
	public void validate_error_message(String fname, String surname, String email, String number, String message){
		homePageObj.clickContact();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		contactPageObj.clickSubmit();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		contactPageObj.validateErrorMessageExist();
		contactPageObj.enterContactPageDetails(true, fname, surname, email, number, message);
	}
	
	@Test(dataProvider="getContactDetailMul")
	public void validate_success_message(String fname, String surname, String email, String number, String message){
		homePageObj.clickContact();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		contactPageObj.enterContactPageDetails(true,fname, surname, email, number, message);
		contactPageObj.clickSubmit();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		contactPageObj.validateSuccessMessage();
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
