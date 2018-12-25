package pmserver.unit;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import com.cts.pm.controller.UserController;
import com.cts.pm.model.User;
import com.cts.pm.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerUnitTest {

	private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .addFilters(new CorsFilter())
                .build();
    }
    
    @Test
    public void testGetAllUsers() throws Exception {
    	User userOne = new User();
        userOne.setEmployeeId(111);
        userOne.setFirstName("FirsTtestUser");
        userOne.setLastName("TestLast");
        List<User> users = Arrays.asList(userOne);
        when(userService.getAllUsers()).thenReturn(users);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].employeeId", is(111)))
                .andExpect(jsonPath("$[0].firstName", is("FirsTtestUser")));
        verify(userService, times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);
    }
    
    @Test
    public void testGetByIdSuccess() throws Exception {
    	User userOne = new User();
        userOne.setEmployeeId(111);
        userOne.setFirstName("FirsTtestUser");
        userOne.setLastName("TestLast");

        when(userService.getUser(1l)).thenReturn(userOne);

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.employeeId", is(111)))
                .andExpect(jsonPath("$.firstName", is("FirsTtestUser")));

        verify(userService, times(1)).getUser(1l);
        verifyNoMoreInteractions(userService);
    }
    
    @Test
    public void testGetById_fail_404_not_found() throws Exception {

        when(userService.getUser(1l)).thenReturn(null);

        mockMvc.perform(get("/users/{id}", 1)).andExpect(status().isNotFound());

        verify(userService, times(1)).getUser(1l);
        verifyNoMoreInteractions(userService);
    }
    
    @Test
    public void testAddUser_success() throws Exception {
    	User userOne = new User();
        userOne.setEmployeeId(111);
        userOne.setFirstName("FirsTtestUser");
        userOne.setLastName("TestLast");
        
        when(userService.addNewUser(userOne)).thenReturn(1l);
        
        mockMvc.perform(
                post("/users").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userOne)))
                .andExpect(status().isCreated());
        verify(userService, times(1)).addNewUser(userOne);
        verifyNoMoreInteractions(userService);
    }
    
    @Test
    public void testUpdateUser_success() throws Exception {
    	User userOne = new User();
        userOne.setEmployeeId(111);
        userOne.setFirstName("FirsTtestUser");
        userOne.setLastName("TestLast");
        
       // doNothing().when(userService.updateUser(userOne));
        
        mockMvc.perform(
                post("/users").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userOne)))
                .andExpect(status().isCreated());
        verify(userService, times(1)).addNewUser(userOne);
        verifyNoMoreInteractions(userService);
    }
    
    @Test
    public void test_cors_headers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"));
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
