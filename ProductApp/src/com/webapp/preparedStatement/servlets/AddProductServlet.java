package com.webapp.preparedStatement.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/addProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection conn;
	private PreparedStatement pstmt;
	public void init() {
		ServletContext ctx=getServletContext();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(ctx.getInitParameter("dbUrl"),ctx.getInitParameter("dbUser"),ctx.getInitParameter("dbPassword"));
			pstmt=conn.prepareStatement("insert into product values(?,?,?,?)");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String desc=request.getParameter("desc");
		int price=Integer.parseInt(request.getParameter("price"));
		int res=0;
		try {
			pstmt.setInt(1, id);
			pstmt.setString(2,name);
			pstmt.setString(3,desc);
			pstmt.setInt(4, price);
			
			res=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(res>0) {
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.println("<b>Data inserted successfully</b>");
			}else {
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.println("<b>Error inserting data!!!</b>");
			
			}
		}
		
	}
	
	public void destroy() {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
