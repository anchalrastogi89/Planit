package JupiterAutomationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import JupiterAutomationFramework.TestBase;



public class HomePage extends TestBase {

	
	public HomePage() {
        PageFactory.initElements(driver, this);
	}
	
	@FindBy(id ="nav-contact")
	WebElement contactLnk;
	
	@FindBy(xpath ="//*[@class='alert alert-info ng-scope']/strong")
	WebElement welcomeLnk;
	
	@FindBy(xpath ="//a[@class = 'btn btn-success btn-large']")
	WebElement startShoppingBtn;
	
	public void clickContact() {
		contactLnk.click();
		Assert.assertEquals("We welcome your feedback",welcomeLnk.getText());
	}
	
	public boolean verifyContactsLabel(){
		return welcomeLnk.isDisplayed();
	}
	
	public void clickStartShopping() {
		startShoppingBtn.click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//img[@ng-src='images/src-embed/frog.jpg']/following-sibling::p//a"))
						.isDisplayed());
	}
}
