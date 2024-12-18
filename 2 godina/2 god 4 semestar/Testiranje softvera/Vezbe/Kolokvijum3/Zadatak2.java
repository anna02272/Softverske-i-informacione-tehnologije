package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Zadatak2 {
	WebDriver driver;

	@BeforeSuite
	public void initalize() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.navigate().to("https://www.calculator.net/calorie-calculator.html");
	}

	@Test
	public void calculate() throws InterruptedException {
		
		WebElement ageInput = driver.findElement(By.id("cage"));
		ageInput.clear();
		ageInput.sendKeys("20");
		
		WebElement femaleRadioButton = driver.findElement(By.xpath("//label[@for='csex2']/span[@class='rbmark']"));
		femaleRadioButton.click();
	
	    WebElement heightInput = driver.findElement(By.id("cheightmeter"));
	    heightInput.clear();
	    heightInput.sendKeys("170");
		
		WebElement weightInput = driver.findElement(By.id("ckg"));
		weightInput.clear();
		weightInput.sendKeys("70");
		
		Select dropdown = new Select(driver.findElement(By.name("cactivity")));
		Thread.sleep(1000);
		dropdown.selectByVisibleText("Light: exercise 1-3 times/week");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[5]/table[4]/tbody/tr[3]/td[2]/input[2]")).click();
		
		WebElement resultsTable = driver.findElement(By.xpath("/html/body/div[3]/div[1]/table[1]"));
		
		WebElement maintainWeightRow = resultsTable.findElement(By.xpath(".//tr[1]"));
		String maintainWeightCalories = maintainWeightRow.findElement(By.xpath(".//td[2]/div/b")).getText();
		Assert.assertEquals(maintainWeightCalories, "2,065");
		
		WebElement mildWeightLossRow = resultsTable.findElement(By.xpath(".//tr[2]"));
	    String mildWeightLossCalories = mildWeightLossRow.findElement(By.xpath(".//td[2]/div/b")).getText();
	    Assert.assertEquals(mildWeightLossCalories, "1,815"); 
	    
	    WebElement weightLossRow = resultsTable.findElement(By.xpath(".//tr[3]"));
	    String weightLossCalories = weightLossRow.findElement(By.xpath(".//td[2]/div/b")).getText();
	    Assert.assertEquals(weightLossCalories, "1,565");
	    
	    WebElement extremeWeightLossRow = resultsTable.findElement(By.xpath(".//tr[4]"));
	    String extremeWeightLossCalories = extremeWeightLossRow.findElement(By.xpath(".//td[2]/div/b")).getText();
	    Assert.assertEquals(extremeWeightLossCalories, "1,065");
		

	}
	
	@Test
	public void calculateEmpty() {

		WebElement ageInput = driver.findElement(By.id("cage"));
		ageInput.clear();
	
	    WebElement heightInput = driver.findElement(By.id("cheightmeter"));
	    heightInput.clear();
		
		WebElement weightInput = driver.findElement(By.id("ckg"));
		weightInput.clear();
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[4]/table[4]/tbody/tr[3]/td[2]/input[2]")).click();
		
		WebElement result = driver.findElement(By.xpath("//h1[contains(text(), 'Calorie Calculator')]"));
		WebElement ageElement = result.findElement(By.xpath("following-sibling::div[1]/font"));
	    WebElement heightElement = result.findElement(By.xpath("following-sibling::div[2]/font"));
	    WebElement weightElement = result.findElement(By.xpath("following-sibling::div[3]/font"));
	    
	    String ageElementMessage = ageElement.getText();
	    String heightElementMessage = heightElement.getText();
	    String weightElementMessage = weightElement.getText();
	    
	    Assert.assertEquals(ageElementMessage, "Please provide an age between 15 and 80.");
	    Assert.assertEquals(heightElementMessage, "Please provide positive height value.");
	    Assert.assertEquals(weightElementMessage, "Please provide positive weight value.");
		
	}


	@AfterSuite
	public void quitDriver() {
		driver.quit();
		driver = null;
	}

}
