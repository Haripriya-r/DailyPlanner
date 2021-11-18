package com.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.todo.bo.HomeBo;
import com.todo.bo.ResponseBo;
import com.todo.bo.TaskBo;
import com.todo.constants.TableConstants;
import com.todo.constants.TaskTableConstants;
import com.todo.singleton.DatabaseConnection;

public class HomeDao {

	public ResponseBo GetHomePage(HomeBo homeBo) {
		Connection con=null;
	    PreparedStatement preparedStatement;
	    PreparedStatement preparedStmt;
	    
	    ResponseBo responseBo=new ResponseBo();
	    ArrayList<TaskBo> taskList= new ArrayList<TaskBo>();
	    int id=0;
	   
	    
		   try {
			    
			    con=DatabaseConnection.getDbConnection();
			    StringBuilder queryForId = new StringBuilder();
			    queryForId.append("select * from "+TableConstants.TABLE_NAME+" where ");
			    queryForId.append(TableConstants.EMAIL+ "=?");
				preparedStmt = con.prepareStatement(queryForId.toString());
				preparedStmt.setString(1,homeBo.getEmail());
				ResultSet rs = preparedStmt.executeQuery();
				
				while(rs.next()) 
				   {
					id=rs.getInt(1);
					responseBo.setName(rs.getString(3));
					responseBo.setMobile(rs.getString(4));
					}
				
				 StringBuilder query = new StringBuilder();
	             query.append("select * from "+TaskTableConstants.TABLE_NAME+" where ");
				 query.append(TaskTableConstants.ID+ "=?");
				 query.append(" order by "+TaskTableConstants.DATETIME);				
				 preparedStatement = con.prepareStatement(query.toString());
				 preparedStatement.setInt(1,id);
					
				 ResultSet resultSet = preparedStatement.executeQuery();
					
					while(resultSet.next())
					  {
				        TaskBo taskBo=new TaskBo();
						taskBo.setTask(resultSet.getString(3));	
						taskBo.setDateTime(resultSet.getString(4));
						taskBo.setCompleted(resultSet.getInt(5));
						taskList.add(taskBo);
						
					   }
					
					}
			  
		   
		    catch (SQLException e) 
		      {
				e.printStackTrace();
			  }
				
		   
		    responseBo.setTaskList(taskList);		 
	        return responseBo;
			}
	
}

