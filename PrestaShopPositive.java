package com.prestashop.tests;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Address;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;
public class PrestaShopPositive {
	WebDriver driver;
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(" http://automationpractice.com");
		driver.manage().window().fullscreen();
		driver.findElement(By.xpath("//div[@class='header_user_info']")).click();
	}
	@Test
	public void positive() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Faker f1 = new Faker();
			String email = f1.name().username();
			String firstName = f1.name().firstName();
			String lastName = f1.name().lastName();
			String address = f1.address().streetAddress();
			String pass = "";
			do {
				pass = f1.name().username();
			} while (pass.length() < 5);
			driver.findElement(By.name("email_create")).sendKeys(email + "@gmail.com" + Keys.ENTER);
			Thread.sleep(2000);
			Random r = new Random();
			int gender = r.nextInt(2) + 1;
			driver.findElement(By.xpath("//input[@name='id_gender'and@value='" + gender + "']")).click();
			driver.findElement(By.name("customer_firstname")).sendKeys(firstName);
			driver.findElement(By.name("customer_lastname")).sendKeys(lastName);
			driver.findElement(By.name("passwd")).sendKeys(pass);
			Date date = f1.date().birthday();
			int days = Integer.parseInt(date.toString().substring(8, 10));
			driver.findElement(By.xpath("//select[@id=\"days\"]/option[@value='" + days + "']")).click();
			for (int j = 1; j < 13; j++) {
				String months = date.toString().substring(4, 7);
				String actual = driver.findElement(By.xpath("//select[@id=\"months\"]/option[@value='" + j + "']"))
						.getText();
				if (actual.contains(months)) {
					driver.findElement(By.xpath("//select[@id=\"months\"]/option[@value='" + j + "']")).click();
					break;
				}
			}
			String year = "" + f1.number().numberBetween(1900, 2018);
			driver.findElement(By.xpath("//select[@id=\"years\" ]/option[@value='" + year + "']")).click();
			driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
			driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
			driver.findElement(By.xpath("//input[@id='address1']")).sendKeys(address);
			String city = f1.address().cityName();
			driver.findElement(By.xpath("//input[@id='city']")).sendKeys(city);
			int state = f1.number().numberBetween(2, 54);
			driver.findElement(By.xpath("//select[@id='id_state']/option[" + state + "]")).click();
			String zip = f1.address().zipCode().substring(0, 5);
			driver.findElement(By.id("postcode")).sendKeys(zip);
			driver.findElement(By.id("id_country")).click();
			String phone = f1.phoneNumber().cellPhone();
			driver.findElement(By.id("phone_mobile")).sendKeys(phone);
			driver.findElement(By.name("alias")).sendKeys("Home Adress");
			driver.findElement(By.id("submitAccount")).click();
			new WebDriverWait(driver, 10)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='logout']")));
			driver.findElement(By.xpath("//a[@class='logout']")).click();
		}
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}