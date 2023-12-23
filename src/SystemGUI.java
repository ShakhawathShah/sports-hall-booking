//IMPORTS VARIOUS CLASS LIBRARIES 
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.border.Border;


public class SystemGUI extends JFrame implements ActionListener 
{
//VARAIBLES THAT ARE DECLARED GLOABALLY AS THEY ARE USED IN MULTIPLE METHODS.
	String cbxTimeVal; 
	int tempPriceVal;
	int idSearchVal;
	String bDay;
	String bMonth;
	int tPrice ;
// LIST OF CONSTANTS FOR THE PRICES OF BOOKING ACTIVITIES
	final int FOOTBALLPRICE = 25;
	final int BASKETBALLPRICE = 20;
	final int BADMINTONPRICE = 15;
	final int TABLETENNISPRICE = 10;
	final int TENNISPRICE = 15;
//CREATES ICONS USING THE IMAGE FILES SHOWN TO BE PASSED INTO LABELS 
	ImageIcon imgBoltonOneLogo =(new ImageIcon("BoltonOneLogo.png"));
	ImageIcon imgUoBLogo = (new ImageIcon("UoBLogo.png"));
	


//FONTS FOR MULTIPLE USES BUTTONS,LABELS ETC
	Font mainTitleFont=new Font("Courier", Font.BOLD,40);
	Font pageTitleFont=new Font("Courier", Font.BOLD,25);
	Font labelFont=new Font("Courier", Font.PLAIN,18);
	Font buttonFont=new Font("Courier", Font.BOLD,18);
	Font tableHeaderFont=new Font("Courier", Font.BOLD,25);
	Font tableRowsFont=new Font("Courier", Font.PLAIN,20);
//CREATES TEXT FOR A LABEL USING TAGS TO MAKE THE TEXT UNDERLINED
	String mainTitleText = ("<html><U>Sports Hall<BR>Booking System<U></html>");
	
//ALL OBJECTS ARE CALLED OF VARIOUS CLASS FILES
	Booking bk = new Booking();
	BookingSystem bs = new BookingSystem();
	LoginList logList = new LoginList();
	CustomerLoginList cLogList = new CustomerLoginList();
	OpeningTimes open = new OpeningTimes();
	Open op = new Open();
	PaymentsList pList = new PaymentsList();
	CustomerInformationList ciList = new CustomerInformationList();
	CustomerBookingsList cbList = new CustomerBookingsList();
	CustomerPaymentsList cpList = new CustomerPaymentsList();
	
	

	
	
	
	
//CREATES A TAPPED PANE USED TO HOLD ALL THE PANELS
	JTabbedPane allTabs = new JTabbedPane();
//DECLARES CONSTANTS USED TO DECIDE THE INDEX OF PANELS
	final int HOMEPAGE = 0;
	final int STAFFLOGINPAGE = 1;
	final int CUSTOMERLOGINPAGE = 2;
	final int STAFFMENUPAGE=3;
	final int CUSTOMERMENUPAGE=4;
	final int STAFFBOOKINGPAGE=5;
	final int CUSTOMERBOOKINGPAGE=6;
	final int STAFFADDBOOKINGPAGE=7;
	final int CUSTOMERADDBOOKINGPAGE=8;
	final int STAFFSEARCHBOOKINGPAGE=9;
	final int CUSTOMERSEARCHBOOKINGPAGE=10;
	final int STAFFPAYMENTSPAGE=11;
	final int CUSTOMERPAYMENTSPAGE=12;
	final int STAFFVIEWPAYMENTSPAGE=13;
	final int CUSTOMERVIEWPAYMENTSPAGE=14;

//CREATES HOME PANEL AND DECLARES ITS COMPONENTS
	JPanel homePanel = new JPanel(null);
	JButton btnStaffLogin = new JButton();
	JButton btnCustomerLogin = new JButton();
	JLabel lblOpeningTable = new JLabel();
	
	//OPENING TIMES TABLE - DECALRES TABLE MODEL, SCROLL AND ARRAY TO CREATE IT
	String[] headings2 = {"Days","Time"};
	String[][] openingData = new String[0][0];
	DefaultTableModel openingTableModel = new DefaultTableModel(openingData, headings2);
	JTable openingTable = new JTable(openingTableModel);
	JScrollPane openingTableScroll = new JScrollPane(openingTable);		
	JTableHeader openingTableHeader = openingTable.getTableHeader();
	
// STAFF LOGIN PANEL - DECLARES ITS COMPONENTS
	JPanel staffLoginPanel = new JPanel(null);
	JButton btnLogin = new JButton();
	JLabel lblPassword = new JLabel();
	JPasswordField tfPassword = new JPasswordField();
	JLabel lblUsername = new JLabel();
	JTextField tfUsername = new JTextField();
	JButton btnLogout = new JButton();
	JLabel lblStaffLoginPanel = new JLabel();

// CUSTOMER LOGIN PANEL - DECLARES ITS COMPONENTS
	JPanel customerLoginPanel = new JPanel(null);
	JButton btnConfirmCustomerLogin = new JButton();
	JPasswordField tfCustomerPassword = new JPasswordField();
	JTextField tfCustomerUsername = new JTextField();
	JButton btnLogout2 = new JButton();	
	JLabel lblPassword2 = new JLabel();
	JLabel lblUsername2 = new JLabel();
	JLabel lblCustomerLoginPanel = new JLabel();

// STAFF MENU PANEL - DECLARES ITS COMPONENTS
	JPanel staffMenuPanel = new JPanel(null); 
 	JLabel lblStaffMenuTitle = new JLabel();
	JButton btnBookings = new JButton();
	JButton btnAddBookings = new JButton();
	JButton btnSearchBookings = new JButton();
	JButton btnPayments = new JButton();
	
// CUSTOMER MENU PANEL - DECLARES ITS COMPONENTS
	JPanel customerMenuPanel = new JPanel(null); 
 	JLabel lblCustomerMenuTitle = new JLabel();
	JButton btnBookings2 = new JButton();
	JButton btnAddBookings2 = new JButton();
	JButton btnSearchBookings2 = new JButton();
	JButton btnPayments2 = new JButton();	

//STAFF VIEW BOOKINGS PANEL - DECLARES ITS COMPONENTS
	JPanel viewBookingsPanel = new JPanel(null);
	JButton btnMenu1 = new JButton();
	JButton btnDeleteBooking = new JButton();
	JButton btnDateFilter = new JButton();
	JLabel lblStaffViewBookings = new JLabel();
	//ALSO DECLARES ARRAY FOR COMBO BOX OPTIONS
	String[] bookingFiltersArray = new String[] {"By Name","By Latest Bookings","By Oldest Bookings"};
	JComboBox<String> cbxFilterBookings = new JComboBox<String> (bookingFiltersArray);
	
	//BOOKING TABLE - DECALRES TABLE MODEL AND ARRAY TO CREATE IT, ALONG WITH OTHER COMPONENTS NECESSARY TO CREATE THE TABLE 
	String[] headings = {"ID","Date","Time","Room","Name","Activity"};
	String[][] tableData = new String[0][0];
	DefaultTableModel bookingTableModel = new DefaultTableModel(tableData, headings);
	JTable bookingTable = new JTable(bookingTableModel);
	JScrollPane bookingTableScroll = new JScrollPane(bookingTable);	
	JTableHeader bookingTableHeader = bookingTable.getTableHeader();//GETS THE TABLES HEADER AND COLUMN SIZES
	TableColumnModel colModel=bookingTable.getColumnModel();
	
	

//CUSTOMER VIEW BOOKINGS PANEL - DECLARES ITS COMPONENTS
	JPanel customerViewBookingsPanel = new JPanel(null);
	JButton btnMenu2 = new JButton();
	JButton btnCustomerDeleteBooking = new JButton();
	JButton btnCustomerDateFilter = new JButton();
	JLabel lblCustomerViewBookings = new JLabel();
	String[] customerBookingFiltersArray = new String[] {"By Latest Bookings","By Oldest Bookings"};
	JComboBox<String> cbxCustomerFilterBookings = new JComboBox<String> (customerBookingFiltersArray);
	
	//CUSTOMER BOOKINGS TABLE COMPONENTS DECLARED 	
	String[][] customerTableData = new String[0][0];
	DefaultTableModel customerBookingTableModel = new DefaultTableModel(customerTableData, headings);
	JTable customerBookingTable = new JTable(customerBookingTableModel);
	JScrollPane customerBookingTableScroll = new JScrollPane(customerBookingTable);
	//GETS SIZE OF HEADER
	JTableHeader customerBookingTableHeader= customerBookingTable.getTableHeader();
	TableColumnModel customerColModel=customerBookingTable.getColumnModel();

	
	
	
//STAFF ADD BOOKINGS PANEL - BUTTONS, TEXT FIELDS,LABELS DECLARED
	JPanel addBookingsPanel = new JPanel(null); //layout
 	JButton btnConfirm = new JButton();
	JTextField tfCustomerFirstName = new JTextField();
	JLabel lblCustomerFirstName = new JLabel();
	JTextField tfCustomerName = new JTextField();
	JLabel lblCustomerName = new JLabel();
	JTextField tfCustomerMobile = new JTextField();
	JLabel lblCustomerMobile= new JLabel();
	JTextField tfCustomerEmail = new JTextField();
	JLabel lblCustomerEmail = new JLabel();
	
	
	//USES LOCALDATE CLASS TO GET CURRENT DATE OF ALL THE DAYS IN THE WEEK AND FORMATS DATES TO DD/MM/YY FORMAT
	LocalDate todaysDate = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
	String formattedTodaysDate = todaysDate.format(formatter);
	LocalDate weekday1 = todaysDate.plusDays(1);
	String formattedWeekday1 = weekday1.format(formatter);	
	LocalDate weekday2 = todaysDate.plusDays(2);
	String formattedWeekday2 = weekday2.format(formatter);	
	LocalDate weekday3 = todaysDate.plusDays(3);
	String formattedWeekday3 = weekday3.format(formatter);	
	LocalDate weekday4 = todaysDate.plusDays(4);
	String formattedWeekday4 = weekday4.format(formatter);	
	LocalDate weekday5 = todaysDate.plusDays(5);
	String formattedWeekday5 = weekday5.format(formatter);	
	LocalDate weekday6 = todaysDate.plusDays(6);
	String formattedWeekday6 = weekday6.format(formatter);	
	LocalDate weekday7 = todaysDate.plusDays(7);
	String formattedWeekday7 = weekday7.format(formatter);
	//CREATES A COMBO BOX, WHICH REQUIRES AN ARRAY TO DISPLAY DATA, FORMATTED DATES ARE THE ARRAY OPTIONS	
	String[] bookingDateArray = new String[] {"Please Select  ",formattedWeekday1, formattedWeekday2,formattedWeekday3,formattedWeekday4 ,formattedWeekday5  ,formattedWeekday6 ,formattedWeekday7};
	JComboBox<String> cbxBookingDate = new JComboBox<String> (bookingDateArray);
	JLabel lblBookingDate = new JLabel();
	String[] bookingTimeArray = new String[] {"Please Select  ", "07:00 - 08:00","08:00 - 09:00", "09:00 - 10:00", "10:00 - 11:00","11:00 - 12:00" , "12:00 - 13:00" ,"13:00 - 14:00" , "14:00 - 15:00" , "15:00 - 16:00" , "16:00 - 17:00", "17:00 - 18:00", "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00" };
	JComboBox<String> cbxBookingTime = new JComboBox<String>(bookingTimeArray);
	JLabel lblBookingTime = new JLabel();
	//OTHER COMBO BOXES DECLARED
	String[] roomsArray = new String[] {"Please Select  ","Room 1", "Room 2","Room 3","Room 4","Room 5"};
	JComboBox<String> cbxRoom = new JComboBox<String> (roomsArray);	
	JLabel lblRoom = new JLabel();
	String[] activityArray = new String[] {"Please Select  ","Football", "Basketball", "Badminton", "Table Tennis", "Tennis"};
	JComboBox<String> cbxActivity = new JComboBox<String> (activityArray);		
	JLabel lblActivity = new JLabel();
	JButton btnMenu3 = new JButton();
	JLabel lblAddBookings = new JLabel();
	

	
//CUSTOMER ADD BOOKINGS PANEL - BUTTONS, TEXT FIELDS, LABELS DECLARED
	JPanel customerAddBookingsPanel = new JPanel(null);
 	JButton btnConfirm2 = new JButton();
	JTextField tfCustomerFirstName2 = new JTextField();
	JLabel lblCustomerFirstName2 = new JLabel();
	JTextField tfCustomerName2 = new JTextField();
	JLabel lblCustomerName2 = new JLabel();
	JTextField tfCustomerMobile2 = new JTextField();
	JLabel lblCustomerMobile2= new JLabel();
	JTextField tfCustomerEmail2 = new JTextField();
	JLabel lblCustomerEmail2 = new JLabel();
	//USES SAME FORMATTED DATES FOR ARRAY
	String[] bookingDateArray2 = new String[] {"Please Select  ",formattedWeekday1, formattedWeekday2,formattedWeekday3,formattedWeekday4 ,formattedWeekday5  ,formattedWeekday6 ,formattedWeekday7};
	JComboBox<String> cbxBookingDate2 = new JComboBox<String> (bookingDateArray2);
	JLabel lblBookingDate2 = new JLabel();
	//OTHER COMBO BOXES DECLARED
	String[] bookingTimeArray2 = new String[] {"Please Select  ", "07:00 - 08:00","08:00 - 09:00", "09:00 - 10:00", "10:00 - 11:00","11:00 - 12:00" , "12:00 - 13:00" ,"13:00 - 14:00" , "14:00 - 15:00" , "15:00 - 16:00" , "16:00 - 17:00", "17:00 - 18:00", "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00" };
	JComboBox<String> cbxBookingTime2 = new JComboBox<String> (bookingTimeArray2);
	JLabel lblBookingTime2 = new JLabel();
	String[] roomsArray2 = new String[] {"Please Select  ","Room 1", "Room 2","Room 3","Room 4","Room 5"};
	JComboBox<String> cbxRoom2 = new JComboBox<String> (roomsArray2);	
	JLabel lblRoom2 = new JLabel();
	String[] activityArray2 = new String[] {"Please Select  ","Football", "Basketball", "Badminton", "Table Tennis", "Tennis"};
	JComboBox<String> cbxActivity2 = new JComboBox<String> (activityArray2);		
	JLabel lblActivity2 = new JLabel();
	JButton btnMenu4 = new JButton();
	JLabel lblAddBookings2 = new JLabel();

	
//LIST OF THE LOGOS AND TITLES TO BE DISPLAYED ON ALL SCREENS
	JLabel lblBoltonOneLogo = new JLabel();
	JLabel lblUoBLogo = new JLabel();
	JLabel lblMainTitle = new JLabel();

	JLabel lblBoltonOneLogo1 = new JLabel();
	JLabel lblUoBLogo1 = new JLabel();
	JLabel lblMainTitle1 = new JLabel();
	
	JLabel lblStaffLoginBoltonOneLogo = new JLabel();
	JLabel lblStaffLoginUoBLogo = new JLabel();
	JLabel lblStaffLoginMainTitle = new JLabel();

	JLabel lblCustomerLoginBoltonOneLogo = new JLabel();
	JLabel lblCustomerloginUoBLogo = new JLabel();
	JLabel lblCustomerLoginMainTitle = new JLabel();
	
	JLabel lblStaffMenuBoltonOneLogo = new JLabel();
	JLabel lblStaffMenuUoBLogo = new JLabel();
	JLabel lblStaffMenuMainTitle = new JLabel();
	
	JLabel lblCustomerMenuBoltonOneLogo = new JLabel();
	JLabel lblCustomerMenuUoBLogo = new JLabel();
	JLabel lblCustomerMenuMainTitle = new JLabel();
	
	JLabel lblStaffViewBookingsBOL = new JLabel();
	JLabel lblStaffViewBookingsUoBL = new JLabel();
	JLabel lblStaffViewBookingsMainTitle = new JLabel();
	
	JLabel lblCustomerViewBookingsBOL = new JLabel();
	JLabel lblCustomerViewBookingsUoBL = new JLabel();
	JLabel lblCustomerViewBookingsMainTitle = new JLabel();
	
	JLabel lblStaffAddBookingsBOL = new JLabel();
	JLabel lblStaffAddBookingsUoBL = new JLabel();
	JLabel lblStaffAddBookingsMainTitle = new JLabel();
	
	JLabel lblCustomerAddBookingsBOL = new JLabel();
	JLabel lblCustomerAddBookingsUoBL = new JLabel();
	JLabel lblCustomerAddBookingsMainTitle = new JLabel();
	
	JLabel lblStaffSearchPanelBOL = new JLabel();
	JLabel lblStaffSearchPanelUoBL = new JLabel();
	JLabel lblStaffSearchPanelMainTitle = new JLabel();

	JLabel lblCustomerSearchPanelBOL = new JLabel();
	JLabel lblCustomerSearchPanelUoBL = new JLabel();
	JLabel lblCustomerSearchPanelMainTitle = new JLabel();
	
	JLabel lblStaffPaymentsPanelBOL = new JLabel();
	JLabel lblStaffPaymentsPanelUoBL = new JLabel();
	JLabel lblStaffPaymentsPanelMainTitle = new JLabel();

	JLabel lblCustomerPaymentsPanelBOL = new JLabel();
	JLabel lblCustomerPaymentsPanelUoBL = new JLabel();
	JLabel lblCustomerPaymentsPanelMainTitle = new JLabel();	

	JLabel lblStaffViewPaymentsBOL = new JLabel();
	JLabel lblStaffViewPaymentsUoBL = new JLabel();
	JLabel lblStaffViewPaymentsMainTitle = new JLabel();	

	JLabel lblCustomerViewPaymentsBOL = new JLabel();
	JLabel lblCustomerViewPaymentsUoBL = new JLabel();
	JLabel lblCustomerViewPaymentsMainTitle = new JLabel();	

	


// STAFF SEARCH BOOKINGS PANEL - COMPONENTS DECLARED
	
	JPanel searchPanel = new JPanel(null);
	JButton btnMenu5 = new JButton();
	JButton btnSearchByID = new JButton();
	JLabel lblSearchByID = new JLabel();
	JTextField tfSearchByID = new JTextField();
	JButton btnSearchByDate = new JButton();
	JLabel lblSearchByDate = new JLabel();
	JTextField tfSearchByDate = new JTextField();
	JButton btnSearchByName = new JButton();
	JLabel lblSearchByName = new JLabel();
	JTextField tfSearchByName = new JTextField();
	JLabel lblStaffSearchBookings = new JLabel();
	
	JTextArea jtaSearchResults =new  JTextArea(5,20);//TEXT AREA FOR SEARCH RESULTS
	Border border = BorderFactory.createLineBorder(Color.BLACK);//USES BORDER CLASS LIBRARY TO CREATE A BORDER FOR TEXT AREA
	JScrollPane searchScroll = new JScrollPane(jtaSearchResults);
	
// CUSTOMER SEARCH BOOKINGS PANEL - COMPONENTS DECLARED
	JPanel customerSearchPanel = new JPanel(null);
	JButton btnMenu6 = new JButton();
	JButton btnSearchByID2 = new JButton();
	JLabel lblSearchByID2 = new JLabel();
	JTextField tfSearchByID2 = new JTextField();
	JButton btnSearchByDate2 = new JButton();
	JLabel lblSearchByDate2 = new JLabel();
	JTextField tfSearchByDate2 = new JTextField();
	JButton btnSearchByName2 = new JButton();
	JLabel lblSearchByName2 = new JLabel();
	JTextField tfSearchByName2 = new JTextField();
	JLabel lblCustomerSearchBookings = new JLabel();
	
	JTextArea jtaCustomerSearchResults =new  JTextArea(5,20);
	JScrollPane customerSearchScroll = new JScrollPane(jtaCustomerSearchResults);
	
	

//STAFF MAKE PAYMENTS PANEL - COMPONENTS DECLARED	
	JPanel paymentsPanel = new JPanel(null);
	JButton btnCancelBooking= new JButton();
	JTextField tfCardNumber = new JTextField();
	JLabel lblCardNumber = new JLabel();
	JTextField tfCardHolder = new JTextField();
	JLabel lblCardHolder = new JLabel();
	JButton btnPay = new JButton();	
	JLabel lblMakePayments = new JLabel();
	JLabel lblTotalPayment = new JLabel();

//CUSTOMER MAKE PAYMENTS PANEL - COMPONENTS DECLARED
	JPanel customerPaymentsPanel = new JPanel(null);
	JButton btnCancelBooking2 = new JButton();
	JTextField tfCardNumber2 = new JTextField();
	JLabel lblCardNumber2 = new JLabel();
	JTextField tfCardHolder2 = new JTextField();
	JLabel lblCardHolder2 = new JLabel();
	JButton btnPay2 = new JButton();	
	JLabel lblMakePayments2 = new JLabel();
	JLabel lblTotalPayment2 = new JLabel();
//STAFF VIEW PAYMENTS PANEL - COMPONENTS DECLARED, AS WELL AS TABLE COMPONENTS
	JPanel viewPaymentsPanel = new JPanel(null);
	JButton btnMenu9 = new JButton();
	JLabel lblStaffViewPayments = new JLabel();
	String[] paymentHeadings = {"Booking ID","Card Holder ","Card Number ","Price"};
	String[][] paymentTableData = new String[0][0];
	DefaultTableModel paymentTableModel = new DefaultTableModel(paymentTableData, paymentHeadings);
	JTable paymentTable = new JTable(paymentTableModel);
	JScrollPane paymentTableScroll = new JScrollPane(paymentTable);	
	JTableHeader paymentTableHeader = paymentTable.getTableHeader();
	TableColumnModel paymentsColModel=paymentTable.getColumnModel();
	
//CUSTOMER VIEW PAYMENTS PANEL - COMPONENTS DECLARED, AS WELL AS TABLE COMPONENTS	
	JPanel customerViewPaymentsPanel = new JPanel(null);
	JButton btnMenu10 = new JButton();
	JLabel lblCustomerViewPayments = new JLabel();

	String[] customerPaymentHeadings = {"Booking ID","Card Holder ","Card Number ","Price"};
	String[][] customerPaymentTableData = new String[0][0];
	DefaultTableModel customerPaymentTableModel = new DefaultTableModel(customerPaymentTableData, customerPaymentHeadings);
	JTable customerPaymentTable = new JTable(customerPaymentTableModel);
	JScrollPane customerPaymentTableScroll = new JScrollPane(customerPaymentTable);	
	JTableHeader customerPaymentTableHeader = customerPaymentTable.getTableHeader();
	TableColumnModel customerPaymentsColModel=customerPaymentTable.getColumnModel();
		
//[EndVariables]

//CREATES THE GUI
public void startGUI()
{
	//CALLS READ FILE METHODS FROM OTHER CLASSES
	bs.readFromBookingFile();
	pList.readFromPaymentsFile();
	logList.readFromLoginFile();
	cLogList.readFromCustomerLoginFile();
	ciList.readFromCustomerInfoFile();
	
	buildTabPane();
	//ADD ALL THE PANELS TO THE TABBED PANE AND NAMES THEM
	allTabs.addTab("Home",homePanel);
	allTabs.addTab("Staff Login",staffLoginPanel);
	allTabs.addTab("Customer Login",customerLoginPanel);
	allTabs.addTab("Staff Menu",staffMenuPanel);
	allTabs.addTab("Customer Menu",customerMenuPanel);
	allTabs.addTab("Staff View Bookings",viewBookingsPanel);
	allTabs.addTab("Customer View Bookings",customerViewBookingsPanel);
	allTabs.addTab("Staff Add Bookings",addBookingsPanel);
	allTabs.addTab("Customer Add Bookings",customerAddBookingsPanel);
	allTabs.addTab("Staff Search Bookings",searchPanel);
	allTabs.addTab("Customer Search Bookings",customerSearchPanel);
	allTabs.addTab("Staff Make Payments",paymentsPanel);
	allTabs.addTab("Customer Make Payments",customerPaymentsPanel);
	allTabs.addTab("Staff View Payments",viewPaymentsPanel);
	allTabs.addTab("Customer View Payments",customerViewPaymentsPanel);

	allTabs.setEnabledAt(0, false);
	allTabs.setEnabledAt(1, false);
	allTabs.setEnabledAt(2, false);
	allTabs.setEnabledAt(3, false);
	allTabs.setEnabledAt(4, false);
	allTabs.setEnabledAt(5, false);
	allTabs.setEnabledAt(6, false);
	allTabs.setEnabledAt(7, false);
	allTabs.setEnabledAt(8, false);
	allTabs.setEnabledAt(9, false);
	allTabs.setEnabledAt(10, false);
	allTabs.setEnabledAt(11, false);
	allTabs.setEnabledAt(12, false);
	allTabs.setEnabledAt(13, false);
	allTabs.setEnabledAt(14, false);

//DECLARES LAYOUT OPTIONS
	this.setLayout(new GridLayout(1,1));
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

	this.add(allTabs);
	this.setTitle("Booking System");
	this.setSize(900,700);
	this.setForeground( new Color(255255255) );
	this.setBackground( new Color(255255255) );
	this.setVisible(true);
	this.setResizable(true);
}
//CREATES THE TABBED PANE WHICH WILL CREATE ALL PANELS
public void buildTabPane()
{
	createHomePanel();
	createStaffLoginPanel();
	createCustomerLoginPanel();	
	createStaffMenuPanel();
	createCustomerMenuPanel();
	createAddBookingsPanel();
	createCustomerAddBookingsPanel();
	createViewBookingPanel();
	createCustomerViewBookingPanel();
	createSearchPanel();
	createCustomerSearchPanel();
	createPaymentsPanel();
	createCustomerPaymentsPanel();
	createViewPaymentsPanel();
	createCustomerViewPaymentsPanel();
}
/* CREATES THE HOME PANEL 
DECLARES ALL THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createHomePanel()
{
	homePanel.setBackground(Color.WHITE);//SETS BACKGORUND TO WHITE
	
	lblBoltonOneLogo.setSize(240,117);
	lblBoltonOneLogo.setLocation(0,0);
	lblBoltonOneLogo.setIcon(imgBoltonOneLogo);//PASSES AN IMAGE DECLARED EARLIER INTO THE LABEL
	homePanel.add(lblBoltonOneLogo); 
	
	lblUoBLogo.setSize(250,117);
	lblUoBLogo.setLocation(625,0);
	lblUoBLogo.setIcon(imgUoBLogo);
	homePanel.add(lblUoBLogo); 	

	lblMainTitle.setLocation(250,0);
	lblMainTitle.setSize(350,100);
	lblMainTitle.setOpaque(false);
    lblMainTitle.setFont(mainTitleFont);
	lblMainTitle.setText(mainTitleText);
	homePanel.add(lblMainTitle);
	
	lblOpeningTable.setLocation(175,235);
	lblOpeningTable.setSize(250,30);
	lblOpeningTable.setOpaque(false);
    lblOpeningTable.setFont(tableHeaderFont);
	lblOpeningTable.setText("Opening Times:");
	homePanel.add(lblOpeningTable);
	
	btnStaffLogin.setLocation(200,150);
	btnStaffLogin.setSize(200,50);
	btnStaffLogin.addActionListener(this);
	btnStaffLogin.setText("Staff Login");
	btnStaffLogin.setFont(buttonFont);
	homePanel.add(btnStaffLogin);	
	
	btnCustomerLogin.setLocation(450,150);
	btnCustomerLogin.setSize(200,50);
	btnCustomerLogin.addActionListener(this);
	btnCustomerLogin.setText("Customer Login");
	btnCustomerLogin.setFont(buttonFont);
	homePanel.add(btnCustomerLogin);	
	
	openingTableScroll.setLocation(175,275);//SETS THE SIZE OF THE TABLE
	openingTableScroll.setSize(500,258);
	homePanel.add(openingTableScroll);
	
	openingTableHeader.setFont(tableHeaderFont);//APPLYS FONT AND CHANGES ROW SIZE
	openingTable.setFont(tableRowsFont);
	openingTable.setRowHeight(openingTable.getRowHeight()+15);
	
//CALLS READ FILE METHOD TO POPULATE TABLE WITH THE DATA
	open.readFromOpeningFile();
	//open.showOpeningList();
	clearOpeningTable();
	populateOpeningTable();
	
}



/* CREATES THE STAFF LOGIN PANEL 
DECLARES THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createStaffLoginPanel()
{
	staffLoginPanel.setBackground(Color.WHITE);
	
	lblStaffLoginBoltonOneLogo.setSize(240,117);
	lblStaffLoginBoltonOneLogo.setLocation(0,0);
	lblStaffLoginBoltonOneLogo.setIcon(imgBoltonOneLogo);
	staffLoginPanel.add(lblStaffLoginBoltonOneLogo); 
	
	lblStaffLoginUoBLogo.setSize(250,117);
	lblStaffLoginUoBLogo.setLocation(625,0);
	lblStaffLoginUoBLogo.setIcon(imgUoBLogo);
	staffLoginPanel.add(lblStaffLoginUoBLogo); 
	
	lblStaffLoginMainTitle.setLocation(250,0); 
	lblStaffLoginMainTitle.setSize(350,100);
	lblStaffLoginMainTitle.setOpaque(false);
    lblStaffLoginMainTitle.setFont(mainTitleFont);
	lblStaffLoginMainTitle.setText(mainTitleText);
	staffLoginPanel.add(lblStaffLoginMainTitle);
	
	lblStaffLoginPanel.setLocation(350,125); 
	lblStaffLoginPanel.setSize(200,50);
	lblStaffLoginPanel.setOpaque(false);
    lblStaffLoginPanel.setFont(pageTitleFont);
	lblStaffLoginPanel.setText("Staff Login");
	staffLoginPanel.add(lblStaffLoginPanel);

	
	btnLogin.setLocation(400,350);
	btnLogin.setSize(150,50);
	btnLogin.addActionListener(this);
	btnLogin.setText("Login");
	btnLogin.setFont(buttonFont);
	staffLoginPanel.add(btnLogin);

	tfUsername.setLocation(400,200);
	tfUsername.setSize(175,50);
	tfUsername.setText("");
	tfUsername.setColumns(10);
	tfUsername.setFont(labelFont);
	staffLoginPanel.add(tfUsername);
	
	lblUsername.setLocation(300,200);
	lblUsername.setSize(100,50);
	lblUsername.setOpaque(false);
	lblUsername.setFont(labelFont);
	lblUsername.setText("Username");
	staffLoginPanel.add(lblUsername);
	
	tfPassword.setLocation(400,275);
	tfPassword.setSize(175,50);
	tfPassword.setText("");
	tfPassword.setColumns(10);
	tfPassword.setEchoChar('*');//ENSURES INPUT WILL BE HIDDEN WITH '*'
	tfPassword.setFont(labelFont);
	staffLoginPanel.add(tfPassword);
	
	lblPassword.setLocation(300,275);
	lblPassword.setSize(100,50);
	lblPassword.setOpaque(false);
	lblPassword.setFont(labelFont);
	lblPassword.setText("Password");
	staffLoginPanel.add(lblPassword);



		

}
/* CREATES THE CUSTOMER LOGIN PANEL 
DECLARES ALL THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createCustomerLoginPanel()
{
	customerLoginPanel.setBackground(Color.WHITE);
	
	lblCustomerLoginBoltonOneLogo.setSize(240,117);
	lblCustomerLoginBoltonOneLogo.setLocation(0,0);
	lblCustomerLoginBoltonOneLogo.setIcon(imgBoltonOneLogo);
	customerLoginPanel.add(lblCustomerLoginBoltonOneLogo); 
	
	lblCustomerloginUoBLogo.setSize(250,117);
	lblCustomerloginUoBLogo.setLocation(625,0);
	lblCustomerloginUoBLogo.setIcon(imgUoBLogo);
	customerLoginPanel.add(lblCustomerloginUoBLogo); 	

	lblCustomerLoginMainTitle.setLocation(250,0);
	lblCustomerLoginMainTitle.setSize(350,100);
	lblCustomerLoginMainTitle.setOpaque(false);
    lblCustomerLoginMainTitle.setFont(mainTitleFont);
	lblCustomerLoginMainTitle.setText(mainTitleText);
	customerLoginPanel.add(lblCustomerLoginMainTitle);

	lblCustomerLoginPanel.setLocation(300,125); 
	lblCustomerLoginPanel.setSize(250,50);
	lblCustomerLoginPanel.setOpaque(false);
    lblCustomerLoginPanel.setFont(pageTitleFont);
	lblCustomerLoginPanel.setText("Customer Login");
	customerLoginPanel.add(lblCustomerLoginPanel);
	
	btnConfirmCustomerLogin.setLocation(400,350);
	btnConfirmCustomerLogin.setSize(150,50);
	btnConfirmCustomerLogin.addActionListener(this);
	btnConfirmCustomerLogin.setText("Login");
	btnConfirmCustomerLogin.setFont(buttonFont);
	customerLoginPanel.add(btnConfirmCustomerLogin);

	tfCustomerUsername.setLocation(400,200);
	tfCustomerUsername.setSize(175,50);
	tfCustomerUsername.setText("");
	tfCustomerUsername.setColumns(10);
	tfCustomerUsername.setFont(labelFont);
	customerLoginPanel.add(tfCustomerUsername);
	
	lblUsername2.setLocation(300,200);
	lblUsername2.setSize(100,50);
	lblUsername2.setOpaque(false);
	lblUsername2.setFont(labelFont);
	lblUsername2.setText("Username");
	customerLoginPanel.add(lblUsername2);
	
	tfCustomerPassword.setLocation(400,275);
	tfCustomerPassword.setSize(175,50);
	tfCustomerPassword.setText("");
	tfCustomerPassword.setColumns(10);
	tfCustomerPassword.setEchoChar('*');
	tfCustomerPassword.setFont(labelFont);
	customerLoginPanel.add(tfCustomerPassword);
	
	lblPassword2.setLocation(300,275);
	lblPassword2.setSize(100,50);
	lblPassword2.setOpaque(false);
	lblPassword2.setFont(labelFont);
	lblPassword2.setText("Password");
	customerLoginPanel.add(lblPassword2);

	

}



/* CREATES THE STAFF MENU PANEL 
DECLARES ALL THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createStaffMenuPanel()
{
	staffMenuPanel.setBackground(Color.WHITE);

	lblStaffMenuBoltonOneLogo.setSize(240,117);
	lblStaffMenuBoltonOneLogo.setLocation(0,0);
	lblStaffMenuBoltonOneLogo.setIcon(imgBoltonOneLogo);
	staffMenuPanel.add(lblStaffMenuBoltonOneLogo); 
	
	lblStaffMenuUoBLogo.setSize(250,117);
	lblStaffMenuUoBLogo.setLocation(625,0);
	lblStaffMenuUoBLogo.setIcon(imgUoBLogo);
	staffMenuPanel.add(lblStaffMenuUoBLogo); 	

	lblStaffMenuMainTitle.setLocation(250,0);
	lblStaffMenuMainTitle.setSize(350,100);
	lblStaffMenuMainTitle.setOpaque(false);
    lblStaffMenuMainTitle.setFont(mainTitleFont);
	lblStaffMenuMainTitle.setText(mainTitleText);
	staffMenuPanel.add(lblStaffMenuMainTitle);
	
	lblStaffMenuTitle.setLocation(275,100);
	lblStaffMenuTitle.setSize(350,50);
	lblStaffMenuTitle.setOpaque(false);
	lblStaffMenuTitle.setFont(pageTitleFont);
	lblStaffMenuTitle.setText("Welcome to Bolton One");
	staffMenuPanel.add(lblStaffMenuTitle);

	btnBookings.setLocation(300,175);
	btnBookings.setSize(250,50);
	btnBookings.addActionListener(this);
	btnBookings.setText("View Bookings");
	btnBookings.setFont(buttonFont);
	staffMenuPanel.add(btnBookings);

	btnAddBookings.setLocation(300,250);
	btnAddBookings.setSize(250,50);
	btnAddBookings.addActionListener(this);
	btnAddBookings.setText("Add Bookings");
	btnAddBookings.setFont(buttonFont);
	staffMenuPanel.add(btnAddBookings);

	btnSearchBookings.setLocation(300,325);
	btnSearchBookings.setSize(250,50);
	btnSearchBookings.addActionListener(this);
	btnSearchBookings.setText("Search Bookings");
	btnSearchBookings.setFont(buttonFont);
	staffMenuPanel.add(btnSearchBookings);
	
	btnPayments.setLocation(300,400);
	btnPayments.setSize(250,50);
	btnPayments.addActionListener(this);
	btnPayments.setText("View Payments");
	btnPayments.setFont(buttonFont);
	staffMenuPanel.add(btnPayments);
	
	btnLogout.setLocation(300,475);
	btnLogout.setSize(250,50);
	btnLogout.addActionListener(this);
	btnLogout.setText("Logout");
	btnLogout.setFont(buttonFont);
	staffMenuPanel.add(btnLogout);	

}
/* CREATES THE CUSTOMER MENU PANEL 
DECLARES ALL THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createCustomerMenuPanel()
{
	customerMenuPanel.setBackground(Color.WHITE);
	
	lblCustomerMenuBoltonOneLogo.setSize(240,117);
	lblCustomerMenuBoltonOneLogo.setLocation(0,0);
	lblCustomerMenuBoltonOneLogo.setIcon(imgBoltonOneLogo);
	customerMenuPanel.add(lblCustomerMenuBoltonOneLogo); 
	
	lblCustomerMenuUoBLogo.setSize(250,117);
	lblCustomerMenuUoBLogo.setLocation(625,0);
	lblCustomerMenuUoBLogo.setIcon(imgUoBLogo);
	customerMenuPanel.add(lblCustomerMenuUoBLogo); 	

	lblCustomerMenuMainTitle.setLocation(250,0);
	lblCustomerMenuMainTitle.setSize(350,100);
	lblCustomerMenuMainTitle.setOpaque(false);
    lblCustomerMenuMainTitle.setFont(mainTitleFont);
	lblCustomerMenuMainTitle.setText(mainTitleText);
	customerMenuPanel.add(lblCustomerMenuMainTitle);
	
	lblCustomerMenuTitle.setLocation(275,100);
	lblCustomerMenuTitle.setSize(350,50);
	lblCustomerMenuTitle.setOpaque(false);
	lblCustomerMenuTitle.setFont(pageTitleFont);
	lblCustomerMenuTitle.setText("Welcome to Bolton One");
	customerMenuPanel.add(lblCustomerMenuTitle);

	btnBookings2.setLocation(300,175);
	btnBookings2.setSize(250,50);
	btnBookings2.addActionListener(this);
	btnBookings2.setText("View My Bookings");
	btnBookings2.setFont(buttonFont);
	customerMenuPanel.add(btnBookings2);

	btnAddBookings2.setLocation(300,250);
	btnAddBookings2.setSize(250,50);
	btnAddBookings2.addActionListener(this);
	btnAddBookings2.setText("Add My Bookings");
	btnAddBookings2.setFont(buttonFont);
	customerMenuPanel.add(btnAddBookings2);

	btnSearchBookings2.setLocation(300,325);
	btnSearchBookings2.setSize(250,50);
	btnSearchBookings2.addActionListener(this);
	btnSearchBookings2.setText("Search My Bookings");
	btnSearchBookings2.setFont(buttonFont);
	customerMenuPanel.add(btnSearchBookings2);
	
	btnPayments2.setLocation(300,400);
	btnPayments2.setSize(250,50);
	btnPayments2.addActionListener(this);
	btnPayments2.setText("View My Payments");
	btnPayments2.setFont(buttonFont);
	customerMenuPanel.add(btnPayments2);
	
	btnLogout2.setLocation(300,475);
	btnLogout2.setSize(250,50);
	btnLogout2.addActionListener(this);
	btnLogout2.setText("Logout");
	btnLogout2.setFont(buttonFont);
	customerMenuPanel.add(btnLogout2);	

}

/* CREATES THE STAFF ADD BOOKINGS PANEL 
DECLARES ALL THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createAddBookingsPanel()
{
	
	addBookingsPanel.setBackground(Color.WHITE);
	
	lblStaffAddBookingsBOL.setSize(240,117);
	lblStaffAddBookingsBOL.setLocation(0,0);
	lblStaffAddBookingsBOL.setIcon(imgBoltonOneLogo);
	addBookingsPanel.add(lblStaffAddBookingsBOL); 
	
	lblStaffAddBookingsUoBL.setSize(250,117);
	lblStaffAddBookingsUoBL.setLocation(625,0);
	lblStaffAddBookingsUoBL.setIcon(imgUoBLogo);
	addBookingsPanel.add(lblStaffAddBookingsUoBL); 
	
	lblStaffAddBookingsMainTitle.setLocation(250,0); 
	lblStaffAddBookingsMainTitle.setSize(350,100);
	lblStaffAddBookingsMainTitle.setOpaque(false);
    lblStaffAddBookingsMainTitle.setFont(mainTitleFont);
	lblStaffAddBookingsMainTitle.setText(mainTitleText);
	addBookingsPanel.add(lblStaffAddBookingsMainTitle);
	
	lblAddBookings.setLocation(275,110);
	lblAddBookings.setSize(275,50);
	lblAddBookings.setOpaque(false);
    lblAddBookings.setFont(pageTitleFont);
	lblAddBookings.setText("Add Bookings");
	addBookingsPanel.add(lblAddBookings);

	btnConfirm.setLocation(600,175);
	btnConfirm.setSize(150,30);
	btnConfirm.addActionListener(this);
	btnConfirm.setText("Confirm Booking");
	addBookingsPanel.add(btnConfirm);

	btnMenu1.setLocation(600,225);
	btnMenu1.setSize(150,30);
	btnMenu1.addActionListener(this);
	btnMenu1.setText("Return To Menu");
	addBookingsPanel.add(btnMenu1);

	tfCustomerFirstName.setLocation(350,175);
	tfCustomerFirstName.setSize(150,30);
	tfCustomerFirstName.setText("");
	tfCustomerFirstName.setColumns(10);
	addBookingsPanel.add(tfCustomerFirstName);
	
	lblCustomerFirstName.setLocation(200,165);
	lblCustomerFirstName.setSize(200,50);
	lblCustomerFirstName.setOpaque(false);
	lblCustomerFirstName.setFont(labelFont);
	lblCustomerFirstName.setText("First Name");
	addBookingsPanel.add(lblCustomerFirstName);

	tfCustomerName.setLocation(350,225);
	tfCustomerName.setSize(150,30);
	tfCustomerName.setText("");
	tfCustomerName.setColumns(10);
	addBookingsPanel.add(tfCustomerName);

	lblCustomerName.setLocation(200,215);
	lblCustomerName.setSize(200,50);
	lblCustomerName.setOpaque(false);
	lblCustomerName.setFont(labelFont);
	lblCustomerName.setText("Surname");
	addBookingsPanel.add(lblCustomerName);	
	
	tfCustomerMobile.setLocation(350,275);
	tfCustomerMobile.setSize(150,30);
	tfCustomerMobile.setText("");
	tfCustomerMobile.setColumns(10);
	addBookingsPanel.add(tfCustomerMobile);

	lblCustomerMobile.setLocation(200,265);
	lblCustomerMobile.setSize(200,50);
	lblCustomerMobile.setOpaque(false);
	lblCustomerMobile.setFont(labelFont);
	lblCustomerMobile.setText("Mobile Number");
	addBookingsPanel.add(lblCustomerMobile);	

	tfCustomerEmail.setLocation(350,325);
	tfCustomerEmail.setSize(150,30);
	tfCustomerEmail.setText("");
	tfCustomerEmail.setColumns(10);
	addBookingsPanel.add(tfCustomerEmail);
	
	lblCustomerEmail.setLocation(200,315);
	lblCustomerEmail.setSize(200,50);
	lblCustomerEmail.setOpaque(false);
	lblCustomerEmail.setFont(labelFont);
	lblCustomerEmail.setText("Email");
	addBookingsPanel.add(lblCustomerEmail);	
	
	cbxBookingDate.setLocation(350,375);
	cbxBookingDate.setSize(150,30);
	addBookingsPanel.add(cbxBookingDate);
	
	lblBookingDate.setLocation(200,365);
	lblBookingDate.setSize(200,50);
	lblBookingDate.setOpaque(false);
	lblBookingDate.setFont(labelFont);
	lblBookingDate.setText("Date");
	addBookingsPanel.add(lblBookingDate);	
	
	cbxBookingTime.setLocation(350,425);	
	cbxBookingTime.setSize(150,30);
	addBookingsPanel.add(cbxBookingTime);
	
	lblBookingTime.setLocation(200,415);
	lblBookingTime.setSize(200,50);
	lblBookingTime.setOpaque(false);
	lblBookingTime.setFont(labelFont);
	lblBookingTime.setText("Time");
	addBookingsPanel.add(lblBookingTime);	
	
	cbxRoom.setLocation(350,475);	
	cbxRoom.setSize(150,30);
	addBookingsPanel.add(cbxRoom);

	lblRoom.setLocation(200,465);
	lblRoom.setSize(200,50);
	lblRoom.setOpaque(false);
	lblRoom.setFont(labelFont);
	lblRoom.setText("Room");
	addBookingsPanel.add(lblRoom);	
	
	cbxActivity.setLocation(350,525);	
	cbxActivity.setSize(150,30);
	addBookingsPanel.add(cbxActivity);
	
	lblActivity.setLocation(200,515);
	lblActivity.setSize(200,50);
	lblActivity.setOpaque(false);
	lblActivity.setFont(labelFont);
	lblActivity.setText("Activity");
	addBookingsPanel.add(lblActivity);	

}
/* CREATES THE CUSTOMER ADD BOOKINGS PANEL 
DECLARES ALL THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createCustomerAddBookingsPanel()
{
	
	customerAddBookingsPanel.setBackground(Color.WHITE);
	
	lblCustomerAddBookingsBOL.setSize(240,117);
	lblCustomerAddBookingsBOL.setLocation(0,0);
	lblCustomerAddBookingsBOL.setIcon(imgBoltonOneLogo);
	customerAddBookingsPanel.add(lblCustomerAddBookingsBOL); 
	
	lblCustomerAddBookingsUoBL.setSize(250,117);
	lblCustomerAddBookingsUoBL.setLocation(625,0);
	lblCustomerAddBookingsUoBL.setIcon(imgUoBLogo);
	customerAddBookingsPanel.add(lblCustomerAddBookingsUoBL); 

	lblCustomerAddBookingsMainTitle.setLocation(250,0);
	lblCustomerAddBookingsMainTitle.setSize(350,100);
	lblCustomerAddBookingsMainTitle.setOpaque(false);
    lblCustomerAddBookingsMainTitle.setFont(mainTitleFont);
	lblCustomerAddBookingsMainTitle.setText(mainTitleText);
	customerAddBookingsPanel.add(lblCustomerAddBookingsMainTitle);
	
	lblAddBookings2.setLocation(275,110);
	lblAddBookings2.setSize(275,50);
	lblAddBookings2.setOpaque(false);
    lblAddBookings2.setFont(pageTitleFont);
	lblAddBookings2.setText("Add Bookings");
	customerAddBookingsPanel.add(lblAddBookings2);

	btnConfirm2.setLocation(600,175);
	btnConfirm2.setSize(150,30);
	btnConfirm2.addActionListener(this);
	btnConfirm2.setText("Confirm Booking");
	customerAddBookingsPanel.add(btnConfirm2);

	btnMenu2.setLocation(600,225);
	btnMenu2.setSize(150,30);
	btnMenu2.addActionListener(this);
	btnMenu2.setText("Return To Menu");
	customerAddBookingsPanel.add(btnMenu2);

	tfCustomerFirstName2.setLocation(350,175);
	tfCustomerFirstName2.setSize(150,30);
	tfCustomerFirstName2.setText("");
	tfCustomerFirstName2.setColumns(10);
	customerAddBookingsPanel.add(tfCustomerFirstName2);
	
	lblCustomerFirstName2.setLocation(200,165);
	lblCustomerFirstName2.setSize(200,50);
	lblCustomerFirstName2.setOpaque(false);
	lblCustomerFirstName2.setFont(labelFont);
	lblCustomerFirstName2.setText("First Name");
	customerAddBookingsPanel.add(lblCustomerFirstName2);

	tfCustomerName2.setLocation(350,225);
	tfCustomerName2.setSize(150,30);
	tfCustomerName2.setText("");
	tfCustomerName2.setColumns(10);
	customerAddBookingsPanel.add(tfCustomerName2);

	lblCustomerName2.setLocation(200,215);
	lblCustomerName2.setSize(200,50);
	lblCustomerName2.setOpaque(false);
	lblCustomerName2.setFont(labelFont);
	lblCustomerName2.setText("Surname");
	customerAddBookingsPanel.add(lblCustomerName2);	
	
	tfCustomerMobile2.setLocation(350,275);
	tfCustomerMobile2.setSize(150,30);
	tfCustomerMobile2.setText("");
	tfCustomerMobile2.setColumns(10);
	customerAddBookingsPanel.add(tfCustomerMobile2);

	lblCustomerMobile2.setLocation(200,265);
	lblCustomerMobile2.setSize(200,50);
	lblCustomerMobile2.setOpaque(false);
	lblCustomerMobile2.setFont(labelFont);
	lblCustomerMobile2.setText("Mobile Number");
	customerAddBookingsPanel.add(lblCustomerMobile2);	

	tfCustomerEmail2.setLocation(350,325);
	tfCustomerEmail2.setSize(150,30);
	tfCustomerEmail2.setText("");
	tfCustomerEmail2.setColumns(10);
	customerAddBookingsPanel.add(tfCustomerEmail2);
	
	lblCustomerEmail2.setLocation(200,315);
	lblCustomerEmail2.setSize(200,50);
	lblCustomerEmail2.setOpaque(false);
	lblCustomerEmail2.setFont(labelFont);
	lblCustomerEmail2.setText("Email");
	customerAddBookingsPanel.add(lblCustomerEmail2);	
	
	cbxBookingDate2.setLocation(350,375);
	cbxBookingDate2.setSize(150,30);
	customerAddBookingsPanel.add(cbxBookingDate2);
	
	lblBookingDate2.setLocation(200,365);
	lblBookingDate2.setSize(200,50);
	lblBookingDate2.setOpaque(false);
	lblBookingDate2.setFont(labelFont);
	lblBookingDate2.setText("Date");
	customerAddBookingsPanel.add(lblBookingDate2);	

	cbxBookingTime2.setLocation(350,425);	
	cbxBookingTime2.setSize(150,30);
	customerAddBookingsPanel.add(cbxBookingTime2);
	
	lblBookingTime2.setLocation(200,415);
	lblBookingTime2.setSize(200,50);
	lblBookingTime2.setOpaque(false);
	lblBookingTime2.setFont(labelFont);
	lblBookingTime2.setText("Time");
	customerAddBookingsPanel.add(lblBookingTime2);	
	
	cbxRoom2.setLocation(350,475);	
	cbxRoom2.setSize(150,30);
	customerAddBookingsPanel.add(cbxRoom2);

	lblRoom2.setLocation(200,465);
	lblRoom2.setSize(200,50);
	lblRoom2.setOpaque(false);
	lblRoom2.setFont(labelFont);
	lblRoom2.setText("Room");
	customerAddBookingsPanel.add(lblRoom2);	
	
	cbxActivity2.setLocation(350,525);	
	cbxActivity2.setSize(150,30);
	customerAddBookingsPanel.add(cbxActivity2);
	
	lblActivity2.setLocation(200,515);
	lblActivity2.setSize(200,50);
	lblActivity2.setOpaque(false);
	lblActivity2.setFont(labelFont);
	lblActivity2.setText("Activity");
	customerAddBookingsPanel.add(lblActivity2);	


	




}
/* CREATES THE STAFF VIEW BOOKINGS PANEL 
DECLARES THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createViewBookingPanel()
{
	viewBookingsPanel.setBackground(Color.WHITE);
	viewBookingsPanel.setLayout(null);//NO SET LAYOUT OF THE PANEL

	
	bookingTableScroll.setSize(800,300);//SETS THE SIZE OF THE TABLE SCROLL
	bookingTableScroll.setLocation(40,275);
	viewBookingsPanel.add(bookingTableScroll);
	
	bookingTableHeader.setFont(tableHeaderFont);
	bookingTable.setFont(tableRowsFont);
	bookingTable.setRowHeight(bookingTable.getRowHeight()+10);
	colModel.getColumn(0).setPreferredWidth(10); //SETS THE SIZES OF EACH COLUMN IN THE TABLE   
	colModel.getColumn(1).setPreferredWidth(75);
	colModel.getColumn(2).setPreferredWidth(125);    
	colModel.getColumn(3).setPreferredWidth(50);
	colModel.getColumn(4).setPreferredWidth(175);    
	colModel.getColumn(5).setPreferredWidth(125);

	lblStaffViewBookingsBOL.setSize(240,117);
	lblStaffViewBookingsBOL.setLocation(0,0);
	lblStaffViewBookingsBOL.setIcon(imgBoltonOneLogo);
	viewBookingsPanel.add(lblStaffViewBookingsBOL); 
	
	lblStaffViewBookingsUoBL.setSize(250,117);
	lblStaffViewBookingsUoBL.setLocation(625,0);
	lblStaffViewBookingsUoBL.setIcon(imgUoBLogo);
	viewBookingsPanel.add(lblStaffViewBookingsUoBL); 

	lblStaffViewBookingsMainTitle.setLocation(250,0);
	lblStaffViewBookingsMainTitle.setSize(350,100);
	lblStaffViewBookingsMainTitle.setOpaque(false);
    lblStaffViewBookingsMainTitle.setFont(mainTitleFont);
	lblStaffViewBookingsMainTitle.setText(mainTitleText);
	viewBookingsPanel.add(lblStaffViewBookingsMainTitle);	
	

	lblStaffViewBookings.setLocation(300,110);
	lblStaffViewBookings.setSize(275,50);
	lblStaffViewBookings.setOpaque(false);
    lblStaffViewBookings.setFont(pageTitleFont);
	lblStaffViewBookings.setText("View Bookings");
	viewBookingsPanel.add(lblStaffViewBookings);
	
	btnMenu3.setLocation(300,185);
	btnMenu3.setSize(225,30);
	btnMenu3.addActionListener(this);
	btnMenu3.setText("Return To Menu");
	btnMenu3.setFont(buttonFont);
	viewBookingsPanel.add(btnMenu3);
	
	btnDateFilter.setLocation(185,225);
	btnDateFilter.setSize(100,30);
	btnDateFilter.addActionListener(this);
	btnDateFilter.setText("Filter");
	btnDateFilter.setFont(buttonFont);
	viewBookingsPanel.add(btnDateFilter);	

	btnDeleteBooking.setLocation(300,225);
	btnDeleteBooking.setSize(200,30);
	btnDeleteBooking.addActionListener(this);
	btnDeleteBooking.setText("Delete Booking");
	btnDeleteBooking.setFont(buttonFont);
	viewBookingsPanel.add(btnDeleteBooking);	
	
	
	cbxFilterBookings.setLocation(40,225);	
	cbxFilterBookings.setSize(135,30);
	viewBookingsPanel.add(cbxFilterBookings);
	
	
	
	
}
/* CREATES THE CUSTOMER VIEW BOOKINGS PANEL 
DECLARES ALL THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createCustomerViewBookingPanel()
{
	customerViewBookingsPanel.setBackground(Color.WHITE);
	customerViewBookingsPanel.setLayout(null);
	
	
	customerBookingTableScroll.setSize(800,300);
	customerBookingTableScroll.setLocation(40,275);
	customerViewBookingsPanel.add(customerBookingTableScroll);
	
	customerBookingTableHeader.setFont(tableHeaderFont);
	customerBookingTable.setFont(tableRowsFont);
	customerBookingTable.setRowHeight(customerBookingTable.getRowHeight()+10);//CHANGES HEADER SIZE
	customerBookingTableScroll.setVisible(true);
	customerColModel.getColumn(0).setPreferredWidth(10);//APPLYS SET COLUMN WIDTHS    
	customerColModel.getColumn(1).setPreferredWidth(75);
	customerColModel.getColumn(2).setPreferredWidth(125);    
	customerColModel.getColumn(3).setPreferredWidth(50);
	customerColModel.getColumn(4).setPreferredWidth(175);    
	customerColModel.getColumn(5).setPreferredWidth(125);
	
	
	lblCustomerViewBookingsBOL.setSize(240,117);
	lblCustomerViewBookingsBOL.setLocation(0,0);
	lblCustomerViewBookingsBOL.setIcon(imgBoltonOneLogo);
	customerViewBookingsPanel.add(lblCustomerViewBookingsBOL); 
	
	lblCustomerViewBookingsUoBL.setSize(250,117);
	lblCustomerViewBookingsUoBL.setLocation(625,0);
	lblCustomerViewBookingsUoBL.setIcon(imgUoBLogo);
	customerViewBookingsPanel.add(lblCustomerViewBookingsUoBL); 

	lblCustomerViewBookingsMainTitle.setLocation(250,0);
	lblCustomerViewBookingsMainTitle.setSize(350,100);
	lblCustomerViewBookingsMainTitle.setOpaque(false);
    lblCustomerViewBookingsMainTitle.setFont(mainTitleFont);
	lblCustomerViewBookingsMainTitle.setText(mainTitleText);
	customerViewBookingsPanel.add(lblCustomerViewBookingsMainTitle);
	
	lblCustomerViewBookings.setLocation(300,110);
	lblCustomerViewBookings.setSize(275,50);
	lblCustomerViewBookings.setOpaque(false);
    lblCustomerViewBookings.setFont(pageTitleFont);
	lblCustomerViewBookings.setText("View Bookings");
	customerViewBookingsPanel.add(lblCustomerViewBookings);

	btnMenu4.setLocation(300,185);
	btnMenu4.setSize(225,30);
	btnMenu4.addActionListener(this);
	btnMenu4.setText("Return To Menu");
	btnMenu4.setFont(buttonFont);
	customerViewBookingsPanel.add(btnMenu4);
	
	btnCustomerDateFilter.setLocation(185,225);
	btnCustomerDateFilter.setSize(100,30);
	btnCustomerDateFilter.addActionListener(this);
	btnCustomerDateFilter.setText("Filter");
	btnCustomerDateFilter.setFont(buttonFont);
	customerViewBookingsPanel.add(btnCustomerDateFilter);	

	btnCustomerDeleteBooking.setLocation(300,225);
	btnCustomerDeleteBooking.setSize(200,30);
	btnCustomerDeleteBooking.addActionListener(this);
	btnCustomerDeleteBooking.setText("Delete Booking");
	btnCustomerDeleteBooking.setFont(buttonFont);
	customerViewBookingsPanel.add(btnCustomerDeleteBooking);	
	
	
	cbxCustomerFilterBookings.setLocation(40,225);	
	cbxCustomerFilterBookings.setSize(135,30);
	customerViewBookingsPanel.add(cbxCustomerFilterBookings);
	
}



/* CREATES THE STAFF SEARCH BOOKINGS PANEL 
DECLARES ALL THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createSearchPanel()
{
	searchPanel.setBackground(Color.WHITE);
	
	lblStaffSearchPanelBOL.setSize(240,117);
	lblStaffSearchPanelBOL.setLocation(0,0);
	lblStaffSearchPanelBOL.setIcon(imgBoltonOneLogo);
	searchPanel.add(lblStaffSearchPanelBOL); 
	
	lblStaffSearchPanelUoBL.setSize(250,117);
	lblStaffSearchPanelUoBL.setLocation(625,0);
	lblStaffSearchPanelUoBL.setIcon(imgUoBLogo);
	searchPanel.add(lblStaffSearchPanelUoBL); 

	lblStaffSearchPanelMainTitle.setLocation(250,0);
	lblStaffSearchPanelMainTitle.setSize(350,100);
	lblStaffSearchPanelMainTitle.setOpaque(false);
    lblStaffSearchPanelMainTitle.setFont(mainTitleFont);
	lblStaffSearchPanelMainTitle.setText(mainTitleText);
	searchPanel.add(lblStaffSearchPanelMainTitle);
	

	lblStaffSearchBookings.setLocation(300,110);
	lblStaffSearchBookings.setSize(275,50);
	lblStaffSearchBookings.setOpaque(false);
    lblStaffSearchBookings.setFont(pageTitleFont);
	lblStaffSearchBookings.setText("Search Bookings");
	searchPanel.add(lblStaffSearchBookings);
	
	btnMenu5.setLocation(300,175);
	btnMenu5.setSize(250,30);
	btnMenu5.addActionListener(this);
	btnMenu5.setText("Return To Menu");
	btnMenu5.setFont(buttonFont);
	searchPanel.add(btnMenu5);
	
	lblSearchByID.setLocation(200,225);
	lblSearchByID.setSize(100,50);
	lblSearchByID.setOpaque(false);
	lblSearchByID.setText("ID");
	lblSearchByID.setFont(labelFont);
	searchPanel.add(lblSearchByID);
	
	btnSearchByID.setLocation(525,225);
	btnSearchByID.setSize(225,50);
	btnSearchByID.addActionListener(this);
	btnSearchByID.setText("Search By ID");
	btnSearchByID.setFont(buttonFont);
	searchPanel.add(btnSearchByID);
	
	tfSearchByID.setLocation(300,225);
	tfSearchByID.setSize(200,50);
	tfSearchByID.setText("");
	tfSearchByID.setColumns(10);
	tfSearchByID.setFont(labelFont);
	searchPanel.add(tfSearchByID);
	
	
	lblSearchByDate.setLocation(200,300);
	lblSearchByDate.setSize(100,50);
	lblSearchByDate.setOpaque(false);
	lblSearchByDate.setText("Date");
	lblSearchByDate.setFont(labelFont);
	searchPanel.add(lblSearchByDate);
	
	btnSearchByDate.setLocation(525,300);
	btnSearchByDate.setSize(225,50);
	btnSearchByDate.addActionListener(this);
	btnSearchByDate.setText("Search By Date");
	btnSearchByDate.setFont(buttonFont);
	searchPanel.add(btnSearchByDate);
	
	tfSearchByDate.setLocation(300,300);
	tfSearchByDate.setSize(200,50);
	tfSearchByDate.setText("");
	tfSearchByDate.setColumns(10);
	tfSearchByDate.setFont(labelFont);
	searchPanel.add(tfSearchByDate);
		
	lblSearchByName.setLocation(200,375);
	lblSearchByName.setSize(100,50);
	lblSearchByName.setOpaque(false);
	lblSearchByName.setText("Name");
	lblSearchByName.setFont(labelFont);
	searchPanel.add(lblSearchByName);
	
	btnSearchByName.setLocation(525,375);
	btnSearchByName.setSize(225,50);
	btnSearchByName.addActionListener(this);
	btnSearchByName.setText("Search By Name");
	btnSearchByName.setFont(buttonFont);
	searchPanel.add(btnSearchByName);
	
	tfSearchByName.setLocation(300,375);
	tfSearchByName.setSize(200,50);
	tfSearchByName.setText("");
	tfSearchByName.setColumns(10);
	tfSearchByName.setFont(labelFont);
	searchPanel.add(tfSearchByName);
	//SETS THE SIZE OF THE TEXT AREA SCROLL TO DISPLAY SEARCH RESULTS
	searchScroll.setSize(650,135); 
	searchScroll.setLocation(125,450);
	jtaSearchResults.setFont(labelFont);
	searchScroll.setBorder(border);//ADDS BORDER TO TEXT AREA
	searchScroll.setVisible(false);//CURRENTLY INVISIBLE
	searchPanel.add(searchScroll);	


}
/* CREATES THE CUSTOMER SEARCH BOOKINGS PANEL 
DECLARES ALL THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createCustomerSearchPanel()
{
	customerSearchPanel.setBackground(Color.WHITE);	

	lblCustomerSearchPanelBOL.setSize(240,117);
	lblCustomerSearchPanelBOL.setLocation(0,0);
	lblCustomerSearchPanelBOL.setIcon(imgBoltonOneLogo);
	customerSearchPanel.add(lblCustomerSearchPanelBOL); 
	
	lblCustomerSearchPanelUoBL.setSize(250,117);
	lblCustomerSearchPanelUoBL.setLocation(625,0);
	lblCustomerSearchPanelUoBL.setIcon(imgUoBLogo);
	customerSearchPanel.add(lblCustomerSearchPanelUoBL); 

	lblCustomerSearchPanelMainTitle.setLocation(250,0);
	lblCustomerSearchPanelMainTitle.setSize(350,100);
	lblCustomerSearchPanelMainTitle.setOpaque(false);
    lblCustomerSearchPanelMainTitle.setFont(mainTitleFont);
	lblCustomerSearchPanelMainTitle.setText(mainTitleText);
	customerSearchPanel.add(lblCustomerSearchPanelMainTitle);
	
	lblCustomerSearchBookings.setLocation(300,110);
	lblCustomerSearchBookings.setSize(275,50);
	lblCustomerSearchBookings.setOpaque(false);
    lblCustomerSearchBookings.setFont(pageTitleFont);
	lblCustomerSearchBookings.setText("Search Bookings");
	customerSearchPanel.add(lblCustomerSearchBookings);
	
	btnMenu6.setLocation(300,175);
	btnMenu6.setSize(250,30);
	btnMenu6.addActionListener(this);
	btnMenu6.setText("Return To Menu");
	btnMenu6.setFont(buttonFont);
	customerSearchPanel.add(btnMenu6);
	
	lblSearchByID2.setLocation(200,225);
	lblSearchByID2.setSize(100,50);
	lblSearchByID2.setOpaque(false);
	lblSearchByID2.setText("ID");
	lblSearchByID2.setFont(labelFont);
	customerSearchPanel.add(lblSearchByID2);
	
	btnSearchByID2.setLocation(525,225);
	btnSearchByID2.setSize(225,50);
	btnSearchByID2.addActionListener(this);
	btnSearchByID2.setText("Search By ID");
	btnSearchByID2.setFont(buttonFont);
	customerSearchPanel.add(btnSearchByID2);
	
	tfSearchByID2.setLocation(300,225);
	tfSearchByID2.setSize(200,50);
	tfSearchByID2.setText("");
	tfSearchByID2.setColumns(10);
	tfSearchByID2.setFont(labelFont);
	customerSearchPanel.add(tfSearchByID2);
	
	lblSearchByDate2.setLocation(200,300);
	lblSearchByDate2.setSize(100,50);
	lblSearchByDate2.setOpaque(false);
	lblSearchByDate2.setText("Date");
	lblSearchByDate2.setFont(labelFont);
	customerSearchPanel.add(lblSearchByDate2);
	
	btnSearchByDate2.setLocation(525,300);
	btnSearchByDate2.setSize(225,50);
	btnSearchByDate2.addActionListener(this);
	btnSearchByDate2.setText("Search By Date");
	btnSearchByDate2.setFont(buttonFont);
	customerSearchPanel.add(btnSearchByDate2);
	
	tfSearchByDate2.setLocation(300,300);
	tfSearchByDate2.setSize(200,50);
	tfSearchByDate2.setText("");
	tfSearchByDate2.setColumns(10);
	tfSearchByDate2.setFont(labelFont);
	customerSearchPanel.add(tfSearchByDate2);
		
	lblSearchByName2.setLocation(200,375);
	lblSearchByName2.setSize(100,50);
	lblSearchByName2.setOpaque(false);
	lblSearchByName2.setText("Name");
	lblSearchByName2.setFont(labelFont);
	customerSearchPanel.add(lblSearchByName2);
	
	btnSearchByName2.setLocation(525,375);
	btnSearchByName2.setSize(225,50);
	btnSearchByName2.addActionListener(this);
	btnSearchByName2.setText("Search By Name");
	btnSearchByName2.setFont(buttonFont);
	customerSearchPanel.add(btnSearchByName2);
	
	tfSearchByName2.setLocation(300,375);
	tfSearchByName2.setSize(200,50);
	tfSearchByName2.setText("");
	tfSearchByName2.setColumns(10);
	tfSearchByName2.setFont(labelFont);
	customerSearchPanel.add(tfSearchByName2);
	
	customerSearchScroll.setSize(650,135); 
	customerSearchScroll.setLocation(125,450);
	jtaCustomerSearchResults.setFont(labelFont);
	customerSearchScroll.setBorder(border);//ADDS BORDER
	customerSearchScroll.setVisible(false);//SETS TO INVISIBLE
	customerSearchPanel.add(customerSearchScroll);	
}
/* CREATES THE STAFF MAKE PAYMENTS PANEL 
DECLARES THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createPaymentsPanel()
{
	paymentsPanel.setBackground(Color.WHITE);
	paymentsPanel.setVisible(false);

	lblStaffPaymentsPanelBOL.setSize(240,117);
	lblStaffPaymentsPanelBOL.setLocation(0,0);
	lblStaffPaymentsPanelBOL.setIcon(imgBoltonOneLogo);
	paymentsPanel.add(lblStaffPaymentsPanelBOL); 
	
	lblStaffPaymentsPanelUoBL.setSize(250,117);
	lblStaffPaymentsPanelUoBL.setLocation(625,0);
	lblStaffPaymentsPanelUoBL.setIcon(imgUoBLogo);
	paymentsPanel.add(lblStaffPaymentsPanelUoBL); 

	lblStaffPaymentsPanelMainTitle.setLocation(250,0);
	lblStaffPaymentsPanelMainTitle.setSize(350,100);
	lblStaffPaymentsPanelMainTitle.setOpaque(false);
    lblStaffPaymentsPanelMainTitle.setFont(mainTitleFont);
	lblStaffPaymentsPanelMainTitle.setText(mainTitleText);
	paymentsPanel.add(lblStaffPaymentsPanelMainTitle);
	
	lblMakePayments.setLocation(325,100);
	lblMakePayments.setSize(200,100);
	lblMakePayments.setOpaque(false);
    lblMakePayments.setFont(pageTitleFont);
	lblMakePayments.setText("Make Payments");
	paymentsPanel.add(lblMakePayments);
	
	lblTotalPayment.setLocation(300,225);
	lblTotalPayment.setSize(350,100);
	lblTotalPayment.setOpaque(false);
    lblTotalPayment.setFont(labelFont);
	paymentsPanel.add(lblTotalPayment);
	
	btnCancelBooking.setLocation(300,200);
	btnCancelBooking.setSize(250,30);
	btnCancelBooking.addActionListener(this);
	btnCancelBooking.setText("Cancel Booking");
	btnCancelBooking.setFont(buttonFont);
	paymentsPanel.add(btnCancelBooking);
	
	btnPay.setLocation(600,300);
	btnPay.setSize(200,50);
	btnPay.addActionListener(this);
	btnPay.setText("Confirm Payment");
	btnPay.setFont(buttonFont);
	paymentsPanel.add(btnPay);
	
	tfCardNumber.setLocation(375,300);
	tfCardNumber.setSize(175,50);
	tfCardNumber.setText("");
	tfCardNumber.setColumns(10);
	tfCardNumber.setFont(labelFont);
	paymentsPanel.add(tfCardNumber);
	
	lblCardNumber.setLocation(200,300);
	lblCardNumber.setSize(200,40);
	lblCardNumber.setOpaque(false);
	lblCardNumber.setText("Card Number");
	lblCardNumber.setFont(labelFont);
	paymentsPanel.add(lblCardNumber);	
	
	tfCardHolder.setLocation(375,375);
	tfCardHolder.setSize(175,50);
	tfCardHolder.setText("");
	tfCardHolder.setColumns(10);
	tfCardHolder.setFont(labelFont);
	paymentsPanel.add(tfCardHolder);
	
	lblCardHolder.setLocation(200,375);
	lblCardHolder.setSize(200,40);
	lblCardHolder.setOpaque(false);
	lblCardHolder.setText("Card Holder");
	lblCardHolder.setFont(labelFont);
	paymentsPanel.add(lblCardHolder);		
}
/* CREATES THE CUSTOMER MAKE PAYMENTS PANEL 
DECLARES THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createCustomerPaymentsPanel()
{
	customerPaymentsPanel.setBackground(Color.WHITE);

	lblCustomerPaymentsPanelBOL.setSize(240,117);
	lblCustomerPaymentsPanelBOL.setLocation(0,0);
	lblCustomerPaymentsPanelBOL.setIcon(imgBoltonOneLogo);
	customerPaymentsPanel.add(lblCustomerPaymentsPanelBOL); 
	
	lblCustomerPaymentsPanelUoBL.setSize(250,117);
	lblCustomerPaymentsPanelUoBL.setLocation(625,0);
	lblCustomerPaymentsPanelUoBL.setIcon(imgUoBLogo);
	customerPaymentsPanel.add(lblCustomerPaymentsPanelUoBL); 

	lblCustomerPaymentsPanelMainTitle.setLocation(250,0);
	lblCustomerPaymentsPanelMainTitle.setSize(350,100);
	lblCustomerPaymentsPanelMainTitle.setOpaque(false);
    lblCustomerPaymentsPanelMainTitle.setFont(mainTitleFont);
	lblCustomerPaymentsPanelMainTitle.setText(mainTitleText);
	customerPaymentsPanel.add(lblCustomerPaymentsPanelMainTitle);	
	
	lblMakePayments2.setLocation(325,100);
	lblMakePayments2.setSize(200,100);
	lblMakePayments2.setOpaque(false);
    lblMakePayments2.setFont(pageTitleFont);
	lblMakePayments2.setText("Make Payments");
	customerPaymentsPanel.add(lblMakePayments2);

	lblTotalPayment2.setLocation(300,225);
	lblTotalPayment2.setSize(350,100);
	lblTotalPayment2.setOpaque(false);
    lblTotalPayment2.setFont(labelFont);
	customerPaymentsPanel.add(lblTotalPayment2);
	
	btnCancelBooking2.setLocation(300,200);
	btnCancelBooking2.setSize(250,30);
	btnCancelBooking2.addActionListener(this);
	btnCancelBooking2.setText("Cancel Booking");
	btnCancelBooking2.setFont(buttonFont);
	customerPaymentsPanel.add(btnCancelBooking2);
	
	btnPay2.setLocation(600,300);
	btnPay2.setSize(200,50);
	btnPay2.addActionListener(this);
	btnPay2.setText("Confirm Payment");
	btnPay2.setFont(buttonFont);
	customerPaymentsPanel.add(btnPay2);
	
	tfCardNumber2.setLocation(375,300);
	tfCardNumber2.setSize(175,50);
	tfCardNumber2.setText("");
	tfCardNumber2.setColumns(10);
	tfCardNumber2.setFont(labelFont);
	customerPaymentsPanel.add(tfCardNumber2);
	
	lblCardNumber2.setLocation(200,300);
	lblCardNumber2.setSize(200,40);
	lblCardNumber2.setOpaque(false);
	lblCardNumber2.setText("Card Number");
	lblCardNumber2.setFont(labelFont);
	customerPaymentsPanel.add(lblCardNumber2);	
	
	tfCardHolder2.setLocation(375,375);
	tfCardHolder2.setSize(175,50);
	tfCardHolder2.setText("");
	tfCardHolder2.setColumns(10);
	tfCardHolder2.setFont(labelFont);
	customerPaymentsPanel.add(tfCardHolder2);
	
	lblCardHolder2.setLocation(200,375);
	lblCardHolder2.setSize(200,40);
	lblCardHolder2.setOpaque(false);
	lblCardHolder2.setText("Card Holder");
	lblCardHolder2.setFont(labelFont);
	customerPaymentsPanel.add(lblCardHolder2);		
	
	
		
}
/* CREATES THE STAFF VIEW PAYMENTS PANEL 
DECLARES THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createViewPaymentsPanel()
{
	viewPaymentsPanel.setBackground(Color.WHITE);
	
	lblStaffViewPaymentsBOL.setSize(240,117);
	lblStaffViewPaymentsBOL.setLocation(0,0);
	lblStaffViewPaymentsBOL.setIcon(imgBoltonOneLogo);
	viewPaymentsPanel.add(lblStaffViewPaymentsBOL); 
	
	lblStaffViewPaymentsUoBL.setSize(250,117);
	lblStaffViewPaymentsUoBL.setLocation(625,0);
	lblStaffViewPaymentsUoBL.setIcon(imgUoBLogo);
	viewPaymentsPanel.add(lblStaffViewPaymentsUoBL); 

	lblStaffViewPaymentsMainTitle.setLocation(250,0);
	lblStaffViewPaymentsMainTitle.setSize(350,100);
	lblStaffViewPaymentsMainTitle.setOpaque(false);
    lblStaffViewPaymentsMainTitle.setFont(mainTitleFont);
	lblStaffViewPaymentsMainTitle.setText(mainTitleText);
	viewPaymentsPanel.add(lblStaffViewPaymentsMainTitle);	
	
	lblStaffViewPayments.setLocation(300,110);
	lblStaffViewPayments.setSize(275,50);
	lblStaffViewPayments.setOpaque(false);
    lblStaffViewPayments.setFont(pageTitleFont);
	lblStaffViewPayments.setText("View Payments");
	viewPaymentsPanel.add(lblStaffViewPayments);
	
	btnMenu9.setLocation(300,200);
	btnMenu9.setSize(250,30);
	btnMenu9.addActionListener(this);
	btnMenu9.setText("Return To Menu");
	btnMenu9.setFont(buttonFont);
	viewPaymentsPanel.add(btnMenu9);
	
	//DECLARES TABLE SCROLL SIZE
	paymentTableScroll.setSize(800,300);
	paymentTableScroll.setLocation(50,250);
	viewPaymentsPanel.add(paymentTableScroll);
	paymentTableHeader.setFont(tableHeaderFont);//ADDS FONTS
	paymentTable.setFont(tableRowsFont);
	paymentTable.setRowHeight(paymentTable.getRowHeight()+15);
	//APPLYS COLUMNS WIDTHS 
	paymentsColModel.getColumn(0).setPreferredWidth(150);    
	paymentsColModel.getColumn(1).setPreferredWidth(225);
	paymentsColModel.getColumn(2).setPreferredWidth(275);    
	paymentsColModel.getColumn(3).setPreferredWidth(75);
	


	
	
		
}
/* CREATES THE CUSTOMER MAKE PAYMENTS PANEL 
DECLARES THE SIZE AND LOCATION FOR ALL COMPONENTS */
public void createCustomerViewPaymentsPanel()
{
	customerViewPaymentsPanel.setBackground(Color.WHITE);
	//DECALRES TBALE SIZE
	customerPaymentTableScroll.setSize(800,300);
	customerPaymentTableScroll.setLocation(50,250);
	customerViewPaymentsPanel.add(customerPaymentTableScroll);
	customerPaymentTableHeader.setFont(tableHeaderFont);
	customerPaymentTable.setFont(tableRowsFont);
	customerPaymentTable.setRowHeight(customerPaymentTable.getRowHeight()+15);
	customerPaymentsColModel.getColumn(0).setPreferredWidth(150); //SETS COLUMNS WIDTHS   
	customerPaymentsColModel.getColumn(1).setPreferredWidth(225);
	customerPaymentsColModel.getColumn(2).setPreferredWidth(275);    
	customerPaymentsColModel.getColumn(3).setPreferredWidth(75);
	
	lblCustomerViewPaymentsBOL.setSize(240,117);
	lblCustomerViewPaymentsBOL.setLocation(0,0);
	lblCustomerViewPaymentsBOL.setIcon(imgBoltonOneLogo);
	customerViewPaymentsPanel.add(lblCustomerViewPaymentsBOL); 
	
	lblCustomerViewPaymentsUoBL.setSize(250,117);
	lblCustomerViewPaymentsUoBL.setLocation(625,0);
	lblCustomerViewPaymentsUoBL.setIcon(imgUoBLogo);
	customerViewPaymentsPanel.add(lblCustomerViewPaymentsUoBL); 

	lblCustomerViewPaymentsMainTitle.setLocation(250,0);
	lblCustomerViewPaymentsMainTitle.setSize(350,100);
	lblCustomerViewPaymentsMainTitle.setOpaque(false);
    lblCustomerViewPaymentsMainTitle.setFont(mainTitleFont);
	lblCustomerViewPaymentsMainTitle.setText(mainTitleText);
	customerViewPaymentsPanel.add(lblCustomerViewPaymentsMainTitle);	
	
	lblCustomerViewPayments.setLocation(300,110);
	lblCustomerViewPayments.setSize(275,50);
	lblCustomerViewPayments.setOpaque(false);
    lblCustomerViewPayments.setFont(pageTitleFont);
	lblCustomerViewPayments.setText("View Payments");
	customerViewPaymentsPanel.add(lblCustomerViewPayments);
	
	btnMenu10.setLocation(300,200);
	btnMenu10.setSize(250,30);
	btnMenu10.addActionListener(this);
	btnMenu10.setText("Return To Menu");
	btnMenu10.setFont(buttonFont);
	customerViewPaymentsPanel.add(btnMenu10);
	
}
//METHOD TO CLEAR TABLE 
public void clearPaymentTable()
{
	System.out.println("Running clearpaymenttable");
	//RUNS A FOR LOOP TO REMOVE ALL ROWS FROM THE PAYMENT TABLE
	int numberOfPaymentRows = paymentTableModel.getRowCount();
	
	for(int i = (numberOfPaymentRows-1); i > 0; i--)
	{
		paymentTableModel.removeRow(i);
		
	}
}

//POPULATES PAYMENT TABLE
public void populatePaymentTable()
{
	System.out.println("Running populatepaymenttable");
	
	paymentTableModel.setRowCount(0);
	//FOR EACH ROW A RECORD OF THE PAYMENTS ARRAY IS ADDED IN THE TABLE ARRAY
	for(int i = 0; i < pList.nextItemLocation; i++)
	{
		String[] dataToAdd = {bs.bookingsArray[i].bookingID+"",pList.paymentsArray[i].cardHolder+"",pList.paymentsArray[i].cardNumber+"",pList.paymentsArray[i].price+""};
		paymentTableModel.addRow(dataToAdd);
	
	}
}

//METHOD TO CLEAR CUSTOMER TABLE 
public void clearCustomerPaymentTable()
{
	System.out.println("Running clearpaymenttable");
	int numberOfCustomerPaymentRows = customerPaymentTableModel.getRowCount();
	
	for(int i = (numberOfCustomerPaymentRows-1); i > 0; i--)
	{
		customerPaymentTableModel.removeRow(i);
		
	}
}

//POPULATES CUSTOMER PAYMENT TABLE
public void populateCustomerPaymentTable()
{
	System.out.println("Running populatepaymenttable");
	
	customerPaymentTableModel.setRowCount(0);
	
	for(int i = 0; i < cpList.nextItemLocation; i++)
	{
		String[] dataToAdd = {cbList.customerBookingsArray[i].bookingID+"",cpList.customerPaymentsArray[i].cardHolder+"",cpList.customerPaymentsArray[i].cardNumber+"",cpList.customerPaymentsArray[i].price+""};
		customerPaymentTableModel.addRow(dataToAdd);
	
	}
}
//METHOD TO CLEAR CUSTOMER BOOKING TABLE - REMOVES EACH ROW IN THE TABLE
public void clearCustomerBookingTable()
{
	System.out.println("Running clearCustomerBookingTable");
	int numberOfCustomerBookingRows = customerBookingTableModel.getRowCount();
	for(int i = (numberOfCustomerBookingRows-1); i > 0; i--)
	{
		
		customerBookingTableModel.removeRow(i);
		
	}
}

//POPULATES CUSTOMER BOOKING TABLE
public void populateCustomerBookingTable()
{
	System.out.println("Running populateCustomerBookingTable");
	
	customerBookingTableModel.setRowCount(0);
	for(int i = 0; i < cbList.nextItemLocation; i++)
	{
		//RUNS A LOOP TO ADD ALL RECORDS OF THE CUSTOMER BOOKING ARRAY TOE THE TABLE ARRAY
		String[] dataToAdd = {cbList.customerBookingsArray[i].bookingID+"",cbList.customerBookingsArray[i].date+"",cbList.customerBookingsArray[i].time+"", cbList.customerBookingsArray[i].room+"", cbList.customerBookingsArray[i].name+""+"", cbList.customerBookingsArray[i].activity+""};
		customerBookingTableModel.addRow(dataToAdd);
	
	}
}
//METHOD TO CLEAR BOOKING TABLE - REMOVES EACH ROW IN THE TABLE
public void clearBookingTable()
{
	System.out.println("Running clearbookingtable");
	int numberOfBookingRows = bookingTableModel.getRowCount();
	
	for(int i = (numberOfBookingRows-1); i > 0; i--)
	{
		bookingTableModel.removeRow(i);
		
	}
}

//POPULATES ALL BOOKINGS TABLE
public void populateBookingTable()
{
	System.out.println("Running populatebookingtable");
	
	bookingTableModel.setRowCount(0);
	for(int i = 0; i < bs.nextItemLocation; i++)
	{
		String[] dataToAdd = {bs.bookingsArray[i].bookingID+"",bs.bookingsArray[i].date+"",bs.bookingsArray[i].time+"", bs.bookingsArray[i].room+"", bs.bookingsArray[i].name+""+"", bs.bookingsArray[i].activity+""};
		bookingTableModel.addRow(dataToAdd);
	
	}
}



//WHEN CALLED EACH OF THESE TEXT FIELDS ARE CLEARED
public void clearFields()
{
	tfCustomerFirstName.setText("");
	tfCustomerName.setText("");
	tfCustomerMobile.setText("");
	tfCustomerEmail.setText("");
	tfCardNumber.setText("");
	tfCardHolder.setText("");
	tfUsername.setText("");
	tfPassword.setText("");
	
}
public void clearFields2()
{
	tfCustomerFirstName2.setText("");
	tfCustomerName2.setText("");
	tfCustomerMobile2.setText("");
	tfCustomerEmail2.setText("");
	tfCardNumber2.setText("");
	tfCardHolder2.setText("");
	tfCustomerUsername.setText("");
	tfCustomerPassword.setText("");
	
}
//METHOD TO CLEAR OPENING TABLE - REMOVES EACH ROW IN THE TABLE
public void clearOpeningTable()
{
	System.out.println("Running ....clearOpeningTable");
	int numberOfOpeningRows = openingTableModel.getRowCount();
	
	for(int i = (numberOfOpeningRows-1); i > 0; i--)
	{
		openingTableModel.removeRow(i);
		
	}
}

//METHOD TO POPULATE OPENING TABLE - ADDS EACH RECORD TO THE TABLE ARRAY
public void populateOpeningTable()
{
	System.out.println("Running ....populateOpeningTable");
	
	openingTableModel.setRowCount(0);
	
	for(int i = 0; i < open.nextItemLocation; i++)
	{
		String[] dataToAdd = {open.openingDataArray[i].openingDay+"",open.openingDataArray[i].openingTime+""};
		openingTableModel.addRow(dataToAdd);
		
	
	}
}
/* METHOD USED DURING TESTING TO ADD MANY BOOKINGS AT ONCE
THE METHOD CREATES MOCK DATA TO BE ADDED AS BOOKINGS AND PAYMENTS
ALL DATA IS MADE TO BE LEGAL AS IT WOULD BE IN REAL USE */
public void stressTestAddingBooking()
{
	System.out.println("Adding bookings for testing");
	final long startTime = System.currentTimeMillis();//STARTS A TIMER
	Random rNumGen = new Random();//RANDOM NUMBER GENERATOR OBJECT
	
	for(int i=0; i<51; i++)//LOOP TO REPEAT THE METHOD WHICH WILL WRITE TO BOOKING AND PAYMENT FILES
	{
		int rNumDay = rNumGen.nextInt(30) +1;//GENERATES RANDOM DAY AND MONTH NUMBER TO CREATE A RANDOM DATE
		System.out.println("");
		int rNumMonth = rNumGen.nextInt(12) +1;
		Booking testTemp = new Booking();
		if(rNumDay<10)//ENSURES THE DATES ARE OF THE SAME FORMAT DD/MM/YY
		{
			 bDay = ("0"+rNumDay+"");
		}
		else if(rNumMonth<10)
		{
			 bMonth = ("0"+rNumMonth+"");
		}
		else 
		{
			bDay = rNumDay+"";
			bMonth = rNumMonth+"";
		}
		
		int rNumIndex = rNumGen.nextInt(11);
		String[] listNames = new String[] {"Shak Shah","Kamran Patel", "Amit Bhudia","Sonia Parmer","Usaamah Lakada","Bob Smtih","Uwais Patel","Harlan Nalder","Rozelle Shouler","Ave Goldfinch","Darb Gazey"};
		String[] listTimes = new String[] {"07:00 - 08:00","08:00 - 09:00", "09:00 - 10:00", "10:00 - 11:00","11:00 - 12:00" , "12:00 - 13:00" ,"13:00 - 14:00" , "14:00 - 15:00" , "15:00 - 16:00" , "16:00 - 17:00", "17:00 - 18:00", "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00" };
		int rNumIndex2 = rNumGen.nextInt(5);
		String[] listActivities = new String[] {"Football", "Basketball", "Badminton", "Table Tennis", "Tennis"};
		//RUNS THE WRTIE TO FILES USING SET DATA 
		//DATA STORED IN AN OBJECT TO BE PASSED INTO ANOTHER METHOD
		testTemp.bookingID = (i);
		testTemp.name = (listNames[rNumIndex]);
		testTemp.date = (bDay+"/"+bMonth+"/19");
		testTemp.time = (listTimes[rNumIndex]);
		testTemp.room = ("Room "+(rNumIndex2+1));
		testTemp.activity = (listActivities[rNumIndex2]);
		String tempCardNum = "";
		for(int j=0; j<15; j++)
		{
			int rNumCard = rNumGen.nextInt(10);
			tempCardNum = tempCardNum + rNumCard+"";
		}
		
		if(rNumIndex2 == 0)
		{
			tPrice = (FOOTBALLPRICE);
		}
		else if(rNumIndex2 == 1)
		{
			tPrice= (BASKETBALLPRICE);
		}
		else if(rNumIndex2 ==2)
		{
			tPrice = (BADMINTONPRICE);
		}
		else if(rNumIndex2==3 )
		{
			tPrice = (TABLETENNISPRICE);
		}
		else if(rNumIndex2==4)
		{
			tPrice = (TENNISPRICE);
		}
		
		bs.addToBookingList(testTemp);
		Payments pTestTemp = new Payments();
		 
		pTestTemp.cardNumber = (tempCardNum);
		pTestTemp.cardHolder = (listNames[rNumIndex]);
		pTestTemp.price =(tPrice);
		pTestTemp.bID = (i);
		
		bs.writeToBookingFile();
		pList.addToPaymentsList(pTestTemp);
		pList.writeToPaymentsFile();
	}
	final long endTime = System.currentTimeMillis();//ENDS A TIMER AND CALCULATES EXECUTION TIME OF THE METHOD
	float executionTime =  (endTime - startTime);
	System.out.println("Total execution time: " + (executionTime)+" Milliseconds OR "+(executionTime/1000)+" Seconds");//DISPLAYS EXECUTION TIME IN SECONDS AND MILLISECONDS

}

//LIST OF ACTIONS PERFORMED ON BUTTONS
public void actionPerformed(ActionEvent e)
{	//ALLOWS NAVIGATION TO CORRECT PAGES
	if(e.getSource()==btnStaffLogin)
	{
		 allTabs.setSelectedIndex(STAFFLOGINPAGE);
		 //stressTestAddingBooking();
	}
	if(e.getSource()==btnCustomerLogin)
	{
		 allTabs.setSelectedIndex(CUSTOMERLOGINPAGE);
		 
	}
	if(e.getSource()==btnLogin)
	{
		//OBTAINS DATA FROM THE TEXT FIELD
		 String passText = tfPassword.getText();
		 String userText = tfUsername.getText();		 
		 boolean staffCorrectLogin=false;
		 boolean partialCorrectLogin=false;
		 
		 
		 
/* 		 LOOP TO COMPARE DATA TO EACH INDEX OF THE LOGIN ARRAY
		 IF THEY MATCH THE PAGE WILL CHANGE TO MNEU SCREEN
		 OTHERWISE AN EROOR MESSAGE IS DISPLAYED */
		 
		 for(int i=0 ; i<logList.nextItemLocation ; i++)
		 {	 
			 if( (userText.equals(logList.loginDataArray[i].userName)) && (passText.equals(logList.loginDataArray[i].password)) )
			 {
				 staffCorrectLogin=true;
			 }
			 else if( (userText.equals(logList.loginDataArray[i].userName)) || (passText.equals(logList.loginDataArray[i].password)) )
			 {
				 partialCorrectLogin=true;
			 }
		 }
		 if(staffCorrectLogin==true)
		 {
			System.out.println("Correct username and password");//CHANGES PAGE TO SHOW LOGIN IS SUCCESSFUL
			System.out.println("You have logged in ");
			System.out.println(userText+":"+passText);
			allTabs.setSelectedIndex(STAFFMENUPAGE);
			clearFields();

		 }
		 else if(staffCorrectLogin==false)
		 {
			 if((userText.equals("")) && (passText.equals("")))
			 {
				 System.out.println("Incorrect");
				 System.out.println(userText+":"+passText);
				 JOptionPane.showMessageDialog(null, ("Please enter both username and password"));
			 }
			 else if(partialCorrectLogin==true)//IDENTIFIES PARTIALLY CORRECT LOGIN WHERE ONE COMPONENT MATCHES
			 {
				 System.out.println("Partially Incorrect");
				 System.out.println(userText+":"+passText);
				 JOptionPane.showMessageDialog(null, ("One of the components of the login is incorrect, try again"));
			 }
			 else
			 {
				 System.out.println("Incorrect");
				 System.out.println(userText+":"+passText);
				 JOptionPane.showMessageDialog(null, ("Incorrect username and password, try again"));
			 } 
		 }
		 

		 
	}
	
	
	
	if(e.getSource()==btnConfirmCustomerLogin)
	{
		 //OBTAINS DATA FROM THE TEXT FIELD
		 String customerPassText = tfCustomerPassword.getText();
		 String customerUserText = tfCustomerUsername.getText();	
		 boolean customerCorrectLogin=false;	
		 boolean partialCorrectCustomerLogin=false;
		 
/* 		 LOOP TO COMPARE DATA TO EACH INDEX OF THE LOGIN ARRAY
		 IF THEY MATCH THE PAGE WILL CHANGE TO MNEU SCREEN
		 OTHERWISE AN EROOR MESSAGE IS DISPLAYED */
		 for(int i=0 ; i<cLogList.nextItemLocation ; i++)
		 {	 
			 if( (customerUserText.equals(cLogList.customerLoginDataArray[i].customerUserName)) && (customerPassText.equals(cLogList.customerLoginDataArray[i].customerPassword)) )
			 {
				customerCorrectLogin=true;
			 }
			 else if( (customerUserText.equals(cLogList.customerLoginDataArray[i].customerUserName)) || (customerPassText.equals(cLogList.customerLoginDataArray[i].customerPassword)) )
			 {
				 partialCorrectCustomerLogin=true;
			 }
		 }	
		 if(customerCorrectLogin==true)//CHANGES PAGE TO SHOW LOGIN IS SUCCESSFUL
		 {
			 System.out.println("Correct username and password");
			 System.out.println("You have logged in ");
			 allTabs.setSelectedIndex(CUSTOMERMENUPAGE);
			 cbList.customerBookingFileName = (customerUserText+"Bookings.txt");//TEXT FILE FOR USER IS COMPRISED OF THEIR USERNAME
			 cpList.customerPaymentsFileName = (customerUserText+"Payments.txt");
			 cbList.readFromCustomerBookingFile();//READS FROM THE CUSTOMERS SPECIFIC TEXT FILES
			 cpList.readFromCustomerPaymentsFile();
			 clearFields2();
		 }
		 else if(customerCorrectLogin==false)//LOGIN UNSUCCESSFUL THEN IDENTIFIES TYPE OF ERROR
		 {
			 if((customerUserText.equals("")) && (customerPassText.equals("")))//VALIDATES NULL DATA INPUT
			 {
				 System.out.println("Incorrect");
				 System.out.println(customerUserText+":"+customerPassText);
				 JOptionPane.showMessageDialog(null, ("Please enter both username and password"));
			 }
			 else if(partialCorrectCustomerLogin==true)//IDENTIFIES PARTIALLY CORRECT LOGIN WHERE ONE COMPONENT MATCHES
			 {
				 System.out.println("Partially Incorrect");
				 System.out.println(customerUserText+":"+customerPassText);
				 JOptionPane.showMessageDialog(null, ("One of the components of the login is incorrect, try again"));
			 }
			 else
			 {
				 System.out.println("Incorrect");//NO MATCH THEREFORE BOTH INCORRECT
				 System.out.println(customerUserText+":"+customerPassText);
				 JOptionPane.showMessageDialog(null, ("Incorrect username and password, try again"));
			 } 
		 }
	}
	
	
	//NAVIGATION WHEN THESE BUTTONSS ARE PRESSED
	if(e.getSource()==btnLogout||e.getSource()==btnLogout2)
	{
		 System.out.println("You have logged out ");
		 allTabs.setSelectedIndex(HOMEPAGE);
	}	
	if(e.getSource()==btnBookings)
	{
		 System.out.println("Moved to Booking Page ");
		 allTabs.setSelectedIndex(STAFFBOOKINGPAGE);
		 clearBookingTable();//POPULATES VIEW BOOKINGS TABLE
		 populateBookingTable();
	}
	if(e.getSource()==btnAddBookings)
	{
		 System.out.println("Moved to Add Bookings Page ");
		 allTabs.setSelectedIndex(STAFFADDBOOKINGPAGE);
	}
	if(e.getSource()==btnSearchBookings)
	{
		 System.out.println("Moved to search Bookings Page ");
		 allTabs.setSelectedIndex(STAFFSEARCHBOOKINGPAGE);
	}
	if(e.getSource()==btnPayments)
	{
		 System.out.println("Moved to Payments Page ");
		 allTabs.setSelectedIndex(STAFFVIEWPAYMENTSPAGE);
		 clearPaymentTable();//POPULATES VIEW PAYMENTS TABLE
		 populatePaymentTable();
	}
	
	
	if(e.getSource()==btnBookings2)
	{
		 System.out.println("Moved to Booking Page ");
		 allTabs.setSelectedIndex(CUSTOMERBOOKINGPAGE);
		 clearCustomerBookingTable();//POPULATES VIEW CUSTOMER BOOKINGS TABLE
		 populateCustomerBookingTable();
	}
	if(e.getSource()==btnAddBookings2)
	{
		 System.out.println("Moved to Add Bookings Page ");
		 allTabs.setSelectedIndex(CUSTOMERADDBOOKINGPAGE);
	}
	if(e.getSource()==btnSearchBookings2)
	{
		 System.out.println("Moved to search Bookings Page ");
		 allTabs.setSelectedIndex(CUSTOMERSEARCHBOOKINGPAGE);
	}
	if(e.getSource()==btnPayments2)
	{
		 System.out.println("Moved to Payments Page ");
		 allTabs.setSelectedIndex(CUSTOMERVIEWPAYMENTSPAGE);
		 clearCustomerPaymentTable();//POPULATES VIEW CUSTOMER PAYMENTS TABLE
		 populateCustomerPaymentTable();
	}
	
	
	if(e.getSource()==btnDateFilter)
	{
		//GETS INDEX OF OPTION SELECTED 
		int cbxFilterBookingsIndex = cbxFilterBookings.getSelectedIndex();
		if(cbxFilterBookingsIndex == 0)
		{
			bs.bookingBubbleSortByName();//WILL RUN BUBBLE SORT BY NAME IF CHOSEN
			populateBookingTable();	//ALSO UPDATES TABLE TO SHOW NEW SORTED DATA	
		}
		else if (cbxFilterBookingsIndex==1)
		{
			bs.bookingBubbleSortByDateLatest();//WILL RUN BUBBLE SORT BY DATE
			populateBookingTable();	
		}
		else if (cbxFilterBookingsIndex==2)
		{
			bs.bookingBubbleSortByDateOldest();//WILL RUN BUBBLE SORT BY DATE
			populateBookingTable();			
		
		}
	
	}
	//TO DELETE BOOKINGS FROM THE FILE
	if(e.getSource()==btnDeleteBooking)
	{
		bs.readFromBookingFile();

		//GETS INDEX OF ROW SELECTED 
		int deletedIndex = bookingTable.getSelectedRow();
		if (deletedIndex == (-1))//IF A ROW ISNT SELECTED THE INDEX BECOME '-1'
		{
			JOptionPane.showMessageDialog(null, ("Error, please select a booking to cancel"));
		}
		else
		{	//DISPLAYS A JOPTIONPANE THAT HAS A YES/NO BUTTON OPTION
			int dialogButton = JOptionPane.YES_NO_OPTION;
			JOptionPane.showConfirmDialog (null, "Are you sure you want to cancel this booking?","Warning",dialogButton);//WARNING DISPLAYED ON POPUP
			if (dialogButton == JOptionPane.YES_OPTION)
			{
				System.out.println("Deleting Record: "+bs.bookingsArray[deletedIndex]);
				bs.bookingsArray[deletedIndex]=null;//SETS VALUE OF ARRAY INDEX TO NULL
				bs.writeToBookingFile();//WRITES TO FILE WITHOUT THE NULL DATA
				bs.readFromBookingFile();	
				clearBookingTable();//UPDATES THE TABLE 
				populateBookingTable();	
				JOptionPane.showMessageDialog(null, ("Booking cancelled"));
			}
			else 
			{
				System.out.println("NOT DELETING!!!!!!! ");
				allTabs.setSelectedIndex(STAFFBOOKINGPAGE);
			}
			
			
		}
	
	}	
	if(e.getSource()==btnConfirm)
	{
		 //OBTAINS DATA FROM TEXT FIELDS AND COMBOBOXES
		 String nameText = tfCustomerFirstName.getText() +" "+ tfCustomerName.getText();
		 String mobileText = tfCustomerMobile.getText();
		 String emailText = tfCustomerEmail.getText();
		 String cbxRoomVal= (String) cbxRoom.getSelectedItem();
		 String cbxActivityVal= (String) cbxActivity.getSelectedItem();
		 String cbxDateVal= (String) cbxBookingDate.getSelectedItem();
		 String cbxTimeVal = (String) cbxBookingTime.getSelectedItem();
		 boolean validPhoneNum = true;
		 for (int i=0; i<mobileText.length();i++)
		 {
			 try//TRIES TO CONVERT EACH CHARACTER OF MOBILE NUM TO AN INTEGER
			 {
				char mobTextChar = mobileText.charAt(i);
				int convMobChar = Integer.parseInt(mobTextChar+"");
				//System.out.println(convMobChar);
			 } 
			 catch (NumberFormatException exc)
			 {

				validPhoneNum = false;//SET FALSE IF CONTAINS A LETTER
				
			 }
		 }
		 
		 if (nameText.equals("")==true || mobileText.equals("")==true || emailText.equals("")==true )
		 {//DISPLAYS ERROR IF DATA IS NULL
			 System.out.println("Error, please fill in all boxes");
			 JOptionPane.showMessageDialog(null, ("Error, please fill in all boxes"));
			 allTabs.setSelectedIndex(STAFFADDBOOKINGPAGE);
			 
		 }		 
		 else if (emailText.contains("@")==false)
		 {//DISPLAYS ERROR IF THE SYBOL IS NOT FOUND
			 System.out.println("Error, please enter a valid email address.");
			 JOptionPane.showMessageDialog(null, ("Error, please enter a valid email address."));
			 allTabs.setSelectedIndex(STAFFADDBOOKINGPAGE);
		 }
		 else if(cbxRoomVal.equals("Please Select One ")||cbxActivityVal.equals("Please Select One ")||cbxDateVal.equals("Please Select One ")||cbxTimeVal.equals("Please Select One "))
		 {//DSIPLAYS ERROR IF THE COMBOBOXES REMAIN UNSELECTED
			 System.out.println("Error, please select an option for one of the drop down menus.");
			 JOptionPane.showMessageDialog(null, ("Error, please select an option for one of the drop down menus."));
			 
		 }
		 else if ((mobileText.length()) !=11)
		 {//DISPLAYS ERROR IF MOBILE LENGTH IS NOT 11 CHARACTERS LONG
			 System.out.println("Error, phone number not correct length.");
			 System.out.println("Phone number length: "+(mobileText.length()));
			 JOptionPane.showMessageDialog(null, ("Error, phone number not correct length."));
			 allTabs.setSelectedIndex(STAFFADDBOOKINGPAGE);
		 }
		 else if (validPhoneNum == false)
		 {
			 System.out.println("Error, please a valid phone number");
			 JOptionPane.showMessageDialog(null, ("Error, please a valid phone number"));
			 allTabs.setSelectedIndex(STAFFADDBOOKINGPAGE);
		 }

		 else 
		 {
			Booking temp = new Booking();//CREATES OBJECT OF RELEVANT CLASSES
			CustomerInformation tempC = new CustomerInformation();
			//DETERMINES PRICE OF BOOKINGS DEPENDING ON CHOICE OF ACTIVITY
			if(cbxActivityVal.equals("Football"))
			{
				tempPriceVal = (FOOTBALLPRICE);
			}
			else if(cbxActivityVal.equals("Basketball"))
			{
				tempPriceVal= (BASKETBALLPRICE);
			}
			else if(cbxActivityVal.equals("Badminton"))
			{
				tempPriceVal = (BADMINTONPRICE);
			}
			else if(cbxActivityVal.equals("Table Tennis"))
			{
				tempPriceVal = (TABLETENNISPRICE);
			}
			else if(cbxActivityVal.equals("Tennis"))
			{
				tempPriceVal = (TENNISPRICE);
			}
			//STORES INPUT DATA INTO TEMORARY OBJECTS OF CLASSES
			temp.bookingID = ((bk.bookingID+1));
			temp.name = nameText;
			temp.date = cbxDateVal;
			temp.time = cbxTimeVal;
			temp.room = cbxRoomVal;
			temp.activity = cbxActivityVal;
			
			tempC.cName = nameText;
			tempC.phoneNum = mobileText;
			tempC.email = emailText;
			//PASSES DATA FROM OBJECT INTO THIS METHOD
			
			//SETS THE LABEL TEXT ON THE PANEL TO SHOW THE PRICE
			lblTotalPayment.setText("Total Payment:     £"+tempPriceVal );
			//PASSES DATA INTO THE METHOD 
			boolean booked = false; 
			for (int i=0; i<bs.nextItemLocation;i++)
			{
				if((bs.bookingsArray[i].date).equals(temp.date) && (bs.bookingsArray[i].time).equals(temp.time)&& (bs.bookingsArray[i].room).equals(temp.room))
				{
					//System.out.println("SAME BOOKING DATA!!!!!!");
					booked = true;
				}
			}
			if(booked == true)
			{
				JOptionPane.showMessageDialog(null, ("Error, this date and time has been booked already"+"\n"+" please choose another date/time slot"));
			}
			else
			{
				ciList.addToCustomerInfoList(tempC);
				bs.addToBookingList(temp);
				//SETS PANEL TO VISIBLE AND SWITCHES TO THE PAYMENTS PANEL
				paymentsPanel.setVisible(true);
				allTabs.setSelectedIndex(STAFFPAYMENTSPAGE);				
			}

		 }
		 
	}	
	if(e.getSource()==btnPay)
	{
		//OBTAINS DATA FROM INPUT
		String cardNumberText = tfCardNumber.getText();
		String cardHolderText = tfCardHolder.getText();
		boolean validCardNum = true;
		 //VALIDATION CHECK TO CONVERT EACH CHARACTER INTO AN INTEGER
		 for (int i=0; i<cardNumberText.length();i++)
		 {
			 try
			 {
				char cardTextChar = cardNumberText.charAt(i);
				
				int convChar = Integer.parseInt(cardTextChar+"");
				//System.out.println(convChar);
				
			 } 
			 catch (NumberFormatException exc)
			 {
				validCardNum = false;
			 }
		 }//VALIDATION - RUNS PRESENCE CHECK
		 if (cardNumberText.isEmpty() ||cardHolderText.isEmpty())
		 {
			 System.out.println("Error, please fill in all fields.");
			 JOptionPane.showMessageDialog(null, ("Error, please fill in all fields."));
		 }
		 //VALIDATION - LENGTH CHECK ON CARD NUMBER 
		 else if ((cardNumberText.length()) !=16)
		 {
			 System.out.println("Error, card number not correct length.");
			 System.out.println("Card number length: "+(cardNumberText.length()));
			 JOptionPane.showMessageDialog(null, ("Error, card number not correct length."));
		
		 }
		 
		 else if (validCardNum == false)
		 {
			 System.out.println("Error, please a valid card number");
			 JOptionPane.showMessageDialog(null, ("Error, please a valid card number"));			 
		 }
		 else 
		 {
			//CREATES OBJECT OF PAYMENTS AND STORES THE DATA INTO IT
			Payments pTemp = new Payments();
			 
			pTemp.cardNumber = cardNumberText;
			pTemp.cardHolder = cardHolderText;
			pTemp.price =tempPriceVal;
			pTemp.bID = (bk.bookingID+1);
			//ALL FILES ARE WRITTEN TO
			bs.writeToBookingFile();
			pList.addToPaymentsList(pTemp);
			pList.writeToPaymentsFile();
			ciList.writeToCustomerInfoFile();
			
			
			JOptionPane.showMessageDialog(null, ("Booking Added"));
			JOptionPane.showMessageDialog(null, ("Payment Made of: £ "+pTemp.price));

			//RESETS COMBOBOXES AND CLEAR TEXT FIELDS
			cbxBookingTime.setSelectedIndex(0);
			cbxBookingDate.setSelectedIndex(0);
			cbxActivity.setSelectedIndex(0);
			cbxRoom.setSelectedIndex(0);
			clearFields();
			allTabs.setSelectedIndex(STAFFMENUPAGE);
		 			 
		 }
		 

		 
	}
//CANCELS BOOKING BEFORE MAKING THE PAYMENT AND NAVIGATES TO MENU
	if(e.getSource()==btnCancelBooking)
	{
		System.out.println("Booking cancelled ");
		clearFields();
		bs.readFromBookingFile();
		allTabs.setSelectedIndex(STAFFMENUPAGE);
		
	}
	
	
	if(e.getSource()==btnCustomerDateFilter)
	{//GETS INDEX OF OPTION SELECTED 
		int cbxCustomerFilterBookingsIndex = cbxCustomerFilterBookings.getSelectedIndex();

		if (cbxCustomerFilterBookingsIndex==0)
		{
			cbList.bookingCustomerBubbleSortByDateLatest();
			populateCustomerBookingTable();	
		}
		else if (cbxCustomerFilterBookingsIndex==1)
		{
			cbList.bookingCustomerBubbleSortByDateOldest();
			populateCustomerBookingTable();			
		
		}
	
	}
	//TO DELETE BOOKINGS FROM THE FILE
	if(e.getSource()==btnCustomerDeleteBooking)
	{
		//GETS INDEX OF ROW SELECTED
		int customerDeletedIndex = customerBookingTable.getSelectedRow();
		if (customerDeletedIndex == (-1))//IF A ROW ISNT SELECTED THE INDEX BECOME '-1'
		{
			JOptionPane.showMessageDialog(null, ("Error, please select a booking to cancel"));
		}
		else
		{//DISPLAYS A JOPTIONPANE HAT HAS A YES/NO BUTTON 
			int customerDialogButton = JOptionPane.YES_NO_OPTION;
			JOptionPane.showConfirmDialog (null, "Are you sure you want to cancel this booking?","Warning",customerDialogButton);
			if (customerDialogButton == JOptionPane.YES_OPTION)
			{
				System.out.println("Deleting Record: "+cbList.customerBookingsArray[customerDeletedIndex]);
				cbList.customerBookingsArray[customerDeletedIndex]=null;//SETS VALUE OF ARRAY INDEX TO NULL
				cbList.writeToCustomerBookingFile();//WRITES TO FILE WITHOUT THE NULL DATA
				cbList.readFromCustomerBookingFile();	
				clearCustomerBookingTable(); 
				populateCustomerBookingTable();	
				JOptionPane.showMessageDialog(null, ("Booking cancelled"));
			}
			else if (customerDialogButton == JOptionPane.NO_OPTION)
			{
				allTabs.setSelectedIndex(CUSTOMERBOOKINGPAGE);
			}
			
			
		}
	
	}
/* 	//WHEN PRESSED IT RUNS MULITPLE VALIDATION 
	CHECKS BEFORE DECIDING THE PRICE OF THE BOOKING */
	if(e.getSource()==btnConfirm2)
	{//OBTAINS DATA FROM TEXT FIELDS AND COMBOBOXES
		 String nameText = tfCustomerFirstName2.getText() +" "+ tfCustomerName2.getText();
		 String mobileText = tfCustomerMobile2.getText();
		 String emailText = tfCustomerEmail2.getText();
		 
		 String cbxRoomVal= (String) cbxRoom2.getSelectedItem();
		 String cbxActivityVal= (String) cbxActivity2.getSelectedItem();
		 String cbxDateVal= (String) cbxBookingDate2.getSelectedItem();
		 String cbxTimeVal = (String) cbxBookingTime2.getSelectedItem();
		 boolean validPhoneNum = true;
/* 		 RUNS VARIOUS VALIDATION CHECKS ON THE USER'S INPUT
		 BEFORE ALLOWING DATA TO BE STORED INTO THE TEXT FILE  */
		 for (int i=0; i<mobileText.length();i++)
		 {
			 try//TRIES TO CONVERT EACH CHARACTER OF INDEX TO AN INTEGER
			 {
				char mobTextChar = mobileText.charAt(i);
				int convMobChar = Integer.parseInt(mobTextChar+"");
				//System.out.println(convMobChar);
			 } 
			 catch (NumberFormatException exc)
			 {

				validPhoneNum = false;
				
			 }
		 }
		 
		 if (nameText.equals("")==true || mobileText.equals("")==true || emailText.equals("")==true )
		 {
			 System.out.println("Error, please fill in all boxes");
			 JOptionPane.showMessageDialog(null, ("Error, please fill in all boxes"));
			 allTabs.setSelectedIndex(CUSTOMERADDBOOKINGPAGE);
			 
		 }		 
		 else if (emailText.contains("@")==false)
		 {
			 System.out.println("Error, please enter a valid email address.");
			 JOptionPane.showMessageDialog(null, ("Error, please enter a valid email address."));
			 allTabs.setSelectedIndex(CUSTOMERADDBOOKINGPAGE);
		 }
		 else if(cbxRoomVal.equals("Please Select One ")||cbxActivityVal.equals("Please Select One ")||cbxDateVal.equals("Please Select One ")||cbxTimeVal.equals("Please Select One "))
		 {
			 System.out.println("Error, please select an option for one of the drop down menus.");
			 JOptionPane.showMessageDialog(null, ("Error, please select an option for one of the drop down menus."));
			 
		 }
		 else if ((mobileText.length()) !=11)
		 {
			 System.out.println("Error, phone number not correct length.");
			 System.out.println("Phone number length: "+(mobileText.length()));
			 JOptionPane.showMessageDialog(null, ("Error, phone number not correct length."));
			 allTabs.setSelectedIndex(CUSTOMERADDBOOKINGPAGE);
		 }
		 else if (validPhoneNum == false)
		 {
			 System.out.println("Error, please a valid phone number");
			 JOptionPane.showMessageDialog(null, ("Error, please a valid phone number"));
			 allTabs.setSelectedIndex(CUSTOMERADDBOOKINGPAGE);
		 }

		 else 
		 {//CREATES OBJECT OF RELEVANT CLASSES
			Booking temp = new Booking();
			CustomerInformation tempC = new CustomerInformation();
			//DETERMINES PRICE OF BOOKINGS DEPENDING ON CHOICE OF ACTIVITY
			if(cbxActivityVal.equals("Football"))
			{
				tempPriceVal = (FOOTBALLPRICE);
			}
			else if(cbxActivityVal.equals("Basketball"))
			{
				tempPriceVal= (BASKETBALLPRICE);
			}
			else if(cbxActivityVal.equals("Badminton"))
			{
				tempPriceVal = (BADMINTONPRICE);
			}
			else if(cbxActivityVal.equals("Table Tennis"))
			{
				tempPriceVal = (TABLETENNISPRICE);
			}
			else if(cbxActivityVal.equals("Tennis"))
			{
				tempPriceVal = (TENNISPRICE);
			}
			//STORES INPUT DATA INTO AN OBJECT
			temp.bookingID = (bs.nextItemLocation);
			temp.name = nameText;
			temp.date = cbxDateVal;
			temp.time = cbxTimeVal;
			temp.room = cbxRoomVal;
			temp.activity = cbxActivityVal;
			//PASSES DATA FROM OBJECT INTO THIS METHOD
			tempC.cName = nameText;
			tempC.phoneNum = mobileText;
			tempC.email = emailText;
			
			boolean booked = false; 
			for (int i=0; i<bs.nextItemLocation;i++)
			{
				if((bs.bookingsArray[i].date).equals(temp.date) && (bs.bookingsArray[i].time).equals(temp.time)&& (bs.bookingsArray[i].room).equals(temp.room))
				{
					
					booked = true;
				}
			}
			if(booked == true)
			{
				JOptionPane.showMessageDialog(null, ("Error, this date and time has been booked already"+"\n"+" please choose another date/time slot"));
			}
			else
			{
				//ciList.addToCustomerInfoList(tempC);
				
				
				lblTotalPayment2.setText("Total Payment:     £"+tempPriceVal );
				
				bs.addToBookingList(temp);
				cbList.addToCustomerBookingList(temp);
				//SETS PANEL TO VISIBLE AND SWITCHES TO THE PANEL
				customerPaymentsPanel.setVisible(true);
				allTabs.setSelectedIndex(CUSTOMERPAYMENTSPAGE);				
			}
			
	

		 }
		 
	}	
/* 	WHEN PRESSED THE DATA IS CHECKED THROUGH DIFFERENT VALIDATION 
	IF THE DATA IS LEGAL THE DATA IS STORED IN AN ONJECT AND PASSED INTO THER ADD LIST METHOD
	AS A PARAMETER, THIS DATA IS THEN WRITTEN TO THE TEXT FILE */
	if(e.getSource()==btnPay2)
	{//OBTAINS DATA FROM INPUT
		String cardNumberText = tfCardNumber2.getText();
		String cardHolderText = tfCardHolder2.getText();
		boolean validCardNum = true;
		 //VALIDATION CHECK TO CONVERT EACH CHARACTER INTO AN 
		 for (int i=0; i<cardNumberText.length();i++)
		 {
			 try
			 {
				char cardTextChar = cardNumberText.charAt(i);
				
				int convChar = Integer.parseInt(cardTextChar+"");
				//System.out.println(convChar);
				
			 } 
			 catch (NumberFormatException exc)
			 {
				validCardNum = false;
			 }
		 }//VALIDATION - RUNS PRESENCE CHECK
		 if (cardNumberText.isEmpty() ||cardHolderText.isEmpty())
		 {
			 System.out.println("Error, please fill in all fields.");
			 JOptionPane.showMessageDialog(null, ("Error, please fill in all fields."));
		 }//VALIDATION - LENGTH CHECK ON CARD NUMBER 
		 else if ((cardNumberText.length()) !=16)
		 {
			 System.out.println("Error, card number not correct length.");
			 System.out.println("Card number length: "+(cardNumberText.length()));
			 JOptionPane.showMessageDialog(null, ("Error, card number not correct length."));
		
		 }
		 else if (validCardNum == false)
		 {
			 System.out.println("Error, please a valid card number");
			 JOptionPane.showMessageDialog(null, ("Error, please a valid card number"));			 
		 }
		 else 
		 {//CREATES OBJECT OF PAYMENTS AND STORES THE DATA INTO IT
			Payments pTemp = new Payments();
			 
			pTemp.cardNumber = cardNumberText;
			pTemp.cardHolder = cardHolderText;
			pTemp.price =tempPriceVal;
			pTemp.bID = (bs.nextItemLocation);
			//ALL FILES ARE WRITTEN TO
			bs.writeToBookingFile();
			pList.addToPaymentsList(pTemp);
			pList.writeToPaymentsFile();
			ciList.writeToCustomerInfoFile();
			//WRTIES TO SPECIFC CUSTOMER FILE AS WELL AS GENERAL FILES
			cbList.writeToCustomerBookingFile();
			cpList.addToCustomerPaymentsList(pTemp);
			cpList.writeToCustomerPaymentsFile();
			//POPUPS DISPLAYED TO USER TO CONFIRM BOOKING AND PAYMENT
			JOptionPane.showMessageDialog(null, ("Booking Added"));
			JOptionPane.showMessageDialog(null, ("Payment Made of: £ "+pTemp.price));
			//RESETS COMBOBOXES AND CLEAR TEXT FIELDS
			cbxBookingTime2.setSelectedIndex(0);
			cbxBookingDate2.setSelectedIndex(0);
			cbxActivity2.setSelectedIndex(0);
			cbxRoom2.setSelectedIndex(0);
			clearFields2();
			allTabs.setSelectedIndex(CUSTOMERMENUPAGE);
		 			 
		 }
		 

		 
	}
//CANCELS BOOKING BEFORE MAKING THE PAYMENT AND NAVIGATES TO MENU
	if(e.getSource()==btnCancelBooking2)
	{
		System.out.println("Booking cancelled ");
		clearFields2();
		bs.readFromBookingFile();
		allTabs.setSelectedIndex(CUSTOMERMENUPAGE);
		
	}
	
//MENU BUTTON NAVIGATIONS FOR BOTH STAFF AND CUSTOMER MENU 
	if(e.getSource()==btnMenu1||e.getSource()==btnMenu3||e.getSource()==btnMenu5||e.getSource()==btnMenu9)
	{
		 System.out.println("Moved to Home ");
		 allTabs.setSelectedIndex(STAFFMENUPAGE);
	}
	if(e.getSource()==btnMenu2||e.getSource()==btnMenu4||e.getSource()==btnMenu6||e.getSource()==btnMenu10)
	{
		 System.out.println("Moved to Home ");
		 allTabs.setSelectedIndex(CUSTOMERMENUPAGE);
	}	
	//RUNS SEARCH FOR BOOKING USING ID
	if(e.getSource()==btnSearchByID)
	{
		 String bookingIDText = tfSearchByID.getText();
		 boolean integerValue = true;
		 try//VALIDATION ENSURES ID IS AN INTEGER
		 {
			idSearchVal = Integer.parseInt(bookingIDText);
			
		 } 
		 catch (NumberFormatException exc)
		 {
			System.out.println("Error, please enter a search value");
			exc.printStackTrace();
			allTabs.setSelectedIndex(STAFFSEARCHBOOKINGPAGE);
			integerValue = false;
			
		 }
		 //VALIDATES FOR NULL INPUT
		 if (bookingIDText.isEmpty()==true )
		 {
			 System.out.println("Error, please enter a search value");
			 JOptionPane.showMessageDialog(null, ("Error, please enter a search value"));
		 }
		 else if (integerValue == false)
		 {
			 System.out.println("Error, please enter a suitable search value");
			 JOptionPane.showMessageDialog(null, ("Error, please enter a suitable search Booking ID"));			 
		 }
		 else
		 {//SEARCH IS RUN WITH ID PASSED AS A PARAMETER
			 System.out.println("Searching for Booking with ID: "+bookingIDText);
			 bs.searchBookingID(idSearchVal);
			 searchScroll.setVisible(true);//SETS TEXT AREA VISIBLE
			 jtaSearchResults.setText(bs.idSearchResults);//SETS TEXT TO A STRING FOUND ANOTHER CLASS
			
		 }
	}
	//RUNS SEARCH FOR BOOKING USING DATE
	if(e.getSource()==btnSearchByDate)
	{
		 
		 
		 String bookingDateText = tfSearchByDate.getText();
		//VALIDATION FOR DATE
		 if (bookingDateText.isEmpty()==true )
		 {
			 System.out.println("Error, please enter a search value");
			 JOptionPane.showMessageDialog(null, ("Error, please enter a search value"));
		 }
		 else if (bookingDateText.contains("/")==false)//FORMAT CHECK
		 {
			 System.out.println("Error, please enter a valid Date");
			 JOptionPane.showMessageDialog(null, ("Error, please enter a valid Date"+"\n"+" in the format dd/mm/yy "));			 
		 }
		 else
		 {//RUNS SEARCH WITH DATE AS A PARAMETER
			 System.out.println("Searching for Booking with Date: "+bookingDateText);
			 bs.searchBookingDate(bookingDateText);
			 searchScroll.setVisible(true);//DISPLAYS TEXT OF RESULTS
			 jtaSearchResults.setText(bs.dateSearchResults);
			
		 }		 

	}
/* 	RUNS SEARCH FOR BOOKING USING NAME
	IT GETS THE SEARCH INPUT AND PASSES THE DATA
	AS A PARAMETER INTO THE SEARCH METHOD */
	if(e.getSource()==btnSearchByName)
	{
		 
		 String bookingNameText = tfSearchByName.getText();

		 
		 if (bookingNameText.isEmpty()==true )
		 {
			 System.out.println("Error, please enter a search value");
			 JOptionPane.showMessageDialog(null, ("Error, please enter a search value"));
		 }
		 else
		 {//RUNS SEARCH USING NAEMAS A PARAMETER
			 System.out.println("Searching for Booking with Name: "+bookingNameText);
			 bs.searchBookingName(bookingNameText);
			 searchScroll.setVisible(true);//DISPLAYS RESULTS IN TEXT AREA
			 jtaSearchResults.setText(bs.nameSearchResults);
			 
		 }
		 
	}
// ALL THE SEARCHES FOR CUSTOMER BOOKINGS 
	if(e.getSource()==btnSearchByID2)
	{
		 String bookingIDText = tfSearchByID2.getText();
		 boolean integerValue = true;
		 try//VALIDATION FOR ID
		 {
			idSearchVal = Integer.parseInt(bookingIDText);
			
		 } 
		 catch (NumberFormatException exc)
		 {
			System.out.println("Error, please enter a search value");
			exc.printStackTrace();
			allTabs.setSelectedIndex(CUSTOMERSEARCHBOOKINGPAGE);
			integerValue = false;
			
		 }
		 
		 if (bookingIDText.isEmpty()==true )
		 {
			 System.out.println("Error, please enter a search value");
			 JOptionPane.showMessageDialog(null, ("Error, please enter a search value"));
		 }
		 else if (integerValue == false)
		 {
			 System.out.println("Error, please enter a suitable search value");
			 JOptionPane.showMessageDialog(null, ("Error, please enter a suitable search Booking ID"));			 
		 }
		 else
		 {//RUNS SEARCH BUT ON CUSTOMER SPECIFIC DATA
			 System.out.println("Searching for Booking with ID: "+bookingIDText);
			 cbList.searchCustomerBookingID(idSearchVal);
			 customerSearchScroll.setVisible(true);
			 jtaCustomerSearchResults.setText(cbList.idCustomerSearchResults);
			
		 }
	}
	if(e.getSource()==btnSearchByDate2)
	{
		 
		 
		 String bookingDateText = tfSearchByDate2.getText();
			//VALIDATION FOR DATE
		 if (bookingDateText.isEmpty()==true )
		 {
			 System.out.println("Error, please enter a search value");
			 JOptionPane.showMessageDialog(null, ("Error, please enter a search value"));
		 }
		 else if (bookingDateText.contains("/")==false)
		 {
			 System.out.println("Error, please enter a valid Date");
			 JOptionPane.showMessageDialog(null, ("Error, please enter a valid Date"+"\n"+" in the format dd/mm/yy "));			 
		 }
		 else
		 {//RUNS SEARCH BUT ON CUSTOMER SPECIFIC DATA
			 System.out.println("Searching for Booking with Date: "+bookingDateText);
			 cbList.searchCustomerBookingDate(bookingDateText);
			 customerSearchScroll.setVisible(true);
			 jtaCustomerSearchResults.setText(cbList.dateCustomerSearchResults);
			
		 }		 

	}
	if(e.getSource()==btnSearchByName2)
	{
		//VALIDATION FOR NAME
		 String bookingNameText = tfSearchByName2.getText();

		 
		 if (bookingNameText.isEmpty()==true )
		 {
			 System.out.println("Error, please enter a search value");
			 JOptionPane.showMessageDialog(null, ("Error, please enter a search value"));
		 }
		 else
		 {//RUNS SEARCH BUT ON CUSTOMER SPECIFIC DATA
			 System.out.println("Searching for Booking with Name: "+bookingNameText);
			 cbList.searchCustomerBookingName(bookingNameText);
			 customerSearchScroll.setVisible(true);
			 jtaCustomerSearchResults.setText(cbList.nameCustomerSearchResults);
			 
		 }
		 
	}
	



//[EndActions]
}

 
public static void main(String[] args )
{
	SystemGUI gd = new SystemGUI();
	gd.startGUI();//RUNS THE CREATE GUI METHOD IN MAIN METHOD
	
}

}  