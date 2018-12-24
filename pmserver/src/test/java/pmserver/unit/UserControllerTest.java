package pmserver.unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.pm.model.User;

public class UserControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getUsersList() throws Exception {
		String uri = "/users";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User[] userList = super.mapFromJson(content, User[].class);
		assertTrue(userList.length > 0);
	}
	
	@Test
	public void addUser() throws Exception {
		String uri = "/users";
		User userOne = new User();
        userOne.setEmployeeId(111);
        userOne.setFirstName("FirsTtestUser");
        userOne.setLastName("TestLast");
        
		String inputJson = super.mapToJson(userOne);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "User is added successfully");
	}

	@Test
	public void updateUser() throws Exception {
		String uri = "/users/1";
		User userOne = new User();
        userOne.setEmployeeId(111);
        userOne.setFirstName("FirsTtestUser");
        userOne.setLastName("TestLast");
		String inputJson = super.mapToJson(userOne);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "User is updated successsfully");
	}
	
	@Test
	public void deleteUser() throws Exception {
		String uri = "/users/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "User is deleted successsfully");
	}

}
