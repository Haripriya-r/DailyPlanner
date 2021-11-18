package com.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.todo.bo.AddTaskBo;
import com.todo.constants.TableConstants;
import com.todo.constants.TaskTableConstants;
import com.todo.singleton.DatabaseConnection;

public class AddTaskDao {

	public boolean addTask(AddTaskBo addTaskBo) {
		int id = 0;
	    boolean error=false;
	    String DateTime=addTaskBo.getDate()+" "+addTaskBo.getTime();
	    
		Connection con=null;
	    PreparedStatement preparedStmt;
	    PreparedStatement preparedStatement;
	    
	    
		   try {
			   
			    con=DatabaseConnection.getDbConnection();
			    StringBuilder query = new StringBuilder();
			    
			    StringBuilder queryForId = new StringBuilder();
			    queryForId.append("select * from "+TableConstants.TABLE_NAME+" where ");
			    queryForId.append(TableConstants.EMAIL+ "=?");
			    preparedStmt = con.prepareStatement(queryForId.toString());
			    preparedStmt.setString(1,addTaskBo.getEmail());
				ResultSet rs = preparedStmt.executeQuery();
			    
				while(rs.next())
				 {
					id=rs.getInt(1);
				 }
			    
			    query.append("Insert into "+ TaskTableConstants.TABLE_NAME+"(" );
			    query.append(TaskTableConstants.ID+","+TaskTableConstants.TASK+","+TaskTableConstants.DATETIME+","+TaskTableConstants.COMPLETED + ")" );
			    query.append("VALUES (?,?,?,?)");
			    preparedStatement = con.prepareStatement(query.toString());
			    preparedStatement.setInt(1,id);
				preparedStatement.setString(2, addTaskBo.getTask());
				preparedStatement.setString(3,DateTime);
				preparedStatement.setInt(4,0);
			    
			    preparedStatement.executeUpdate();
			    
			    
		        }
		   catch(Exception e) {
			   
			   e.printStackTrace();
			   error=true;
			   
		   }
	
		   return error;
	}

}
