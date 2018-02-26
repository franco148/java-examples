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
 * 
 * There are a few starters for technical stuff as well
 * -spring-boot-starter-actuator: To use advanced features like monitoring
 *  & tracing to your application out the box.
 * - spring-boot-starter-undertow, spring-boot-starter-jetty, spring-boot-starter-tomcat:
 *  To pick your specific choice of Embedded Servlet Container
 * -spring-boot-starter-logging: For logging using logback
 * -spring-boot-starter-log4j2: Logging using Log4j2
 * 
 * Spring Boot aims to enable production ready applications in quick time.
 * -Actuator: Enables advanced monitoring and tracing of applications.
 * -Embedded Server Integrations: Since server is integrated into the application, I
 *  would need to have a separate application server installed on the server.
 * -Default Error Handling.
 * 
 * SpringBoot Actuator: Give us all information about the service.
 * SpringBoot Devtools: helps us to restart the application automatically, it means any changes in our
 * 						java code we will not need to stop and start again the service (because it takes more time).
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
