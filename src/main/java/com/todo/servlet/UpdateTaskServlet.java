package com.todo.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.todo.bo.ResponseBo;
import com.todo.bo.UpdateTaskBo;
import com.todo.service.UpdateTaskService;

public class UpdateTaskServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		UpdateTaskBo updateTaskBo=new UpdateTaskBo();	
		UpdateTaskService updateTaskService=new UpdateTaskService();
		
	    String task=request.getParameter("task");
	    String time=request.getParameter("time");
	    String email= request.getParameter("email");
	    updateTaskBo.setEmail(email);
	    updateTaskBo.setTask(task);
	    updateTaskBo.setTime(time);
	    ResponseBo responseBo=updateTaskService.updateTask(updateTaskBo);
	    
	    
	    String taskList=new Gson().toJson(responseBo);
	   
	    try 
	      {
			response.getWriter().write(taskList);
		  } 
	    catch (IOException e) 
	      {
			e.printStackTrace();
		  }
	    
	}
}
