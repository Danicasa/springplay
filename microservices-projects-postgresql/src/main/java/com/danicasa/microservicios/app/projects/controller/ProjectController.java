package com.danicasa.microservicios.app.projects.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danicasa.microservices.app.expection.ResourceNotFoundException;
import com.danicasa.microservicios.app.projects.model.entity.Project;
import com.danicasa.microservicios.app.projects.service.ProjectServices;



@RestController
public class ProjectController {

	@Autowired
	private ProjectServices service;
		
	@GetMapping("/")
	public ResponseEntity<?> get(){
		List<Project> project = service.getAll();
		return ResponseEntity.ok(project);
	}
	
	@GetMapping("/get/{projectId}")
	public ResponseEntity<?> getById(@PathVariable int projectId) throws ResourceNotFoundException {
		Project project = service.getById(projectId);
		if(project != null)
			new ResourceNotFoundException("Project not found for this id : " + projectId);
		return ResponseEntity.ok(project);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		List<Project> project = service.getAll();
		return ResponseEntity.ok(project);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Project project){
		Project projectDB = service.save(project);
		return ResponseEntity.status(HttpStatus.CREATED).body(projectDB);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Project project) throws ResourceNotFoundException {
		Project projectDB = service.getById(project.getId());
		if(projectDB != null)
			new ResourceNotFoundException("Project not found for this id : " + project.getId());
		
		projectDB.setDescription(project.getDescription());
		projectDB.setName(project.getName());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(projectDB);
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") int projectId) throws ResourceNotFoundException {
		
		Project project = service.getById(projectId);
		if(project != null)
			new ResourceNotFoundException("Project not found for this id : " + projectId);

		service.delete(project);
		Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
	}
	
}
