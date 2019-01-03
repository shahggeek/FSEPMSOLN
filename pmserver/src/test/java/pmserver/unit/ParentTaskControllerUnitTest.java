package pmserver.unit;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
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

import com.cts.pm.controller.ParentTaskController;
import com.cts.pm.model.ParentTask;
import com.cts.pm.service.ParentTaskService;
import com.cts.pm.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParentTaskControllerUnitTest {

	private MockMvc mockMvc;

    @Mock
    private UserService userService;
    
    @Mock
    private ParentTaskService parenttaskService;

    
    @InjectMocks
    private ParentTaskController parenttaskController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(parenttaskController)
                .addFilters(new CorsFilter())
                .build();
    }
    
    @Test
    public void testGetAllTasks() throws Exception {
    	
    	ParentTask parentTaskOne = new ParentTask();
    	parentTaskOne.setParentId(1l);
    	parentTaskOne.setParentTaskName("Test parent task");
        
        List<ParentTask> parenttasks = Arrays.asList(parentTaskOne);
        when(parenttaskService.getAllParentTasks()).thenReturn(parenttasks);
        mockMvc.perform(get("/parenttasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(parenttaskService, times(1)).getAllParentTasks();
        verifyNoMoreInteractions(parenttaskService);
    }
    
    @Test
    public void testGetByIdSuccess() throws Exception {
    	ParentTask parentTaskOne = new ParentTask();
    	parentTaskOne.setParentId(1l);
    	parentTaskOne.setParentTaskName("Test parent task");
        
        when(parenttaskService.getParentTask(1l)).thenReturn(parentTaskOne);

        mockMvc.perform(get("/parenttasks/{id}", 1))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testAddParentTask_success() throws Exception {
    	
    	ParentTask parentTaskOne = new ParentTask();
    	parentTaskOne.setParentId(1l);
    	parentTaskOne.setParentTaskName("Test parent task");
        
        
        when(parenttaskService.addNewParentTask(parentTaskOne)).thenReturn(1l);
        
        mockMvc.perform(
                post("/parenttasks").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(parentTaskOne)))
                .andExpect(status().isCreated());
    }
    
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
