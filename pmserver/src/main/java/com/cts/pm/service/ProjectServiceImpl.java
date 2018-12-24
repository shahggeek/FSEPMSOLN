package com.cts.pm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.pm.model.Project;
import com.cts.pm.repository.ProjectRepositoryDao;

public class ProjectServiceImpl implements ProjectService {

	private ProjectRepositoryDao projectRepositoryDao;

    @Autowired
    public ProjectServiceImpl(ProjectRepositoryDao projectRepositoryDao) {
        this.projectRepositoryDao = projectRepositoryDao;
    }
    
	@Override
	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getProject(Long projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addNewProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProject(Project project) {
		// TODO Auto-generated method stub
		return false;
	}

}
