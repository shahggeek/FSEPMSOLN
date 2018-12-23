package com.cts.pm.repository;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.pm.model.User;

@Repository(value="userRepositoryDao")
@Transactional
public class UserRepositoryDao extends PMRepository{

	public List<User> getAllUsers() throws SQLException{
		List<User> allUsers = loadAllData(User.class, getSession());
		return allUsers;
	}
	
	public User getUser(Long userId) throws SQLException{
		Session session = getSession();
		return session.get(User.class, userId);
	}
	
	public Long addNewUser(User user) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Long userId = (Long) session.save(user);
		transaction.commit();
		return userId;
	}
	
	public void updateUser(User user) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(user);
		transaction.commit();
	}
	
	public void deleteUser(User user) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(session.load(User.class, user.getUserId()));
		transaction.commit();
	}
	
}
