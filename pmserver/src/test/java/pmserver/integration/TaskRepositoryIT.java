package pmserver.integration;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cts.pm.config.ApplicationConfig;
import com.cts.pm.main.ProjectManagementMain;
import com.cts.pm.model.Project;
import com.cts.pm.model.Task;
import com.cts.pm.model.User;
import com.cts.pm.repository.ProjectRepositoryDao;
import com.cts.pm.repository.TaskRepositoryDao;
import com.cts.pm.repository.UserRepositoryDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ApplicationConfig.class, ProjectManagementMain.class })
@Transactional
public class TaskRepositoryIT {

	@Resource(name="taskRepositoryDao")
	 private TaskRepositoryDao taskRepositoryDao;
	
	@Resource(name="projectRepositoryDao")
	 private ProjectRepositoryDao projectRepositoryDao;
	
	@Resource(name="userRepositoryDao")
	 private UserRepositoryDao userRepositoryDao;
	
	@Test
	public void testTaskRepository() throws SQLException{
		List<Task> allTasks = taskRepositoryDao.getAllTasks();
		System.out.println(allTasks.size());
	}
	
	@Test
	public void testAddTask() throws SQLException {
		Project project = new Project();
		project.setProjectName("Test Project");
		project.setPriority(1);
		projectRepositoryDao.addNewProject(project);
		Task task = new Task();
		task.setTaskName("Test Task");
		task.setStartDate(new Date());
		task.setEndDate(new Date());
		task.setPriority(2);
		task.setProject(project);
		Long taskId = taskRepositoryDao.addNewTask(task);
		task.setTaskName("Updated task name");
		taskRepositoryDao.updateTask(task);
		taskRepositoryDao.deleteTask(task);
		projectRepositoryDao.deleteProject(project);
	}

	@Test
	public void testAddTaskWithOwner() throws SQLException {
		
		User user = new User();
		user.setEmployeeId(123);
		user.setFirstName("TestUser");
		user.setLastName("TestUser");
		Long userId = userRepositoryDao.addNewUser(user);
		System.out.println("Added User "+userId);
		
		Project project = new Project();
		project.setProjectName("Test Project");
		project.setPriority(1);
		projectRepositoryDao.addNewProject(project);
		Task task = new Task();
		task.setTaskName("Test Task");
		task.setStartDate(new Date());
		task.setEndDate(new Date());
		task.setPriority(2);
		task.setProject(project);
		task.setUser(user);
		user.setTask(task);
		taskRepositoryDao.addNewTask(task);
		userRepositoryDao.updateUser(user);
		//taskRepositoryDao.deleteTask(task);
		//projectRepositoryDao.deleteProject(project);
	}
}
