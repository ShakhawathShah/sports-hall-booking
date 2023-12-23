import java.io.*;
import java.util.*;
public class CustomerInformationList
{
	//CREATES ARRAY OF CUSTOMERINFORMATION CLASS
	CustomerInformation[] CustomerInfoArray = new CustomerInformation[5000];
	int nextItemLocation = 0;
	
	//FILE TO BE USED
	String itemFileName = "CustomerInfo.txt";	
	Booking bk = new Booking();
	//READ FROM CUSTOMERINFORMATION FILE
	public void readFromCustomerInfoFile()
	{
		System.out.println("Running readFromCustomerInfoFile");
		nextItemLocation = 0;
		
		try
		{//CREATES A FILE READER 
			FileReader fr = new FileReader(itemFileName); 
			BufferedReader br = new BufferedReader(fr);
			
			//READS LINE AND STORED INTO STRING
			String listLine = br.readLine();
			
			while(listLine != null )
			{	//SPLITS EACH RECORD AND STORED INTO TEMP ARRAY
				String[] paymentData = listLine.split(",");
				CustomerInformation tempCI = new CustomerInformation();
				//EACH INDEX OF THE TEMP ARRAY IS STORED INTO AN OBJECT OF CUSTOMERINFORMATION
				tempCI.cName= paymentData[0];
				tempCI.phoneNum = paymentData[1];
				tempCI.email = paymentData[2];

				
				//USES THE OBJECT AS A PAREMETER
				addToCustomerInfoList(tempCI);
				listLine = br.readLine();
			}	
		}
		catch(Exception e)
		{
			System.out.println("Error reading from file: " + e);
		}
	}
	
	
	
	public void writeToCustomerInfoFile()
	{
		System.out.println("Running writeListToFile For CustomerInfo");
		try
		{//CREATES A FILE WRITER 
			FileWriter fw = new FileWriter(itemFileName);
			BufferedWriter bw = new BufferedWriter(fw);
			//WRITES TO FILE EACH INDEX OF THE ARRAY
			for(int i = 0; i < nextItemLocation; i++)
			{
				String currentData = (CustomerInfoArray[i].toString());
				bw.write(currentData);
				bw.newLine();
			}
			bw.close();
		}
		catch(Exception e)
		{//ERROR DIPLAYED IF IT CANT WRITE TO FILE
			System.out.println("Error writing to file: " + e);
		}
	}	
	//ADDS RECORD DATA TO THE ARRAY USING A PARAMETER
	public void addToCustomerInfoList(CustomerInformation passedItem)
	{
		System.out.println("Running addToCustomerInfoList");
		
		CustomerInfoArray[nextItemLocation] = passedItem;
		nextItemLocation++;
		System.out.println(passedItem);
	}
/* 	//METHOD TO DISPLAY EACH RECORD OF THE ARRAY ON TO THE COMMAND LINE
	BY RUNNING A LOOP THE LENGTH OF THE ARRAY */
	public void showCustomerInfoList()
	{
		for(int i = 0; i < nextItemLocation; i++)
		{
			String currentData = CustomerInfoArray[i].toString();
			System.out.println("Current item info is: " + currentData);
		}
	}
}