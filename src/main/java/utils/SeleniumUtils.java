package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	protected static WebDriver driver;
	protected static HSSFSheet sheet;
	protected static Logger logger = LogManager.getLogger(SeleniumUtils.class.getName());;
	protected static Properties prop;
	protected static String basePath = System.getProperty("user.dir");

	// To read Excel sheet which will return sheet object
	public HSSFSheet readExcelFile(int sheetIndex, String excelPath) {
		try {
			File objFile = new File(basePath + excelPath);
			FileInputStream inputFile = new FileInputStream(objFile);
			HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
			sheet = workBook.getSheetAt(sheetIndex);
			return sheet;
		} catch (Exception Ex) {
			logger.error("Exception occured while reading Excel file :" + Ex.getMessage());
		}
		return sheet;
	}

	// Returns string cell value of Excel sheet
	public String getStringCellValue(int rowIndex, int columnIndex, int sheetIndex, String excelPath) {
		readExcelFile(sheetIndex, excelPath);
		return sheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
	}

	// To read properties from .properties file
	public void propFile(String propPath) {
		try {
			prop = new Properties();
			FileInputStream inputfile = new FileInputStream(basePath + propPath);
			prop.load(inputfile);
		} catch (FileNotFoundException Ex) {
			logger.error("File not found: " + Ex.getMessage());

		} catch (IOException Ex) {
			logger.error("Exception occurred while loading Properties file: " + Ex.getMessage());
		}
	}

	// To initiate browser in different browser
	public void openBrowser(String browser) {

		if (browser.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", basePath + "/chromedriver");
			driver = new ChromeDriver();
		} else if (browser.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", basePath + "/geckodriver");
			driver = new FirefoxDriver();
		} else if (browser.contains("headlessChrome")) {
			System.setProperty("webdriver.chrome.driver", basePath + "/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		logger.info("Browser " + browser + " is getting launched.");
	}

	// To open website in browser
	public void getURL(String url) {
		driver.get(url);
		logger.info("Website " + url + " is getting launched in browser.");
	}

	// To navigate an URL
	public void navigateToSite(String url) {
		driver.navigate().to(url);
		logger.info("Navigating to " + url + " website in browser.");
	}

	// To wait till presence of element
	public WebElement waitForElementPresense(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// To get text of element
	public String getElementtext(By locator) {
		return driver.findElement(locator).getText();
	}

	// To quit all instances of current driver
	public void tearDown() {
		logger.info("Closing browser.");
		driver.quit();
	}

}
