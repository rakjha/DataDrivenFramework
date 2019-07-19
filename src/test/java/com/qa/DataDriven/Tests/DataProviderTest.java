package com.qa.DataDriven.Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Excel.Utilities.DataProviderUtility;

public class DataProviderTest 
{
	WebDriver driver;
	String sheetName = "ParameteriseData"; 
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://scgi.half.ebay.com/ws/eBayISAPI.dll?RegisterEnterInfo&usage=2943&ru=");
	}
	
	@DataProvider
	public Object[][] getCRMContactsTestData() //To Access Sheet from Test Data Sheet
	{
		Object data [][] = DataProviderUtility.getTestData(sheetName);
		return data;
	}
	
	@Test(dataProvider="getCRMContactsTestData")
	public void dataProviderTest(String firstName, String lastName, String address1, String address2, 
			String city, String state, String zipCode, String emailAddress)
	{
		driver.findElement(By.xpath("//input[@id='firstname']")).clear();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		
		driver.findElement(By.xpath("//input[@id='lastname']")).clear();
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		
		driver.findElement(By.xpath("//input[@id='address1']")).clear();
		driver.findElement(By.xpath("//input[@id='address1']")).sendKeys(address1);
		
		driver.findElement(By.xpath("//input[@id='address2']")).clear();
		driver.findElement(By.xpath("//input[@id='address2']")).sendKeys(address2);
		
		driver.findElement(By.xpath("//input[@id='city']")).clear();
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys(city);
			
		Select select = new Select(driver.findElement(By.xpath("//select[@id='state']")));
		select.selectByVisibleText(state);
		
		driver.findElement(By.xpath("//input[@id='zip']")).clear();
		driver.findElement(By.xpath("//input[@id='zip']")).sendKeys(zipCode);
		
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress);
		
		driver.findElement(By.xpath("//input[@id='retype_email']")).clear();
		driver.findElement(By.xpath("//input[@id='retype_email']")).sendKeys(emailAddress);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
