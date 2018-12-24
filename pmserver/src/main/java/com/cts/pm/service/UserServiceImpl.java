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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addNewUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
