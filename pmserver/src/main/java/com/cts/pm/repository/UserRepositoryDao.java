package com.cts.pm.repository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.pm.model.User;

@Repository(value="userRepositoryDao")
@Transactional
public class UserRepositoryDao {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public Set<User> getAllUsers() throws SQLException{
		Set<User> allUsers = new HashSet<User>();
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		HibernateUtils.loadAllData(User.class, session);
		return allUsers;
	}
	
}
