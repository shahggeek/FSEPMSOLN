package com.cts.pm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.pm.model.Project;
import com.cts.pm.repository.ProjectRepositoryDao;

@Service
public class ProjectServiceImpl implements ProjectService {

	private ProjectRepositoryDao projectRepositoryDao;

    @Autowired
    public ProjectServiceImpl(ProjectRepositoryDao projectRepositoryDao) {
        this.projectRepositoryDao = projectRepositoryDao;
    }
    
	@Override
	public List<Project> getAllProjects() {
		return projectRepositoryDao.getAllProjects();
	}

	@Override
	public Project getProject(Long projectId) {
		return projectRepositoryDao.getProject(projectId);
	}

	@Override
	public Long addNewProject(Project project) {
		return projectRepositoryDao.addNewProject(project);
	}

	@Override
	public void updateProject(Project project) {
		projectRepositoryDao.updateProject(project);
	}

	@Override
	public void deleteProject(Project project) {
		projectRepositoryDao.deleteProject(project);
	}

}
