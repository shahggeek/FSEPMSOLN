package pmserver.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.pm.model.Project;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectControllerTest extends AbstractTest {

	private static String projectId = "1"; 
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void addProject() throws Exception {
		String uri = "/projects";
		Project projectOne = new Project();
        projectOne.setProjectName("Test Project 1");
        projectOne.setPriority(1);
        projectOne.setStartDate(new Date());
        projectOne.setEndDate(new Date());
        
		String inputJson = super.mapToJson(projectOne);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		String [] locationTulips = mvcResult.getResponse().getHeader("Location").split("/");
		projectId = locationTulips[locationTulips.length-1];
		assertEquals(201, status);
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
	public void updateProject() throws Exception {
		String uri = "/projects/"+projectId;
		System.out.println("Update URI "+uri);
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
		
		mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(204, status);
	}
}
