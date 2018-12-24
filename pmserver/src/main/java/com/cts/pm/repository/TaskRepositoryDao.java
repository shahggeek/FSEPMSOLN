package com.cts.pm.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
		Transaction transaction = session.beginTransaction();
		Long taskId = (Long) session.save(task);
		transaction.commit();
		return taskId;
	}
	
	public void updateTask(Task task) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(task);
		transaction.commit();
	}
	
	public void deleteTask(Task task) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(session.load(Task.class, task.getTaskId()));
		transaction.commit();
	}
	
}
