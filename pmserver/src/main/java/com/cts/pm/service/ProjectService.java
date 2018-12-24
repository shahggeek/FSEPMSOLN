package com.cts.pm.service;

import java.util.List;

import com.cts.pm.model.Project;

public interface ProjectService {

	List<Project> getAllProjects();
	
	Project getProject(Long projectId);
	
	Long addNewProject(Project project);
	
	void updateProject(Project project);
	
	void deleteProject(Project project);
	
}
