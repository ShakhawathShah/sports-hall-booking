import java.io.*;
import java.util.*;
public class CustomerPaymentsList
{
	//CREATES ARRAY OF PAYMENTS CLASS
	Payments[] customerPaymentsArray = new Payments[5000];
	int nextItemLocation = 0;
	
	
	String customerPaymentsFileName;	
	Booking bk = new Booking();
	

	public static void main(String[] args)
	{
		CustomerPaymentsList cpList = new CustomerPaymentsList();
		


		
	
	}
	//READ FROM CUSTOMER PAYMENTS FILE
	public void readFromCustomerPaymentsFile()
	{
		System.out.println("Running readFromCustomerPaymentsFile");
		nextItemLocation = 0;
		
		try
		{
			FileReader fr = new FileReader(customerPaymentsFileName); 
			BufferedReader br = new BufferedReader(fr);
			
			//READS LINE AND STORED INTO STRING
			String listLine = br.readLine();
			
			while(listLine != null )
			{	//SPLITS EACH RECORD AND STORED INTO TEMP ARRAY
				String[] paymentData = listLine.split(",");
				Payments tempPay = new Payments();
				//EACH INDEX OF THE TEMP ARRAY IS STORED INTO AN OBJECT OF PAYMENTS
				tempPay.bID= Integer.parseInt(paymentData[0]);
				tempPay.cardHolder = paymentData[2];
				tempPay.cardNumber = paymentData[1];
				tempPay.price = Integer.parseInt(paymentData[3]);
				
				//USES THE OBJECT AS A PAREMETER
				addToCustomerPaymentsList(tempPay);
				listLine = br.readLine();
			}	
		}
		catch(Exception e)
		{
			System.out.println("Error reading from file: " + e);
		}
	}
	
	
	
	public void writeToCustomerPaymentsFile()
	{//CREATES A FILE WRITER 
		System.out.println("Running writeListToCustomerFile For payments");
		try
		{//WRITES THE RECORD FROM THE ARRAY TO FILE
			FileWriter fw = new FileWriter(customerPaymentsFileName);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i < nextItemLocation; i++)
			{
				String currentData = (customerPaymentsArray[i].toString());
				bw.write(currentData);
				bw.newLine();
			}
			bw.close();
		}
		catch(Exception e)
		{
			System.out.println("Error writing to file: " + e);
		}
	}	
	//ADDS RECORD DATA TO THE ARRAY USING A PARAMETER
	public void addToCustomerPaymentsList(Payments passedItem)
	{
		System.out.println("Running addToCustomerPaymentsList");
		
		customerPaymentsArray[nextItemLocation] = passedItem;
		nextItemLocation++;
		System.out.println(passedItem);
	}
/* 	//METHOD TO DISPLAY EACH RECORD OF THE ARRAY ON TO THE COMMAND LINE
	BY RUNNING A LOOP THE LENGTH OF THE ARRAY */
	public void showCustomerPaymentsList()
	{
		for(int i = 0; i < nextItemLocation; i++)
		{
			String currentData = customerPaymentsArray[i].toString();
			System.out.println("Current item info is: " + currentData);
		}
	}
}