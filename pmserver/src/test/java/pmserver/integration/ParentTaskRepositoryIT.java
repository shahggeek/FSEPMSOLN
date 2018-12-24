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
import com.cts.pm.model.ParentTask;
import com.cts.pm.repository.ParentTaskRepositoryDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ApplicationConfig.class, ProjectManagementMain.class })
@Transactional
public class ParentTaskRepositoryIT {

	@Resource(name="parentTaskRepositoryDao")
	 private ParentTaskRepositoryDao parentTaskRepositoryDao;
	
	@Test
	public void testParentTaskRepository() throws SQLException{
		List<ParentTask> allParentTasks = parentTaskRepositoryDao.getAllParentTasks();
		System.out.println(allParentTasks.size());
	}
	
	@Test
	public void testAddParentTask() throws SQLException {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentTaskName("Test Parent Task");
		Long parentId = parentTaskRepositoryDao.addNewParentTask(parentTask);
		System.out.println("Added ParentTask "+parentId);
		parentTaskRepositoryDao.deleteParentTask(parentTask);
	}
	
	@Test
	public void testUpdateParentTask() throws SQLException {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentTaskName("Test Parent Task 2");
		parentTaskRepositoryDao.addNewParentTask(parentTask);
		parentTask.setParentTaskName("Updated Parent task");
		parentTaskRepositoryDao.updateParentTask(parentTask);
		parentTaskRepositoryDao.deleteParentTask(parentTask);
	}
}
