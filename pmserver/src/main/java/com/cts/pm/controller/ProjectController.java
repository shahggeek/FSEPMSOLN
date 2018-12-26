package com.cts.pm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cts.pm.exceptions.DataAccessException;
import com.cts.pm.model.Project;
import com.cts.pm.service.ProjectService;

@RestController
public class ProjectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	ProjectService projectService;
	
	
	@RequestMapping(value="/projects", method=RequestMethod.GET)
	public ResponseEntity<List<Project>> getAllProjects(){
		return new ResponseEntity<List<Project>>(projectService.getAllProjects(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public ResponseEntity<Project> getProjectById(@PathVariable("id") long projectId){
		Project project = projectService.getProject(projectId);
    	if (project == null){
            throw new DataAccessException("Project doesn´t exist");
    	}
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/projects", method = RequestMethod.POST)
   	public ResponseEntity<?> addProject(@RequestBody Project project, UriComponentsBuilder ucBuilder){
		Long createdProjectId = projectService.addNewProject(project);
		LOGGER.info("Added ProjectId:"+createdProjectId);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/projects/{id}").buildAndExpand(createdProjectId).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
   	}
	
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.PUT)
   	public ResponseEntity<Project> updateProject(@PathVariable("id") long projectId,@RequestBody Project project){
		Project currentProject = projectService.getProject(projectId);
		 if (currentProject == null) {
			 throw new DataAccessException("Project doesn´t exist to Update");
	        }
		currentProject.setStartDate(project.getStartDate());
		currentProject.setEndDate(project.getEndDate());
		currentProject.setPriority(project.getPriority());
		currentProject.setProjectName(project.getProjectName());
		projectService.updateProject(currentProject);
		return new ResponseEntity<Project>(HttpStatus.OK);
   	}
	
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Project> deleteProjectById(@PathVariable("id") long projectId){
		Project project = projectService.getProject(projectId);
		if (project == null){
            throw new DataAccessException("Project doesn´t exist to Delete");
    	}
		projectService.deleteProject(project);
		return new ResponseEntity<Project>(HttpStatus.NO_CONTENT);
	}
}
