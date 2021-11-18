package com.todo.singleton;

import java.sql.Connection;
import java.sql.DriverManager;

import com.todo.constants.DbConstants;

public class DatabaseConnection {
	private static Connection con = null;
	private DatabaseConnection(){	
		
		try{  	
			Class.forName(DbConstants.DRIVER_NAME);
			con=DriverManager.getConnection(DbConstants.URL,DbConstants.USERNAME,DbConstants.PASSWORD);		
		}
		catch(Exception e){
			System.out.println(e);
		}		 
	}  
	public static Connection getDbConnection() {
		if(con==null) {
			new DatabaseConnection();
		}
		return con;
	}
}
