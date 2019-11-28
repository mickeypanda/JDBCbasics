package com.mickey.jdbc.beans;

public class Account {
	public String lastname;
	public String firstname;
	public int acc_num;
	public int bal;
	
	@Override
	public String toString() {
		return firstname+" "+lastname+","+"bal="+bal+","; 
	}
}
