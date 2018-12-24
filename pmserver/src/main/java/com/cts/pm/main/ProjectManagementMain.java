package com.cts.pm.main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cts.pm")
@EntityScan( basePackages = {"com.cts.pm"} )
public class ProjectManagementMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectManagementMain.class);
	
	public static void main( String[] args )
    {
		LOGGER.info("Starting project management app");
    	SpringApplication.run(ProjectManagementMain.class, args);
    }
}
