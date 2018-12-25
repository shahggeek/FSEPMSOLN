package com.cts.pm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.pm.model.Task;
import com.cts.pm.repository.TaskRepositoryDao;

@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepositoryDao taskRepositoryDao;

    @Autowired
    public TaskServiceImpl(TaskRepositoryDao taskRepositoryDao) {
        this.taskRepositoryDao = taskRepositoryDao;
    }
	
    
	@Override
	public List<Task> getAllTasks() {
		return taskRepositoryDao.getAllTasks();
	}

	@Override
	public Task getTask(Long taskId) {
		return taskRepositoryDao.getTask(taskId);
	}

	@Override
	public Long addNewTask(Task task) {
		return taskRepositoryDao.addNewTask(task);
	}

	@Override
	public void updateTask(Task task) {
		taskRepositoryDao.updateTask(task);;
	}

	@Override
	public void deleteTask(Task task) {
		taskRepositoryDao.deleteTask(task);;
	}

}
