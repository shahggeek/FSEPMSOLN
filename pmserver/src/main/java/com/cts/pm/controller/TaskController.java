package com.cts.pm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cts.pm.exceptions.DataAccessException;
import com.cts.pm.model.ParentTask;
import com.cts.pm.model.Project;
import com.cts.pm.model.Task;
import com.cts.pm.model.User;
import com.cts.pm.service.ParentTaskService;
import com.cts.pm.service.ProjectService;
import com.cts.pm.service.TaskService;
import com.cts.pm.service.UserService;

@RestController
@CrossOrigin
public class TaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ParentTaskService parentService;
	
	@Autowired
	ProjectService projectService;
	
	@RequestMapping(value="/tasks", method=RequestMethod.GET)
	public ResponseEntity<List<Task>> getAllTasks(){
		return new ResponseEntity<List<Task>>(taskService.getAllTasks(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
	public ResponseEntity<Task> getTaskById(@PathVariable("id") long taskId){
		Task task = taskService.getTask(taskId);
    	if (task == null){
            throw new DataAccessException("Task doesn´t exist");
    	}
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tasks", method = RequestMethod.POST)
   	public ResponseEntity<?> addTask(@RequestBody Task task, UriComponentsBuilder ucBuilder){
		Long userId = task.getUser().getUserId();
		User user = userService.getUser(userId);
		task.setUser(user);
		Long createdTaskId = taskService.addNewTask(task);
		user.setTask(task);
		userService.updateUser(user);
		LOGGER.info("Added TaskId:"+createdTaskId);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/tasks/{id}").buildAndExpand(createdTaskId).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
   	}
	
	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
   	public ResponseEntity<Task> updateTask(@PathVariable("id") long taskId,@RequestBody Task task){
		Task currentTask = taskService.getTask(taskId);
		 if (currentTask == null) {
			 throw new DataAccessException("Task doesn´t exist to Update");
	     }
		currentTask.setTaskName(task.getTaskName());
		currentTask.setStartDate(task.getStartDate());
		currentTask.setEndDate(task.getEndDate());
		currentTask.setPriority(task.getPriority());
		currentTask.setStatus(task.getStatus());
		taskService.updateTask(currentTask);
		return new ResponseEntity<Task>(HttpStatus.OK);
   	}
	
	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Task> deleteTaskById(@PathVariable("id") long taskId){
		LOGGER.info("Deleting TaskId:"+taskId);
		Task task = taskService.getTask(taskId);
		if (task == null){
			LOGGER.error("Task doesn´t exist to Delete"+taskId);
            throw new DataAccessException("Task doesn´t exist to Delete");
    	}
		if(task.getUser() != null && task.getUser().getUserId() != 0){
			User user = userService.getUser(task.getUser().getUserId());
			user.setTask(null);
			userService.updateUser(user);
		}
		task.setUser(null);
		taskService.deleteTask(task);
		return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
	}
	
}
