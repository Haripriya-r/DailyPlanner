package com.todo.service;

import com.todo.bo.HomeBo;
import com.todo.bo.ResponseBo;
import com.todo.dao.ViewTaskDao;

public class ViewTaskService {

  public ResponseBo viewTask(HomeBo homeBo) {
	 
	  ViewTaskDao viewTaskDao=new ViewTaskDao();
	  return viewTaskDao.viewTask(homeBo);
	  
	}

}
