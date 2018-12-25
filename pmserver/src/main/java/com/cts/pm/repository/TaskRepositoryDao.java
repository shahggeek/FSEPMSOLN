package com.cts.pm.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.pm.model.Task;

@Repository(value="taskRepositoryDao")
@Transactional
public class TaskRepositoryDao extends PMRepository {

	public List<Task> getAllTasks(){
		List<Task> allTasks = loadAllData(Task.class, getSession());
		return allTasks;
	}
	
	public Task getTask(Long taskId){
		Session session = getSession();
		return session.get(Task.class, taskId);
	}
	
	public Long addNewTask(Task task) {
		Session session = getSession();
		Long taskId = (Long) session.save(task);
		return taskId;
	}
	
	public void updateTask(Task task) {
		Session session = getSession();
		session.saveOrUpdate(task);
	}
	
	public void deleteTask(Task task) {
		Session session = getSession();
		session.delete(session.load(Task.class, task.getTaskId()));
	}
	
}
