package com.cts.pm.service;

import java.util.List;

import com.cts.pm.model.User;

public interface UserService {

	List<User> getAllUsers();
	
	User getUser(Long userId);
	
	Long addNewUser(User user);
	
	Long updateUser(User user);
	
	boolean deleteUser(User user);
}
