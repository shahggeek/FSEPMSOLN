package pmserver.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.pm.model.Project;

public class ProjectControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getProjectList() throws Exception {
		String uri = "/projects";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Project[] projectList = super.mapFromJson(content, Project[].class);
		assertTrue(projectList.length > 0);
	}
	
	@Test
	public void addProject() throws Exception {
		String uri = "/projects";
		Project projectOne = new Project();
        projectOne.setProjectName("Test Project 1");
        projectOne.setPriority(1);
        projectOne.setStartDate(new Date());
        
		String inputJson = super.mapToJson(projectOne);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Project is added successfully");
	}

	@Test
	public void updateProject() throws Exception {
		String uri = "/projects/1";
		Project projectOne = new Project();
        projectOne.setProjectName("Test Project 1");
        projectOne.setPriority(1);
        projectOne.setStartDate(new Date());
		String inputJson = super.mapToJson(projectOne);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Project is updated successsfully");
	}
	
	@Test
	public void deleteProject() throws Exception {
		String uri = "/projects/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Project is deleted successsfully");
	}

}
