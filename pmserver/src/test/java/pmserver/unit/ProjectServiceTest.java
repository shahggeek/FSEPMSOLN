package pmserver.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cts.pm.model.Project;
import com.cts.pm.repository.ProjectRepositoryDao;
import com.cts.pm.service.ProjectServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

	@Mock
	private ProjectRepositoryDao projectRepositoryDao;

	@InjectMocks
	private ProjectServiceImpl projectService ;


	@Test
    public void getAllProjectsTest() throws SQLException
    {
        List<Project> list = new ArrayList<Project>();
        Project projectOne = getTestProject();
        
        Project projectTwo = new Project();
        projectTwo.setProjectName("Test Project 2");
        projectTwo.setPriority(2);
        projectTwo.setStartDate(new Date());
        
        list.add(projectOne);
        list.add(projectTwo);
        
         
        when(projectRepositoryDao.getAllProjects()).thenReturn(list);
         
        //test
        List<Project> projectList = projectService.getAllProjects();
         
        assertEquals(2, projectList.size());
        verify(projectRepositoryDao, times(1)).getAllProjects();
    }
	
	  
    @Test
    public void getProjectTest() throws SQLException
    {
    	Project projectOne = getTestProject();
        
        when(projectRepositoryDao.getProject(1l)).thenReturn(projectOne);
         
        Project project = projectService.getProject(1l);
         
        assertEquals("Test Project 1", project.getProjectName());
        assertEquals(1, project.getPriority());
    }
	
	@Test
    public void addProjectTest()
    {
		Project projectOne = getTestProject();
         
        projectService.addNewProject(projectOne);
         
        verify(projectRepositoryDao, times(1)).addNewProject(projectOne);
    }
	
	@Test
    public void deleteProjectTest()
    {
		Project projectOne = getTestProject();
         
        projectService.deleteProject(projectOne);
         
        verify(projectRepositoryDao, times(1)).deleteProject(projectOne);
    }
	
	@Test
    public void updateProjectTest()
    {
		Project projectOne = getTestProject();
         
        projectService.updateProject(projectOne);
         
        verify(projectRepositoryDao, times(1)).updateProject(projectOne);
    }


	private Project getTestProject() {
		Project projectOne = new Project();
        projectOne.setProjectName("Test Project 1");
        projectOne.setPriority(1);
        projectOne.setStartDate(new Date());
		return projectOne;
	}
}
