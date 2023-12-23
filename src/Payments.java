public class Payments
{
//DECLARES LIST OF VARIABLES
	String cardNumber;
	String cardHolder;
	int price;
	int bID;

//COMBINES VARIABLES INTO A STRING 
	public String toString()
	{
		String PaymentsInfo = (bID+","+cardNumber+","+cardHolder+","+price);
		return PaymentsInfo;
	}


	
}