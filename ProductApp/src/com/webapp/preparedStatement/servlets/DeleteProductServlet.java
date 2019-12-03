package com.webapp.preparedStatement.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/deleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	private PreparedStatement pstmt;
	
	public void init(ServletConfig config) {
		ServletContext ctx=config.getServletContext();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(ctx.getInitParameter("dbUrl"),ctx.getInitParameter("dbUser"),ctx.getInitParameter("dbPassword"));
			pstmt=conn.prepareStatement("delete from product where id=?");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));

		int res=0;
		try {
			pstmt.setInt(1, id);
			
			res=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(res>0) {
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.println("<b>Data deleted successfully</b>");
			}else {
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.println("<b>Error deleting data!!!</b>");
			
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
