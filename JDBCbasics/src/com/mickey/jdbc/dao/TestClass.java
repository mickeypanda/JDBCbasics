package com.mickey.jdbc.dao;

import java.util.ArrayList;

import com.mickey.jdbc.beans.Account;

public class TestClass {

	public static void main(String[] args) {
		
		//Create account
		
//		int count=AccountDAO.account.CreateAccount(1003, "Roy", "Mc", 13000);
//		if(count>0) {
//			System.out.println(count+" rows created.");
//		}
//		else {
//			System.out.println("No rows inserted.");
//		}
		
		
		//Read accounts
		
		ArrayList<Account> accounts=AccountDAO.account.ReadAccount("select * from account");
		if(!accounts.isEmpty()) {
			for(Account ac: accounts){
				System.out.println(ac);
			}
		}
		
		
		//Update account
		
//		int count=AccountDAO.account.UpdateAccount("update account set bal=30000 where acc_num=1003");
//		if(count>0) {
//			System.out.println(count+" row updated.");
//		}
//		else {
//			System.out.println("No rows updated.");
//		}
		
		//Delete Account
		
//		int count=AccountDAO.account.DeleteAccount(1001);
//		if(count>0) {
//			System.out.println(count+"rows deleted");
//		}else {
//			System.out.println("no rows deleted");
//		}
		
	}

}
