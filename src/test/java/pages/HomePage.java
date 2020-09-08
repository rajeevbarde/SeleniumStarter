package pages;

import org.openqa.selenium.By;

import utils.SeleniumUtils;

public class HomePage extends SeleniumUtils{
		
	public static class HomePage_locators{
		
		public static final By signUpButton = By.className("sign_up");
	}
	
	public void clickSignUpButton() {
		waitForElementPresense(HomePage_locators.signUpButton).click();
	}
}
