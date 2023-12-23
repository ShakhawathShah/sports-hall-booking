public class Booking
{
	//DECLARES LIST OF VARIABLES
	int bookingID;
	String name;
	String room;
	String time;
	String date;
	String activity;
	
//COMBINES VARIABLES INTO A STRING 
	public String toString()
	{
		String BookingInfo = (bookingID+","+date+","+time+","+room+","+name+","+activity);
		return BookingInfo;
	}


	
}