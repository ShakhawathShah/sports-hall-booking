public class CustomerInformation
{
	//DECLARES VARIABLES 
	String phoneNum;
	String email;
	String cName;

	



	public static void main(String[]  args)
	{
		CustomerInformation ci = new CustomerInformation();
	}
	//USES TO STRING METHOD TO COMBINE THE VARIABLES
	public String toString()
	{
		String CustomerInfo = (cName+","+phoneNum+","+email);
		return CustomerInfo;
	}


	
}