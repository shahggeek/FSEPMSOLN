package pmserver.unit;

import java.sql.SQLException;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.pm.config.ApplicationConfig;
import com.cts.pm.main.ProjectManagementMain;
import com.cts.pm.model.User;
import com.cts.pm.repository.UserRepositoryDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ApplicationConfig.class, ProjectManagementMain.class })
public class UserRepositoryTest {

	@Resource(name="userRepositoryDao")
	 private UserRepositoryDao userRepositoryDao;
	
	@Test
	public void testUserRepository() throws SQLException{
		Set<User> allUsers = userRepositoryDao.getAllUsers();
		System.out.println(allUsers.size());
	}
}
