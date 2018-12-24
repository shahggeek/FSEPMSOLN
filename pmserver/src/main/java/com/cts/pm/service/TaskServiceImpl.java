package com.cts.pm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.pm.model.Task;
import com.cts.pm.repository.TaskRepositoryDao;

public class TaskServiceImpl implements TaskService {

	private TaskRepositoryDao taskRepositoryDao;

    @Autowired
    public TaskServiceImpl(TaskRepositoryDao taskRepositoryDao) {
        this.taskRepositoryDao = taskRepositoryDao;
    }
	
    
	@Override
	public List<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task getTask(Long taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addNewTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteTask(Task task) {
		// TODO Auto-generated method stub
		return false;
	}

}
