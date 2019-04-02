import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;

@WebServlet("/ViewOrder")

public class ViewOrder extends HttpServlet {
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("Login");
			return;
		}
		String username=utility.username();
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='ViewOrder' action='ViewOrder' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");

		/*check if the order button is clicked 
		if order button is not clicked that means the view order page is visited freshly
		then user will get textbox to give order number by which he can view order 
		if order button is clicked user will be directed to this same servlet and user has given order number 
		then this page shows all the order details*/
	
		if(request.getParameter("Order")==null)
		{
			pw.print("<table align='center'><tr><td>Enter OrderNo &nbsp&nbsp<input name='orderId' type='text'></td>");
			pw.print("<td><input type='submit' name='Order' value='ViewOrder' class='btnbuy'></td></tr></table>");
		}

		//hashmap gets all the order details from file 

		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");

		/*try
		{
			FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
			orderPayments = (HashMap)objectInputStream.readObject();
		}
		catch(Exception e)
		{
		}*/
		

		/*if order button is clicked that is user provided a order number to view order 
		order details will be fetched and displayed in  a table 
		Also user will get an button to cancel the order */

		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("ViewOrder"))
		{
			HttpSession session = request.getSession(true);
			if (request.getParameter("orderId") != null && !request.getParameter("orderId").isEmpty())
			{	
				int orderId=Integer.parseInt(request.getParameter("orderId"));
				pw.print("<input type='hidden' name='orderId' value='"+orderId+"'>");
				//get the order details from file
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
				int size=0;
			

				/*get the order size and check if there exist an order with given order number 
				if there is no order present give a message no order stored with this id */

				if(orderPayments.get(orderId)!=null)
				{
					for(OrderPayment od:orderPayments.get(orderId)){
							if (session.getAttribute("usertype").equals("manager"))
							{	
								size= orderPayments.get(orderId).size();
							}
							else
							{
								if(od.getUserName().equals(username)){
									size= orderPayments.get(orderId).size();
								}
							}
					
					}	
					
				}
				// display the orders if there exist order with order id
				if(size>0)
				{	
					pw.print("<table  class='gridtable'>");
					pw.print("<tr><td></td>");
					pw.print("<td>OrderId:</td>");
					pw.print("<td>UserName:</td>");
					pw.print("<td>productOrdered:</td>");
					pw.print("<td>productPrice:</td></tr>");
					for (OrderPayment oi : orderPayments.get(orderId)) 
					{
						//if(oi.getUserName().equals(username)){
							pw.print("<tr>");			
							pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
							pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
							pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
							pw.print("</tr>");
						//}
						
					}
					pw.print("</table>");
				}
				else
				{
					pw.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
				}
			}else
				
			{
				pw.print("<h4 style='color:red'>Please enter the valid order number</h4>");	
			}
		}
		//if the user presses cancel order from order details shown then process to cancel the order
		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("CancelOrder"))
		{
			String orderName=request.getParameter("orderName");
			if(request.getParameter("orderName") != null)
			{
				
				int orderId=0;
				orderId=Integer.parseInt(request.getParameter("orderId"));
				ArrayList<OrderPayment> ListOrderPayment =new ArrayList<OrderPayment>();
				//get the order from file
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
				//get the exact order with same ordername and add it into cancel list to remove it later
				for (OrderPayment oi : orderPayments.get(orderId)) 
					{
							/*User user=utility.getUser();
							if(user.getUsertype().equals("manager")){
								if(oi.getOrderName().equals(orderName))
									{
										ListOrderPayment.add(oi);
										//pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");								
									}

							}
							else{
									if(oi.getOrderName().equals(orderName) && oi.getUserName().equals(username))
									{
										ListOrderPayment.add(oi);
										//pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");								
									}
							}*/

							if(oi.getOrderName().equals(orderName))
							{
								MySqlDataStoreUtilities.deleteOrder(orderId,orderName);
								ListOrderPayment.add(oi);
								//pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");								
							}
							
					}
				//remove all the orders from hashmap that exist in cancel list
				orderPayments.get(orderId).removeAll(ListOrderPayment);
				if(orderPayments.get(orderId).size()==0)
					{
							orderPayments.remove(orderId);

					}
				//save the updated hashmap with removed order to the file	
				/*try
				{	
					FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
					objectOutputStream.writeObject(orderPayments);
					objectOutputStream.flush();
					objectOutputStream.close();       
					fileOutputStream.close();
				}
				catch(Exception e)
				{
				
				}	*/
				pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");
			}else
			{
				pw.print("<h4 style='color:red'>Please select any product</h4>");
			}
		}



		// Add orders to the customer

		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("Add"))
		{
			if(request.getParameter("productName") != null){


				Phone phone = null;
				Speaker speaker = null;
				Laptop laptop = null;
				FitnessWatch fitnesswatch = null;
				SmartWatch smartwatch = null;
				HeadPhone headphone = null;
				VirtualReality virtualreality = null;
				Tracker tracker = null;
				Accessory accessory = null;
				String name = request.getParameter("productName");
				phone = MySqlDataStoreUtilities.getPhones().get(name);
				speaker = MySqlDataStoreUtilities.getSpeakers().get(name);
				laptop = MySqlDataStoreUtilities.getLaptops().get(name);
				fitnesswatch = MySqlDataStoreUtilities.getFitnessWatches().get(name);
				smartwatch = MySqlDataStoreUtilities.getSmartWatches().get(name);
				headphone = MySqlDataStoreUtilities.getHeadPhones().get(name);
				virtualreality = MySqlDataStoreUtilities.getVirtualReality().get(name);
				tracker = MySqlDataStoreUtilities.getTrackers().get(name);
				accessory = MySqlDataStoreUtilities.getAccessories().get(name);
				//OrderItem orderitem = null;
				String username1=request.getParameter("userName");
				Integer orderId = Integer.parseInt(request.getParameter("orderId"));
				if(phone != null){
					System.out.print(phone.getName());
					System.out.print(phone.getPrice());
					System.out.print(phone.getRetailer());

						/*HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
						String TOMCAT_HOME = System.getProperty("catalina.home");
						// get the payment details file 
						try
						{
							FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
							ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
							orderPayments = (HashMap)objectInputStream.readObject();
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
							System.out.print("Hello");
						}*/
						/*ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
						OrderPayment orderpayment = new OrderPayment(orderId,username1,phone.getName(),phone.getPrice(),"chicago","111");
						listOrderPayment.add(orderpayment);*/
						try                       
						{	
							/*FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			            	objectOutputStream.writeObject(orderPayments);
							objectOutputStream.flush();
							objectOutputStream.close();       
							fileOutputStream.close();*/
							MySqlDataStoreUtilities.insertOrder(orderId,username1,phone.getName(),phone.getPrice(),"chicago","1234567812345678");
							pw.print("<h4 style='color:red'>Order Has been added..</h4>");
						}
						catch(Exception e)
						{
							System.out.println("inside exception file not written properly");
						}	
				}
				else if(speaker !=null){
					System.out.print(speaker.getName());
					System.out.print(speaker.getPrice());
					System.out.print(speaker.getRetailer());
					/*ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
						OrderPayment orderpayment = new OrderPayment(orderId,username1,speaker.getName(),speaker.getPrice(),"chicago","111");
						listOrderPayment.add(orderpayment);*/
						try                       
						{	
							/*FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			            	objectOutputStream.writeObject(orderPayments);
							objectOutputStream.flush();
							objectOutputStream.close();       
							fileOutputStream.close();*/
							MySqlDataStoreUtilities.insertOrder(orderId,username1,speaker.getName(),speaker.getPrice(),"chicago","1234567812345678");
							pw.print("<h4 style='color:red'>Order Has been added..</h4>");
						}
						catch(Exception e)
						{
							System.out.println("inside exception file not written properly");
						}	
				}
				else if(laptop !=null){
					System.out.print(laptop.getName());
					System.out.print(laptop.getPrice());
					System.out.print(laptop.getRetailer());
					/*ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
						OrderPayment orderpayment = new OrderPayment(orderId,username1,laptop.getName(),laptop.getPrice(),"chicago","111");
						listOrderPayment.add(orderpayment);*/
						try                       
						{	
							/*FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			            	objectOutputStream.writeObject(orderPayments);
							objectOutputStream.flush();
							objectOutputStream.close();       
							fileOutputStream.close();*/
							MySqlDataStoreUtilities.insertOrder(orderId,username1,laptop.getName(),laptop.getPrice(),"chicago","1234567812345678");
							
							pw.print("<h4 style='color:red'>Order Has been added..</h4>");
						}
						catch(Exception e)
						{
							System.out.println("inside exception file not written properly");
						}	
				}
				else if(fitnesswatch !=null){
					System.out.print(fitnesswatch.getName());
					System.out.print(fitnesswatch.getPrice());
					System.out.print(fitnesswatch.getRetailer());
					/*ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
						OrderPayment orderpayment = new OrderPayment(orderId,username1,fitnesswatch.getName(),fitnesswatch.getPrice(),"chicago","111");
						listOrderPayment.add(orderpayment);*/
						try                       
						{	
							/*FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			            	objectOutputStream.writeObject(orderPayments);
							objectOutputStream.flush();
							objectOutputStream.close();       
							fileOutputStream.close();*/
							MySqlDataStoreUtilities.insertOrder(orderId,username1,fitnesswatch.getName(),fitnesswatch.getPrice(),"chicago","1234567812345678");
							
							pw.print("<h4 style='color:red'>Order Has been added..</h4>");
						}
						catch(Exception e)
						{
							System.out.println("inside exception file not written properly");
						}	
				}
				else if(smartwatch !=null){
					System.out.print(smartwatch.getName());
					System.out.print(smartwatch.getPrice());
					System.out.print(smartwatch.getRetailer());
					/*ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
						OrderPayment orderpayment = new OrderPayment(orderId,username1,smartwatch.getName(),smartwatch.getPrice(),"chicago","111");
						listOrderPayment.add(orderpayment);*/
						try                       
						{	
							/*FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			            	objectOutputStream.writeObject(orderPayments);
							objectOutputStream.flush();
							objectOutputStream.close();       
							fileOutputStream.close();*/
							MySqlDataStoreUtilities.insertOrder(orderId,username1,smartwatch.getName(),smartwatch.getPrice(),"chicago","1234567812345678");
							
							pw.print("<h4 style='color:red'>Order Has been added..</h4>");
						}
						catch(Exception e)
						{
							System.out.println("inside exception file not written properly");
						}	
				}
				else if(headphone !=null){
					System.out.print(headphone.getName());
					System.out.print(headphone.getPrice());
					System.out.print(headphone.getRetailer());
					/*ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
						OrderPayment orderpayment = new OrderPayment(orderId,username1,headphone.getName(),headphone.getPrice(),"chicago","111");
						listOrderPayment.add(orderpayment);*/
						try                       
						{	
							/*FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			            	objectOutputStream.writeObject(orderPayments);
							objectOutputStream.flush();
							objectOutputStream.close();       
							fileOutputStream.close();*/
							MySqlDataStoreUtilities.insertOrder(orderId,username1,headphone.getName(),headphone.getPrice(),"chicago","1234567812345678");
							
							pw.print("<h4 style='color:red'>Order Has been added..</h4>");
						}
						catch(Exception e)
						{
							System.out.println("inside exception file not written properly");
						}	
				}
				else if(virtualreality != null){
					System.out.print(virtualreality.getName());
					System.out.print(virtualreality.getPrice());
					System.out.print(virtualreality.getRetailer());
					/*ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
						OrderPayment orderpayment = new OrderPayment(orderId,username1,virtualreality.getName(),virtualreality.getPrice(),"chicago","111");
						listOrderPayment.add(orderpayment);*/
						try                       
						{	
							/*FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			            	objectOutputStream.writeObject(orderPayments);
							objectOutputStream.flush();
							objectOutputStream.close();       
							fileOutputStream.close();*/
							MySqlDataStoreUtilities.insertOrder(orderId,username1,virtualreality.getName(),virtualreality.getPrice(),"chicago","1234567812345678");
							
							pw.print("<h4 style='color:red'>Order Has been added..</h4>");
						}
						catch(Exception e)
						{
							System.out.println("inside exception file not written properly");
						}	
				}
				else if(tracker !=null){
					System.out.print(tracker.getName());
					System.out.print(tracker.getPrice());
					System.out.print(tracker.getRetailer());
					/*ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
						OrderPayment orderpayment = new OrderPayment(orderId,username1,tracker.getName(),tracker.getPrice(),"chicago","111");
						listOrderPayment.add(orderpayment);*/
						try                       
						{	
							/*FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			            	objectOutputStream.writeObject(orderPayments);
							objectOutputStream.flush();
							objectOutputStream.close();       
							fileOutputStream.close();*/
							MySqlDataStoreUtilities.insertOrder(orderId,username1,tracker.getName(),tracker.getPrice(),"chicago","1234567812345678");
							
							pw.print("<h4 style='color:red'>Order Has been added..</h4>");
						}
						catch(Exception e)
						{
							System.out.println("inside exception file not written properly");
						}	
				}
				else if(accessory !=null){
					System.out.print(accessory.getName());
					System.out.print(accessory.getPrice());
					System.out.print(accessory.getRetailer());
					/*ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
						OrderPayment orderpayment = new OrderPayment(orderId,username1,accessory.getName(),accessory.getPrice(),"chicago","111");
						listOrderPayment.add(orderpayment);*/
						try                       
						{	
							/*FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			            	objectOutputStream.writeObject(orderPayments);
							objectOutputStream.flush();
							objectOutputStream.close();       
							fileOutputStream.close();*/
							MySqlDataStoreUtilities.insertOrder(orderId,username1,accessory.getName(),accessory.getPrice(),"chicago","1234567812345678");
							
							pw.print("<h4 style='color:red'>Order Has been added..</h4>");
						}
						catch(Exception e)
						{
							System.out.println("inside exception file not written properly");
						}	
				}
				else{
					pw.print("<h4 style='color:red'>Enter correct product name</h4>");
				}
			}
			else
			{

				pw.print("<h4 style='color:red'>Please select any product</h4>");
			}
		
			
			
		}
		






		pw.print("</form></div></div></div>");		
		utility.printHtml("Footer.html");
	}

}


