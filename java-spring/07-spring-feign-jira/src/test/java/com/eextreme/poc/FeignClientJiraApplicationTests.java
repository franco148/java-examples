package com.eextreme.poc;

import com.eextreme.poc.feign.JiraProjectsClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertFalse;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeignClientJiraApplicationTests {

	@Autowired
	private JiraProjectsClient jiraClient;

	@Test
	public void contextLoads() {
	}

	@Test
	public void shouldLoadAllProject() {
		final List<Object> jiraProjects = jiraClient.findAll();
		Assert.assertNotNull(jiraProjects);
		Assert.assertFalse(jiraProjects.isEmpty());
	}
}
