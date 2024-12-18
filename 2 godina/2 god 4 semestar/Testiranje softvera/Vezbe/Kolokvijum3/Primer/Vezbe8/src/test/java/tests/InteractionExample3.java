package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class InteractionExample3 {
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
		driver.navigate().to("http://jqueryui.com/droppable/#default");

	}

	@Test
	public void interaction() throws InterruptedException {

		Actions act = new Actions(driver);

		// Locate element by its TAG NAME (element selector)
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		
		System.out.println(iFrame.getSize());
		
		driver.switchTo().frame(iFrame);
		
		// Select source element
		WebElement From = driver.findElement(By.id("draggable"));
		System.out.println(From.getLocation());
		
		// Select destination element
		WebElement To = driver.findElement(By.id("droppable"));
		System.out.println(To.getLocation());
		
		Thread.sleep(3000);
		
		// Do drag and drop
		act.dragAndDrop(From, To).build().perform();
		
		Thread.sleep(3000);

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
