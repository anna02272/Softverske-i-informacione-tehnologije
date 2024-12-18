package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class InteractionExample2 {
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
		driver.navigate().to("https://www.calculator.net/mortgage-calculator.html");

	}

	@Test
	public void interaction() throws InterruptedException {

		boolean stateBeforeClick = driver.findElement(By.id("caddoptional")).isSelected();
		System.out.println("The Output of the IsSelected " + stateBeforeClick);
		
		driver.findElement(By.id("caddoptional")).click();
//		driver.findElement(By.id("caddoptional")).click();
		
		boolean stateAfterClick = driver.findElement(By.id("caddoptional")).isSelected();
		System.out.println("The Output of the IsSelected " + stateAfterClick);
		
		System.out.println("The Output of the IsEnabled " + driver.findElement(By.id("caddoptional")).isEnabled());

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
