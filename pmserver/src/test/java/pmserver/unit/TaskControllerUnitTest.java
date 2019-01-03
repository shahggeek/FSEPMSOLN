package pmserver.unit;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.pm.config.ApplicationConfig;
import com.cts.pm.controller.TaskController;
import com.cts.pm.main.ProjectManagementMain;
import com.cts.pm.model.Task;
import com.cts.pm.model.User;
import com.cts.pm.service.ProjectService;
import com.cts.pm.service.TaskService;
import com.cts.pm.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TaskControllerUnitTest {

	private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @Mock
    private UserService userService;
    
    @Mock
    private TaskService taskService;

    
    @InjectMocks
    private TaskController taskController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(taskController)
                .addFilters(new CorsFilter())
                .build();
    }
    
    @Test
    public void testGetAllTasks() throws Exception {
    	Task taskOne = new Task();
        taskOne.setTaskName("Test Task 1");
        taskOne.setPriority(1);
        taskOne.setStartDate(new Date());
        taskOne.setEndDate(new Date());
        taskOne.setStatus("InProgress");
        
        List<Task> tasks = Arrays.asList(taskOne);
        when(taskService.getAllTasks()).thenReturn(tasks);
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(taskService, times(1)).getAllTasks();
        verifyNoMoreInteractions(taskService);
    }
    
    @Test
    public void testGetByIdSuccess() throws Exception {
    	Task taskOne = new Task();
        taskOne.setTaskName("Test Task 1");
        taskOne.setPriority(1);
        taskOne.setStartDate(new Date());
        taskOne.setEndDate(new Date());
        taskOne.setStatus("InProgress");
        
        when(taskService.getTask(1l)).thenReturn(taskOne);

        mockMvc.perform(get("/tasks/{id}", 1))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testAddTask_success() throws Exception {
    	
    	User userOne = new User();
        userOne.setEmployeeId(111);
        userOne.setFirstName("FirsTtestUser");
        userOne.setLastName("TestLast");
        userOne.setUserId(1l);
        
        Task taskOne = new Task();
        taskOne.setTaskName("Test Task 1");
        taskOne.setPriority(1);
        taskOne.setStartDate(new Date());
        taskOne.setEndDate(new Date());
        taskOne.setStatus("InProgress");
        taskOne.setUser(userOne);
        
        when(taskService.addNewTask(taskOne)).thenReturn(1l);
        when(userService.getUser(1l)).thenReturn(userOne);
        
        mockMvc.perform(
                post("/tasks").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(taskOne)))
                .andExpect(status().isCreated());
    }
    
    
    @Test
    public void testUpdateTask_success() throws Exception {
    	 Task taskOne = new Task();
         taskOne.setTaskName("Test Task 1");
         taskOne.setPriority(1);
         taskOne.setStartDate(new Date());
         taskOne.setEndDate(new Date());
         taskOne.setStatus("InProgress");
        
       doNothing().when(taskService).deleteTask(taskOne);
        
        mockMvc.perform(
                put("/tasks").content(asJsonString(taskOne)))
                .andExpect(status().isMethodNotAllowed());
    }
    
    @Test
    public void testDeleteTask_success() throws Exception {
    	 Task taskOne = new Task();
         taskOne.setTaskName("Test Task 1");
         taskOne.setPriority(1);
         taskOne.setStartDate(new Date());
         taskOne.setEndDate(new Date());
         taskOne.setStatus("InProgress");
         taskOne.setTaskId(1l);
         when(taskService.getTask(1l)).thenReturn(taskOne);
         doNothing().when(taskService).deleteTask(taskOne);
        
        mockMvc.perform(
                delete("/tasks").content(asJsonString(taskOne)))
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
