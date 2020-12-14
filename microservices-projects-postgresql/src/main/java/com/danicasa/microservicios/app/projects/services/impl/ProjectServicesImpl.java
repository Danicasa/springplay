package com.danicasa.microservicios.app.projects.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danicasa.microservicios.app.projects.models.entity.Projects;
import com.danicasa.microservicios.app.projects.repository.ProjectRepository;
import com.danicasa.microservicios.app.projects.services.ProjectServices;

@Service
public class ProjectServicesImpl implements ProjectServices {
	
	@Autowired
	private ProjectRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Projects getById(Integer projectId) {
		return repository.findById(projectId).get();
		//return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Projects> getAll() {
		return (List<Projects>) repository.findAll();
		//return null;
	}

	@Override
	public Projects save(Projects project) {
		return repository.save(project);
		//return null;
	}

}
