package pmserver.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cts.pm.model.ParentTask;
import com.cts.pm.repository.ParentTaskRepositoryDao;
import com.cts.pm.service.ParentTaskServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ParentTaskServiceTest {

	@Mock
	private ParentTaskRepositoryDao parenttaskRepositoryDao;

	@InjectMocks
	private ParentTaskServiceImpl parenttaskService ;

	
	@Test
    public void getAllParentParentTasksTest() throws SQLException
    {
        List<ParentTask> list = new ArrayList<ParentTask>();
        ParentTask parenttaskOne = getTestParentTask();
        ParentTask parenttaskTwo = new ParentTask();
        parenttaskTwo.setParentTaskName("Test ParentTask 2");
        list.add(parenttaskOne);
        list.add(parenttaskTwo);
        
         
        when(parenttaskRepositoryDao.getAllParentTasks()).thenReturn(list);
         
        //test
        List<ParentTask> parenttaskList = parenttaskService.getAllParentTasks();
         
        assertEquals(2, parenttaskList.size());
        verify(parenttaskRepositoryDao, times(1)).getAllParentTasks();
    }
	
	  
    @Test
    public void getParentTaskTest() throws SQLException
    {
    	ParentTask parenttaskOne = getTestParentTask();
        
        when(parenttaskRepositoryDao.getParentTask(1l)).thenReturn(parenttaskOne);
         
        ParentTask parenttask = parenttaskService.getParentTask(1l);
         
        assertEquals("Test ParentTask 1", parenttask.getParentTaskName());
    }
	
	@Test
    public void addParentTaskTest()
    {
		ParentTask parenttaskOne = getTestParentTask();
         
        parenttaskService.addNewParentTask(parenttaskOne);
         
        verify(parenttaskRepositoryDao, times(1)).addNewParentTask(parenttaskOne);
    }

	private ParentTask getTestParentTask() {
		ParentTask parenttaskOne = new ParentTask();
        parenttaskOne.setParentTaskName("Test ParentTask 1");
		return parenttaskOne;
	}
}
