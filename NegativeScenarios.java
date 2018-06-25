package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativeScenarios {
	WebDriver driver; 
	@BeforeMethod
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("before method");
	}
	
	@AfterMethod
	public void tearDownTest() {
		driver.close();
		System.out.println("after method");
	}
//	test scenario: wrong credentials test
//	go to http://automationpractice.com (Links to an external site.)Links to an external site.
//
//	try to login by entering a any wrong email (email must have a correct format) and any password
//
//	verify that message Authentication failed. is displayed
	
	
	@Test
	public void wrongCredentialsTest() {
		driver.get("http://automationpractice.com");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("me@me.com");
		driver.findElement(By.id("passwd")).sendKeys("hello"+Keys.ENTER);
		String authentication = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]")).getText();
		Assert.assertTrue(authentication.contains("Authentication failed"));
		
	}
	
	
//	test scenario: invalid email test
//	go to http://automationpractice.com (Links to an external site.)Links to an external site.
//
//	try to login by entering a email with invalid format and any password
//
//	verify that message Invalid email address. is displayed
	@Test
	public void invalidEmailTest() {
		driver.get("http://automationpractice.com");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("hello");
		driver.findElement(By.id("passwd")).sendKeys("hello"+Keys.ENTER);
		String authentication = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]")).getText();
		Assert.assertTrue(authentication.contains("Invalid email address"));	
	}
	
//	test scenario: blank email test
//	go to http://automationpractice.com (Links to an external site.)Links to an external site.
//
//	try to login by leaving the email field empty and entering any password
//
//	verify that message An email address required. is displayed
	@Test
	public void blankEmailTest() {
		driver.get("http://automationpractice.com");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("passwd")).sendKeys("hello"+Keys.ENTER);
		String authentication = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]")).getText();
		Assert.assertTrue(authentication.contains("An email address required."));	
	}
	
	
//	test scenario: blank password test
//	go to http://automationpractice.com (Links to an external site.)Links to an external site.
//
//	try to login by entering an email and leaving the password field blank
//
//	verify that message Password is required. is displayed
	@Test
	public void blankPassWordTest() {
		driver.get("http://automationpractice.com");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("me@me.com");
		driver.findElement(By.id("passwd")).sendKeys(""+Keys.ENTER);
		String authentication = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]")).getText();
		Assert.assertTrue(authentication.contains("Password is required."));	
	}
	
}
