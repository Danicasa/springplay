package com.danicasa.microservices.app.projectControllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danicasa.microservicios.app.projects.MicroservicioProjectsApplication;
import com.danicasa.microservicios.app.projects.model.entity.Project;

@SpringBootTest(classes = MicroservicioProjectsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
    @LocalServerPort
    private int port;
	
	private String getRootUrl() {
		return "http://localhost:" + port;
	}
	
	@Test
    public void contextLoads() {

    }
	
	@Test
    public void testGetAllProjects() {
    HttpHeaders headers = new HttpHeaders();
       HttpEntity<String> entity = new HttpEntity<String>(null, headers);
       ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/getAll",
       HttpMethod.GET, entity, String.class);  
       assertNotNull(response.getBody());
   }
	
    @Test
    public void testGetProjectById() {
        Project project = restTemplate.getForObject(getRootUrl() + "/get/1", Project.class);
        assertNotNull(project.getId());
    }
    
    @Test
    public void testGetProjectById_fails_when_id_not_exist() {
        Project project = restTemplate.getForObject(getRootUrl() + "/get/1000000", Project.class);
        assertNull(project.getId());
    }
    
    @Test
    public void testCreateProject() {
    	Project project = new Project();
    	project.setName("name for new project");
    	project.setDescription("description for new project");
    	ResponseEntity<Project> postResponse =restTemplate.postForEntity(getRootUrl() + "/save", project, Project.class);
    	assertEquals(postResponse.getStatusCode(), HttpStatus.CREATED);
    	assertNotNull(postResponse.getBody().getId());
    }


}
