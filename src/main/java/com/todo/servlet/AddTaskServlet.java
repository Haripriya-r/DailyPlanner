package com.todo.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.bo.AddTaskBo;
import com.todo.bo.HomeBo;
import com.todo.dao.AddTaskDao;
import com.todo.service.AddTaskService;
import com.todo.service.HomeService;

public class AddTaskServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		AddTaskBo addTaskBo=new AddTaskBo();
		
		String task=request.getParameter("task");
		addTaskBo.setTask(task);
		
		String date=request.getParameter("date");
		addTaskBo.setDate(date);
		
		String time=request.getParameter("time");
		addTaskBo.setTime(time);
		
		String email=request.getParameter("email");
		addTaskBo.setEmail(email);
		
		AddTaskService addTaskService=new AddTaskService();
		boolean verify=addTaskService.addTask(addTaskBo);
		
		try {
			
	      if (!verify) {
			response.getWriter().write("1");
			} 
			else {
			response.getWriter().write("0");
			}
	      
		}
		
		catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		
	}



