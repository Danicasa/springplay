package com.danicasa.microservicios.app.projects.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danicasa.microservicios.app.projects.models.entity.Project;
import com.danicasa.microservicios.app.projects.services.ProjectServices;


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
	public ResponseEntity<?> getById(@PathVariable Long projectId){
		Project project = service.getById(projectId);
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
	
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Project project){
		Project projectDB = service.save(project);
		return ResponseEntity.status(HttpStatus.CREATED).body(projectDB);
	}
	
}
