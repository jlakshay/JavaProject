package com.examples.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DisplayServ")

public class DisplayServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con=null;
       Statement stmt=null;

       List<Employee> li= new ArrayList<Employee>();       
       Employee emp=new Employee();
         
    public List<Employee> DisplayEmp(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException, SQLException
    {
    
		try{
			
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","pswd");

			if(!con.isClosed())
			
			stmt = con.createStatement();
			String sql = "SELECT * FROM EMP";
			ResultSet rs= stmt.executeQuery(sql);

			while(rs.next())
			{
		    	int id,age,salary;
		    	String name;
		    	
				id=rs.getInt(1);
				name=rs.getString(2);
				age= rs.getInt(3);
				salary=rs.getInt(4);
				
				emp.setId(id);
				emp.setName(name);
				emp.setAge(age);
				emp.setSalary(salary);
				
			li.add(emp);
			}
    		} 
    catch(Exception e){
    	System.out.println(e);
    }
		return li;

    }


	public void fetchData(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  
		
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			List<Employee> li = null;
			try {
				li = DisplayEmp(req,res);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				try {
					for(int i=0;i<li.size();i++)
					{
						out.print(li.get(i).getId());
						out.print(li.get(i).getName());
						out.print(li.get(i).getAge());
						out.print(li.get(i).getSalary());
						out.println("\n");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	fetchData(req,res);
	}
	}
