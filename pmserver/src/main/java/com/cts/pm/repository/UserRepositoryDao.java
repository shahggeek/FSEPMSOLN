package com.cts.pm.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.pm.model.User;

@Repository(value="userRepositoryDao")
@Transactional
public class UserRepositoryDao extends PMRepository{

	public List<User> getAllUsers(){
		List<User> allUsers = loadAllData(User.class, getSession());
		return allUsers;
	}
	
	public User getUser(Long userId){
		Session session = getSession();
		return session.get(User.class, userId);
	}
	
	public Long addNewUser(User user) {
		Session session = getSession();
		Long userId = (Long) session.save(user);
		return userId;
	}
	
	public void updateUser(User user) {
		Session session = getSession();
		session.saveOrUpdate(user);
	}
	
	public void deleteUser(User user) {
		Session session = getSession();
		session.delete(session.load(User.class, user.getUserId()));
	}
	
}
