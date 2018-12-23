package pmserver.unit;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cts.pm.config.ApplicationConfig;
import com.cts.pm.main.ProjectManagementMain;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ApplicationConfig.class, ProjectManagementMain.class })
@EnableConfigurationProperties
@Transactional
public class HibernateSessionFactoryTest {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	 
	 
	 @Test
	 public void testSession(){
		 Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		 Assert.assertNotNull(session); 
	 }
}
