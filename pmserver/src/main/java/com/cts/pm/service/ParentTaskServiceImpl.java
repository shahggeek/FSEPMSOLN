package com.cts.pm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.pm.model.ParentTask;
import com.cts.pm.repository.ParentTaskRepositoryDao;

public class ParentTaskServiceImpl implements ParentTaskService {

	private ParentTaskRepositoryDao parentTaskRepositoryDao;

    @Autowired
    public ParentTaskServiceImpl(ParentTaskRepositoryDao parentTaskRepositoryDao) {
        this.parentTaskRepositoryDao = parentTaskRepositoryDao;
    }
	
    
	@Override
	public List<ParentTask> getAllParentTasks() {
		return parentTaskRepositoryDao.getAllParentTasks();
	}

	@Override
	public ParentTask getParentTask(Long parentId) {
		return parentTaskRepositoryDao.getParentTask(parentId);
	}

	@Override
	public Long addNewParentTask(ParentTask parentTask) {
		return parentTaskRepositoryDao.addNewParentTask(parentTask);
	}

}
