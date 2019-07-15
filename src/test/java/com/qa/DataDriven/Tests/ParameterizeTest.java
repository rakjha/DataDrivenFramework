package com.qa.DataDriven.Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.Excel.Utility.Xls_Reader;

//Below is example of Parameterization
//Parameterization is always done by using For-Loop.
//It can also be called as Data Driven Approach - Used to Create Data Driven Framework.
//Data Driven Framework means Driving the Test Data from Excel Files Or Some Other Resources.
//Data Driven Framework is also called Parameterization.

//Using below program, we can fill data into Single Page with Different Set of Data.
public class ParameterizeTest 
{
	@Test
	public void fillRegistrationPageData()
	{
		//WebDriver Code.
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
			
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
		driver.get("https://scgi.half.ebay.com/ws/eBayISAPI.dll?RegisterEnterInfo&usage=2943&ru=");	
		
		//To Make the Connection with Excel File and Get Data.
		Xls_Reader reader = new Xls_Reader("D:\\Automation_Workspace\\DataDrivenFramework\\src\\main\\java\\com\\qa\\TestData\\HalfEbayTestData.xlsx");
	
		int rowCount = reader.getRowCount("ParameteriseData");
		System.out.println("The Row Count is ::: " +rowCount);
		
		System.out.println("-------------------------------------");
		
		for(int rowNumber=2; rowNumber<=rowCount; rowNumber++)
		{
			String firstName = reader.getCellData("ParameteriseData", "FirstName", rowNumber);
			System.out.println("The First Name is ::: " +firstName);
			 
			String lastName = reader.getCellData("ParameteriseData", "LastName", rowNumber);
			System.out.println("The Last Name is ::: " +lastName);
				
			String address1 = reader.getCellData("ParameteriseData", "Address1", rowNumber);
			System.out.println("The Address_1 is ::: " +address1);
				
			String address2 = reader.getCellData("ParameteriseData", "Address2", rowNumber);
			System.out.println("The Address_2 is ::: " +address2);
				
			String city = reader.getCellData("ParameteriseData", "City", rowNumber);
			System.out.println("The City Name is ::: " +city);
				
			String state = reader.getCellData("ParameteriseData", "State", rowNumber);
			System.out.println("The State Name is ::: " +state);
						
			String zipCode = reader.getCellData("ParameteriseData", "ZipCode", rowNumber);
			System.out.println("The Zip Code is ::: " +zipCode);
				
			String emailAddress = reader.getCellData("ParameteriseData", "EmailAddress", rowNumber);
			System.out.println("The Email Address is ::: " +emailAddress);
				
			System.out.println("-------------------------------------");
			
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
	}
}
