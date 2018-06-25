package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PositiveScenarios {

//	@DataProvider(name = "forSearch", parallel = true)
//	public Object[] forSearch() {
//		String[] firstNames = new String[20];
//		Faker faker = new Faker();
//		for (int i = 0; i < 20; i++) {
//			firstNames[i] = faker.name().firstName();
//		}
//		return firstNames;
//	}

	public static void waitFor(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
	}

	public static void waitForId(WebDriver driver, String Id) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id(Id)));
	}

	WebDriver driver;

	@BeforeMethod
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("before method");
	}

	@AfterMethod
	public void tearDownTest() throws InterruptedException {
		driver.findElement(By.className("logout")).click();
		Thread.sleep(2000);
		driver.close();
		System.out.println("after method");
	}

	// test scenario: login test
	// go to http://automationpractice.com (Links to an external site.)Links to an
	// external site.
	//
	// register a new user. You have to generate a new email, first name, last name
	// every time
	//
	// Sign out once the registration is complete
	//
	// Log back using the same information
	//
	// Verify that correct first name and last name is displayed on the top right

	@Test//(description = "Search text",dataProvider = "forSearch")
	public void loginTest() throws InterruptedException {
		driver.get("http://automationpractice.com");
		driver.findElement(By.className("login")).click();
		waitForId(driver, "email_create");
		driver.findElement(By.id("email_create")).sendKeys("merd@mer.com" + Keys.ENTER);
		waitForId(driver, "customer_firstname");
		driver.findElement(By.id("customer_firstname")).sendKeys("metr");
		Thread.sleep(1000);
		driver.findElement(By.id("customer_lastname")).sendKeys("lastrr");
		driver.findElement(By.id("passwd")).sendKeys("11111");
		driver.findElement(By.id("address1")).sendKeys("last");
		driver.findElement(By.id("city")).sendKeys("last");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//select[@id='id_state']/option[@value='46']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("postcode")).sendKeys("11111");
		driver.findElement(By.xpath("//select[@id='id_country']/option[@value='21']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("phone_mobile")).sendKeys("1112223333");
		Thread.sleep(5000);
		driver.findElement(By.id("submitAccount")).click();
		Thread.sleep(5000);
	}
}
