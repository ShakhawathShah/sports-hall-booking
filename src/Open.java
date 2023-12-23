public class Open
{
//DECLARES VARIABLES
	String openingDay;
	String openingTime;
//COMBINES VARIABLES INTO A STRING 
	public String toString()
	{
		String OpeningInfo = (openingDay+","+openingTime);
		return OpeningInfo;
	}
}