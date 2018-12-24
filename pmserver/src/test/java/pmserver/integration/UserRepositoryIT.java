package pmserver.integration;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cts.pm.config.ApplicationConfig;
import com.cts.pm.main.ProjectManagementMain;
import com.cts.pm.model.User;
import com.cts.pm.repository.UserRepositoryDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ApplicationConfig.class, ProjectManagementMain.class })
@Transactional
public class UserRepositoryIT {

	@Resource(name="userRepositoryDao")
	 private UserRepositoryDao userRepositoryDao;
	
	@Test
	public void testUserRepository() throws SQLException{
		List<User> allUsers = userRepositoryDao.getAllUsers();
		System.out.println(allUsers.size());
	}
	
	@Test
	public void testAddUser() throws SQLException {
		User user = new User();
		user.setEmployeeId(123);
		user.setFirstName("TestUser");
		user.setLastName("TestUser");
		Long userId = userRepositoryDao.addNewUser(user);
		System.out.println("Added User "+userId);
		userRepositoryDao.deleteUser(user);
	}
	
	@Test
	public void testUpdateUser() throws SQLException {
		User user = new User();
		user.setEmployeeId(345);
		user.setFirstName("TestUpdateUser");
		user.setLastName("TestUpdateUser");
		userRepositoryDao.addNewUser(user);
		user.setFirstName("Updated Name");
		userRepositoryDao.updateUser(user);
		userRepositoryDao.deleteUser(user);
	}
}
