package JupiterAutomationTestcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import JupiterAutomationFramework.TestBase;
import JupiterAutomationFramework.Utilities;
import JupiterAutomationPages.ContactPage;
import JupiterAutomationPages.HomePage;
import JupiterAutomationPages.ShopPage;

public class AddProductTestcase extends TestBase {

	HomePage homePageObj;
	ContactPage contactPageObj;
	Utilities utility;
	ShopPage shopPageObj;

	public AddProductTestcase() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		homePageObj = new HomePage();
		contactPageObj = new ContactPage();
		shopPageObj = new ShopPage();
	}

	@Test
	public void add_product() {
		homePageObj.clickStartShopping();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		float frogPrice = shopPageObj.selectProduct("Stuffed Frog", 2);
		float bunnyPrice = shopPageObj.selectProduct("Fluffy Bunny", 5);
		float bearPrice = shopPageObj.selectProduct("Valentine Bear", 3);
		shopPageObj.clickCart();
		shopPageObj.validatePrice("Stuffed Frog", frogPrice, 2);
		shopPageObj.validatePrice("Fluffy Bunny", bunnyPrice, 5);
		shopPageObj.validatePrice("Valentine Bear", bearPrice, 3);

		Assert.assertEquals(
				shopPageObj.roundOffTwoDecimals(
						(Math.round((frogPrice * 2 + bunnyPrice * 5 + bearPrice * 3) * 100.0) / 100.0)),
				driver.findElement(By.xpath("//tfoot/tr[1]/td[1]/strong")).getText().substring(7),
				"Total is incorrect");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
