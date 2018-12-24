package com.cts.pm.service;

import java.util.List;

import com.cts.pm.model.Task;

public interface TaskService {

	List<Task> getAllTasks();
	
	Task getTask(Long taskId);
	
	Long addNewTask(Task task);
	
	void updateTask(Task task);
	
	void deleteTask(Task task);
	
}
