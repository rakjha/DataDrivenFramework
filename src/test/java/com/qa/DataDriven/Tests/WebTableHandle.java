package com.qa.DataDriven.Tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.Excel.Utilities.Xls_Reader;

public class WebTableHandle 
{
	//Writing WebTable Values into Excel Sheet in Selenium wit Apace POI API.
	@Test
	public void webTable() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
			
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		
		//<<<---- To Handle WebTable Rows ---->>>
		//<<<<<<< tr - Rows - Vertically>>>>>>>
		//<<<<<<< td - Columns - Vertically>>>>>>>
		
		//To Find Size of Rows to Iterate in For Loop
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='customers']/tbody/tr"));
		int rowCount = rows.size();
		System.out.println("Total Number of Rows are ::: " +rowCount);
		
		//XPath for Company Names Column.
		String beforeXpath_company = "//*[@id=\"customers\"]/tbody/tr[";
		String afterXpath_company = "]/td[1]";
		
		//XPath for Contact Names Column.
		String beforeXpath_contact = "//*[@id=\"customers\"]/tbody/tr[";
		String afterXpath_contact = "]/td[2]";
		
		//Path of Excel File and Creating New Tab.
		Xls_Reader reader = new Xls_Reader("D:\\Automation_Workspace\\DataDrivenFramework\\src\\main"
				+ "\\java\\com\\qa\\TestData\\HalfEbayTestData.xlsx");
		
		//Putting this Condition Since WebTableData Tab is already created in Excel File. 
		//There is no Option to Delete a Column - We need to delete Columns Manually.
		if(!reader.isSheetExist("WebTableData"))
		{
			reader.addSheet("WebTableData");
		}		
		
		//Creating Column Names in WebTableData Tab.
		//We can put below to statements into above Condition - If we do not want to Override Data.
		reader.addColumn("WebTableData", "CompanyName");
		reader.addColumn("WebTableData", "ContactName");
		
		System.out.println("***Company Names + Contact Names***");
		
		//Below are to find the Sequence of Xpath in WebTable to write Customized Xpath
		//Company Names XPath
		//*[@id="customers"]/tbody/tr[2]/td[1]
		//*[@id="customers"]/tbody/tr[3]/td[1]
		//*[@id="customers"]/tbody/tr[4]/td[1]
		//*[@id="customers"]/tbody/tr[7]/td[1]
		
		//Contact Names XPath
		//*[@id="customers"]/tbody/tr[2]/td[2]
		//*[@id="customers"]/tbody/tr[3]/td[2]
		//*[@id="customers"]/tbody/tr[4]/td[2]
		//*[@id="customers"]/tbody/tr[7]/td[2]
		
		//1. To Print all First Row Values Vertically.
		//2. To Print all Second Row Values Vertically.
		for(int i=2; i<=rowCount; i++)
		{
			String actualXpath_company = beforeXpath_company + i + afterXpath_company;
			String companyName = driver.findElement(By.xpath(actualXpath_company)).getText();
			System.out.println(companyName);
			//Sending Data into Excel File.
			reader.setCellData("WebTableData", "CompanyName", i, companyName);
			
			String actualXpath_contact = beforeXpath_contact + i + afterXpath_contact;
			String contactName = driver.findElement(By.xpath(actualXpath_contact)).getText();
			System.out.println(contactName);
			//Sending Data into Excel File.
			reader.setCellData("WebTableData", "ContactName", i, contactName);
		}
	}
}
