package pmserver.unit;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.pm.controller.ProjectController;
import com.cts.pm.exceptions.DataAccessException;
import com.cts.pm.model.Project;
import com.cts.pm.model.Task;
import com.cts.pm.model.User;
import com.cts.pm.service.ProjectService;
import com.cts.pm.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProjectControllerUnitTest {

	private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @Mock
    private UserService userService;

    
    @InjectMocks
    private ProjectController projectController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(projectController)
                .addFilters(new CorsFilter())
                .build();
    }
    
    @Test
    public void testGetAllProjects() throws Exception {
    	Project projectOne = new Project();
        projectOne.setProjectName("Test Project 1");
        projectOne.setPriority(1);
        projectOne.setStartDate(new Date());
        projectOne.setEndDate(new Date());
        
        List<Project> projects = Arrays.asList(projectOne);
        when(projectService.getAllProjects()).thenReturn(projects);
        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].projectName", is("Test Project 1")));
        verify(projectService, times(1)).getAllProjects();
        verifyNoMoreInteractions(projectService);
    }
    
    @Test
    public void testGetByIdSuccess() throws Exception {
    	Project projectOne = new Project();
        projectOne.setProjectName("Test Project 1");
        projectOne.setPriority(1);
        projectOne.setStartDate(new Date());
        projectOne.setEndDate(new Date());

        when(projectService.getProject(1l)).thenReturn(projectOne);

        mockMvc.perform(get("/projects/{id}", 1))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testAddProject_success() throws Exception {
    	
    	User userOne = new User();
        userOne.setEmployeeId(111);
        userOne.setFirstName("FirsTtestUser");
        userOne.setLastName("TestLast");
        userOne.setUserId(1l);
        
    	Project projectOne = new Project();
        projectOne.setProjectName("Test Project 1");
        projectOne.setPriority(1);
        projectOne.setStartDate(new Date());
        projectOne.setEndDate(new Date());
        projectOne.setUser(userOne);
        
        when(projectService.addNewProject(projectOne)).thenReturn(1l);
        when(userService.getUser(1l)).thenReturn(userOne);
        
        mockMvc.perform(
                post("/projects").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(projectOne)))
                .andExpect(status().isCreated());
    }
    
    
    @Test
    public void testUpdateUser_success() throws Exception {
    	Project projectOne = new Project();
        projectOne.setProjectName("Test Project 1");
        projectOne.setPriority(1);
        projectOne.setStartDate(new Date());
        projectOne.setEndDate(new Date());
        
       doNothing().when(projectService).updateProject(projectOne);
        
        mockMvc.perform(
                put("/projects").content(asJsonString(projectOne)))
                .andExpect(status().isMethodNotAllowed());
    }
    
    @Test
    public void testDeleteProject_success() throws Exception {
    	Project projectOne = new Project();
        projectOne.setProjectName("Test Project 1");
        projectOne.setPriority(1);
        projectOne.setStartDate(new Date());
        projectOne.setEndDate(new Date());
        projectOne.setProjectId(1l);
        
         when(projectService.getProject(1l)).thenReturn(projectOne);
         doNothing().when(projectService).deleteProject(projectOne);
        
        mockMvc.perform(
                delete("/projects").content(asJsonString(projectOne)))
                .andExpect(status().isMethodNotAllowed());
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
