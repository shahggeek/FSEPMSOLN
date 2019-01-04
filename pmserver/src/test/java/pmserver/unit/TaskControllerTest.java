package pmserver.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.pm.model.Task;
import com.cts.pm.model.User;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskControllerTest extends AbstractTest {

	private static String taskId = "3"; 
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void addTask() throws Exception {
		String usersuri = "/users";
		User userOne = new User();
        userOne.setEmployeeId(111);
        userOne.setFirstName("FirsTtestUser");
        userOne.setLastName("TestLast");
        
		String inputUserJson = super.mapToJson(userOne);
		MvcResult mvcUserResult = mvc.perform(
				MockMvcRequestBuilders.post(usersuri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputUserJson))
				.andReturn();

		String [] userlocationTulips = mvcUserResult.getResponse().getHeader("Location").split("/");
		String userId = userlocationTulips[userlocationTulips.length-1];
		
		String uri = "/tasks";
		Task taskOne = new Task();
        taskOne.setTaskName("Test Task 1");
        taskOne.setPriority(1);
        taskOne.setStartDate(new Date());
        taskOne.setEndDate(new Date());
        taskOne.setStatus("InProgress");
        userOne.setUserId(Long.valueOf(userId));
        taskOne.setUser(userOne);
		String inputJson = super.mapToJson(taskOne);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		String [] locationTulips = mvcResult.getResponse().getHeader("Location").split("/");
		taskId = locationTulips[locationTulips.length-1];
		assertNotNull(taskId);
		assertEquals(201, status);
	}

	@Test
	public void getTaskList() throws Exception {
		String uri = "/tasks";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Task[] taskList = super.mapFromJson(content, Task[].class);
		assertTrue(taskList.length > 0);
	}
	
}
