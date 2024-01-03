import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	static Connection con = null;
	static String url = "jdbc:mysql://localhost:3306/usermanagement";
	static String un = "root";
	static String pwd = "root";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter writer = resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		int id = Integer.parseInt(req.getParameter("id"));
		writer.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		
		//load the JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,un,pwd);
			final String query = "select name,email,mobile,dob,city,gender from user where id=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();
			res.next();
			writer.println("<div style='margin:auto;width:500px;margin-top:100px;text-align:center;'>");
			writer.println("<form action='edit?id="+id+"' method='post'>");
			writer.println("<table class='table table-hover table-striped'>");
			writer.println("<tr>");
			writer.println("<td>Name</td>");
			writer.println("<td><input type='text' name='name' value='"+res.getString(1)+"'></td>");
		    writer.println("</tr>");
		    writer.println("<tr>");
			writer.println("<td>Email</td>");
			writer.println("<td><input type='email' name='email' value='"+res.getString(2)+"'></td>");
		    writer.println("</tr>");
		    writer.println("<tr>");
			writer.println("<td>Mobile</td>");
			writer.println("<td><input type='text' name='mobile' value='"+res.getString(3)+"'></td>");
		    writer.println("</tr>");
		    writer.println("<tr>");
			writer.println("<td>dob</td>");
			writer.println("<td><input type='date' name='dob' value='"+res.getString(4)+"'></td>");
		    writer.println("</tr>");
		    writer.println("<tr>");
			writer.println("<td>city</td>");
			writer.println("<td><input type='text' name='city' value='"+res.getString(5)+"'></td>");
		    writer.println("</tr>");
		    writer.println("<tr>");
			writer.println("<td>gender</td>");
			writer.println("<td><input type='text' name='gender' value='"+res.getString(6)+"'></td>");
		    writer.println("</tr>");
		    writer.println("<tr>");
	    	writer.println("<td><button type='submit' class='btn btn-outline-success'>Edit</button></td>");
			writer.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>");
		    writer.println("</tr>");
            writer.println("</table>");
			writer.println("</form>");
			} catch (Exception e) {
			writer.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}
		writer.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
		writer.println("</div>");
		writer.close();
		

	} 


}
