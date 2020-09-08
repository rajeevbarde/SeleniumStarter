package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utils.SeleniumUtils;

public class SignUpPopup extends SeleniumUtils {

	public static class SignUpPage_locators {

		public static final By emailTextBox = By.name("email");
		public static final By passwordTextBox = By.name("password1");
		public static final By createProfile_dropDown = By.xpath("//div[@class='Dropdown-placeholder']");
		public static final By nextButton = By.xpath("//button[contains(text(),'Next')]");
		public static final By motherTongueDropDown = By
				.xpath("//div[@class='Dropdown-control mother_tongue_selector Dropdown-disabled']");
		public static final By dropDownOptions = By.className("Dropdown-option");
		public static final By genderMaleRadioBtn = By.id("gender_male");
	}

	public void enterEmail_password(String email, String password) {

		waitForElementPresense(SignUpPage_locators.emailTextBox).sendKeys(email);
		waitForElementPresense(SignUpPage_locators.passwordTextBox).sendKeys(password);
	}

	public void chooseCreateProfileFor_option() {

		waitForElementPresense(SignUpPage_locators.createProfile_dropDown).click();
	}

	public void chooseDropDownOption(String createProfileFor) {

		List<WebElement> drpDownOptions = driver.findElements(SignUpPage_locators.dropDownOptions);
		for (WebElement dropdown : drpDownOptions) {
			if (dropdown.getText().contentEquals(createProfileFor)) {
				dropdown.click();
				break;
			}
		}
	}

	public void chooseMaleRadioBtn() {
		waitForElementPresense(SignUpPage_locators.genderMaleRadioBtn).click();
	}

	public void clickNextButton() {
		waitForElementPresense(SignUpPage_locators.nextButton).click();
	}

	public void fillUpRegistrationInfo_and_click_next(String email, String password, String createProfileFor) {

		enterEmail_password(email, password);
		chooseCreateProfileFor_option();
		chooseDropDownOption(createProfileFor);
		chooseMaleRadioBtn();
		clickNextButton();
	}

	public void verifyMotherTongue(String expectedMotherTongue) {
		String motherTongue = getElementtext(SignUpPage_locators.motherTongueDropDown);
		logger.info("Verifying Mother Tongue " + motherTongue);
		Assert.assertEquals(motherTongue, expectedMotherTongue);
	}
}
