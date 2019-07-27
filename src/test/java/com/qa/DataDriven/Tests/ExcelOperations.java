package com.qa.DataDriven.Tests;

import org.testng.annotations.Test;

import com.Excel.Utilities.Xls_Reader;

public class ExcelOperations 
{
	@Test
	public void excelOperations()
	{
		//To Make the Connection with Excel File and Get Data
		Xls_Reader reader = new Xls_Reader("D:\\Automation_Workspace\\DataDrivenFramework\\src\\main\\java\\com\\qa\\TestData\\HalfEbayTestData.xlsx");
	
		//To Create New Sheet in a Excel File.
		reader.addSheet("HomePage");
		
		//If Contacts Page tab already exists >> It will throw an Error.
		//If it is not exist >> It will create a Contacts Page tab in Excel File.
		if(!reader.isSheetExist("ContactsPage"))
		{
			reader.addSheet("ContactsPage");
		}
		
		//To Check Number of Columns Present in any Tab.
		int columnCount = reader.getColumnCount("ParameteriseData");
		System.out.println("The Total Columns Present in ParameteriseData Tab is ::: " +columnCount);
		
		//To Print Cell Row Number based on Column Name.
		System.out.println(reader.getCellRowNum("ParameteriseData", "FirstName", "Bair"));
	}
}
