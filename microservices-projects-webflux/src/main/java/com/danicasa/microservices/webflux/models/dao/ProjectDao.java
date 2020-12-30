package com.danicasa.microservices.webflux.models.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.danicasa.microservices.webflux.models.document.Project;

public interface ProjectDao extends ReactiveMongoRepository<Project, Long>{

}
