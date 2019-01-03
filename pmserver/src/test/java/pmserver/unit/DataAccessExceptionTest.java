package pmserver.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cts.pm.config.ApplicationConfig;
import com.cts.pm.controller.ProjectController;
import com.cts.pm.exceptions.DataAccessException;
import com.cts.pm.main.ProjectManagementMain;
import com.cts.pm.model.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ApplicationConfig.class, ProjectManagementMain.class })
@WebAppConfiguration
public class DataAccessExceptionTest {

	@Autowired
	ProjectController projectController;
	
	@Test(expected=DataAccessException.class)
	public void testExceptionDeleteProject(){
		projectController.deleteProjectById(0l);
	}
	
	@Test(expected=DataAccessException.class)
	public void testExceptionAddProject(){
		projectController.updateProject(0l, new Project());
	}
	
	@Test(expected=DataAccessException.class)
	public void testException(){
		throw new DataAccessException("Test");
	}
}
