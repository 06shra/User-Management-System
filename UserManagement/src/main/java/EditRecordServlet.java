import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditRecordServlet extends HttpServlet {
	static Connection con = null;
	static String url = "jdbc:mysql://localhost:3306/usermanagement";
	static String un = "root";
	static String pwd = "root";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String query ="update user set name=?,email=?,mobile=?,dob=?,city=?,gender=? where id=?";
		//get PrintWriter
		PrintWriter writer = resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		writer.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		//get the value
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String dob = req.getParameter("dob");
		String city = req.getParameter("city");
		String gender = req.getParameter("gender");
		//load the JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,un,pwd);
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1,name);
			pstmt.setString(2,email);
			pstmt.setString(3,mobile);
			pstmt.setString(4,dob);
			pstmt.setString(5,city);
			pstmt.setString(6,gender);
			pstmt.setInt(7,id);
			int count = pstmt.executeUpdate();
			writer.println("<div class='card' style='margin:auto; width:300px; margin-top:100px'>");
			if(count == 1) {
				writer.println("<h2 class='bg-danger text-light text-center'>Record Edited Successfully</h2>");
			}else {
				writer.println("<h2 class='bg-danger text-light text-center'>Record not Edited</h2>");
			}
		} catch (Exception e) {
			writer.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}
		writer.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
		writer.println("&nbsp; &nbsp;");
		writer.println("<a href='showdata'><button class='btn btn-outline-success'>Show User</button></a>");
		writer.println("</div>");
		writer.close();
		

	} 

}
