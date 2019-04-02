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

@WebServlet("/AddOrder")

public class AddOrder extends HttpServlet {
	
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
		String username=request.getParameter("userName");
		String orderid = request.getParameter("orderId");
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='ViewOrder' action='ViewOrder' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table style='width:75%'>");
		pw.print("<tr><td>Enter the Product name : </td><td><input type='text' name='productName' value='' class='input' required></input></td><td><input type='submit' name='Order' class='btnbuy' value='Add'></input></td></tr>");
		pw.print("</table>");
		pw.print("<input type='hidden' name='userName' value='"+username+"'>");
		pw.print("<input type='hidden' name='orderId' value='"+orderid+"'>");
		pw.print("</div></div></div></form>");		
		utility.printHtml("Footer.html");
	}

}


