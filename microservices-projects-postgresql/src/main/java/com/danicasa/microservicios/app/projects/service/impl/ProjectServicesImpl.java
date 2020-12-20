package com.danicasa.microservicios.app.projects.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danicasa.microservicios.app.projects.model.entity.Project;
import com.danicasa.microservicios.app.projects.repository.ProjectRepository;
import com.danicasa.microservicios.app.projects.service.ProjectServices;

@Service
public class ProjectServicesImpl implements ProjectServices {
	
	@Autowired
	private ProjectRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Project getById(Integer projectId) {
		return repository.findById(projectId).get();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Project> getAll() {
		return (List<Project>) repository.findAll();
	}

	@Override
	public Project save(Project project) {
		return repository.save(project);
	}

	@Override
	public void delete(Project project) {
		repository.delete(project);				
	}

}
