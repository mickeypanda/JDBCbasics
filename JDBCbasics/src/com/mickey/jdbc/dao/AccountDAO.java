package com.mickey.jdbc.dao;

import java.sql.*;
import java.util.ArrayList;
import com.mickey.jdbc.beans.*;

public class AccountDAO {
	public static AccountDAO account=new AccountDAO();
	
	private Connection conn;
	private Statement stmt;
	private String sqlQuery="";
	private String dbURL="jdbc:mysql://localhost:3306/mydb";
	private String user="root";
	private String password="igate@123";
	
	
	public int CreateAccount(int acc_num,String lastname,String firstname,int bal) {
		int count=0;
		try{
			conn=DriverManager.getConnection(dbURL, user, password);
		    stmt=conn.createStatement();
			sqlQuery="insert into account values("+acc_num+",'"+lastname+"','"+firstname+"',"+bal+")";
			count= stmt.executeUpdate(sqlQuery);

			} catch (SQLException e) {
			e.printStackTrace();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		finally {
			
			try {
				stmt.close();
				conn.close();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return count;
	}
	
	public ArrayList<Account> ReadAccount(String sqlQuery) {
		ArrayList<Account> accountList=new ArrayList<Account>();
		try {
			conn=DriverManager.getConnection(dbURL,user,password);
			stmt=conn.createStatement();
			this.sqlQuery=sqlQuery;
			ResultSet rs=stmt.executeQuery(sqlQuery);
			
			while(rs.next()) {
				Account acc=new Account();
				acc.acc_num=rs.getInt("acc_num");
				acc.bal=rs.getInt("bal");
				acc.lastname=rs.getString("lastname");
				acc.firstname=rs.getString("firstname");
				
				accountList.add(acc);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		
		
		return accountList;
	}
	
	public int UpdateAccount(String sqlQuery) {
		int count=0;
		try {
			conn=DriverManager.getConnection(dbURL,user,password);
			stmt=conn.createStatement();
			this.sqlQuery=sqlQuery;
			count=stmt.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int DeleteAccount(int acc_num) {
		int count=0;
		try {
			conn=DriverManager.getConnection(dbURL,user,password);
			stmt=conn.createStatement();
			this.sqlQuery="delete from account where acc_num="+acc_num;
			count=stmt.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
