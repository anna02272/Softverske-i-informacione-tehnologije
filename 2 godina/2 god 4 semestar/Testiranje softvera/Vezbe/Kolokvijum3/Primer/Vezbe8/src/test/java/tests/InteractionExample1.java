package tests;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class InteractionExample1 {
	WebDriver driver;

	/**
	 * This method will be executed before the test start.
	 */
	@BeforeSuite
	public void initalize() {

		// Create a Selenium WebDriver instance
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();

		// Configure browser if required
		// Maximize browser window
		driver.manage().window().maximize();

		// Wait 5 seconds for loading the page before Exception
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// Wait 1 second before very action
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		// Navigate to the required web page
		driver.navigate().to("https://www.calculator.net/tax-calculator.html");

	}

	@Test
	public void interaction() throws InterruptedException {

		// Drop down item selection
		Select dropdown = new Select(driver.findElement(By.name("cfilestatus")));
		Thread.sleep(1000);
		
		dropdown.selectByVisibleText("Qualified Widow");
		Thread.sleep(1000);
		
		// You can also select by value at value attribute of each option
		// dropdown.selectByValue("Widow");
		
		// Or you can select by index of each option, INDEX STARTS FROM 0
		// dropdown.selectByIndex(4);
		
		// Text Box interaction 
		driver.findElement(By.id("callowance")).sendKeys("2");
		driver.findElement(By.id("callowanceold")).sendKeys("1");

		// Radio Button selection
		driver.findElement(By.id("ctaxyeara")).click();
		
		// Click in Calculate button
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table[5]/tbody/tr[12]/td/input[2]")).click();

	}

	/**
	 * This method will be executed at the end of the test.
	 */
	@AfterSuite
	public void quitDriver() {

		// Close browser window
		driver.quit();
		driver = null;
	}
}
