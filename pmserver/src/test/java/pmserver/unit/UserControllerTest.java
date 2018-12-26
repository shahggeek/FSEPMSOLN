package pmserver.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.pm.model.User;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest extends AbstractTest {

	private static String userId; 
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
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
		String [] locationTulips = mvcResult.getResponse().getHeader("Location").split("/");
		userId = locationTulips[locationTulips.length-1];
		assertNotNull(userId);
		assertEquals(201, status);
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
	public void updateUser() throws Exception {
		String uri = "/users/"+userId;
		System.out.println("Update URI "+uri);
		User userOne = new User();
        userOne.setEmployeeId(111);
        userOne.setFirstName("UpdatedFisrtName");
        userOne.setLastName("UpdatedLastName");
		String inputJson = super.mapToJson(userOne);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(204, status);
	}
	
}
