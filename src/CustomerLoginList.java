import java.io.*;
import java.util.*;
public class CustomerLoginList
{
	//CREATES ARRAY OF CUSTOMERLOGIN CLASS
	CustomerLogin[] customerLoginDataArray = new CustomerLogin[100];
	int nextItemLocation = 0;
	//FILE TO BE USED
	String itemFileName2 = "CustomerLoginInfo.txt";


	//READ FROM LOGIN FILE
	public void readFromCustomerLoginFile()
	{
		System.out.println("Running reading customer login file");
		nextItemLocation = 0;
		try
		{//CREATES A FILE READER
			FileReader fr = new FileReader(itemFileName2); 
			BufferedReader br = new BufferedReader(fr);
			String listLine = br.readLine();
			
			while(listLine != null )
			{//SPLITS EACH RECORD AND STORED INTO TEMP ARRAY	
				String[] customerLoginData = listLine.split(",");
				CustomerLogin cLogin = new CustomerLogin();
				//EACH INDEX OF THE TEMP ARRAY IS STORED INTO AN OBJECT OF CUSTOMERLOGIN
				cLogin.customerUserName = customerLoginData[0];
				cLogin.customerPassword = customerLoginData[1];
				//USES THE OBJECT AS A PAREMETER
				addToCustomerLoginList(cLogin);
				
				listLine = br.readLine();
			}

			
		}
		catch(Exception e)
		{
			System.out.println("Error reading from file: " + e);
		}

	}
	
	//ADDS RECORD DATA TO THE ARRAY USING A PARAMETER THAT GETS PASSED IN
	public void addToCustomerLoginList(CustomerLogin passedItem)
	{
		System.out.println("Running addToCustomerLoginList");
		customerLoginDataArray[nextItemLocation] = passedItem;
		nextItemLocation++;
		System.out.println(passedItem);
	}
	
	

}