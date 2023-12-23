import java.io.*;
import java.util.*;
import javax.swing.table.*;
import java.time.LocalDate;
import java.time.format.*;
import java.time.*;
import java.text.SimpleDateFormat;  
public class CustomerBookingsList
{
	//CREATES ARRAY OF BOOKING CLASS
	Booking[] customerBookingsArray = new Booking[5000];
	int nextItemLocation = 0;
	int resultOfBookingSearch=-1;
	//FILE TO BE USED
	String customerBookingFileName;	
	Booking bk = new Booking();

	
	//VARIABLES FOR SEARCH RESULTS
	String idCustomerSearchResults;
	String dateCustomerSearchResults;
	String nameCustomerSearchResults;

	
	

	public static void main(String[] args)
	{
		CustomerBookingsList cbList = new CustomerBookingsList();
		
	
	
	}
	//READ FROM BOOKING FILE
	public void readFromCustomerBookingFile()
	{
		System.out.println("Running readFromFile");
		nextItemLocation = 0;
		
		try
		{//CREATES A FILE READER 
			FileReader fr = new FileReader(customerBookingFileName); 
			BufferedReader br = new BufferedReader(fr);
			
			//READS LINE AND STORED INTO STRING
			String listLine = br.readLine();
			
			while(listLine != null )
			{	//SPLITS EACH RECORD AND STORED INTO TEMP ARRAY
				String[] bookingData = listLine.split(",");
				Booking tempBooking = new Booking();
				//EACH INDEX OF THE TEMP ARRAY IS STORED INTO AN OBJECT OF BOOKING
				tempBooking.bookingID = Integer.parseInt(bookingData[0]);
				tempBooking.date = bookingData[1];
				tempBooking.time = bookingData[2];
				tempBooking.room = bookingData[3];
				tempBooking.name = bookingData[4];
				tempBooking.activity = bookingData[5];
				//USES THE OBJECT AS A PAREMETER
				addToCustomerBookingList(tempBooking);
				
				//READS NEXT LINE
				listLine = br.readLine();
			}
			
			
			


			
		}
		catch(Exception e)
		{//ERROR DISPLAYED IF IT CANT READ FROM FILE
			System.out.println("Error reading from file: " + e);
		}
	}
	
	
	//WRITE TO BOOKING FILE
	public void writeToCustomerBookingFile()
	{
		System.out.println("Running writeListToFile For Bookings");
		try
		{//CREATES A FILE WRITER 
			FileWriter fw = new FileWriter(customerBookingFileName);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i < nextItemLocation; i++)
			{//WRITES TO FILE AS LONG AS THE INDEX OF THE ARRAY IS NOT NULL
				if (customerBookingsArray[i] != null)
				{
					String currentData = customerBookingsArray[i].toString();
					bw.write(currentData);
					bw.newLine();
				}
			}
			bw.close();
		}
		catch(Exception e)
		{//ERROR DIPLAYED IF IT CANT WRITE TO FILE
			System.out.println("Error writing to file: " + e);
		}
	}	
	//ADDS RECORD DATA TO THE ARRAY USING A PARAMETER
	public void addToCustomerBookingList(Booking passedItem)
	{
		//System.out.println("Running addToList");
		
		customerBookingsArray[nextItemLocation] = passedItem;
		nextItemLocation++;
		//System.out.println(passedItem);
	}
	
	public void showCustomerBookingList()
	{
		for(int i = 0; i < nextItemLocation; i++)
		{
			String currentData = customerBookingsArray[i].toString();
			System.out.println("Current item info is: " + currentData);
		}
	}

	//SEARCH METHOD USING AN INTEGER PARAMETER
	public void searchCustomerBookingID(int search)
	{
		boolean correctSearchID=false;
		idCustomerSearchResults =("Search Results:");
		for(int i=0; i< nextItemLocation;i++)
		{//SEARCHES FOR ID AT EACH INDEX OF THE ARRAY
			if(customerBookingsArray[i].bookingID == search)
			{//IF FOUND IT WILL STORE THE RECORD OF THAT INDEX INTO A STRING VALUE
				correctSearchID=true;
				idCustomerSearchResults = (idCustomerSearchResults +"\n"+customerBookingsArray[i]+"");
				break;
			}

		}//IF NOT FOUND A DEFAULT MESSSAGE WILL BE STORED INTO THE STRING
		if(correctSearchID==false)
		{
			System.out.println("Booking ID: "+search +" not found ");
			idCustomerSearchResults = (idCustomerSearchResults+"Bookings with ID: "+search+" not found");
		}
	}
	//SEARCH FOR BOOKING METHOD USING AN STRING PARAMETER
	public void searchCustomerBookingDate(String searchDate)
	{
		boolean correctSearchDate=false;
		dateCustomerSearchResults =("Search Results:");
		for(int i=0; i< nextItemLocation;i++)
		{//LOOPS UNTIL INPUT MATCHES DATE OF RECORD IN THE ARRAY
			if(searchDate.equals(customerBookingsArray[i].date))
			{
				correctSearchDate=true;//IF FOUND IT WILL STORE THE RECORD OF THAT INDEX INTO A STRING VALUE
				dateCustomerSearchResults = (dateCustomerSearchResults +"\n"+customerBookingsArray[i]+"");
			}
		}
		if(correctSearchDate==false)
		{
			System.out.println("Booking Date: "+searchDate +" not found ");
			dateCustomerSearchResults = (dateCustomerSearchResults+"Bookings with date: "+searchDate+" not found");
		}
		
		
	}
	//MEHTOD WITH A STRING PAREMETER
	public void searchCustomerBookingName(String searchName)
	{
		boolean correctSearchName=false;
		nameCustomerSearchResults =("Search Results:");
		for(int i=0; i< nextItemLocation;i++)
		{
			if(searchName.equals(customerBookingsArray[i].name))//SERACHES POSTION OFF ARRAY FOR THAT ATTRIBUTE
			{
				correctSearchName=true;//IF FOUND IT WILL STORE THE RECORD OF THAT INDEX INTO A STRING VALUE
				nameCustomerSearchResults = (nameCustomerSearchResults +"\n"+customerBookingsArray[i]+"");
			}
		}
		if(correctSearchName==false)
		{
			System.out.println("Booking Name: "+searchName +" not found ");
			nameCustomerSearchResults = (nameCustomerSearchResults+"Bookings with Name: "+searchName+" not found");
		}

	}

/* 	RUNS BUBBLE SORT BY DATES ON BOOKING DATA
	FOR EACH LOCATION OF THE BOOKING ARRAY IT CONVERTS THE DATE ATTRIBUTE IF THE RECORD INTO LOCALDATE UNDER A FORMATTER 
	USES THE 'isAfter' METHOD TO SORT THE DATES IN ASCENDING ORDER
	 */
	public void bookingCustomerBubbleSortByDateLatest()
	{
		
		System.out.println("Running Bubble sort by latest date");
		for(int i=0; i<nextItemLocation;i++ )
		{
			for(int j=0; j<nextItemLocation-1; j++)
			{
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
				
				
				LocalDate tempDate1 = LocalDate.parse(customerBookingsArray[j].date, formatter);
				 
				LocalDate tempDate2 = LocalDate.parse(customerBookingsArray[j+1].date, formatter);
				boolean swap = tempDate2.isAfter(tempDate1);
				
				if(swap==true)
				{
					Booking tempPosition2 = customerBookingsArray[j] ;
					customerBookingsArray[j] = customerBookingsArray[j+1];
					customerBookingsArray[j+1] = tempPosition2;
				} 
			}
		}




	
	
		
	}
/* 	RUNS BUBBLE SORT BY DATES ON BOOKING DATA
	FOR EACH LOCATION OF THE BOOKING ARRAY IT CONVERTS THE DATE ATTRIBUTE IF THE RECORD INTO LOCALDATE UNDER A FORMATTER 
	USES THE 'isBefore' METHOD TO SORT THE DATES IN DESCENDING ORDER
	 */
	public void bookingCustomerBubbleSortByDateOldest()
	{

		System.out.println("Running Bubble sort by oldest dates");
		for(int i=0; i<nextItemLocation;i++ )
		{
			for(int j=0; j<nextItemLocation-1; j++)
			{
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
				
				
				LocalDate tempDate3 = LocalDate.parse(customerBookingsArray[j].date, formatter);
				 
				LocalDate tempDate4 = LocalDate.parse(customerBookingsArray[j+1].date, formatter);
				boolean swap = tempDate4.isBefore(tempDate3);
				
				if(swap==true)
				{
					Booking tempPosition3 = customerBookingsArray[j] ;
					customerBookingsArray[j] = customerBookingsArray[j+1];
					customerBookingsArray[j+1] = tempPosition3;
				} 
			}
		}

		
	
		
	}
}