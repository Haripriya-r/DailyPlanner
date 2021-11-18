package com.todo.service;

import com.todo.bo.HomeBo;
import com.todo.bo.ResponseBo;
import com.todo.dao.HomeDao;

public class HomeService {

public ResponseBo GetHomePage(HomeBo HomeBo) {
		
		return new HomeDao().GetHomePage(HomeBo);
}
}
