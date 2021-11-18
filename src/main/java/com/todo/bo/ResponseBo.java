package com.todo.bo;

import java.util.ArrayList;

public class ResponseBo {
	private String mobile;
	private String name;
    private ArrayList<TaskBo> TaskList;

	public ArrayList<TaskBo> getTaskList() {
		return TaskList;
	}

	public void setTaskList(ArrayList<TaskBo> taskList) {
		TaskList = taskList;
	}

	
	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	 
}
