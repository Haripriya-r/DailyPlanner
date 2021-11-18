package com.todo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.bo.LoginBo;
import com.todo.service.LoginService;

public class LoginServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	{
		LoginBo loginBo=new LoginBo();	
		LoginService loginService=new LoginService();
		response.setContentType("text/plain");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		loginBo.setEmail(email);
		loginBo.setPassword(password);
		
		try {	
		   if(loginService.Login(loginBo)) {
			    response.getWriter().write("1");
		      }
		   else {
			response.getWriter().write("0");	
		      }
		    }
		
		catch(Exception e)
		  {
			e.printStackTrace();
		  }
		
		}

}

