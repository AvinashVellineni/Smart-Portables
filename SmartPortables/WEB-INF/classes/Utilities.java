import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	/*public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}*/
	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				//result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result + "<li><a href='ViewOrder'><span class='avicl'>ViewOrder</span></a></li>"
						+ "<li><a><span class='avicl'>"+username+"</span></a></li>"
						+ "<li><a href='Account'><span class='avicl'>Account</span></a></li>";
				if(session.getAttribute("usertype").equals("manager")){
					result=result + "<li><a href='Registration'><span class='avicl'>AddCustomer</span></a></li>";
				}
				if(session.getAttribute("usertype").equals("retailer"))
				{
					result = result + "<li><a href='ProductModify?button=Addproduct'><span class='avicl'>Add product</span></a></li>"
						+ "<li style='background-color: black !important;'><a href='ProductModify?button=Updateproduct'><span class='avicl'>Update product</span></a></li>"
						+"<li style='background-color: black !important;'><a href='ProductModify?button=Deleteproduct'><span class='avicl'>Delete product</span></a></li>"
						+"<li style='background-color: black !important;'><a href='DataAnalytics'><span class='avicl'>Data Analytics</span></a></li>"
						+"<li style='background-color: black !important;'><a href='DataVisualization'><span class='avicl'>Data Visualization</span></a></li>"
						+"<li class='' style='background-color: black !important;'><div class='dropdown'><a class='avicl'>Sales Report</a><div class='dropdown-content' style='border-bottom: 1px solid black'><a href='SalesReport?button=ProductAvailability'><span class='avicl'>Product Availability</span></a><a href='SalesReport?button=GraphicalView'><span class='avicl'>Graphical View</span></a><a href='SalesReport?button=SalesByDate'><span class='avicl'>Sales By Date</span></a></div></div></li>"
						+"<li class='' style='background-color: black !important;'><div class='dropdown'><a class='avicl'>Inventory Report</a><div class='dropdown-content' style='border-bottom: 1px solid black'><a href='InventoryReport?button=ProductsInventory'><span class='avicl'>Products Inventory</span></a><a href='InventoryReport?button=GraphicalView'><span class='avicl'>Graphical View</span></a><a href='InventoryReport?button=AllProductsOnSale'><span class='avicl'>Products On Sale</span></a><a href='InventoryReport?button=ManufacturerRebates'><span class='avicl'>Manufacturer Rebates</span></a></div></div></li>";
				}
				result=result + "<li style='background-color: black !important;'><a href='Logout'><span class='avicl'>Logout</span></a></li>";
			}
			else
				result = result +"<li style='background-color: black !important;'><a href='ViewOrder'><span class='avicl'>ViewOrder</span></a></li>"+ "<li><a href='Login'><span class='avicl'>Login</span></a></li>";
				result = result +"<div align='right'>"+"<li style='background-color: black !important;'><a href='Cart'><span class='glyphicon glyphicon-shopping-cart'>("+CartCount()+")</span></a></li></div></ul></nav><div id='page' style='margin-top: 15px;'>";
				/*
				if (session.getAttribute("username")!=null){
					if(session.getAttribute("usertype").equals("retailer")){
						result=result+"<nav><li><a href='AddCustomer'><span class='avicl'>AddProduct</span></a></li></nav><div id='page'>";
					}
				}

				else{
					result=result+"<div id='page'>";
				}*/
				pw.print(result);
		} else
				pw.print(result);
	}

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
				FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\UserDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				hm= (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	public void removeOrder(int i){
		ArrayList<OrderItem> allorder= getCustomerOrders();
			System.out.println(i);
		 allorder.remove(i);
		 return;
	}


	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
		int size=0;
			try
			{
				orderPayments =MySqlDataStoreUtilities.selectOrder();
			}
			catch(Exception e)
			{
			
			}
			
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 size=entry.getKey();
					 
			}
			return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	/*public void removeProduct(String name,String type){
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(type.equals("laptops")){
			Laptop laptop = null;
			laptop = SaxParserDataStore.laptops.get(name);
			OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer());
			orderItems.remove(orderitem);
		}

	}*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());

		HashMap<String,Phone> allphones = new HashMap<String,Phone> ();
		HashMap<String,Speaker> allspeakers = new HashMap<String,Speaker> ();
		HashMap<String,Laptop> alllaptops = new HashMap<String,Laptop> ();
		HashMap<String,FitnessWatch> allfitnesswatches=new HashMap<String,FitnessWatch>();
		HashMap<String,SmartWatch> allsmartwatches = new HashMap<String,SmartWatch> ();
		HashMap<String,HeadPhone> allheadphones = new HashMap<String,HeadPhone> ();
		HashMap<String,VirtualReality> allvirtualrealities = new HashMap<String,VirtualReality> ();
		HashMap<String,Tracker> alltrackers=new HashMap<String,Tracker>();
		HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();


		if(type.equals("consoles")){
			Console console;
			console = SaxParserDataStore.consoles.get(name);
			OrderItem orderitem = new OrderItem(console.getName(), console.getPrice(), console.getImage(), console.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("games")){
			Game game = null;
			game = SaxParserDataStore.games.get(name);
			OrderItem orderitem = new OrderItem(game.getName(), game.getPrice(), game.getImage(), game.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("tablets")){
			Tablet tablet = null;
			tablet = SaxParserDataStore.tablets.get(name);
			OrderItem orderitem = new OrderItem(tablet.getName(), tablet.getPrice(), tablet.getImage(), tablet.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("phones")){
			Phone phone = null;
			try{
			allphones = MySqlDataStoreUtilities.getPhones();			
			}
			catch(Exception e){				
			}
			phone = allphones.get(name);
			OrderItem orderitem = new OrderItem(phone.getName(), phone.getPrice(), phone.getImage(), phone.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("speakers")){
			Speaker speaker = null;
			try{
			allspeakers = MySqlDataStoreUtilities.getSpeakers();			
			}
			catch(Exception e){				
			}
			speaker = allspeakers.get(name);
			OrderItem orderitem = new OrderItem(speaker.getName(), speaker.getPrice(), speaker.getImage(), speaker.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("laptops")){
			Laptop laptop = null;
			try{
			alllaptops = MySqlDataStoreUtilities.getLaptops();			
			}
			catch(Exception e){				
			}
			laptop = alllaptops.get(name);
			OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("fitnesswatches")){			
			FitnessWatch fitnesswatch = null;
			try{
			allfitnesswatches = MySqlDataStoreUtilities.getFitnessWatches();			
			}
			catch(Exception e){				
			}
			fitnesswatch = allfitnesswatches.get(name);
			OrderItem orderitem = new OrderItem(fitnesswatch.getName(), fitnesswatch.getPrice(), fitnesswatch.getImage(), fitnesswatch.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("smartwatches")){
			SmartWatch smartwatch = null;
			try{
			allsmartwatches = MySqlDataStoreUtilities.getSmartWatches();			
			}
			catch(Exception e){				
			}
			smartwatch = allsmartwatches.get(name);
			OrderItem orderitem = new OrderItem(smartwatch.getName(), smartwatch.getPrice(), smartwatch.getImage(), smartwatch.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("headphones")){
			try{
			allheadphones = MySqlDataStoreUtilities.getHeadPhones();			
			}
			catch(Exception e){				
			}
			HeadPhone headphone = null;
			headphone = allheadphones.get(name);
			OrderItem orderitem = new OrderItem(headphone.getName(), headphone.getPrice(), headphone.getImage(), headphone.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("virtualrealities")){
			VirtualReality virtualreality = null;
			try{
			allvirtualrealities = MySqlDataStoreUtilities.getVirtualReality();			
			}
			catch(Exception e){				
			}
			virtualreality = allvirtualrealities.get(name);
			OrderItem orderitem = new OrderItem(virtualreality.getName(), virtualreality.getPrice(), virtualreality.getImage(), virtualreality.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("trackers")){
			Tracker tracker = null;
			try{
			alltrackers = MySqlDataStoreUtilities.getTrackers();			
			}
			catch(Exception e){				
			}
			tracker = alltrackers.get(name);
			OrderItem orderitem = new OrderItem(tracker.getName(), tracker.getPrice(), tracker.getImage(), tracker.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("accessories")){	
			Accessory accessory = null;
			try{
			allaccessories = MySqlDataStoreUtilities.getAccessories();			
			}
			catch(Exception e){				
			}

			accessory = allaccessories.get(name); 
			OrderItem orderitem = new OrderItem(accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer());
			orderItems.add(orderitem);
		}
		
	}
	// store the payment details for orders
	public void storePayment(int orderId,
		String orderName,double orderPrice,String userAddress,String creditCardNo,String customer){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		//String TOMCAT_HOME = System.getProperty("catalina.home");
			// get the payment details file 
			try
			{
				/*FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();*/
				orderPayments=MySqlDataStoreUtilities.selectOrder();
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo);
		listOrderPayment.add(orderpayment);	
			
			// add order details into file

			try                       
			{	
				/*FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            	objectOutputStream.writeObject(orderPayments);
				objectOutputStream.flush();
				objectOutputStream.close();       
				fileOutputStream.close();*/

				if(session.getAttribute("usertype").equals("manager"))
				{
					MySqlDataStoreUtilities.insertOrder(orderId,customer,orderName,orderPrice,userAddress,creditCardNo);
				}else{
					MySqlDataStoreUtilities.insertOrder(orderId,username(),orderName,orderPrice,userAddress,creditCardNo);
				}


			}
			catch(Exception e)
			{
				System.out.println("inside exception file not written properly");
			}	
	}

	 public String storeReview(String userid,String productname,String producttype,String productprice,String retailername,String manufacturername,String reviewrating,String userage,String usergender,String useroccupation,String productonsale,String manufacturerrebate,String zipcode,String retailercity,String retailerstate,String reviewdate,String reviewtext){
	String message=MongoDBDataStoreUtilities.insertReview(userid,productname,producttype,productprice,retailername,manufacturername,reviewrating,userage,usergender,useroccupation,productonsale,manufacturerrebate,zipcode,retailercity,retailerstate,reviewdate,reviewtext);
		if(!message.equals("Successfull"))
		{ return "UnSuccessfull";
		}
		else
		{
		HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
		try
		{
			reviews=MongoDBDataStoreUtilities.selectReview();
		}
		catch(Exception e)
		{
			
		}
		if(reviews==null)
		{
			reviews = new HashMap<String, ArrayList<Review>>();
		}
			// if there exist product review already add it into same list for productname or create a new record with product name
			
		if(!reviews.containsKey(productname)){	
			ArrayList<Review> arr = new ArrayList<Review>();
			reviews.put(productname, arr);
		}
		ArrayList<Review> listReview = reviews.get(productname);		
		Review review = new Review(userid,productname,producttype,productprice,retailername,manufacturername,reviewrating,userage,usergender,useroccupation,productonsale,manufacturerrebate,zipcode,retailercity,retailerstate,reviewdate,reviewtext);
		listReview.add(review);	
			
			// add Reviews into database
		
		return "Successfull";	
		}
	}

	
	/* getConsoles Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, Console> getConsoles(){
			HashMap<String, Console> hm = new HashMap<String, Console>();
			hm.putAll(SaxParserDataStore.consoles);
			return hm;
	}
	
	/* getGames Functions returns the  Hashmap with all Games in the store.*/

	public HashMap<String, Game> getGames(){
			HashMap<String, Game> hm = new HashMap<String, Game>();
			hm.putAll(SaxParserDataStore.games);
			return hm;
	}
	
	/* getTablets Functions returns the Hashmap with all Tablet in the store.*/

	public HashMap<String, Tablet> getTablets(){
			HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
			hm.putAll(SaxParserDataStore.tablets);
			return hm;
	}
	
	/* getProducts Functions returns the Arraylist of consoles in the store.*/

	public ArrayList<String> getProducts(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Console> entry : getConsoles().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of games in the store.*/

	public ArrayList<String> getProductsGame(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Game> entry : getGames().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of Tablets in the store.*/

	public ArrayList<String> getProductsTablets(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Tablet> entry : getTablets().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	

}
