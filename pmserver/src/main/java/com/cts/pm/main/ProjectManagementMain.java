package com.cts.pm.main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cts.pm")
@EntityScan( basePackages = {"com.cts.pm"} )
public class ProjectManagementMain {

	public static void main( String[] args )
    {
    	SpringApplication.run(ProjectManagementMain.class, args);
    }
}
