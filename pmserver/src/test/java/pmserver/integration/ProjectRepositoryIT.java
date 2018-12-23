package pmserver.integration;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.pm.config.ApplicationConfig;
import com.cts.pm.main.ProjectManagementMain;
import com.cts.pm.model.Project;
import com.cts.pm.repository.ProjectRepositoryDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ApplicationConfig.class, ProjectManagementMain.class })
public class ProjectRepositoryIT {

	@Resource(name="projectRepositoryDao")
	 private ProjectRepositoryDao projectRepositoryDao;
	
	@Test
	public void testProjectRepository() throws SQLException{
		List<Project> allProjects = projectRepositoryDao.getAllProjects();
		System.out.println(allProjects.size());
	}
	
	@Test
	public void testAddProject() throws SQLException {
		Project project = new Project();
		project.setProjectName("Test Project");
		project.setPriority(1);
		Long projectId = projectRepositoryDao.addNewProject(project);
		System.out.println("Added Project "+projectId);
		projectRepositoryDao.deleteProject(project);
	}
	
	@Test
	public void testUpdateProject() throws SQLException {
		Project project = new Project();
		project.setProjectName("TestQA Project");
		project.setPriority(2);
		projectRepositoryDao.addNewProject(project);
		project.setProjectName("Updated Project Name");
		projectRepositoryDao.updateProject(project);
		projectRepositoryDao.deleteProject(project);
	}
}
