package com.todo.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.todo.bo.HomeBo;
import com.todo.bo.ResponseBo;
import com.todo.service.ViewTaskService;

public class ViewTaskServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		HomeBo homeBo = new HomeBo();
		String email=request.getParameter("email");
		String val=request.getParameter("status");
		int status=Integer.parseInt(val);
		
		homeBo.setEmail(email);
		homeBo.setStatus(status);
		
		ViewTaskService viewTaskService=new ViewTaskService();
		ResponseBo responseBo=viewTaskService.viewTask(homeBo);
	    String task=new Gson().toJson(responseBo);
	   
	    try 
	    {
			response.getWriter().write(task);
		} 
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	    
		}

}
