package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Zadatak1 {
	WebDriver driver;

	@BeforeSuite
	public void initalize() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.navigate().to("https://www.calculator.net/");
	}

	@Test
	public void generate() {
		driver.findElement(By.xpath("//*[@id=\"hl3\"]/li[4]/a")).click();
		
		WebElement slowerInput = driver.findElement(By.name("slower"));
		slowerInput.clear();
		slowerInput.sendKeys("0");
	
	    WebElement supperInput = driver.findElement(By.name("supper"));
		supperInput.clear();
		supperInput.sendKeys("20");
		
		driver.findElement(By.name("submit1")).click();
		
		String result = driver.findElement(By.className("verybigtext")).getText();
		int generatedNumber = Integer.parseInt(result);

		System.out.println(" The Result is " + result);
		
		Assert.assertTrue(generatedNumber >= 0 && generatedNumber <= 20);

	}
	
	@Test
	public void generateEmpty() {

		WebElement slowerInput = driver.findElement(By.name("slower"));
		slowerInput.clear();
	
		WebElement supperInput = driver.findElement(By.name("supper"));
		supperInput.clear();
		
		driver.findElement(By.name("submit1")).click();
		
		WebElement result = driver.findElement(By.className("h2result"));
		WebElement lowerLimitElement = result.findElement(By.xpath("following-sibling::font[1]"));
	    WebElement upperLimitElement = result.findElement(By.xpath("following-sibling::font[2]"));
	    
	    String lowerLimitMessage = lowerLimitElement.getText();
	    String upperLimitMessage = upperLimitElement.getText();
	    
	    Assert.assertEquals(lowerLimitMessage, "Please provide a valid integer as lower limit value.");
	    Assert.assertEquals(upperLimitMessage, "Please provide a valid integer as upper limit value.");
	}


	@AfterSuite
	public void quitDriver() {
		driver.quit();
		driver = null;
	}

}
