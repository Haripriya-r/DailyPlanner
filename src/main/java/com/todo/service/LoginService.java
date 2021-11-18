package com.todo.service;

import com.todo.bo.LoginBo;
import com.todo.dao.LoginDao;


public class LoginService {
	
 public Boolean Login(LoginBo loginBo) {
		
	 return new LoginDao().Login(loginBo);
		
	}

}
