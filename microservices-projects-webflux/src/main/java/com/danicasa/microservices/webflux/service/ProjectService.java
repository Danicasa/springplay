package com.danicasa.microservices.webflux.service;

import com.danicasa.microservices.webflux.models.document.Project;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectService {
	
	public Flux<Project> findAll();	
	public Mono<Project> findById(long id);	
	public Mono<Project> save(Project producto);	
	public Mono<Void> delete(Project producto);
	
}
