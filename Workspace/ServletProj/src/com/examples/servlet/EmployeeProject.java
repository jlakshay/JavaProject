package com.examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeProject")
public class EmployeeProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con=null;
       Statement stmt=null;
       PreparedStatement pstmt=null;
  

    public void insertEmp(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException, SQLException
    {
    	String Id= req.getParameter("empid");
		String Name= req.getParameter("empname");
		String Age= req.getParameter("empage"); 
		String Salary= req.getParameter("empsalary"); 
		int empid = Integer.parseInt(Id);
		int empage=Integer.parseInt(Age);
		int empsalary= Integer.parseInt(Salary);
		
		try{  
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","pswd");
			if(!con.isClosed())
				System.out.println("Successfully Connected");
				stmt = con.createStatement();

		String sql = "INSERT into EMP (id, name, age,salary) VALUES (?,?,?,?)";
		PreparedStatement pstmt= con.prepareStatement(sql);
	
		  pstmt.setInt(1, empid);
		  pstmt.setString(2, Name);
		  pstmt.setInt(3, empage);
		  pstmt.setInt(4, empsalary);
			 	int i= pstmt.executeUpdate();
			 		if(i==1)
			 		{
			 		System.out.println("true"+i);
			 		}
			 		else{
					  System.out.println("False"+i);
			 		}
		}//try
		catch(Exception e)
		{
		 System.out.println(e);	
		}//catch
		finally
		{
			con.close();
			pstmt.close();
			stmt.close();
		}
    }//insert close
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("text/html");

		try{
			insertEmp(req,res);
			res.sendRedirect("success.html"); 
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
