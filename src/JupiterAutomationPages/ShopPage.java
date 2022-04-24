package JupiterAutomationPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import JupiterAutomationFramework.TestBase;

public class ShopPage extends TestBase {

	public ShopPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@ng-src='images/src-embed/frog.jpg']/following-sibling::p//a")
	WebElement stuffedFrog;

	@FindBy(xpath = "//img[@ng-src='images/src-embed/bunny.jpg']/following-sibling::p//a")
	WebElement fluffyBunny;

	@FindBy(xpath = "//img[@ng-src='images/src-embed/valentine.jpg']/following-sibling::p//a")
	WebElement valentineBunny;

	@FindBy(partialLinkText = "Cart")
	WebElement cartLink;

	float price, sum = 0;
	String cost;

	public float selectProduct(String product, int quan) {

		switch (product) {
		case "Teddy Bear":
			System.out.println("Add Teddy Bear in cart");
			return price;

		case "Stuffed Frog":
			System.out.println("Add Teddy Bear in cart");
			cost = driver.findElement(By.xpath("//img[@ng-src='images/src-embed/frog.jpg']/following-sibling::p//span"))
					.getText().substring(1);
			price = Float.parseFloat(cost);
			for (int i = 0; i < quan; i++) {
				stuffedFrog.click();
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return (Float.parseFloat(cost));

		case "Handmade Doll":
			System.out.println("Add Handmade Doll in cart");
			return price;
		case "Fluffy Bunny":
			System.out.println("Add Fluffy Bunny in cart");
			cost = driver
					.findElement(By.xpath("//img[@ng-src='images/src-embed/bunny.jpg']/following-sibling::p//span"))
					.getText().substring(1);
			price = Float.parseFloat(cost);
			for (int i = 0; i < quan; i++) {
				fluffyBunny.click();
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return (Float.parseFloat(cost));

		case "Smiley Bear":
			System.out.println("Add Smiley Bear in cart");
			return price;

		case "Funny Cow":
			System.out.println("Add Funny Cow in cart");
			return price;

		case "Valentine Bear":
			System.out.println("Add Valentine Bear in cart");
			cost = driver
					.findElement(By.xpath("//img[@ng-src='images/src-embed/valentine.jpg']/following-sibling::p//span"))
					.getText().substring(1);
			price = Float.parseFloat(cost);
			for (int i = 0; i < quan; i++) {
				valentineBunny.click();
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return (Float.parseFloat(cost));

		case "Smiley Face":
			System.out.println("Add Smiley Face in cart");
		}
		return price;
	}

	public void validatePrice(String product, float amount, int quan) {
		switch (product) {
		case "Stuffed Frog":
			System.out.println("Validate Stuffed Frog price and subtotal");
			Assert.assertEquals(amount,
					Float.parseFloat(driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText().substring(1)),
					"Not equal");
			Assert.assertEquals((amount * quan),
					Float.parseFloat(driver.findElement(By.xpath("//tbody/tr[1]/td[4]")).getText().substring(1)),
					"Not equal");
			break;
		case "Fluffy Bunny":
			System.out.println("Validate Fluffy Bunny price and subtotal");
			Assert.assertEquals(amount,
					Float.parseFloat(driver.findElement(By.xpath("//tbody/tr[2]/td[2]")).getText().substring(1)),
					"Not equal");

			Assert.assertEquals(roundOffTwoDecimals((Math.round((amount * quan) * 100.0) / 100.0)),
					driver.findElement(By.xpath("//tbody/tr[2]/td[4]")).getText().substring(1), "Not equal");
			break;
		case "Valentine Bear":
			System.out.println("Validate Valentine Bear price and subtotal");
			Assert.assertEquals(amount,
					Float.parseFloat(driver.findElement(By.xpath("//tbody/tr[3]/td[2]")).getText().substring(1)),
					"Not equal");
			Assert.assertEquals((amount * quan),
					Float.parseFloat(driver.findElement(By.xpath("//tbody/tr[3]/td[4]")).getText().substring(1)),
					"Not equal");
			break;
		}
	}

	public String roundOffTwoDecimals(Double d) {

		String val = Double.toString(d);

		int decimalIndex = val.indexOf(".");

		if (val.length() > (decimalIndex + 3))
			return val.substring(0, decimalIndex + 3);
		else
			return val;

	}

	public void clickCart() {
		cartLink.click();
		Assert.assertTrue(driver.findElement(By.partialLinkText("Check Out")).isDisplayed());

	}

}
