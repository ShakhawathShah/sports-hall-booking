import java.io.*;
import java.util.*;
import javax.swing.table.*;
import java.time.LocalDate;
import java.time.format.*;
import java.time.*;
import java.text.SimpleDateFormat;  
public class BookingSystem
{
	//CREATES ARRAY OF BOOKING CLASS
	Booking[] bookingsArray = new Booking[5000];
	int nextItemLocation = 0;
	int resultOfBookingSearch=-1;
	//FILE TO BE USED
	String itemFileName = "AllBookingsData.txt";	
	Booking bk = new Booking();//OBJECT OF BOOKING

	//VARIABLES FOR SEARCH RESULTS
	String idSearchResults;
	String dateSearchResults;
	String nameSearchResults;

	

	public static void main(String[] args)
	{
		BookingSystem bs = new BookingSystem();
		//bs.readFromBookingFile();
	}
	//READ FROM BOOKING FILE
	public void readFromBookingFile()
	{
		System.out.println("Running readFromFile");
		nextItemLocation = 0;
		
		try
		{//CREATES A FILE READER 
			FileReader fr = new FileReader(itemFileName); 
			BufferedReader br = new BufferedReader(fr);
			
			//READS LINE AND STORED INTO STRING
			String listLine = br.readLine();
			
			while(listLine != null )
			{//SPLITS EACH RECORD AND STORED INTO TEMP ARRAY
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
				addToBookingList(tempBooking);
				
				//READS NEXT LINE
				listLine = br.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println("Error reading from file: " + e);
		}
	}
	
	
	//WRITE TO BOOKING FILE
	public void writeToBookingFile()
	{
		System.out.println("Running writeListToFile For Bookings");
		try
		{//CREATES A FILE WRITER 
			FileWriter fw = new FileWriter(itemFileName);
			BufferedWriter bw = new BufferedWriter(fw);
			//WRITES TO FILE AS LONG AS THE INDEX OF THE ARRAY IS NOT NULL
			for(int i = 0; i < nextItemLocation; i++)
			{
				if (bookingsArray[i] != null)
				{
					String currentData = bookingsArray[i].toString();//WRITES THE RECORD FROM THE ARRAY TO FILE
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
	public void addToBookingList(Booking passedItem)
	{
		//System.out.println("Running addToList");
		
		bookingsArray[nextItemLocation] = passedItem;
		nextItemLocation++;
		//System.out.println(passedItem);
	}
	
	public void showBookingList()
	{
		for(int i = 0; i < nextItemLocation; i++)
		{
			String currentData = bookingsArray[i].toString();
			System.out.println("Current item info is: " + currentData);
		}
	}

	//SEARCH METHOD USING AN INTEGER PARAMETER
	public void searchBookingID(int search)
	{
		boolean correctSearchID=false;
		idSearchResults =("Search Results:");
		for(int i=0; i< nextItemLocation;i++)
		{//SEARCHES FOR ID AT EACH INDEX OF THE ARRAY
			if(bookingsArray[i].bookingID == search)
			{//IF FOUND IT WILL STORE THE RECORD OF THAT INDEX INTO A STRING VALUE
				correctSearchID=true;
				idSearchResults = (idSearchResults +"\n"+bookingsArray[i]+"");
				break;//ENDS LOOP
			}

		}//IF NOT FOUND A DEFAULT MESSSAGE WILL BE STORED INTO THE STRING
		if(correctSearchID==false)
		{
			System.out.println("Booking ID: "+search +" not found ");
			idSearchResults = (idSearchResults+"Bookings with ID: "+search+" not found");
		}
	}
	//SEARCH FOR BOOKING METHOD USING AN STRING PARAMETER
	public void searchBookingDate(String searchDate)
	{
		boolean correctSearchDate=false;
		dateSearchResults =("Search Results:");
		for(int i=0; i< nextItemLocation;i++)
		{//LOOPS UNTIL INPUT MATCHES DATE OF RECORD IN THE ARRAY
			if(searchDate.equals(bookingsArray[i].date))
			{
				correctSearchDate=true;//IF FOUND IT WILL STORE THE RECORD OF THAT INDEX INTO A STRING VALUE
				dateSearchResults = (dateSearchResults +"\n"+bookingsArray[i]+"");
			}
		}
		if(correctSearchDate==false)
		{
			System.out.println("Booking Date: "+searchDate +" not found ");
			dateSearchResults = (dateSearchResults+"Bookings with date: "+searchDate+" not found");
		}
		
		
	}
	//MEHTOD WITH A STRING PAREMETER
	public void searchBookingName(String searchName)
	{
		boolean correctSearchName=false;
		nameSearchResults =("Search Results:");
		for(int i=0; i< nextItemLocation;i++)
		{
			if(searchName.equals(bookingsArray[i].name))//SERACHES POSTION OFF ARRAY FOR THAT ATTRIBUTE
			{
				correctSearchName=true;//IF FOUND IT WILL STORE THE RECORD OF THAT INDEX INTO A STRING VALUE
				nameSearchResults = (nameSearchResults +"\n"+bookingsArray[i]+"");
			}
		}
		if(correctSearchName==false)
		{
			System.out.println("Booking Name: "+searchName +" not found ");
			nameSearchResults = (nameSearchResults+"Bookings with Name: "+searchName+" not found");
		}

	}
/* 	RUNS BUBBLE SORT BY NAME ON BOOKING DATA
	LOOPS THROUGH THE BOOKING ARRAY AND COMPARES THE NAME OF THAT LOCATION TO THE NAME OF THE NEXT LOCATION
	SWAPS THE NAMES TO MAKE THEM BE SORTED ALPHABETICALLY 
	 */
	
	public void bookingBubbleSortByName()
	{
		System.out.println("running bubble sort by name");
		for(int i=0; i<nextItemLocation; i++)
		{
			for(int j=0; j<(nextItemLocation-1); j++)
			{
				
				if((bookingsArray[j].name).compareTo(bookingsArray[j+1].name)>0)
				{
					Booking tempPosition = bookingsArray[j] ;
					bookingsArray[j] = bookingsArray[j+1];
					bookingsArray[j+1] = tempPosition;		
				}
				
				
				
			}
		}


	}
/* 	RUNS BUBBLE SORT BY DATES ON BOOKING DATA
	FOR EACH LOCATION OF THE BOOKING ARRAY IT CONVERTS THE DATE ATTRIBUTE IF THE RECORD INTO LOCALDATE UNDER A FORMATTER 
	USES THE 'isAfter' METHOD TO SORT THE DATES IN ASCENDING ORDER
	 */
	public void bookingBubbleSortByDateLatest()
	{

		System.out.println("Running Bubble sort by latest date");
		for(int i=0; i<nextItemLocation;i++ )
		{
			for(int j=0; j<nextItemLocation-1; j++)
			{
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
				
				
				LocalDate tempDate1 = LocalDate.parse(bookingsArray[j].date, formatter);
				 
				LocalDate tempDate2 = LocalDate.parse(bookingsArray[j+1].date, formatter);
				boolean swap = tempDate2.isAfter(tempDate1);
				
				if(swap==true)
				{
					Booking tempPosition2 = bookingsArray[j] ;
					bookingsArray[j] = bookingsArray[j+1];
					bookingsArray[j+1] = tempPosition2;
				} 
			}
		}




	
	
		
	}
/* 	RUNS BUBBLE SORT BY DATES ON BOOKING DATA
	FOR EACH LOCATION OF THE BOOKING ARRAY IT CONVERTS THE DATE ATTRIBUTE IF THE RECORD INTO LOCALDATE UNDER A FORMATTER 
	USES THE 'isBefore' METHOD TO SORT THE DATES IN DESCENDING ORDER
	 */
	public void bookingBubbleSortByDateOldest()
	{

		System.out.println("Running Bubble sort by oldest dates");
		for(int i=0; i<nextItemLocation;i++ )
		{
			for(int j=0; j<nextItemLocation-1; j++)
			{
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
				
				
				LocalDate tempDate3 = LocalDate.parse(bookingsArray[j].date, formatter);
				 
				LocalDate tempDate4 = LocalDate.parse(bookingsArray[j+1].date, formatter);
				boolean swap = tempDate4.isBefore(tempDate3);
				
				if(swap==true)
				{
					Booking tempPosition3 = bookingsArray[j] ;
					bookingsArray[j] = bookingsArray[j+1];
					bookingsArray[j+1] = tempPosition3;
				} 
			}
		}

		
	
		
	}
}