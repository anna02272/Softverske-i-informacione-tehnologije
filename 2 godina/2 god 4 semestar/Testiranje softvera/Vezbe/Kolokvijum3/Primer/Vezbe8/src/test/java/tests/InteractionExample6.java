package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class InteractionExample6 {
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
		driver.navigate().to("http://demo.guru99.com/test/simple_context_menu.html");

	}

	@Test
	public void interaction() throws InterruptedException {

		// Double click the button to launch an alertbox
		Actions action = new Actions(driver);
		
		WebElement doubleClickLink = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
		action.doubleClick(doubleClickLink).perform();
		
		// Switch to the alert box and click on OK button
		Alert alert = driver.switchTo().alert();
		
		System.out.println("Alert Text\n" + alert.getText());
		
		Thread.sleep(2000);
		alert.accept();
		
		WebElement rightClickLink = driver.findElement(By.cssSelector(".context-menu-one"));
		action.contextClick(rightClickLink).perform();
		
		// Click on Edit link on the displayed menu options
		WebElement element = driver.findElement(By.cssSelector(".context-menu-icon-copy"));
		element.click();

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
