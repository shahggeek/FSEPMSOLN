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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParentTask getParentTask(Long parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addNewParentTask(ParentTask parentTask) {
		// TODO Auto-generated method stub
		return null;
	}

}
