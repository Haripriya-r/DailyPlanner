package com.todo.service;

import com.todo.bo.SignupBo;
import com.todo.dao.SignupDao;

public class SignupService {
	
	public int signup(SignupBo signupBo) {
		
		return new SignupDao().SignupDao(signupBo);
		
	}

}
