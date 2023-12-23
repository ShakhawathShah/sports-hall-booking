import java.io.*;
import java.util.*;
public class LoginList
{
	//CREATES ARRAY OF LOGIN CLASS
	Login[] loginDataArray = new Login[100];
	int nextItemLocation = 0;
	//FILE TO BE USED
	String itemFileName2 = "StaffLoginInfo.txt";

	//READ FROM LOGIN FILE
	public void readFromLoginFile()
	{
		System.out.println("Running reading from Staff Login File ");
		nextItemLocation = 0;
		try
		{//CREATES A FILE READER
			FileReader fr = new FileReader(itemFileName2); 
			BufferedReader br = new BufferedReader(fr);
			String listLine = br.readLine();
			
			while(listLine != null )
			{//SPLITS EACH RECORD AND STORED INTO TEMP ARRAY
				String[] loginData = listLine.split(",");
				Login blogin = new Login();
				//EACH INDEX OF THE TEMP ARRAY IS STORED INTO AN OBJECT OF LOGIN
				blogin.userName = loginData[0];
				blogin.password = loginData[1];
				//USES THE OBJECT AS A PAREMETER
				addToLoginList(blogin);
				
				listLine = br.readLine();
			}

			
		}
		catch(Exception e)
		{
			System.out.println("Error reading from file: " + e);
		}

	}
	
	//ADDS RECORD DATA TO THE ARRAY USING A PARAMETER THAT GETS PASSED IN
	public void addToLoginList(Login passedItem)
	{
		System.out.println("Running add To Staff Login List");
		loginDataArray[nextItemLocation] = passedItem;
		nextItemLocation++;
		System.out.println(passedItem);
	}
	
	

}