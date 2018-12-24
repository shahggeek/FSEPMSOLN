package pmserver.unit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cts.pm.model.User;
import com.cts.pm.repository.UserRepositoryDao;
import com.cts.pm.service.UserServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepositoryDao userRepositoryDao;

	@InjectMocks
	private UserServiceImpl userService;


	@Test
    public void getAllUsersTest() throws SQLException
    {
        List<User> list = new ArrayList<User>();
        User userOne = getTestUser();
        
        User userTwo = new User();
        userTwo.setEmployeeId(222);
        userTwo.setFirstName("SecondtestUser");
        userTwo.setLastName("TestSecondLast");
         
        list.add(userOne);
        list.add(userTwo);
         
        when(userRepositoryDao.getAllUsers()).thenReturn(list);
         
        //test
        List<User> userList =userService.getAllUsers();
         
        assertEquals(2, userList.size());
        verify(userRepositoryDao, times(1)).getAllUsers();
    }
	
	  
    @Test
    public void getUserTest() throws SQLException
    {
    	User userOne = getTestUser();
        
        when(userRepositoryDao.getUser(1l)).thenReturn(userOne);
         
        User user = userService.getUser(1l);
         
        assertEquals("FirsTtestUser", user.getFirstName());
        assertEquals("TestLast", user.getLastName());
    }


	@Test
    public void addUserTest()
    {
		User userOne = getTestUser();
         
        userService.addNewUser(userOne);
         
        verify(userRepositoryDao, times(1)).addNewUser(userOne);
    }
	
	@Test
    public void deleteUserTest()
    {
		User userOne = getTestUser();
         
        userService.deleteUser(userOne);
         
        verify(userRepositoryDao, times(1)).deleteUser(userOne);
    }
	
	@Test
    public void updateUserTest()
    {
		User userOne = getTestUser();
         
        userService.updateUser(userOne);
         
        verify(userRepositoryDao, times(1)).updateUser(userOne);
    }
	

	private User getTestUser() {
		User userOne = new User();
        userOne.setEmployeeId(111);
        userOne.setFirstName("FirsTtestUser");
        userOne.setLastName("TestLast");
		return userOne;
	}
	
}
