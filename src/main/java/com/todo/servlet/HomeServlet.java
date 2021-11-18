package com.todo.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.todo.bo.HomeBo;
import com.todo.bo.ResponseBo;
import com.todo.service.HomeService;

public class HomeServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		HomeBo homeBo=new HomeBo();
		HomeService HomeService=new HomeService();
		
		String email=request.getParameter("email");
		homeBo.setEmail(email);
		
		ResponseBo responseBo=HomeService.GetHomePage(homeBo);
		String task=new Gson().toJson(responseBo);
		try {
			response.getWriter().write(task);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
