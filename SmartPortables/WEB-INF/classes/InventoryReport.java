import java.io.*;
import java.io.PrintWriter;
import java.util.*;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;


@WebServlet("/InventoryReport")


public class InventoryReport extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		if(action.equals("ProductsInventory"))
		{
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Products Inventory</a></h2>"
						+ "<div class='entry'>");
			HashMap<String,Product> allproducts = new HashMap<String,Product> ();

			allproducts =MySqlDataStoreUtilities.getAllProducts();
				pw.println("<table border='2' style='width:80%;Margin-top: 30px'>"+
					"<tr style='background-color: #14e01b'><th style='font-size:14px;font-weight: bold;text-align:center'>Product Name </th><th style='font-size:14px;font-weight: bold;text-align:center'>Price</th><th style='font-size:14px;font-weight: bold;text-align:center'>Quantity</th></tr>");
			for (Map.Entry<String, Product> entry : allproducts.entrySet()) {
				Product prdct = entry.getValue();
				pw.println("<tr><td style='font-size:12px;text-align:center'>" +prdct.getName()+ " </td><td style='font-size:12px;text-align:center'>$" +prdct.getPrice()+ "</td><td style='font-size:12px;text-align:center'>" +prdct.getQuantity()+ "</td></tr>");

			}
			pw.println("</table>");
			pw.print("</div></div></div>");

			
		
		
		}
		else if(action.equals("AllProductsOnSale")){

			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Products On Sale</a></h2>"
						+ "<div class='entry'>");
			HashMap<String,Product> allproducts = new HashMap<String,Product> ();
			//productDiscount
			allproducts =MySqlDataStoreUtilities.getAllProducts();
				pw.println("<table border='2' style='width:80%;Margin-top: 30px'>"+
					"<tr style='background-color: #14e01b'><th style='font-size:14px;font-weight: bold;text-align:center'>Product Name </th><th style='font-size:14px;font-weight: bold;text-align:center'>Price</th><th style='font-size:14px;font-weight: bold;text-align:center'>Quantity</th><th style='font-size:14px;font-weight: bold;text-align:center'>Discount</th><th style='font-size:14px;font-weight: bold;text-align:center'>Manufacturer Rebate</th></tr>");
			for (Map.Entry<String, Product> entry : allproducts.entrySet()) {
				Product prdct = entry.getValue();
				pw.println("<tr><td style='font-size:12px;text-align:center'>" +prdct.getName()+ " </td><td style='font-size:12px;text-align:center'>$" +prdct.getPrice()+ "</td><td style='font-size:12px;text-align:center'>" +prdct.getQuantity()+ "</td><td style='font-size:12px;text-align:center'>" +prdct.getDiscount()+ " </td><td style='font-size:12px;text-align:center'>" +prdct.getManufacturerRebate()+ " </td></tr>");

			}
			pw.println("</table>");
			pw.print("</div></div></div>");
		}
		else if(action.equals("ManufacturerRebates")){
			HashMap<String,Product> allrebateproducts = new HashMap<String,Product> ();
			allrebateproducts =MySqlDataStoreUtilities.getAllManufactureRebateProducts();
			if(!allrebateproducts.isEmpty()){
				pw.print("<div id='content'><div class='post'>");
				pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Manufacture Rebate Products</a></h2>"
							+ "<div class='entry'>");

				pw.println("<table border='2' style='width:80%;Margin-top: 30px'>"
					+"<tr style='background-color: #14e01b'><th style='font-size:14px;font-weight: bold;text-align:center'>Product Name </th><th style='font-size:14px;font-weight: bold;text-align:center'>Price</th><th style='font-size:14px;font-weight: bold;text-align:center'>Quantity</th><th style='font-size:14px;font-weight: bold;text-align:center'>Manufacturer Rebate</th></tr>");
				for (Map.Entry<String, Product> entry : allrebateproducts.entrySet()) {
					Product prdct = entry.getValue();
					pw.println("<tr><td style='font-size:12px;text-align:center'>" +prdct.getName()+ " </td><td style='font-size:12px;text-align:center'>$" +prdct.getPrice()+ "</td><td style='font-size:12px;text-align:center'>" +prdct.getQuantity()+ "</td><td style='font-size:12px;text-align:center'>" +prdct.getManufacturerRebate()+ " </td></tr>");

				}
				pw.println("</table>");

				pw.print("</div></div></div>");
			}
			

		}		
		else if(action.equals("GraphicalView")) {
				pw.print("<div id='content'><div class='post'>");
				pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Graphical View Of Products Quantity</a></h2>"
						+ "<div class='entry'>");
		   		pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		   		pw.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>"+
							"<script type='text/javascript' src='https://www.google.com/jsapi'></script>");

		   		pw.println("<script type='text/javascript'>");
				HashMap<String,Product> map1 = new HashMap<String,Product> ();
				map1 = MySqlDataStoreUtilities.getAllProducts();
				pw.println("google.charts.load('current', {'packages':['corechart']});");
				pw.println("google.charts.setOnLoadCallback(drawChart);");
				pw.println("function drawChart() {");
				pw.println("var data = new google.visualization.DataTable();");
				pw.println("data.addColumn('string', 'Product Name');");
				pw.println("data.addColumn('number', 'Quantity');");
			   	pw.println(" data.addRows([");

			   	for (Map.Entry<String, Product> entry : map1.entrySet()) {
					Product prdct = entry.getValue();
					pw.println(" ['"+prdct.getName()+"', "+prdct.getQuantity()+"],");
				}


			   	/*
					Iterator it = map1.entrySet().iterator();
					while(it.hasNext())
					{
						Map.Entry entry = (Map.Entry) it.next();
						OrderItem key = (OrderItem) entry.getKey();
						String value = (String) entry.getValue();
						//Float totalsale= Float.parseFloat(key.getPrice() * Float.parseFloat(value));
						pw.println(" ['"+key.getName()+"', "+value+"],");
						//pw.println("<tr><td style='font-size:12px;text-align:center'>" +key.getName()+ " </td><td style='font-size:12px;text-align:center'>$" +key.getPrice()+ "</td><td style='font-size:12px;text-align:center'>" +value+ "</td><td style='font-size:12px;text-align:center'>$" + key.getPrice() * Float.parseFloat(value) + "</td></tr>");
					}*/


				pw.println("]);");
				// Set chart options
				pw.println(" var options = {'title':'Items Quantity',");
				pw.println("        'width':600,");
				pw.println("       'height':650};");
	 
				// Instantiate and draw our chart, passing in some options.
				pw.println(" var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
				pw.println("  chart.draw(data, options);     }");
				pw.println(" </script>");
			//Draw chart
		 		pw.println("<div id='chart_div'></div>");


		   		pw.print("</div></div></div>");
		}
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
		}
	}