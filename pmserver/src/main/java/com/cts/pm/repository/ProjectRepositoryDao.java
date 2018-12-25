package com.cts.pm.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.pm.model.Project;

@Repository(value="projectRepositoryDao")
@Transactional
public class ProjectRepositoryDao extends PMRepository {

	public List<Project> getAllProjects(){
		List<Project> allProjects = loadAllData(Project.class, getSession());
		return allProjects;
	}
	
	public Project getProject(Long projectId){
		Session session = getSession();
		return session.get(Project.class, projectId);
	}
	
	public Long addNewProject(Project project) {
		Session session = getSession();
		Long projectId = (Long) session.save(project);
		return projectId;
	}
	
	public void updateProject(Project project) {
		Session session = getSession();
		session.saveOrUpdate(project);
	}
	
	public void deleteProject(Project project) {
		Session session = getSession();
		session.delete(session.load(Project.class, project.getProjectId()));
	}
	
}
