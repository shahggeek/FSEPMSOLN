package com.cts.pm.repository;

import java.util.List;

import org.hibernate.Session;
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
		Long parentTaskId = (Long) session.save(parentTask);
		return parentTaskId;
	}
	
	public void updateParentTask(ParentTask parentTask) {
		Session session = getSession();
		session.saveOrUpdate(parentTask);
	}
	
	public void deleteParentTask(ParentTask parentTask) {
		Session session = getSession();
		session.delete(session.load(ParentTask.class, parentTask.getParentId()));
	}
	
}
