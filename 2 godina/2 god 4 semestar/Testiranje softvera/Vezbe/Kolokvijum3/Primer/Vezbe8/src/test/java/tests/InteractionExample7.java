package tests;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class InteractionExample7 {

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
		driver.navigate().to("https://www.google.com");
		
		// Same as 
		// driver.get("https://www.google.com");

	}

	@Test(priority = 0)
	public void search() {

		// Locate element by value of its NAME attribute
		WebElement input = driver.findElement(By.name("q"));
		
		// Enter text 
		Actions builder = new Actions(driver);
		
		Action seriesOfActions = builder
			.moveToElement(input)
			.click()
			.keyDown(input, Keys.SHIFT)
			.sendKeys(input, "please turn off caps lock")
			.keyUp(input, Keys.SHIFT)
			.build();
		
		seriesOfActions.perform();
		
		// Press ENTER key on keyboard
		input.sendKeys(Keys.ENTER);

	}


	/**
	 * This method will be executed at the end of the test.
	 */
	@AfterSuite
	public void quitDriver() {
		
		// Close browser window
//		driver.quit();
		driver = null;
	}

}
