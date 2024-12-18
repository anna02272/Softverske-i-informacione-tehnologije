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

public class Zadatak3 {
	WebDriver driver;

	@BeforeSuite
	public void initalize() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.navigate().to("https://www.calculator.net/grade-calculator.html");
	}

	@Test
	public void calculate() throws InterruptedException {
		
		WebElement examInput = driver.findElement(By.name("d1"));
		examInput.clear();
		examInput.sendKeys("Testiranje");
		
		WebElement gradeInput = driver.findElement(By.name("s1"));
		gradeInput.clear();
		gradeInput.sendKeys("10");
		
		WebElement weightInput = driver.findElement(By.name("w1"));
		weightInput.clear();
		weightInput.sendKeys("20");
		
		WebElement exam2Input = driver.findElement(By.name("d2"));
		exam2Input.clear();
		exam2Input.sendKeys("Klijentske");
		
		WebElement grade2Input = driver.findElement(By.name("s2"));
		grade2Input.clear();
		grade2Input.sendKeys("8");
		
		WebElement weight2Input = driver.findElement(By.name("w2"));
		weight2Input.clear();
		weight2Input.sendKeys("20");
		
		WebElement exam3Input = driver.findElement(By.name("d3"));
		exam3Input.clear();
		exam3Input.sendKeys("Serverske");
		
		WebElement grade3Input = driver.findElement(By.name("s3"));
		grade3Input.clear();
		grade3Input.sendKeys("7");
		
		WebElement weight3Input = driver.findElement(By.name("w3"));
		weight3Input.clear();
		weight3Input.sendKeys("20");
		
		WebElement exam4Input = driver.findElement(By.name("d4"));
		exam4Input.sendKeys("Mobilne");
		
		WebElement grade4Input = driver.findElement(By.name("s4"));
		grade4Input.sendKeys("9");
		
		WebElement weight4Input = driver.findElement(By.name("w4"));
		weight4Input.sendKeys("20");
		
		WebElement exam5Input = driver.findElement(By.name("d5"));
		exam5Input.sendKeys("Alati");
		
		WebElement grade5Input = driver.findElement(By.name("s5"));
		grade5Input.sendKeys("10");
		
		WebElement weight5Input = driver.findElement(By.name("w5"));
		weight5Input.sendKeys("20");
		
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/form[1]/table/tbody/tr/td/table[9]/tbody/tr/td[1]/input")).click();
		
		  WebElement result = driver.findElement(By.className("verybigtext"));
		    String resultText = result.getText();
		    Assert.assertEquals(resultText, "Average grade: 8.8 (F)");


	}
	
	@Test
	public void calculateEmpty() {
		WebElement examInput = driver.findElement(By.name("d1"));
		examInput.clear();
		
		WebElement gradeInput = driver.findElement(By.name("s1"));
		gradeInput.clear();
		
		WebElement weightInput = driver.findElement(By.name("w1"));
		weightInput.clear();
		
		WebElement exam2Input = driver.findElement(By.name("d2"));
		exam2Input.clear();
		
		WebElement grade2Input = driver.findElement(By.name("s2"));
		grade2Input.clear();
		
		WebElement weight2Input = driver.findElement(By.name("w2"));
		weight2Input.clear();
		
		WebElement exam3Input = driver.findElement(By.name("d3"));
		exam3Input.clear();
		
		WebElement grade3Input = driver.findElement(By.name("s3"));
		grade3Input.clear();
		
		WebElement weight3Input = driver.findElement(By.name("w3"));
		weight3Input.clear();
		
		WebElement exam4Input = driver.findElement(By.name("d4"));
		exam4Input.clear();
		
		WebElement grade4Input = driver.findElement(By.name("s4"));
		grade4Input.clear();
		
		WebElement weight4Input = driver.findElement(By.name("w4"));
		weight4Input.clear();
		
		WebElement exam5Input = driver.findElement(By.name("d5"));
		exam5Input.clear();
		
		WebElement grade5Input = driver.findElement(By.name("s5"));
		grade5Input.clear();
		
		WebElement weight5Input = driver.findElement(By.name("w5"));
		weight5Input.clear();
		
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/form[1]/table/tbody/tr/td/table[9]/tbody/tr/td[1]/input")).click();
		

	    WebElement result = driver.findElement(By.xpath("//div/font[@color='red']"));
	    String resultText = result.getText();
	    Assert.assertEquals(resultText, "Please provide at least one item to calculate.");

		
	}


	@AfterSuite
	public void quitDriver() {
		driver.quit();
		driver = null;
	}

}
