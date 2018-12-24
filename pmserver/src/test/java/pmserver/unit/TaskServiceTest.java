package pmserver.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cts.pm.model.Task;
import com.cts.pm.repository.TaskRepositoryDao;
import com.cts.pm.service.TaskServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

	@Mock
	private TaskRepositoryDao taskRepositoryDao;

	@InjectMocks
	private TaskServiceImpl taskService ;


	@Test
    public void getAllTasksTest() throws SQLException
    {
        List<Task> list = new ArrayList<Task>();
        Task taskOne = getTestTask();
        
        Task taskTwo = new Task();
        taskTwo.setTaskName("Test Task 2");
        taskTwo.setPriority(2);
        taskTwo.setStartDate(new Date());
        
        list.add(taskOne);
        list.add(taskTwo);
        
         
        when(taskRepositoryDao.getAllTasks()).thenReturn(list);
         
        //test
        List<Task> taskList = taskService.getAllTasks();
         
        assertEquals(2, taskList.size());
        verify(taskRepositoryDao, times(1)).getAllTasks();
    }
	
	  
    @Test
    public void getTaskTest() throws SQLException
    {
    	Task taskOne = getTestTask();
        
        when(taskRepositoryDao.getTask(1l)).thenReturn(taskOne);
         
        Task task = taskService.getTask(1l);
         
        assertEquals("Test Task 1", task.getTaskName());
        assertEquals(1, task.getPriority());
    }
	
	@Test
    public void addTaskTest()
    {
		Task taskOne = getTestTask();
         
        taskService.addNewTask(taskOne);
         
        verify(taskRepositoryDao, times(1)).addNewTask(taskOne);
    }
	
	@Test
    public void deleteTaskTest()
    {
		Task taskOne = getTestTask();
         
        taskService.deleteTask(taskOne);
         
        verify(taskRepositoryDao, times(1)).deleteTask(taskOne);
    }
	
	@Test
    public void updateTaskTest()
    {
		Task taskOne = getTestTask();
         
        taskService.updateTask(taskOne);
         
        verify(taskRepositoryDao, times(1)).updateTask(taskOne);
    }


	private Task getTestTask() {
		Task taskOne = new Task();
        taskOne.setTaskName("Test Task 1");
        taskOne.setPriority(1);
        taskOne.setStartDate(new Date());
		return taskOne;
	}
}
