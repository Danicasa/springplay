package com.danicasa.microservices.webflux.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danicasa.microservices.webflux.models.dao.ProjectDao;
import com.danicasa.microservices.webflux.models.document.Project;
import com.danicasa.microservices.webflux.service.ProjectService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectDao dao;
	
	@Override
	public Flux<Project> findAll() {
		return dao.findAll();
	}

	@Override
	public Mono<Project> findById(long id) {
		return dao.findById(id);
	}

	@Override
	public Mono<Project> save(Project producto) {
		return dao.save(producto);
	}

	@Override
	public Mono<Void> delete(Project producto) {
		return dao.delete(producto);
	}


}
