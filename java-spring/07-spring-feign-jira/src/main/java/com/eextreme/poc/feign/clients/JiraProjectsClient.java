package com.eextreme.poc.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "jira-projects", url = "${spring.feign.poc.jira-url}")
public interface JiraProjectsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/rest/api/3/project")
    List<Object> findAll();
}
