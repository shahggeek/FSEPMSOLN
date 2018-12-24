package com.cts.pm.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.pm.model.ParentTask;

@Repository(value="parentTaskRepositoryDao")
@Transactional
public class ParentTaskRepositoryDao extends PMRepository {

	public List<ParentTask> getAllParentTasks(){
		List<ParentTask> allParentTasks = loadAllData(ParentTask.class, getSession());
		return allParentTasks;
	}
	
	public ParentTask getParentTask(Long parentTaskId){
		Session session = getSession();
		return session.get(ParentTask.class, parentTaskId);
	}
	
	public Long addNewParentTask(ParentTask parentTask) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Long parentTaskId = (Long) session.save(parentTask);
		transaction.commit();
		return parentTaskId;
	}
	
	public void updateParentTask(ParentTask parentTask) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(parentTask);
		transaction.commit();
	}
	
	public void deleteParentTask(ParentTask parentTask) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(session.load(ParentTask.class, parentTask.getParentId()));
		transaction.commit();
	}
	
}
