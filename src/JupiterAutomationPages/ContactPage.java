package JupiterAutomationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import JupiterAutomationFramework.TestBase;

public class ContactPage extends TestBase {

	public ContactPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@class='btn-contact btn btn-primary']")
	WebElement submitBtn;

	@FindBy(id = "forename-err")
	WebElement forenameErrTxt;

	@FindBy(id = "email-err")
	WebElement emailErrTxt;

	@FindBy(id = "message-err")
	WebElement messageErrTxt;

	@FindBy(id = "forename")
	WebElement forenameTxt;

	@FindBy(id = "surname")
	WebElement surnameTxt;

	@FindBy(id = "email")
	WebElement emailTxt;

	@FindBy(id = "telephone")
	WebElement telephoneTxt;

	@FindBy(id = "message")
	WebElement messageTxt;

	@FindBy(className = "ng-binding")
	WebElement successMsg;

	public void clickSubmit() {
		submitBtn.click();
	}

	public void validateErrorMessageExist() {
		Assert.assertTrue(forenameErrTxt.isDisplayed());
		Assert.assertTrue(emailErrTxt.isDisplayed());
		Assert.assertTrue(messageErrTxt.isDisplayed());
	}

	public void validateErrorMessageDoesNotExist() {
		Assert.assertFalse(forenameErrTxt.isDisplayed());
		Assert.assertFalse(emailErrTxt.isEnabled());
		Assert.assertFalse(messageErrTxt.isEnabled());
	}

	public void enterContactPageDetails(boolean mandatory, String ftName, String ltName, String email, String phone,
			String msg) {
		forenameTxt.sendKeys(ftName);
		emailTxt.sendKeys(email);
		messageTxt.sendKeys(msg);
		if (!mandatory) {
			surnameTxt.sendKeys(ltName);
			telephoneTxt.sendKeys(phone);
		}
	}

	public void validateSuccessMessage() {
		Assert.assertTrue(successMsg.isDisplayed());
	}

}
