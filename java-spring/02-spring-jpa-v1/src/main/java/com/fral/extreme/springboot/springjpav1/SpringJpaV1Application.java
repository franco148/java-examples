package com.fral.extreme.springboot.springjpav1;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @SpringBootApplication defines the context of the project.
 * - It defines the context
 * - it defines the autoconfiguration
 * - it defines the componentscan
 * 
 * Spring Boot looks at
 * a) Frameworks available on the CLASSPATH
 * b) Existing configuration for the application. Based on these,
 *    Sprint Boot provides basic configuration needed to configure
 *    the application with these frameworks. This is called "Auto Configuration"
 * 
 * @author Franco
 *
 */
@SpringBootApplication
public class SpringJpaV1Application {

	public static void main(String[] args) {
		//The following sentence returns an ApplicationContext
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringJpaV1Application.class, args);
		
		for (String name : applicationContext.getBeanDefinitionNames()) {
			//System.out.println(name);
		}
	}
}
