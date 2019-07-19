package com.qa.DataDriven.Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.Excel.Utilities.Xls_Reader;

//This Program is just to Read the Data from Excel File.
//This approach is good, when we have to fill data in any Page with One Set of Data.
//If we need to fill data in any Page with Multiple Set of Data >> Go for Parameterization.
public class DataDrivenTest 
{
	@Test
	public void fillRegistrationPageData()
	{
		//To Make the Connection with Excel File and Get Data.
		Xls_Reader reader = new Xls_Reader("D:\\Automation_Workspace\\DataDrivenFramework\\src\\main\\java\\com\\qa\\TestData\\HalfEbayTestData.xlsx");
		
		String firstName = reader.getCellData("RegTestData", "FirstName", 2);
		System.out.println("The First Name is ::: " +firstName);
		
		String lastName = reader.getCellData("RegTestData", "LastName", 2);
		System.out.println("The Last Name is ::: " +lastName);
		
		String address1 = reader.getCellData("RegTestData", "Address1", 2);
		System.out.println("The Address_1 is ::: " +address1);
		
		String address2 = reader.getCellData("RegTestData", "Address2", 2);
		System.out.println("The Address_2 is ::: " +address2);
		
		String city = reader.getCellData("RegTestData", "City", 2);
		System.out.println("The City Name is ::: " +city);
		
		String state = reader.getCellData("RegTestData", "State", 2);
		System.out.println("The State Name is ::: " +state);
				
		String zipCode = reader.getCellData("RegTestData", "ZipCode", 2);
		System.out.println("The Zip Code is ::: " +zipCode);
		
		String emailAddress = reader.getCellData("RegTestData", "EmailAddress", 2);
		System.out.println("The Email Address is ::: " +emailAddress);
		
		//WebDriver Code.
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://scgi.half.ebay.com/ws/eBayISAPI.dll?RegisterEnterInfo&usage=2943&ru=");	
	
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='address1']")).sendKeys(address1);
		driver.findElement(By.xpath("//input[@id='address2']")).sendKeys(address2);
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys(city);
		
		Select select = new Select(driver.findElement(By.xpath("//select[@id='state']")));
		select.selectByVisibleText(state);
		
		driver.findElement(By.xpath("//input[@id='zip']")).sendKeys(zipCode);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='retype_email']")).sendKeys(emailAddress);
	}
}
