import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;
static String message;
public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartdatabase","root","Hello");							
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}

public static void Insertproducts()
{
	try{
		
		
		getConnection();
		
		
		String truncatetableacc = "delete from Product_accessories;";
		PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
		pstt.executeUpdate();
		
		String truncatetableprod = "delete from  Productdetails;";
		PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
		psttprod.executeUpdate();
		
				
		
		String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,quantity,manufacturerRebate)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?);";
		
		
		for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
		{   
	        Laptop laptop = entry.getValue();
			String name = "laptops";
			
						
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,laptop.getId());
			pst.setString(3,laptop.getName());
			pst.setDouble(4,laptop.getPrice());
			pst.setString(5,laptop.getImage());
			pst.setString(6,laptop.getRetailer());
			pst.setString(7,laptop.getCondition());
			pst.setDouble(8,laptop.getDiscount());
			pst.setDouble(9,laptop.getQuantity());
			pst.setString(10,laptop.getManufacturerRebate());
			
			pst.executeUpdate();
			try{
			HashMap<String,String> acc = laptop.getAccessories();
			String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
			"VALUES (?,?);";
			for(Map.Entry<String,String> accentry : acc.entrySet())
			{
				PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
				pstacc.setString(1,laptop.getId());
				pstacc.setString(2,accentry.getValue());
				pstacc.executeUpdate();
			}
			}catch(Exception et){
				et.printStackTrace();
			}
		}
		for(Map.Entry<String,Speaker> entry : SaxParserDataStore.speakers.entrySet())
		{   
			String name = "speakers";
	        Speaker speaker = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,speaker.getId());
			pst.setString(3,speaker.getName());
			pst.setDouble(4,speaker.getPrice());
			pst.setString(5,speaker.getImage());
			pst.setString(6,speaker.getRetailer());
			pst.setString(7,speaker.getCondition());
			pst.setDouble(8,speaker.getDiscount());
			pst.setDouble(9,speaker.getQuantity());
			pst.setString(10,speaker.getManufacturerRebate());
			
			pst.executeUpdate();
			
			
		}
		for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
		{   
			String name = "phones";
	        Phone phone = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,phone.getId());
			pst.setString(3,phone.getName());
			pst.setDouble(4,phone.getPrice());
			pst.setString(5,phone.getImage());
			pst.setString(6,phone.getRetailer());
			pst.setString(7,phone.getCondition());
			pst.setDouble(8,phone.getDiscount());
			pst.setDouble(9,phone.getQuantity());
			pst.setString(10,phone.getManufacturerRebate());			
			pst.executeUpdate();			
			
		}
		for(Map.Entry<String,Tracker> entry : SaxParserDataStore.trackers.entrySet())
		{   
			String name = "trackers";
	        Tracker tracker = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,tracker.getId());
			pst.setString(3,tracker.getName());
			pst.setDouble(4,tracker.getPrice());
			pst.setString(5,tracker.getImage());
			pst.setString(6,tracker.getRetailer());
			pst.setString(7,tracker.getCondition());
			pst.setDouble(8,tracker.getDiscount());	
			pst.setDouble(9,tracker.getQuantity());
			pst.setString(10,tracker.getManufacturerRebate());		
			pst.executeUpdate();			
			
		}
		for(Map.Entry<String,FitnessWatch> entry : SaxParserDataStore.fitnesswatches.entrySet())
		{   
			String name = "fitnesswatches";
	        FitnessWatch fitnesswatch = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,fitnesswatch.getId());
			pst.setString(3,fitnesswatch.getName());
			pst.setDouble(4,fitnesswatch.getPrice());
			pst.setString(5,fitnesswatch.getImage());
			pst.setString(6,fitnesswatch.getRetailer());
			pst.setString(7,fitnesswatch.getCondition());
			pst.setDouble(8,fitnesswatch.getDiscount());	
			pst.setDouble(9,fitnesswatch.getQuantity());
			pst.setString(10,fitnesswatch.getManufacturerRebate());			
			pst.executeUpdate();			
			
		}
		for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.smartwatches.entrySet())
		{   
			String name = "smartwatches";
	        SmartWatch smartwatch = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,smartwatch.getId());
			pst.setString(3,smartwatch.getName());
			pst.setDouble(4,smartwatch.getPrice());
			pst.setString(5,smartwatch.getImage());
			pst.setString(6,smartwatch.getRetailer());
			pst.setString(7,smartwatch.getCondition());
			pst.setDouble(8,smartwatch.getDiscount());	
			pst.setDouble(9,smartwatch.getQuantity());
			pst.setString(10,smartwatch.getManufacturerRebate());			
			pst.executeUpdate();			
			
		}
		for(Map.Entry<String,HeadPhone> entry : SaxParserDataStore.headphones.entrySet())
		{   
			String name = "headphones";
	        HeadPhone headphone = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,headphone.getId());
			pst.setString(3,headphone.getName());
			pst.setDouble(4,headphone.getPrice());
			pst.setString(5,headphone.getImage());
			pst.setString(6,headphone.getRetailer());
			pst.setString(7,headphone.getCondition());
			pst.setDouble(8,headphone.getDiscount());
			pst.setDouble(9,headphone.getQuantity());
			pst.setString(10,headphone.getManufacturerRebate());			
			pst.executeUpdate();			
			
		}
		for(Map.Entry<String,VirtualReality> entry : SaxParserDataStore.virtualrealities.entrySet())
		{   
			String name = "virtualrealities";
	        VirtualReality virtualreality = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,virtualreality.getId());
			pst.setString(3,virtualreality.getName());
			pst.setDouble(4,virtualreality.getPrice());
			pst.setString(5,virtualreality.getImage());
			pst.setString(6,virtualreality.getRetailer());
			pst.setString(7,virtualreality.getCondition());
			pst.setDouble(8,virtualreality.getDiscount());
			pst.setDouble(9,virtualreality.getQuantity());
			pst.setString(10,virtualreality.getManufacturerRebate());				
			pst.executeUpdate();			
			
		}


		




		for(Map.Entry<String,Accessory> entry : SaxParserDataStore.accessories.entrySet())
		{   
			String name = "accessories";
	        Accessory acc = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,acc.getId());
			pst.setString(3,acc.getName());
			pst.setDouble(4,acc.getPrice());
			pst.setString(5,acc.getImage());
			pst.setString(6,acc.getRetailer());
			pst.setString(7,acc.getCondition());
			pst.setDouble(8,acc.getDiscount());
			pst.setDouble(9,acc.getQuantity());
			pst.setString(10,acc.getManufacturerRebate());
			
			pst.executeUpdate();
			
			
		}
		
	}catch(Exception e)
	{
  		e.printStackTrace();
	}
} 


//Laptops

public static HashMap<String,Laptop> getLaptops()
{	
	HashMap<String,Laptop> hm=new HashMap<String,Laptop>();
	try 
	{
		getConnection();
		
		String selectLaptop="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectLaptop);
		pst.setString(1,"laptops");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Laptop con = new Laptop(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				
				try
				{
				String selectaccessory = "Select * from Product_accessories where productName=?";
				PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
				pstacc.setString(1,rs.getString("Id"));
				ResultSet rsacc = pstacc.executeQuery();
				
				HashMap<String,String> acchashmap = new HashMap<String,String>();
				while(rsacc.next())
				{    
					if (rsacc.getString("accessoriesName") != null){
					
					 acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
					 con.setAccessories(acchashmap);
					}
					 
				}					
				}catch(Exception e)
				{
						e.printStackTrace();
				}
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


//Phones
public static HashMap<String,Phone> getPhones()
{	
	HashMap<String,Phone> hm=new HashMap<String,Phone>();
	try 
	{
		System.out.print("Inside getphones");
		getConnection();
		
		String selectPhone="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectPhone);
		pst.setString(1,"phones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Phone pho = new Phone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), pho);
				pho.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


//Speakers

public static HashMap<String,Speaker> getSpeakers()
{	
	HashMap<String,Speaker> hm=new HashMap<String,Speaker>();
	try 
	{
		System.out.print("Inside getspeakers");
		getConnection();
		
		String selectSpeaker="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectSpeaker);
		pst.setString(1,"speakers");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Speaker spk = new Speaker(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), spk);
				spk.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


//Fitnesswatch

public static HashMap<String,FitnessWatch> getFitnessWatches()
{	
	HashMap<String,FitnessWatch> hm=new HashMap<String,FitnessWatch>();
	try 
	{
		System.out.print("Inside getfitnesswatches");
		getConnection();
		
		String selectFitnessWatches="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectFitnessWatches);
		pst.setString(1,"fitnesswatches");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	FitnessWatch fwh = new FitnessWatch(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), fwh);
				fwh.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}



//SmartWatch

public static HashMap<String,SmartWatch> getSmartWatches()
{	
	HashMap<String,SmartWatch> hm=new HashMap<String,SmartWatch>();
	try 
	{
		System.out.print("Inside getsmartfitnesswatches");
		getConnection();
		
		String selectsmartWatches="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectsmartWatches);
		pst.setString(1,"smartwatches");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	SmartWatch swh = new SmartWatch(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), swh);
				swh.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


//Head Phones
public static HashMap<String,HeadPhone> getHeadPhones()
{	
	HashMap<String,HeadPhone> hm=new HashMap<String,HeadPhone>();
	try 
	{
		System.out.print("Inside getheadphones");
		getConnection();
		
		String selectheadphones="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectheadphones);
		pst.setString(1,"headphones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	HeadPhone hps = new HeadPhone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), hps);
				hps.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}




//Vrtualrealities
public static HashMap<String,VirtualReality> getVirtualReality()
{	
	HashMap<String,VirtualReality> hm=new HashMap<String,VirtualReality>();
	try 
	{
		System.out.print("Inside getvirtualrealities");
		getConnection();
		
		String selectvirtualrealities="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectvirtualrealities);
		pst.setString(1,"virtualrealities");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	VirtualReality vrlty = new VirtualReality(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), vrlty);
				vrlty.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}



//Trackers
public static HashMap<String,Tracker> getTrackers()
{	
	HashMap<String,Tracker> hm=new HashMap<String,Tracker>();
	try 
	{
		System.out.print("Inside getTrackers");
		getConnection();
		
		String selecttrackers="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selecttrackers);
		pst.setString(1,"trackers");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Tracker trk = new Tracker(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), trk);
				trk.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

/*public static HashMap<String,Console> getConsoles()
{	
	HashMap<String,Console> hm=new HashMap<String,Console>();
	try 
	{
		getConnection();
		
		String selectConsole="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectConsole);
		pst.setString(1,"consoles");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Console con = new Console(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				
				try
				{
				String selectaccessory = "Select * from Product_accessories where productName=?";
				PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
				pstacc.setString(1,rs.getString("Id"));
				ResultSet rsacc = pstacc.executeQuery();
				
				HashMap<String,String> acchashmap = new HashMap<String,String>();
				while(rsacc.next())
				{    
					if (rsacc.getString("accessoriesName") != null){
					
					 acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
					 con.setAccessories(acchashmap);
					}
					 
				}					
				}catch(Exception e)
				{
						e.printStackTrace();
				}
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}*/

/*public static HashMap<String,Tablet> getTablets()
{	
	HashMap<String,Tablet> hm=new HashMap<String,Tablet>();
	try 
	{
		getConnection();
		
		String selectTablet="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectTablet);
		pst.setString(1,"tablets");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Tablet tab = new Tablet(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), tab);
				tab.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Game> getGames()
{	
	HashMap<String,Game> hm=new HashMap<String,Game>();
	try 
	{
		getConnection();
		
		String selectGame="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectGame);
		pst.setString(1,"games");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Game game = new Game(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), game);
				game.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Accessory> getAccessories()
{	
	HashMap<String,Accessory> hm=new HashMap<String,Accessory>();
	try 
	{
		getConnection();
		
		String selectAcc="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		pst.setString(1,"accessories");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Accessory acc = new Accessory(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), acc);
				acc.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}*/

public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,String prod)
{
	String msg = "Product is added successfully";
	try{
		
		getConnection();
		String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,quantity,manufacturerRebate)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?);";
		   
			String name = producttype;
	        			
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1,name);
			pst.setString(2,productId);
			pst.setString(3,productName);
			pst.setDouble(4,productPrice);
			pst.setString(5,productImage);
			pst.setString(6,productManufacturer);
			pst.setString(7,productCondition);
			pst.setDouble(8,productDiscount);
			pst.setDouble(9,100);
			pst.setString(10,"No");
			
			pst.executeUpdate();
			try{
				if (!prod.isEmpty())
				{
					String addaprodacc =  "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
					pst1.setString(1,prod);
					pst1.setString(2,productId);
					pst1.executeUpdate();
					
				}
			}catch(Exception e)
			{
				msg = "Erro while adding the product";
				e.printStackTrace();
		
			}
			
			
		
	}
	catch(Exception e)
	{
		msg = "Erro while adding the product";
		e.printStackTrace();
		
	}
	return msg;
}
public static String updateproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount)
{ 
    String msg = "Product is updated successfully";
	try{
		
		getConnection();
		String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=? where Id =?;" ;
		
		   
				        			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,productName);
			pst.setDouble(2,productPrice);
			pst.setString(3,productImage);
			pst.setString(4,productManufacturer);
			pst.setString(5,productCondition);
			pst.setDouble(6,productDiscount);
			pst.setString(7,productId);
			pst.executeUpdate();
			
			
		
	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();
		
	}
 return msg;	
}
public static String deleteproducts(String productId)
{   String msg = "Product is deleted successfully";
	try
	{
		
		getConnection();
		String deleteproductsQuery ="Delete from Productdetails where Id=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,productId);
		
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Proudct cannot be deleted";
	}
	return msg;
}

public static HashMap<String,Accessory> getAccessories()
{	
	HashMap<String,Accessory> hm=new HashMap<String,Accessory>();
	try 
	{
		getConnection();
		
		String selectAcc="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		pst.setString(1,"accessories");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Accessory acc = new Accessory(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), acc);
				acc.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


public static void deleteOrder(int orderId,String orderName)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from customerorders where OrderId=? and orderName=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,orderId);
		pst.setString(2,orderName);
		pst.executeUpdate();
		MySqlDataStoreUtilities.Updateproductquantity();
	}
	catch(Exception e)
	{
			
	}
}

public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo)
{
	try
	{
	
		getConnection();
		
		String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserName,OrderName,OrderPrice,userAddress,creditCardNo,deliverydate) "
		+ "VALUES (?,?,?,?,?,?,?);";	
			
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,orderId);
		pst.setString(2,userName);
		pst.setString(3,orderName);
		pst.setDouble(4,orderPrice);
		pst.setString(5,userAddress);
		pst.setString(6,creditCardNo);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 14);
		Date date = cal.getTime();
		String DATE_FORMAT = "MM/dd/yyyy"; 
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
		String deliverydate = sdf.format(date);
		pst.setString(7,deliverydate);

		pst.execute();
		MySqlDataStoreUtilities.Updateproductquantity();
	}
	catch(Exception e)
	{
	
	}		
}

public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from customerorders";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("OrderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("orderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));		
			

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"));
			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	return orderPayments;
}

public static Map getAllProductSold()
	{

		getConnection();
		Map<OrderItem,String> map= new HashMap<OrderItem,String>();
		String query = "select *,count(*) AS quantity FROM customerorders GROUP BY orderName ORDER BY orderName ASC ;";
		try
		{
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			OrderItem order=null;
			while(rs.next())
			{
				order = new OrderItem();
				order.setName(rs.getString("orderName"));
				order.setPrice(rs.getDouble("orderPrice"));
				map.put(order,rs.getString("quantity"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}

public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  Productdetails";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	Product p = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getString("ProductType"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), p);
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return hm;			
	}

public static TreeMap getDailySales()
	{

		TreeMap<String,String> map= new TreeMap<String,String>();
		String query = "select deliverydate,SUM(OrderPrice) AS quantity from customerorders group by deliverydate ORDER BY CAST(deliverydate As DATETIME);";
		try
		{
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				Calendar cal = Calendar.getInstance();
				String DATE_FORMAT = "MM/dd/yyyy"; 
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				java.util.Date deliveryDate = sdf.parse(rs.getString("deliveryDate"));
				cal.setTime(deliveryDate);
				cal.add(Calendar.DATE, -14);
				java.util.Date date = cal.getTime();
				String purchaseDate = sdf.format(date);
				map.put(purchaseDate,rs.getString("quantity"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}


public static void insertUser(String username,String password,String repassword,String usertype)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
		+ "VALUES (?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,repassword);
		pst.setString(4,usertype);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}

public static void Updateproductquantity(){
	try
	{	

		getConnection();

		Map<OrderItem,String> map = new HashMap<OrderItem,String>();
		map = MySqlDataStoreUtilities.getAllProductSold();
		if(map!=null){
			Iterator it = map.entrySet().iterator();
					while(it.hasNext())
					{
						Map.Entry entry = (Map.Entry) it.next();
						OrderItem key = (OrderItem) entry.getKey();
						String value = (String) entry.getValue();
						double d = Double.parseDouble(value);
						double qnew= 100-d;

						String updateProductQuantityQurey = "UPDATE Productdetails SET quantity=? where productName =?;" ;
		
		   
				        			
						PreparedStatement pst = conn.prepareStatement(updateProductQuantityQurey);
						
						
						pst.setDouble(1,qnew);
						pst.setString(2,key.getName());
						pst.executeUpdate();

						//pw.println("<tr><td style='font-size:12px;text-align:center'>" +key.getName()+ " </td><td style='font-size:12px;text-align:center'>$" +key.getPrice()+ "</td><td style='font-size:12px;text-align:center'>" +value+ "</td><td style='font-size:12px;text-align:center'>$" + key.getPrice() * Float.parseFloat(value) + "</td></tr>");
					}
		}
		
	}
	catch(Exception e)
	{
	
	}	
}

public static HashMap<String,Product> getAllProducts(){

	HashMap<String,Product> hm=new HashMap<String,Product>();
	try 
	{
		//System.out.print("Inside getTrackers");
		getConnection();
		
		String selecttrackers="select * from  Productdetails";
		PreparedStatement pst = conn.prepareStatement(selecttrackers);
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
				Product trk = new Product(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getDouble("quantity"),rs.getString("manufacturerRebate"));
				hm.put(rs.getString("Id"), trk);
				trk.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;		

}


public static HashMap<String,Product> getAllManufactureRebateProducts(){

	HashMap<String,Product> hm=new HashMap<String,Product>();
	try 
	{
		getConnection();
		
		String selecttrackers="select * from  Productdetails where manufacturerRebate=?";
		PreparedStatement pst = conn.prepareStatement(selecttrackers);
		pst.setString(1,"Yes");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
				Product trk = new Product(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getDouble("quantity"),rs.getString("manufacturerRebate"));
				hm.put(rs.getString("Id"), trk);
				trk.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;		

}


public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

	
}	