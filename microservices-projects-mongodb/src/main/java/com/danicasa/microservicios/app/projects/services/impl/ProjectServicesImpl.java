package com.danicasa.microservicios.app.projects.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danicasa.microservicios.app.projects.models.entity.Project;
import com.danicasa.microservicios.app.projects.repository.ProjectRepository;
import com.danicasa.microservicios.app.projects.services.ProjectServices;

@Service
public class ProjectServicesImpl implements ProjectServices {
	
	@Autowired
	private ProjectRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Project getById(Long projectId) {
		return repository.findById(projectId).get();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Project> getAll() {
		return repository.findAll();
	}

	@Override
	public Project save(Project project) {
		return repository.save(project);
	}

}
