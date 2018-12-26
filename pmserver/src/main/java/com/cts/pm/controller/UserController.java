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
import com.cts.pm.model.User;
import com.cts.pm.service.UserService;

@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") long userId){
    	User user = userService.getUser(userId);
    	if (user == null){
            throw new DataAccessException("User doesn´t exist with Id "+userId);
    	}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
   	public ResponseEntity<?> addUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
		Long createdUserId = userService.addNewUser(user);
		LOGGER.info("Added userId:"+createdUserId);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(createdUserId).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
   	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
   	public ResponseEntity<User> updateUser(@PathVariable("id") long userId,@RequestBody User user){
		User currentUser = userService.getUser(userId);
		 if (currentUser == null) {
			 throw new DataAccessException("User doesn´t exist to Update "+userId);
	     }
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setEmployeeId(user.getEmployeeId());
		userService.updateUser(user);
		return new ResponseEntity<User>(HttpStatus.OK);
   	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUserById(@PathVariable("id") long userId){
		User user = userService.getUser(userId);
		if (user == null){
            throw new DataAccessException("User doesn´t exist to Delete");
    	}
		userService.deleteUser(user);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
}
