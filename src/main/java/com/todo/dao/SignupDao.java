package com.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.todo.bo.SignupBo;
import com.todo.constants.TableConstants;
import com.todo.singleton.DatabaseConnection;

public class SignupDao {
	public  int SignupDao(SignupBo signupBo) {
	Connection con=null;
    PreparedStatement preparedStatement;
    int error_code=0;
	   try {
		    con=DatabaseConnection.getDbConnection();
		    StringBuilder query = new StringBuilder();
		    query.append("Insert into "+ TableConstants.TABLE_NAME+"(" );
			query.append(TableConstants.EMAIL +","+ TableConstants.NAME +","+ TableConstants.PASSWORD + "," );
			query.append(TableConstants.PHONE +")" );
			query.append(" VALUES (?,?,?,?)");
			preparedStatement = con.prepareStatement(query.toString());
			preparedStatement.setString(1,signupBo.getEmail());
			preparedStatement.setString(2, signupBo.getUsername());
			preparedStatement.setString(3, signupBo.getPassword());
			preparedStatement.setString(4, signupBo.getMobile());
			int updateCount = preparedStatement.executeUpdate();
			if(updateCount!=0) {
			error_code=updateCount;
			}
			}
	   
	   catch (SQLException e) {
			e.printStackTrace();
		    if (e.getErrorCode()==1062) 
			error_code=-1;
			}
	   
		return error_code;
}
	
}
