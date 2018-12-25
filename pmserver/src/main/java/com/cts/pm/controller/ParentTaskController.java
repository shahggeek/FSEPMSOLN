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
import com.cts.pm.model.ParentTask;
import com.cts.pm.service.ParentTaskService;

@RestController
public class ParentTaskController {



	private static final Logger LOGGER = LoggerFactory.getLogger(ParentTaskController.class);
	
	@Autowired
	ParentTaskService parentTaskService;
	
	
	@RequestMapping(value="/parenttasks", method=RequestMethod.GET)
	public ResponseEntity<List<ParentTask>> getAllTasks(){
		return new ResponseEntity<List<ParentTask>>(parentTaskService.getAllParentTasks(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/parenttasks/{id}", method = RequestMethod.GET)
	public ResponseEntity<ParentTask> getTaskById(@PathVariable("id") long parentId) throws DataAccessException{
		ParentTask parentTask = parentTaskService.getParentTask(parentId);
    	if (parentTask == null){
            throw new DataAccessException("User doesn´t exist");
    	}
		return new ResponseEntity<ParentTask>(parentTask, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/parenttasks", method = RequestMethod.POST)
   	public ResponseEntity<?> addTask(@RequestBody ParentTask parentTask, UriComponentsBuilder ucBuilder) throws DataAccessException{
		Long createdParentTaskId = parentTaskService.addNewParentTask(parentTask);
		LOGGER.info("Added Parent Task:"+createdParentTaskId);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/parenttasks/{id}").buildAndExpand(createdParentTaskId).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
   	}
	
	
}
