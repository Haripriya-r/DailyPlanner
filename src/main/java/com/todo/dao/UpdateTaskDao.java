package com.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.todo.bo.HomeBo;
import com.todo.bo.ResponseBo;
import com.todo.bo.UpdateTaskBo;
import com.todo.constants.TableConstants;
import com.todo.constants.TaskTableConstants;
import com.todo.singleton.DatabaseConnection;

public class UpdateTaskDao {

	public ResponseBo updateTask(UpdateTaskBo updateTaskBo) {
		Connection con=null;
	    PreparedStatement preparedStatement;
	    PreparedStatement preparedStmt;
	    int id=0;
	   
	    StringBuilder query = new StringBuilder();
	    query.append("select * from "+TableConstants.TABLE_NAME+" where ");
	    query.append(TableConstants.EMAIL+ "=?");
	    
	    
	    StringBuilder queryForTask = new StringBuilder();
	    queryForTask.append("UPDATE "+ TaskTableConstants.TABLE_NAME );
		queryForTask.append(" SET "+TaskTableConstants.COMPLETED+"=1");
		queryForTask.append(" WHERE "+TaskTableConstants.ID+"=?");
		queryForTask.append(" and "+TaskTableConstants.TASK+"=?");
		queryForTask.append(" and "+TaskTableConstants.DATETIME+"=?");
		 
		 ViewTaskDao viewTaskDao=new ViewTaskDao();
		 ResponseBo responseBo = null;
	    
	    
	     try {
			    con=DatabaseConnection.getDbConnection();
			    
			    preparedStatement = con.prepareStatement(query.toString());
			    preparedStatement.setString(1,updateTaskBo.getEmail());
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next())
				{
					id=rs.getInt(1);
				}
			    			 
				preparedStmt = con.prepareStatement(queryForTask.toString());
			    preparedStmt.setInt(1,id);
			    preparedStmt.setString(2,updateTaskBo.getTask());
			    preparedStmt.setString(3, updateTaskBo.getTime());
			    int count=preparedStmt.executeUpdate();
			    
			    
			    HomeBo homeBo=new HomeBo();
			    homeBo.setEmail(updateTaskBo.getEmail());
			    homeBo.setStatus(0);
			    responseBo=viewTaskDao.viewTask(homeBo);
			    	
		   }
		   catch(SQLException e) {
			   e.printStackTrace();
		   }
		   
		   
  		    return responseBo;
	}
	
}
	