package com.cts.pm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.pm.model.User;
import com.cts.pm.repository.UserRepositoryDao;

@Service
public class UserServiceImpl implements UserService {

	
	private UserRepositoryDao userRepositoryDao;

    @Autowired
    public UserServiceImpl(UserRepositoryDao userRepositoryDao) {
        this.userRepositoryDao = userRepositoryDao;
    }
	
	@Override
	public List<User> getAllUsers() {
		return userRepositoryDao.getAllUsers();
	}
	
	@Override
	public User getUser(Long userId) {
		return userRepositoryDao.getUser(userId);
	}

	@Override
	public Long addNewUser(User user){
		return userRepositoryDao.addNewUser(user);
		
	}

	@Override
	public void updateUser(User user){
		userRepositoryDao.updateUser(user);
	}

	@Override
	public void deleteUser(User user){
		userRepositoryDao.deleteUser(user);
	}

}
