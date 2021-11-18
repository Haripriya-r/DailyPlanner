package com.todo.service;

import com.todo.bo.ResponseBo;
import com.todo.bo.UpdateTaskBo;
import com.todo.dao.UpdateTaskDao;

public class UpdateTaskService {

	public ResponseBo updateTask(UpdateTaskBo updateTaskBo) {
		
		UpdateTaskDao updateTaskDao=new UpdateTaskDao();
		return updateTaskDao.updateTask(updateTaskBo);
	}

}
