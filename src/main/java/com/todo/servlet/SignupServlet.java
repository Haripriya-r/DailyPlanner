package com.todo.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.bo.SignupBo;
import com.todo.service.SignupService;

public class SignupServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		SignupService signupService=new SignupService();
		int error_code;
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		
		SignupBo signupBo=new SignupBo();
		signupBo.setUsername(userName);
		signupBo.setPassword(password);
		signupBo.setEmail(email);
		signupBo.setMobile(mobile);
		
         error_code=signupService.signup(signupBo);
         String s=""+error_code;
         try 
            {
			response.getWriter().write(s);
		     } 
         catch (IOException e)
           {
			e.printStackTrace();
		   }
		
  		}
        
        
	}


