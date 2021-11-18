package com.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.todo.bo.LoginBo;
import com.todo.constants.TableConstants;
import com.todo.singleton.DatabaseConnection;

public class LoginDao {
	
  public boolean Login(LoginBo loginBo) {
	  
	  boolean verify=false;
	  int count=0;
	 	
	  Connection con=null;
	  PreparedStatement preparedStatement;
	 
		   try {
			   
			   System.out.println("working");
			    con=DatabaseConnection.getDbConnection();
			    StringBuilder query = new StringBuilder();
                query.append("select count(*) from "+TableConstants.TABLE_NAME+" where ");
				query.append(TableConstants.EMAIL+ "=? and " + TableConstants.PASSWORD + "=?");
				preparedStatement = con.prepareStatement(query.toString());
				preparedStatement.setString(1,loginBo.getEmail());
				preparedStatement.setString(2, loginBo.getPassword());
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					count=rs.getInt(1);
					 }
				}
		   
		    catch (SQLException e) {
				e.printStackTrace();
				}
				
			if(count==1) 
			   verify=true; 
			 
		    return verify;
	
			}
}