package com.cts.pm.service;

import java.util.List;

import com.cts.pm.model.ParentTask;


public interface ParentTaskService {

	List<ParentTask> getAllParentTasks();
	
	ParentTask getParentTask(Long parentId);
	
	Long addNewParentTask(ParentTask parentTask);
	
}
