import java.io.*;
import java.util.*;
public class PaymentsList
{
	//CREATES ARRAY OF PAYMENTS CLASS
	Payments[] paymentsArray = new Payments[5000];
	int nextItemLocation = 0;
	
	
	String itemFileName = "AllPaymentsData.txt";	
	Booking bk = new Booking();
	
	
	//READ FROM PAYMENTS FILE
	public void readFromPaymentsFile()
	{
		System.out.println("Running readFromFile");
		nextItemLocation = 0;
		
		try
		{
			FileReader fr = new FileReader(itemFileName); 
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
				addToPaymentsList(tempPay);
				listLine = br.readLine();
			}	
		}
		catch(Exception e)
		{
			System.out.println("Error reading from file: " + e);
		}
	}
	
	
	
	public void writeToPaymentsFile()
	{
		System.out.println("Running writeListToFile For payments");
		try
		{//CREATES A FILE WRITER 
			FileWriter fw = new FileWriter(itemFileName);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i < nextItemLocation; i++)
			{//WRITES THE RECORD FROM THE ARRAY TO FILE
				String currentData = (paymentsArray[i].toString());
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
	public void addToPaymentsList(Payments passedItem)
	{
		System.out.println("Running addToList");
		
		paymentsArray[nextItemLocation] = passedItem;
		nextItemLocation++;
		System.out.println(passedItem);
	}
/* 	//METHOD TO DISPLAY EACH RECORD OF THE ARRAY ON TO THE COMMAND LINE
	BY RUNNING A LOOP THE LENGTH OF THE ARRAY */
	public void showPaymentsList()
	{
		for(int i = 0; i < nextItemLocation; i++)
		{
			String currentData = paymentsArray[i].toString();
			System.out.println("Current item info is: " + currentData);
		}
	}
}