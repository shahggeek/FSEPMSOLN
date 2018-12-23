package com.cts.pm.repository;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.pm.model.Project;

@Repository(value="projectRepositoryDao")
@Transactional
public class ProjectRepositoryDao extends PMRepository {

	public List<Project> getAllProjects() throws SQLException{
		List<Project> allProjects = loadAllData(Project.class, getSession());
		return allProjects;
	}
	
	public Project getProject(Long projectId) throws SQLException{
		Session session = getSession();
		return session.get(Project.class, projectId);
	}
	
	public Long addNewProject(Project project) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Long projectId = (Long) session.save(project);
		transaction.commit();
		return projectId;
	}
	
	public void updateProject(Project project) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(project);
		transaction.commit();
	}
	
	public void deleteProject(Project project) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(session.load(Project.class, project.getProjectId()));
		transaction.commit();
	}
	
}
