package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignUpPopup;
import utils.SeleniumUtils;

public class VerifyMotherTongue extends SeleniumUtils {

	HomePage objHomePage = new HomePage();
	SignUpPopup objSignUp = new SignUpPopup();
	String createProfileFor = "Self";

	@BeforeClass
	@Parameters({ "browser", "excelPath", "propPath" })
	public void setup(String browser, String excelPath, String propPath) {

		openBrowser(browser);
		propFile(propPath);
		getURL(getStringCellValue(1, 0, 0, excelPath));
	}

	@Test(priority = 1)
	@Parameters({ "browser", "excelPath" })
	public void verifyMarathiSiteTongue(String browser, String excelPath) {

		objHomePage.clickSignUpButton();
		objSignUp.fillUpRegistrationInfo_and_click_next(prop.getProperty("email"), prop.getProperty("password"),
				createProfileFor);
		objSignUp.verifyMotherTongue(getStringCellValue(1, 1, 0, excelPath));
	}

	@Test(priority = 2)
	@Parameters({ "browser", "excelPath" })
	public void verifyGujaratiSiteTongue(String browser, String excelPath) {

		navigateToSite(getStringCellValue(2, 0, 0, excelPath));
		objHomePage.clickSignUpButton();
		objSignUp.fillUpRegistrationInfo_and_click_next(prop.getProperty("email"), prop.getProperty("password"),
				createProfileFor);
		objSignUp.verifyMotherTongue(getStringCellValue(2, 1, 0, excelPath));
	}

	@AfterClass
	public void closeBrowser() {
		tearDown();
	}

}
