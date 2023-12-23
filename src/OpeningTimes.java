import java.io.*;
import java.util.*;
public class OpeningTimes
{	
	//CREATES ARRAY OF OPEN CLASS
	Open[] openingDataArray = new Open[100];
	int nextItemLocation = 0;
	
	//FILE TO BE USED
	String itemFileName2 = "OpeningTimes.txt";

	//READ FROM OPENING DATA FILE
	public void readFromOpeningFile()
	{
		System.out.println("Running reading from opening file");
		nextItemLocation = 0;

		
		try
		{//CREATES A FILE READER
			FileReader fr = new FileReader(itemFileName2); 
			BufferedReader br = new BufferedReader(fr);
			
			
			
			String listLine = br.readLine();
			
			while(listLine != null )
			{	//SPLITS EACH RECORD AND STORED INTO TEMP ARRAY
				String[] openingData = listLine.split(",");
				Open tempOpening = new Open();
				//EACH INDEX OF THE TEMP ARRAY IS STORED INTO AN OBJECT OF OPEN
				tempOpening.openingDay = openingData[0];
				tempOpening.openingTime = openingData[1];
				//USES THE OBJECT AS A PAREMETER
				addToOpeningList(tempOpening);
				
				
				listLine = br.readLine();
			}


			
		}
		catch(Exception e)
		{
			System.out.println("Error reading from file: " + e);
		}
			
	}
	//ADDS RECORD DATA TO THE ARRAY USING A PARAMETER
	public void addToOpeningList(Open passedItem)
	{
		System.out.println("Running addToList");
		
		openingDataArray[nextItemLocation] = passedItem;
		nextItemLocation++;
		System.out.println(passedItem);
	}

	
 	public void showOpeningList()
	{
		for(int i = 0; i < nextItemLocation; i++)
		{
			String currentData = openingDataArray[i].toString();
			System.out.println("Current item info is: " + currentData);
		}
	}
	  
	
	
	

}