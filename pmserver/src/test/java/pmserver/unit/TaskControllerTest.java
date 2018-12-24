package pmserver.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.pm.model.Task;

public class TaskControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
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
	
	@Test
	public void addTask() throws Exception {
		String uri = "/tasks";
		Task taskOne = new Task();
        taskOne.setTaskName("Test Task 1");
        taskOne.setPriority(1);
        taskOne.setStartDate(new Date());
        
		String inputJson = super.mapToJson(taskOne);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Task is added successfully");
	}

	@Test
	public void updateTask() throws Exception {
		String uri = "/tasks/1";
		Task taskOne = new Task();
        taskOne.setTaskName("Test Task 1");
        taskOne.setPriority(1);
        taskOne.setStartDate(new Date());
		String inputJson = super.mapToJson(taskOne);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Task is updated successsfully");
	}
	
	@Test
	public void deleteTask() throws Exception {
		String uri = "/tasks/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Task is deleted successsfully");
	}

}
