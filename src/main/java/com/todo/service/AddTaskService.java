package com.todo.service;

import com.todo.bo.AddTaskBo;
import com.todo.dao.AddTaskDao;

public class AddTaskService {

	public boolean addTask(AddTaskBo addTaskBo) {
		
		AddTaskDao addTaskDao=new AddTaskDao();
		return addTaskDao.addTask(addTaskBo);
		
	}

}
