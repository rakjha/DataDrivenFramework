package com.qa.DataDriven.Tests;

public class Utilities 
{
	//1. 
	//Creating a New Column in Excel Sheet to Write Status Pass Or Fail
	//reader.addColumn("ParameteriseData", "Status");
	
	//2.
	//To Write Data into Cell [In Status Column, It will write Pass].
	//reader.setCellData("ParameteriseData", "Status", rowNumber, "Pass");
	
	//3.
	//To Create New Sheet in a Excel File.
	//reader.addSheet("HomePage");
	
	//4.
	//If Contacts Page tab already exists >> It will throw an Error.
	//If it is not exist >> It will create a Contacts Page tab in Excel File.
	//if(!reader.isSheetExist("ContactsPage"))
	//{
		//reader.addSheet("ContactsPage");
	//}
	
	//5.
	//To Check Number of Columns Present in any Tab.
	//int columnCount = reader.getColumnCount("ParameteriseData");
	//System.out.println("The Total Columns Present in ParameteriseData Tab is ::: " +columnCount);
	
	//6.
	//To Print Cell Row Number based on Column Name.
	//System.out.println(reader.getCellRowNum("ParameteriseData", "FirstName", "Bair"));
}
