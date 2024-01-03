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

@WebServlet("/showdata")
public class ShowUserServlet extends HttpServlet {
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
		writer.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		writer.println("<marquee><h2 class='text-primary'>User Data</h2></marquee>");
		//load the JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,un,pwd);
			final String query = "select * from user";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet res = pstmt.executeQuery();
			writer.println("<div style='margin:auto;width:900px;margin-top:100px;text-align:center;'>");
			writer.println("<table class='table table-hover table-striped'>");
			writer.println("<tr>");
			writer.println("<th>ID</th>");
			writer.println("<th>Name</th>");
			writer.println("<th>Email</th>");
			writer.println("<th>Mobile No</th>");
			writer.println("<th>DOB</th>");
			writer.println("<th>City</th>");
			writer.println("<th>Gender</th>");
			writer.println("<th>Edit</th>");
			writer.println("<th>Delete</th>");
			writer.println("</tr>");
			writer.println("");
			while(res.next()) {
				writer.println("<tr>");
						 writer.println("<td>" + res.getInt(1) + "</td>");
						 writer.println("<td>" + res.getString(2) + "</td>");
						 writer.println("<td>" + res.getString(3) + "</td>");
						 writer.println("<td>" + res.getString(4) + "</td>");
						 writer.println("<td>" + res.getString(5) + "</td>");
						 writer.println("<td>" + res.getString(6) + "</td>");
						 writer.println("<td>" + res.getString(7) + "</td>");
						 writer.println("<td><a href='editurl?id="+ res.getInt(1) +"'>Edit</a></td>");
						 writer.println("<td><a href='deleteurl?id="+ res.getInt(1) +"'>Delete</a></td>");
				         writer.println("</tr>");
			}
			writer.println("</table>");
			} catch (Exception e) {
			writer.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}
		writer.println("<a href='home.html'><button class='btn btn-outline-success d-block'>Home</a></button>");
		writer.println("</div>");
		writer.close();
		

	} 

}
