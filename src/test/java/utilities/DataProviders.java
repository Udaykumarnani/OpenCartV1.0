package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="loginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_Logins.xlsx"; // taking file from test data
		
		ExcelUtilities utilities= new ExcelUtilities(path);  // utility object
		
		int totalRows=utilities.getRowCount("Uday");
		int totalColumns=utilities.getColCount("Uday", 1);
		
		String loginData [][]= new String [totalRows][totalColumns]; // 2 dimensional array to store data
		
		for(int i=1;i<=totalRows;i++)  // 1 reading data from Excel and storing data in 2d array
		{
			for(int j=0;j<totalColumns;j++) // 0  
			{
				loginData[i-1][j]=utilities.getCellData("Uday", i, j);  // 1,0 
			}
		}
		       
	  return loginData;
		
	}

}
