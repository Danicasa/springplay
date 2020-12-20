package com.danicasa.microservicios.app.projects.service;

import java.util.List;

import com.danicasa.microservicios.app.projects.model.entity.Project;

public interface ProjectServices {
	
	public Project getById(Integer projectId);
	public List<Project> getAll();
	public Project save(Project project);
	public void delete(Project project);

}